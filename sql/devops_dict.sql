-- 构建类型字典数据
insert into sys_dict_type(dict_name, dict_type, status, create_by, remark)
values ('构建类型', 'build_type', '0', 'admin', '服务构建类型');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (1, 'Maven', 'maven', 'build_type', '0', 'admin', 'Maven构建');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (2, 'Gradle', 'gradle', 'build_type', '0', 'admin', 'Gradle构建');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (3, 'Docker', 'docker', 'build_type', '0', 'admin', 'Docker构建');

-- 任务类型字典类型
insert into sys_dict_type(dict_name, dict_type, status, create_by, remark)
values ('任务类型', 'task_type', '0', 'admin', '任务类型列表');

-- 任务类型字典数据
insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, remark)
values (1, 'Shell脚本', 'SHELL_SCRIPT', 'task_type', '', 'primary', 'Y', '0', 'admin', 'Shell脚本任务');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, remark)
values (2, 'Python脚本', 'PYTHON_SCRIPT', 'task_type', '', 'success', 'N', '0', 'admin', 'Python脚本任务');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, remark)
values (3, 'Docker构建', 'DOCKER_BUILD', 'task_type', '', 'info', 'N', '0', 'admin', 'Docker构建任务'); 