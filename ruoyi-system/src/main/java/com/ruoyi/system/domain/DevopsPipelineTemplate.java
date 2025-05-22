package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 流水线模板对象 devops_pipeline_template
 * 
 * @author ruoyi
 */
public class DevopsPipelineTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模板ID */
    private Long templateId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 模板类型（0基础模板 1自定义模板） */
    @Excel(name = "模板类型", readConverterExp = "0=基础模板,1=自定义模板")
    private String templateType;

    /** 模板内容 */
    @Excel(name = "模板内容")
    private String templateContent;

    /** 模板参数定义 (JSON格式) */
    private String parameters;

    /** 模板描述 */
    @Excel(name = "模板描述")
    private String description;

    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }

    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }

    public void setTemplateType(String templateType) 
    {
        this.templateType = templateType;
    }

    public String getTemplateType() 
    {
        return templateType;
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

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("templateId", getTemplateId())
            .append("templateName", getTemplateName())
            .append("templateType", getTemplateType())
            .append("templateContent", getTemplateContent())
            .append("parameters", getParameters())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
} 