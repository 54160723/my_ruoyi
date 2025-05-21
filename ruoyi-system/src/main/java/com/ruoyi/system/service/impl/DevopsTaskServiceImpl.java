package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DevopsTaskMapper;
import com.ruoyi.system.domain.DevopsTask;
import com.ruoyi.system.service.IDevopsTaskService;

/**
 * 任务配置Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class DevopsTaskServiceImpl implements IDevopsTaskService 
{
    @Autowired
    private DevopsTaskMapper devopsTaskMapper;

    /**
     * 查询任务配置
     * 
     * @param taskId 任务配置主键
     * @return 任务配置
     */
    @Override
    public DevopsTask selectDevopsTaskByTaskId(Long taskId)
    {
        return devopsTaskMapper.selectDevopsTaskByTaskId(taskId);
    }

    /**
     * 查询任务配置列表
     * 
     * @param devopsTask 任务配置
     * @return 任务配置
     */
    @Override
    public List<DevopsTask> selectDevopsTaskList(DevopsTask devopsTask)
    {
        return devopsTaskMapper.selectDevopsTaskList(devopsTask);
    }

    /**
     * 新增任务配置
     * 
     * @param devopsTask 任务配置
     * @return 结果
     */
    @Override
    public int insertDevopsTask(DevopsTask devopsTask)
    {
        return devopsTaskMapper.insertDevopsTask(devopsTask);
    }

    /**
     * 修改任务配置
     * 
     * @param devopsTask 任务配置
     * @return 结果
     */
    @Override
    public int updateDevopsTask(DevopsTask devopsTask)
    {
        return devopsTaskMapper.updateDevopsTask(devopsTask);
    }

    /**
     * 批量删除任务配置
     * 
     * @param taskIds 需要删除的任务配置主键
     * @return 结果
     */
    @Override
    public int deleteDevopsTaskByTaskIds(Long[] taskIds)
    {
        return devopsTaskMapper.deleteDevopsTaskByTaskIds(taskIds);
    }

    /**
     * 删除任务配置信息
     * 
     * @param taskId 任务配置主键
     * @return 结果
     */
    @Override
    public int deleteDevopsTaskByTaskId(Long taskId)
    {
        return devopsTaskMapper.deleteDevopsTaskByTaskId(taskId);
    }
} 