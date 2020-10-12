package com.xn.tvdeploy.service;

import com.xn.common.service.BaseService;
import com.xn.tvdeploy.model.ChannelReplenishModel;

/**
 * @Description 渠道补单申请的Service层
 * @Author yoko
 * @Date 2020/10/12 14:23
 * @Version 1.0
 */
public interface ChannelReplenishService<T> extends BaseService<T> {

    /**
     * @Description: 更新审核信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/10/12 14:49
     */
    public int updateCheck(ChannelReplenishModel model);
}
