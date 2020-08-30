package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.AccountDpDao;
import com.xn.tvdeploy.service.AccountDpService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @Description:开发者账号的Service层的实现层
 * @create 2018-09-06 21:12
 **/
@Service("accountDpService")
public class AccountDpServiceImpl<T> extends BaseServiceImpl<T> implements AccountDpService<T> {

    private static Logger log=Logger.getLogger(AccountDpServiceImpl.class);

    @Autowired
    private AccountDpDao<T> accountDpDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return accountDpDao;
    }
}
