package com.burton.lts.config;

import com.alibaba.fastjson.JSON;
import com.burton.lts.tasktracker.TaskTrackerJobRunner;
import com.github.ltsopensource.spring.TaskTrackerAnnotationFactoryBean;
import com.github.ltsopensource.tasktracker.TaskTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Tainy
 * @date 2018/6/20 19:44
 */
@Configuration
public class LtsTaskTrackerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(LtsTaskTrackerConfig.class);

    @Bean
    public TaskTrackerAnnotationFactoryBean getTaskTrackerAnnotationFactoryBean(){
        TaskTrackerAnnotationFactoryBean taskTrackerAnnotationFactoryBean = new TaskTrackerAnnotationFactoryBean();
        taskTrackerAnnotationFactoryBean.setJobRunnerClass(TaskTrackerJobRunner.class);
        taskTrackerAnnotationFactoryBean.setClusterName("burton_lts_cluster");
        taskTrackerAnnotationFactoryBean.setRegistryAddress("zookeeper://127.0.0.1:2181");
        taskTrackerAnnotationFactoryBean.setNodeGroup("lts_taskTracker");
        taskTrackerAnnotationFactoryBean.setWorkThreads(64);
        Properties properties = new Properties();
        properties.setProperty("job.fail.store", "mapdb");
        taskTrackerAnnotationFactoryBean.setConfigs(properties);

        LOGGER.info("taskTrackerAnnotationFactoryBean={}", JSON.toJSONString(taskTrackerAnnotationFactoryBean));

        return taskTrackerAnnotationFactoryBean;
    }

    @Bean
    public TaskTracker getTaskTracker() throws Exception {
        TaskTrackerAnnotationFactoryBean taskTrackerAnnotationFactoryBean = this.getTaskTrackerAnnotationFactoryBean();
        TaskTracker taskTracker = taskTrackerAnnotationFactoryBean.getObject();
        taskTracker.start();

        return taskTracker;
    }
}
