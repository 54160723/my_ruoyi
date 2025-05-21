package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * DevOps服务对象 devops_service
 * 
 * @author ruoyi
 */
public class DevopsService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务ID */
    private Long serviceId;

    /** 所属项目ID */
    @Excel(name = "所属项目ID")
    private Long projectId;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serviceName;

    /** 服务标识 */
    @Excel(name = "服务标识")
    private String serviceKey;

    /** Git分支 */
    @Excel(name = "Git分支")
    private String gitBranch;

    /** 构建类型 */
    @Excel(name = "构建类型", readConverterExp = "Maven=1,Node=2,Docker=3")
    private String buildType;

    /** 构建命令 */
    @Excel(name = "构建命令")
    private String buildCommand;

    /** Dockerfile路径 */
    @Excel(name = "Dockerfile路径")
    private String dockerfilePath;

    /** 所属项目名称 */
    @Excel(name = "所属项目名称")
    private String projectName;

    public void setServiceId(Long serviceId) 
    {
        this.serviceId = serviceId;
    }

    public Long getServiceId() 
    {
        return serviceId;
    }

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }

    public void setServiceName(String serviceName) 
    {
        this.serviceName = serviceName;
    }

    public String getServiceName() 
    {
        return serviceName;
    }

    public void setServiceKey(String serviceKey) 
    {
        this.serviceKey = serviceKey;
    }

    public String getServiceKey() 
    {
        return serviceKey;
    }

    public void setGitBranch(String gitBranch) 
    {
        this.gitBranch = gitBranch;
    }

    public String getGitBranch() 
    {
        return gitBranch;
    }

    public void setBuildType(String buildType) 
    {
        this.buildType = buildType;
    }

    public String getBuildType() 
    {
        return buildType;
    }

    public void setBuildCommand(String buildCommand) 
    {
        this.buildCommand = buildCommand;
    }

    public String getBuildCommand() 
    {
        return buildCommand;
    }

    public void setDockerfilePath(String dockerfilePath) 
    {
        this.dockerfilePath = dockerfilePath;
    }

    public String getDockerfilePath() 
    {
        return dockerfilePath;
    }

    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serviceId", getServiceId())
            .append("projectId", getProjectId())
            .append("serviceName", getServiceName())
            .append("serviceKey", getServiceKey())
            .append("gitBranch", getGitBranch())
            .append("buildType", getBuildType())
            .append("buildCommand", getBuildCommand())
            .append("dockerfilePath", getDockerfilePath())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("projectName", getProjectName())
            .toString();
    }
} 