package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.DevopsTask;

/**
 * 任务配置Service接口
 * 
 * @author ruoyi
 */
public interface IDevopsTaskService 
{
    /**
     * 查询任务配置
     * 
     * @param taskId 任务配置主键
     * @return 任务配置
     */
    public DevopsTask selectDevopsTaskByTaskId(Long taskId);

    /**
     * 查询任务配置列表
     * 
     * @param devopsTask 任务配置
     * @return 任务配置集合
     */
    public List<DevopsTask> selectDevopsTaskList(DevopsTask devopsTask);

    /**
     * 新增任务配置
     * 
     * @param devopsTask 任务配置
     * @return 结果
     */
    public int insertDevopsTask(DevopsTask devopsTask);

    /**
     * 修改任务配置
     * 
     * @param devopsTask 任务配置
     * @return 结果
     */
    public int updateDevopsTask(DevopsTask devopsTask);

    /**
     * 批量删除任务配置
     * 
     * @param taskIds 需要删除的任务配置主键集合
     * @return 结果
     */
    public int deleteDevopsTaskByTaskIds(Long[] taskIds);

    /**
     * 删除任务配置信息
     * 
     * @param taskId 任务配置主键
     * @return 结果
     */
    public int deleteDevopsTaskByTaskId(Long taskId);
} 