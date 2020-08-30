package com.xn.tvdeploy.util.call.model.ts.req;

import java.util.List;

public class SspModel {
	/**
	 * 广告请求唯一标识，由SSP生成
	 */
	private String id;
	
	/**
	 * APP对象，应用信息
	 */
	private AppModel app;
	
	/**
	 * Device 对象，设备信息
	 */
	private DeviceModel device;
	
	/**
	 * 曝光对象，一次request可以包含多个imp
	 */
	private List<ImpModel> imp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AppModel getApp() {
		return app;
	}

	public void setApp(AppModel app) {
		this.app = app;
	}

	public DeviceModel getDevice() {
		return device;
	}

	public void setDevice(DeviceModel device) {
		this.device = device;
	}

	public List<ImpModel> getImp() {
		return imp;
	}

	public void setImp(List<ImpModel> imp) {
		this.imp = imp;
	}
	
	
	
	
}
