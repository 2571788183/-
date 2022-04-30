package org.competition.dao.impl;

import org.apache.ibatis.annotations.Mapper;
import org.competition.dao.BaseMapper;
import org.competition.dao.WayDao;
import org.competition.model.PersistenceModel;
import org.competition.model.Signup;
import org.competition.model.User;
import org.competition.model.Way;
import org.competition.utils.ModelUtil;
import org.competition.utils.PageContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Huangchuxiong
 * @create 2021-05-09 13:28
 */
@Repository("wayDao")
public class WayDaoImpl implements WayDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Mapper
    public interface WayMapper extends BaseMapper<Way> {
    }

    @Autowired
    WayDaoImpl.WayMapper wayMapper;

    @Override
    public void insert(Way way) {
        try {
            Map<String, PersistenceModel> values = ModelUtil.values(Way.class, way);
            wayMapper.save("way", values, way);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        getSession().delete(getSession().load(Way.class, id));
    }

    @Override
    public List<Way> select() {
        List list = new ArrayList();
        String sql = "select * from t_way  where 1=1 ";
        try {
            list = getSession().createSQLQuery(sql)
                    .addEntity(Way.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
}
