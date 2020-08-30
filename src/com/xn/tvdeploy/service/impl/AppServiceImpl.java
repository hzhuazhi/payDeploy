package com.xn.tvdeploy.service.impl;


import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.AppDao;
import com.xn.tvdeploy.service.AppService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @Description:应用的Service层的实现层
 * @create 2018-07-27 16:38
 **/
@Service("appService")
public class AppServiceImpl <T> extends BaseServiceImpl<T> implements AppService<T> {
    private final static Logger log = Logger.getLogger(AppServiceImpl.class);
    @Autowired
    private AppDao appDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return appDao;
    }
}
