package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DevopsJenkinsMapper;
import com.ruoyi.system.domain.DevopsJenkins;
import com.ruoyi.system.service.IDevopsJenkinsService;

/**
 * Jenkins配置Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class DevopsJenkinsServiceImpl implements IDevopsJenkinsService 
{
    @Autowired
    private DevopsJenkinsMapper devopsJenkinsMapper;

    /**
     * 查询Jenkins配置
     * 
     * @param jenkinsId Jenkins配置主键
     * @return Jenkins配置
     */
    @Override
    public DevopsJenkins selectDevopsJenkinsByJenkinsId(Long jenkinsId)
    {
        return devopsJenkinsMapper.selectDevopsJenkinsByJenkinsId(jenkinsId);
    }

    /**
     * 查询Jenkins配置列表
     * 
     * @param devopsJenkins Jenkins配置
     * @return Jenkins配置
     */
    @Override
    public List<DevopsJenkins> selectDevopsJenkinsList(DevopsJenkins devopsJenkins)
    {
        return devopsJenkinsMapper.selectDevopsJenkinsList(devopsJenkins);
    }

    /**
     * 新增Jenkins配置
     * 
     * @param devopsJenkins Jenkins配置
     * @return 结果
     */
    @Override
    public int insertDevopsJenkins(DevopsJenkins devopsJenkins)
    {
        return devopsJenkinsMapper.insertDevopsJenkins(devopsJenkins);
    }

    /**
     * 修改Jenkins配置
     * 
     * @param devopsJenkins Jenkins配置
     * @return 结果
     */
    @Override
    public int updateDevopsJenkins(DevopsJenkins devopsJenkins)
    {
        return devopsJenkinsMapper.updateDevopsJenkins(devopsJenkins);
    }

    /**
     * 批量删除Jenkins配置
     * 
     * @param jenkinsIds 需要删除的Jenkins配置主键
     * @return 结果
     */
    @Override
    public int deleteDevopsJenkinsByJenkinsIds(Long[] jenkinsIds)
    {
        return devopsJenkinsMapper.deleteDevopsJenkinsByJenkinsIds(jenkinsIds);
    }

    /**
     * 删除Jenkins配置信息
     * 
     * @param jenkinsId Jenkins配置主键
     * @return 结果
     */
    @Override
    public int deleteDevopsJenkinsByJenkinsId(Long jenkinsId)
    {
        return devopsJenkinsMapper.deleteDevopsJenkinsByJenkinsId(jenkinsId);
    }
} 