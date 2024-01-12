-- =====================================================================================================================
-- 基础数据
-- =====================================================================================================================

--
-- 角色
--

insert into sys_role (`id`, `code`, `title`, `label`, `source`, `active`, `created_at`, `created_by`)
values (1, 'SYSTEM_ADMINISTRATOR', 'System Administrator', 'label_role_system_administrator', 1, 1, now(), 1),
       (2, 'ADMINISTRATOR', 'Administrator', 'label_role_administrator', 1, 1, now(), 1),
       (3, 'USER', 'User', 'label_role_user', 1, 1, now(), 1);

--
-- 内置用户
--

insert into sys_user (`id`, `username`, `email`, `mobile_country_code`, `mobile_number`,
                      `display_name`, `birthday`, `active`, `created_at`, `password`)
values (1, 'admin', 'me@elvea.cn', '0086', '13500000000', 'Administrator', now(), 1, now(),
        '$2a$10$MLkjYEPJkO6KNrfUUBld6eWVr1G09nugg5UpIQVUtsQ.3Z9U2lOSK');

--
-- 顶层组织架构
--

insert into sys_organization (`id`, `code`, `label`, `title`, `root_ind`, `default_ind`)
values (1, 'ROOT_ORG', 'label_top_organization', 'All Organization', 1, 1);

--
-- 顶层岗位
--

insert into sys_position (`id`, `code`, `label`, `title`, `root_ind`, `default_ind`)
values (1, 'ROOT_PST', 'label_top_position', 'All Position', 1, 1);

--
-- 内置用户关联数据
--

insert into sys_user_role (`id`, `user_id`, `role_id`, `created_at`)
values (1, 1, 1, now()),
       (2, 1, 2, now()),
       (3, 1, 3, now());

insert into sys_entity_relation (`id`, `ancestor_id`, `entity_id`, `parent_ind`,
                                 `relation_type`, `relation_path`, `relation_index`)
values (1, 1, 1, 1, 'USR_CURRENT_ORG', '1', 1),
       (2, 1, 1, 1, 'USR_CURRENT_PST', '1', 1);

--
-- 语言类型
--

insert into sys_lang (`id`, `code`, `lang`, `country`, `label`, `description`, `default_ind`, `active`)
values (1, 'zh_cn', 'zh', 'cn', 'label_lang_zh_cn', '简体中文', 1, 1),
       (2, 'zh_tw', 'zh', 'tw', 'label_lang_zh_tw', '繁体中文', 0, 1),
       (3, 'en_us', 'en', 'us', 'label_lang_en_us', '美式英语', 0, 1);

--
-- 权限
--

truncate sys_authority;

