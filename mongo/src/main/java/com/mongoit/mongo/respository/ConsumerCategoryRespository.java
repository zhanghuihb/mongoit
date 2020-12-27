package com.mongoit.mongo.respository;

import com.mongoit.common.domain.ConsumerCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/14 15:34
 */
public interface ConsumerCategoryRespository extends JpaRepository<ConsumerCategory, Integer> {

    List<ConsumerCategory> findAllByDelFlagAndStatus(Integer delFlag, Integer status);

}
