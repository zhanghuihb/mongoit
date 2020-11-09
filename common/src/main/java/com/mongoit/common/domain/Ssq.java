package com.mongoit.common.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/10/11 12:31
 */
@Data
@Entity
@Table(name = "ssq_reward_record")
public class Ssq implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键'")
    private String id;

    @Column(name = "date", columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 COMMENT '日期'")
    private String  date;

    @Column(name = "red1", columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 COMMENT '红1'")
    private String red1;

    @Column(name = "red2", columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 COMMENT '红2'")
    private String red2;

    @Column(name = "red3", columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 COMMENT '红3'")
    private String red3;

    @Column(name = "red4", columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 COMMENT '红4'")
    private String red4;

    @Column(name = "red5", columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 COMMENT '红5'")
    private String red5;

    @Column(name = "red6", columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 COMMENT '红6'")
    private String red6;

    @Column(name = "blue1", columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 COMMENT '蓝1'")
    private String blue1;
}
