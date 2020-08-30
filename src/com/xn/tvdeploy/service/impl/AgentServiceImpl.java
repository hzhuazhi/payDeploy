package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.AgentDao;
import com.xn.tvdeploy.service.AgentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 代理的Service层的实现层
 * @Author yoko
 * @Date 2020/4/29 17:21
 * @Version 1.0
 */
@Service("agentService")
public class AgentServiceImpl <T> extends BaseServiceImpl<T> implements AgentService<T> {

    private static Logger log=Logger.getLogger(AgentServiceImpl.class);

    @Autowired
    private AgentDao<T> agentDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return agentDao;
    }
}
