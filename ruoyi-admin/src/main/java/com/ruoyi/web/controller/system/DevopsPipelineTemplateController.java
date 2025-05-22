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
import com.ruoyi.system.domain.DevopsPipelineTemplate;
import com.ruoyi.system.service.IDevopsPipelineTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.DevopsPipelineTemplateVersion;

/**
 * 流水线模板Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/devops/template")
public class DevopsPipelineTemplateController extends BaseController
{
    @Autowired
    private IDevopsPipelineTemplateService devopsPipelineTemplateService;

    /**
     * 查询流水线模板列表
     */
    @PreAuthorize("@ss.hasPermi('system:template:list')")
    @GetMapping("/list")
    public TableDataInfo list(DevopsPipelineTemplate devopsPipelineTemplate)
    {
        startPage();
        List<DevopsPipelineTemplate> list = devopsPipelineTemplateService.selectDevopsPipelineTemplateList(devopsPipelineTemplate);
        return getDataTable(list);
    }

    /**
     * 导出流水线模板列表
     */
    @PreAuthorize("@ss.hasPermi('system:template:export')")
    @Log(title = "流水线模板", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response, DevopsPipelineTemplate devopsPipelineTemplate)
    {
        List<DevopsPipelineTemplate> list = devopsPipelineTemplateService.selectDevopsPipelineTemplateList(devopsPipelineTemplate);
        ExcelUtil<DevopsPipelineTemplate> util = new ExcelUtil<DevopsPipelineTemplate>(DevopsPipelineTemplate.class);
        util.exportExcel(response, list, "流水线模板数据");
    }

    /**
     * 获取流水线模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:template:query')")
    @GetMapping(value = "/{templateId}")
    public AjaxResult getInfo(@PathVariable("templateId") Long templateId)
    {
        return success(devopsPipelineTemplateService.selectDevopsPipelineTemplateByTemplateId(templateId));
    }

    /**
     * 新增流水线模板
     */
    @PreAuthorize("@ss.hasPermi('system:template:add')")
    @Log(title = "流水线模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DevopsPipelineTemplate devopsPipelineTemplate)
    {
        return toAjax(devopsPipelineTemplateService.insertDevopsPipelineTemplate(devopsPipelineTemplate));
    }

    /**
     * 修改流水线模板
     */
    @PreAuthorize("@ss.hasPermi('system:template:edit')")
    @Log(title = "流水线模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DevopsPipelineTemplate devopsPipelineTemplate)
    {
        return toAjax(devopsPipelineTemplateService.updateDevopsPipelineTemplate(devopsPipelineTemplate));
    }

    /**
     * 删除流水线模板
     */
    @PreAuthorize("@ss.hasPermi('system:template:remove')")
    @Log(title = "流水线模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{templateIds}")
    public AjaxResult remove(@PathVariable Long[] templateIds)
    {
        return toAjax(devopsPipelineTemplateService.deleteDevopsPipelineTemplateByTemplateIds(templateIds));
    }

    /**
     * 查询流水线模板版本历史
     */
    @PreAuthorize("@ss.hasPermi('system:template:query')")
    @GetMapping("/versionList/{templateId}")
    public TableDataInfo versionList(@PathVariable("templateId") Long templateId)
    {
        startPage();
        DevopsPipelineTemplateVersion query = new DevopsPipelineTemplateVersion();
        query.setTemplateId(templateId);
        List<DevopsPipelineTemplateVersion> list = devopsPipelineTemplateService.selectDevopsPipelineTemplateVersionList(templateId);
        return getDataTable(list);
    }

    /**
     * 恢复流水线模板版本
     */
    @PreAuthorize("@ss.hasPermi('system:template:edit')")
    @Log(title = "流水线模板", businessType = BusinessType.UPDATE)
    @PutMapping("/restore/{versionId}")
    public AjaxResult restoreVersion(@PathVariable("versionId") Long versionId)
    {
        return toAjax(devopsPipelineTemplateService.restorePipelineTemplateVersion(versionId));
    }
} 