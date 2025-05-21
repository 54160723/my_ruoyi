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
import com.ruoyi.system.domain.DevopsProject;
import com.ruoyi.system.service.IDevopsProjectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * DevOps项目Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/devops/project")
public class DevopsProjectController extends BaseController
{
    @Autowired
    private IDevopsProjectService devopsProjectService;

    /**
     * 查询DevOps项目列表
     */
    @PreAuthorize("@ss.hasPermi('system:devops:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(DevopsProject devopsProject)
    {
        startPage();
        List<DevopsProject> list = devopsProjectService.selectDevopsProjectList(devopsProject);
        return getDataTable(list);
    }

    /**
     * 导出DevOps项目列表
     */
    @PreAuthorize("@ss.hasPermi('system:devops:project:export')")
    @Log(title = "DevOps项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DevopsProject devopsProject)
    {
        List<DevopsProject> list = devopsProjectService.selectDevopsProjectList(devopsProject);
        ExcelUtil<DevopsProject> util = new ExcelUtil<DevopsProject>(DevopsProject.class);
        util.exportExcel(response, list, "DevOps项目数据");
    }

    /**
     * 获取DevOps项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:devops:project:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return success(devopsProjectService.selectDevopsProjectByProjectId(projectId));
    }

    /**
     * 新增DevOps项目
     */
    @PreAuthorize("@ss.hasPermi('system:devops:project:add')")
    @Log(title = "DevOps项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DevopsProject devopsProject)
    {
        return toAjax(devopsProjectService.insertDevopsProject(devopsProject));
    }

    /**
     * 修改DevOps项目
     */
    @PreAuthorize("@ss.hasPermi('system:devops:project:edit')")
    @Log(title = "DevOps项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DevopsProject devopsProject)
    {
        return toAjax(devopsProjectService.updateDevopsProject(devopsProject));
    }

    /**
     * 删除DevOps项目
     */
    @PreAuthorize("@ss.hasPermi('system:devops:project:remove')")
    @Log(title = "DevOps项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(devopsProjectService.deleteDevopsProjectByProjectIds(projectIds));
    }
} 