package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DevopsPipelineTemplateMapper;
import com.ruoyi.system.domain.DevopsPipelineTemplate;
import com.ruoyi.system.service.IDevopsPipelineTemplateService;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.mapper.DevopsPipelineTemplateVersionMapper;
import com.ruoyi.system.domain.DevopsPipelineTemplateVersion;
import com.ruoyi.common.utils.DateUtils;

/**
 * 流水线模板Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class DevopsPipelineTemplateServiceImpl implements IDevopsPipelineTemplateService 
{
    @Autowired
    private DevopsPipelineTemplateMapper devopsPipelineTemplateMapper;

    @Autowired
    private DevopsPipelineTemplateVersionMapper devopsPipelineTemplateVersionMapper;

    /**
     * 查询流水线模板
     * 
     * @param templateId 流水线模板主键
     * @return 流水线模板
     */
    @Override
    public DevopsPipelineTemplate selectDevopsPipelineTemplateByTemplateId(Long templateId)
    {
        return devopsPipelineTemplateMapper.selectDevopsPipelineTemplateByTemplateId(templateId);
    }

    /**
     * 查询流水线模板列表
     * 
     * @param devopsPipelineTemplate 流水线模板
     * @return 流水线模板集合
     */
    @Override
    public List<DevopsPipelineTemplate> selectDevopsPipelineTemplateList(DevopsPipelineTemplate devopsPipelineTemplate)
    {
        return devopsPipelineTemplateMapper.selectDevopsPipelineTemplateList(devopsPipelineTemplate);
    }

    /**
     * 新增流水线模板
     * 
     * @param devopsPipelineTemplate 流水线模板
     * @return 结果
     */
    @Override
    public int insertDevopsPipelineTemplate(DevopsPipelineTemplate devopsPipelineTemplate)
    {
        return devopsPipelineTemplateMapper.insertDevopsPipelineTemplate(devopsPipelineTemplate);
    }

    /**
     * 修改流水线模板
     * 
     * @param devopsPipelineTemplate 流水线模板
     * @return 结果
     */
    @Override
    public int updateDevopsPipelineTemplate(DevopsPipelineTemplate devopsPipelineTemplate)
    {
        // 查询当前模板信息
        DevopsPipelineTemplate oldTemplate = selectDevopsPipelineTemplateByTemplateId(devopsPipelineTemplate.getTemplateId());
        if (oldTemplate == null) {
            throw new RuntimeException("模板不存在");
        }

        // 创建新的版本记录
        DevopsPipelineTemplateVersion newVersion = new DevopsPipelineTemplateVersion();
        newVersion.setTemplateId(oldTemplate.getTemplateId());
        newVersion.setTemplateContent(oldTemplate.getTemplateContent());
        newVersion.setParameters(oldTemplate.getParameters());
        // 生成版本号，简单实现为当前时间戳
        newVersion.setVersionNumber(String.valueOf(System.currentTimeMillis())); // 可以根据需求生成更友好的版本号
        newVersion.setCreateBy(devopsPipelineTemplate.getUpdateBy()); // 使用更新者作为版本创建者
        newVersion.setCreateTime(DateUtils.getNowDate());

        devopsPipelineTemplateVersionMapper.insertDevopsPipelineTemplateVersion(newVersion);

        // 更新主模板信息
        return devopsPipelineTemplateMapper.updateDevopsPipelineTemplate(devopsPipelineTemplate);
    }

    /**
     * 批量删除流水线模板
     * 
     * @param templateIds 需要删除的流水线模板主键
     * @return 结果
     */
    @Override
    public int deleteDevopsPipelineTemplateByTemplateIds(Long[] templateIds)
    {
        return devopsPipelineTemplateMapper.deleteDevopsPipelineTemplateByTemplateIds(templateIds);
    }

    /**
     * 删除流水线模板信息
     * 
     * @param templateId 流水线模板主键
     * @return 结果
     */
    @Override
    public int deleteDevopsPipelineTemplateByTemplateId(Long templateId)
    {
        return devopsPipelineTemplateMapper.deleteDevopsPipelineTemplateByTemplateId(templateId);
    }

    /**
     * 根据模板生成流水线脚本
     * 
     * @param templateId 模板ID
     * @param parameters 参数
     * @return 流水线脚本
     */
    @Override
    public String generatePipelineScript(Long templateId, Map<String, String> parameters)
    {
        DevopsPipelineTemplate template = selectDevopsPipelineTemplateByTemplateId(templateId);
        if (template == null)
        {
            throw new RuntimeException("模板不存在");
        }

        String script = template.getTemplateContent();
        String parameterDefinitionsJson = template.getParameters();

        // 解析模板参数定义并进行验证
        if (StringUtils.isNotEmpty(parameterDefinitionsJson)) {
            try {
                JSONArray parameterDefinitions = JSON.parseArray(parameterDefinitionsJson);
                for (int i = 0; i < parameterDefinitions.size(); i++) {
                    JSONObject paramDef = parameterDefinitions.getJSONObject(i);
                    String paramName = paramDef.getString("name");
                    boolean required = paramDef.getBoolean("required");
                    String defaultValue = paramDef.getString("defaultValue");

                    // 检查必填参数
                    if (required && (parameters == null || !parameters.containsKey(paramName) || StringUtils.isEmpty(parameters.get(paramName)))) {
                        throw new RuntimeException("缺少必填参数: " + paramName);
                    }

                    // 应用默认值
                    if (parameters != null && !parameters.containsKey(paramName) && StringUtils.isNotEmpty(defaultValue)) {
                        parameters.put(paramName, defaultValue);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("解析模板参数定义失败或参数验证失败: " + e.getMessage(), e);
            }
        }

        // 替换模板中的占位符
        if (parameters != null)
        {
            for (Map.Entry<String, String> entry : parameters.entrySet())
            {
                // 使用更安全的替换方式，避免意外替换
                script = script.replace("${{" + entry.getKey() + "}}", entry.getValue());
            }
        }

        return script;
    }

    /**
     * 查询流水线模板版本列表
     *
     * @param templateId 模板ID
     * @return 流水线模板版本集合
     */
    @Override
    public List<DevopsPipelineTemplateVersion> selectDevopsPipelineTemplateVersionList(Long templateId) {
        DevopsPipelineTemplateVersion query = new DevopsPipelineTemplateVersion();
        query.setTemplateId(templateId);
        return devopsPipelineTemplateVersionMapper.selectDevopsPipelineTemplateVersionList(query);
    }

    /**
     * 恢复流水线模板版本
     *
     * @param versionId 版本ID
     * @return 结果
     */
    @Override
    public int restorePipelineTemplateVersion(Long versionId) {
        // 1. 查询历史版本信息
        DevopsPipelineTemplateVersion version = devopsPipelineTemplateVersionMapper.selectDevopsPipelineTemplateVersionByVersionId(versionId);
        if (version == null) {
            throw new RuntimeException("版本不存在");
        }

        // 2. 查询当前模板信息
        DevopsPipelineTemplate template = selectDevopsPipelineTemplateByTemplateId(version.getTemplateId());
        if (template == null) {
            throw new RuntimeException("模板不存在");
        }

        // 3. 创建新的版本记录（保存当前版本）
        DevopsPipelineTemplateVersion newVersion = new DevopsPipelineTemplateVersion();
        newVersion.setTemplateId(template.getTemplateId());
        newVersion.setTemplateContent(template.getTemplateContent());
        newVersion.setParameters(template.getParameters());
        newVersion.setVersionNumber(String.valueOf(System.currentTimeMillis()));
        newVersion.setCreateBy(template.getUpdateBy());
        newVersion.setCreateTime(DateUtils.getNowDate());
        devopsPipelineTemplateVersionMapper.insertDevopsPipelineTemplateVersion(newVersion);

        // 4. 更新主模板内容为历史版本内容
        template.setTemplateContent(version.getTemplateContent());
        template.setParameters(version.getParameters());
        template.setUpdateBy(version.getCreateBy());
        template.setUpdateTime(DateUtils.getNowDate());

        return devopsPipelineTemplateMapper.updateDevopsPipelineTemplate(template);
    }
} 