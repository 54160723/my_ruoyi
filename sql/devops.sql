-- ----------------------------
-- DevOps项目表
-- ----------------------------
DROP TABLE IF EXISTS `devops_project`;
CREATE TABLE `devops_project` (
    `project_id`        BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '项目ID',
    `project_name`      VARCHAR(50)     NOT NULL                   COMMENT '项目名称',
    `project_key`       VARCHAR(50)     NOT NULL                   COMMENT '项目标识',
    `description`       VARCHAR(500)    DEFAULT NULL               COMMENT '项目描述',
    `git_url`          VARCHAR(200)    DEFAULT NULL               COMMENT 'Git仓库地址',
    `create_by`        VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
    `create_time`      DATETIME                                   COMMENT '创建时间',
    `update_by`        VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
    `update_time`      DATETIME                                   COMMENT '更新时间',
    `remark`           VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
    PRIMARY KEY (`project_id`),
    UNIQUE KEY `uk_project_key` (`project_key`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='DevOps项目表';

-- ----------------------------
-- 初始化-DevOps项目表数据
-- ----------------------------
INSERT INTO `devops_project` VALUES (1, '示例项目', 'SAMPLE', '这是一个示例项目', 'https://github.com/example/sample.git', 'admin', sysdate(), '', null, '示例项目');

-- ----------------------------
-- DevOps服务表
-- ----------------------------
DROP TABLE IF EXISTS `devops_service`;
CREATE TABLE `devops_service` (
    `service_id`       BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '服务ID',
    `project_id`       BIGINT(20)      NOT NULL                   COMMENT '所属项目ID',
    `service_name`     VARCHAR(50)     NOT NULL                   COMMENT '服务名称',
    `service_key`      VARCHAR(50)     NOT NULL                   COMMENT '服务标识',
    `git_branch`       VARCHAR(100)    DEFAULT NULL               COMMENT 'Git分支',
    `build_type`       VARCHAR(20)     NOT NULL                   COMMENT '构建类型(Maven/Node/Docker)',
    `build_command`    VARCHAR(500)    DEFAULT NULL               COMMENT '构建命令',
    `dockerfile_path`  VARCHAR(200)    DEFAULT NULL               COMMENT 'Dockerfile路径',
    `create_by`        VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
    `create_time`      DATETIME                                   COMMENT '创建时间',
    `update_by`        VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
    `update_time`      DATETIME                                   COMMENT '更新时间',
    `remark`           VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
    PRIMARY KEY (`service_id`),
    UNIQUE KEY `uk_project_service_key` (`project_id`, `service_key`),
    CONSTRAINT `fk_service_project` FOREIGN KEY (`project_id`) REFERENCES `devops_project` (`project_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='DevOps服务表';

-- ----------------------------
-- 初始化-DevOps服务表数据
-- ----------------------------
INSERT INTO `devops_service` VALUES (1, 1, '示例服务', 'SAMPLE-SERVICE', 'main', 'Maven', 'mvn clean package', 'Dockerfile', 'admin', sysdate(), '', null, '示例服务'); 