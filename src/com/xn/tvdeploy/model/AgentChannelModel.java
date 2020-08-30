package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

/**
 * @Description 代理与渠道的关联关系的实体属性Bean
 * @Author yoko
 * @Date 2020/4/29 17:16
 * @Version 1.0
 */
public class AgentChannelModel extends BasePage {
    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 代理的的主键ID
     */
    private long agentId;

    /**
     * 渠道ID
     */
    private long channelId;


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
     * 代理名称
     */
    private String agentName;

    /**
     * 渠道名称
     */
    private String channelName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
