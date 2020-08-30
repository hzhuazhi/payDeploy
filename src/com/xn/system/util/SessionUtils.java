package com.xn.system.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.xn.system.entity.User;

/**
 * Session 工具类
 */
public final class SessionUtils {

	protected static final Logger logger = Logger.getLogger(SessionUtils.class);

	private static final String SESSION_USER = "session_user";

	/**
	  * 设置session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	public static void setAttr(HttpServletRequest request, String key, Object value) {
		request.getSession(true).setAttribute(key, value);
	}

	/**
	 * 获取session的值
	 * @param request
	 * @param key
	 * @param value
	 */
	public static Object getAttr(HttpServletRequest request, String key) {
		return request.getSession(true).getAttribute(key);
	}

	/**
	 * 删除Session值
	 * @param request
	 * @param key
	 */
	public static void removeAttr(HttpServletRequest request, String key) {
		request.getSession(true).removeAttribute(key);
	}

	/**
	 * 设置用户信息 到session
	 * @param request
	 * @param u
	 */
	public static void setUser(HttpServletRequest request, User u) {
		request.getSession(true).setAttribute(SESSION_USER, u);
	}

	/**
	 * 从session中获取用户信息
	 * @param request
	 * @return SysUser
	 */
	public static User getUser(HttpServletRequest request) {
		return (User) request.getSession(true).getAttribute(SESSION_USER);
	}

	/**
	 * 从session中获取用户信息
	 * @param request
	 * @return SysUser
	 */
	public static Long getUserId(HttpServletRequest request) {
		User user = getUser(request);
		if (user != null) {
			return user.getId();
		}
		return null;
	}

	/**
	 * 从session中获取用户信息
	 * @param request
	 * @return SysUser
	 */
	public static void removeUser(HttpServletRequest request) {
		removeAttr(request, SESSION_USER);
	}

}