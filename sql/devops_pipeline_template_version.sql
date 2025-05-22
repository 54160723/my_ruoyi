-- ----------------------------
-- DevOps 流水线模板版本表
-- ----------------------------
DROP TABLE IF EXISTS devops_pipeline_template_version;
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