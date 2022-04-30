package org.competition.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.competition.dao.MemberDao;
import org.competition.model.Member;
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


@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Mapper
	public interface MemberMapper extends BaseMapper<Member>{}
	@Autowired
	MemberMapper memberMapper;
	
	
	public void save(Member member) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Member.class, member);
			memberMapper.save("member",values,member);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Member member) {
		try {
			Map<String,PersistenceModel> values = ModelUtil.values(Member.class, member);
			memberMapper.update("member",values);
			getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Member member) {
		getSession().delete(member.getUsers());
		getSession().delete((Member)(getSession().load(Member.class, member.getId())));
	}

	public Member getById(int id) {
		return (Member)getSession().load(Member.class, id);
	}
	
	public Member getByUserId(int id) {
		Member member=null;
		String sql = "select * from t_member where users = ?";
		try {
			member = (Member)getSession().createSQLQuery(sql).addEntity(Member.class).setParameter(1, id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	public List<Member> query(Search search) {
		List list=new ArrayList();
		String sql = "select * from t_member  where 1=1 ";
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
			Integer recordsCount = memberMapper.selectCount(sqlRecordsCount);
			PageContext.getPage().setRecordsCount(recordsCount.intValue());
			list =	getSession().createSQLQuery(sql)
					.addEntity(Member.class)
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
