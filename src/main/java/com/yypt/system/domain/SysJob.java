package com.yypt.system.domain;

/**
 * @创建人 zhk
 * @创建时间 2019-05-20
 * @描述
 */
public class SysJob {
    private String schedName;
    //TRIGGER 名称
    private String triggerName;

    //TRIGGER 组
    private String triggerGroup;

    // JOB 名称
    private String jobName;

    //JOB组
    private String jobGroup;

    //JOB 类名
    private String jobClassName;

    // JOB描述
    private String description;

    private int status;


    //job参数
    private byte[] param;


    private String oldJobName;


    private String oldJobGroup;

    private Long nextFireTime;

    private  Long prevFireTime;


    private Long startTime;

    private Long endTime;

    private String cronExpression;

    private String paramstr;


    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public String getOldJobName() {
        return oldJobName;
    }

    public void setOldJobName(String oldJobName) {
        this.oldJobName = oldJobName;
    }

    public String getOldJobGroup() {
        return oldJobGroup;
    }

    public void setOldJobGroup(String oldJobGroup) {
        this.oldJobGroup = oldJobGroup;
    }

    public Long getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Long nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public Long getPrevFireTime() {
        return prevFireTime;
    }

    public void setPrevFireTime(Long prevFireTime) {
        this.prevFireTime = prevFireTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public byte[] getParam() {
        return this.param;
    }

    public void setParam(byte[] param) {
        this.param = param;
    }

    public String getParamstr() {
        return paramstr;
    }

    public void setParamstr(String paramstr) {
        this.paramstr = paramstr;
    }
}
