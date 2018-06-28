package com.burton.common.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Tainy
 * @date 2018/6/27 15:57
 */
@Data
@Entity
@Table(name="console_opinion")
@DynamicInsert
@DynamicUpdate
public class Opinion implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键'")
    private Integer id;

    @Column(name = "content", columnDefinition = "VARCHAR(1024) CHARACTER SET utf8mb4 NOT NULL COMMENT '意见及建议内容'")
    private String content;

    @Column(name = "user_id", columnDefinition = "int(11) NOT NULL DEFAULT 0 COMMENT '用户ID'")
    private Integer userId;

    @Column(name = "app_id", columnDefinition = "int(11) NOT NULL COMMENT '应用ID'")
    private Integer appId;

    @Column(name = "reading", columnDefinition = "int(4) NOT NULL DEFAULT 2 COMMENT '已读未读(1：已读 2：未读)'")
    private Integer reading;

    @Column(name = "create_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;

    @Column(name = "update_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updateTime;

    @Column(name = "del_flag", columnDefinition = "int(4) NOT NULL DEFAULT '1' COMMENT '删除标志'")
    private Integer delFlag;
}
