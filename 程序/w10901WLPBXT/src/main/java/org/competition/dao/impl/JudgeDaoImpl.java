package org.competition.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.competition.dao.JudgeDao;
import org.competition.model.Judge;
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


@Repository("judgeDao")
public class JudgeDaoImpl implements JudgeDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Mapper
	public interface JudgeMapper extends BaseMapper<Judge>{}
	@Autowired
	JudgeMapper judgeMapper;
	
	
	public void save(Judge judge) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Judge.class, judge);
			judgeMapper.save("judge",values,judge);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Judge judge) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Judge.class, judge);
			judgeMapper.update("judge",values);
			getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Judge judge) {
		getSession().delete(judge.getUsers());
		getSession().delete((Judge)(getSession().load(Judge.class, judge.getId())));
	}

	public Judge getById(int id) {
		return (Judge)getSession().load(Judge.class, id);
	}
	
	public Judge getByUserId(int id) {
		Judge judge=null;
		String sql = "select * from t_judge where users = ?";
		try {
			judge = (Judge)getSession().createSQLQuery(sql).addEntity(Judge.class).setParameter(1, id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return judge;
	}
	public List<Judge> query(Search search) {
		List list=new ArrayList();
		String sql = "select * from t_judge  where 1=1 ";
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
			Integer recordsCount = judgeMapper.selectCount(sqlRecordsCount);
			PageContext.getPage().setRecordsCount(recordsCount.intValue());
			list =	getSession().createSQLQuery(sql)
					.addEntity(Judge.class)
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
