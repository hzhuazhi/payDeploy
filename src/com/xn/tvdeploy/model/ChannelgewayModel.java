package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

/**
 * @Description 渠道与通道的关联关系的实体属性Bean
 * @Author yoko
 * @Date 2020/4/1 16:02
 * @Version 1.0
 */
public class ChannelgewayModel extends BasePage {
    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 渠道ID
     */
    private long channelId;

    /**
     * 通道的主键ID
     */
    private long gewayId;

    /**
     * 关联关系的名称
     */
    private String linkName;

    /**
     * 手续费
     */
    private String serviceCharge;

    /**
     * 扣量比例
     */
    private int deductRatio;

    /**
     * 收益类型：1普通收益类型，2多人分配收益类型
     */
    private int profitType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 是否有效：0有效，1无效/删除
     */
    private int yn;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 通道名称
     */
    private String gewayName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public long getGewayId() {
        return gewayId;
    }

    public void setGewayId(long gewayId) {
        this.gewayId = gewayId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public int getDeductRatio() {
        return deductRatio;
    }

    public void setDeductRatio(int deductRatio) {
        this.deductRatio = deductRatio;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getGewayName() {
        return gewayName;
    }

    public void setGewayName(String gewayName) {
        this.gewayName = gewayName;
    }

    public int getProfitType() {
        return profitType;
    }

    public void setProfitType(int profitType) {
        this.profitType = profitType;
    }
}
