package org.competition.dao;

import java.util.List;

import org.competition.model.Role;

public interface RoleDao {
	public void save(Role role);

	public void delete(Role role);

	public void update(Role role);

	public Role getById(int id);

	public List<Role> query(String str) ;
}
