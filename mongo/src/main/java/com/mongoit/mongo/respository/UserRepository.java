package com.mongoit.mongo.respository;

import com.mongoit.common.domain.XcxUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/15 16:38
 */
public interface UserRepository extends JpaRepository<XcxUser, Integer> {

    List<XcxUser> findXcxUserByUnionId(String unionId);

}
