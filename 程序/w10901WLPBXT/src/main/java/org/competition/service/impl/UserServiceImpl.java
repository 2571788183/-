package org.competition.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.competition.dao.UserDao;
import org.competition.model.Role;
import org.competition.model.Search;
import org.competition.model.User;
import org.competition.service.UserService;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	public void add(User user) {
		userDao.save(user);
	}

	public void delete(User user) {
		userDao.delete(user);
	}
	
	public void update(User user) {
		userDao.update(user);
	}

	public User findById(int id) {
		return userDao.getById(id);
	}

	public User findByUsername(String username) {
		return userDao.getByUsername(username);
	}
	
	public List<User> search(String str) {
		return userDao.query(str);
	}
	public List<User> search(Search search) {
		return userDao.query(search);
	}

}
