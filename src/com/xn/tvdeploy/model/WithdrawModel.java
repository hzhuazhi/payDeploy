package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

/**
 * @Description 提现的实体属性Bean
 * @Author yoko
 * @Date 2020/3/26 21:50
 * @Version 1.0
 */
public class WithdrawModel extends BasePage {
    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 关联的主键ID:根据角色类型来确定，如果角色是渠道，则这个是渠道的主键ID
     */
    private long linkId;

    /**
     * 提现金额
     */
    private String money;

    /**
     * 手续费
     */
    private String serviceCharge;

    /**
     * 绑定银行卡的主键ID
     */
    private long bankId;

    /**
     * 开户名
     */
    private String accountName;

    /**
     * 提现银行卡卡号
     */
    private String bankCard;

    /**
     * 提现状态:1提现中，2提现失败，3提现成功
     */
    private int withdrawStatus;

    /**
     * 说明:提现失败的情况说明
     */
    private String withdrawExplain;

    /**
     * 所属角色ID
     */
    private long roleId;


    /**
     * 备注
     */
    private String remark;

    /**
     *运行计算次数
     */
    private int runNum;

    /**
     * 运行计算状态：：0初始化，1锁定，2计算失败，3计算成功
     */
    private int runStatus;

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
     * 代理名称
     */
    private String agentName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(int withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
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

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getWithdrawExplain() {
        return withdrawExplain;
    }

    public void setWithdrawExplain(String withdrawExplain) {
        this.withdrawExplain = withdrawExplain;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getRunNum() {
        return runNum;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
    }

    public int getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(int runStatus) {
        this.runStatus = runStatus;
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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
}
