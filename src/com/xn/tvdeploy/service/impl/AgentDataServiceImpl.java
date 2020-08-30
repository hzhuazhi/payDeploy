package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.AgentDataDao;
import com.xn.tvdeploy.model.AgentDataModel;
import com.xn.tvdeploy.service.AgentDataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 代理-订单-收益的Service层的实现层
 * @Author yoko
 * @Date 2020/5/6 0:20
 * @Version 1.0
 */
@Service("agentDataService")
public class AgentDataServiceImpl <T> extends BaseServiceImpl<T> implements AgentDataService<T> {
    private final static Logger log = Logger.getLogger(AgentDataServiceImpl.class);
    @Autowired
    private AgentDataDao agentDataDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return agentDataDao;
    }

    @Override
    public AgentDataModel getTotalData(AgentDataModel model) {
        return agentDataDao.getTotalData(model);
    }
}
