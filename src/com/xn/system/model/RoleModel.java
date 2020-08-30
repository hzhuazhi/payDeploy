package com.xn.system.model;

import com.xn.common.page.BasePage;

import java.util.Date;
import java.sql.Timestamp;


/**
 * @author:yoko
 * @since: 2016-06-22 19:38:09
 *
 */
public class RoleModel extends BasePage {
 

    private Integer roleId; //角色ID
    private String roleName; //角色名称
    private Integer roleType; //角色类型（0为后台，1为厂家，2为用户）
    private Date createTime; //创建时间
    private Integer isOk; //是否有效
    private String remark; //备注
    private Timestamp updateTime; //更新时间
	
    public void setRoleId(Integer roleId){
       this.roleId = roleId;
    }
    
    public Integer getRoleId(){
       return this.roleId ;
    }
    public void setRoleName(String roleName){
       this.roleName = roleName;
    }
    
    public String getRoleName(){
       return this.roleName ;
    }
    public void setRoleType(Integer roleType){
       this.roleType = roleType;
    }
    
    public Integer getRoleType(){
       return this.roleType ;
    }
    public void setCreateTime(Date createTime){
       this.createTime = createTime;
    }
    
    public Date getCreateTime(){
       return this.createTime ;
    }
    public void setIsOk(Integer isOk){
       this.isOk = isOk;
    }
    
    public Integer getIsOk(){
       return this.isOk ;
    }
    public void setRemark(String remark){
       this.remark = remark;
    }
    
    public String getRemark(){
       return this.remark ;
    }
    public void setUpdateTime(Timestamp updateTime){
       this.updateTime = updateTime;
    }
    
    public Timestamp getUpdateTime(){
       return this.updateTime ;
    }
	
}
