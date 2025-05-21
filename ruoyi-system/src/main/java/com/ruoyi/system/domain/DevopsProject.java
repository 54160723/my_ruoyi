package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * DevOps项目对象 devops_project
 * 
 * @author ruoyi
 */
public class DevopsProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private Long projectId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目标识 */
    @Excel(name = "项目标识")
    private String projectKey;

    /** 项目描述 */
    @Excel(name = "项目描述")
    private String description;

    /** Git仓库地址 */
    @Excel(name = "Git仓库地址")
    private String gitUrl;

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }

    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }

    public void setProjectKey(String projectKey) 
    {
        this.projectKey = projectKey;
    }

    public String getProjectKey() 
    {
        return projectKey;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setGitUrl(String gitUrl) 
    {
        this.gitUrl = gitUrl;
    }

    public String getGitUrl() 
    {
        return gitUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("projectKey", getProjectKey())
            .append("description", getDescription())
            .append("gitUrl", getGitUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
} 