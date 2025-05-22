-- ----------------------------
-- DevOps 流水线模板表
-- ----------------------------
DROP TABLE IF EXISTS devops_pipeline_template;
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

-- 初始数据
INSERT INTO devops_pipeline_template (template_name, template_type, template_content, parameters, description, create_by, create_time) VALUES (
'基础Java应用模板', 'basic', 'pipeline { agent any stages { stage(\'Build\') { steps { echo \'Building Java application...\' } } stage(\'Test\') { steps { echo \'Running tests...\' } } stage(\'Deploy\') { steps { echo \'Deploying Java application...\' } } } }', NULL, '适用于Java应用的流水线模板', 'admin', sysdate());

INSERT INTO devops_pipeline_template (template_name, template_type, template_content, parameters, description, create_by, create_time) VALUES (
'基础Node.js应用模板', 'basic', 'pipeline { agent any stages { stage(\'Build\') { steps { echo \'Building Node.js application...\' } } stage(\'Test\') { steps { echo \'Running tests...\' } } stage(\'Deploy\') { steps { echo \'Deploying Node.js application...\' } } } }', NULL, '适用于Node.js应用的流水线模板', 'admin', sysdate()); 