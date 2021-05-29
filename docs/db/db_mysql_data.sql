USE `platform`;

/* ========================================================================================================= */
/* 基础数据 */
/* ========================================================================================================= */

insert into sys_tenant (id, code, title, source, active, created_at, created_by)
values (1, 'TNT001', '顶层租户', 1, 1, now(), 1),
       (2, 'TNT002', '演示租户', 1, 1, now(), 1);

insert into sys_role (id, tenant_id, code, title, label, source, active, created_at, created_by)
values (1, 1, 'SUPER_ADMINISTRATOR', 'Super Administrator', 'label_role_super_administrator', 1, 1, now(), 1),
       (2, 1, 'SYSTEM_ADMINISTRATOR', 'System Administrator', 'label_role_system_administrator', 1, 1, now(), 1),
       (3, 1, 'ADMINISTRATOR', 'Administrator', 'label_role_administrator', 1, 1, now(), 1),
       (4, 1, 'USER', 'User', 'label_role_user', 1, 1, now(), 1),
       (5, 2, 'SYSTEM_ADMINISTRATOR', 'System Administrator', 'label_role_system_administrator', 1, 1, now(), 1),
       (6, 2, 'ADMINISTRATOR', 'Administrator', 'label_role_administrator', 1, 1, now(), 1),
       (7, 2, 'USER', 'User', 'label_role_user', 1, 1, now(), 1);

insert into sys_user (id, username, email, mobile_country_code, mobile, display_name, active, created_at, password)
values (1, 'admin', 'admin@elvea.cn', '86', '13500000000', 'Administrator', 1, now(),
        '$2a$10$MLkjYEPJkO6KNrfUUBld6eWVr1G09nugg5UpIQVUtsQ.3Z9U2lOSK'),
       (2, 'test', 'test@elvea.cn', '86', '13500000001', 'Administrator', 1, now(),
        '$2a$10$MLkjYEPJkO6KNrfUUBld6eWVr1G09nugg5UpIQVUtsQ.3Z9U2lOSK');

/* 语言类型 */
insert into sys_tenant_user (id, tenant_id, user_id, name, display_name, status, source, active, created_at, created_by)
values (1, 1, 1, 'Administrator', 'Administrator', 1, 1, 1, now(), 1),
       (2, 1, 1, 'Administrator', 'Administrator', 1, 1, 1, now(), 1),
       (3, 2, 2, 'User', 'User', 1, 1, 1, now(), 1),
       (4, 2, 2, 'User', 'User', 1, 1, 1, now(), 1);

/* 部门 */
insert into sys_department (id, tenant_id, code, title, root_ind, active, created_at, created_by)
values (1, 1, 'ROOT', 'Root', 1, 1, now(), 1),
       (2, 2, 'ROOT', 'Root', 1, 1, now(), 1);

/* 语言类型 */
insert into sys_lang (id, code, lang, country, label, description, default_ind, active)
values (1, 'zh_cn', 'zh', 'cn', 'label_lang_zh_cn', '简体中文', 1, 1),
       (2, 'zh_tw', 'zh', 'tw', 'label_lang_zh_tw', '繁体中文', 0, 1),
       (3, 'en_us', 'en', 'us', 'label_lang_en_us', '美式英语', 0, 1);
