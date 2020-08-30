package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.WithdrawAgentDao;
import com.xn.tvdeploy.service.WithdrawAgentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 提现-代理的Service层的实现层
 * @Author yoko
 * @Date 2020/5/4 19:12
 * @Version 1.0
 */
@Service("withdrawAgentService")
public class WithdrawAgentServiceImpl<T> extends BaseServiceImpl<T> implements WithdrawAgentService<T> {

    private static Logger log=Logger.getLogger(WithdrawAgentServiceImpl.class);

    @Autowired
    private WithdrawAgentDao<T> withdrawAgentDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return withdrawAgentDao;
    }
}
