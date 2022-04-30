package org.competition.service;

import java.util.List;

import org.competition.model.Shenhe;
import org.competition.model.Search;

public interface ShenheService extends BaseService{
	public void add(Shenhe shenhe);
	public void delete(Shenhe shenhe);
	public void update(Shenhe shenhe);
	public Shenhe findById(int id);
	public Shenhe findByUserId(int id);
	public List<Shenhe> search(Search search);
}

