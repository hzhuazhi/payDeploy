package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @author df
 * @Description:广告详情表：存储最初始广告的素材标签描述等详情的实体Bean
 * @create 2018-10-13 13:30
 **/
public class OfferDModel extends BasePage {
    private long id;

    /**
     * 广告的主档ID:对应表tb_xn_offer_m的主键ID
     */
    private long offerMId;

    /**
     * 广告详情名称
     */
    private String offerDName;

    /**
     * 资源地址：图片/视频物理url
     */
    private String resUrl;

    /**
     * 所有广告详情的数据_json数据
     */
    private String offerJson;

    /**
     *备注
     */
    private String remark;

    /**
     *是否启用：0初始化属于暂停状态，1表示暂停使用，2正常状态
     */
    private int isEnable;

    /**
     *创建人
     */
    private long createUser;

    /**
     *更新人
     */
    private long updateUser;

    /**
     *创建时间
     */
    private String createTime;

    /**
     *更新时间
     */
    private String updateTime;

    /**
     *是否有效：0初始化，1失效/删除
     */
    private int yn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOfferMId() {
        return offerMId;
    }

    public void setOfferMId(long offerMId) {
        this.offerMId = offerMId;
    }

    public String getOfferDName() {
        return offerDName;
    }

    public void setOfferDName(String offerDName) {
        this.offerDName = offerDName;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getOfferJson() {
        return offerJson;
    }

    public void setOfferJson(String offerJson) {
        this.offerJson = offerJson;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    public long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(long updateUser) {
        this.updateUser = updateUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }
}
