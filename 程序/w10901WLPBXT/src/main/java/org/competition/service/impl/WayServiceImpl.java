package org.competition.service.impl;

import org.competition.dao.WayDao;
import org.competition.model.Way;
import org.competition.service.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Huangchuxiong
 * @create 2021-05-09 13:33
 */
@Service
public class WayServiceImpl implements WayService {
    @Autowired
    WayDao wayDao;

    @Override
    public void insert(Way way) {
        wayDao.insert(way);
    }

    @Override
    public List<Way> search() {
        return wayDao.select();
    }

    @Override
    public void delete(int id) {
        wayDao.delete(id);
    }
}
