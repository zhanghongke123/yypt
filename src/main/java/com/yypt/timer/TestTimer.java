package com.yypt.timer;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @创建人 zhk
 * @创建时间 2020-05-26
 * @描述
 */
@Slf4j
@DisallowConcurrentExecution
public class TestTimer extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String param = jobExecutionContext.getMergedJobDataMap().getString("param");
        log.error("================================"+param);
    }
}
