package org.competition.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import javax.persistence.CascadeType;
import org.hibernate.annotations.BatchSize;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.hibernate.annotations.BatchSize;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.hibernate.annotations.BatchSize;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_member")
public class Member {
	private int id;
	private Role roles;
	private String username;
	private String password;
	private String img;
	private String name;
	private String sex;
	private Date birthday;
	private String tel;
	private String address;
	private String email;
	private String idcardimg;
	private User users;
	private List<Signup> signup;
	private List<Production> production;
	private List<ExpertReview> expertReview;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",columnDefinition="int(11) comment '用户id,主键'")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Transient
	public Role getRoles() {
		return roles;
	}
	public void setRoles(Role roles) {
		this.roles = roles;
	}
	@Transient
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="img",columnDefinition="varchar(200) DEFAULT NULL COMMENT '头像'")
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	@Column(name="name",columnDefinition="varchar(200) DEFAULT NULL COMMENT '姓名'")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="sex",columnDefinition="varchar(200) DEFAULT NULL COMMENT '性别'")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name="birthday",columnDefinition="datetime DEFAULT NULL COMMENT '出生日期'")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(name="tel",columnDefinition="varchar(200) DEFAULT NULL COMMENT '联系电话'")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Column(name="address",columnDefinition="varchar(200) DEFAULT NULL COMMENT '住址'")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="email",columnDefinition="varchar(200) DEFAULT NULL COMMENT '邮箱'")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="idcardimg",columnDefinition="varchar(200) DEFAULT NULL COMMENT '身份证'")
	public String getIdcardimg() {
		return idcardimg;
	}
	public void setIdcardimg(String idcardimg) {
		this.idcardimg = idcardimg;
	}
	
	@ManyToOne
	@JoinColumn(name="users",columnDefinition="int(11) DEFAULT NULL COMMENT '登录账号id,外键'")
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	
	@BatchSize(size=10)
	@OneToMany(fetch=FetchType.LAZY,mappedBy="member",cascade={CascadeType.REMOVE})
	public List<Signup> getSignup() {
		return signup;
	}
	public void setSignup(List<Signup> signup) {
		this.signup = signup;
	}
	
	@BatchSize(size=10)
	@OneToMany(fetch=FetchType.LAZY,mappedBy="member",cascade={CascadeType.REMOVE})
	public List<Production> getProduction() {
		return production;
	}
	public void setProduction(List<Production> production) {
		this.production = production;
	}
	
	@BatchSize(size=10)
	@OneToMany(fetch=FetchType.LAZY,mappedBy="member",cascade={CascadeType.REMOVE})
	public List<ExpertReview> getExpertReview() {
		return expertReview;
	}
	public void setExpertReview(List<ExpertReview> expertReview) {
		this.expertReview = expertReview;
	}
	
	public String toString() {
		return "Member [id=" + id+ "]";
	}

}

