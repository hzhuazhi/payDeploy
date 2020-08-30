package com.xn.system.enums;

/**
 * 菜单级别
 * @author yoko
 * @createTime  2016-6-24 上午10:48:43
 */
public enum ModuleLevel {

	ONE(1, "一级（导航栏）"), TWO(2, "二级（主模块）"), THREE(3, "三级（子模块）");
	public int key;
	public String value;

	private ModuleLevel(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public static ModuleLevel get(int key) {
		ModuleLevel[] values = ModuleLevel.values();
		for (ModuleLevel object : values) {
			if (object.key == key) {
				return object;
			}
		}
		return null;
	}

}
