package com.xn.system.dao;

import com.xn.common.dao.BaseDao;
import com.xn.system.entity.Account;

/**
 * @author: yoko
 * @since: 2016-06-27 16:59:46
 *
 */
public interface AccountStudioRelDao<T> extends BaseDao<T> {

	/**
	 * 删除某个帐号已有的关联关系
	 * @param bean
	 */
	void deleteRel(Account bean);

}
