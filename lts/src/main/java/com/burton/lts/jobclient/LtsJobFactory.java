package com.burton.lts.jobclient;

import com.alibaba.fastjson.JSON;
import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.jobclient.JobClient;
import com.github.ltsopensource.jobclient.domain.Response;
import com.github.ltsopensource.remoting.annotation.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Job工厂类
 *
 * @author Tainy
 * @date 2018/6/20 15:42
 */
public class LtsJobFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(LtsJobFactory.class);

    private static class SingletonHolder {
        private static final LtsJobFactory INSTANCE = new LtsJobFactory();
    }

    private LtsJobFactory(){

    }

    /**
     * 生成工厂对象
     *
     * @return
     */
    public static final LtsJobFactory getInstance(){
        // 单例模式
        return SingletonHolder.INSTANCE;
    }

    /** taskId */
    private String  taskId;

    /** 请求参数 */
    private Map<String, String> params = new HashMap<>();

    /** 执行任务阶段组 */
    @NotNull
    private static String taskTrackerNodeGroup;

    /** 是否反馈 */
    private boolean needFeedback = false;

    /** 当任务队列中存在这个任务的时候，是否替换更新 */
    private boolean replaceOnExist = false;

    /** 重复执行表达式 */
    private String cronExpression;

    /** 定时触发时间点 */
    private Long triggerTime;

    /** 任务提交 */
    private static JobClient jobClient;

    /** 最大重试次数 */
    private int maxRetryTimes = 0;

    /** 提交节点组 */
    private String submitNodeGroup;

    /**  优先级 */
    private Integer priority = Integer.valueOf(100);

    /** 任务类型 */
    private String taskType;

    /** 事件类型 */
    private String eventType;

    /**
     * 创建Job
     *
     * @return
     */
    public Job createJob(){
        Map<String, String> map = this.params;
        String triggerTime = map.get("triggerTime");
        String cronExpression = map.get("cronExpression");
        if(StringUtils.isNotEmpty(triggerTime)){
            this.triggerTime = Long.valueOf(triggerTime);
        }
        if(StringUtils.isNotEmpty(cronExpression)){
            this.cronExpression = cronExpression;
        }
        StringBuilder taskId = new StringBuilder();
        taskId.append(this.taskType).append("_").append(this.eventType).append("_")
                .append(UUID.randomUUID().toString().replace("-",""));

        Job job = new Job();
        job.setTaskId(taskId.toString());
        job.setExtParams(this.params);
        job.setSubmitNodeGroup(this.submitNodeGroup);
        job.setTaskTrackerNodeGroup(this.taskTrackerNodeGroup);
        job.setNeedFeedback(this.needFeedback);
        job.setReplaceOnExist(this.replaceOnExist);
        job.setCronExpression(this.cronExpression);
        job.setTriggerTime(this.triggerTime);
        job.setMaxRetryTimes(this.maxRetryTimes);
        job.setPriority(this.priority);

        LOGGER.info("createJob is success and Job's content is {}", JSON.toJSONString(job));
        return job;
    }

    /**
     * job提交
     *
     * @return
     */
    public Map<Job, Response> jobsubmit(){
        if(StringUtils.isAnyEmpty(this.taskType, this.eventType)){
            throw new IllegalArgumentException("taskType or eventType is null");
        }

        Job job = this.createJob();
        Response response = jobClient.submitJob(job);
        LOGGER.info("method[jobsubmit] taskId={} , response={}",job.getTaskId(), JSON.toJSONString(response));

        Map<Job, Response> responseMap = new HashMap<>();
        responseMap.put(job, response);

        return responseMap;
    }

    /**
     * job取消
     *
     * @param taskId
     * @param taskTrackerNodeGroup
     * @return
     */
    public Map<Job, Response> jobCancel(String taskId, String taskTrackerNodeGroup){
        if(StringUtils.isAnyEmpty(this.taskId, this.taskTrackerNodeGroup)){
            throw new IllegalArgumentException("taskId or taskTrackerNodeGroup is null");
        }

        Response response = jobClient.cancelJob(taskId, taskTrackerNodeGroup);
        Job job = this.createJob();
        Map<Job, Response> responseMap = new HashMap<>();
        responseMap.put(job, response);

        return responseMap;

    }

    public String getTaskId() {
        return taskId;
    }

    public LtsJobFactory setTaskId(String taskId) {
        this.taskId = taskId;

        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public LtsJobFactory setParams(Map<String, String> params) {
        this.params = params;

        return this;
    }

    public LtsJobFactory setParam(String key, String value) {
        this.params.put(key, value);

        return this;
    }

    public String getTaskTrackerNodeGroup() {
        return taskTrackerNodeGroup;
    }

    public void setTaskTrackerNodeGroup(String taskTrackerNodeGroup) {
        LtsJobFactory.taskTrackerNodeGroup = taskTrackerNodeGroup;
    }

    public boolean isNeedFeedback() {
        return needFeedback;
    }

    public LtsJobFactory setNeedFeedback(boolean needFeedback) {
        this.needFeedback = needFeedback;

        return this;
    }

    public boolean isReplaceOnExist() {
        return replaceOnExist;
    }

    public LtsJobFactory setReplaceOnExist(boolean replaceOnExist) {
        this.replaceOnExist = replaceOnExist;

        return this;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public LtsJobFactory setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;

        return this;
    }

    public Long getTriggerTime() {
        return triggerTime;
    }

    public LtsJobFactory setTriggerTime(Long triggerTime) {
        this.triggerTime = triggerTime;

        return this;
    }

    public JobClient getJobClient() {
        return jobClient;
    }

    public void setJobClient(JobClient jobClient) {
        LtsJobFactory.jobClient = jobClient;
    }

    public int getMaxRetryTimes() {
        return maxRetryTimes;
    }

    public LtsJobFactory setMaxRetryTimes(int maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;

        return this;
    }

    public String getSubmitNodeGroup() {
        return submitNodeGroup;
    }

    public LtsJobFactory setSubmitNodeGroup(String submitNodeGroup) {
        this.submitNodeGroup = submitNodeGroup;

        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public LtsJobFactory setPriority(Integer priority) {
        this.priority = priority;

        return this;
    }

    public String getTaskType() {
        return taskType;
    }

    public LtsJobFactory setTaskType(String taskType) {
        this.taskType = taskType;

        return this;
    }

    public String getEventType() {
        return eventType;
    }

    public LtsJobFactory setEventType(String eventType) {
        this.eventType = eventType;

        return this;
    }
}
