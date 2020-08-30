package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.AccountTpDao;
import com.xn.tvdeploy.dao.BankDao;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.BankService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 银行的Service层的实现层
 * @Author yoko
 * @Date 2020/3/26 19:37
 * @Version 1.0
 */
@Service("bankService")
public class BankServiceImpl <T> extends BaseServiceImpl<T> implements BankService<T> {

    private static Logger log=Logger.getLogger(BankServiceImpl.class);

    @Autowired
    private BankDao<T> bankDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return bankDao;
    }
}
