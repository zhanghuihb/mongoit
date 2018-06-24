package com.burton.user.repository;

import com.burton.common.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/6/23
 */
public interface AppRepository extends JpaRepository<App, Integer> {

    List<App> findAppsByDelFlagAndSwitched(Integer delFlag, Integer switched);

}
