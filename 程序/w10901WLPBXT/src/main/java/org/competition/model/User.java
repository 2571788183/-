package org.competition.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_user")
public class User {
	private int id;
	private String username;
	private String password;
	private Role role;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",columnDefinition="int(11) comment '主键'") 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="username",columnDefinition="varchar(200) comment '用户名'")  
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password",columnDefinition="varchar(200) comment '密码'")  
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@ManyToOne
	@JoinColumn(name="role",columnDefinition="int(11) comment '外键，角色id'")  
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", role=" + role + "]";
	}
	
	
}
