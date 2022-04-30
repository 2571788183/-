package org.competition.service;

import org.competition.model.Way;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Huangchuxiong
 * @create 2021-05-09 13:32
 */
public interface WayService {
    public void insert(Way way);
    public void delete(int id);
    public List<Way> search();
}
