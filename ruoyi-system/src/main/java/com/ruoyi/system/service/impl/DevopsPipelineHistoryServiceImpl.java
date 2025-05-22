package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DevopsPipelineHistoryMapper;
import com.ruoyi.system.domain.DevopsPipelineHistory;
import com.ruoyi.system.service.IDevopsPipelineHistoryService;

/**
 * 流水线执行历史Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class DevopsPipelineHistoryServiceImpl implements IDevopsPipelineHistoryService 
{
    @Autowired
    private DevopsPipelineHistoryMapper devopsPipelineHistoryMapper;

    /**
     * 查询流水线执行历史
     * 
     * @param historyId 流水线执行历史主键
     * @return 流水线执行历史
     */
    @Override
    public DevopsPipelineHistory selectDevopsPipelineHistoryByHistoryId(Long historyId)
    {
        return devopsPipelineHistoryMapper.selectDevopsPipelineHistoryByHistoryId(historyId);
    }

    /**
     * 查询流水线执行历史列表
     * 
     * @param devopsPipelineHistory 流水线执行历史
     * @return 流水线执行历史
     */
    @Override
    public List<DevopsPipelineHistory> selectDevopsPipelineHistoryList(DevopsPipelineHistory devopsPipelineHistory)
    {
        return devopsPipelineHistoryMapper.selectDevopsPipelineHistoryList(devopsPipelineHistory);
    }

    /**
     * 新增流水线执行历史
     * 
     * @param devopsPipelineHistory 流水线执行历史
     * @return 结果
     */
    @Override
    public int insertDevopsPipelineHistory(DevopsPipelineHistory devopsPipelineHistory)
    {
        return devopsPipelineHistoryMapper.insertDevopsPipelineHistory(devopsPipelineHistory);
    }

    /**
     * 修改流水线执行历史
     * 
     * @param devopsPipelineHistory 流水线执行历史
     * @return 结果
     */
    @Override
    public int updateDevopsPipelineHistory(DevopsPipelineHistory devopsPipelineHistory)
    {
        return devopsPipelineHistoryMapper.updateDevopsPipelineHistory(devopsPipelineHistory);
    }

    /**
     * 批量删除流水线执行历史
     * 
     * @param historyIds 需要删除的流水线执行历史主键
     * @return 结果
     */
    @Override
    public int deleteDevopsPipelineHistoryByHistoryIds(Long[] historyIds)
    {
        return devopsPipelineHistoryMapper.deleteDevopsPipelineHistoryByHistoryIds(historyIds);
    }

    /**
     * 删除流水线执行历史信息
     * 
     * @param historyId 流水线执行历史主键
     * @return 结果
     */
    @Override
    public int deleteDevopsPipelineHistoryByHistoryId(Long historyId)
    {
        return devopsPipelineHistoryMapper.deleteDevopsPipelineHistoryByHistoryId(historyId);
    }

    /**
     * 更新流水线执行状态
     * 
     * @param historyId 流水线执行历史主键
     * @param status 状态
     * @param buildUrl 构建URL
     * @return 结果
     */
    @Override
    public int updatePipelineHistoryStatus(Long historyId, String status, String buildUrl)
    {
        DevopsPipelineHistory history = new DevopsPipelineHistory();
        history.setHistoryId(historyId);
        history.setStatus(status);
        history.setBuildUrl(buildUrl);
        return devopsPipelineHistoryMapper.updateDevopsPipelineHistory(history);
    }
} 