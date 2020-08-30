package com.xn.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xn.common.controller.BaseController;
import com.xn.common.exception.ServiceException;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.RoleModuleRel;
import com.xn.system.model.RoleModuleRelModel;
import com.xn.system.service.RoleModuleRelService;

/**
 * @author:yoko
 * @since: 2016-06-27 10:15:58
 *
 */
@Controller
@RequestMapping("/roleModuleRel") 
public class RoleModuleRelController extends BaseController{

	@SuppressWarnings("unused")
	private static Logger log= Logger.getLogger(RoleModuleRelController.class);
	
	@Autowired
	private RoleModuleRelService<RoleModuleRel> roleModuleRelService; 

	/**
	 * 获取页面
	 */
	@RequestMapping("/list") 
	public String  list() {
		return "roleModuleRel";
	}
	
	/**
	 * 获取数据json列表页面
	 */
	@RequestMapping("/dataList")
	public void  dataList(RoleModuleRelModel model,HttpServletResponse response) throws Exception{
		List<RoleModuleRel> dataList = roleModuleRelService.queryByList(model);
		HtmlUtil.writerJson(response, model.getPage(), dataList);
	}
	
	/**
	 * 添加数据
	 */
	@RequestMapping("/add")
	public void add(RoleModuleRel bean,HttpServletResponse response) throws Exception{
		//TODO 自定义新增逻辑
		roleModuleRelService.add(bean);
		sendSuccessMessage(response, "保存成功~");
	}
	
	/**
	 * 修改数据
	 */
	@RequestMapping("/update")
	public void update(RoleModuleRel bean,HttpServletResponse response) throws Exception{
		roleModuleRelService.update(bean);
		sendSuccessMessage(response, "保存成功~");
	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		RoleModuleRel bean  = roleModuleRelService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		sendSuccessMessage(response, "", bean);
	}
	
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		//TODO  真删除，假删除调用update语句
		roleModuleRelService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
