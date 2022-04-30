package org.competition.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="t_production")
public class Production {
	private int id;
	private String name;
	private Date settime;
	private String docts;
	private String descp;
	private String content;
	private Member member;
	private List<ExpertReview> expertReview;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",columnDefinition="int(11) comment '作品id,主键'")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name",columnDefinition="varchar(200) DEFAULT NULL COMMENT '作品名称'")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="settime",columnDefinition="datetime DEFAULT NULL COMMENT '发布时间'")
	public Date getSettime() {
		return settime;
	}
	public void setSettime(Date settime) {
		this.settime = settime;
	}
	
	@Column(name="docts",columnDefinition="varchar(200) DEFAULT NULL COMMENT '作品文件'")
	public String getDocts() {
		return docts;
	}
	public void setDocts(String docts) {
		this.docts = docts;
	}
	
	@Column(name="descp",columnDefinition="varchar(200) DEFAULT NULL COMMENT '作品简介'")
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	
	@Column(name="content",columnDefinition="text DEFAULT NULL COMMENT '作品详情'")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@ManyToOne
	@JoinColumn(name="member",columnDefinition="int(11) DEFAULT NULL COMMENT '参赛者id,外键'")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	@BatchSize(size=10)
	@OneToMany(fetch=FetchType.LAZY,mappedBy="production",cascade={CascadeType.REMOVE})
	public List<ExpertReview> getExpertReview() {
		return expertReview;
	}
	public void setExpertReview(List<ExpertReview> expertReview) {
		this.expertReview = expertReview;
	}
	
	public String toString() {
		return "Production [id=" + id+ "]";
	}

}

