package com.xn.common.enums;

public enum Deleted {

	NO(0, "未删除"), YES(1, "已删除");
	public int key;
	public String value;

	private Deleted(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public static Deleted get(int key) {
		Deleted[] values = Deleted.values();
		for (Deleted object : values) {
			if (object.key == key) {
				return object;
			}
		}
		return null;
	}

}
