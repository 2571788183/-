package org.competition.service.impl;

import java.util.List;
import javax.annotation.Resource
;import org.springframework.stereotype.Service;
import org.competition.dao.JudgeDao;
import org.competition.model.Judge;
import org.competition.model.Search;
import org.competition.service.JudgeService;

@Service("judgeService")
public class JudgeServiceImpl extends BaseServiceImpl implements JudgeService {
	@Resource
	JudgeDao judgeDao ;
	public void add(Judge judge) {
		judgeDao.save(judge);
	}

	public void delete(Judge judge) {
		judgeDao.delete(judge);
	}

	public void update(Judge judge) {
		judgeDao.update(judge);
	}

	public Judge findById(int id) {
		return judgeDao.getById(id);
	}

	public Judge findByUserId(int id) {
		return judgeDao.getByUserId(id);
	}

	public List<Judge> search(Search search) {
		return judgeDao.query(search);
	}

}
