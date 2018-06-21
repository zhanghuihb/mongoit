package com.burton.lts.jobclient;

/**
 * 任务类型
 *
 * @author Tainy
 * @date 2018/6/20 16:38
 */
public class LtsJobType {

    /** 任务类型 */
    public enum TASK_TYPE{
        RUN_NOW_TASK(1, "run_now_task"),// 立即执行任务
        WAIT_FOR_TASK(2, "wait_for_task"),// 定时执行任务
        CRON_EXPRESSION_TASK(3, "cron_expression_task");// 周期性任务

        private Integer code;

        private String desc;

        TASK_TYPE(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /** 事件类型 */
    public enum EVENT_TYPE{

        TEST("test");

        private String desc;

        EVENT_TYPE(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /** 任务优先级 */
    public enum PRIORITY {
        HIGHEST(5,"最高"),
        HIGH(7,"较高"),
        MIDDLE(9,"中"),
        LOW(11,"较低"),
        LOWEST(13,"最低"),
        DEFAULT(100,"默认");

        private final Integer level;

        private final String desc;

        PRIORITY(Integer level, String desc) {
            this.level = level;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public Integer getLevel() {
            return level;
        }
    }
}
