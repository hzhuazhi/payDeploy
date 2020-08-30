package com.xn.common.constant;

/**
 * @author df
 * @Description:所有常量
 * @create 2018-04-16 19:51
 **/
public class ManagerConstant {

    /**
     * 公共，公用的常量
     */
    public final class PUBLIC_CONSTANT{
        /**
         * 系统管理员
         */
        public static final int ROLE_SYS = 1;

        /**
         * 渠道
         */
        public static final int ROLE_TP = 2;

        /**
         * 代理
         */
        public static final int ROLE_AGENT = 3;



        /**
         *值是否等于0的判断条件
         */
        public static final int SIZE_VALUE_ZERO = 0;

        /**
         * 值是否等于1的判断条件
         */
        public static final int SIZE_VALUE_ONE = 1;

        /**
         * 用户登录缓存到session的账号
         */
        public static final String ACCOUNT = "ACCOUNT";

        /**
         * 是否启用：0初始化属于暂停状态，1表示暂停使用，2正常状态
         * 这里1表示暂停
         */
        public static final int IS_ENABLE_ZT = 1;

        /**
         * 是否启用：0初始化属于暂停状态，1表示暂停使用，2正常状态
         * 这里2表示正常
         */
        public static final int IS_ENABLE_ZC = 2;

        /**
         * 批次类型：1系统自动生成，2手动录入
         * 这里2表示手动录入
         */
        public static final int BATCH_TYPE_SDLR = 2;

        /**
         * 是否需要对广告进行处理：0初始化，1不需要处理，2需要处理
         * 这里2表示不需要处理
         */
        public static final int IS_HANDLE_XYCL = 2;

    }
}
