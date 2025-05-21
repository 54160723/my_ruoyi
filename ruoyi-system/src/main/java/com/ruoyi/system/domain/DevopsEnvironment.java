package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 环境管理对象 devops_environment
 * 
 * @author ruoyi
 */
public class DevopsEnvironment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 环境ID */
    private Long envId;

    /** 环境名称 */
    @Excel(name = "环境名称")
    private String envName;

    /** 环境标识 */
    @Excel(name = "环境标识")
    private String envKey;

    /** 环境类型（1开发 2测试 3预发 4生产） */
    @Excel(name = "环境类型", readConverterExp = "1=开发,2=测试,3=预发,4=生产")
    private String envType;

    /** Jenkins地址 */
    @Excel(name = "Jenkins地址")
    private String jenkinsUrl;

    /** Jenkins用户名 */
    @Excel(name = "Jenkins用户名")
    private String jenkinsUser;

    /** Jenkins Token */
    @Excel(name = "Jenkins Token")
    private String jenkinsToken;

    /** Docker仓库地址 */
    @Excel(name = "Docker仓库地址")
    private String dockerRegistry;

    /** Docker用户名 */
    @Excel(name = "Docker用户名")
    private String dockerUser;

    /** Docker密码 */
    @Excel(name = "Docker密码")
    private String dockerPassword;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setEnvId(Long envId) 
    {
        this.envId = envId;
    }

    public Long getEnvId() 
    {
        return envId;
    }

    public void setEnvName(String envName) 
    {
        this.envName = envName;
    }

    public String getEnvName() 
    {
        return envName;
    }

    public void setEnvKey(String envKey) 
    {
        this.envKey = envKey;
    }

    public String getEnvKey() 
    {
        return envKey;
    }

    public void setEnvType(String envType) 
    {
        this.envType = envType;
    }

    public String getEnvType() 
    {
        return envType;
    }

    public void setJenkinsUrl(String jenkinsUrl) 
    {
        this.jenkinsUrl = jenkinsUrl;
    }

    public String getJenkinsUrl() 
    {
        return jenkinsUrl;
    }

    public void setJenkinsUser(String jenkinsUser) 
    {
        this.jenkinsUser = jenkinsUser;
    }

    public String getJenkinsUser() 
    {
        return jenkinsUser;
    }

    public void setJenkinsToken(String jenkinsToken) 
    {
        this.jenkinsToken = jenkinsToken;
    }

    public String getJenkinsToken() 
    {
        return jenkinsToken;
    }

    public void setDockerRegistry(String dockerRegistry) 
    {
        this.dockerRegistry = dockerRegistry;
    }

    public String getDockerRegistry() 
    {
        return dockerRegistry;
    }

    public void setDockerUser(String dockerUser) 
    {
        this.dockerUser = dockerUser;
    }

    public String getDockerUser() 
    {
        return dockerUser;
    }

    public void setDockerPassword(String dockerPassword) 
    {
        this.dockerPassword = dockerPassword;
    }

    public String getDockerPassword() 
    {
        return dockerPassword;
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
            .append("envId", getEnvId())
            .append("envName", getEnvName())
            .append("envKey", getEnvKey())
            .append("envType", getEnvType())
            .append("jenkinsUrl", getJenkinsUrl())
            .append("jenkinsUser", getJenkinsUser())
            .append("jenkinsToken", getJenkinsToken())
            .append("dockerRegistry", getDockerRegistry())
            .append("dockerUser", getDockerUser())
            .append("dockerPassword", getDockerPassword())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
} 