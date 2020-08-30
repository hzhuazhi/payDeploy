package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.GewayDao;
import com.xn.tvdeploy.service.GewayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 通道的Service层的实现层
 * @Author yoko
 * @Date 2020/3/31 20:49
 * @Version 1.0
 */
@Service("gewayService")
public class GewayServiceImpl <T> extends BaseServiceImpl<T> implements GewayService<T> {

    private static Logger log=Logger.getLogger(GewayServiceImpl.class);

    @Autowired
    private GewayDao gewayDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return gewayDao;
    }
}
