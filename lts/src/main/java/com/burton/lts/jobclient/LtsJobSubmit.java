package com.burton.lts.jobclient;

import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.core.json.JSON;
import com.github.ltsopensource.jobclient.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 *lts提交任务
 *
 * @author Tainy
 * @date 2018/6/20 15:53
 */
public class LtsJobSubmit{

    private static final Logger LOGGER = LoggerFactory.getLogger(LtsJobSubmit.class);

    /**
     * 任务提交
     * 提交任务时，默认为实时任务，如是定时任务或者周期性任务，
     * 需把时间(时间戳，毫秒级别)或者cronExpression表达式作为参数，放入params中，
     * 否则系统无法认定是定时任务或者周期性任务，一律按照实时任务执行
     * 时间戳：比如任务提交后24小时执行：new Date().getTime() + 1 * 24 * 60 * 60 * 1000; key 指定为"triggerTime"
     * cronExpression: 比如每隔5秒执行一次 ： "0/5 * * * * ?"; key 指定为"cronExpression"
     */
    public static void submitJob(Map<String, String> params){
        try {
            Map<Job, Response> jobResponseMap = LtsJobFactory.getInstance()
                    .setTaskType(LtsJobType.TASK_TYPE.WAIT_FOR_TASK.getDesc())
                    .setEventType(LtsJobType.EVENT_TYPE.TEST.getDesc())
                    .setParams(params)
                    .setMaxRetryTimes(3)
                    .jobsubmit();

            LOGGER.info("method[submitJob] result={}", JSON.toJSONString(jobResponseMap));
        } catch (Exception e) {
            LOGGER.info("method[submitJob] result:{}", e);
        }
    }

    /**
     * 任务取消
     *
     * 如需使用此功能，需持久化该参数
     * @param taskId
     * @param taskTrackernodeGroup
     *
     */
    public static void cancelJob(String taskId, String taskTrackernodeGroup){
        try{
            Map<Job, Response> jobResponseMap = LtsJobFactory.getInstance().jobCancel(taskId, taskTrackernodeGroup);
            LOGGER.info("method[cancelJob] result={}", JSON.toJSONString(jobResponseMap));
        }catch (Exception e){
            LOGGER.info("method[cancelJob] result:{}", e);
        }

    }
}
