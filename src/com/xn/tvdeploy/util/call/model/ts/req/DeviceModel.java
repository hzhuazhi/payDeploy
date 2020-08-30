package com.xn.tvdeploy.util.call.model.ts.req;

public class DeviceModel {
	
	/**
	 * Ipv4 地址 例如 4.4.4.4
	 */
	private String ip;
	
	/**
	 * 可选
	 * 安卓设备唯一标识支持,默认0 0-IMEI或IDFA 1-AndroidID
	 */
	private int andt;
	
	/**
	 * 根据andt 默认Android 为IMEI；iOS 为IFA
	 */
	private String did;
	
	/**
	 * 可选
	 * mac 地址
	 */
	private String mac;
	
	/**
	 * 设备使用的运营商,MCC+MNC的值 例：46001
	 */
	private String carrier;
	
	/**
	 * 设备制造商 例：Samsung
	 */
	private String make;
	
	/**
	 * 设备型号 例：Note4
	 */
	private String model;
	
	/**
	 * 设备操作系统,目前支持两种:IOS,Android
	 */
	private String os;
	
	/**
	 * 设备操作系统版本号 例:3.5.2
	 */
	private String osv;
	
	/**
	 * 设备联网类型，【参考附录2】
	 */
	private int connectiontype;
	
	/**
	 * 设备类型，【参考附录3】
	 */
	private int devicetype;
	
	/**
	 * 可选
	 * 设备屏幕像素密度
	 */
	private float s_density;
	
	/**
	 * 设备屏幕逻辑分辨率宽度 单位像素
	 */
	private int sw;
	
	/**
	 * 设备屏幕逻辑分辨率高度 单位像素
	 */
	private int sh;
	
	/**
	 * 可选
	 * 设备屏幕方向 0-竖向 1-横向 默认0
	 */
	private int orientation;
	
	/**
	 * 设备浏览器UserAgent
	 */
	private String ua;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getAndt() {
		return andt;
	}

	public void setAndt(int andt) {
		this.andt = andt;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsv() {
		return osv;
	}

	public void setOsv(String osv) {
		this.osv = osv;
	}

	public int getConnectiontype() {
		return connectiontype;
	}

	public void setConnectiontype(int connectiontype) {
		this.connectiontype = connectiontype;
	}

	public int getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

	public float getS_density() {
		return s_density;
	}

	public void setS_density(float sDensity) {
		s_density = sDensity;
	}

	public int getSw() {
		return sw;
	}

	public void setSw(int sw) {
		this.sw = sw;
	}

	public int getSh() {
		return sh;
	}

	public void setSh(int sh) {
		this.sh = sh;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}
	
	
}
