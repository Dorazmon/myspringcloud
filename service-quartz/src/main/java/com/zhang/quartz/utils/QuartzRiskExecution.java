package com.zhang.quartz.utils;

import com.sinopec.common.constant.ScheduleConstants;
import com.sinopec.common.constant.ServiceNameConstants;
import com.sinopec.common.utils.bean.BeanUtils;
import com.sinopec.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class QuartzRiskExecution implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

            log.debug("{{----风控中心任务调度----}}");
    }
}
