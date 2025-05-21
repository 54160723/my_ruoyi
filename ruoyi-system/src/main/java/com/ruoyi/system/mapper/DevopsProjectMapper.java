package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DevopsProject;

/**
 * DevOps项目Mapper接口
 * 
 * @author ruoyi
 */
public interface DevopsProjectMapper 
{
    /**
     * 查询DevOps项目
     * 
     * @param projectId DevOps项目主键
     * @return DevOps项目
     */
    public DevopsProject selectDevopsProjectByProjectId(Long projectId);

    /**
     * 查询DevOps项目列表
     * 
     * @param devopsProject DevOps项目
     * @return DevOps项目集合
     */
    public List<DevopsProject> selectDevopsProjectList(DevopsProject devopsProject);

    /**
     * 新增DevOps项目
     * 
     * @param devopsProject DevOps项目
     * @return 结果
     */
    public int insertDevopsProject(DevopsProject devopsProject);

    /**
     * 修改DevOps项目
     * 
     * @param devopsProject DevOps项目
     * @return 结果
     */
    public int updateDevopsProject(DevopsProject devopsProject);

    /**
     * 删除DevOps项目
     * 
     * @param projectId DevOps项目主键
     * @return 结果
     */
    public int deleteDevopsProjectByProjectId(Long projectId);

    /**
     * 批量删除DevOps项目
     * 
     * @param projectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDevopsProjectByProjectIds(Long[] projectIds);
} 