-- 流水线模板类型字典数据
INSERT INTO sys_dict_type(dict_name, dict_type, status, create_by, remark) VALUES ('模板类型', 'template_type', '0', 'admin', '流水线模板类型');

INSERT INTO sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES (1, '基础模板', '0', 'template_type', '0', 'admin', '系统预设的基础模板');
INSERT INTO sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES (2, '自定义模板', '1', 'template_type', '0', 'admin', '用户自定义的模板'); 