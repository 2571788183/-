package org.competition.dao;

import java.util.List;

public interface BaseDao {
	public void save(Object entity);
	public void udpate(Object entity);
	public void del(Object entity);
	public <T> T findById(Class<T> entityClass,int id);
	public <T> List<T> findAll(Class<T> entityClass);
}
