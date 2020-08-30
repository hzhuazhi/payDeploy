package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

/**
 * @Description 银行的实体属性Bean
 * @Author yoko
 * @Date 2020/3/26 19:22
 * @Version 1.0
 */
public class BankModel extends BasePage {
    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 渠道ID
     */
    private long linkId;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 支行名称
     */
    private String subbranchName;

    /**
     * 开户名
     */
    private String accountName;

    /**
     * 银行卡号
     */
    private String bankCard;

    /**
     * 所在省份
     */
    private String province;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 所属角色ID
     */
    private long roleId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 昵称
     */
    private String nickName;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSubbranchName() {
        return subbranchName;
    }

    public void setSubbranchName(String subbranchName) {
        this.subbranchName = subbranchName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
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

    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
