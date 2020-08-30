package com.xn.tvdeploy.util.call.model.ts.req;

public class ImpModel {
	/**
	 * 此id是在请求的时候唯一确定,SSP生成
	 */
	private String id;
	
	/**
	 * 货币种类 统一为人民币 CNY
	 */
	private String bidfloorcur;
	
	/**
	 * 展现形式 0-横幅 1-插屏或全屏 4-开屏 7-原生广告 默认0
	 */
	private String instl;
	
	/**
	 * 原生广告类型 0-不使用原生模板 1-使用原生模板 （展现形式为7原生广告时生效）
	 */
	private String tpl;
	
	/**
	 * Device 千次曝光底价 底价，数值为CPM价格*1000，如底价为CPM价格0.2元，则取值0.2X1000=200
	 */
	private float bidfloor;
	
	/**
	 * 图文类型的广告对象
	 */
	private BannerModel banner;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBidfloorcur() {
		return bidfloorcur;
	}

	public void setBidfloorcur(String bidfloorcur) {
		this.bidfloorcur = bidfloorcur;
	}

	public String getInstl() {
		return instl;
	}

	public void setInstl(String instl) {
		this.instl = instl;
	}

	public String getTpl() {
		return tpl;
	}

	public void setTpl(String tpl) {
		this.tpl = tpl;
	}

	public float getBidfloor() {
		return bidfloor;
	}

	public void setBidfloor(float bidfloor) {
		this.bidfloor = bidfloor;
	}

	public BannerModel getBanner() {
		return banner;
	}

	public void setBanner(BannerModel banner) {
		this.banner = banner;
	}
	
	
}
