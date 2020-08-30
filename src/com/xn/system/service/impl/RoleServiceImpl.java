package com.xn.system.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.system.dao.RoleDao;
import com.xn.system.dao.RoleModuleRelDao;
import com.xn.system.entity.Role;
import com.xn.system.entity.RoleModuleRel;
import com.xn.system.model.RoleModel;
import com.xn.system.service.RoleService;

/**
 * @author: yoko
 * @since: 2016-06-07 11:23:51
 *
 */
@Service("roleService")
public class RoleServiceImpl<T> extends BaseServiceImpl<T> implements RoleService<T> {

	@SuppressWarnings("unused")
	private final static Logger log = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDao<T> roleDao;
	@Autowired
	private RoleModuleRelDao<RoleModuleRel> roleModuleRelDao;

	@Override
	public BaseDao<T> getDao() {
		return roleDao;
	}

	/** 
	 * 添加角色以及相关权限
	 */
	public void addRoleAndModule(Role bean, Integer[] moduleIds) {
		bean.setCreateTime(new Date());
		roleDao.add((T) bean);
		if (moduleIds != null && moduleIds.length > 0) {
			for (Integer moduleId : moduleIds) {
				RoleModuleRel roleModuleRel = new RoleModuleRel();
				roleModuleRel.setRoleId(bean.getRoleId());
				roleModuleRel.setModuleId(moduleId);
				roleModuleRelDao.add(roleModuleRel);
			}
		}

	}

	/** 
	 * 根据角色名查找，判断是否已存在该角色
	 */
	public Role queryByName(String roleName) {
		return roleDao.queryByName(roleName);
	}

	/** 
	 * 编辑角色
	 */
	public void updateRoleAndModule(Role bean, Integer[] moduleIds) {
		roleDao.update((T) bean);
		//删除已有权限
		roleModuleRelDao.cancleModule(bean);
		//添加新的权限
		if (moduleIds != null && moduleIds.length > 0) {
			for (Integer moduleId : moduleIds) {
				RoleModuleRel roleModuleRel = new RoleModuleRel();
				roleModuleRel.setRoleId(bean.getRoleId());
				roleModuleRel.setModuleId(moduleId);
				roleModuleRelDao.add(roleModuleRel);
			}
		}

	}

	/** 
	 * 根据类型获取所有角色
	 */
	public List<Role> getRoleByType(RoleModel model) {
		return roleDao.getRoleByType(model);
	}
	/**
	 * 查询所有数据不分页
	 */
   public List<Role>  queryList(){
	   return roleDao.queryList();
   }
}