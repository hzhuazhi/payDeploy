package com.xn.tvdeploy.util.call.model.ts.req;

import java.util.List;

public class BannerModel {
	/**
	 * 广告位宽
	 */
	private int w;
	
	/**
	 * 广告位高
	 */
	private int h;
	
	/**
	 * 可选
	 * 拒绝的广告素材类型，【参考附录1】 暂不支持
	 */
	private List<String> btype;

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public List<String> getBtype() {
		return btype;
	}

	public void setBtype(List<String> btype) {
		this.btype = btype;
	}
	
	
}
