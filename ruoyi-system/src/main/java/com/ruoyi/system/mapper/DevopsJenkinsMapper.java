package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ruoyi.system.domain.DevopsJenkins;

/**
 * Jenkins配置Mapper接口
 * 
 * @author ruoyi
 */
@Mapper
public interface DevopsJenkinsMapper 
{
    /**
     * 查询Jenkins配置
     * 
     * @param jenkinsId Jenkins配置主键
     * @return Jenkins配置
     */
    public DevopsJenkins selectDevopsJenkinsByJenkinsId(Long jenkinsId);

    /**
     * 查询Jenkins配置列表
     * 
     * @param devopsJenkins Jenkins配置
     * @return Jenkins配置集合
     */
    public List<DevopsJenkins> selectDevopsJenkinsList(DevopsJenkins devopsJenkins);

    /**
     * 新增Jenkins配置
     * 
     * @param devopsJenkins Jenkins配置
     * @return 结果
     */
    public int insertDevopsJenkins(DevopsJenkins devopsJenkins);

    /**
     * 修改Jenkins配置
     * 
     * @param devopsJenkins Jenkins配置
     * @return 结果
     */
    public int updateDevopsJenkins(DevopsJenkins devopsJenkins);

    /**
     * 删除Jenkins配置
     * 
     * @param jenkinsId Jenkins配置主键
     * @return 结果
     */
    public int deleteDevopsJenkinsByJenkinsId(Long jenkinsId);

    /**
     * 批量删除Jenkins配置
     * 
     * @param jenkinsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDevopsJenkinsByJenkinsIds(Long[] jenkinsIds);
} 