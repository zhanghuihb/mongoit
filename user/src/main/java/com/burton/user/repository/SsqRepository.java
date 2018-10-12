package com.burton.user.repository;

import com.burton.common.domain.Ssq;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/10/11 12:37
 */
public interface SsqRepository extends JpaRepository<Ssq, String> {

    List<Ssq> findByAndDateBeforeOrderByDateDesc(String date, Pageable pageable);
}
