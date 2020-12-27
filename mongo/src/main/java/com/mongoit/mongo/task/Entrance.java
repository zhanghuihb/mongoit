package com.mongoit.mongo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Configuration
public class Entrance implements InitializingBean {

    @Autowired
    private SchedulerService schedulerService;

    @Override
    public void afterPropertiesSet(){

        log.info("定时任务开始...");
        schedulerService.execute();
    }
}
