package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.AccountTpDao;
import com.xn.tvdeploy.service.AccountTpService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @Description:渠道账号的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("accountTpService")
public class AccountTpServiceImpl <T> extends BaseServiceImpl<T> implements AccountTpService<T> {

    private static Logger log=Logger.getLogger(AccountTpServiceImpl.class);

    @Autowired
    private AccountTpDao<T> accountTpDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return accountTpDao;
    }
}
