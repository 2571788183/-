package org.competition.model;

public class Search {
	private int id=0;
	private String type="backend";
	private String string="";
	private String authorityName="";
	private String authorityValue="";
	private String membersearch="";
	private String judgesearch="";
	private String namesearch="";
	private String settimesearch="";
	private String shenhesearch="";
	private String productionsearch="";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public String getAuthorityValue() {
		return authorityValue;
	}
	public void setAuthorityValue(String authorityValue) {
		this.authorityValue = authorityValue;
	}
	public String getMembersearch() {
		return membersearch;
	}
	public void setMembersearch(String membersearch) {
		this.membersearch = membersearch;
	}
	public String getJudgesearch() {
		return judgesearch;
	}
	public void setJudgesearch(String judgesearch) {
		this.judgesearch = judgesearch;
	}
	public String getNamesearch() {
		return namesearch;
	}
	public void setNamesearch(String namesearch) {
		this.namesearch = namesearch;
	}
	public String getSettimesearch() {
		return settimesearch;
	}
	public void setSettimesearch(String settimesearch) {
		this.settimesearch = settimesearch;
	}
	public String getShenhesearch() {
		return shenhesearch;
	}
	public void setShenhesearch(String shenhesearch) {
		this.shenhesearch = shenhesearch;
	}
	public String getProductionsearch() {
		return productionsearch;
	}
	public void setProductionsearch(String productionsearch) {
		this.productionsearch = productionsearch;
	}
	@Override
	public String toString() {
		return "Search [ id=" + id + ",type=" + type + ", string=" + string
				+ ", authorityName=" + authorityName + ", authorityValue="
				+ authorityValue + ", membersearch=" + membersearch + ", judgesearch=" + judgesearch + ", namesearch=" + namesearch + ", settimesearch=" + settimesearch + ", shenhesearch=" + shenhesearch + ", productionsearch=" + productionsearch + "]";
	}
	
} 
