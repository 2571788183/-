package org.competition.service.impl;

import java.util.List;
import javax.annotation.Resource
;import org.springframework.stereotype.Service;
import org.competition.dao.SignupDao;
import org.competition.model.Signup;
import org.competition.model.Search;
import org.competition.service.SignupService;

@Service("signupService")
public class SignupServiceImpl extends BaseServiceImpl implements SignupService {
	@Resource
	SignupDao signupDao ;
	public void add(Signup signup) {
		signupDao.save(signup);
	}

	public void delete(Signup signup) {
		signupDao.delete(signup);
	}

	public void update(Signup signup) {
		signupDao.update(signup);
	}

	public Signup findById(int id) {
		return signupDao.getById(id);
	}

	public Signup findByUserId(int id) {
		return signupDao.getByUserId(id);
	}

	public List<Signup> search(Search search) {
		return signupDao.query(search);
	}

}
