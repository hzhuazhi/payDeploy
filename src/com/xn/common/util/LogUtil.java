package com.xn.common.util;

import org.apache.log4j.Logger;

public class LogUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LogUtil.class);
	
	public final static void info(String log){
		logger.info(log);
	}

	public final static void error(String log){
		logger.error(log);
	}
}
