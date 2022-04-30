package org.competition.dao;
import java.util.List;
import org.competition.model.Production;
import org.competition.model.Search;
public interface ProductionDao {
public void save(Production production);
public void delete(Production production);
public void update(Production production);
public Production getById(int id);
public List<Production> query(Search search);
public Production getByUserId(int id);
}

