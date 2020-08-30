package com.xn.system.entity;

import java.util.Date;

/**
 * 系统操作日志
 * @author yoko
 * @since 2016-05-09
 */
public class Log {

	private Long id; //主键ID
	private String username; //操作用户帐号
	private String action; //操作动作
	private String detail; //操作详情
	private Date createTime; //操作时间
	private String type; //操作类型

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
