package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.AgentChannelDao;
import com.xn.tvdeploy.service.AgentChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 代理与渠道的关联关系Service层的实现层
 * @Author yoko
 * @Date 2020/4/29 17:21
 * @Version 1.0
 */
@Service("agentChannelService")
public class AgentChannelServiceImpl <T> extends BaseServiceImpl<T> implements AgentChannelService<T> {

    private static Logger log=Logger.getLogger(AgentChannelServiceImpl.class);

    @Autowired
    private AgentChannelDao<T> agentChannelDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return agentChannelDao;
    }
}
