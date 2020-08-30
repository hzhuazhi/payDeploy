package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.WithdrawDao;
import com.xn.tvdeploy.service.WithdrawService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 提现的Service层的实现层
 * @Author yoko
 * @Date 2020/3/27 9:55
 * @Version 1.0
 */
@Service("withdrawService")
public class WithdrawServiceImpl <T> extends BaseServiceImpl<T> implements WithdrawService<T> {

    private static Logger log=Logger.getLogger(WithdrawServiceImpl.class);

    @Autowired
    private WithdrawDao<T> withdrawDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return withdrawDao;
    }
}
