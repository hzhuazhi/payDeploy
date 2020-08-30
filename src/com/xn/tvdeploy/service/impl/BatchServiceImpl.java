package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.BatchDao;
import com.xn.tvdeploy.service.BatchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @Description:批次号的Service层的实现层
 * @create 2018-07-27 16:35
 **/
@Service("batchService")
public class BatchServiceImpl  <T> extends BaseServiceImpl<T> implements BatchService<T> {
    private final static Logger log = Logger.getLogger(BatchServiceImpl.class);
    @Autowired
    private BatchDao batchDao;

    @Override
    public BaseDao<T> getDao() {
        return batchDao;
    }
}
