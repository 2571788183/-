package org.competition.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.competition.dao.UserDao;
import org.competition.model.Search;
import org.competition.model.User;
import org.hibernate.Session;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.ibatis.annotations.Mapper;
import org.competition.dao.BaseMapper;
import org.competition.utils.ModelUtil;
import org.competition.model.PersistenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	@Mapper
	public interface UserMapper extends BaseMapper<User>{}
	@Autowired
	UserMapper userMapper;
	
	public User login(String username, String password) {
		User user = getByUsername(username);
		if (user == null) {
			throw new RuntimeException("用户名不存在");
		} else {
			if (!password.equals(user.getPassword())) {
				throw new RuntimeException("密码错误");
			}
		}
		return user;
	}
	
	public User getByUsername(String username) {
		User user=null;
		String sql = "select * from t_user where username = ?";
		try {
			user = (User)getSession().createSQLQuery(sql).addEntity(User.class).setParameter(1, username).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void save(User user) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(User.class, user);
			userMapper.save("user",values,user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(User user) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(User.class, user);
			userMapper.update("user",values);
			getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(User user) {
		getSession().delete((User)(getSession().load(User.class, user.getId())));
	}
	
	public User getById(int id) {
		return (User)getSession().load(User.class, id);
	}
	
	public List<User> query(String str) {
		return getSession().createQuery("from User").list();
	}


	public List<User> query(Search search) {
		return getSession().createQuery("from User").list();
	}


	
	private Session getSession(){
		return  entityManager.unwrap(Session.class);
	}

}
