package com.xn.system.service.impl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.system.dao.AccountDao;
import com.xn.system.entity.Account;
import com.xn.system.model.AccountModel;
import com.xn.system.service.AccountService;

/**
 * @author: yoko
 * @since: 2016-06-22 20:29:37
 *
 */
@Service("accountService")
public class AccountServiceImpl<T> extends BaseServiceImpl<T> implements AccountService<T> {

	private final static Logger log = Logger.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountDao<T> accountDao;

	@Override
	public BaseDao<T> getDao() {
		return accountDao;
	}

}