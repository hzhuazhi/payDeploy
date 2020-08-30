package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

import java.io.Serializable;
import java.util.List;

/**
 * @author df
 * @Description:sdk模块的实体属性Bean
 * @create 2018-07-31 11:16
 **/
public class SdkModuleModel extends BasePage {
    private long id;

    /**
     *模块名称_显示名称
     */
    private String moduleShowName;

    /**
     *给终端使用的名称
     */
    private String moduleName;

    /**
     *最新的模块的版本号
     */
    private String moduleVer;

    /**
     *要更新的模块类型：1主体，2下载模块.....
     */
    private int moduleType;

    /**
     *模块文件下载地址
     */
    private String moduleUrl;

    /**
     * 文件大小：单位（KB）
     */
    private String fileSize;

    /**
     * 唯一值：下载check使用
     */
    private String onlyValue;

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
     * ID的集合list
     */
    private List<Long> idList;

    /**
     * sdk模块更新策略的主键ID
     */
    private long sgySdkUpId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModuleShowName() {
        return moduleShowName;
    }

    public void setModuleShowName(String moduleShowName) {
        this.moduleShowName = moduleShowName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleVer() {
        return moduleVer;
    }

    public void setModuleVer(String moduleVer) {
        this.moduleVer = moduleVer;
    }

    public int getModuleType() {
        return moduleType;
    }

    public void setModuleType(int moduleType) {
        this.moduleType = moduleType;
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
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

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public long getSgySdkUpId() {
        return sgySdkUpId;
    }

    public void setSgySdkUpId(long sgySdkUpId) {
        this.sgySdkUpId = sgySdkUpId;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getOnlyValue() {
        return onlyValue;
    }

    public void setOnlyValue(String onlyValue) {
        this.onlyValue = onlyValue;
    }
}
