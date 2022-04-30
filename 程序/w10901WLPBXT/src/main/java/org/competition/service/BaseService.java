package org.competition.service;
import org.hibernate.Session;

public interface BaseService{
	public Session getSession();
	public void sessionClear();
	public void sessionFlush();
	public void sessionRefresh(Object object);
	public void sessionEvict(Object object);
}

