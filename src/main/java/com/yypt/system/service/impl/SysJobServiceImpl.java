package com.yypt.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yypt.system.dao.SysJobMapper;
import com.yypt.system.domain.SysJob;
import com.yypt.system.service.SysJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.Trigger.TriggerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-05-15
 * @描述
 */
@Service
@Slf4j
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

    @Autowired
    SysJobMapper sysJobMapper;

    @Autowired
    private Scheduler scheduler;

    @Override
    public List<SysJob> getAllJob() throws Exception{

        List<SysJob> res = sysJobMapper.getAllJob(null);
        res.forEach(item ->{
            String triggerName = item.getTriggerName();
            String triggerGroup = item.getTriggerGroup();
            int status = this.getStatus(triggerName,triggerGroup);
            item.setStatus(status);
            JobDataMap jobDataMap = (JobDataMap)this.toObject(item.getParam());
            String val = jobDataMap.getString("param");
            item.setParamstr(val == null?"": val);
        });
        return res;
    }

    @Override
    public void resume(SysJob sysJob) throws Exception {
        JobKey key = new JobKey(sysJob.getJobName(),sysJob.getJobGroup());
        scheduler.resumeJob(key);
    }

    @Override
    public void add(SysJob sysJob) throws Exception {
           String jobName = sysJob.getJobName();
           String jobGroup = "GROUP_"+jobName;
           String  description = sysJob.getDescription();
            JobKey key = new JobKey(jobName,jobGroup);
            scheduler.deleteJob(key);
            Class cls = Class.forName(sysJob.getJobClassName());
            cls.newInstance();
            //构建job信息
            JobDetail job = JobBuilder.newJob(cls).withIdentity(jobName, jobName)
                    .withDescription(description).build();
            job.getJobDataMap().put("param", sysJob.getParamstr());

            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("TRIGGER_"+jobName, jobGroup)
                    .startNow().withSchedule(cronScheduleBuilder).build();
            //添加触发器监听
            //TriggerListener listener = new MonitorTriggerListener();
            //scheduler.getListenerManager().addTriggerListener(listener);

            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);

    }

    @Override
    public void remove(SysJob sysJob) throws Exception {
            String triggerName = sysJob.getTriggerName();
            String triggerGroup = sysJob.getTriggerGroup();
            String jobName = sysJob.getJobName();
            String jobGroup = sysJob.getJobGroup();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,
                    triggerGroup);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
    }

    @Override
    public void pause(SysJob sysJob) throws Exception {
        JobKey key = new JobKey(sysJob.getJobName(),sysJob.getJobGroup());
        scheduler.pauseJob(key);
    }

    @Override
    public void edit(SysJob sysJob) throws Exception {
        String jobName = sysJob.getJobName();
        String jobGroup = sysJob.getJobGroup();
        String oldJobName = sysJob.getOldJobName();
        String oldJobGroup = sysJob.getOldJobGroup();
        String  description = sysJob.getDescription();
        JobKey key = new JobKey(oldJobName,oldJobGroup);
        scheduler.deleteJob(key);
        Class cls = Class.forName(sysJob.getJobClassName());
        cls.newInstance();
        //构建job信息
        JobDetail job = JobBuilder.newJob(cls).withIdentity(jobName, jobName)
                .withDescription(description).build();
        job.getJobDataMap().put("param", sysJob.getParamstr());

        // 触发时间点
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob.getCronExpression());
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("TRIGGER"+jobName, jobGroup)
                .startNow().withSchedule(cronScheduleBuilder).build();
        //添加触发器监听
        //TriggerListener listener = new MonitorTriggerListener();
        //scheduler.getListenerManager().addTriggerListener(listener);

        //交由Scheduler安排触发
        scheduler.scheduleJob(job, trigger);
    }


    public int getStatus(String triggeName,String triggeGroup) {
        /*
               STATE_BLOCKED 4 阻塞
               STATE_COMPLETE 2 完成
               STATE_ERROR 3 错误
              STATE_NONE -1 不存在
             STATE_NORMAL 0 正常
            STATE_PAUSED 1 暂停
         */
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggeName, triggeGroup);
            TriggerState tristate  = scheduler.getTriggerState(triggerKey);
            if(tristate == TriggerState.NONE){
                return -1;
            }else if(tristate == TriggerState.COMPLETE){
                return 2;
            }else if(tristate == TriggerState.BLOCKED){
                return 4;
            }else if(tristate == TriggerState.ERROR){
                return 3;
            }else if(tristate == TriggerState.NORMAL){
                return 0;
            }else if(tristate == TriggerState.PAUSED){
                return 1;
            } else{
                return -999;
            }
        }catch (Exception e){
            log.error("获取定时任务状态出错:{}",e);
        }
        return -999;
    }


    /**
     * 数组转对象
     * @param bytes
     * @return
     */
    public Object toObject (byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }




}
