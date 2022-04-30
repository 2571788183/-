package org.competition.service.impl;

import javax.annotation.Resource;

import org.competition.service.BaseService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service("baseService")
public class BaseServiceImpl implements BaseService {
	@Resource
	private SessionFactory sessionFactory;
	@Override
	public void sessionClear() {}
	@Override
	public void sessionFlush() {}
	@Override
	public void sessionRefresh(Object object) {}
	@Override
	public void sessionEvict(Object object) {
		getSession().evict(object);
	}
	public Session getSession(){
		return  sessionFactory.getCurrentSession();
	}
}
