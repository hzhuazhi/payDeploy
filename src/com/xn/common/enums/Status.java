package com.xn.common.enums;

public enum Status {

	ENABLE(0, "可用"), DISABLE(1, "禁用");
	public int key;
	public String value;

	private Status(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public static Status get(int key) {
		Status[] values = Status.values();
		for (Status object : values) {
			if (object.key == key) {
				return object;
			}
		}
		return null;
	}

}
