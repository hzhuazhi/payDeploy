package com.xn.tvdeploy.service;

import com.xn.common.service.BaseService;
import com.xn.tvdeploy.model.AgentDataModel;

/**
 * @Description 代理-订单-收益的Service层
 * @Author yoko
 * @Date 2020/5/6 0:18
 * @Version 1.0
 */
public interface AgentDataService<T> extends BaseService<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public AgentDataModel getTotalData(AgentDataModel model);
}
