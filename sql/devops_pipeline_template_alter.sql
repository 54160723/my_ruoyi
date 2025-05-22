-- 添加 parameters 字段到 devops_pipeline_template 表
ALTER TABLE devops_pipeline_template ADD COLUMN parameters text COMMENT '模板参数定义 (JSON格式)'; 