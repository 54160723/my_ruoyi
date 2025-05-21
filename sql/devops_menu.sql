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