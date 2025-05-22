package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DevopsPipelineTemplateVersionMapper;
import com.ruoyi.system.domain.DevopsPipelineTemplateVersion;
import com.ruoyi.system.service.IDevopsPipelineTemplateVersionService;
import com.ruoyi.common.utils.DateUtils;

/**
 * 流水线模板版本Service业务层处理
 *
 * @author ruoyi
 *
 */
@Service
public class DevopsPipelineTemplateVersionServiceImpl implements IDevopsPipelineTemplateVersionService 
{
    @Autowired
    private DevopsPipelineTemplateVersionMapper devopsPipelineTemplateVersionMapper;

    /**
     * 查询流水线模板版本列表
     *
     * @param devopsPipelineTemplateVersion 流水线模板版本
     * @return 流水线模板版本
     */
    @Override
    public List<DevopsPipelineTemplateVersion> selectDevopsPipelineTemplateVersionList(DevopsPipelineTemplateVersion devopsPipelineTemplateVersion)
    {
        return devopsPipelineTemplateVersionMapper.selectDevopsPipelineTemplateVersionList(devopsPipelineTemplateVersion);
    }

    /**
     * 查询流水线模板版本详细
     *
     * @param versionId 流水线模板版本主键
     * @return 流水线模板版本
     */
    @Override
    public DevopsPipelineTemplateVersion selectDevopsPipelineTemplateVersionByVersionId(Long versionId)
    {
        return devopsPipelineTemplateVersionMapper.selectDevopsPipelineTemplateVersionByVersionId(versionId);
    }

    /**
     * 新增流水线模板版本
     *
     * @param devopsPipelineTemplateVersion 流水线模板版本
     * @return 结果
     */
    @Override
    public int insertDevopsPipelineTemplateVersion(DevopsPipelineTemplateVersion devopsPipelineTemplateVersion)
    {
        devopsPipelineTemplateVersion.setCreateTime(DateUtils.getNowDate());
        return devopsPipelineTemplateVersionMapper.insertDevopsPipelineTemplateVersion(devopsPipelineTemplateVersion);
    }

    /**
     * 修改流水线模板版本
     *
     * @param devopsPipelineTemplateVersion 流水线模板版本
     * @return 结果
     */
    @Override
    public int updateDevopsPipelineTemplateVersion(DevopsPipelineTemplateVersion devopsPipelineTemplateVersion)
    {
        devopsPipelineTemplateVersion.setUpdateTime(DateUtils.getNowDate());
        return devopsPipelineTemplateVersionMapper.updateDevopsPipelineTemplateVersion(devopsPipelineTemplateVersion);
    }

    /**
     * 批量删除流水线模板版本
     *
     * @param versionIds 需要删除的流水线模板版本主键
     * @return 结果
     */
    @Override
    public int deleteDevopsPipelineTemplateVersionByVersionIds(Long[] versionIds)
    {
        return devopsPipelineTemplateVersionMapper.deleteDevopsPipelineTemplateVersionByVersionIds(versionIds);
    }

    /**
     * 删除流水线模板版本信息
     *
     * @param versionId 流水线模板版本主键
     * @return 结果
     */
    @Override
    public int deleteDevopsPipelineTemplateVersionByVersionId(Long versionId)
    {
        return devopsPipelineTemplateVersionMapper.deleteDevopsPipelineTemplateVersionByVersionId(versionId);
    }

    /**
     * 根据模板ID查询最新的版本
     *
     * @param templateId 模板ID
     * @return 最新的流水线模板版本
     */
    @Override
    public DevopsPipelineTemplateVersion selectLatestDevopsPipelineTemplateVersionByTemplateId(Long templateId) {
        return devopsPipelineTemplateVersionMapper.selectLatestDevopsPipelineTemplateVersionByTemplateId(templateId);
    }
} 