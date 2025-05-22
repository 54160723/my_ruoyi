-- ----------------------------
-- DevOps 流水线模板相关表结构
-- ----------------------------

-- 删除 DevOps 流水线模板版本表
DROP TABLE IF EXISTS devops_pipeline_template_version;

-- 删除 DevOps 流水线模板表
DROP TABLE IF EXISTS devops_pipeline_template;

-- ----------------------------
-- 创建 DevOps 流水线模板表
-- ----------------------------
CREATE TABLE devops_pipeline_template (
  template_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流水线模板ID',
  template_name varchar(100) NOT NULL COMMENT '模板名称',
  template_type varchar(50) NOT NULL COMMENT '模板类型 (如：Java, Node.js, 通用)',
  template_content longtext NOT NULL COMMENT '模板内容 (Jenkinsfile)',
  parameters text COMMENT '模板参数定义 (JSON格式)',
  description varchar(500) DEFAULT NULL COMMENT '模板描述',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (template_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='DevOps流水线模板表';

-- ----------------------------
-- 插入初始数据到 DevOps 流水线模板表
-- ----------------------------
INSERT INTO devops_pipeline_template (template_name, template_type, template_content, parameters, description, create_by, create_time) VALUES (
'基础Java应用模板', 'basic', 'pipeline { agent any stages { stage(\'Build\') { steps { echo \'Building Java application...\' } } stage(\'Test\') { steps { echo \'Running tests...\' } } stage(\'Deploy\') { steps { echo \'Deploying Java application...\' } } } }', NULL, '适用于Java应用的流水线模板', 'admin', sysdate());

INSERT INTO devops_pipeline_template (template_name, template_type, template_content, parameters, description, create_by, create_time) VALUES (
'基础Node.js应用模板', 'basic', 'pipeline { agent any stages { stage(\'Build\') { steps { echo \'Building Node.js application...\' } } stage(\'Test\') { steps { echo \'Running tests...\' } } stage(\'Deploy\') { steps { echo \'Deploying Node.js application...\' } } } }', NULL, '适用于Node.js应用的流水线模板', 'admin', sysdate());

-- ----------------------------
-- 创建 DevOps 流水线模板版本表
-- ----------------------------
CREATE TABLE devops_pipeline_template_version (
  version_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '版本ID',
  template_id bigint(20) NOT NULL COMMENT '流水线模板ID',
  version_number varchar(50) NOT NULL COMMENT '版本号',
  template_content longtext NOT NULL COMMENT '模板内容 (Jenkinsfile)',
  parameters text COMMENT '模板参数定义 (JSON格式)',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (version_id),
  FOREIGN KEY (template_id) REFERENCES devops_pipeline_template(template_id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='DevOps流水线模板版本表'; 