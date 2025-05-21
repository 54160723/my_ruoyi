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
import com.ruoyi.system.domain.DevopsJenkins;
import com.ruoyi.system.service.IDevopsJenkinsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * Jenkins配置Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/devops/jenkins")
public class DevopsJenkinsController extends BaseController
{
    @Autowired
    private IDevopsJenkinsService devopsJenkinsService;

    /**
     * 查询Jenkins配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:devops:jenkins:list')")
    @GetMapping("/list")
    public TableDataInfo list(DevopsJenkins devopsJenkins)
    {
        startPage();
        List<DevopsJenkins> list = devopsJenkinsService.selectDevopsJenkinsList(devopsJenkins);
        return getDataTable(list);
    }

    /**
     * 导出Jenkins配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:devops:jenkins:export')")
    @Log(title = "Jenkins配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DevopsJenkins devopsJenkins)
    {
        List<DevopsJenkins> list = devopsJenkinsService.selectDevopsJenkinsList(devopsJenkins);
        ExcelUtil<DevopsJenkins> util = new ExcelUtil<DevopsJenkins>(DevopsJenkins.class);
        util.exportExcel(response, list, "Jenkins配置数据");
    }

    /**
     * 获取Jenkins配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:devops:jenkins:query')")
    @GetMapping(value = "/{jenkinsId}")
    public AjaxResult getInfo(@PathVariable("jenkinsId") Long jenkinsId)
    {
        return success(devopsJenkinsService.selectDevopsJenkinsByJenkinsId(jenkinsId));
    }

    /**
     * 新增Jenkins配置
     */
    @PreAuthorize("@ss.hasPermi('system:devops:jenkins:add')")
    @Log(title = "Jenkins配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DevopsJenkins devopsJenkins)
    {
        return toAjax(devopsJenkinsService.insertDevopsJenkins(devopsJenkins));
    }

    /**
     * 修改Jenkins配置
     */
    @PreAuthorize("@ss.hasPermi('system:devops:jenkins:edit')")
    @Log(title = "Jenkins配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DevopsJenkins devopsJenkins)
    {
        return toAjax(devopsJenkinsService.updateDevopsJenkins(devopsJenkins));
    }

    /**
     * 删除Jenkins配置
     */
    @PreAuthorize("@ss.hasPermi('system:devops:jenkins:remove')")
    @Log(title = "Jenkins配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jenkinsIds}")
    public AjaxResult remove(@PathVariable Long[] jenkinsIds)
    {
        return toAjax(devopsJenkinsService.deleteDevopsJenkinsByJenkinsIds(jenkinsIds));
    }
} 