package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DevopsProjectMapper;
import com.ruoyi.system.domain.DevopsProject;
import com.ruoyi.system.service.IDevopsProjectService;

/**
 * DevOps项目Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class DevopsProjectServiceImpl implements IDevopsProjectService 
{
    @Autowired
    private DevopsProjectMapper devopsProjectMapper;

    /**
     * 查询DevOps项目
     * 
     * @param projectId DevOps项目主键
     * @return DevOps项目
     */
    @Override
    public DevopsProject selectDevopsProjectByProjectId(Long projectId)
    {
        return devopsProjectMapper.selectDevopsProjectByProjectId(projectId);
    }

    /**
     * 查询DevOps项目列表
     * 
     * @param devopsProject DevOps项目
     * @return DevOps项目
     */
    @Override
    public List<DevopsProject> selectDevopsProjectList(DevopsProject devopsProject)
    {
        return devopsProjectMapper.selectDevopsProjectList(devopsProject);
    }

    /**
     * 新增DevOps项目
     * 
     * @param devopsProject DevOps项目
     * @return 结果
     */
    @Override
    public int insertDevopsProject(DevopsProject devopsProject)
    {
        return devopsProjectMapper.insertDevopsProject(devopsProject);
    }

    /**
     * 修改DevOps项目
     * 
     * @param devopsProject DevOps项目
     * @return 结果
     */
    @Override
    public int updateDevopsProject(DevopsProject devopsProject)
    {
        return devopsProjectMapper.updateDevopsProject(devopsProject);
    }

    /**
     * 批量删除DevOps项目
     * 
     * @param projectIds 需要删除的DevOps项目主键
     * @return 结果
     */
    @Override
    public int deleteDevopsProjectByProjectIds(Long[] projectIds)
    {
        return devopsProjectMapper.deleteDevopsProjectByProjectIds(projectIds);
    }

    /**
     * 删除DevOps项目信息
     * 
     * @param projectId DevOps项目主键
     * @return 结果
     */
    @Override
    public int deleteDevopsProjectByProjectId(Long projectId)
    {
        return devopsProjectMapper.deleteDevopsProjectByProjectId(projectId);
    }
} 