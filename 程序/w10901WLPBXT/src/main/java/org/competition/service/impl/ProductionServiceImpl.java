package org.competition.service.impl;

import java.util.List;
import javax.annotation.Resource
;import org.springframework.stereotype.Service;
import org.competition.dao.ProductionDao;
import org.competition.model.Production;
import org.competition.model.Search;
import org.competition.service.ProductionService;

@Service("productionService")
public class ProductionServiceImpl extends BaseServiceImpl implements ProductionService {
	@Resource
	ProductionDao productionDao ;
	public void add(Production production) {
		productionDao.save(production);
	}

	public void delete(Production production) {
		productionDao.delete(production);
	}

	public void update(Production production) {
		productionDao.update(production);
	}

	public Production findById(int id) {
		return productionDao.getById(id);
	}

	public Production findByUserId(int id) {
		return productionDao.getByUserId(id);
	}

	public List<Production> search(Search search) {
		return productionDao.query(search);
	}

}
