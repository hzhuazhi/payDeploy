package com.xn.tvdeploy.util.call.model.ts.req;

public class AppModel {
	/**
	 * 探索平台媒体应用的唯一ID
	 */
	private String id;
	
	/**
	 * 媒体名称
	 */
	private String name;
	
	/**
	 * 可选
	 * App官网地址
	 */
	private String domain;
	
	/**
	 * 可选
	 * App类型
	 */
	private String cat;
	
	/**
	 * 可选
	 * 媒体APP版本 如：1.1
	 */
	private String ver;
	
	/**
	 * APP应用包名
	 */
	private String bundle;
	
	/**
	 * 可选
	 * 是否为付费APP 0-不是 1-是 2-应用内付费
	 */
	private int paid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}
	
	
}
