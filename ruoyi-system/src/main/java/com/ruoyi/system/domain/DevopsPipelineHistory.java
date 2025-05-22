package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 流水线执行历史对象 devops_pipeline_history
 * 
 * @author ruoyi
 */
public class DevopsPipelineHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 历史记录ID */
    private Long historyId;

    /** 流水线ID */
    @Excel(name = "流水线ID")
    private Long pipelineId;

    /** Jenkins任务名称 */
    @Excel(name = "Jenkins任务名称")
    private String jenkinsJob;

    /** 构建编号 */
    @Excel(name = "构建编号")
    private Integer buildNumber;

    /** 构建URL */
    @Excel(name = "构建URL")
    private String buildUrl;

    /** 状态（0排队中 1运行中 2成功 3失败） */
    @Excel(name = "状态", readConverterExp = "0=排队中,1=运行中,2=成功,3=失败")
    private String status;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 持续时间(毫秒) */
    @Excel(name = "持续时间(毫秒)")
    private Long duration;

    /** 触发用户 */
    @Excel(name = "触发用户")
    private String triggerUser;

    /** 构建参数(JSON格式) */
    private String parameters;

    /** Jenkins配置ID */
    private Long jenkinsId;

    public void setHistoryId(Long historyId) 
    {
        this.historyId = historyId;
    }

    public Long getHistoryId() 
    {
        return historyId;
    }

    public void setPipelineId(Long pipelineId) 
    {
        this.pipelineId = pipelineId;
    }

    public Long getPipelineId() 
    {
        return pipelineId;
    }

    public void setJenkinsJob(String jenkinsJob) 
    {
        this.jenkinsJob = jenkinsJob;
    }

    public String getJenkinsJob() 
    {
        return jenkinsJob;
    }

    public void setBuildNumber(Integer buildNumber) 
    {
        this.buildNumber = buildNumber;
    }

    public Integer getBuildNumber() 
    {
        return buildNumber;
    }

    public void setBuildUrl(String buildUrl) 
    {
        this.buildUrl = buildUrl;
    }

    public String getBuildUrl() 
    {
        return buildUrl;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
    }

    public void setTriggerUser(String triggerUser) 
    {
        this.triggerUser = triggerUser;
    }

    public String getTriggerUser() 
    {
        return triggerUser;
    }

    public void setParameters(String parameters) 
    {
        this.parameters = parameters;
    }

    public String getParameters() 
    {
        return parameters;
    }

    public Long getJenkinsId() {
        return jenkinsId;
    }

    public void setJenkinsId(Long jenkinsId) {
        this.jenkinsId = jenkinsId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("historyId", getHistoryId())
            .append("pipelineId", getPipelineId())
            .append("jenkinsJob", getJenkinsJob())
            .append("buildNumber", getBuildNumber())
            .append("buildUrl", getBuildUrl())
            .append("status", getStatus())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("duration", getDuration())
            .append("triggerUser", getTriggerUser())
            .append("parameters", getParameters())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
} 