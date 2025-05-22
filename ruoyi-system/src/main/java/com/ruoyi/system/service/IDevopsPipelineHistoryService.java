package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.DevopsPipelineHistory;

/**
 * 流水线执行历史Service接口
 * 
 * @author ruoyi
 */
public interface IDevopsPipelineHistoryService 
{
    /**
     * 查询流水线执行历史
     * 
     * @param historyId 流水线执行历史主键
     * @return 流水线执行历史
     */
    public DevopsPipelineHistory selectDevopsPipelineHistoryByHistoryId(Long historyId);

    /**
     * 查询流水线执行历史列表
     * 
     * @param devopsPipelineHistory 流水线执行历史
     * @return 流水线执行历史集合
     */
    public List<DevopsPipelineHistory> selectDevopsPipelineHistoryList(DevopsPipelineHistory devopsPipelineHistory);

    /**
     * 新增流水线执行历史
     * 
     * @param devopsPipelineHistory 流水线执行历史
     * @return 结果
     */
    public int insertDevopsPipelineHistory(DevopsPipelineHistory devopsPipelineHistory);

    /**
     * 修改流水线执行历史
     * 
     * @param devopsPipelineHistory 流水线执行历史
     * @return 结果
     */
    public int updateDevopsPipelineHistory(DevopsPipelineHistory devopsPipelineHistory);

    /**
     * 批量删除流水线执行历史
     * 
     * @param historyIds 需要删除的流水线执行历史主键集合
     * @return 结果
     */
    public int deleteDevopsPipelineHistoryByHistoryIds(Long[] historyIds);

    /**
     * 删除流水线执行历史信息
     * 
     * @param historyId 流水线执行历史主键
     * @return 结果
     */
    public int deleteDevopsPipelineHistoryByHistoryId(Long historyId);

    /**
     * 更新流水线执行状态
     * 
     * @param historyId 流水线执行历史主键
     * @param status 状态
     * @param buildUrl 构建URL
     * @return 结果
     */
    public int updatePipelineHistoryStatus(Long historyId, String status, String buildUrl);
} 