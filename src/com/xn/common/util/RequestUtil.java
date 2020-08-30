package com.xn.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * ClassName: RequestUtil
 * @Description: TODO
 * @author yoko
 * @date 2016-8-5
 */
public class RequestUtil {

	public static boolean isAjax(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(header);
	}

	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
