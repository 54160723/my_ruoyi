package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DevopsPipelineTemplate;

/**
 * 流水线模板Mapper接口
 * 
 * @author ruoyi
 */
public interface DevopsPipelineTemplateMapper 
{
    /**
     * 查询流水线模板
     * 
     * @param templateId 流水线模板主键
     * @return 流水线模板
     */
    public DevopsPipelineTemplate selectDevopsPipelineTemplateByTemplateId(Long templateId);

    /**
     * 查询流水线模板列表
     * 
     * @param devopsPipelineTemplate 流水线模板
     * @return 流水线模板集合
     */
    public List<DevopsPipelineTemplate> selectDevopsPipelineTemplateList(DevopsPipelineTemplate devopsPipelineTemplate);

    /**
     * 新增流水线模板
     * 
     * @param devopsPipelineTemplate 流水线模板
     * @return 结果
     */
    public int insertDevopsPipelineTemplate(DevopsPipelineTemplate devopsPipelineTemplate);

    /**
     * 修改流水线模板
     * 
     * @param devopsPipelineTemplate 流水线模板
     * @return 结果
     */
    public int updateDevopsPipelineTemplate(DevopsPipelineTemplate devopsPipelineTemplate);

    /**
     * 删除流水线模板
     * 
     * @param templateId 流水线模板主键
     * @return 结果
     */
    public int deleteDevopsPipelineTemplateByTemplateId(Long templateId);

    /**
     * 批量删除流水线模板
     * 
     * @param templateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDevopsPipelineTemplateByTemplateIds(Long[] templateIds);
} 