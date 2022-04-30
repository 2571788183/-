package org.competition.service;

import java.util.List;

import org.competition.model.Search;
import org.competition.model.User;


public interface UserService {
	public void add(User user) ;
	public void update(User user);
	public void delete(User user);
	public User findById(int id);
	public User findByUsername(String username);
	public List<User> search(String str);
	public List<User> search(Search search);
}
