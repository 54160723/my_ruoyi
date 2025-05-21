-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('DevOps平台', '0', '1', 'devops', null, 1, 0, 'M', '0', '0', '', 'el-icon-s-platform', 'admin', sysdate(), '', null, 'DevOps平台菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 项目管理菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理', @parentId, '1', 'project', 'system/devops/project/index', 1, 0, 'C', '0', '0', 'system:devops:project:list', 'el-icon-folder', 'admin', sysdate(), '', null, '项目管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 项目管理按钮
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:project:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:project:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:project:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:project:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:project:export',       '#', 'admin', sysdate(), '', null, '');

-- 获取DevOps平台菜单ID
SELECT @devopsId := menu_id from sys_menu where menu_name = 'DevOps平台';

-- 服务定义菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义', @devopsId, '2', 'service', 'system/devops/service/index', 1, 0, 'C', '0', '0', 'system:devops:service:list', 'el-icon-s-operation', 'admin', sysdate(), '', null, '服务定义菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 服务定义按钮
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:service:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:service:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:service:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:service:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('服务定义导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:service:export',       '#', 'admin', sysdate(), '', null, '');