package com.burton.lanbitou.respository;

import com.burton.common.base.BaseResultStatics;
import com.burton.common.domain.ConsumerInfo;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/12 19:43
 */
public interface ConsumerInfoRepository extends JpaRepository<ConsumerInfo, Integer>{

    PageImpl<ConsumerInfo> findByDelFlagAndUserIdOrderByConsumerTimeDesc(Integer delFlag, Integer userId, Pageable pageable);

    @Query(value = "select new" +
            " com.burton.common.base.BaseResultStatics(c.digest, sum(c.amount)) " +
            " from ConsumerInfo c " +
            " where c.userId = ?1 " +
            " and c.delFlag = 1 " +
            " group by c.digest")
    List<BaseResultStatics> getAccountInfo(Integer userId);

    @Query(value = "select new"
            + " com.burton.common.base.BaseResultStatics(c.digest, sum(c.amount))"
            + " from ConsumerInfo c "
            + " where c.userId = ?1 "
            + " and c.delFlag = 1"
            + " and c.consumerTime between ?2 and ?3 "
            + " group by c.digest")
    List<BaseResultStatics> staticsResultByMonth(Integer userId, LocalDateTime startTime, LocalDateTime endTime);

    List<ConsumerInfo> findByDelFlagAndUserIdAndDigestAndConsumerTimeBetweenOrderByAmountDesc(Integer delFlag, Integer userId, Integer digest, LocalDateTime startTime, LocalDateTime endTime);
}
