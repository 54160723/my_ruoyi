package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DevopsServiceMapper;
import com.ruoyi.system.domain.DevopsService;
import com.ruoyi.system.service.IDevopsService;

/**
 * DevOps服务Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class DevopsServiceImpl implements IDevopsService 
{
    @Autowired
    private DevopsServiceMapper devopsServiceMapper;

    /**
     * 查询DevOps服务
     * 
     * @param serviceId DevOps服务主键
     * @return DevOps服务
     */
    @Override
    public DevopsService selectDevopsServiceByServiceId(Long serviceId)
    {
        return devopsServiceMapper.selectDevopsServiceByServiceId(serviceId);
    }

    /**
     * 查询DevOps服务列表
     * 
     * @param devopsService DevOps服务
     * @return DevOps服务
     */
    @Override
    public List<DevopsService> selectDevopsServiceList(DevopsService devopsService)
    {
        return devopsServiceMapper.selectDevopsServiceList(devopsService);
    }

    /**
     * 新增DevOps服务
     * 
     * @param devopsService DevOps服务
     * @return 结果
     */
    @Override
    public int insertDevopsService(DevopsService devopsService)
    {
        return devopsServiceMapper.insertDevopsService(devopsService);
    }

    /**
     * 修改DevOps服务
     * 
     * @param devopsService DevOps服务
     * @return 结果
     */
    @Override
    public int updateDevopsService(DevopsService devopsService)
    {
        return devopsServiceMapper.updateDevopsService(devopsService);
    }

    /**
     * 批量删除DevOps服务
     * 
     * @param serviceIds 需要删除的DevOps服务主键
     * @return 结果
     */
    @Override
    public int deleteDevopsServiceByServiceIds(Long[] serviceIds)
    {
        return devopsServiceMapper.deleteDevopsServiceByServiceIds(serviceIds);
    }

    /**
     * 删除DevOps服务信息
     * 
     * @param serviceId DevOps服务主键
     * @return 结果
     */
    @Override
    public int deleteDevopsServiceByServiceId(Long serviceId)
    {
        return devopsServiceMapper.deleteDevopsServiceByServiceId(serviceId);
    }
} 