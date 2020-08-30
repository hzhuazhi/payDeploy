package com.xn.common.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.xn.common.util.HtmlUtil;
import com.xn.common.util.edit.DateEditor;
import com.xn.common.util.edit.DoubleEditor;
import com.xn.common.util.edit.IntegerEditor;
import com.xn.common.util.edit.LongEditor;
import com.xn.common.util.edit.MyEditor;
import com.xn.common.util.edit.TimestampEditor;

/**
 * 
 * 基础Controller
 *@author yoko
 *@since 2016-05-05
 *
 */
public class BaseController {

	public final static String SUCCESS = "success";

	public final static String MSG = "msg";

	public final static String DATA = "data";

	public static Logger log = Logger.getLogger("BaseController");

	//参数绑定编辑器
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(int.class, new MyEditor());
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(Timestamp.class, new TimestampEditor());
	}

	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
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

	/**
	 * 所有ActionMap 统一从这里获取
	 * @return
	 */
	public Map<String, Object> getRootMap() {
		return new HashMap<String, Object>();
	}

	/**
	 *
	 * 提示成功信息
	 * @param message
	 *
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 提示成功信息
	 * @param response
	 * @param message
	 * @param data 附带的数据
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
		result.put(DATA, data);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 提示失败信息
	 * @param message
	 *
	 */
	public void sendFailureMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}

}
