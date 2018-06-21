package com.burton.lanbitou.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Tainy
 * @date 2018/6/15 15:50
 */
@Data
@Entity
@Table(name = "console_xcx_user")
@DynamicInsert
@DynamicUpdate
public class XcxUser implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键'")
    private Integer id;

    @Column(name = "user_id", columnDefinition = "INT(11) NOT NULL DEFAULT 0 COMMENT '用户ID'")
    private Integer userId;

    @Column(name = "union_id", unique = true, columnDefinition = "VARCHAR(64) NOT NULL COMMENT 'UNIONID'")
    private String unionId;

    @Column(name = "nick_name", columnDefinition = "VARCHAR(64) COMMENT '昵称'")
    private String nickName;

    @Column(name = "gender", columnDefinition = "int(4) NOT NULL DEFAULT 1 COMMENT '性别'")
    private Integer gender;

    @Column(name = "avatarUrl", columnDefinition = "VARCHAR(128) COMMENT '头像'")
    private String avatarUrl;

    @Column(name = "city", columnDefinition = "VARCHAR(64) COMMENT '城市'")
    private String city;

    @Column(name = "province", columnDefinition = "VARCHAR(64) COMMENT '省份'")
    private String province;

    @Column(name = "country", columnDefinition = "VARCHAR(64) COMMENT '区县'")
    private String country;

    @Column(name = "create_user", columnDefinition = "varchar(32) NOT NULL DEFAULT '0' COMMENT '创建者'")
    private String createUser;

    @Column(name = "create_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;

    @Column(name = "update_user", columnDefinition = "varchar(32) NOT NULL DEFAULT '0' COMMENT '更新者'")
    private String updateUser;

    @Column(name = "update_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updateTime;

    @Column(name = "del_flag", columnDefinition = "int(4) NOT NULL DEFAULT '1' COMMENT '删除标志'")
    private Integer delFlag;
}
