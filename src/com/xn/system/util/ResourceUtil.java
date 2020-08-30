package com.xn.system.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author df
 * @Description:配置文件读取类
 * @create 2018-09-04 17:54
 **/
public class ResourceUtil {
    private static Log log = LogFactory.getLog(ResourceUtil.class);
//    private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("application_"+System.getProperty("profilesName")+".properties");
    private static final ResourceBundle bundle = ResourceBundle.getBundle("application_"+ System.getProperty("profilesName"));


    public static final String getData(String proKey){
//        log.info("------------------------环境:"+System.getProperty("profilesName"));
//        log.info("-----------------------------dir:"+System.getProperty("user.dir"));
        ResourceBundle bundle = ResourceBundle.getBundle("application_"+ System.getProperty("profilesName"));
        return bundle.getString(proKey);
    }
}
