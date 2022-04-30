package org.competition.dao;
import java.util.List;
import org.competition.model.ExpertReview;
import org.competition.model.Search;
public interface ExpertReviewDao {
public void save(ExpertReview expertReview);
public void delete(ExpertReview expertReview);
public void update(ExpertReview expertReview);
public ExpertReview getById(int id);
public List<ExpertReview> query(Search search);
public ExpertReview getByUserId(int id);
}

