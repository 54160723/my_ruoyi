package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.system.domain.DevopsPipelineTemplate;
import com.ruoyi.system.domain.DevopsPipelineTemplateVersion;

/**
 * 流水线模板Service接口
 * 
 * @author ruoyi
 */
public interface IDevopsPipelineTemplateService 
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
     * 批量删除流水线模板
     * 
     * @param templateIds 需要删除的流水线模板主键集合
     * @return 结果
     */
    public int deleteDevopsPipelineTemplateByTemplateIds(Long[] templateIds);

    /**
     * 删除流水线模板信息
     * 
     * @param templateId 流水线模板主键
     * @return 结果
     */
    public int deleteDevopsPipelineTemplateByTemplateId(Long templateId);

    /**
     * 根据模板生成流水线脚本
     * 
     * @param templateId 模板ID
     * @param parameters 参数
     * @return 流水线脚本
     */
    public String generatePipelineScript(Long templateId, Map<String, String> parameters);

    /**
     * 查询流水线模板版本列表
     *
     * @param templateId 模板ID
     * @return 流水线模板版本集合
     */
    public List<DevopsPipelineTemplateVersion> selectDevopsPipelineTemplateVersionList(Long templateId);

    /**
     * 恢复流水线模板版本
     *
     * @param versionId 版本ID
     * @return 结果
     */
    public int restorePipelineTemplateVersion(Long versionId);
} 