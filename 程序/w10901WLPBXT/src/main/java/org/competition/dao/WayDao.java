package org.competition.dao;

import org.competition.model.Way;

import java.util.List;

/**
 * @author Huangchuxiong
 * @create 2021-05-09 13:20
 */
public interface WayDao {
    public void insert(Way way);
    public void delete(int id);
    public List<Way> select();
}
