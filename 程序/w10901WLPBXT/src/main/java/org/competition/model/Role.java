package org.competition.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_role")
public class Role {
	private int id;
	private String name;
	private String descp;

	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",columnDefinition="int(11) comment '主键'") 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="name",columnDefinition="varchar(200) comment '角色'")  
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="descp",columnDefinition="varchar(200) comment '权限'")  
	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", descp=" + descp + "]";
	}
}
