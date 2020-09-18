package com.xn.tvdeploy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author df
 * @Description:根据redis获取唯一id值
 * @create 2018-12-13 15:01
 **/
@Service
public class RedisIdService {

    @Autowired(required=false)
    private RedisTemplate redisTemplate;

//    public String getId() {
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
//        Date date=new Date();
//        String formatDate=sdf.format(date);
//        String key="key"+formatDate;
//        Long incr = getIncr(key, getCurrent2TodayEndMillisTime());
//        if(incr==0) {
//            incr = getIncr(key, getCurrent2TodayEndMillisTime());//从001开始
//        }
//        DecimalFormat df=new DecimalFormat("000");//三位序列号
//        return formatDate+df.format(incr);
//    }

    public String getId()  throws Exception{
//        String formatDate =DateUtil.getNowPlusTimeMill();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        Date date=new Date();
        String formatDate=sdf.format(date);
        String key="key"+formatDate;
        Long incr = getIncr(key, getCurrent2TodayEndMillisTime());
        if(incr==0) {
            incr = getIncr(key, getCurrent2TodayEndMillisTime());//从001开始
        }
//        DecimalFormat df=new DecimalFormat("000");//三位序列号
        DecimalFormat df=new DecimalFormat("0000000");//七位序列号
        return formatDate+df.format(incr);
    }


    public long getId(String redisKey)  throws Exception{
        String key = redisKey;
        Long incr = getIncrByDay(key, 1);
        if(incr==0) {
            incr = getIncrByDay(key, 1);//从1开始
        }
        return incr;
    }


    public Long getIncrByDay(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, TimeUnit.DAYS);//单位小时
        }
        return increment;
    }


    public Long getIncr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, TimeUnit.MILLISECONDS);//单位毫秒
        }
        return increment;
    }

    //现在到今天结束的毫秒数
    public Long getCurrent2TodayEndMillisTime() {
        Calendar todayEnd = Calendar.getInstance();
        // Calendar.HOUR 12小时制
        // HOUR_OF_DAY 24小时制
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTimeInMillis()-new Date().getTime();
    }

    private final String LOCKVALUE = "lockvalue";
    private boolean locked = false;

    /**
     * @Description: TODO(redis锁)
     * @author df
     * @param lockKey-要锁的key
     * @create 16:37 2019/1/31
     **/
    public synchronized boolean lock(String lockKey){
        /*该方法会在没有key时，设置key;存在key时返回false；因此可以通过该方法及设置key的有效期，判断是否有其它线程持有锁*/
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey,LOCKVALUE);
        if(success != null && success){
            redisTemplate.expire(lockKey,5,TimeUnit.SECONDS);
            locked = true;
        }else{
            locked = false;
        }
        return locked;
    }

    /**
     * @Description: TODO(redis解锁)
     * @author df
     * @param lockKey-要删除的锁的key
     * @create 16:37 2019/1/31
     **/
    public synchronized void delLock(String lockKey){
        redisTemplate.delete(lockKey);
    }


}
