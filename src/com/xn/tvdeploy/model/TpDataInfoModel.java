package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

import java.util.List;

/**
 * @Description 渠道订单信息-详情的实体属性Bean
 * @Author yoko
 * @Date 2020/4/8 10:28
 * @Version 1.0
 */
public class TpDataInfoModel extends BasePage {

    /**
     * 主键ID
     */
    private long id;

    /**
     * 上游数据的ID
     */
    private long dataCoreId;

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
     * 客户端IP地址
     */
    private String clientIp;

    /**
     * 参数名：平台订单时间格式：yyyy-MM-dd HH:mm:ss
     */
    private String tradeTime;

    /**
     * 参数名称：平台返回签名数据该参数用于验签
     */
    private String sign;

    /**
     * 提交时间
     */
    private String subTime;

    /**
     *商品名称
     */
    private String productName;

    /**
     * 商品码
     */
    private String productCode;

    /**
     * 渠道的主键ID
     */
    private long channelId;

    /**
     * 通道的主键ID
     */
    private long gewayId;

    /**
     * 渠道号/商铺号
     */
    private String channel;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 异步通知地址
     */
    private String notifyUrl;

    /**
     * 我方异步通知地址
     */
    private String myNotifyUrl;

    /**
     * 接口版本
     */
    private String interfaceVer;

    /**
     * 参数名称：页面跳转同步通知地址；支付成功后，通过页面跳转的方式跳转到商家网站
     */
    private String returnUrl;

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

    private String sendOkStr;
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

    /**
     * 总的实际支付金额
     */
    private String totalPayAmount;

    /**
     * 总的实际支付后扣手续之后的金额
     */
    private String totalPayActualMoney;

    /**
     * 请求是否成功：1成功，2失败
     */
    private int sendOk;

    /**
     * 其它状态
     */
    private int otherStatus;

    /**
     * 实际支付金额：用户实际支付的金额
     */
    private String payAmount;

    /**
     * 实际支付金额扣手续费后的金额：字段pay_amount扣除手续费后的金额
     */
    private String payActualMoney;

    /**
     * 是否是补单：1初始化不是补单，2是补单
     */
    private int replenishType;

    /**
     * 补单类型：1补下发，2补全套
     */
    private int dataType;

    /**
     * 订单金额是否与实际支付金额一致：1初始化，2少了，3多了，4一致
     */
    private int moneyFitType;

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

    public int getSendOk() {
        return sendOk;
    }

    public void setSendOk(int sendOk) {
        this.sendOk = sendOk;
    }

    public long getDataCoreId() {
        return dataCoreId;
    }

    public void setDataCoreId(long dataCoreId) {
        this.dataCoreId = dataCoreId;
    }

    public String getSendOkStr() {
        return sendOkStr;
    }

    public void setSendOkStr(String sendOkStr) {
        this.sendOkStr = sendOkStr;
    }


    public int getOtherStatus() {
        return otherStatus;
    }

    public void setOtherStatus(int otherStatus) {
        this.otherStatus = otherStatus;
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

    public int getReplenishType() {
        return replenishType;
    }

    public void setReplenishType(int replenishType) {
        this.replenishType = replenishType;
    }

    public String getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(String totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getTotalPayActualMoney() {
        return totalPayActualMoney;
    }

    public void setTotalPayActualMoney(String totalPayActualMoney) {
        this.totalPayActualMoney = totalPayActualMoney;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getSubTime() {
        return subTime;
    }

    public void setSubTime(String subTime) {
        this.subTime = subTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getMyNotifyUrl() {
        return myNotifyUrl;
    }

    public void setMyNotifyUrl(String myNotifyUrl) {
        this.myNotifyUrl = myNotifyUrl;
    }

    public String getInterfaceVer() {
        return interfaceVer;
    }

    public void setInterfaceVer(String interfaceVer) {
        this.interfaceVer = interfaceVer;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getMoneyFitType() {
        return moneyFitType;
    }

    public void setMoneyFitType(int moneyFitType) {
        this.moneyFitType = moneyFitType;
    }
}
