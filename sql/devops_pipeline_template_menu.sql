-- 流水线模板菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线模板', @devopsId, '7', 'template', 'system/devops/template/index', 1, 0, 'C', '0', '0', 'system:devops:template:list', 'el-icon-document', 'admin', sysdate(), '', null, '流水线模板菜单');

-- 获取流水线模板菜单ID
SELECT @templateId := LAST_INSERT_ID();

-- 流水线模板按钮
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线模板查询', @templateId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:template:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线模板新增', @templateId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:template:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线模板修改', @templateId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:template:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线模板删除', @templateId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:template:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('流水线模板导出', @templateId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:devops:template:export',       '#', 'admin', sysdate(), '', null, ''); 