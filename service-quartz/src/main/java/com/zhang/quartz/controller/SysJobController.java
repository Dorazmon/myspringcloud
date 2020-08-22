package com.zhang.quartz.controller;

import com.sinopec.common.core.controller.BaseController;
import com.sinopec.common.core.domain.R;
import com.sinopec.common.core.page.TableDataInfo;
import com.sinopec.common.exception.job.TaskException;
import com.sinopec.common.utils.poi.ExcelUtil;
import com.zhang.quartz.domain.SysJob;
import com.zhang.quartz.service.ISysJobService;
import com.zhang.quartz.utils.CronUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调度任务信息操作处理
 * 
 * @author sinopec
 */
@RestController
@RequestMapping("/quartz/job")
public class SysJobController extends BaseController
{
    @Autowired
    private ISysJobService jobService;
//    @Autowired
//    private RemoteUserService retailService;

    /**
     * 查询定时任务列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysJob sysJob)
    {
        startPage();
        List<SysJob> list = jobService.selectJobList(sysJob);
        return getDataTable(list);
    }

    /**
     * 导出定时任务列表
     */
    @GetMapping("/export")
    public R export(SysJob sysJob)
    {
        List<SysJob> list = jobService.selectJobList(sysJob);
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        return util.exportExcel(list, "定时任务");
    }

    /**
     * 获取定时任务详细信息
     */
    @GetMapping(value = "/{jobId}")
    public R getInfo(@PathVariable("jobId") Long jobId)
    {
        return R.data(jobService.selectJobById(jobId));
    }

    /**
     * 新增定时任务
     */
    @PostMapping
    public R add(@RequestBody SysJob sysJob) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(sysJob.getCronExpression()))
        {
            return R.error("cron表达式不正确");
        }
        sysJob.setCreateBy("测试");
        jobService.insertJob(sysJob);
        Map<String,Object> map = new HashMap<>();
        map.put("job",sysJob);
        return R.ok(map);
    }

    /**
     * 修改定时任务
     */
    @PutMapping
    public R edit(@RequestBody SysJob sysJob) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(sysJob.getCronExpression()))
        {
            return R.error("cron表达式不正确");
        }
        sysJob.setUpdateBy("测试");
        return toAjax(jobService.updateJob(sysJob));
    }

    /**
     * 定时任务状态修改
     */
    @PutMapping("/changeStatus")
    public R changeStatus(@RequestBody SysJob job) throws SchedulerException
    {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }

    /**
     * 定时任务立即执行一次
     */
    @PutMapping("/run")
    public R run(@RequestBody SysJob job) throws SchedulerException
    {
        jobService.run(job);
        return R.ok();
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping("/{jobIds}")
    public R remove(@PathVariable Long[] jobIds) throws SchedulerException, TaskException
    {
        jobService.deleteJobByIds(jobIds);
        return R.ok();
    }

//    @GetMapping("test")
//    public R list(){
//        SysUser sysUser = retailService.selectSysUserByUserId(1L);
//
//        return R.data(sysUser);
//    }
}
