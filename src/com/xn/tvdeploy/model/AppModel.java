package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @author df
 * @Description:应用的实体属性Bean
 * @create 2018-07-27 14:52
 **/
public class AppModel extends BasePage {
    private long id;

    /**
     *所属开发者的主键ID：对应表tb_xn_dp的主键
     */
    private long dpId;

    /**
     *应用编号
     */
    private String appNum;

    /**
     *app的Key
     */
    private String appKey;

    /**
     *应用名称
     */
    private String appName;

    /**
     *应用版本号
     */
    private String appVer;

    /**
     *应用下载地址
     */
    private String appUrl;

    /**
     *应用包名
     */
    private String appPackage;

    /**
     *启动的界面
     */
    private String startBoundary;

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
     * 开发者名称
     */
    private String acName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDpId() {
        return dpId;
    }

    public void setDpId(long dpId) {
        this.dpId = dpId;
    }

    public String getAppNum() {
        return appNum;
    }

    public void setAppNum(String appNum) {
        this.appNum = appNum;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getStartBoundary() {
        return startBoundary;
    }

    public void setStartBoundary(String startBoundary) {
        this.startBoundary = startBoundary;
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

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }
}
