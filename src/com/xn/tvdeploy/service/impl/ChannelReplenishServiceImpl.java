package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.ChannelReplenishDao;
import com.xn.tvdeploy.model.ChannelReplenishModel;
import com.xn.tvdeploy.service.ChannelReplenishService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道补单申请的Service层的实现层
 * @Author yoko
 * @Date 2020/10/12 14:27
 * @Version 1.0
 */
@Service("channelReplenishService")
public class ChannelReplenishServiceImpl<T> extends BaseServiceImpl<T> implements ChannelReplenishService<T> {
    private final static Logger log = Logger.getLogger(ChannelReplenishServiceImpl.class);
    @Autowired
    private ChannelReplenishDao channelReplenishDao;

    @Override
    public BaseDao<T> getDao() {
        return channelReplenishDao;
    }

    @Override
    public int updateCheck(ChannelReplenishModel model) {
        return channelReplenishDao.updateCheck(model);
    }
}
