package org.competition.service;

import java.util.List;

import org.competition.model.ExpertReview;
import org.competition.model.Search;

public interface ExpertReviewService extends BaseService{
	public void add(ExpertReview expertReview);
	public void delete(ExpertReview expertReview);
	public void update(ExpertReview expertReview);
	public ExpertReview findById(int id);
	public ExpertReview findByUserId(int id);
	public List<ExpertReview> search(Search search);
}

