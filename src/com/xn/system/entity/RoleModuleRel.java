package com.xn.system.entity;

import java.util.Date;
import java.sql.Timestamp;

/**
 * @author:yoko
 * @since: 2016-06-27 10:15:58
 *
 */
public class RoleModuleRel{

	private Integer id; //自增主键
	private Integer roleId; //角色ID
	private Integer moduleId; //权限ID
	private Integer isOk; //是否有效，默认为0代表有效

	public Integer getId(){
	   return this.id ;
	}

	public void setId(Integer id){
	   this.id = id;
	}
	
	public Integer getRoleId(){
	   return this.roleId ;
	}

	public void setRoleId(Integer roleId){
	   this.roleId = roleId;
	}
	
	public Integer getModuleId(){
	   return this.moduleId ;
	}

	public void setModuleId(Integer moduleId){
	   this.moduleId = moduleId;
	}
	
	public Integer getIsOk(){
	   return this.isOk ;
	}

	public void setIsOk(Integer isOk){
	   this.isOk = isOk;
	}
	
	
}
