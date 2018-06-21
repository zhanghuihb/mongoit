package com.burton.lts.config;

import com.alibaba.fastjson.JSON;
import com.burton.lts.jobclient.LtsJobFactory;
import com.github.ltsopensource.jobclient.JobClient;
import com.github.ltsopensource.spring.JobClientFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Tainy
 * @date 2018/6/20 17:46
 */
@Configuration
public class LtsJobClientConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(LtsJobClientConfig.class);

    @Bean
    public LtsJobFactory getLtsJobFactory() throws Exception {
        LtsJobFactory ltsJobFactory = LtsJobFactory.getInstance();
        JobClient jobClient = getJobClientFactoryBean().getObject();
        jobClient.start();
        ltsJobFactory.setJobClient(jobClient);
        ltsJobFactory.setTaskTrackerNodeGroup("lts_taskTracker");

        return ltsJobFactory;
    }

    @Bean
    public JobClientFactoryBean getJobClientFactoryBean(){
        JobClientFactoryBean jobClientFactoryBean = new JobClientFactoryBean();
        jobClientFactoryBean.setClusterName("burton_lts_cluster");
        jobClientFactoryBean.setRegistryAddress("zookeeper://127.0.0.1:2181");
        jobClientFactoryBean.setNodeGroup("lts_jobClient");
        Properties properties = new Properties();
        properties.setProperty("job.fail.store", "mapdb");
        jobClientFactoryBean.setConfigs(properties);

        LOGGER.info("jobClientFactoryBean = {}", JSON.toJSONString(jobClientFactoryBean));
        return jobClientFactoryBean;
    }
}
