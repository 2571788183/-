package org.competition.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_signup")
public class Signup {
	private int id;
	private String name;
	private Date settime;
	private String tel;
	private String email;
	private String idno;
	private String birthday;
	private String content;
	private Shenhe shenhe;
	private Member member;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",columnDefinition="int(11) comment '报名id,主键'")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name",columnDefinition="varchar(200) DEFAULT NULL COMMENT '姓名'")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="settime",columnDefinition="datetime DEFAULT NULL COMMENT '报名时间'")
	public Date getSettime() {
		return settime;
	}
	public void setSettime(Date settime) {
		this.settime = settime;
	}
	
	@Column(name="tel",columnDefinition="varchar(200) DEFAULT NULL COMMENT '电话'")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Column(name="email",columnDefinition="varchar(200) DEFAULT NULL COMMENT '邮箱'")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="idno",columnDefinition="varchar(200) DEFAULT NULL COMMENT '身份证号'")
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	
	@Column(name="birthday",columnDefinition="varchar(200) DEFAULT NULL COMMENT '出生日期'")
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Column(name="content",columnDefinition="text DEFAULT NULL COMMENT '其他报名材料'")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@ManyToOne
	@JoinColumn(name="shenhe",columnDefinition="int(11) DEFAULT NULL COMMENT '审核id,外键'")
	public Shenhe getShenhe() {
		return shenhe;
	}
	public void setShenhe(Shenhe shenhe) {
		this.shenhe = shenhe;
	}
	
	@ManyToOne
	@JoinColumn(name="member",columnDefinition="int(11) DEFAULT NULL COMMENT '报名账号id,外键'")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	public String toString() {
		return "Signup [id=" + id+ "]";
	}

}

