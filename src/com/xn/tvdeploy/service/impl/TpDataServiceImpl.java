package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.TpDataDao;
import com.xn.tvdeploy.model.TpDataModel;
import com.xn.tvdeploy.service.TpDataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author yoko
 * @Date 2020/3/26 15:52
 * @Version 1.0
 */
@Service("tpDataService")
public class TpDataServiceImpl <T> extends BaseServiceImpl<T> implements TpDataService<T> {
    private final static Logger log = Logger.getLogger(AppServiceImpl.class);
    @Autowired
    private TpDataDao tpDataDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return tpDataDao;
    }

    @Override
    public TpDataModel getTotalData(TpDataModel model) {
        return tpDataDao.getTotalData(model);
    }
}
