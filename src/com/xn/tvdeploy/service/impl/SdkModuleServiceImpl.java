package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.SdkModuleDao;
import com.xn.tvdeploy.service.SdkModuleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @Description:sdk模块的Service层的实现层
 * @create 2018-07-31 11:21
 **/
@Service("sdkModuleService")
public class SdkModuleServiceImpl <T> extends BaseServiceImpl<T> implements SdkModuleService<T> {
    private final static Logger log = Logger.getLogger(SdkModuleServiceImpl.class);
    @Autowired
    private SdkModuleDao sdkModuleDao;

    @Override
    public BaseDao<T> getDao() {
        return sdkModuleDao;
    }
}
