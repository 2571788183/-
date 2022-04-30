package org.competition.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import javax.persistence.CascadeType;
import org.hibernate.annotations.BatchSize;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_shenhe")
public class Shenhe {
	private int id;
	private String name;
	private String descp;
	private List<Signup> signup;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",columnDefinition="int(11) comment '报名审核id,主键'")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name",columnDefinition="varchar(200) DEFAULT NULL COMMENT '状态名称'")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="descp",columnDefinition="varchar(200) DEFAULT NULL COMMENT '备注'")
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	
	@BatchSize(size=10)
	@OneToMany(fetch=FetchType.LAZY,mappedBy="shenhe",cascade={CascadeType.REMOVE})
	public List<Signup> getSignup() {
		return signup;
	}
	public void setSignup(List<Signup> signup) {
		this.signup = signup;
	}
	
	public String toString() {
		return "Shenhe [id=" + id+ "]";
	}

}

