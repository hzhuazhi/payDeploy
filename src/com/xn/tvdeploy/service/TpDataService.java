package com.xn.tvdeploy.service;

import com.xn.common.service.BaseService;
import com.xn.tvdeploy.model.TpDataModel;

/**
 * @Description 渠道数据的Service层
 * @Author yoko
 * @Date 2020/3/26 15:51
 * @Version 1.0
 */
public interface TpDataService<T> extends BaseService<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
    */
    public TpDataModel getTotalData(TpDataModel model);
}
