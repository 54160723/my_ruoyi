package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.DevopsService;

/**
 * DevOps服务Service接口
 * 
 * @author ruoyi
 */
public interface IDevopsService 
{
    /**
     * 查询DevOps服务
     * 
     * @param serviceId DevOps服务主键
     * @return DevOps服务
     */
    public DevopsService selectDevopsServiceByServiceId(Long serviceId);

    /**
     * 查询DevOps服务列表
     * 
     * @param devopsService DevOps服务
     * @return DevOps服务集合
     */
    public List<DevopsService> selectDevopsServiceList(DevopsService devopsService);

    /**
     * 新增DevOps服务
     * 
     * @param devopsService DevOps服务
     * @return 结果
     */
    public int insertDevopsService(DevopsService devopsService);

    /**
     * 修改DevOps服务
     * 
     * @param devopsService DevOps服务
     * @return 结果
     */
    public int updateDevopsService(DevopsService devopsService);

    /**
     * 批量删除DevOps服务
     * 
     * @param serviceIds 需要删除的DevOps服务主键集合
     * @return 结果
     */
    public int deleteDevopsServiceByServiceIds(Long[] serviceIds);

    /**
     * 删除DevOps服务信息
     * 
     * @param serviceId DevOps服务主键
     * @return 结果
     */
    public int deleteDevopsServiceByServiceId(Long serviceId);
} 