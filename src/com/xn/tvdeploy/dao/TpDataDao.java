package com.xn.tvdeploy.dao;

import com.xn.common.dao.BaseDao;
import com.xn.tvdeploy.model.TpDataModel;

/**
 * @Description 渠道数据的Dao层
 * @Author yoko
 * @Date 2020/3/26 15:53
 * @Version 1.0
 */
public interface TpDataDao <T> extends BaseDao<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public TpDataModel getTotalData(TpDataModel model);
}
