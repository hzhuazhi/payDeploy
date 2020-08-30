package com.xn.system.dao;

import com.xn.common.dao.BaseDao;
import com.xn.system.entity.Role;

/**
 * @author: yoko
 * @since: 2016-06-27 10:15:58
 *
 */
public interface RoleModuleRelDao<T> extends BaseDao<T> {

	/**
	 * 删除用户已有权限
	 * @param bean
	 */
	void cancleModule(Role bean);

}
