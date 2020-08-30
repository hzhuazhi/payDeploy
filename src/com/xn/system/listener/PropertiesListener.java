package com.xn.system.listener;

import com.xn.common.constant.Constant;
import com.xn.common.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author df
 * @Description:环境变量的监听器
 * @create 2018-09-04 17:43
 **/
public class PropertiesListener implements ServletContextListener {
    private static Log log = LogFactory.getLog(PropertiesListener.class);
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
        if (StringUtils.isNotEmpty(servletContext.getInitParameter("spring.profiles.active"))){
            //将当期文件名放入内存
            System.setProperty("profilesName", servletContext.getInitParameter("spring.profiles.active"));
            log.info(servletContext.getInitParameter("spring.profiles.active"));
        }else{
            //将当期文件名放入内存
            System.setProperty("profilesName", "development");

        }
    }

}
