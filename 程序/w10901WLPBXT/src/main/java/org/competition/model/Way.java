package org.competition.model;

import javax.persistence.*;

/**
 * @author Huangchuxiong
 * @create 2021-05-09 13:17
 */
@Entity
@Table(name = "t_way")
public class Way {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) comment '主键'")
    private int id;

    @Column(name = "wayName", columnDefinition = "varchar(255) comment '赛道名'")
    private String wayName;

    @Column(name = "wayContent", columnDefinition = "varchar(255) comment '赛道介绍'")
    private String wayContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getWayContent() {
        return wayContent;
    }

    public void setWayContent(String wayContent) {
        this.wayContent = wayContent;
    }
}
