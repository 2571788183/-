package org.competition.service;

import java.util.List;

import org.competition.model.Signup;
import org.competition.model.Search;

public interface SignupService extends BaseService{
	public void add(Signup signup);
	public void delete(Signup signup);
	public void update(Signup signup);
	public Signup findById(int id);
	public Signup findByUserId(int id);
	public List<Signup> search(Search search);
}

