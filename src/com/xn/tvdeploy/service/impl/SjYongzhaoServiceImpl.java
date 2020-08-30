package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.SjYongzhaoDao;
import com.xn.tvdeploy.service.SjYongzhaoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @Description:审计
 * @create 2018-09-22 13:46
 **/
@Service("sjYongzhaoService")
public class SjYongzhaoServiceImpl <T> extends BaseServiceImpl<T> implements SjYongzhaoService<T> {
    private final static Logger log = Logger.getLogger(SjYongzhaoServiceImpl.class);
    @Autowired
    private SjYongzhaoDao sjYongzhaoDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return sjYongzhaoDao;
    }
}
