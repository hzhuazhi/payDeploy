package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

import java.util.List;

/**
 * @author df
 * @Description:渠道号实体属性Bean
 * @create 2018-09-13 13:33
 **/
public class ChannelModel extends BasePage {

    private long id;

    /**
     *所属渠道对应表：tb_xn_tp的主键ID
     */
    private long tpId;

    /**
     *渠道编号名称
     */
    private String channelName;

    /**
     *渠道号
     */
    private String channelNum;

    /**
     *渠道号类型：1我方生成，2渠道自己填写的渠道号
     */
    private int channelType;

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
     * 渠道名称
     */
    private String acName;

    /**
     * ID集合
     */
    private List<Long> idList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTpId() {
        return tpId;
    }

    public void setTpId(long tpId) {
        this.tpId = tpId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
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

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}
