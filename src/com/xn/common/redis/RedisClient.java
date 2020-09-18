package com.xn.common.redis;

import com.xn.common.util.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
import redis.clients.util.Pool;

import java.util.*;

/**
 * RedisClient,支持JedisPool和JedisSentinelPool
 * <br/><br/>
 *  注意:本客户端不支持多key操作,因为考虑到以后要切换到RedisCluster,cluster对多key操作、事务、pipeline的支持不够好（因为多个key有可能分散在不同的redis上）
 */
public class RedisClient implements JedisCommands,InitializingBean,DisposableBean {

    private static final Logger log = Logger.getLogger(RedisClient.class);

    private static final int DEFAULT_CONN_OR_SO_TIMEOUT = 1000;//redis默认的连接和socketTimeout超时时间

    private Pool<Jedis> jedisPool;

    private Thread monitorThread;
    private Integer maxPoolSize;
    private Integer warnPoolSize;//超过这个poolsize需要打印报警日志

    /**
     * 构造RedisClient,使用默认2秒的超时时间,使用默认的连接池配置
     * @param isUseSentinel 是否使用RedisSentinel
     * @param sentinelMasterName 当isUseSentinel为真时有用,指定Sentinel的masterName.
     * @param servers 当isUseSentinel为真时表示RedisSentinel的地址列表,格式:(ip1:port,ip2:port),不包含括号;当isUseSentinel为假时表示redis服务器地址,格式:（ip:port）
     * @param password 密码,无密码则传入null
     */
    public RedisClient(boolean isUseSentinel, String sentinelMasterName, String servers, String password) {
        init(isUseSentinel, sentinelMasterName, servers, password, DEFAULT_CONN_OR_SO_TIMEOUT, getDefaultConfig());
    }

    /**
     * 构造RedisClient,使用默认的连接池配置
     * @param isUseSentinel 是否使用RedisSentinel
     * @param sentinelMasterName 当isUseSentinel为真时有用,指定Sentinel的masterName.
     * @param servers 当isUseSentinel为真时表示RedisSentinel的地址列表,格式:(ip1:port,ip2:port),不包含括号;当isUseSentinel为假时表示redis服务器地址,格式:（ip:port）
     * @param password 密码,无密码则传入null
     * @param connOrSoTimeout 服务器ConnectionTimeout 和 SocketTimeout的毫秒数
     */
    public RedisClient(boolean isUseSentinel, String sentinelMasterName, String servers, String password, int connOrSoTimeout) {
        init(isUseSentinel, sentinelMasterName, servers, password, connOrSoTimeout, getDefaultConfig());
    }

    /**
     * 构造RedisClient
     * @param isUseSentinel 是否使用RedisSentinel
     * @param sentinelMasterName 当isUseSentinel为真时有用,指定Sentinel的masterName.
     * @param servers 当isUseSentinel为真时表示RedisSentinel的地址列表,格式:(ip1:port,ip2:port),不包含括号;当isUseSentinel为假时表示redis服务器地址,格式:（ip:port）
     * @param password 密码,无密码则传入null
     * @param connOrSoTimeout 服务器ConnectionTimeout 和 SocketTimeout的毫秒数
     * @param maxPoolSize redis连接池最大值
     */
    public RedisClient(boolean isUseSentinel, String sentinelMasterName, String servers, String password, int connOrSoTimeout, Integer maxPoolSize) {
        JedisPoolConfig config = getDefaultConfig();
        config.setMaxTotal(maxPoolSize);
        config.setMaxIdle(maxPoolSize);
        if(config.getMinIdle() > maxPoolSize){
            config.setMinIdle(maxPoolSize);
        }
        init(isUseSentinel, sentinelMasterName, servers, password, connOrSoTimeout, config);
    }

    /**
     * 构造RedisClient
     * @param isUseSentinel 是否使用RedisSentinel
     * @param sentinelMasterName 当isUseSentinel为真时有用,指定Sentinel的masterName.
     * @param servers 当isUseSentinel为真时表示RedisSentinel的地址列表,格式:(ip1:port,ip2:port),不包含括号;当isUseSentinel为假时表示redis服务器地址,格式:（ip:port）
     * @param password 密码,无密码则传入null
     * @param connOrSoTimeout 服务器ConnectionTimeout 和 SocketTimeout的毫秒数
     * @param config redis连接池配置
     */
    public RedisClient(boolean isUseSentinel, String sentinelMasterName, String servers, String password, int connOrSoTimeout, JedisPoolConfig config) {
        init(isUseSentinel, sentinelMasterName, servers, password, connOrSoTimeout, config);
    }

