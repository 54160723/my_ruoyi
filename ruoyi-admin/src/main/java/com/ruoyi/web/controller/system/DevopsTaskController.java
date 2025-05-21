package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.DevopsTask;
import com.ruoyi.system.service.IDevopsTaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务配置Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/devops/task")
public class DevopsTaskController extends BaseController
{
    @Autowired
    private IDevopsTaskService devopsTaskService;

    /**
     * 查询任务配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:devops:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(DevopsTask devopsTask)
    {
        startPage();
        List<DevopsTask> list = devopsTaskService.selectDevopsTaskList(devopsTask);
        return getDataTable(list);
    }

    /**
     * 导出任务配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:devops:task:export')")
    @Log(title = "任务配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DevopsTask devopsTask)
    {
        List<DevopsTask> list = devopsTaskService.selectDevopsTaskList(devopsTask);
        ExcelUtil<DevopsTask> util = new ExcelUtil<DevopsTask>(DevopsTask.class);
        util.exportExcel(response, list, "任务配置数据");
    }

    /**
     * 获取任务配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:devops:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(devopsTaskService.selectDevopsTaskByTaskId(taskId));
    }

    /**
     * 新增任务配置
     */
    @PreAuthorize("@ss.hasPermi('system:devops:task:add')")
    @Log(title = "任务配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DevopsTask devopsTask)
    {
        return toAjax(devopsTaskService.insertDevopsTask(devopsTask));
    }

    /**
     * 修改任务配置
     */
    @PreAuthorize("@ss.hasPermi('system:devops:task:edit')")
    @Log(title = "任务配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DevopsTask devopsTask)
    {
        return toAjax(devopsTaskService.updateDevopsTask(devopsTask));
    }

    /**
     * 删除任务配置
     */
    @PreAuthorize("@ss.hasPermi('system:devops:task:remove')")
    @Log(title = "任务配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(devopsTaskService.deleteDevopsTaskByTaskIds(taskIds));
    }
} 