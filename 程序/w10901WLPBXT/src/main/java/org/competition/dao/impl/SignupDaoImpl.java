package org.competition.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.competition.dao.SignupDao;
import org.competition.model.Signup;
import org.competition.model.Search;
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


@Repository("signupDao")
public class SignupDaoImpl implements SignupDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Mapper
	public interface SignupMapper extends BaseMapper<Signup>{}
	@Autowired
	SignupMapper signupMapper;
	
	
	public void save(Signup signup) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Signup.class, signup);
			signupMapper.save("signup",values,signup);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Signup signup) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Signup.class, signup);
			signupMapper.update("signup",values);
			getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Signup signup) {
		getSession().delete((Signup)(getSession().load(Signup.class, signup.getId())));
	}

	public Signup getById(int id) {
		return (Signup)getSession().load(Signup.class, id);
	}
	
	public Signup getByUserId(int id) {
		Signup signup=null;
		String sql = "select * from t_signup where users = ?";
		try {
			signup = (Signup)getSession().createSQLQuery(sql).addEntity(Signup.class).setParameter(1, id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signup;
	}
	public List<Signup> query(Search search) {
		List list=new ArrayList();
		String sql = "select * from t_signup  where 1=1 ";
		if(search.getType().equals("backend")){
			if(!search.getNamesearch().equals(""))sql+=" and name like '%"+search.getNamesearch()+"%' ";
			if(!search.getSettimesearch().equals(""))sql+=" and settime like '%"+search.getSettimesearch()+"%' ";
			if(!search.getShenhesearch().equals("")){
				sql+="  and shenhe="+search.getShenhesearch()+" ";
			}
		}
		if(!search.getAuthorityName().equals("")){
			sql+=" and "+search.getAuthorityName()+" = "+search.getAuthorityValue()+" ";
		}
		String sqlRecordsCount = "select count(*) "
				+ sql.substring(sql.indexOf("from"));
		sql += " limit ?,?";
		try {
			Integer recordsCount = signupMapper.selectCount(sqlRecordsCount);
			PageContext.getPage().setRecordsCount(recordsCount.intValue());
			list =	getSession().createSQLQuery(sql)
					.addEntity(Signup.class)
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
