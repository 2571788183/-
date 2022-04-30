package org.competition.dao;
import java.util.List;
import org.competition.model.Judge;
import org.competition.model.Search;
public interface JudgeDao {
public void save(Judge judge);
public void delete(Judge judge);
public void update(Judge judge);
public Judge getById(int id);
public List<Judge> query(Search search);
public Judge getByUserId(int id);
}

