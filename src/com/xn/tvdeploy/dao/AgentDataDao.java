package com.xn.tvdeploy.dao;

import com.xn.common.dao.BaseDao;
import com.xn.tvdeploy.model.AgentDataModel;
import com.xn.tvdeploy.model.TpDataModel;

/**
 * @Description 代理-订单-收益的Dao层
 * @Author yoko
 * @Date 2020/5/6 0:17
 * @Version 1.0
 */
public interface AgentDataDao <T> extends BaseDao<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public AgentDataModel getTotalData(AgentDataModel model);
}
