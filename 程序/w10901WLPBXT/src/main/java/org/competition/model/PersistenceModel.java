package org.competition.model;

public class PersistenceModel {
	protected Object value;
	protected String jdbcType;
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	@Override
	public String toString() {
		return "PersistenceModel [value=" + value + ", jdbcType=" + jdbcType + "]";
	}
}
