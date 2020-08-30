package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

import java.util.List;

/**
 * @Description 渠道订单信息
 * @Author yoko
 * @Date 2020/3/26 15:43
 * @Version 1.0
 */
public class TpDataModel extends BasePage {

    /**
     * 主键ID
     */
    private long id;

    /**
     * 代理的主键ID
     */
    private long agentId;

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
     * 订单状态：1成功，2失败，3其它
     */
    private int tradeStatus;

    /**
     * 参数名称：回传参数商户如果支付请求是传递了该参数，则通知商户支付成功时会回传该参数
     */
    private String extraReturnParam;

    /**
     * 参数名：平台订单时间格式：yyyy-MM-dd HH:mm:ss
     */
    private String tradeTime;

    /**
     * 参数名称：平台返回签名数据该参数用于验签
     */
    private String sign;

    /**
     * 渠道的主键ID
     */
    private long channelId;

    /**
     * 通道的主键ID
     */
    private long gewayId;

    /**
     * 异步通知地址
     */
    private String notifyUrl;

    /**
     * 返回的成功标识
     */
    private String notifySuc;

    /**
     * 下游透传参数
     */
    private String xyExtraReturnParam;

    /**
     * 扣量比例
     */
    private int deductRatio;

    /**
     * 值以work形式填充计算,以后数据多起来则这些字段值填充由worker来跑数据：0初始化，1填充完毕，2无需下发数据
     */
    private int workType;

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
     * did的集合
     */
    private List<Long> idList;

    private String tradeStatusStr;
    private String runStatusStr;


    /**
     * 总的订单金额
     */
    private String totalMoney;

    /**
     * 总的手续费
     */
    private String totalServiceCharge;

    /**
     * 总的实际金额
     */
    private String totalActualMoney;

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

    public int getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(int tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getExtraReturnParam() {
        return extraReturnParam;
    }

    public void setExtraReturnParam(String extraReturnParam) {
        this.extraReturnParam = extraReturnParam;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getNotifySuc() {
        return notifySuc;
    }

    public void setNotifySuc(String notifySuc) {
        this.notifySuc = notifySuc;
    }

    public String getXyExtraReturnParam() {
        return xyExtraReturnParam;
    }

    public void setXyExtraReturnParam(String xyExtraReturnParam) {
        this.xyExtraReturnParam = xyExtraReturnParam;
    }

    public int getDeductRatio() {
        return deductRatio;
    }

    public void setDeductRatio(int deductRatio) {
        this.deductRatio = deductRatio;
    }

    public int getWorkType() {
        return workType;
    }

    public void setWorkType(int workType) {
        this.workType = workType;
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

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public String getTradeStatusStr() {
        return tradeStatusStr;
    }

    public void setTradeStatusStr(String tradeStatusStr) {
        this.tradeStatusStr = tradeStatusStr;
    }

    public String getRunStatusStr() {
        return runStatusStr;
    }

    public void setRunStatusStr(String runStatusStr) {
        this.runStatusStr = runStatusStr;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(String totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public String getTotalActualMoney() {
        return totalActualMoney;
    }

    public void setTotalActualMoney(String totalActualMoney) {
        this.totalActualMoney = totalActualMoney;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }
}
