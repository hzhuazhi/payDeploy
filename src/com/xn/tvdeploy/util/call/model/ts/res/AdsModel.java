package com.xn.tvdeploy.util.call.model.ts.res;

import java.util.List;

/**
 * @author df
 * @Description:探索的广告的实体属性Bean
 * @create 2018-10-15 14:09
 **/
public class AdsModel {

    /**
     * 广告创意ID，可用于去重
     */
    private String cid;

    /**
     * 带延迟的展示汇报，由客户端发送，HTML广告也需处理该字段。例如：{“0”:[“URL1”,“URL2”],“10”:[“URL3”,“URL4”]}
     */
    private List<String> nurl;

    /**
     * 点击监控地址,有客户端发送，HTML广告也需处理该字段。例如："curl": ["url_1","url_2", ...]
     */
    private List<String> curl;

    /**
     * 广告点击跳转URL（落地页），包含重定向。
     */
    private String adurl;

    /**
     * 对应请求IMP的唯一ID
     */
    private String impid;

    /**
     * 图片/视频物料URL
     */
    private String adi;

    /**
     * 广告点击行为类型:0未确认,1打开网页,2下载应用,4打开地图,8发送短信,16发送邮件,32拨打电话,64播放视频
     *
     */
    private String adct;

    /**
     * 1图片广告,2GIF动画广告,3文字链广告,4HTML5广告,5MRAID v2.0广告,6视频广告,7FLASH广告,8NATIVE广告
     */
    private String admt;

    /**
     * 价格，数值为CPM或CPC价格1000,如底价为CPM价格0.6元，则取值0.61000=600.如果没有合适的竞价广告，会返回不带价格的广告，此时price为0，表示事后确定结算价格
     */
    private String price;

    private List<NativeModel> nativeMo;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public List<String> getNurl() {
        return nurl;
    }

    public void setNurl(List<String> nurl) {
        this.nurl = nurl;
    }

    public List<String> getCurl() {
        return curl;
    }

    public void setCurl(List<String> curl) {
        this.curl = curl;
    }

    public String getAdurl() {
        return adurl;
    }

    public void setAdurl(String adurl) {
        this.adurl = adurl;
    }

    public String getImpid() {
        return impid;
    }

    public void setImpid(String impid) {
        this.impid = impid;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getAdct() {
        return adct;
    }

    public void setAdct(String adct) {
        this.adct = adct;
    }

    public String getAdmt() {
        return admt;
    }

    public void setAdmt(String admt) {
        this.admt = admt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<NativeModel> getNativeMo() {
        return nativeMo;
    }

    public void setNativeMo(List<NativeModel> nativeMo) {
        this.nativeMo = nativeMo;
    }
}
