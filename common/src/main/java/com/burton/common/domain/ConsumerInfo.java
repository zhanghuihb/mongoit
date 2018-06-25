package com.burton.common.domain;

import com.burton.common.base.BaseResultStatics;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Tainy
 * @date 2018/6/12 19:31
 */
@Data
@Entity
@Table(name = "console_consumer_info")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerInfo implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "code")
    private String code;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "parent_code")
    private String parentCode;

    @Column(name = "parent_code_name")
    private String parentCodeName;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "digest")
    private Integer digest;

    @Column(name = "consumer")
    private String consumer;

    @Column(name = "consumer_time")
    private LocalDateTime consumerTime;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "del_flag")
    private Integer delFlag;

    @Transient
    private BaseResultStatics baseResultStatics;

    public ConsumerInfo(BaseResultStatics baseResultStatics) {
        this.baseResultStatics = baseResultStatics;
    }
}
