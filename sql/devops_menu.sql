-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('DevOps平台', '0', '1', 'devops', null, 1, 0, 'M', '0', '0', '', 'el-icon-s-platform', 'admin', sysdate(), '', null, 'DevOps平台菜单');

-- 获取DevOps平台菜单ID
SELECT @devopsId := LAST_INSERT_ID();

-- 项目管理菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理', @devopsId, '1', 'project', 'system/devops/project/index', 1, 0, 'C', '0', '0', 'system:devops:project:list', 'el-icon-folder', 'admin', sysdate(), '', null, '项目管理菜单');

-- 获取项目管理菜单ID
SELECT @projectId := LAST_INSERT_ID();

-- 项目管理按钮
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理查询', @projectId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:project:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理新增', @projectId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:project:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理修改', @projectId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:project:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理删除', @projectId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:project:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理导出', @projectId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:project:export',       '#', 'admin', sysdate(), '', null, '');

-- 服务定义菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义', @devopsId, '2', 'service', 'system/devops/service/index', 1, 0, 'C', '0', '0', 'system:devops:service:list', 'el-icon-s-operation', 'admin', sysdate(), '', null, '服务定义菜单');

-- 获取服务定义菜单ID
SELECT @serviceId := LAST_INSERT_ID();

-- 服务定义按钮
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义查询', @serviceId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:service:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义新增', @serviceId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:service:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义修改', @serviceId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:service:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义删除', @serviceId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:service:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义导出', @serviceId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:service:export',       '#', 'admin', sysdate(), '', null, '');

-- 环境管理菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('环境管理', @devopsId, '3', 'environment', 'system/devops/environment/index', 1, 0, 'C', '0', '0', 'system:devops:environment:list', 'el-icon-s-management', 'admin', sysdate(), '', null, '环境管理菜单');

-- 获取环境管理菜单ID
SELECT @envId := LAST_INSERT_ID();

-- 环境管理按钮
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('环境管理查询', @envId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:environment:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('环境管理新增', @envId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:environment:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('环境管理修改', @envId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:environment:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('环境管理删除', @envId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:environment:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('环境管理导出', @envId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:environment:export',       '#', 'admin', sysdate(), '', null, '');

-- 任务配置菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务配置', @devopsId, '4', 'task', 'system/devops/task/index', 1, 0, 'C', '0', '0', 'system:devops:task:list', 'el-icon-s-order', 'admin', sysdate(), '', null, '任务配置菜单');

-- 获取任务配置菜单ID
SELECT @taskId := LAST_INSERT_ID();

-- 任务配置按钮
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务配置查询', @taskId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:task:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务配置新增', @taskId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:task:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务配置修改', @taskId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:task:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务配置删除', @taskId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:task:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务配置导出', @taskId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:task:export',       '#', 'admin', sysdate(), '', null, '');

-- Jenkins配置菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Jenkins配置', @devopsId, '5', 'jenkins', 'system/devops/jenkins/index', 1, 0, 'C', '0', '0', 'system:devops:jenkins:list', 'el-icon-s-platform', 'admin', sysdate(), '', null, 'Jenkins配置菜单');

-- 获取Jenkins配置菜单ID
SELECT @jenkinsId := LAST_INSERT_ID();

-- Jenkins配置按钮
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Jenkins配置查询', @jenkinsId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:jenkins:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Jenkins配置新增', @jenkinsId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:jenkins:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Jenkins配置修改', @jenkinsId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:jenkins:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Jenkins配置删除', @jenkinsId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:jenkins:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Jenkins配置导出', @jenkinsId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:jenkins:export',       '#', 'admin', sysdate(), '', null, '');

-- 流水线配置菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线配置', @devopsId, '6', 'pipeline', 'system/devops/pipeline/index', 1, 0, 'C', '0', '0', 'system:devops:pipeline:list', 'el-icon-s-operation', 'admin', sysdate(), '', null, '流水线配置菜单');

-- 获取流水线配置菜单ID
SELECT @pipelineId := LAST_INSERT_ID();

-- 流水线配置按钮
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线配置查询', @pipelineId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:pipeline:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线配置新增', @pipelineId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:pipeline:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线配置修改', @pipelineId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:pipeline:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线配置删除', @pipelineId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:pipeline:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线配置导出', @pipelineId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:pipeline:export',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线执行', @pipelineId, '6',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:pipeline:execute',       '#', 'admin', sysdate(), '', null, '');