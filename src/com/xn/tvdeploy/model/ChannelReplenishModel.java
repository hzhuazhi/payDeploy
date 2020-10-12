package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

/**
 * @Description 渠道补单申请的实体属性Bean
 * @Author yoko
 * @Date 2020/10/12 14:06
 * @Version 1.0
 */
public class ChannelReplenishModel extends BasePage {

    /**
     * 主键ID
     */
    private long id;

    /**
     * 渠道的主键ID
     */
    private long channelId;

    /**
     * 我方订单号
     */
    private String myTradeNo;

    /**
     * 参数名称：商家订单号
     */
    private String outTradeNo;

    /**
     * 参数名称：商家订单金额，订单总金额
     */
    private String totalAmount;

    /**
     * 渠道填写的金额
     */
    private String channelMoney;

    /**
     * 渠道上传的图片
     */
    private String pictureAds;

    /**
     * 审核金额：上游给出的金额；审核反馈的金额
     */
    private String checkMoney;

    /**
     * 审核上传的图片：上游给出的图片；审核反馈的图片
     */
    private String checkPictureAds;

    /**
     * 审核状态（上游反馈）：1初始化，2审核失败，3审核成功
     */
    private int checkStatus;

    /**
     * 审核失败缘由，审核失败的原因
     */
    private String checkInfo;

    /**
     * 审核时间：审核反馈的时间
     */
    private String checkTime;

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
     * 渠道名称
     */
    private String channelName;


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

    public String getMyTradeNo() {
        return myTradeNo;
    }

    public void setMyTradeNo(String myTradeNo) {
        this.myTradeNo = myTradeNo;
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

    public String getChannelMoney() {
        return channelMoney;
    }

    public void setChannelMoney(String channelMoney) {
        this.channelMoney = channelMoney;
    }

    public String getPictureAds() {
        return pictureAds;
    }

    public void setPictureAds(String pictureAds) {
        this.pictureAds = pictureAds;
    }

    public String getCheckMoney() {
        return checkMoney;
    }

    public void setCheckMoney(String checkMoney) {
        this.checkMoney = checkMoney;
    }

    public String getCheckPictureAds() {
        return checkPictureAds;
    }

    public void setCheckPictureAds(String checkPictureAds) {
        this.checkPictureAds = checkPictureAds;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
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

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
