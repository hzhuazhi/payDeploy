package com.xn.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * ClassName: Module
 * @Description: TODO
 * @author yoko
 * @date 2016-7-26
 */
public class Module {

	private Integer moduleId; //模块ID（权限ID）
	private String moduleName; //模块名称
	private Integer parentId; //父级模块ID
	private String parentName; //父级模块名称
	private Date createTime; //创建时间
	private Integer isOk; //是否有效
	private String actionUrl; //请求动作
	private String remark; //备注
	private Integer type; //权限类型（0：后台；1：影楼，2：厂家）
	private Integer level; //菜单级别（1：导航栏2，模块级，3页面级）
	private Integer isAllowed; //是否可分配：0：可以1：不可以
	private Integer sort; //排序
	private Integer moduleType;//模块菜单类型：1系统模块，2后台模块，3报表模块
	private boolean check = false; //是否被选中
	private List<Module> children = new ArrayList<Module>(); //包含的子菜单

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsOk() {
		return this.isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}

	public String getActionUrl() {
		return this.actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIsAllowed() {
		return this.isAllowed;
	}

	public void setIsAllowed(Integer isAllowed) {
		this.isAllowed = isAllowed;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return sort;
	}

	public void setChildren(List<Module> children) {
		this.children = children;
	}

	public List<Module> getChildren() {
		return children;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean isCheck() {
		return check;
	}

	public Integer getModuleType() {
		return moduleType;
	}

	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
	}
}
