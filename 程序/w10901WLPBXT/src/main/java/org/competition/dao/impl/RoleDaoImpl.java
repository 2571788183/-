package org.competition.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.competition.dao.RoleDao;
import org.competition.model.Role;
import org.competition.utils.PageContext;
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


@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager entityManager;
	@Mapper
	public interface RoleMapper extends BaseMapper<Role>{}
	@Autowired
	RoleMapper roleMapper;
	
	
	public void save(Role role) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Role.class, role);
			roleMapper.save("role",values,role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Role role) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Role.class, role);
			roleMapper.update("role",values);
			getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Role role) {
		getSession().delete((Role)(getSession().load(Role.class, role.getId())));
	}

	public Role getById(int id) {
		return (Role)getSession().load(Role.class, id);
	}
	
	public List<Role> query(String str) {
		List list=null;
		String sql = "select * from t_role";
		String sqlRecordsCount = "select count(*) "
				+ sql.substring(sql.indexOf("from"));
		sql += " limit ?,?";
		try {
			Integer recordsCount = roleMapper.selectCount(sqlRecordsCount);
			PageContext.getPage().setRecordsCount(recordsCount.intValue());
			list =	getSession().createSQLQuery(sql)
					.addEntity(Role.class)
					.setParameter(1, PageContext.getPage().getPageIndex())
					.setParameter(2, PageContext.getPage().getPageSize())
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
	private Session getSession(){
		return  entityManager.unwrap(Session.class);
	}



}
