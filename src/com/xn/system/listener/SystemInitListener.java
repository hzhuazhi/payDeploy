package com.xn.system.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.xn.common.constant.Constant;
import com.xn.common.util.DateUtil;

/**
 * 系统启动监听器,继承servletContextListener,添加自定义提醒
 * 加载缓存，配置，系统初始化参数等信息
 * 
 * @author yoko
 * @since 2016-05-09
 */
public class SystemInitListener implements ServletContextListener {
	private static Log log = LogFactory.getLog(SystemInitListener.class);
	private boolean success = true;
	private ContextLoader contextLoader;
	private ApplicationContext wac = null;

	/**
	 * Initialize the root web application context.
	 */
	public void contextInitialized(ServletContextEvent event) {
		this.contextLoader = createContextLoader();
		systemStartup(event.getServletContext());
	}

	protected ContextLoader createContextLoader() {
		return new ContextLoader();
	}

	public ContextLoader getContextLoader() {
		return this.contextLoader;
	}

	public void contextDestroyed(ServletContextEvent event) {
		if (this.contextLoader != null) {
			this.contextLoader.closeWebApplicationContext(event.getServletContext());
		}
	}

	/**
	 * 应用平台启动
	 * @param servletContext
	 */
	private void systemStartup(ServletContext servletContext) {
		long start = System.currentTimeMillis();
		log.info("*******************************************************");
		log.info("破解服务后台开始启动...");
		log.info("*******************************************************");
		try {
			log.info("系统正在初始化服务容器...");
			wac = this.contextLoader.initWebApplicationContext(servletContext);
			log.info("容器初始化成功啦，您的托管Bean已经被实例化。");
		} catch (Exception e) {
			success = false;
			log.error(Constant.Exception_Head + "初始化服务容器发生错误,请仔细检查您的配置文件喔!\n" + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}

		//============================================================================================
		//TODO
		//此处可加上系统初始化操作，系统启动时，加载缓存或者系统配置参数等

		//============================================================================================

		long timeSec = (System.currentTimeMillis() - start) / 1000;
		log.info("********************************************");
		if (success) {
			log.info("破解后台系统[crack]启动成功[" + DateUtil.currentTimestamp() + "]");
			log.info("启动总耗时: " + timeSec / 60 + "分 " + timeSec % 60 + "秒 ");
		} else {
			log.error("破解后台系统[crack][" + DateUtil.currentTimestamp() + "]");
			log.error("启动总耗时: " + timeSec / 60 + "分" + timeSec % 60 + "秒");
		}
		log.info("********************************************");
	}

	/**
	 * 获取一个SpringBean服务
	 * 
	 * @param pBeanId
	 *            Spring配置文件名中配置的SpringID号
	 * @return Object 返回的SpringBean实例
	 */
	public Object getSpringBean(String pBeanId) {
		Object springBean = null;
		try {
			springBean = wac.getBean(pBeanId);
		} catch (NoSuchBeanDefinitionException e) {
			log.error(Constant.Exception_Head + "Spring配置文件中没有匹配到ID号为:[" + pBeanId + "]的SpringBean组件,请检查!");
			log.error(e.getMessage());
		}
		return springBean;
	}
}