package org.competition.service;

import java.util.List;

import org.competition.model.Judge;
import org.competition.model.Search;

public interface JudgeService extends BaseService{
	public void add(Judge judge);
	public void delete(Judge judge);
	public void update(Judge judge);
	public Judge findById(int id);
	public Judge findByUserId(int id);
	public List<Judge> search(Search search);
}

