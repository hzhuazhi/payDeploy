package com.xn.system.model;

import com.xn.common.page.BasePage;

import java.util.Date;
import java.sql.Timestamp;


/**
 * @author:yoko
 * @since: 2016-06-27 10:15:58
 *
 */
public class RoleModuleRelModel extends BasePage {
 

    private Integer id; //自增主键
    private Integer roleId; //角色ID
    private Integer moduleId; //权限ID
    private Integer isOk; //是否有效，默认为0代表有效
	
    public void setId(Integer id){
       this.id = id;
    }
    
    public Integer getId(){
       return this.id ;
    }
    public void setRoleId(Integer roleId){
       this.roleId = roleId;
    }
    
    public Integer getRoleId(){
       return this.roleId ;
    }
    public void setModuleId(Integer moduleId){
       this.moduleId = moduleId;
    }
    
    public Integer getModuleId(){
       return this.moduleId ;
    }
    public void setIsOk(Integer isOk){
       this.isOk = isOk;
    }
    
    public Integer getIsOk(){
       return this.isOk ;
    }
	
}
