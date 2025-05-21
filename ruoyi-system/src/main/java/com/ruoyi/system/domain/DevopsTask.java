package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务配置对象 devops_task
 * 
 * @author ruoyi
 */
public class DevopsTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 服务ID */
    @Excel(name = "服务ID")
    private Long serviceId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 任务类型 */
    @Excel(name = "任务类型", readConverterExp = "SHELL_SCRIPT=Shell脚本,PYTHON_SCRIPT=Python脚本,DOCKER_BUILD=Docker构建")
    private String taskType;

    /** 任务参数 */
    @Excel(name = "任务参数")
    private String taskParams;

    /** 工作目录 */
    @Excel(name = "工作目录")
    private String workDir;

    /** 任务顺序 */
    @Excel(name = "任务顺序")
    private Integer taskOrder;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serviceName;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }

    public void setServiceId(Long serviceId) 
    {
        this.serviceId = serviceId;
    }

    public Long getServiceId() 
    {
        return serviceId;
    }

    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }

    public void setTaskType(String taskType) 
    {
        this.taskType = taskType;
    }

    public String getTaskType() 
    {
        return taskType;
    }

    public void setTaskParams(String taskParams) 
    {
        this.taskParams = taskParams;
    }

    public String getTaskParams() 
    {
        return taskParams;
    }

    public void setWorkDir(String workDir) 
    {
        this.workDir = workDir;
    }

    public String getWorkDir() 
    {
        return workDir;
    }

    public void setTaskOrder(Integer taskOrder) 
    {
        this.taskOrder = taskOrder;
    }

    public Integer getTaskOrder() 
    {
        return taskOrder;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setServiceName(String serviceName) 
    {
        this.serviceName = serviceName;
    }

    public String getServiceName() 
    {
        return serviceName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("serviceId", getServiceId())
            .append("taskName", getTaskName())
            .append("taskType", getTaskType())
            .append("taskParams", getTaskParams())
            .append("workDir", getWorkDir())
            .append("taskOrder", getTaskOrder())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("serviceName", getServiceName())
            .toString();
    }
} 