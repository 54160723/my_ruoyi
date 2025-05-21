package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 流水线配置对象 devops_pipeline
 * 
 * @author ruoyi
 */
public class DevopsPipeline extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水线ID */
    private Long pipelineId;

    /** 服务ID */
    @Excel(name = "服务ID")
    private Long serviceId;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serviceName;

    /** 流水线名称 */
    @Excel(name = "流水线名称")
    private String pipelineName;

    /** Jenkins配置ID */
    @Excel(name = "Jenkins配置ID")
    private Long jenkinsId;

    /** Jenkins名称 */
    @Excel(name = "Jenkins名称")
    private String jenkinsName;

    /** Jenkins任务名称 */
    @Excel(name = "Jenkins任务名称")
    private String jenkinsJob;

    /** 流水线类型（0自动生成 1手动配置） */
    @Excel(name = "流水线类型", readConverterExp = "0=自动生成,1=手动配置")
    private String pipelineType;

    /** 流水线脚本 */
    private String pipelineScript;

    /** 代码仓库类型 */
    @Excel(name = "代码仓库类型")
    private String scmType;

    /** 代码仓库地址 */
    @Excel(name = "代码仓库地址")
    private String scmUrl;

    /** 代码分支 */
    @Excel(name = "代码分支")
    private String scmBranch;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setPipelineId(Long pipelineId) 
    {
        this.pipelineId = pipelineId;
    }

    public Long getPipelineId() 
    {
        return pipelineId;
    }
    public void setServiceId(Long serviceId) 
    {
        this.serviceId = serviceId;
    }

    public Long getServiceId() 
    {
        return serviceId;
    }
    public void setServiceName(String serviceName) 
    {
        this.serviceName = serviceName;
    }

    public String getServiceName() 
    {
        return serviceName;
    }
    public void setPipelineName(String pipelineName) 
    {
        this.pipelineName = pipelineName;
    }

    public String getPipelineName() 
    {
        return pipelineName;
    }
    public void setJenkinsId(Long jenkinsId) 
    {
        this.jenkinsId = jenkinsId;
    }

    public Long getJenkinsId() 
    {
        return jenkinsId;
    }
    public void setJenkinsName(String jenkinsName) 
    {
        this.jenkinsName = jenkinsName;
    }

    public String getJenkinsName() 
    {
        return jenkinsName;
    }
    public void setJenkinsJob(String jenkinsJob) 
    {
        this.jenkinsJob = jenkinsJob;
    }

    public String getJenkinsJob() 
    {
        return jenkinsJob;
    }
    public void setPipelineType(String pipelineType) 
    {
        this.pipelineType = pipelineType;
    }

    public String getPipelineType() 
    {
        return pipelineType;
    }
    public void setPipelineScript(String pipelineScript) 
    {
        this.pipelineScript = pipelineScript;
    }

    public String getPipelineScript() 
    {
        return pipelineScript;
    }
    public void setScmType(String scmType) 
    {
        this.scmType = scmType;
    }

    public String getScmType() 
    {
        return scmType;
    }
    public void setScmUrl(String scmUrl) 
    {
        this.scmUrl = scmUrl;
    }

    public String getScmUrl() 
    {
        return scmUrl;
    }
    public void setScmBranch(String scmBranch) 
    {
        this.scmBranch = scmBranch;
    }

    public String getScmBranch() 
    {
        return scmBranch;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pipelineId", getPipelineId())
            .append("serviceId", getServiceId())
            .append("serviceName", getServiceName())
            .append("pipelineName", getPipelineName())
            .append("jenkinsId", getJenkinsId())
            .append("jenkinsName", getJenkinsName())
            .append("jenkinsJob", getJenkinsJob())
            .append("pipelineType", getPipelineType())
            .append("pipelineScript", getPipelineScript())
            .append("scmType", getScmType())
            .append("scmUrl", getScmUrl())
            .append("scmBranch", getScmBranch())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
} 