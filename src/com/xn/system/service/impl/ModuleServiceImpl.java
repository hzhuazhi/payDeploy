package com.xn.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.common.dao.BaseDao;
import com.xn.common.exception.ServiceException;
import com.xn.common.page.BasePage;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.system.dao.ModuleDao;
import com.xn.system.entity.Module;
import com.xn.system.enums.ModuleLevel;
import com.xn.system.model.ModuleModel;
import com.xn.system.service.ModuleService;

/**
 * @author: yoko
 * @since: 2016-06-29 15:04:56
 *
 */
@Service("moduleService")
public class ModuleServiceImpl<T> extends BaseServiceImpl<T> implements ModuleService<T> {

	@SuppressWarnings("unused")
	private final static Logger log = Logger.getLogger(ModuleServiceImpl.class);

	@Autowired
	private ModuleDao<T> moduleDao;

	@Override
	public BaseDao<T> getDao() {
		return moduleDao;
	}

	/** 
	 * 获取父亲级菜单
	 */
	public List<Module> getParentModule(ModuleModel model) {
		return moduleDao.getParentModule(model);
	}

	/** 
	 * 获取角色绑定的权限
	 */
	public List<Module> getRoleModule(Integer roleId) {
		ModuleModel model = new ModuleModel();
		List<Module> module = new ArrayList<Module>();
		model.setRoleId(roleId);
		model.setLevel(ModuleLevel.ONE.key);
		List<Module> moduleOne = moduleDao.getModuleList(model);
		model.setLevel(ModuleLevel.TWO.key);
		List<Module> moduleTwo = moduleDao.getModuleList(model);
		model.setLevel(ModuleLevel.THREE.key);
		List<Module> moduleThree = moduleDao.getModuleList(model);
		//登记关系配置
		module.addAll(moduleOne);
		for (Module m : module) {
			for (Module cm : moduleTwo) {
				if (cm.getParentId().equals(m.getModuleId())) {
					m.getChildren().add(cm);
				}
			}
		}
		for (Module m : moduleTwo) {
			for (Module cm : moduleThree) {
				if (cm.getParentId().equals(m.getModuleId())) {
					m.getChildren().add(cm);
				}
			}
		}
		return module;
	}

	/** 
	 * 获取指定类型的所有的权限列表
	 */
	public List<Module> getAllModule(Integer type, Integer isAllowed, Integer roleId) {
		ModuleModel model = new ModuleModel();
		model.setType(type);
		model.setIsAllowed(isAllowed);
		List<Module> module = new ArrayList<Module>();
		model.setLevel(ModuleLevel.ONE.key);
		List<Module> moduleOne = moduleDao.getAllModule(model);
		model.setLevel(ModuleLevel.TWO.key);
		List<Module> moduleTwo = moduleDao.getAllModule(model);
		model.setLevel(ModuleLevel.THREE.key);
		List<Module> moduleThree = moduleDao.getAllModule(model);
		List<Module> roleModules = new ArrayList<Module>();
		if (roleId != null) {
			model.setRoleId(roleId);
			model.setLevel(ModuleLevel.THREE.key);
			roleModules = moduleDao.getModuleList(model);
		}

		//登记关系配置
		module.addAll(moduleOne);
		for (Module m : module) {
			for (Module cm : moduleTwo) {
				if (cm.getParentId().equals(m.getModuleId())) {
					for(Module twCm : roleModules){
						if(cm.getModuleId().equals(twCm.getParentId())){
							cm.setCheck(true);
						}
					}
					m.getChildren().add(cm);
				}
			}
		}
		for (Module m : moduleTwo) {
			for (Module cm : moduleThree) {
				for (Module temp : roleModules) {
					if (temp.getModuleId().equals(cm.getModuleId())) {
						cm.setCheck(true);
					}

				}
				if (cm.getParentId().equals(m.getModuleId())) {
					m.getChildren().add(cm);
				}
			}
		}
		return module;
	}

}