package org.competition.service.impl;

import java.util.List;
import javax.annotation.Resource
;import org.springframework.stereotype.Service;
import org.competition.dao.ExpertReviewDao;
import org.competition.model.ExpertReview;
import org.competition.model.Search;
import org.competition.service.ExpertReviewService;

@Service("expertReviewService")
public class ExpertReviewServiceImpl extends BaseServiceImpl implements ExpertReviewService {
	@Resource
	ExpertReviewDao expertReviewDao ;
	public void add(ExpertReview expertReview) {
		expertReviewDao.save(expertReview);
	}

	public void delete(ExpertReview expertReview) {
		expertReviewDao.delete(expertReview);
	}

	public void update(ExpertReview expertReview) {
		expertReviewDao.update(expertReview);
	}

	public ExpertReview findById(int id) {
		return expertReviewDao.getById(id);
	}

	public ExpertReview findByUserId(int id) {
		return expertReviewDao.getByUserId(id);
	}

	public List<ExpertReview> search(Search search) {
		return expertReviewDao.query(search);
	}

}
