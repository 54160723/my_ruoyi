package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DevopsPipelineHistory;

/**
 * 流水线执行历史Mapper接口
 * 
 * @author ruoyi
 */
public interface DevopsPipelineHistoryMapper 
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
     * 删除流水线执行历史
     * 
     * @param historyId 流水线执行历史主键
     * @return 结果
     */
    public int deleteDevopsPipelineHistoryByHistoryId(Long historyId);

    /**
     * 批量删除流水线执行历史
     * 
     * @param historyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDevopsPipelineHistoryByHistoryIds(Long[] historyIds);
} 