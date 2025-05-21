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

-- 流水线类型字典类型
insert into sys_dict_type(dict_name, dict_type, status, create_by, remark)
values ('流水线类型', 'pipeline_type', '0', 'admin', '流水线类型列表');

-- 流水线类型字典数据
insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, remark)
values (1, '自动生成', '0', 'pipeline_type', '', 'primary', 'Y', '0', 'admin', '根据任务配置自动生成流水线');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, remark)
values (2, '手动配置', '1', 'pipeline_type', '', 'success', 'N', '0', 'admin', '手动配置流水线脚本');

-- 代码仓库类型字典类型
insert into sys_dict_type(dict_name, dict_type, status, create_by, remark)
values ('代码仓库类型', 'scm_type', '0', 'admin', '代码仓库类型列表');

-- 代码仓库类型字典数据
insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, remark)
values (1, 'Git', 'git', 'scm_type', '', 'primary', 'Y', '0', 'admin', 'Git代码仓库');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, remark)
values (2, 'SVN', 'svn', 'scm_type', '', 'success', 'N', '0', 'admin', 'SVN代码仓库'); 