    private void init(boolean isUseSentinel, String sentinelMasterName, String servers, String password, int connOrSoTimeout, JedisPoolConfig config){
        String[] serverArr = servers.split(",");
        List<String> serverList = new ArrayList<>();
        for(String s : serverArr){
            if(!StringUtils.isBlank(s)) {
                serverList.add(s);
            }
        }
        if(isUseSentinel){
            jedisPool = new JedisSentinelPool(sentinelMasterName, new HashSet<String>(serverList), config, connOrSoTimeout, password);
        }else{
            if(serverList.size() != 1){
                throw new IllegalArgumentException("when not using sentinel, [servers] must contains one and only one node.");
            }
            String[] serverConfig = serverList.get(0).split(":");
            String host = serverConfig[0];
            int port = NumberUtils.parseInt(serverConfig[1], 0);
            maxPoolSize = config.getMaxTotal();
            warnPoolSize = maxPoolSize / 2;
            jedisPool = new JedisPool(config, host, port, connOrSoTimeout, password);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //每隔5秒扫描一次，查看redis连接池是否正常，超过阈值则打印日志
        monitorThread = new Thread(){
            @Override
            public void run(){
                while(true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        break;
                    }
                    if(Thread.currentThread().isInterrupted()){
                        break;
                    }

                    int numActive = jedisPool.getNumActive();
                    int numIdle = jedisPool.getNumIdle();
                    int numWaiters = jedisPool.getNumWaiters();
                    if(numActive > warnPoolSize || numWaiters > 1){
//                        log.warn("redis 告警,maxTotal:{},numActive:{},numIdle:{},numWaiters:{}",maxPoolSize,numActive,numIdle,numWaiters);
                    }
                }
            }
        };

        monitorThread.start();
    }

    @Override
    public void destroy() throws Exception {
        monitorThread.interrupt();
    }

