package com.burton.common.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Tainy
 * @date 2018/6/22 13:36
 */
@Entity
@Table(name = "xcx_app")
@Data
public class App implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键'")
    private Integer id;

    @Column(name = "app_id", unique = true, columnDefinition = "INT(11) NOT NULL COMMENT '我们自己定义的小程序应用ID'")
    private Integer appId;

    @Column(name = "app_name", columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '小程序应用名称'")
    private String appName;

    @Column(name = "app_code", columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '微信小程序应用ID'")
    private String appCode;

    @Column(name = "app_secret", columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '微信小程序应用密钥'")
    private String appSecret;

    @Column(name = "switched", columnDefinition = "INT(4) NOT NULL DEFAULT 1 COMMENT '定时刷寻开关(1：需要刷新 0：不需要刷新)'")
    private Integer switched;

    @Column(name = "access_token", columnDefinition = "VARCHAR(1024) NOT NULL COMMENT 'access_token有效期两小时'")
    private Integer accessToken;

    @Column(name = "valid_time", columnDefinition = "INT(11) NOT NULL DEFAULT 7200 COMMENT '有效时间，单位：秒，默认2个小时'")
    private Integer validTime;

    @Column(name = "create_user", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 NOT NULL DEFAULT '0' COMMENT '创建者'")
    private String createUser;

    @Column(name = "create_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;

    @Column(name = "update_user", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 NOT NULL DEFAULT '0' COMMENT '更新者'")
    private String updateUser;

    @Column(name = "update_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updateTime;

    @Column(name = "del_flag", columnDefinition = "int(4) NOT NULL DEFAULT '1' COMMENT '删除标志'")
    private Integer delFlag;

}
