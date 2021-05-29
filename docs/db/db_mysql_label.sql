USE `platform`;

truncate `sys_label`;

insert into `sys_label` (`id`, `code`,
                         `zh_cn_label`, `zh_cn_final_ind`, `zh_cn_source_ind`,
                         `zh_hk_label`, `zh_hk_final_ind`, `zh_hk_source_ind`,
                         `en_us_label`, `en_us_final_ind`, `en_us_source_ind`)
values (1000001, 'label_demo', '测试', 1, 1, '', 0, 0, '', 0, 0),
       (1001001, 'label_role_system_administrator', '系统管理员', 1, 1, '系統管理員', 1, 0, 'System Administrator', 1, 0),
       (1001002, 'label_role_administrator', '管理员', 1, 1, '管理員', 1, 0, 'Administrator', 1, 0),
       (1001003, 'label_role_user', '用户', 1, 1, '用戶', 1, 0, 'User', 1, 0),
       (1001004, 'label_role_anonymous_user', '匿名用户', 1, 1, '匿名用戶', 1, 0, 'Anonymous User', 1, 0),
       (1002001, 'label_lang_zh_cn', '简体中文', 1, 1, '簡體中文', 1, 0, 'Simplified Chinese', 1, 0),
       (1002002, 'label_lang_zh_tw', '繁体中文', 1, 1, '繁體中文', 1, 0, 'Traditional Chinese', 1, 0),
       (1002003, 'label_lang_en_us', '美式英语', 1, 1, '美式英語', 1, 0, 'English', 1, 0);

update sys_label
set zh_cn_final_ind = 0,
    zh_hk_final_ind = 0,
    en_us_final_ind = 0
where id = 1000001;
