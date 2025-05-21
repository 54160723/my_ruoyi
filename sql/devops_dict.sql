-- 构建类型字典数据
insert into sys_dict_type(dict_name, dict_type, status, create_by, remark)
values ('构建类型', 'build_type', '0', 'admin', '服务构建类型');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (1, 'Maven', 'maven', 'build_type', '0', 'admin', 'Maven构建');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (2, 'Gradle', 'gradle', 'build_type', '0', 'admin', 'Gradle构建');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (3, 'Docker', 'docker', 'build_type', '0', 'admin', 'Docker构建'); 