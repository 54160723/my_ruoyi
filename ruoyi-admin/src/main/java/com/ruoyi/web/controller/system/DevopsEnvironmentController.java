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
import com.ruoyi.system.domain.DevopsEnvironment;
import com.ruoyi.system.service.IDevopsEnvironmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 环境管理Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/devops/environment")
public class DevopsEnvironmentController extends BaseController
{
    @Autowired
    private IDevopsEnvironmentService devopsEnvironmentService;

    /**
     * 查询环境管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:devops:environment:list')")
    @GetMapping("/list")
    public TableDataInfo list(DevopsEnvironment devopsEnvironment)
    {
        startPage();
        List<DevopsEnvironment> list = devopsEnvironmentService.selectDevopsEnvironmentList(devopsEnvironment);
        return getDataTable(list);
    }

    /**
     * 导出环境管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:devops:environment:export')")
    @Log(title = "环境管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DevopsEnvironment devopsEnvironment)
    {
        List<DevopsEnvironment> list = devopsEnvironmentService.selectDevopsEnvironmentList(devopsEnvironment);
        ExcelUtil<DevopsEnvironment> util = new ExcelUtil<DevopsEnvironment>(DevopsEnvironment.class);
        util.exportExcel(response, list, "环境管理数据");
    }

    /**
     * 获取环境管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:devops:environment:query')")
    @GetMapping(value = "/{envId}")
    public AjaxResult getInfo(@PathVariable("envId") Long envId)
    {
        return success(devopsEnvironmentService.selectDevopsEnvironmentByEnvId(envId));
    }

    /**
     * 新增环境管理
     */
    @PreAuthorize("@ss.hasPermi('system:devops:environment:add')")
    @Log(title = "环境管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DevopsEnvironment devopsEnvironment)
    {
        return toAjax(devopsEnvironmentService.insertDevopsEnvironment(devopsEnvironment));
    }

    /**
     * 修改环境管理
     */
    @PreAuthorize("@ss.hasPermi('system:devops:environment:edit')")
    @Log(title = "环境管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DevopsEnvironment devopsEnvironment)
    {
        return toAjax(devopsEnvironmentService.updateDevopsEnvironment(devopsEnvironment));
    }

    /**
     * 删除环境管理
     */
    @PreAuthorize("@ss.hasPermi('system:devops:environment:remove')")
    @Log(title = "环境管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{envIds}")
    public AjaxResult remove(@PathVariable Long[] envIds)
    {
        return toAjax(devopsEnvironmentService.deleteDevopsEnvironmentByEnvIds(envIds));
    }
} 