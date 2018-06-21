package com.burton.lanbitou.respository;

import com.burton.lanbitou.domain.ConsumerInfo;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/12 19:43
 */
public interface ConsumerInfoRepository extends JpaRepository<ConsumerInfo, Integer>{

    PageImpl<ConsumerInfo> findByDelFlagAndUserIdOrderByConsumerTimeDesc(Integer delFlag, Integer userId, Pageable pageable);

    @Query(value = "select c.id, c.user_id,c.city, c.province,c.code,c.code_name,c.parent_code,c.parent_code_name,c.consumer,c.consumer_time,c.description,c.status,c.create_user,c.create_time,c.update_time,c.update_user,c.del_flag, sum(c.amount) as amount, c.digest from console_consumer_info c where c.user_id = ?1 and c.del_flag = 1 group by c.digest", nativeQuery = true)
    List<ConsumerInfo> getAccountInfo(Integer userId);
}
