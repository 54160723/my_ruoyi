package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Jenkins配置对象 devops_jenkins
 * 
 * @author ruoyi
 */
public class DevopsJenkins extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Jenkins配置ID */
    private Long jenkinsId;

    /** Jenkins名称 */
    @Excel(name = "Jenkins名称")
    private String jenkinsName;

    /** Jenkins地址 */
    @Excel(name = "Jenkins地址")
    private String jenkinsUrl;

    /** Jenkins用户名 */
    @Excel(name = "Jenkins用户名")
    private String jenkinsUser;

    /** Jenkins Token */
    @Excel(name = "Jenkins Token")
    private String jenkinsToken;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

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
            .append("jenkinsId", getJenkinsId())
            .append("jenkinsName", getJenkinsName())
            .append("jenkinsUrl", getJenkinsUrl())
            .append("jenkinsUser", getJenkinsUser())
            .append("jenkinsToken", getJenkinsToken())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
} 