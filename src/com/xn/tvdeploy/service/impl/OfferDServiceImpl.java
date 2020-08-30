package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.OfferDDao;
import com.xn.tvdeploy.service.OfferDService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @Description:广告详情的Service层的实现层
 * @create 2018-10-15 15:35
 **/
@Service("offerDService")
public class OfferDServiceImpl <T> extends BaseServiceImpl<T> implements OfferDService<T> {
    private final static Logger log = Logger.getLogger(OfferDServiceImpl.class);
    @Autowired
    private OfferDDao offerDDao;

    @Override
    public BaseDao<T> getDao() {
        return offerDDao;
    }
}
