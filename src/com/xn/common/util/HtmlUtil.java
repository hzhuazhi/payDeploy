package com.xn.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.xn.common.page.Page;
import com.xn.common.util.json.JSONUtil;

/**
 * 页面工具类
 */
public class HtmlUtil {

	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出json格式<br>
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void writerJson(HttpServletResponse response, String jsonStr) {
//		response.setContentType("text/html");
		writer(response, jsonStr);
	}

	public static void writerJson(HttpServletResponse response, Object object) {
		try {
			response.setContentType("application/json");
			writer(response, JSONUtil.toJSONString(object));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页只返回记录总数，由前端分页插件分页
	 * 
	 * @param response
	 * @param count
	 * @param data
	 */
	public static void writerJson(HttpServletResponse response, int count, List<?> data) {
		try {
			response.setContentType("application/json");
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", count);
			jsonMap.put("rows", data);
			writer(response, JSONUtil.toJSONString(jsonMap));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回分页所有相关字段，封装于Pager对象中
	 * 
	 * @param response
	 * @param page
	 * @param data
	 */
	public static void writerJson(HttpServletResponse response, Page page, List<?> data) {
		try {
			response.setContentType("application/json");
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", page);
			jsonMap.put("rows", data);
			writer(response, JSONUtil.toJSONString(jsonMap));
			System.out.println("the writerJson:"+JSONUtil.toJSONString(jsonMap));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出HTML代码<br>
	 * @param response
	 * @param htmlStr
	 * @throws Exception
	 */
	public static void writerHtml(HttpServletResponse response, String htmlStr) {
		writer(response, htmlStr);
	}
	
	
	
	/**
	 * 返回页面数据：返回int类型的一个数字
	 * 
	 * @param response
	 * @param num
	 */
	public static void writerJson(HttpServletResponse response, int num) {
		try {
			response.setContentType("application/json");
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("num", num);
			writer(response, JSONUtil.toJSONString(jsonMap));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private static void writer(HttpServletResponse response, String str) {
		try {
			//设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			out = response.getWriter();
			System.out.println("=======================resData:"+str);
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
