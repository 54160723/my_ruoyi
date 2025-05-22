package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.DevopsJenkins;
import com.ruoyi.system.service.JenkinsClientService;

/**
 * Jenkins客户端服务实现类
 */
@Service
public class JenkinsClientServiceImpl implements JenkinsClientService {

    @Override
    public String triggerBuild(DevopsJenkins jenkins, String jobName, Map<String, String> parameters) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 构建触发URL
            String url = jenkins.getJenkinsUrl() + "/job/" + jobName + "/build";
            
            // 创建POST请求
            HttpPost httpPost = new HttpPost(url);
            
            // 设置认证信息
            String auth = jenkins.getJenkinsUser() + ":" + jenkins.getJenkinsToken();
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
            httpPost.setHeader("Authorization", "Basic " + encodedAuth);
            
            // 设置请求参数
            if (parameters != null && !parameters.isEmpty()) {
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("parameter", parameters);
                StringEntity entity = new StringEntity(jsonParams.toString(), "UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            
            // 发送请求
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 201) {
                    // 获取构建URL
                    String location = response.getFirstHeader("Location").getValue();
                    return location;
                } else {
                    throw new RuntimeException("触发构建失败，状态码：" + statusCode);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("触发构建失败：" + e.getMessage());
        }
    }

    @Override
    public String getBuildLog(DevopsJenkins jenkins, String jobName, Integer buildNumber) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 构建日志URL
            String url = jenkins.getJenkinsUrl() + "/job/" + jobName + "/" + buildNumber + "/consoleText";
            
            // 创建GET请求
            HttpGet httpGet = new HttpGet(url);
            
            // 设置认证信息
            String auth = jenkins.getJenkinsUser() + ":" + jenkins.getJenkinsToken();
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
            httpGet.setHeader("Authorization", "Basic " + encodedAuth);
            
            // 发送请求
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : "";
                } else {
                    throw new RuntimeException("获取构建日志失败，状态码：" + statusCode);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("获取构建日志失败：" + e.getMessage());
        }
    }

    @Override
    public String getBuildStatus(DevopsJenkins jenkins, String jobName, Integer buildNumber) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 构建状态URL
            String url = jenkins.getJenkinsUrl() + "/job/" + jobName + "/" + buildNumber + "/api/json";
            
            // 创建GET请求
            HttpGet httpGet = new HttpGet(url);
            
            // 设置认证信息
            String auth = jenkins.getJenkinsUser() + ":" + jenkins.getJenkinsToken();
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
            httpGet.setHeader("Authorization", "Basic " + encodedAuth);
            
            // 发送请求
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String json = EntityUtils.toString(entity);
                        JSONObject buildInfo = JSON.parseObject(json);
                        
                        // 检查构建是否在队列中
                        if (buildInfo.getBoolean("building")) {
                            return "1"; // 运行中
                        }
                        
                        // 检查构建结果
                        String result = buildInfo.getString("result");
                        if (result != null) {
                            switch (result) {
                                case "SUCCESS":
                                    return "2"; // 成功
                                case "FAILURE":
                                case "ABORTED":
                                case "UNSTABLE":
                                    return "3"; // 失败
                                default:
                                    return "0"; // 排队中
                            }
                        }
                    }
                    return "0"; // 默认排队中
                } else {
                    throw new RuntimeException("获取构建状态失败，状态码：" + statusCode);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("获取构建状态失败：" + e.getMessage());
        }
    }

    @Override
    public JSONObject getLastBuildInfo(DevopsJenkins jenkins, String jobName) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 构建获取最新构建信息的URL
            String url = jenkins.getJenkinsUrl() + "/job/" + jobName + "/lastBuild/api/json";

            // 创建GET请求
            HttpGet httpGet = new HttpGet(url);

            // 设置认证信息
            String auth = jenkins.getJenkinsUser() + ":" + jenkins.getJenkinsToken();
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
            httpGet.setHeader("Authorization", "Basic " + encodedAuth);

            // 发送请求
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String json = EntityUtils.toString(entity);
                        return JSON.parseObject(json);
                    }
                } else if (statusCode == 404) {
                    // Job或Last Build不存在
                    return null;
                } else {
                    throw new RuntimeException("获取最新构建信息失败，状态码：" + statusCode);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("获取最新构建信息失败：" + e.getMessage());
        }
        return null; // Should not reach here
    }
} 