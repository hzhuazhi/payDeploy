package com.xn.tvdeploy.dao;

import com.xn.common.dao.BaseDao;
import com.xn.tvdeploy.model.TpDataInfoModel;

/**
 * @Description 渠道订单信息-详情的Dao层
 * @Author yoko
 * @Date 2020/4/7 19:14
 * @Version 1.0
 */
public interface TpDataInfoDao <T> extends BaseDao<T> {
    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public TpDataInfoModel getTotalData(TpDataInfoModel model);

    /**
     * @Description: 根据条件查询渠道上报信息
     * <p>
     *     补单使用：
     *      A可以根据我方订单号查询。
     *      B根据渠道方订单号查询。
     *      B根据渠道ID查询最近的一条订单信息。
     *
     * </p>
     * @param model
     * @return
     * @author yoko
     * @date 2020/8/6 11:12
    */
    public TpDataInfoModel getChannelData(TpDataInfoModel model);

    /**
     * @Description: 添加上游下发的数据
     * @param model
     * @return
     * @author yoko
     * @date 2020/8/6 14:25
    */
    public int addDataCore(TpDataInfoModel model);
}
