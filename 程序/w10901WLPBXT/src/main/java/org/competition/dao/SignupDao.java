package org.competition.dao;
import java.util.List;
import org.competition.model.Signup;
import org.competition.model.Search;
public interface SignupDao {
public void save(Signup signup);
public void delete(Signup signup);
public void update(Signup signup);
public Signup getById(int id);
public List<Signup> query(Search search);
public Signup getByUserId(int id);
}

