package org.competition.service.impl;

import java.util.List;
import javax.annotation.Resource
;import org.springframework.stereotype.Service;
import org.competition.dao.ShenheDao;
import org.competition.model.Shenhe;
import org.competition.model.Search;
import org.competition.service.ShenheService;

@Service("shenheService")
public class ShenheServiceImpl extends BaseServiceImpl implements ShenheService {
	@Resource
	ShenheDao shenheDao ;
	public void add(Shenhe shenhe) {
		shenheDao.save(shenhe);
	}

	public void delete(Shenhe shenhe) {
		shenheDao.delete(shenhe);
	}

	public void update(Shenhe shenhe) {
		shenheDao.update(shenhe);
	}

	public Shenhe findById(int id) {
		return shenheDao.getById(id);
	}

	public Shenhe findByUserId(int id) {
		return shenheDao.getByUserId(id);
	}

	public List<Shenhe> search(Search search) {
		return shenheDao.query(search);
	}

}
