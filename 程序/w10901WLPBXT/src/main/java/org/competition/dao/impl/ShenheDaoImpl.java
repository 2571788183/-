package org.competition.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.competition.dao.ShenheDao;
import org.competition.model.Shenhe;
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


@Repository("shenheDao")
public class ShenheDaoImpl implements ShenheDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Mapper
	public interface ShenheMapper extends BaseMapper<Shenhe>{}
	@Autowired
	ShenheMapper shenheMapper;
	
	
	public void save(Shenhe shenhe) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Shenhe.class, shenhe);
			shenheMapper.save("shenhe",values,shenhe);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Shenhe shenhe) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Shenhe.class, shenhe);
			shenheMapper.update("shenhe",values);
			getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Shenhe shenhe) {
		getSession().delete((Shenhe)(getSession().load(Shenhe.class, shenhe.getId())));
	}

	public Shenhe getById(int id) {
		return (Shenhe)getSession().load(Shenhe.class, id);
	}
	
	public Shenhe getByUserId(int id) {
		Shenhe shenhe=null;
		String sql = "select * from t_shenhe where users = ?";
		try {
			shenhe = (Shenhe)getSession().createSQLQuery(sql).addEntity(Shenhe.class).setParameter(1, id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shenhe;
	}
	public List<Shenhe> query(Search search) {
		List list=new ArrayList();
		String sql = "select * from t_shenhe  where 1=1 ";
		if(search.getType().equals("string")){
			sql+=" and name like '%"+search.getString()+"%' ";
		}
		if(!search.getAuthorityName().equals("")){
			sql+=" and "+search.getAuthorityName()+" = "+search.getAuthorityValue()+" ";
		}
		String sqlRecordsCount = "select count(*) "
				+ sql.substring(sql.indexOf("from"));
		sql += " limit ?,?";
		try {
			Integer recordsCount = shenheMapper.selectCount(sqlRecordsCount);
			PageContext.getPage().setRecordsCount(recordsCount.intValue());
			list =	getSession().createSQLQuery(sql)
					.addEntity(Shenhe.class)
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
