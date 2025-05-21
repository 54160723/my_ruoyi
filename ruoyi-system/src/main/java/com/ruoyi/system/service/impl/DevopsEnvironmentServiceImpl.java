package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DevopsEnvironmentMapper;
import com.ruoyi.system.domain.DevopsEnvironment;
import com.ruoyi.system.service.IDevopsEnvironmentService;

/**
 * 环境管理Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class DevopsEnvironmentServiceImpl implements IDevopsEnvironmentService 
{
    @Autowired
    private DevopsEnvironmentMapper devopsEnvironmentMapper;

    /**
     * 查询环境管理
     * 
     * @param envId 环境管理主键
     * @return 环境管理
     */
    @Override
    public DevopsEnvironment selectDevopsEnvironmentByEnvId(Long envId)
    {
        return devopsEnvironmentMapper.selectDevopsEnvironmentByEnvId(envId);
    }

    /**
     * 查询环境管理列表
     * 
     * @param devopsEnvironment 环境管理
     * @return 环境管理
     */
    @Override
    public List<DevopsEnvironment> selectDevopsEnvironmentList(DevopsEnvironment devopsEnvironment)
    {
        return devopsEnvironmentMapper.selectDevopsEnvironmentList(devopsEnvironment);
    }

    /**
     * 新增环境管理
     * 
     * @param devopsEnvironment 环境管理
     * @return 结果
     */
    @Override
    public int insertDevopsEnvironment(DevopsEnvironment devopsEnvironment)
    {
        return devopsEnvironmentMapper.insertDevopsEnvironment(devopsEnvironment);
    }

    /**
     * 修改环境管理
     * 
     * @param devopsEnvironment 环境管理
     * @return 结果
     */
    @Override
    public int updateDevopsEnvironment(DevopsEnvironment devopsEnvironment)
    {
        return devopsEnvironmentMapper.updateDevopsEnvironment(devopsEnvironment);
    }

    /**
     * 批量删除环境管理
     * 
     * @param envIds 需要删除的环境管理主键
     * @return 结果
     */
    @Override
    public int deleteDevopsEnvironmentByEnvIds(Long[] envIds)
    {
        return devopsEnvironmentMapper.deleteDevopsEnvironmentByEnvIds(envIds);
    }

    /**
     * 删除环境管理信息
     * 
     * @param envId 环境管理主键
     * @return 结果
     */
    @Override
    public int deleteDevopsEnvironmentByEnvId(Long envId)
    {
        return devopsEnvironmentMapper.deleteDevopsEnvironmentByEnvId(envId);
    }
} 