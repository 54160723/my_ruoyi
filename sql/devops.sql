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

-- 环境管理表
DROP TABLE IF EXISTS devops_environment;
CREATE TABLE devops_environment (
  env_id           BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '环境ID',
  env_name         VARCHAR(50)     NOT NULL                   COMMENT '环境名称',
  env_key          VARCHAR(50)     NOT NULL                   COMMENT '环境标识',
  env_type         CHAR(1)         NOT NULL                   COMMENT '环境类型（1开发 2测试 3预发 4生产）',
  jenkins_url      VARCHAR(255)    NOT NULL                   COMMENT 'Jenkins地址',
  jenkins_user     VARCHAR(50)     NOT NULL                   COMMENT 'Jenkins用户名',
  jenkins_token    VARCHAR(100)    NOT NULL                   COMMENT 'Jenkins Token',
  docker_registry  VARCHAR(255)    NOT NULL                   COMMENT 'Docker仓库地址',
  docker_user      VARCHAR(50)     NOT NULL                   COMMENT 'Docker用户名',
  docker_password  VARCHAR(100)    NOT NULL                   COMMENT 'Docker密码',
  status           CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  create_by        VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time      DATETIME                                   COMMENT '创建时间',
  update_by        VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time      DATETIME                                   COMMENT '更新时间',
  remark           VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (env_id)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='环境管理表';

-- 初始化-环境管理表数据
INSERT INTO devops_environment VALUES(1, '开发环境', 'dev', '1', 'http://jenkins-dev.example.com', 'jenkins', 'dev-token', 'registry-dev.example.com', 'docker', 'dev-password', '0', 'admin', sysdate(), '', null, '开发环境配置');
INSERT INTO devops_environment VALUES(2, '测试环境', 'test', '2', 'http://jenkins-test.example.com', 'jenkins', 'test-token', 'registry-test.example.com', 'docker', 'test-password', '0', 'admin', sysdate(), '', null, '测试环境配置');

-- 任务配置表
DROP TABLE IF EXISTS devops_task;
CREATE TABLE devops_task (
  task_id           BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '任务ID',
  service_id        BIGINT(20)      NOT NULL                   COMMENT '服务ID',
  task_name         VARCHAR(50)     NOT NULL                   COMMENT '任务名称',
  task_type         VARCHAR(20)     NOT NULL                   COMMENT '任务类型（SHELL_SCRIPT、PYTHON_SCRIPT、DOCKER_BUILD）',
  task_params       TEXT            NOT NULL                   COMMENT '任务参数（JSON格式）',
  work_dir          VARCHAR(255)                               COMMENT '工作目录',
  task_order        INT(4)           NOT NULL                   COMMENT '任务顺序',
  status            CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  create_by         VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time       DATETIME                                   COMMENT '创建时间',
  update_by         VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time       DATETIME                                   COMMENT '更新时间',
  remark            VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (task_id)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='任务配置表';

-- 添加外键约束
ALTER TABLE devops_task ADD CONSTRAINT fk_task_service FOREIGN KEY (service_id) REFERENCES devops_service (service_id) ON DELETE CASCADE;

-- 初始化-任务配置表数据
INSERT INTO devops_task VALUES(1, 1, '代码编译', 'SHELL_SCRIPT', '{"script": "mvn clean package -DskipTests"}', '/app', 1, '0', 'admin', sysdate(), '', null, 'Maven编译');
INSERT INTO devops_task VALUES(2, 1, '单元测试', 'SHELL_SCRIPT', '{"script": "mvn test"}', '/app', 2, '0', 'admin', sysdate(), '', null, '运行单元测试');
INSERT INTO devops_task VALUES(3, 1, '构建Docker镜像', 'DOCKER_BUILD', '{"dockerfile": "Dockerfile", "context": ".", "image": "myapp", "tag": "latest"}', '/app', 3, '0', 'admin', sysdate(), '', null, '构建Docker镜像');

-- Jenkins配置表
DROP TABLE IF EXISTS devops_jenkins;
CREATE TABLE devops_jenkins (
  jenkins_id           BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT 'Jenkins配置ID',
  jenkins_name         VARCHAR(50)     NOT NULL                   COMMENT 'Jenkins名称',
  jenkins_url          VARCHAR(200)    NOT NULL                   COMMENT 'Jenkins地址',
  jenkins_user         VARCHAR(50)     NOT NULL                   COMMENT 'Jenkins用户名',
  jenkins_token        VARCHAR(100)    NOT NULL                   COMMENT 'Jenkins API Token',
  status               CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  create_by            VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time          DATETIME                                   COMMENT '创建时间',
  update_by            VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time          DATETIME                                   COMMENT '更新时间',
  remark               VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (jenkins_id)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='Jenkins配置表';

-- 流水线配置表
DROP TABLE IF EXISTS devops_pipeline;
CREATE TABLE devops_pipeline (
  pipeline_id          BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '流水线ID',
  service_id           BIGINT(20)      NOT NULL                   COMMENT '服务ID',
  pipeline_name        VARCHAR(50)     NOT NULL                   COMMENT '流水线名称',
  jenkins_id           BIGINT(20)      NOT NULL                   COMMENT 'Jenkins配置ID',
  jenkins_job          VARCHAR(100)    NOT NULL                   COMMENT 'Jenkins任务名称',
  pipeline_type        CHAR(1)         DEFAULT '0'                COMMENT '流水线类型（0自动生成 1手动配置）',
  pipeline_script      TEXT            DEFAULT NULL               COMMENT '流水线脚本',
  scm_type             VARCHAR(20)     DEFAULT NULL               COMMENT '代码仓库类型',
  scm_url              VARCHAR(200)    DEFAULT NULL               COMMENT '代码仓库地址',
  scm_branch           VARCHAR(50)     DEFAULT NULL               COMMENT '代码分支',
  status               CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  create_by            VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time          DATETIME                                   COMMENT '创建时间',
  update_by            VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time          DATETIME                                   COMMENT '更新时间',
  remark               VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (pipeline_id)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='流水线配置表';

-- 初始化Jenkins配置数据
INSERT INTO devops_jenkins VALUES(1, '默认Jenkins', 'http://localhost:8080', 'admin', 'your-api-token', '0', 'admin', sysdate(), '', null, '默认Jenkins服务器');

-- 初始化流水线配置数据
INSERT INTO devops_pipeline VALUES(1, 1, '示例流水线', 1, 'example-pipeline', '0', null, 'git', 'https://github.com/example/repo.git', 'main', '0', 'admin', sysdate(), '', null, '示例流水线配置'); 