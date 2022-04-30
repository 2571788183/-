package org.competition.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_expertReview")
public class ExpertReview {
	private int id;
	private Production production;
	private Date settime;
	private String yijiancontent;
	private int score;
	private String jieluncontent;
	private Judge judge;
	private Member member;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",columnDefinition="int(11) comment '专家评审id,主键'")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="production",columnDefinition="int(11) DEFAULT NULL COMMENT '作品id,外键'")
	public Production getProduction() {
		return production;
	}
	public void setProduction(Production production) {
		this.production = production;
	}
	
	@Column(name="settime",columnDefinition="datetime DEFAULT NULL COMMENT '发布时间'")
	public Date getSettime() {
		return settime;
	}
	public void setSettime(Date settime) {
		this.settime = settime;
	}
	
	@Column(name="yijiancontent",columnDefinition="text DEFAULT NULL COMMENT '专家意见'")
	public String getYijiancontent() {
		return yijiancontent;
	}
	public void setYijiancontent(String yijiancontent) {
		this.yijiancontent = yijiancontent;
	}
	
	@Column(name="score",columnDefinition="int(11) DEFAULT NULL COMMENT '评分'")
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Column(name="jieluncontent",columnDefinition="text DEFAULT NULL COMMENT '结论'")
	public String getJieluncontent() {
		return jieluncontent;
	}
	public void setJieluncontent(String jieluncontent) {
		this.jieluncontent = jieluncontent;
	}
	
	@ManyToOne
	@JoinColumn(name="judge",columnDefinition="int(11) DEFAULT NULL COMMENT '评委id,外键'")
	public Judge getJudge() {
		return judge;
	}
	public void setJudge(Judge judge) {
		this.judge = judge;
	}
	
	@ManyToOne
	@JoinColumn(name="member",columnDefinition="int(11) DEFAULT NULL COMMENT '参赛者id,外键'")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	public String toString() {
		return "ExpertReview [id=" + id+ "]";
	}

}

