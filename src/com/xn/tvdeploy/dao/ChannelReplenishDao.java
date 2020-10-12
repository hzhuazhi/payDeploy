package com.xn.tvdeploy.dao;

import com.xn.common.dao.BaseDao;
import com.xn.tvdeploy.model.ChannelReplenishModel;

/**
 * @Description 渠道补单申请的Dao层
 * @Author yoko
 * @Date 2020/10/12 14:21
 * @Version 1.0
 */
public interface ChannelReplenishDao<T> extends BaseDao<T> {

    /**
     * @Description: 更新审核信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/10/12 14:49
    */
    public int updateCheck(ChannelReplenishModel model);

}
