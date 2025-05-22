package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DevopsPipelineMapper;
import com.ruoyi.system.domain.DevopsPipeline;
import com.ruoyi.system.domain.DevopsJenkins;
import com.ruoyi.system.domain.DevopsPipelineHistory;
import com.ruoyi.system.service.IDevopsPipelineService;
import com.ruoyi.system.service.IDevopsJenkinsService;
import com.ruoyi.system.service.IDevopsPipelineHistoryService;
import com.ruoyi.system.service.JenkinsClientService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import java.util.Date;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import java.net.URI;

/**
 * 流水线配置Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class DevopsPipelineServiceImpl implements IDevopsPipelineService 
{
    @Autowired
    private DevopsPipelineMapper devopsPipelineMapper;

    @Autowired
    private IDevopsJenkinsService jenkinsService;

    @Autowired
    private IDevopsPipelineHistoryService pipelineHistoryService;

    @Autowired
    private JenkinsClientService jenkinsClientService;

    /**
     * 查询流水线配置
     * 
     * @param pipelineId 流水线配置主键
     * @return 流水线配置
     */
    @Override
    public DevopsPipeline selectDevopsPipelineByPipelineId(Long pipelineId)
    {
        return devopsPipelineMapper.selectDevopsPipelineByPipelineId(pipelineId);
    }

    /**
     * 查询流水线配置列表
     * 
     * @param devopsPipeline 流水线配置
     * @return 流水线配置
     */
    @Override
    public List<DevopsPipeline> selectDevopsPipelineList(DevopsPipeline devopsPipeline)
    {
        return devopsPipelineMapper.selectDevopsPipelineList(devopsPipeline);
    }

    /**
     * 新增流水线配置
     * 
     * @param devopsPipeline 流水线配置
     * @return 结果
     */
    @Override
    public int insertDevopsPipeline(DevopsPipeline devopsPipeline)
    {
        // 创建Jenkins Pipeline Job
        try {
            DevopsJenkins jenkins = jenkinsService.selectDevopsJenkinsByJenkinsId(devopsPipeline.getJenkinsId());
            jenkinsClientService.createOrUpdatePipelineJob(jenkins, devopsPipeline.getJenkinsJob(), devopsPipeline.getPipelineScript());
        } catch (Exception e) {
            // 记录日志而不是直接抛出异常，以便流水线配置能够保存成功
            e.printStackTrace(); // 或者使用日志框架记录
            // 可以考虑设置一个状态或者标记来指示 Jenkins Job 创建失败
        }
        return devopsPipelineMapper.insertDevopsPipeline(devopsPipeline);
    }

    /**
     * 修改流水线配置
     * 
     * @param devopsPipeline 流水线配置
     * @return 结果
     */
    @Override
    public int updateDevopsPipeline(DevopsPipeline devopsPipeline)
    {
        // 更新Jenkins Pipeline Job
        try {
            DevopsJenkins jenkins = jenkinsService.selectDevopsJenkinsByJenkinsId(devopsPipeline.getJenkinsId());
            // 在更新前，可以先判断 Job 是否已存在，如果不存在则创建
            jenkinsClientService.createOrUpdatePipelineJob(jenkins, devopsPipeline.getJenkinsJob(), devopsPipeline.getPipelineScript());
        } catch (Exception e) {
            // 记录日志而不是直接抛出异常
            e.printStackTrace(); // 或者使用日志框架记录
            // 可以考虑设置一个状态或者标记来指示 Jenkins Job 更新失败
        }
        return devopsPipelineMapper.updateDevopsPipeline(devopsPipeline);
    }

    /**
     * 批量删除流水线配置
     * 
     * @param pipelineIds 需要删除的流水线配置主键
     * @return 结果
     */
    @Override
    public int deleteDevopsPipelineByPipelineIds(Long[] pipelineIds)
    {
        return devopsPipelineMapper.deleteDevopsPipelineByPipelineIds(pipelineIds);
    }

    /**
     * 删除流水线配置信息
     * 
     * @param pipelineId 流水线配置主键
     * @return 结果
     */
    @Override
    public int deleteDevopsPipelineByPipelineId(Long pipelineId)
    {
        return devopsPipelineMapper.deleteDevopsPipelineByPipelineId(pipelineId);
    }

    /**
     * 执行流水线
     * 
     * @param pipelineId 流水线配置主键
     * @return 结果
     */
    @Override
    public boolean executePipeline(Long pipelineId)
    {
        try {
            // 获取流水线配置
            DevopsPipeline pipeline = selectDevopsPipelineByPipelineId(pipelineId);
            if (pipeline == null) {
                throw new RuntimeException("流水线配置不存在");
            }

            // 获取Jenkins配置
            DevopsJenkins jenkins = jenkinsService.selectDevopsJenkinsByJenkinsId(pipeline.getJenkinsId());
            if (jenkins == null) {
                throw new RuntimeException("Jenkins配置不存在");
            }

            // 准备构建参数 (可以根据实际需求从前端或其他地方获取)
            Map<String, String> parameters = new HashMap<>();
            // 示例参数，实际应根据模板或用户输入生成
            // parameters.put("BRANCH", pipeline.getScmBranch());
            // parameters.put("SCM_URL", pipeline.getScmUrl());

            // 触发Jenkins构建
            HttpResponse response = jenkinsClientService.triggerPipelineJob(jenkins, pipeline.getJenkinsJob(), parameters);

            if (response != null && response.getStatusLine().getStatusCode() == 201) {
                // Jenkins 返回 201 Created，表示触发成功
                String location = response.getFirstHeader("Location").getValue();

                // 记录执行历史，初始状态为排队中
                DevopsPipelineHistory history = new DevopsPipelineHistory();
                history.setPipelineId(pipelineId);
                history.setJenkinsJob(pipeline.getJenkinsJob());
                // 构建号和构建 URL 后续通过定时任务或 webhook 更新
                history.setBuildNumber(-1); // 初始未知构建号
                history.setBuildUrl(location); // 记录 Jenkins 返回的 location URL
                history.setStatus("0"); // 0:排队中
                history.setStartTime(DateUtils.getNowDate()); // setStartTime now accepts Date
                history.setTriggerUser(SecurityUtils.getUsername());
                // 记录触发时的参数，如果parameters为null，保存空JSON字符串
                history.setParameters(parameters != null ? JSON.toJSONString(parameters) : "{}");

                pipelineHistoryService.insertDevopsPipelineHistory(history);

                return true;
            } else {
                // 触发失败
                 String errorMsg = "触发Jenkins构建失败";
                if (response != null) { // Check for null response
                     errorMsg += ": Status Code " + response.getStatusLine().getStatusCode();
                    if (response.getEntity() != null) {
                         errorMsg += ", Response: " + EntityUtils.toString(response.getEntity());
                    }
                }
                throw new RuntimeException(errorMsg);
            }

        } catch (Exception e) {
            e.printStackTrace(); // 记录日志
            throw new RuntimeException("执行流水线失败: " + e.getMessage());
        }
    }

    @Override
    public void triggerPipeline(Long pipelineId, Map<String, String> parameters) {
        // 考虑是否保留此方法或与executePipeline合并，executePipeline已包含更多逻辑
        // 如果保留，需要确保历史记录和状态更新逻辑与executePipeline一致
        executePipeline(pipelineId); // 暂时直接调用executePipeline
    }

    @Override
    public int triggerPipeline(Long pipelineId) {
        DevopsPipeline pipeline = devopsPipelineMapper.selectDevopsPipelineByPipelineId(pipelineId);
        if (pipeline == null) {
            throw new RuntimeException("流水线不存在");
        }

        // 获取Jenkins配置
        DevopsJenkins jenkins = jenkinsService.selectDevopsJenkinsByJenkinsId(pipeline.getJenkinsId());
        if (jenkins == null) {
            throw new RuntimeException("Jenkins配置不存在");
        }

        // 创建历史记录
        DevopsPipelineHistory history = new DevopsPipelineHistory();
        history.setPipelineId(pipelineId);
        history.setJenkinsJob(pipeline.getJenkinsJob());
        history.setBuildNumber(-1); // 初始构建编号
        history.setStatus("0"); // 排队中
        history.setStartTime(DateUtils.getNowDate());
        history.setTriggerUser(SecurityUtils.getUsername());
        history.setParameters("{}");

        // 插入历史记录
        pipelineHistoryService.insertDevopsPipelineHistory(history);

        try {
            // 调用Jenkins API触发构建
            String buildUrl = jenkinsClientService.triggerBuild(jenkins, pipeline.getJenkinsJob(), new HashMap<>());

            // 尝试从Jenkins获取最新的构建信息
            // 注意：这可能无法准确对应刚刚触发的构建，尤其是在短时间内有多次构建的情况下。
            // 一个更可靠的方法是轮询Jenkins队列或使用Webhook。
            JSONObject lastBuildInfo = jenkinsClientService.getLastBuildInfo(jenkins, pipeline.getJenkinsJob());
            if (lastBuildInfo != null) {
                Integer buildNumber = lastBuildInfo.getInteger("number");
                String actualBuildUrl = lastBuildInfo.getString("url");
                if (buildNumber != null) {
                    history.setBuildNumber(buildNumber);
                }
                if (actualBuildUrl != null) {
                     history.setBuildUrl(actualBuildUrl);
                }
            }

            // 更新历史记录状态为运行中（即使构建编号可能还没获取到）
            history.setStatus("1"); // 运行中
            pipelineHistoryService.updateDevopsPipelineHistory(history);

            return 1;
        } catch (Exception e) {
            // 更新历史记录状态为失败
            history.setStatus("3"); // 失败
            history.setEndTime(DateUtils.getNowDate());
            pipelineHistoryService.updateDevopsPipelineHistory(history);
            throw new RuntimeException("执行流水线失败: " + e.getMessage());
        }
    }

    @Override
    public List<DevopsPipelineHistory> selectPipelineHistoryList(DevopsPipelineHistory history) {
        return pipelineHistoryService.selectDevopsPipelineHistoryList(history);
    }

    @Override
    public String getBuildLog(Long historyId) {
        DevopsPipelineHistory history = pipelineHistoryService.selectDevopsPipelineHistoryByHistoryId(historyId);
        if (history == null) {
            throw new RuntimeException("历史记录不存在");
        }

        DevopsPipeline pipeline = devopsPipelineMapper.selectDevopsPipelineByPipelineId(history.getPipelineId());
        if (pipeline == null) {
            throw new RuntimeException("流水线不存在");
        }

        DevopsJenkins jenkins = jenkinsService.selectDevopsJenkinsByJenkinsId(pipeline.getJenkinsId());
        if (jenkins == null) {
            throw new RuntimeException("Jenkins配置不存在");
        }

        try {
            return jenkinsClientService.getBuildLog(jenkins, history.getJenkinsJob(), history.getBuildNumber());
        } catch (Exception e) {
            throw new RuntimeException("获取构建日志失败: " + e.getMessage());
        }
    }

    @Override
    public int refreshBuildStatus(Long historyId) {
        DevopsPipelineHistory history = pipelineHistoryService.selectDevopsPipelineHistoryByHistoryId(historyId);
        if (history == null) {
            throw new RuntimeException("历史记录不存在");
        }

        DevopsPipeline pipeline = devopsPipelineMapper.selectDevopsPipelineByPipelineId(history.getPipelineId());
        if (pipeline == null) {
            throw new RuntimeException("流水线不存在");
        }

        DevopsJenkins jenkins = jenkinsService.selectDevopsJenkinsByJenkinsId(pipeline.getJenkinsId());
        if (jenkins == null) {
            throw new RuntimeException("Jenkins配置不存在");
        }

        try {
            // 获取构建状态
            String status = jenkinsClientService.getBuildStatus(jenkins, history.getJenkinsJob(), history.getBuildNumber());
            
            // 更新历史记录
            history.setStatus(status);
            if ("2".equals(status) || "3".equals(status)) {
                history.setEndTime(DateUtils.getNowDate());
                if (history.getStartTime() != null) {
                    history.setDuration(history.getEndTime().getTime() - history.getStartTime().getTime());
                }
            }
            
            return pipelineHistoryService.updateDevopsPipelineHistory(history);
        } catch (Exception e) {
            throw new RuntimeException("刷新构建状态失败: " + e.getMessage());
        }
    }
} 