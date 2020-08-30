package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.GewaytradetypeDao;
import com.xn.tvdeploy.service.GewaytradetypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 通道的支付类型的Service层的实现层
 * @Author yoko
 * @Date 2020/3/31 17:35
 * @Version 1.0
 */
@Service("gewaytradetypeService")
public class GewaytradetypeServiceImpl <T> extends BaseServiceImpl<T> implements GewaytradetypeService<T> {
    private final static Logger log = Logger.getLogger(GewaytradetypeServiceImpl.class);
    @Autowired
    private GewaytradetypeDao gewaytradetypeDao;

    @Override
    public BaseDao<T> getDao() {
        return gewaytradetypeDao;
    }
}
