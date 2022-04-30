package org.competition.service;

import java.util.List;

import org.competition.model.Production;
import org.competition.model.Search;

public interface ProductionService extends BaseService{
	public void add(Production production);
	public void delete(Production production);
	public void update(Production production);
	public Production findById(int id);
	public Production findByUserId(int id);
	public List<Production> search(Search search);
}

