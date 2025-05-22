package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.system.domain.DevopsPipeline;
import com.ruoyi.system.domain.DevopsPipelineHistory;

/**
 * 流水线配置Service接口
 * 
 * @author ruoyi
 */
public interface IDevopsPipelineService 
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
     * 批量删除流水线配置
     * 
     * @param pipelineIds 需要删除的流水线配置主键集合
     * @return 结果
     */
    public int deleteDevopsPipelineByPipelineIds(Long[] pipelineIds);

    /**
     * 删除流水线配置信息
     * 
     * @param pipelineId 流水线配置主键
     * @return 结果
     */
    public int deleteDevopsPipelineByPipelineId(Long pipelineId);

    /**
     * 执行流水线
     * 
     * @param pipelineId 流水线配置主键
     * @return 结果
     */
    public boolean executePipeline(Long pipelineId);

    /**
     * 触发流水线执行
     * 
     * @param pipelineId 流水线ID
     * @return 结果
     */
    public int triggerPipeline(Long pipelineId);

    /**
     * 查询流水线执行历史列表
     * 
     * @param history 查询条件
     * @return 历史记录列表
     */
    public List<DevopsPipelineHistory> selectPipelineHistoryList(DevopsPipelineHistory history);

    /**
     * 获取构建日志
     * 
     * @param historyId 历史记录ID
     * @return 构建日志
     */
    public String getBuildLog(Long historyId);

    /**
     * 刷新构建状态
     * 
     * @param historyId 历史记录ID
     * @return 结果
     */
    public int refreshBuildStatus(Long historyId);
} 