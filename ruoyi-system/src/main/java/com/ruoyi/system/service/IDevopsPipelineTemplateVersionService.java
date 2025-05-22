package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.DevopsPipelineTemplateVersion;

/**
 * 流水线模板版本Service接口
 *
 * @author ruoyi
 *
 */
public interface IDevopsPipelineTemplateVersionService 
{
    /**
     * 查询流水线模板版本列表
     *
     * @param devopsPipelineTemplateVersion 流水线模板版本
     * @return 流水线模板版本集合
     */
    public List<DevopsPipelineTemplateVersion> selectDevopsPipelineTemplateVersionList(DevopsPipelineTemplateVersion devopsPipelineTemplateVersion);

    /**
     * 查询流水线模板版本详细
     *
     * @param versionId 流水线模板版本主键
     * @return 流水线模板版本
     */
    public DevopsPipelineTemplateVersion selectDevopsPipelineTemplateVersionByVersionId(Long versionId);

    /**
     * 新增流水线模板版本
     *
     * @param devopsPipelineTemplateVersion 流水线模板版本
     * @return 结果
     */
    public int insertDevopsPipelineTemplateVersion(DevopsPipelineTemplateVersion devopsPipelineTemplateVersion);

    /**
     * 修改流水线模板版本
     *
     * @param devopsPipelineTemplateVersion 流水线模板版本
     * @return 结果
     */
    public int updateDevopsPipelineTemplateVersion(DevopsPipelineTemplateVersion devopsPipelineTemplateVersion);

    /**
     * 批量删除流水线模板版本
     *
     * @param versionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDevopsPipelineTemplateVersionByVersionIds(Long[] versionIds);

    /**
     * 删除流水线模板版本信息
     *
     * @param versionId 流水线模板版本主键
     * @return 结果
     */
    public int deleteDevopsPipelineTemplateVersionByVersionId(Long versionId);

    /**
     * 根据模板ID查询最新的版本
     *
     * @param templateId 模板ID
     * @return 最新的流水线模板版本
     */
    public DevopsPipelineTemplateVersion selectLatestDevopsPipelineTemplateVersionByTemplateId(Long templateId);
} 