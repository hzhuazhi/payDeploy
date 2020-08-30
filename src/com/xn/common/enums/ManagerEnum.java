package com.xn.common.enums;

/**
 * Created by df on 2018/6/20 12:02
 */
public enum ManagerEnum {

     ;

    /**
     * @author df
     * @Description: TODO(角色类型的枚举)
     * @create 21:31 2018/9/6
     **/
    public enum RoleTypeEnum{
        ADMIN(1,"管理员账号"),
        TP(2,"渠道账号"),
        AGENT(3,"代理账号"),
        ;
        /**
         * 角色ID
         */
        private int roleType;
        /**
         * 描述
         */
        private String desc;

        private RoleTypeEnum(int roleType, String desc) {
            this.roleType = roleType;
            this.desc = desc;
        }

        public int getRoleType() {
            return roleType;
        }

        public void setRoleType(int roleType) {
            this.roleType = roleType;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * @author df
     * @Description: TODO(账号类型的枚举)
     * @create 21:31 2018/9/6
     **/
    public enum AccountTypeEnum{
        ACCOUNT("ACCOUNT","管理员账号"),
        OTHER_ACCOUNT("OTHER_ACCOUNT","其它账号..开发者、渠道、广告主等账号"),
        ;
        /**
         * 账号类型
         */
        private String accountType;
        /**
         * 描述
         */
        private String desc;

        private AccountTypeEnum(String accountType, String desc) {
            this.accountType = accountType;
            this.desc = desc;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
