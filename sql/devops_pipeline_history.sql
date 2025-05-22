-- 创建流水线执行历史表
CREATE TABLE `devops_pipeline_history` (
  `history_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '历史记录ID',
  `pipeline_id` bigint(20) NOT NULL COMMENT '流水线ID',
  `jenkins_job` varchar(100) NOT NULL COMMENT 'Jenkins任务名称',
  `build_number` int(11) NOT NULL COMMENT '构建编号',
  `build_url` varchar(500) DEFAULT NULL COMMENT '构建URL',
  `status` varchar(20) NOT NULL COMMENT '状态（0排队中 1运行中 2成功 3失败）',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `duration` bigint(20) DEFAULT NULL COMMENT '持续时间(毫秒)',
  `trigger_user` varchar(64) DEFAULT NULL COMMENT '触发用户',
  `parameters` varchar(2000) DEFAULT NULL COMMENT '构建参数(JSON格式)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`history_id`),
  KEY `idx_pipeline_id` (`pipeline_id`),
  KEY `idx_jenkins_job` (`jenkins_job`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='流水线执行历史'; 