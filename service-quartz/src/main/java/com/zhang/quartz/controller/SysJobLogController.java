package com.zhang.quartz.controller;

import com.sinopec.common.core.controller.BaseController;
import com.sinopec.common.core.domain.R;
import com.sinopec.common.core.page.TableDataInfo;
import com.sinopec.common.utils.poi.ExcelUtil;
import com.zhang.quartz.domain.SysJobLog;
import com.zhang.quartz.service.ISysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度日志操作处理
 * 
 * @author sinopec
 */
@RestController
@RequestMapping("/monitor/jobLog")
public class SysJobLogController extends BaseController
{
    @Autowired
    private ISysJobLogService jobLogService;

    /**
     * 查询定时任务调度日志列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysJobLog sysJobLog)
    {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
        return getDataTable(list);
    }

    /**
     * 导出定时任务调度日志列表
     */
    @GetMapping("/export")
    public R export(SysJobLog sysJobLog)
    {
        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
        ExcelUtil<SysJobLog> util = new ExcelUtil<SysJobLog>(SysJobLog.class);
        return util.exportExcel(list, "调度日志");
    }
    
    /**
     * 根据调度编号获取详细信息
     */
    @GetMapping(value = "/{configId}")
    public R getInfo(@PathVariable Long jobLogId)
    {
        return R.data(jobLogService.selectJobLogById(jobLogId));
    }


    /**
     * 删除定时任务调度日志
     */

    @DeleteMapping("/{jobLogIds}")
    public R remove(@PathVariable Long[] jobLogIds)
    {
        return toAjax(jobLogService.deleteJobLogByIds(jobLogIds));
    }

    /**
     * 清空定时任务调度日志
     */
    @DeleteMapping("/clean")
    public R clean()
    {
        jobLogService.cleanJobLog();
        return R.ok();
    }
}
