package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

import java.util.List;

/**
 * @Description 代理-订单信息-收益的实体属性Bean
 * @Author yoko
 * @Date 2020/5/6 0:11
 * @Version 1.0
 */
public class AgentDataModel extends BasePage {

    /**
     * 主键ID
     */
    private long id;

    /**
     * 我方订单号
     */
    private String myTradeNo;

    /**
     * 平台订单号
     */
    private String tradeNo;

    /**
     * 参数名称：商家订单号
     */
    private String outTradeNo;

    /**
     * 参数名称：商家订单金额，订单总金额
     */
    private String totalAmount;

    /**
     * 手续费
     */
    private String serviceCharge;

    /**
     * 实际金额
     */
    private String actualMoney;

    /**
     * 实际支付金额
     */
    private String payAmount;

    /**
     * 实际支付金额后扣了收付费后的金额
     */
    private String payActualMoney;


    /**
     * 渠道的主键ID
     */
    private long channelId;

    /**
     * 通道的主键ID
     */
    private long gewayId;

    /**
     * 代理主键ID
     */
    private long agentId;

    /**
     * 渠道与通道关联关系ID
     */
    private long channelGewayId;

    /**
     * 是否补单
     */
    private int replenishType;

    /**
     * 金额是否一致
     */
    private int moneyFitType;

    /**
     * 收益占比
     */
    private String profitRatio;

    /**
     * 实际收益
     */
    private String profit;



    /**
     * 创建日期：存的日期格式20160530
     */
    private int curday;

    /**
     * 创建所属小时：24小时制
     */
    private int curhour;

    /**
     * 创建所属分钟：60分钟制
     */
    private int curminute;

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

    private int curdayStart;
    private int curdayEnd;

    /**
     * 代理名称
     */
    private String agentName;

    /**
     * 渠道名称
     */
    private String channelName;

    private String totalProfit;

    private String totalMoney;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMyTradeNo() {
        return myTradeNo;
    }

    public void setMyTradeNo(String myTradeNo) {
        this.myTradeNo = myTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(String actualMoney) {
        this.actualMoney = actualMoney;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayActualMoney() {
        return payActualMoney;
    }

    public void setPayActualMoney(String payActualMoney) {
        this.payActualMoney = payActualMoney;
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

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public long getChannelGewayId() {
        return channelGewayId;
    }

    public void setChannelGewayId(long channelGewayId) {
        this.channelGewayId = channelGewayId;
    }

    public int getReplenishType() {
        return replenishType;
    }

    public void setReplenishType(int replenishType) {
        this.replenishType = replenishType;
    }

    public int getMoneyFitType() {
        return moneyFitType;
    }

    public void setMoneyFitType(int moneyFitType) {
        this.moneyFitType = moneyFitType;
    }

    public String getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(String profitRatio) {
        this.profitRatio = profitRatio;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public int getCurday() {
        return curday;
    }

    public void setCurday(int curday) {
        this.curday = curday;
    }

    public int getCurhour() {
        return curhour;
    }

    public void setCurhour(int curhour) {
        this.curhour = curhour;
    }

    public int getCurminute() {
        return curminute;
    }

    public void setCurminute(int curminute) {
        this.curminute = curminute;
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

    public int getCurdayStart() {
        return curdayStart;
    }

    public void setCurdayStart(int curdayStart) {
        this.curdayStart = curdayStart;
    }

    public int getCurdayEnd() {
        return curdayEnd;
    }

    public void setCurdayEnd(int curdayEnd) {
        this.curdayEnd = curdayEnd;
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

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
}
