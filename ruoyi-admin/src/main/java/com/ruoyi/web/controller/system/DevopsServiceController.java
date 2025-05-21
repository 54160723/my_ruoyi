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
import com.ruoyi.system.domain.DevopsService;
import com.ruoyi.system.service.IDevopsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * DevOps服务Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/devops/service")
public class DevopsServiceController extends BaseController
{
    @Autowired
    private IDevopsService devopsServiceService;

    /**
     * 查询DevOps服务列表
     */
    @PreAuthorize("@ss.hasPermi('system:devops:service:list')")
    @GetMapping("/list")
    public TableDataInfo list(DevopsService devopsService)
    {
        startPage();
        List<DevopsService> list = devopsServiceService.selectDevopsServiceList(devopsService);
        return getDataTable(list);
    }

    /**
     * 导出DevOps服务列表
     */
    @PreAuthorize("@ss.hasPermi('system:devops:service:export')")
    @Log(title = "DevOps服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DevopsService devopsService)
    {
        List<DevopsService> list = devopsServiceService.selectDevopsServiceList(devopsService);
        ExcelUtil<DevopsService> util = new ExcelUtil<DevopsService>(DevopsService.class);
        util.exportExcel(response, list, "DevOps服务数据");
    }

    /**
     * 获取DevOps服务详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:devops:service:query')")
    @GetMapping(value = "/{serviceId}")
    public AjaxResult getInfo(@PathVariable("serviceId") Long serviceId)
    {
        return success(devopsServiceService.selectDevopsServiceByServiceId(serviceId));
    }

    /**
     * 新增DevOps服务
     */
    @PreAuthorize("@ss.hasPermi('system:devops:service:add')")
    @Log(title = "DevOps服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DevopsService devopsService)
    {
        return toAjax(devopsServiceService.insertDevopsService(devopsService));
    }

    /**
     * 修改DevOps服务
     */
    @PreAuthorize("@ss.hasPermi('system:devops:service:edit')")
    @Log(title = "DevOps服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DevopsService devopsService)
    {
        return toAjax(devopsServiceService.updateDevopsService(devopsService));
    }

    /**
     * 删除DevOps服务
     */
    @PreAuthorize("@ss.hasPermi('system:devops:service:remove')")
    @Log(title = "DevOps服务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{serviceIds}")
    public AjaxResult remove(@PathVariable Long[] serviceIds)
    {
        return toAjax(devopsServiceService.deleteDevopsServiceByServiceIds(serviceIds));
    }
} 