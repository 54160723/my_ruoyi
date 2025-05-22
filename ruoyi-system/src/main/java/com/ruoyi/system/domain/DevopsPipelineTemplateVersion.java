package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 流水线模板版本对象 devops_pipeline_template_version
 *
 * @author ruoyi
 *
 */
public class DevopsPipelineTemplateVersion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 版本ID */
    private Long versionId;

    /** 流水线模板ID */
    private Long templateId;

    /** 版本号 */
    @Excel(name = "版本号")
    private String versionNumber;

    /** 模板内容 (Jenkinsfile) */
    private String templateContent;

    /** 模板参数定义 (JSON格式) */
    private String parameters;

    public void setVersionId(Long versionId)
    {
        this.versionId = versionId;
    }

    public Long getVersionId()
    {
        return versionId;
    }
    public void setTemplateId(Long templateId)
    {
        this.templateId = templateId;
    }

    public Long getTemplateId()
    {
        return templateId;
    }
    public void setVersionNumber(String versionNumber)
    {
        this.versionNumber = versionNumber;
    }

    public String getVersionNumber()
    {
        return versionNumber;
    }
    public void setTemplateContent(String templateContent)
    {
        this.templateContent = templateContent;
    }

    public String getTemplateContent()
    {
        return templateContent;
    }
    public void setParameters(String parameters)
    {
        this.parameters = parameters;
    }

    public String getParameters()
    {
        return parameters;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("versionId", getVersionId())
            .append("templateId", getTemplateId())
            .append("versionNumber", getVersionNumber())
            .append("templateContent", getTemplateContent())
            .append("parameters", getParameters())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .toString();
    }
} 