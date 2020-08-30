package com.xn.common.constant;

/**
 *
 * 总体说明
 *
 * <p>具体说明</p>
 *
 * @author yoko
 * @createTime  2016-5-9 下午01:15:09
 */
public class Constant {

	/**
	 * 异常信息统一头信息<br>
	 * 非常遗憾的通知您,程序发生了异常
	 */
	public static final String Exception_Head = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS. ";

	/**
	 * 默认密码
	 */
	public static String DEFAULT_PASSWORD = "123456";
	/**
	 * 自增主键：创建的代码的ID的类型=dm
	 */
	public static final String PRIMARY_TYPE_VALUE_DM="dm";
	/**
	 * 自增主键：创建的通道的ID的类型=ch
	 */
	public static final String PRIMARY_TYPE_VALUE_CH="ch";
	/**
	 * 自增主键：创建的破解的ID的类型=pj
	 */
	public static final String PRIMARY_TYPE_VALUE_PJ="pj";
	/**
	 * 自增主键：创建的虚拟机的ID的类型=xnj
	 */
	public static final String PRIMARY_TYPE_VALUE_XNJ="xnj";
	/**
	 * 自增主键：创建的业务的ID的类型=yw
	 */
	public static final String PRIMARY_TYPE_VALUE_YW="yw";
	/**
	 * 自增主键：创建的破解SDK的ID的类型=sdk
	 */
	public static final String PRIMARY_TYPE_VALUE_SDK="sdk";
	/**
	 * 自增主键：创建的代理ip的ID的类型=dl
	 */
	public static final String PRIMARY_TYPE_VALUE_DL="dl";
	
	public static final String PRIMARY_TYPE_UNDERLINE="_";
	
	/**
	 * 存放文件路径地址
	 */
    public static String APP_PATH="//opt//app//tomcat08//apache-tomcat-6.0.44//bin";
//	public static String APP_PATH="D:\\opt";
    
    public static String APP_DOWN_URL="http://116.62.102.4:13008/fileServer/file/fStart.do?fiNa=###fileName###&fiAres=&fiNaAres=###filePath###";
}