insert into sys_authority (`id`, `parent_id`, `code`, `title`, `label`, `authority_type`, `sort_order`, `active`)
values
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 组织架构 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1001000000, 0, 'organization', '组织架构', 'authority_organization', 'CATALOG', 88, 1),
    /* 组织架构 */
    (1001001000, 1001000000, 'organization:organization', '组织架构', 'authority_organization_organization', 'MENU', 1, 1),
    (1001001001, 1001001000, 'organization:organization:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1001001002, 1001001000, 'organization:organization:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1001001003, 1001001000, 'organization:organization:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1001001004, 1001001000, 'organization:organization:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    (1001001005, 1001001000, 'organization:organization:import', '导入', 'authority_import', 'RESOURCE', 1, 1),
    (1001001006, 1001001000, 'organization:organization:export', '导出', 'authority_export', 'RESOURCE', 1, 1),
    /* 岗位管理 */
    (1001002000, 1001000000, 'organization:position', '岗位管理', 'authority_organization_position', 'MENU', 2, 1),
    (1001002001, 1001002000, 'organization:position:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1001002002, 1001002000, 'organization:position:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1001002003, 1001002000, 'organization:position:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1001002004, 1001002000, 'organization:position:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    (1001002005, 1001002000, 'organization:position:import', '导入', 'authority_import', 'RESOURCE', 1, 1),
    (1001002006, 1001002000, 'organization:position:export', '导出', 'authority_export', 'RESOURCE', 1, 1),
    /* 用户管理 */
    (1001003000, 1001000000, 'organization:user', '用户管理', 'authority_organization_user', 'MENU', 3, 1),
    (1001003001, 1001003000, 'organization:user:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1001003002, 1001003000, 'organization:user:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1001003003, 1001003000, 'organization:user:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1001003004, 1001003000, 'organization:user:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    (1001003005, 1001003000, 'organization:user:import', '导入', 'authority_import', 'RESOURCE', 1, 1),
    (1001003006, 1001003000, 'organization:user:export', '导出', 'authority_export', 'RESOURCE', 1, 1),
    /* 群组管理 */
    (1001004000, 1001000000, 'organization:group', '群组管理', 'authority_organization_group', 'MENU', 4, 1),
    (1001004001, 1001004000, 'organization:group:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1001004002, 1001004000, 'organization:group:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1001004003, 1001004000, 'organization:group:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1001004004, 1001004000, 'organization:group:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 资源管理 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1002000000, 0, 'resource', '资源管理', 'authority_resource', 'CATALOG', 77, 1),
    /* 字典管理 */
    (1002001000, 1002000000, 'resource:dictionary', '字典管理', 'authority_resource_dictionary', 'MENU', 1, 1),
    (1002001001, 1002001000, 'resource:dictionary:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002001002, 1002001000, 'resource:dictionary:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002001003, 1002001000, 'resource:dictionary:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002001004, 1002001000, 'resource:dictionary:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 标签管理 */
    (1002002000, 1002000000, 'resource:tag', '标签管理', 'authority_resource_tag', 'MENU', 2, 1),
    (1002002001, 1002002000, 'resource:tag:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002002002, 1002002000, 'resource:tag:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002002003, 1002002000, 'resource:tag:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002002004, 1002002000, 'resource:tag:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 地区管理 */
    (1002003000, 1002000000, 'resource:area', '地区管理', 'authority_resource_area', 'MENU', 3, 1),
    (1002003001, 1002003000, 'resource:area:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002003002, 1002003000, 'resource:area:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002003003, 1002003000, 'resource:area:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002003004, 1002003000, 'resource:area:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 附件管理 */
    (1002004000, 1002000000, 'resource:attachment', '附件管理', 'authority_resource_attachment', 'MENU', 4, 1),
    (1002004001, 1002004000, 'resource:attachment:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002004002, 1002004000, 'resource:attachment:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002004003, 1002004000, 'resource:attachment:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002004004, 1002004000, 'resource:attachment:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 多语言文本 */
    (1002005000, 1002000000, 'resource:label', '多语言文本', 'authority_resource_label', 'MENU', 5, 1),
    (1002005001, 1002005000, 'resource:label:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002005002, 1002005000, 'resource:label:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002005003, 1002005000, 'resource:label:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002005004, 1002005000, 'resource:label:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 消息管理 */
    (1002006000, 1002000000, 'resource:message', '消息管理', 'authority_resource_message', 'MENU', 6, 1),
    (1002006001, 1002006000, 'resource:message:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1002006002, 1002006000, 'resource:message:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1002006003, 1002006000, 'resource:message:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1002006004, 1002006000, 'resource:message:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 系统设置 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1003000000, 0, 'system', '系统', 'authority_system', 'CATALOG', 99, 1),
    /* 权限管理 */
    (1003001000, 1003000000, 'system:authority', '权限管理', 'authority_system_authority', 'MENU', 1, 1),
    (1003001001, 1003001000, 'system:authority:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003001002, 1003001000, 'system:authority:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    /* 角色管理 */
    (1003002000, 1003000000, 'system:role', '角色管理', 'authority_system_role', 'MENU', 2, 1),
    (1003002001, 1003002000, 'system:role:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003002002, 1003002000, 'system:role:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1003002003, 1003002000, 'system:role:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1003002004, 1003002000, 'system:role:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 系统设置 */
    (1003003000, 1003000000, 'system:setting', '系统设置', 'authority_system_setting', 'MENU', 3, 1),
    (1003003001, 1003003000, 'system:setting:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003003002, 1003003000, 'system:setting:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    /* 在线用户 */
    (1003004000, 1003000000, 'system:user', '在线用户', 'authority_system_user', 'MENU', 4, 1),
    (1003004001, 1003004000, 'system:user:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003004002, 1003004000, 'system:user:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 系统日志 */
    (1003005000, 1003000000, 'system:logging', '系统日志', 'authority_system_logging', 'MENU', 5, 1),
    (1003005001, 1003005000, 'system:logging:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003005002, 1003005000, 'system:logging:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 定时任务 */
    (1003006000, 1003000000, 'system:task', '定时任务', 'authority_system_task', 'MENU', 6, 1),
    (1003006001, 1003006000, 'system:task:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003006002, 1003006000, 'system:task:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    /* 应用管理 */
    (1003007000, 1003000000, 'system:application', '应用管理', 'authority_system_application', 'MENU', 7, 1),
    (1003007001, 1003007000, 'system:application:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1003007002, 1003007000, 'system:application:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 消息管理 */
    (1003008000, 1003000000, 'system:message', '消息管理', 'authority_system_application', 'MENU', 7, 1),
    (1003008001, 1003008000, 'system:message:notice', '查看系统通知', 'authority_view', 'RESOURCE', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 工作台 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1004000000, 0, 'workbench', '工作台', 'authority_workbench', 'CATALOG', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 仪表盘 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1005000000, 0, 'dashboard', '仪表盘', 'authority_workbench', 'CATALOG', 2, 1),
    /* 仪表盘 */
    (1005001000, 1005000000, 'dashboard:analysis', '数据分析', 'authority_workbench_dashboard', 'MENU', 1, 1),
    (1005002000, 1005000000, 'dashboard:monitor', '系统监控', 'authority_workbench_dashboard', 'MENU', 1, 1),
    /* -------------------------------------------------------------------------------------------------------------- */
    /* 站点管理 */
    /* -------------------------------------------------------------------------------------------------------------- */
    (1006000000, 0, 'site', '站点', 'authority_site', 'CATALOG', 3, 1),
    /* 公告管理 */
    (1006001000, 1006000000, 'site:announcement', '公告管理', 'authority_site_product', 'MENU', 1, 1),
    (1006001001, 1006001000, 'site:announcement:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1006001002, 1006001000, 'site:announcement:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1006001003, 1006001000, 'site:announcement:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1006001004, 1006001000, 'site:announcement:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 宣传栏管理 */
    (1006002000, 1006000000, 'site:poster', '公告管理', 'authority_site_product', 'MENU', 1, 1),
    (1006002001, 1006002000, 'site:poster:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1006002002, 1006002000, 'site:poster:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1006002003, 1006002000, 'site:poster:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1006002004, 1006002000, 'site:poster:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1),
    /* 产品管理 */
    (1006003000, 1006000000, 'site:product', '产品管理', 'authority_site_product', 'MENU', 1, 1),
    (1006003001, 1006003000, 'site:product:view', '查看', 'authority_view', 'RESOURCE', 1, 1),
    (1006003002, 1006003000, 'site:product:add', '添加', 'authority_add', 'RESOURCE', 1, 1),
    (1006003003, 1006003000, 'site:product:edit', '编辑', 'authority_edit', 'RESOURCE', 1, 1),
    (1006003004, 1006003000, 'site:product:delete', '删除', 'authority_delete', 'RESOURCE', 1, 1);

--
-- 角色权限关联
--

truncate sys_role_authority;

insert into sys_role_authority (`id`, `role_id`, `authority_id`, `created_at`)
select concat(rpad(sr.id, 6, 0), rpad(sa.id, 10, 0)), sr.id, sa.id, now()
from sys_role sr,
     sys_authority sa
where sr.id = 1;

--
-- 系统设置项
--

truncate sys_config;

insert into sys_config (`id`, `config_key`, `config_value`, `label`, `description`, `active`)
values (1, 'APP_TITLE', 'Application', 'label_config_site_title', '站点标题', 1),
       (2, 'APP_COPYRIGHT', 'Copyright@2023', 'label_config_site_copyright', '站点版权信息', 1),
       (3, 'LOGIN_CAPTCHA_ENABLED', 'false', 'label_config_login_captcha_enabled', '是否启用登录验证码', 1);

--
-- 系统设置项
--

truncate `sys_attachment_type`;

insert into `sys_attachment_type` (`id`, `code`, `label`, `title`, `multiple_ind`, `source`)
values (1000001, 'UNSPECIFIED', '', '未指定', 0, 1),
       (1000002, 'USER_AVATAR', '', '用户头像', 0, 1),
       (1000003, 'POSTER_COVER', '', '宣传栏封面', 0, 1),
       (1000004, 'POSTER_MOBILE_COVER', '', '宣传栏移动端封面', 0, 1);

-- =====================================================================================================================
-- 多语言
-- =====================================================================================================================

truncate sys_label;

insert into sys_label (`id`, `code`, `zh_label`, `en_label`)
values (10010010001, 'label_lang_type__zh_cn', '简体中文', 'Simplified Chinese'),
       (10010010002, 'label_lang_type__zh_tw', '繁体中文', 'Traditional Chinese'),
       (10010010003, 'label_lang_type__en_us', '英文', 'English'),
       (10010020001, 'label_mobile_country_code__0085', '中国', 'China'),
       (10010020002, 'label_mobile_country_code__00852', '中国香港', 'Hong Kong'),
       (10010020003, 'label_mobile_country_code__00886', '中国台湾', 'Taiwan'),
       (10010020004, 'label_mobile_country_code__00853', '中国澳门', 'Macao'),
       (20010000001, 'label__ok', 'OK', 'OK'),
       (20010000002, 'label__delete', '删除', 'Delete'),
       (20010000003, 'label__save', '保存', 'Save'),
       (20010000004, 'label__reset', '重置', 'Reset'),
       (20010000005, 'label__submit', '提交', 'Submit'),
       (20010000006, 'label__add', '添加', 'Add'),
       (20010000007, 'label__edit', '编辑', 'Edit'),
       (20010000008, 'label__remove', '移除', 'Remove'),
       (20020000001, 'label__id', 'ID', 'ID'),
       (20020000002, 'label__code', '编号', 'Code'),
       (20020000003, 'label__title', '标题', 'Title'),
       (20020000004, 'label__name', '名称', 'Name'),
       (20020000005, 'label__description', '描述说明', 'Description'),
       (30010000001, 'label__x', '占位', '占位');

-- ==============================¬=======================================================================================
-- Message
-- =====================================================================================================================

truncate table sys_message_template_type;

insert into sys_message_template_type (`id`, `code`, `label`, `title`, `status`, `active`)
values (1001001, 'NOTICE', 'label_message_template_type__NOTICE', '通知', 1, 1),
       (1001002, 'MAIL', 'label_message_template_type__MAIL', '邮件', 1, 1),
       (1001003, 'SMS', 'label_message_template_type__SMS', '短息', 1, 1),
       (1001004, 'WECHAT', 'label_message_template_type__WECHAT', '微信', 1, 1),
       (1001005, 'WEWORK', 'label_message_template_type__WEWORK', '企微', 1, 1),
       (1001006, 'LARK', 'label_message_template_type__LARK', '飞书', 1, 1),
       (1001007, 'DINGTALK', 'label_message_template_type__DINGTALK', '钉钉', 1, 1);

truncate table sys_message_type;

insert into sys_message_type (`id`, `code`, `label`, `title`, `status`, `active`)
values (1001000, 'TEST_MESSAGE', 'label_message_type__TEST_MESSAGE', '测试专用消息', 1, 1),
       (1001001, 'CAPTCHA_MESSAGE', 'label_message_type__CAPTCHA_MESSAGE', '验证码消息', 1, 1),
       (1001002, 'REGISTER_SUCCESS_MESSAGE', 'label_message_type__REGISTER_SUCCESS_MESSAGE', '注册成功消息', 1, 1);

truncate table sys_message_template;

insert into sys_message_template (`id`, `type_id`, `template_type_id`, `content`, `status`, `active`)
values (1001000001, 1001000, 1001001, '', 1, 1),
       (1001000002, 1001000, 1001002, '', 1, 1),
       (1001000003, 1001000, 1001003, '', 1, 1),
       (1001000004, 1001000, 1001004, '', 1, 1),
       (1001000005, 1001000, 1001005, '', 1, 1),
       (1001000006, 1001000, 1001006, '', 1, 1),
       (1001000007, 1001000, 1001007, '', 1, 1),
       (1001001001, 1001001, 1001001, '', 1, 1),
       (1001001002, 1001001, 1001002, '', 1, 1),
       (1001001003, 1001001, 1001003, '', 1, 1),
       (1001001004, 1001001, 1001004, '', 1, 1),
       (1001001005, 1001001, 1001005, '', 1, 1),
       (1001001006, 1001001, 1001006, '', 1, 1),
       (1001001007, 1001001, 1001007, '', 1, 1),
       (1001002001, 1001002, 1001001, '', 1, 1),
       (1001002002, 1001002, 1001002, '', 1, 1),
       (1001002003, 1001002, 1001003, '', 1, 1),
       (1001002004, 1001002, 1001004, '', 1, 1),
       (1001002005, 1001002, 1001005, '', 1, 1),
       (1001002006, 1001002, 1001006, '', 1, 1),
       (1001002007, 1001002, 1001007, '', 1, 1);

-- ==============================¬=======================================================================================
-- 前台系统基础表
-- =====================================================================================================================

--
-- 会员
--

insert into `sys_vip` (`id`, `code`, `title`, `label`, `source`, `active`)
values (1000001, 'MEMBER', 'Member', 'label_vip__member', 1, 0),
       (1000002, 'VIP', 'VIP', 'label_vip__vip', 1, 1);

--
-- 会员套餐
--

insert into `sys_member_type_item` (`id`, `vip_id`, `code`, `title`, `label`, `source`, `active`)
values (1000002001, 1000002, 'MONTH', '月卡', 'label_vip_item__month', 1, 0),
       (1000002001, 1000002, 'QUARTER', '季卡', 'label_vip_item__quarter', 1, 0),
       (1000002001, 1000002, 'YEAR', '年卡', 'label_vip_item__year', 1, 1);

--
-- 订单类型表
--

truncate `sys_order_type`;

insert into `sys_order_type` (`id`, `code`, `title`)
values (1000001, 'VIP', 'VIP');

-- ==============================¬=======================================================================================
-- OAuth
-- =====================================================================================================================

--
-- 客户端
--

truncate table sys_client;

insert into sys_client (`id`, `client_id`, `client_name`, `client_secret`, `authorization_grant_types`,
                        `client_authentication_methods`, `redirect_uris`, `scopes`, `active`)
values (1, 'webapp', 'webapp', '$2a$10$kFl5GA5II.hPTPYcTkeVoe2J7HaUpP.d3ttZtdWmQj1N5Sul94a7a',
        'authorization_code,refresh_token,client_credentials,password',
        'client_secret_basic,client_secret_post,client_secret_jwt,private_key_jwt',
        'http://127.0.0.1:8080,http://127.0.0.1:9292,http://127.0.0.1:9292/login/oauth/code/webapp',
        'openid,profile', 1),
       (2, 'system', 'system', '$2a$10$kFl5GA5II.hPTPYcTkeVoe2J7HaUpP.d3ttZtdWmQj1N5Sul94a7a',
        'authorization_code,refresh_token,client_credentials,password',
        'client_secret_basic,client_secret_post,client_secret_jwt,private_key_jwt',
        'http://127.0.0.1:8080,http://127.0.0.1:9292,http://127.0.0.1:9292/login/oauth/code/webapp',
        'openid,profile', 1),
       (3, 'demo', 'demo', '$2a$10$U8AYqn8pV8OSH.5y.teuguFLkIVx98qwIobe3jSP1hhS4K1Oe9Jyu',
        'authorization_code,refresh_token,client_credentials,password',
        'client_secret_basic,client_secret_post,client_secret_jwt,private_key_jwt',
        'http://127.0.0.1:8080,http://127.0.0.1:9292,http://127.0.0.1:9191/login/oauth/code/demo',
        'openid,profile', 0);
