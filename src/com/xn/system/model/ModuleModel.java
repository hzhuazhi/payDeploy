package com.xn.system.model;

import java.util.Date;

import com.xn.common.page.BasePage;

/**
 * 
 * ClassName: ModuleModel
 * @Description: TODO
 * @author yoko
 * @date 2016-7-26
 */
public class ModuleModel extends BasePage {

	private Integer moduleId; //模块ID（权限ID）
	private String moduleName; //模块名称
	private Integer parentId; //父级模块ID
	private Date createTime; //创建时间
	private Integer isOk; //是否有效
	private String actionUrl; //请求动作
	private String remark; //备注
	private Integer type; //权限类型（0：后台；1：影楼，2：厂家）
	private Integer level; //菜单级别（1：导航栏2，模块级，3页面级）
	private Integer isAllowed; //是否可分配：0：可以1：不可以
	private Integer sort; //排序
	private Integer roleId; //角色ID
	private Integer accountId; //帐号ID

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}

	public Integer getIsOk() {
		return this.isOk;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getActionUrl() {
		return this.actionUrl;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setIsAllowed(Integer isAllowed) {
		this.isAllowed = isAllowed;
	}

	public Integer getIsAllowed() {
		return this.isAllowed;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return sort;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getAccountId() {
		return accountId;
	}

}
