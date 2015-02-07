package com.dj.dy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role {

	private String roleId;
	private String roleName;
	private String rolePath;

	@Id
	@Column(name = "ROLE_ID")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ROLE_PATH")
	public String getRolePath() {
		return rolePath;
	}

	public void setRolePath(String rolePath) {
		this.rolePath = rolePath;
	}

}
