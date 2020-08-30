package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.TpDataInfoDao;
import com.xn.tvdeploy.model.TpDataInfoModel;
import com.xn.tvdeploy.service.TpDataInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道订单信息-详情的Service层的实现层
 * @Author yoko
 * @Date 2020/4/8 15:01
 * @Version 1.0
 */
@Service("tpDataInfoService")
public class TpDataInfoServiceImpl <T> extends BaseServiceImpl<T> implements TpDataInfoService<T> {
    private final static Logger log = Logger.getLogger(TpDataInfoServiceImpl.class);
    @Autowired
    private TpDataInfoDao tpDataInfoDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return tpDataInfoDao;
    }

    @Override
    public TpDataInfoModel getTotalData(TpDataInfoModel model) {
        return tpDataInfoDao.getTotalData(model);
    }

    @Override
    public TpDataInfoModel getChannelData(TpDataInfoModel model) {
        return tpDataInfoDao.getChannelData(model);
    }

    @Override
    public int addDataCore(TpDataInfoModel model) {
        return tpDataInfoDao.addDataCore(model);
    }
}