    private JedisPoolConfig getDefaultConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(5);
        config.setTestOnBorrow(false);
        config.setMaxTotal(60);
        config.setMaxIdle(config.getMaxTotal());
        config.setMaxWaitMillis(200);//Borrow最大等待时间
        config.setTestWhileIdle(true);
        config.setTimeBetweenEvictionRunsMillis(90000);//每隔90S testWhileIdle一次
        return config;
    }

    /**
     * 从连接池获得一个redis连接,使用完毕后必须close掉,否则连接池会被耗尽.
     * <br/>
     * 该方法目前私有,是为了防止客户端获得后忘了close.Redis的管道/事务调用方式不好封装,后续如果有需求可以考虑放开,或者把管道/事务也封装一下
     *
     * @return jedis连接,使用完后务必调用jedis.close()释放资源占用
     */
    private Jedis getResource() {
        return jedisPool.getResource();
    }

    /**
     * 销毁连接池,一般在应用退出前调用
     */
    public void close() {
        jedisPool.close();
    }

    /**
     * Set the string value as value of the key. The string can't be longer than 1073741824 bytes (1
     * GB).
     * <p>
     * Time complexity: O(1)
     * @param key
     * @param value
     * @return Status code reply
     */
    @Override
    public String set(String key, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String set(String key, String value, String nxxx, String expx, long time) {
        Jedis jedis = getResource();
        try {
            return jedis.set(key, value, nxxx, expx, time);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String set(String key, String value, String nxxx) {
        Jedis jedis = getResource();
        try {
            return jedis.set(key, value, nxxx);
        } finally {
            jedis.close();
        }
    }

    /**
     * 获得key对应的字符串value
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        long start = System.currentTimeMillis();
        Jedis jedis = getResource();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
            long period = System.currentTimeMillis() - start;
            if(period > 50){
//                log.warn("redis.get cost {} ms, too long,key is:{}", period,key);
            }
        }
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.exists(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long persist(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.persist(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String type(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.type(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = getResource();
        try {
            return jedis.expire(key, seconds);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        Jedis jedis = getResource();
        try {
            return jedis.pexpire(key, milliseconds);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        Jedis jedis = getResource();
        try {
            return jedis.expireAt(key, unixTime);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        Jedis jedis = getResource();
        try {
            return jedis.pexpireAt(key, millisecondsTimestamp);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.ttl(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long pttl(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.pttl(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        Jedis jedis = getResource();
        try {
            return jedis.setbit(key, offset, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.setbit(key, offset, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Boolean getbit(String key, long offset) {
        Jedis jedis = getResource();
        try {
            return jedis.getbit(key, offset);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.setrange(key, offset, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        Jedis jedis = getResource();
        try {
            return jedis.getrange(key, startOffset, endOffset);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String getSet(String key, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.getSet(key, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long setnx(String key, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.setnx(key, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String setex(String key, int seconds, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.setex(key, seconds, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String psetex(String key, long milliseconds, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.psetex(key, milliseconds, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long decrBy(String key, long integer) {
        Jedis jedis = getResource();
        try {
            return jedis.decrBy(key, integer);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.decr(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long incrBy(String key, long integer) {
        Jedis jedis = getResource();
        try {
            return jedis.incrBy(key, integer);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Double incrByFloat(String key, double value) {
        Jedis jedis = getResource();
        try {
            return jedis.incrByFloat(key, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.incr(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long append(String key, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.append(key, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String substr(String key, int start, int end) {
        Jedis jedis = getResource();
        try {
            return jedis.substr(key, start, end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.hset(key, field, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = getResource();
        try {
            return jedis.hget(key, field);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.hsetnx(key, field, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        Jedis jedis = getResource();
        try {
            return jedis.hmset(key, hash);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        Jedis jedis = getResource();
        try {
            return jedis.hmget(key, fields);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        Jedis jedis = getResource();
        try {
            return jedis.hincrBy(key, field, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        Jedis jedis = getResource();
        try {
            return jedis.hincrByFloat(key, field, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Boolean hexists(String key, String field) {
        Jedis jedis = getResource();
        try {
            return jedis.hexists(key, field);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long hdel(String key, String... field) {
        Jedis jedis = getResource();
        try {
            return jedis.hdel(key, field);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long hlen(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.hlen(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> hkeys(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.hkeys(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> hvals(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.hvals(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.hgetAll(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long rpush(String key, String... string) {
        Jedis jedis = getResource();
        try {
            return jedis.rpush(key, string);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long lpush(String key, String... string) {
        Jedis jedis = getResource();
        try {
            return jedis.lpush(key, string);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long llen(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.llen(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = getResource();
        try {
            return jedis.lrange(key, start, end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String ltrim(String key, long start, long end) {
        Jedis jedis = getResource();
        try {
            return jedis.ltrim(key, start, end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String lindex(String key, long index) {
        Jedis jedis = getResource();
        try {
            return jedis.lindex(key, index);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String lset(String key, long index, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.lset(key, index, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long lrem(String key, long count, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.lrem(key, count, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String lpop(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.lpop(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String rpop(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.rpop(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long sadd(String key, String... member) {
        Jedis jedis = getResource();
        try {
            return jedis.sadd(key, member);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> smembers(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.smembers(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long srem(String key, String... member) {
        Jedis jedis = getResource();
        try {
            return jedis.srem(key, member);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String spop(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.spop(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> spop(String key, long count) {
        Jedis jedis = getResource();
        try {
            return jedis.spop(key, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long scard(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.scard(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Boolean sismember(String key, String member) {
        Jedis jedis = getResource();
        try {
            return jedis.sismember(key, member);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String srandmember(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.srandmember(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> srandmember(String key, int count) {
        Jedis jedis = getResource();
        try {
            return jedis.srandmember(key, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long strlen(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.strlen(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zadd(String key, double score, String member) {
        Jedis jedis = getResource();
        try {
            return jedis.zadd(key, score, member);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zadd(String key, double score, String member, ZAddParams params) {
        Jedis jedis = getResource();
        try {
            return jedis.zadd(key, score, member, params);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        Jedis jedis = getResource();
        try {
            return jedis.zadd(key, scoreMembers);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        Jedis jedis = getResource();
        try {
            return jedis.zadd(key, scoreMembers, params);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        Jedis jedis = getResource();
        try {
            return jedis.zrange(key, start, end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zrem(String key, String... member) {
        Jedis jedis = getResource();
        try {
            return jedis.zrem(key, member);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        Jedis jedis = getResource();
        try {
            return jedis.zincrby(key, score, member);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Double zincrby(String key, double score, String member, ZIncrByParams params) {
        Jedis jedis = getResource();
        try {
            return jedis.zincrby(key, score, member, params);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zrank(String key, String member) {
        Jedis jedis = getResource();
        try {
            return jedis.zrank(key, member);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zrevrank(String key, String member) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrank(key, member);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrange(key, start, end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        Jedis jedis = getResource();
        try {
            return jedis.zrangeWithScores(key, start, end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrangeWithScores(key, start, end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zcard(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.zcard(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Double zscore(String key, String member) {
        Jedis jedis = getResource();
        try {
            return jedis.zscore(key, member);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> sort(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.sort(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        Jedis jedis = getResource();
        try {
            return jedis.sort(key, sortingParameters);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zcount(String key, double min, double max) {
        Jedis jedis = getResource();
        try {
            return jedis.zcount(key, min, max);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zcount(String key, String min, String max) {
        Jedis jedis = getResource();
        try {
            return jedis.zcount(key, min, max);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        Jedis jedis = getResource();
        try {
            return jedis.zrangeByScore(key, min, max);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        Jedis jedis = getResource();
        try {
            return jedis.zrangeByScore(key, min, max);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        Jedis jedis = getResource();
        try {
            return jedis.zrangeByScore(key, min, max, offset, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        Jedis jedis = getResource();
        try {
            return jedis.zrangeByScore(key, min, max, offset, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        Jedis jedis = getResource();
        try {
            return jedis.zrangeByScoreWithScores(key, min, max);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        Jedis jedis = getResource();
        try {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        Jedis jedis = getResource();
        try {
            return jedis.zrangeByScoreWithScores(key, min, max);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        Jedis jedis = getResource();
        try {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        Jedis jedis = getResource();
        try {
            return jedis.zremrangeByRank(key, start, end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zremrangeByScore(String key, double start, double end) {
        Jedis jedis = getResource();
        try {
            return jedis.zremrangeByScore(key, start, end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) {
        Jedis jedis = getResource();
        try {
            return jedis.zremrangeByScore(key, start, end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        Jedis jedis = getResource();
        try {
            return jedis.zlexcount(key, min, max);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        Jedis jedis = getResource();
        try {
            return jedis.zrangeByLex(key, min, max);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        Jedis jedis = getResource();
        try {
            return jedis.zrangeByLex(key, min, max, offset, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrangeByLex(key, max, min);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        Jedis jedis = getResource();
        try {
            return jedis.zrevrangeByLex(key, max, min, offset, count);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        Jedis jedis = getResource();
        try {
            return jedis.zremrangeByLex(key, min, max);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        Jedis jedis = getResource();
        try {
            return jedis.linsert(key, where, pivot, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long lpushx(String key, String... string) {
        Jedis jedis = getResource();
        try {
            return jedis.lpushx(key, string);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long rpushx(String key, String... string) {
        Jedis jedis = getResource();
        try {
            return jedis.rpushx(key, string);
        } finally {
            jedis.close();
        }
    }

    @Override
    @Deprecated
    public List<String> blpop(String arg) {
        Jedis jedis = getResource();
        try {
            return jedis.blpop(arg);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        Jedis jedis = getResource();
        try {
            return jedis.blpop(timeout, key);
        } finally {
            jedis.close();
        }
    }

    @Override
    @Deprecated
    public List<String> brpop(String arg) {
        Jedis jedis = getResource();
        try {
            return jedis.brpop(arg);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        Jedis jedis = getResource();
        try {
            return jedis.brpop(timeout, key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long del(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    /**
     * 批量删除
     * @param keys
     * @return
     */
    public Long del(String... keys) {
        Jedis jedis = getResource();
        try {
            return jedis.del(keys);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String echo(String string) {
        Jedis jedis = getResource();
        try {
            return jedis.echo(string);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long move(String key, int dbIndex) {
        Jedis jedis = getResource();
        try {
            return jedis.move(key, dbIndex);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long bitcount(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.bitcount(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        Jedis jedis = getResource();
        try {
            return jedis.bitcount(key, start, end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long bitpos(String key, boolean value) {
        Jedis jedis = getResource();
        try {
            return jedis.bitpos(key, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long bitpos(String key, boolean value, BitPosParams params) {
        Jedis jedis = getResource();
        try {
            return jedis.bitpos(key, value, params);
        } finally {
            jedis.close();
        }
    }

    public ScanResult<String> scan(String cursor){
        Jedis jedis = getResource();
        try {
            return jedis.scan(cursor);
        } finally {
            jedis.close();
        }
    }

    public ScanResult<String> scan(String cursor, ScanParams scanParams){
        Jedis jedis = getResource();
        try {
            return jedis.scan(cursor, scanParams);
        } finally {
            jedis.close();
        }
    }

    @Override
    @Deprecated
    public ScanResult<Map.Entry<String, String>> hscan(String key, int cursor) {
        Jedis jedis = getResource();
        try {
            return jedis.hscan(key, cursor);
        } finally {
            jedis.close();
        }
    }

    @Override
    @Deprecated
    public ScanResult<String> sscan(String key, int cursor) {
        Jedis jedis = getResource();
        try {
            return jedis.sscan(key, cursor);
        } finally {
            jedis.close();
        }
    }

    @Override
    @Deprecated
    public ScanResult<Tuple> zscan(String key, int cursor) {
        Jedis jedis = getResource();
        try {
            return jedis.zscan(key, cursor);
        } finally {
            jedis.close();
        }
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        Jedis jedis = getResource();
        try {
            return jedis.hscan(key, cursor);
        } finally {
            jedis.close();
        }
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        Jedis jedis = getResource();
        try {
            return jedis.hscan(key, cursor, params);
        } finally {
            jedis.close();
        }
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        Jedis jedis = getResource();
        try {
            return jedis.sscan(key, cursor);
        } finally {
            jedis.close();
        }
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        Jedis jedis = getResource();
        try {
            return jedis.sscan(key, cursor, params);
        } finally {
            jedis.close();
        }
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        Jedis jedis = getResource();
        try {
            return jedis.zscan(key, cursor);
        } finally {
            jedis.close();
        }
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        Jedis jedis = getResource();
        try {
            return jedis.zscan(key, cursor, params);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long pfadd(String key, String... elements) {
        Jedis jedis = getResource();
        try {
            return jedis.pfadd(key, elements);
        } finally {
            jedis.close();
        }
    }

    @Override
    public long pfcount(String key) {
        Jedis jedis = getResource();
        try {
            return jedis.pfcount(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        Jedis jedis = getResource();
        try {
            return jedis.geoadd(key, longitude, latitude, member);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = getResource();
        try {
            return jedis.geoadd(key, memberCoordinateMap);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        Jedis jedis = getResource();
        try {
            return jedis.geodist(key, member1, member2);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        Jedis jedis = getResource();
        try {
            return jedis.geodist(key, member1, member2, unit);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> geohash(String key, String... members) {
        Jedis jedis = getResource();
        try {
            return jedis.geohash(key, members);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        Jedis jedis = getResource();
        try {
            return jedis.geopos(key, members);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        Jedis jedis = getResource();
        try {
            return jedis.georadius(key, longitude, latitude, radius, unit);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        Jedis jedis = getResource();
        try {
            return jedis.georadius(key, longitude, latitude, radius, unit, param);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        Jedis jedis = getResource();
        try {
            return jedis.georadiusByMember(key, member, radius, unit);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        Jedis jedis = getResource();
        try {
            return jedis.georadiusByMember(key, member, radius, unit, param);
        } finally {
            jedis.close();
        }
    }

    /**
     * 发布消息
     * @param channel
     * @param message
     * @return
     */
    public Long publish(String channel, String message) {
        Jedis jedis = getResource();
        try {
            return jedis.publish(channel, message);
        } finally {
            jedis.close();
        }
    }

    /**
     * 发布消息
     * @param channel
     * @param message
     * @return
     */
    public Long publish(byte[] channel, byte[] message) {
        Jedis jedis = getResource();
        try {
            return jedis.publish(channel, message);
        } finally {
            jedis.close();
        }
    }

    /**
     * 订阅消息
     * @param jedisPubSub
     * @param channels
     */
    public void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels) {
        Jedis jedis = getResource();
        try {
            jedis.subscribe(jedisPubSub, channels);
        } finally {
            jedis.close();
        }
    }

    /**
     * 订阅消息
     * @param jedisPubSub
     * @param channels
     */
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        Jedis jedis = getResource();
        try {
            jedis.subscribe(jedisPubSub, channels);
        } finally {
            jedis.close();
        }
    }

    /**
     * 订阅消息
     * @param jedisPubSub
     * @param patterns
     */
    public void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns) {
        Jedis jedis = getResource();
        try {
            jedis.psubscribe(jedisPubSub, patterns);
        } finally {
            jedis.close();
        }
    }

    /**
     * 订阅消息
     * @param jedisPubSub
     * @param patterns
     */
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        Jedis jedis = getResource();
        try {
            jedis.psubscribe(jedisPubSub, patterns);
        } finally {
            jedis.close();
        }
    }

    /**
     * eval
     */
    public void eval(String script) {
        Jedis jedis = getResource();
        try {
            jedis.eval(script);
        } finally {
            jedis.close();
        }
    }

    /**
     * eval
     */
    public void eval(String script,int keyCount, String... params) {
        Jedis jedis = getResource();
        try {
            jedis.eval(script, keyCount, params);
        } finally {
            jedis.close();
        }
    }

    /**
     * eval
     */
    public void eval(String script, List<String> keys, List<String>  args) {
        Jedis jedis = getResource();
        try {
            jedis.eval(script, keys, args);
        } finally {
            jedis.close();
        }
    }

}
