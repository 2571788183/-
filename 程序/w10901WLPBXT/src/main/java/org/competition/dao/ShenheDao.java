package org.competition.dao;
import java.util.List;
import org.competition.model.Shenhe;
import org.competition.model.Search;
public interface ShenheDao {
public void save(Shenhe shenhe);
public void delete(Shenhe shenhe);
public void update(Shenhe shenhe);
public Shenhe getById(int id);
public List<Shenhe> query(Search search);
public Shenhe getByUserId(int id);
}

