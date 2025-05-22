package com.ruoyi.system.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.DevopsJenkins;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Map;

/**
 * Jenkins客户端服务接口
 */
public interface JenkinsClientService {
    
    /**
     * 触发Jenkins构建
     * 
     * @param jenkins Jenkins配置
     * @param jobName 任务名称
     * @param parameters 构建参数
     * @return 构建URL
     */
    String triggerBuild(DevopsJenkins jenkins, String jobName, Map<String, String> parameters);

    /**
     * 获取构建日志
     * 
     * @param jenkins Jenkins配置
     * @param jobName 任务名称
     * @param buildNumber 构建编号
     * @return 构建日志
     */
    String getBuildLog(DevopsJenkins jenkins, String jobName, Integer buildNumber);

    /**
     * 获取构建状态
     * 
     * @param jenkins Jenkins配置
     * @param jobName 任务名称
     * @param buildNumber 构建编号
     * @return 构建状态（0排队中 1运行中 2成功 3失败）
     */
    String getBuildStatus(DevopsJenkins jenkins, String jobName, Integer buildNumber);

    /**
     * 获取Jenkins Job的最新构建信息
     *
     * @param jenkins Jenkins配置
     * @param jobName 任务名称
     * @return 最新构建信息的JSONObject，如果不存在则返回null
     */
    JSONObject getLastBuildInfo(DevopsJenkins jenkins, String jobName);
} 