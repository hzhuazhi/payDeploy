package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;


/**
 * @author df
 * @Description:策略-激活-下次联网时间的实体属性Bean
 * @create 2018-07-25 13:48
 **/
public class StrategyActivationModel extends BasePage {

    private long id;

    /**
     *渠道号的主键ID
     */
    private long channelId;

    /**
     *所属批次ID
     */
    private long batchId;

    /**
     *所属app的主键ID
     */
    private long appId;

    /**
     * 策略激活名称
     */
    private String activationName;

    /**
     *要限制多少个用户
     */
    private int limitNum;

    /**
     *已经限制了多少用户
     */
    private int isLimitNum;

    /**
     *是否以及完成了限制目标：1未完成，2完成
     */
    private int isLimitOk;

    /**
     *用户省份
     */
    private String province;

    /**
     *用户城市
     */
    private String city;

    /**
     *限制类型：1初始化全局限制，2省份限制
     */
    private int limitType;

    /**
     *激活时间间隔：以用户第一次联网时间之后的间隔时间：单位分
     */
    private int activationTime;

    /**
     *下次联网时间：分钟
     */
    private  int nextTime;

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
     * 创建人的角色ID
     */
    private long createRole;

    /**
     * 更新人的角色ID
     */
    private long updateRole;

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

    /**
     * 渠道账号ID
     */
    private long tpId;

    /**
     * 所属渠道
     */
    private String tpAcName;


    /**
     * 所属渠道号名称
     */
    private String channelName;

    /**
     * 所属批次号名称
     */
    private String batchName;

    /**
     * 开发者ID
     */
    private long dpId;

    /**
     * 所属开发者名称
     */
    private String dpAcName;

    /**
     * 所属应用名称
     */
    private String appName;


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

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public int getIsLimitNum() {
        return isLimitNum;
    }

    public void setIsLimitNum(int isLimitNum) {
        this.isLimitNum = isLimitNum;
    }

    public int getIsLimitOk() {
        return isLimitOk;
    }

    public void setIsLimitOk(int isLimitOk) {
        this.isLimitOk = isLimitOk;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLimitType() {
        return limitType;
    }

    public void setLimitType(int limitType) {
        this.limitType = limitType;
    }

    public int getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(int activationTime) {
        this.activationTime = activationTime;
    }

    public int getNextTime() {
        return nextTime;
    }

    public void setNextTime(int nextTime) {
        this.nextTime = nextTime;
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

    public long getCreateRole() {
        return createRole;
    }

    public void setCreateRole(long createRole) {
        this.createRole = createRole;
    }

    public long getUpdateRole() {
        return updateRole;
    }

    public void setUpdateRole(long updateRole) {
        this.updateRole = updateRole;
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

    public long getTpId() {
        return tpId;
    }

    public void setTpId(long tpId) {
        this.tpId = tpId;
    }

    public String getTpAcName() {
        return tpAcName;
    }

    public void setTpAcName(String tpAcName) {
        this.tpAcName = tpAcName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public long getDpId() {
        return dpId;
    }

    public void setDpId(long dpId) {
        this.dpId = dpId;
    }

    public String getDpAcName() {
        return dpAcName;
    }

    public void setDpAcName(String dpAcName) {
        this.dpAcName = dpAcName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getActivationName() {
        return activationName;
    }

    public void setActivationName(String activationName) {
        this.activationName = activationName;
    }
}
