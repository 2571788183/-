package org.competition.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.competition.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class BaseDaoImpl implements BaseDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void del(Object entity) {
		getSession().delete(entity);
	}

	@Override
	public <T> List<T> findAll(Class<T> entityClass) {
		return getSession().createCriteria(entityClass).list();
	}

	@Override
	public <T> T findById(Class<T> entityClass, int id) {
		return (T)getSession().load(entityClass, id);
	}

	@Override
	public void save(Object entity) {
		getSession().save(entity);
	}

	@Override
	public void udpate(Object entity) {
		getSession().update(entity);
	}
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
}
