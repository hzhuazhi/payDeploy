package com.xn.tvdeploy.util.call.model.ts.res;

import java.util.List;

/**
 * @author df
 * @Description:通过调用探索API接口返回的数据字段的实体Bean
 * @create 2018-10-15 14:05
 **/
public class TsModel {
    /**
     * 返回AdResponse的唯一标识，由探索生成
     */
    private String id;

    /**
     * 对应请求的唯一标识,由SSP生成
     */
    private String reqid;

    /**
     * 广告信息
     */
    private List<AdsModel> ads;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public List<AdsModel> getAds() {
        return ads;
    }

    public void setAds(List<AdsModel> ads) {
        this.ads = ads;
    }
}
