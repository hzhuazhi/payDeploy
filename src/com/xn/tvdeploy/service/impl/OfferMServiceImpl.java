package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.OfferMDao;
import com.xn.tvdeploy.service.OfferMService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @Description:广告主档表:存储最初始的广告信息的Service层的实现层
 * @create 2018-10-13 13:27
 **/
@Service("offerMService")
public class OfferMServiceImpl <T> extends BaseServiceImpl<T> implements OfferMService<T> {
    private final static Logger log = Logger.getLogger(OfferMServiceImpl.class);
    @Autowired
    private OfferMDao offerMDao;

    @Override
    public BaseDao<T> getDao() {
        return offerMDao;
    }
}
