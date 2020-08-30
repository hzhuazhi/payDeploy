package com.xn.common.service.impl;

import java.util.List;


import com.xn.common.dao.BaseDao;
import com.xn.common.exception.ServiceException;
import com.xn.common.page.BasePage;
import com.xn.common.service.BaseService;

/**
 * 业务接口实现基础类
 * @author yoko
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {



	public abstract BaseDao<T> getDao();

	public void add(T t) throws ServiceException {
		getDao().add(t);
	}

	public void update(T t) throws ServiceException {
		getDao().update(t);
	}

	public void delete(Object... ids) throws ServiceException {
		if (ids == null || ids.length < 1) {
			return;
		}
		for (Object id : ids) {
			getDao().delete(id);
		}
	}

	public int queryByCount(BasePage page) throws ServiceException {
		return getDao().queryByCount(page);
	}

	public List<T> queryByList(BasePage page) throws ServiceException {
		Integer rowCount = queryByCount(page);
		page.setRowCount(rowCount);
		return getDao().queryByList(page);
	}
	
	public List<T> queryAllList() throws ServiceException {
		return getDao().queryAllList();
	}
	
	public List<T> queryAllList(T t) throws ServiceException {
		return getDao().queryAllList(t);
	} 

	public T queryById(Object id) throws ServiceException {
		return getDao().queryById(id);
	}
	
	public T queryByCondition(T t) throws ServiceException {
		return getDao().queryByCondition(t);
	}

	public int queryCount(T t) throws ServiceException {
		return getDao().queryCount(t);
	}

	public int manyOperation(T t) {
		return getDao().manyOperation(t);
	}
}
