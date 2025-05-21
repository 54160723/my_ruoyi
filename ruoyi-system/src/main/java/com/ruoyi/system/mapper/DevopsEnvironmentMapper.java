package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DevopsEnvironment;

/**
 * 环境管理Mapper接口
 * 
 * @author ruoyi
 */
public interface DevopsEnvironmentMapper 
{
    /**
     * 查询环境管理
     * 
     * @param envId 环境管理主键
     * @return 环境管理
     */
    public DevopsEnvironment selectDevopsEnvironmentByEnvId(Long envId);

    /**
     * 查询环境管理列表
     * 
     * @param devopsEnvironment 环境管理
     * @return 环境管理集合
     */
    public List<DevopsEnvironment> selectDevopsEnvironmentList(DevopsEnvironment devopsEnvironment);

    /**
     * 新增环境管理
     * 
     * @param devopsEnvironment 环境管理
     * @return 结果
     */
    public int insertDevopsEnvironment(DevopsEnvironment devopsEnvironment);

    /**
     * 修改环境管理
     * 
     * @param devopsEnvironment 环境管理
     * @return 结果
     */
    public int updateDevopsEnvironment(DevopsEnvironment devopsEnvironment);

    /**
     * 删除环境管理
     * 
     * @param envId 环境管理主键
     * @return 结果
     */
    public int deleteDevopsEnvironmentByEnvId(Long envId);

    /**
     * 批量删除环境管理
     * 
     * @param envIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDevopsEnvironmentByEnvIds(Long[] envIds);
} 