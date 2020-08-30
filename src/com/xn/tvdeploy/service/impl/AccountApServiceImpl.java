package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.AccountApDao;
import com.xn.tvdeploy.service.AccountApService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @Description:广告主账号的Service层的实现层
 * @create 2018-09-11 15:40
 **/
@Service("accountApService")
public class AccountApServiceImpl <T> extends BaseServiceImpl<T> implements AccountApService<T> {

    private static Logger log=Logger.getLogger(AccountApServiceImpl.class);

    @Autowired
    private AccountApDao<T> accountApDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return accountApDao;
    }
}
