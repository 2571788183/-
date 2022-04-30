package org.competition.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.competition.dao.ProductionDao;
import org.competition.model.Production;
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


@Repository("productionDao")
public class ProductionDaoImpl implements ProductionDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Mapper
	public interface ProductionMapper extends BaseMapper<Production>{}
	@Autowired
	ProductionMapper productionMapper;
	
	
	public void save(Production production) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Production.class, production);
			productionMapper.save("production",values,production);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Production production) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Production.class, production);
			productionMapper.update("production",values);
			getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Production production) {
		getSession().delete((Production)(getSession().load(Production.class, production.getId())));
	}

	public Production getById(int id) {
		return (Production)getSession().load(Production.class, id);
	}
	
	public Production getByUserId(int id) {
		Production production=null;
		String sql = "select * from t_production where users = ?";
		try {
			production = (Production)getSession().createSQLQuery(sql).addEntity(Production.class).setParameter(1, id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return production;
	}
	public List<Production> query(Search search) {
		List list=new ArrayList();
		String sql = "select * from t_production  where 1=1 ";
		if(search.getType().equals("backend")){
			if(!search.getNamesearch().equals(""))sql+=" and name like '%"+search.getNamesearch()+"%' ";
		}
		if(!search.getAuthorityName().equals("")){
			sql+=" and "+search.getAuthorityName()+" = "+search.getAuthorityValue()+" ";
		}
		String sqlRecordsCount = "select count(*) "
				+ sql.substring(sql.indexOf("from"));
		sql += " limit ?,?";
		try {
			Integer recordsCount = productionMapper.selectCount(sqlRecordsCount);
			PageContext.getPage().setRecordsCount(recordsCount.intValue());
			list =	getSession().createSQLQuery(sql)
					.addEntity(Production.class)
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
