package org.competition.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.competition.dao.ExpertReviewDao;
import org.competition.model.ExpertReview;
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


@Repository("expertReviewDao")
public class ExpertReviewDaoImpl implements ExpertReviewDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Mapper
	public interface ExpertReviewMapper extends BaseMapper<ExpertReview>{}
	@Autowired
	ExpertReviewMapper expertReviewMapper;
	
	
	public void save(ExpertReview expertReview) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(ExpertReview.class, expertReview);
			expertReviewMapper.save("expertReview",values,expertReview);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(ExpertReview expertReview) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(ExpertReview.class, expertReview);
			expertReviewMapper.update("expertReview",values);
			getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(ExpertReview expertReview) {
		getSession().delete((ExpertReview)(getSession().load(ExpertReview.class, expertReview.getId())));
	}

	public ExpertReview getById(int id) {
		return (ExpertReview)getSession().load(ExpertReview.class, id);
	}
	
	public ExpertReview getByUserId(int id) {
		ExpertReview expertReview=null;
		String sql = "select * from t_expertReview where users = ?";
		try {
			expertReview = (ExpertReview)getSession().createSQLQuery(sql).addEntity(ExpertReview.class).setParameter(1, id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expertReview;
	}
	public List<ExpertReview> query(Search search) {
		List list=new ArrayList();
		String sql = "select * from t_expertReview  where 1=1 ";
		if(search.getType().equals("backend")){
			if(!search.getProductionsearch().equals("")){
				sql+="  and production="+search.getProductionsearch()+" ";
			}
			if(!search.getJudgesearch().equals("")){
				sql+="  and judge="+search.getJudgesearch()+" ";
			}
		}
		if(!search.getAuthorityName().equals("")){
			sql+=" and "+search.getAuthorityName()+" = "+search.getAuthorityValue()+" ";
		}
		String sqlRecordsCount = "select count(*) "
				+ sql.substring(sql.indexOf("from"));
		sql += " limit ?,?";
		try {
			Integer recordsCount = expertReviewMapper.selectCount(sqlRecordsCount);
			PageContext.getPage().setRecordsCount(recordsCount.intValue());
			list =	getSession().createSQLQuery(sql)
					.addEntity(ExpertReview.class)
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
