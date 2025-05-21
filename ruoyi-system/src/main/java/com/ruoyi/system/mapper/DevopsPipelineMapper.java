package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DevopsPipeline;

/**
 * 流水线配置Mapper接口
 * 
 * @author ruoyi
 */
public interface DevopsPipelineMapper 
{
    /**
     * 查询流水线配置
     * 
     * @param pipelineId 流水线配置主键
     * @return 流水线配置
     */
    public DevopsPipeline selectDevopsPipelineByPipelineId(Long pipelineId);

    /**
     * 查询流水线配置列表
     * 
     * @param devopsPipeline 流水线配置
     * @return 流水线配置集合
     */
    public List<DevopsPipeline> selectDevopsPipelineList(DevopsPipeline devopsPipeline);

    /**
     * 新增流水线配置
     * 
     * @param devopsPipeline 流水线配置
     * @return 结果
     */
    public int insertDevopsPipeline(DevopsPipeline devopsPipeline);

    /**
     * 修改流水线配置
     * 
     * @param devopsPipeline 流水线配置
     * @return 结果
     */
    public int updateDevopsPipeline(DevopsPipeline devopsPipeline);

    /**
     * 删除流水线配置
     * 
     * @param pipelineId 流水线配置主键
     * @return 结果
     */
    public int deleteDevopsPipelineByPipelineId(Long pipelineId);

    /**
     * 批量删除流水线配置
     * 
     * @param pipelineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDevopsPipelineByPipelineIds(Long[] pipelineIds);
} 