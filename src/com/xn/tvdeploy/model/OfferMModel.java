package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @author df
 * @Description:广告主档表:存储最初始的广告信息的实体Bean
 * @create 2018-10-13 13:09
 **/
public class OfferMModel extends BasePage {
    private long id;

    /**
     * 来自于哪个广告接口的广告：对应表tb_xn_advert_api的主键ID
     */
    private long apiId;

    /**
     * 归属广告主，对应表tb_xn_account_ap的主键ID，属于这个广告主的广告
     */
    private long advertiserId;

    /**
     * 第三方广告的ID（源ID）
     */
    private String sourceId;

    /**
     * 源的标识类型：例如探索的源标识类型=sre_ts,前缀以sre_开头
     */
    private String sourceType;

    /**
     * 广告名称
     */
    private String offerName;

    /**
     * 广告推广地址
     */
    private String spreadUrl;

    /**
     * 推广类型：0初始化，1CPC，2CPA，3CPS，4CPM
     */
    private int spreadType;

    /**
     * 价格/比例
     */
    private String payout;

    /**
     * 广告类型：0初始化，1其它，2图片广告，3GIF动画广告，4文字链广告，5HTML5广告，6MRAID
     */
    private int offerType;

    /**
     * 广告点击行为类型：0初始化，1其它，2打开网页，3下载应用，4打开地图，5发送短信，6发送邮件，7拨打电话，8播放视频
     */
    private int offerClickType;

    /**
     * 入库类型：0初始化，1work调用接口，2用户实时调用，3手动录入
     */
    private int warehousingType;

    /**
     * 每日预算类型：0初始化，1不设预算，2设置预算
     */
    private int dayBudgetType;

    /**
     * 每日预算金额
     */
    private String dayBudget;

    /**
     * 推广周期类型：0初始化，1长期投放，2自定义
     */
    private int spreadCycleType;

    /**
     * 推广开始时间：年月日
     */
    private String spreadStartDay;

    /**
     * 推广结束时间：年月日
     */
    private String spreadEndDay;

    /**
     * 推广时间段_开始时间：时分
     */
    private String startTime;

    /**
     * 推广时间段_结束时间：时分
     */
    private String endTime;

    /**
     * 投放省份，以逗号隔开
     */
    private String province;

    /**
     * 投放城市，以逗号隔开
     */
    private String city;

    /**
     * 是否需要对广告进行处理：0初始化，1不需要处理，2需要处理
     */
    private int isHandle;

    /**
     * 对广告进行处理的状态：1未处理，2已处理
     */
    private int handleStatus;

    /**
     * 更新标识-注意这里1表：1初始化，2更新，3复核
     */
    private int upStatus;

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
     * 广告详情表
     */
    private OfferDModel offerDModel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getApiId() {
        return apiId;
    }

    public void setApiId(long apiId) {
        this.apiId = apiId;
    }

    public long getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(long advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getSpreadUrl() {
        return spreadUrl;
    }

    public void setSpreadUrl(String spreadUrl) {
        this.spreadUrl = spreadUrl;
    }

    public int getSpreadType() {
        return spreadType;
    }

    public void setSpreadType(int spreadType) {
        this.spreadType = spreadType;
    }

    public String getPayout() {
        return payout;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }

    public int getOfferType() {
        return offerType;
    }

    public void setOfferType(int offerType) {
        this.offerType = offerType;
    }

    public int getOfferClickType() {
        return offerClickType;
    }

    public void setOfferClickType(int offerClickType) {
        this.offerClickType = offerClickType;
    }

    public int getWarehousingType() {
        return warehousingType;
    }

    public void setWarehousingType(int warehousingType) {
        this.warehousingType = warehousingType;
    }

    public int getDayBudgetType() {
        return dayBudgetType;
    }

    public void setDayBudgetType(int dayBudgetType) {
        this.dayBudgetType = dayBudgetType;
    }

    public String getDayBudget() {
        return dayBudget;
    }

    public void setDayBudget(String dayBudget) {
        this.dayBudget = dayBudget;
    }

    public int getSpreadCycleType() {
        return spreadCycleType;
    }

    public void setSpreadCycleType(int spreadCycleType) {
        this.spreadCycleType = spreadCycleType;
    }

    public String getSpreadStartDay() {
        return spreadStartDay;
    }

    public void setSpreadStartDay(String spreadStartDay) {
        this.spreadStartDay = spreadStartDay;
    }

    public String getSpreadEndDay() {
        return spreadEndDay;
    }

    public void setSpreadEndDay(String spreadEndDay) {
        this.spreadEndDay = spreadEndDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public int getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(int isHandle) {
        this.isHandle = isHandle;
    }

    public int getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(int handleStatus) {
        this.handleStatus = handleStatus;
    }

    public int getUpStatus() {
        return upStatus;
    }

    public void setUpStatus(int upStatus) {
        this.upStatus = upStatus;
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

    public OfferDModel getOfferDModel() {
        return offerDModel;
    }

    public void setOfferDModel(OfferDModel offerDModel) {
        this.offerDModel = offerDModel;
    }
}
