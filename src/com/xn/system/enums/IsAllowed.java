package com.xn.system.enums;

/**
 * 模块是否可分配
 * @author yoko
 * @createTime  2016-6-24 上午10:48:43
 */
public enum IsAllowed {

	YES(0, "可分配"), NO(1, "不可分配");
	public int key;
	public String value;

	private IsAllowed(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public static IsAllowed get(int key) {
		IsAllowed[] values = IsAllowed.values();
		for (IsAllowed object : values) {
			if (object.key == key) {
				return object;
			}
		}
		return null;
	}

}
