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
import com.ruoyi.system.domain.DevopsPipeline;
import com.ruoyi.system.service.IDevopsPipelineService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 流水线配置Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/devops/pipeline")
public class DevopsPipelineController extends BaseController
{
    @Autowired
    private IDevopsPipelineService devopsPipelineService;

    /**
     * 查询流水线配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:pipeline:list')")
    @GetMapping("/list")
    public TableDataInfo list(DevopsPipeline devopsPipeline)
    {
        startPage();
        List<DevopsPipeline> list = devopsPipelineService.selectDevopsPipelineList(devopsPipeline);
        return getDataTable(list);
    }

    /**
     * 导出流水线配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:pipeline:export')")
    @Log(title = "流水线配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DevopsPipeline devopsPipeline)
    {
        List<DevopsPipeline> list = devopsPipelineService.selectDevopsPipelineList(devopsPipeline);
        ExcelUtil<DevopsPipeline> util = new ExcelUtil<DevopsPipeline>(DevopsPipeline.class);
        util.exportExcel(response, list, "流水线配置数据");
    }

    /**
     * 获取流水线配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:pipeline:query')")
    @GetMapping(value = "/{pipelineId}")
    public AjaxResult getInfo(@PathVariable("pipelineId") Long pipelineId)
    {
        return success(devopsPipelineService.selectDevopsPipelineByPipelineId(pipelineId));
    }

    /**
     * 新增流水线配置
     */
    @PreAuthorize("@ss.hasPermi('system:pipeline:add')")
    @Log(title = "流水线配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DevopsPipeline devopsPipeline)
    {
        return toAjax(devopsPipelineService.insertDevopsPipeline(devopsPipeline));
    }

    /**
     * 修改流水线配置
     */
    @PreAuthorize("@ss.hasPermi('system:pipeline:edit')")
    @Log(title = "流水线配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DevopsPipeline devopsPipeline)
    {
        return toAjax(devopsPipelineService.updateDevopsPipeline(devopsPipeline));
    }

    /**
     * 删除流水线配置
     */
    @PreAuthorize("@ss.hasPermi('system:pipeline:remove')")
    @Log(title = "流水线配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pipelineIds}")
    public AjaxResult remove(@PathVariable Long[] pipelineIds)
    {
        return toAjax(devopsPipelineService.deleteDevopsPipelineByPipelineIds(pipelineIds));
    }

    /**
     * 执行流水线
     */
    @PreAuthorize("@ss.hasPermi('system:pipeline:execute')")
    @Log(title = "流水线配置", businessType = BusinessType.UPDATE)
    @PutMapping("/execute/{pipelineId}")
    public AjaxResult execute(@PathVariable("pipelineId") Long pipelineId)
    {
        return toAjax(devopsPipelineService.executePipeline(pipelineId));
    }
} 