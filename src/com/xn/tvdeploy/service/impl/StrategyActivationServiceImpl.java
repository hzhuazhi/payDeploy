package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.StrategyActivationDao;
import com.xn.tvdeploy.service.StrategyActivationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author df
 * @Description:策略-激活-下次联网时间的Service层的实现层
 * @create 2018-07-25 13:47
 **/
@Service("strategyActivationService")
public class StrategyActivationServiceImpl  <T> extends BaseServiceImpl<T> implements StrategyActivationService<T> {
    private final static Logger log = Logger.getLogger(StrategyActivationServiceImpl.class);

    @Autowired
    private StrategyActivationDao strategyActivationDao;

    @Override
    public BaseDao<T> getDao() {
        return strategyActivationDao;
    }
}
