package com.ruoyi.quartz.task;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.system.domain.DevopsPipelineHistory;
import com.ruoyi.system.domain.DevopsJenkins;
import com.ruoyi.system.service.IDevopsPipelineHistoryService;
import com.ruoyi.system.service.IDevopsJenkinsService;
import com.ruoyi.system.service.JenkinsClientService;
import com.ruoyi.common.utils.DateUtils;
import com.alibaba.fastjson2.JSONObject;
import java.util.Date;

/**
 * 流水线状态更新定时任务
 * 
 * @author ruoyi
 */
@Component("pipelineStatusTask")
public class PipelineStatusTask
{
    @Autowired
    private IDevopsPipelineHistoryService pipelineHistoryService;

    @Autowired
    private IDevopsJenkinsService jenkinsService;

    @Autowired
    private JenkinsClientService jenkinsClientService;

    /**
     * 更新流水线执行状态
     */
    public void updatePipelineStatus()
    {
        try {
            // 查询所有未完成的流水线执行记录
            DevopsPipelineHistory query = new DevopsPipelineHistory();
            query.setStatus("0"); // 排队中
            List<DevopsPipelineHistory> queuedList = pipelineHistoryService.selectDevopsPipelineHistoryList(query);
            query.setStatus("1"); // 运行中
            List<DevopsPipelineHistory> runningList = pipelineHistoryService.selectDevopsPipelineHistoryList(query);

            // 更新排队中的记录
            for (DevopsPipelineHistory history : queuedList) {
                updateHistoryStatus(history);
            }

            // 更新运行中的记录
            for (DevopsPipelineHistory history : runningList) {
                updateHistoryStatus(history);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新单条执行记录状态
     */
    private void updateHistoryStatus(DevopsPipelineHistory history)
    {
        try {
            // 获取Jenkins配置
            DevopsJenkins jenkins = jenkinsService.selectDevopsJenkinsByJenkinsId(history.getJenkinsId());
            if (jenkins == null) {
                return;
            }

            // 获取Jenkins构建状态
            JSONObject buildInfo = jenkinsClientService.getJobBuildStatus(jenkins, history.getJenkinsJob(), history.getBuildNumber());
            if (buildInfo == null || buildInfo.getString("result") == null) {
                return;
            }

            // 更新状态
            String status;
            String result = buildInfo.getString("result");

            if (buildInfo.getBooleanValue("building")) {
                status = "1"; // 运行中
            } else {
                switch (result) {
                    case "SUCCESS":
                        status = "2"; // 成功
                        break;
                    case "FAILURE":
                        status = "3"; // 失败
                        break;
                    case "ABORTED":
                        status = "4"; // 中止
                        break;
                    case "UNSTABLE":
                        status = "5"; // 不稳定
                        break;
                    default:
                        status = "0"; // 未知状态，保留排队中或设置为其他状态
                }
            }

            // 如果状态发生变化，更新记录
            if (!status.equals(history.getStatus())) {
                history.setStatus(status);
                if (!buildInfo.getBooleanValue("building")) {
                    history.setEndTime(DateUtils.getNowDate());
                    if (history.getStartTime() != null) {
                        long duration = history.getEndTime().getTime() - history.getStartTime().getTime();
                        history.setDuration(duration);
                    }
                }
                pipelineHistoryService.updateDevopsPipelineHistory(history);
            } else if (status.equals("1") && history.getStartTime() == null) {
                history.setStartTime(DateUtils.getNowDate());
                pipelineHistoryService.updateDevopsPipelineHistory(history);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 