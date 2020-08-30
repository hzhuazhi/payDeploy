package com.xn.system.enums;

/**
 * 日志类型
 * @author yoko
 * @createTime  2016-6-28 上午10:48:43
 */
public enum LogType {

	NORMAL(0, "正常"), EXCEPTION(1, "异常");
	public int key;
	public String value;

	private LogType(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public static LogType get(int key) {
		LogType[] values = LogType.values();
		for (LogType object : values) {
			if (object.key == key) {
				return object;
			}
		}
		return null;
	}

}
