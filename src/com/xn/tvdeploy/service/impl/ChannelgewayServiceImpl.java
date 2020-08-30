package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.ChannelgewayDao;
import com.xn.tvdeploy.service.ChannelgewayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道与通道的关联关系的Service层的实现层
 * @Author yoko
 * @Date 2020/4/1 16:07
 * @Version 1.0
 */
@Service("channelgeway")
public class ChannelgewayServiceImpl <T> extends BaseServiceImpl<T> implements ChannelgewayService<T> {

    private static Logger log=Logger.getLogger(ChannelgewayServiceImpl.class);

    @Autowired
    private ChannelgewayDao<T> channelgewayDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelgewayDao;
    }
}
