package com.xn.system.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.enums.ManagerEnum;
import com.xn.system.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Module;
//import com.xn.system.enums.AccountType;
import com.xn.system.model.ModuleModel;
import com.xn.system.service.ModuleService;
import org.springframework.web.util.WebUtils;

/**
 * 
 * ClassName: ModuleController
 * @Description: TODO
 * @author yoko
 * @date 2016-7-27
 */
@Controller
@RequestMapping("/system/module")
public class ModuleController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(ModuleController.class);

	@Autowired
	private ModuleService<Module> moduleService;

	@ModelAttribute("headType")
	public String returnHeadType() {
		return "系统信息";
	}

	/**
	 * 获取页面
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		return "system/module/list-module";
	}

	/**
	 * 获取数据json列表页面
	 */
	@RequestMapping("/dataList")
	public void dataList(ModuleModel model, HttpServletResponse response) throws Exception {
		List<Module> dataList = moduleService.queryByList(model);
		HtmlUtil.writerJson(response, model.getPage(), dataList);
	}

	/**
	 * 添加数据
	 */
	@RequestMapping("/add")
	public void add(Module bean, HttpServletResponse response) throws Exception {
		bean.setCreateTime(new Date());
		moduleService.add(bean);
		sendSuccessMessage(response, "保存成功~");
	}

	/**
	 * 修改数据
	 */
	@RequestMapping("/update")
	public void update(Module bean, HttpServletResponse response) throws Exception {
		moduleService.update(bean);
		sendSuccessMessage(response, "保存成功~");
	}

	@RequestMapping("/getId")
	public void getId(Integer moduleId, HttpServletResponse response) throws Exception {
		Map<String, Object> context = getRootMap();
		Module bean = moduleService.queryById(moduleId);
		if (bean == null) {
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		sendSuccessMessage(response, "", bean);
	}

	@RequestMapping("/delete")
	public void delete(Module module, HttpServletResponse response) throws Exception {
		moduleService.update(module);
		sendSuccessMessage(response, "删除成功");
	}

	/**
	 * 获取父级菜单
	 * 
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/parentModule")
	public void getParentModule(ModuleModel model, HttpServletResponse response) throws Exception {
		model.setLevel(model.getLevel() - 1);
		List<Module> modules = moduleService.getParentModule(model);
		Map<String, Object> map = getRootMap();
		map.put("modules", modules);
		sendSuccessMessage(response, "", map);
	}

	/**
	 * 获取用户的权限列表
	 * 
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/menuList")
	public void getMenuList(HttpServletRequest request, HttpServletResponse response, ModuleModel model) throws Exception {
		List<Module> modules = null;
		Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
		if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
			if (account.getRoleId() == ManagerEnum.RoleTypeEnum.ADMIN.getRoleType()){
				//管理员账号
				modules = moduleService.getAllModule(null, null, null);
			}else{
				modules = moduleService.getRoleModule((int) account.getRoleId());
			}
//			modules = moduleService.getRoleModule((int) account.getRoleId());
		}
		Map<String, Object> map = getRootMap();
		map.put("menuList", modules);
		sendSuccessMessage(response, "", map);

	}
}
