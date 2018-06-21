package com.burton.lts.config;

import com.alibaba.fastjson.JSON;
import com.github.ltsopensource.jobtracker.JobTracker;
import com.github.ltsopensource.spring.JobTrackerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Tainy
 * @date 2018/6/20 19:34
 */
@Configuration
public class LtsJobTrackerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(LtsJobTrackerConfig.class);

    @Bean
    public JobTrackerFactoryBean getJobTrackerFactoryBean(){
        JobTrackerFactoryBean jobTrackerFactoryBean = new JobTrackerFactoryBean();
        jobTrackerFactoryBean.setClusterName("burton_lts_cluster");
        jobTrackerFactoryBean.setRegistryAddress("zookeeper://127.0.0.1:2181");
        jobTrackerFactoryBean.setListenPort(30005);
        Properties properties = new Properties();
        properties.setProperty("job.logger", "mysql");
        properties.setProperty("job.queue", "mysql");
        properties.setProperty("jdbc.url", "jdbc:mysql://127.0.0.1:3306/hui_project");
        properties.setProperty("jdbc.username", "root");
        properties.setProperty("jdbc.password", "root");
        jobTrackerFactoryBean.setConfigs(properties);

        LOGGER.info("jobTrackerFactoryBean={}", JSON.toJSONString(jobTrackerFactoryBean));
        return jobTrackerFactoryBean;
    }

    @Bean
    public JobTracker getJobTracker() throws Exception {
        JobTrackerFactoryBean jobTrackerFactoryBean = this.getJobTrackerFactoryBean();
        JobTracker jobTracker = jobTrackerFactoryBean.getObject();
        jobTracker.start();

        return jobTracker;
    }
}
