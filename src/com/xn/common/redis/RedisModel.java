package com.xn.common.redis;

import java.io.Serializable;

/**
 * @author df
 * @Description:存储零时数据的实体Bean
 * @create 2018-12-07 0:36
 **/
public class RedisModel implements Serializable {
    private static final long serialVersionUID = -2832842937952340363L;

    /**
     * 数据Key
     */
    private String dataKey;

    /**
     * 数据值
     */
    private String dataValue;

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }
}
