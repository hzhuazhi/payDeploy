package com.xn.common.util;

import java.security.MessageDigest;

public class Md5Pass {
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String MD5(String value) {
		if (null == value || "".equals(value.toString().trim())) {
			return "";
		}
		StringBuffer result = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(value.getBytes());
			byte[] bt = md.digest();
			int idx = 0;
			for (int i = 0; i < bt.length; i++) {
				idx = bt[i];
				if (idx < 0) {
					idx += 256;
				}
				if (idx < 16) {
					result.append("0");
				}
				result.append(Integer.toHexString(idx));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	} 
}
