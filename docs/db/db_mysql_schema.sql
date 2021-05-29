DROP DATABASE IF EXISTS `platform`;

CREATE DATABASE IF NOT EXISTS `platform`
    DEFAULT CHARACTER SET `utf8mb4`
    DEFAULT COLLATE `utf8mb4_0900_ai_ci`;

USE `platform`;

-- ---------------------------------------------------------------------------------------------------------------------
-- 客户端
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_client`
(
    `id`                            BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `client_id`                     VARCHAR(150) COMMENT 'Client ID',
    `client_secret`                 VARCHAR(255) COMMENT 'Client Secret',
    `client_name`                   VARCHAR(255) COMMENT 'Client Name',
    `authorization_grant_types`     VARCHAR(255) COMMENT 'Authorization Grant Types',
    `client_authentication_methods` VARCHAR(255) COMMENT 'Client Authentication Methods',
    `scopes`                        VARCHAR(255) COMMENT 'Score',
    `redirect_uris`                 VARCHAR(255) COMMENT 'Redirect Uris',
    `description`                   VARCHAR(255) COMMENT '备注',
    `active`                        TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`                    DATETIME COMMENT '创建时间',
    `created_by`                    BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at`              DATETIME COMMENT '最后修改时间',
    `last_modified_by`              BIGINT(20) UNSIGNED COMMENT '最后修改人',
    `deleted_at`                    DATETIME COMMENT '删除时间',
    `deleted_by`                    BIGINT(20) UNSIGNED COMMENT '删除人',
    CONSTRAINT `pk_sys_client` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_client`
    COMMENT '客户端';

CREATE INDEX `ix_sys_client__client_id` ON `sys_client` (`client_id`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 权限表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_authority`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `parent_id`        BIGINT(20) UNSIGNED NOT NULL COMMENT 'Parent ID',
    `code`             VARCHAR(150) COMMENT '编码',
    `title`            VARCHAR(150) COMMENT '标题',
    `label`            VARCHAR(150) COMMENT '多语言文本',
    `description`      VARCHAR(255) COMMENT '备注说明',
    `type_`            INT(1) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '权限类型',
    `index_`           INT(2) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '排序序号',
    `active`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`       DATETIME COMMENT '创建时间',
    `created_by`       BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at` DATETIME COMMENT '最后修改时间',
    `last_modified_by` BIGINT(20) UNSIGNED COMMENT '最后修改人',
    `deleted_at`       DATETIME COMMENT '删除时间',
    `deleted_by`       BIGINT(20) UNSIGNED COMMENT '删除人',
    CONSTRAINT `pk_sys_authority` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_authority`
    COMMENT '权限表';

CREATE INDEX `ix_sys_authority__code` ON `sys_authority` (`code`);
CREATE INDEX `ix_sys_authority__parent_id` ON `sys_authority` (`parent_id`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 租户表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_tenant`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `code`             VARCHAR(150) COMMENT '编号',
    `title`            VARCHAR(150) COMMENT '名称',
    `description`      VARCHAR(250) COMMENT '备注',
    `status`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`       DATETIME COMMENT '创建时间',
    `created_by`       BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at` DATETIME COMMENT '最后修改时间',
    `last_modified_by` BIGINT(20) UNSIGNED COMMENT '最后修改人',
    `deleted_at`       DATETIME COMMENT '删除时间',
    `deleted_by`       BIGINT(20) UNSIGNED COMMENT '删除人',
    CONSTRAINT `sys_tenant` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_tenant`
    COMMENT '租户表';

CREATE INDEX `ix_sys_tenant__code` ON `sys_tenant` (`code`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 角色表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_role`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `tenant_id`        BIGINT(20) UNSIGNED NOT NULL COMMENT '租户ID',
    `code`             VARCHAR(150) COMMENT '编号',
    `title`            VARCHAR(150) COMMENT '标题',
    `label`            VARCHAR(150) COMMENT '文本',
    `description`      VARCHAR(255) COMMENT '备注',
    `status`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `source`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`       DATETIME COMMENT '创建时间',
    `created_by`       BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at` DATETIME COMMENT '最后修改时间',
    `last_modified_by` BIGINT(20) UNSIGNED COMMENT '最后修改人',
    `deleted_at`       DATETIME COMMENT '删除时间',
    `deleted_by`       BIGINT(20) UNSIGNED COMMENT '删除人',
    CONSTRAINT `pk_sys_role` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_role`
    COMMENT '角色表';

CREATE INDEX `ix_sys_role__code` ON `sys_role` (`code`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 部门表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_department`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `tenant_id`        BIGINT(20) UNSIGNED NOT NULL COMMENT '租户ID',
    `code`             VARCHAR(150) COMMENT '编号',
    `title`            VARCHAR(150) COMMENT '标题',
    `description`      VARCHAR(255) COMMENT '备注说明',
    `root_ind`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `active`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`       DATETIME COMMENT '创建时间',
    `created_by`       BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at` DATETIME COMMENT '最后修改时间',
    `last_modified_by` BIGINT(20) UNSIGNED COMMENT '最后修改人',
    `deleted_at`       DATETIME COMMENT '删除时间',
    `deleted_by`       BIGINT(20) UNSIGNED COMMENT '删除人',
    CONSTRAINT `pk_sys_department` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_department`
    COMMENT '部门表';

CREATE INDEX `ix_sys_department__code` ON `sys_department` (`code`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 岗位表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_position`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `tenant_id`        BIGINT(20) UNSIGNED NOT NULL COMMENT '租户ID',
    `code`             VARCHAR(150) COMMENT '编号',
    `title`            VARCHAR(150) COMMENT '标题',
    `description`      VARCHAR(255) COMMENT '备注说明',
    `root_ind`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `active`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`       DATETIME COMMENT '创建时间',
    `created_by`       BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at` DATETIME COMMENT '最后修改时间',
    `last_modified_by` BIGINT(20) UNSIGNED COMMENT '最后修改人',
    `deleted_at`       DATETIME COMMENT '删除时间',
    `deleted_by`       BIGINT(20) UNSIGNED COMMENT '删除人',
    CONSTRAINT `pk_sys_position` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_position`
    COMMENT '岗位表';

CREATE INDEX `ix_sys_position__code` ON `sys_position` (`code`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 用户表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_user`
(
    `id`                  BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `username`            VARCHAR(150) COMMENT '用户名',
    `email`               VARCHAR(150) COMMENT '电子邮箱',
    `mobile_country_code` VARCHAR(10) COMMENT '手机国家区号',
    `mobile`              VARCHAR(50) COMMENT '手机号码',
    `password`            VARCHAR(255) COMMENT '密码',
    `name`                VARCHAR(255) COMMENT '全名',
    `display_name`        VARCHAR(255) COMMENT '昵称',
    `id_card_type`        VARCHAR(50) COMMENT '证件类型',
    `id_card_no`          VARCHAR(50) COMMENT '证件号码',
    `sex`                 VARCHAR(10) COMMENT '性别',
    `birthday`            DATETIME COMMENT '生日',
    `description`         VARCHAR(255) COMMENT '备注',
    `status`              TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '用户状态',
    `source`              TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`              TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`          DATETIME COMMENT '创建时间',
    `created_by`          BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at`    DATETIME COMMENT '最后修改时间',
    `last_modified_by`    BIGINT(20) UNSIGNED COMMENT '最后修改人',
    `deleted_at`          DATETIME COMMENT '删除时间',
    `deleted_by`          BIGINT(20) UNSIGNED COMMENT '删除人',
    CONSTRAINT `pk_sys_user` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_position`
    COMMENT '用户表';

CREATE INDEX `ix_sys_user__username` ON `sys_user` (`username`);
CREATE INDEX `ix_sys_user__email` ON `sys_user` (`email`);
CREATE INDEX `ix_sys_user__mobile` ON `sys_user` (`mobile_country_code`, `mobile`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 租户用户表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_tenant_user`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `tenant_id`        BIGINT(20) UNSIGNED NOT NULL COMMENT '租户ID',
    `user_id`          BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `name`             VARCHAR(255) COMMENT '租户内全名',
    `display_name`     VARCHAR(255) COMMENT '租户内昵称',
    `entry_date`       DATETIME COMMENT '入职日期',
    `resignation_date` DATETIME COMMENT '离职日期',
    `status`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '用户状态',
    `source`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`       DATETIME COMMENT '创建时间',
    `created_by`       BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at` DATETIME COMMENT '最后修改时间',
    `last_modified_by` BIGINT(20) UNSIGNED COMMENT '最后修改人',
    `deleted_at`       DATETIME COMMENT '删除时间',
    `deleted_by`       BIGINT(20) UNSIGNED COMMENT '删除人',
    CONSTRAINT `pk_sys_tenant_user` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_tenant_user`
    COMMENT '租户用户表';

CREATE INDEX `ix_sys_tenant_user__tenant_id` ON `sys_tenant_user` (`tenant_id`);
CREATE INDEX `ix_sys_tenant_user__user_id` ON `sys_tenant_user` (`user_id`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 租户-权限关联表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_tenant_authority_relation`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `tenant_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '租户ID',
    `authority_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '权限ID',
    `created_at`   DATETIME COMMENT '创建时间',
    `created_by`   BIGINT(20) UNSIGNED COMMENT '创建人',
    CONSTRAINT `pk_sys_tenant_authority_relation` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_tenant_authority_relation`
    COMMENT '租户-权限关联表';

CREATE INDEX `ix_sys_tenant_authority_relation__tenant_id` ON `sys_tenant_authority_relation` (`tenant_id`);
CREATE INDEX `ix_sys_tenant_authority_relation__authority_id` ON `sys_tenant_authority_relation` (`authority_id`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 角色-权限关联表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_role_authority_relation`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `tenant_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '租户ID',
    `role_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '角色ID',
    `authority_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '权限ID',
    `created_at`   DATETIME COMMENT '创建时间',
    `created_by`   BIGINT(20) UNSIGNED COMMENT '创建人',
    CONSTRAINT `pk_sys_role_authority_relation` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_role_authority_relation`
    COMMENT '角色-权限关联表';

CREATE INDEX `ix_sys_role_authority_relation__tenant_id` ON `sys_role_authority_relation` (`tenant_id`);
CREATE INDEX `ix_sys_role_authority_relation__role_id` ON `sys_role_authority_relation` (`role_id`);
CREATE INDEX `ix_sys_role_authority_relation__authority_id` ON `sys_role_authority_relation` (`authority_id`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 用户-角色关联表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_user_role_relation`
(
    `id`         BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `tenant_id`  BIGINT(20) UNSIGNED NOT NULL COMMENT '租户ID',
    `role_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '角色ID',
    `user_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `created_at` DATETIME COMMENT '创建时间',
    `created_by` BIGINT(20) UNSIGNED COMMENT '创建人',
    CONSTRAINT `pk_sys_user_role_relation` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_user_role_relation`
    COMMENT '用户-角色关联表';

CREATE INDEX `ix_sys_user_role_relation__tenant_id` ON `sys_user_role_relation` (`tenant_id`);
CREATE INDEX `ix_sys_user_role_relation__role_id` ON `sys_user_role_relation` (`role_id`);
CREATE INDEX `ix_sys_user_role_relation__user_id` ON `sys_user_role_relation` (`user_id`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 实体关联表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_entity_relation`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `tenant_id`     BIGINT(20) UNSIGNED NOT NULL COMMENT '租户ID',
    `ancestor_id`   BIGINT(20) UNSIGNED NOT NULL COMMENT '祖先ID',
    `entity_id`     BIGINT(20) UNSIGNED NOT NULL COMMENT '实体ID',
    `relation_type` VARCHAR(50) COMMENT '关联类型',
    `parent_ind`    TINYINT(1) UNSIGNED COMMENT '是否直接上级',
    `path_`         VARCHAR(2000) COMMENT '层级路径',
    `index_`        INT(2) UNSIGNED COMMENT '层级序号',
    `created_at`    DATETIME COMMENT '创建时间',
    `created_by`    BIGINT(20) UNSIGNED COMMENT '创建人',
    CONSTRAINT `pk_sys_entity_relation` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_entity_relation`
    COMMENT '实体关联表';

CREATE INDEX `ix_sys_entity_relation__tenant_id` ON `sys_entity_relation` (`tenant_id`);
CREATE INDEX `ix_sys_entity_relation__ancestor_id` ON `sys_entity_relation` (`ancestor_id`);
CREATE INDEX `ix_sys_entity_relation__entity_id` ON `sys_entity_relation` (`entity_id`);
CREATE INDEX `ix_sys_entity_relation__relation_type` ON `sys_entity_relation` (`relation_type`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 用户登录会话记录
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_user_session`
(
    `id`                   BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `tenant_id`            BIGINT(20) UNSIGNED NOT NULL COMMENT '租户ID',
    `user_id`              BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `session_id`           VARCHAR(150) COMMENT 'Session ID',
    `host`                 VARCHAR(150) COMMENT '登录主机',
    `device`               VARCHAR(150) COMMENT '登录设备',
    `client_id`            VARCHAR(150) COMMENT '客户端编号',
    `client_version`       VARCHAR(150) COMMENT '客户端版本',
    `platform`             VARCHAR(150) COMMENT '登录平台',
    `start_datetime`       DATETIME COMMENT '会话开始时间',
    `last_access_datetime` DATETIME COMMENT '最近访问时间',
    `end_datetime`         DATETIME COMMENT '会话结束时间',
    `created_at`           DATETIME COMMENT '创建时间',
    `created_by`           BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at`     DATETIME COMMENT '最后修改时间',
    `last_modified_by`     BIGINT(20) UNSIGNED COMMENT '最后修改人',
    CONSTRAINT `pk_sys_user_session_id` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_user_session`
    COMMENT '用户会话表';

-- =====================================================================================================================
-- 国际化
-- =====================================================================================================================

-- ---------------------------------------------------------------------------------------------------------------------
-- 语言表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_lang`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `code`        VARCHAR(50) COMMENT '编号',
    `lang`        VARCHAR(50) COMMENT '语言编码',
    `country`     VARCHAR(50) COMMENT '地区编码',
    `label`       VARCHAR(250) COMMENT '文本',
    `description` VARCHAR(250) COMMENT '备注说明',
    `default_ind` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '默认语言',
    `active`      TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    CONSTRAINT `pk_sys_lang` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_lang`
    COMMENT '语言表';

CREATE INDEX `ix_sys_lang__code` ON `sys_lang` (`code`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 多语言文本表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_label`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `code`             VARCHAR(255) COMMENT '多语言标识',
    `description`      VARCHAR(255) COMMENT '备注说明',
    `zh_cn_label`      TEXT COMMENT '简体中文',
    `zh_cn_source_ind` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '简体中文作为翻译基准',
    `zh_cn_final_ind`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '简体中文固定不再翻译',
    `zh_hk_label`      TEXT COMMENT '繁体中文',
    `zh_hk_source_ind` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '繁体中文作为翻译基准',
    `zh_hk_final_ind`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '繁体中文固定不再翻译',
    `en_us_label`      TEXT COMMENT '美式英语',
    `en_us_source_ind` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '美式英语作为翻译基准',
    `en_us_final_ind`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '美式英语固定不再翻译',
    `created_at`       DATETIME COMMENT '创建时间',
    `created_by`       BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at` DATETIME COMMENT '最后修改时间',
    `last_modified_by` BIGINT(20) UNSIGNED COMMENT '最后修改人',
    CONSTRAINT `pk_sys_label` PRIMARY KEY (`id`)
);

ALTER TABLE `sys_label`
    COMMENT '多语言文本表';

CREATE INDEX `ix_sys_label__code` ON `sys_label` (`code`);

-- =====================================================================================================================
-- 通用目录相关
-- =====================================================================================================================

-- ---------------------------------------------------------------------------------------------------------------------
-- 目录类型表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_catalog_type`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `code`        VARCHAR(100) COMMENT '编号',
    `label`       VARCHAR(150) COMMENT '文本',
    `description` VARCHAR(250) COMMENT '备注说明',
    `active`      TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    CONSTRAINT `pk_sys_catalog_type` PRIMARY KEY (`id`)
);

ALTER TABLE `sys_catalog_type`
    COMMENT '目录类型表';

CREATE INDEX `ix_sys_catalog_type__code` ON `sys_catalog_type` (`code`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 目录表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_catalog`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `catalog_type_id`  BIGINT(20) UNSIGNED NOT NULL COMMENT '分类类型ID',
    `code`             VARCHAR(150) COMMENT '编号',
    `title`            VARCHAR(255) COMMENT '标题',
    `description`      VARCHAR(255) COMMENT '简介',
    `root_ind`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否顶层',
    `source`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`       DATETIME COMMENT '创建时间',
    `created_by`       BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at` DATETIME COMMENT '最后修改时间',
    `last_modified_by` BIGINT(20) UNSIGNED COMMENT '最后修改人',
    `deleted_at`       DATETIME COMMENT '删除时间',
    `deleted_by`       BIGINT(20) UNSIGNED COMMENT '删除人',
    CONSTRAINT `pk_sys_catalog` PRIMARY KEY (`id`)
);

ALTER TABLE `sys_catalog`
    COMMENT '目录表';

CREATE INDEX `ix_sys_catalog__catalog_type_id` ON `sys_catalog` (`catalog_type_id`);
CREATE INDEX `ix_sys_catalog__code` ON `sys_catalog` (`code`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 目录分类关联表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_catalog_relation`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `ancestor_id`   BIGINT(20) UNSIGNED NOT NULL COMMENT '祖先ID',
    `entity_id`     BIGINT(20) UNSIGNED NOT NULL COMMENT '实体ID',
    `relation_type` VARCHAR(50) COMMENT '关联类型',
    `parent_ind`    TINYINT(1) UNSIGNED COMMENT '是否直接上级',
    `path_`         VARCHAR(2000) COMMENT '层级路径',
    `index_`        INT(2) UNSIGNED COMMENT '层级序号',
    `created_at`    DATETIME COMMENT '创建时间',
    `created_by`    BIGINT(20) UNSIGNED COMMENT '创建人',
    CONSTRAINT `pk_sys_catalog_relation` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_catalog_relation`
    COMMENT '目录分类关联表';

CREATE INDEX `ix_sys_catalog_relation__relation_type` ON `sys_catalog_relation` (`relation_type`);
CREATE INDEX `ix_sys_catalog_relation__ancestor_id` ON `sys_catalog_relation` (`ancestor_id`);
CREATE INDEX `ix_sys_catalog_relation__entity_id` ON `sys_catalog_relation` (`entity_id`);

-- =====================================================================================================================
-- 通用字典相关
-- =====================================================================================================================

-- ---------------------------------------------------------------------------------------------------------------------
-- 字典类型表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_dictionary_type`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `code`        VARCHAR(100) COMMENT '编号',
    `label`       VARCHAR(150) COMMENT '文本',
    `description` VARCHAR(250) COMMENT '备注说明',
    CONSTRAINT `pk_sys_dictionary_type` PRIMARY KEY (`id`)
);

ALTER TABLE `sys_dictionary_type`
    COMMENT '字典类型表';

CREATE INDEX `ix_sys_dictionary_type__code` ON `sys_dictionary_type` (`code`);

/* 字典明细 */
CREATE TABLE `sys_dictionary_item`
(
    `id`                 BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `dictionary_type_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '字典类型ID',
    `index_`             INT(2) COMMENT '序号',
    `source`             TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`             TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`         DATETIME COMMENT '创建时间',
    `created_by`         BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at`   DATETIME COMMENT '最后修改时间',
    `last_modified_by`   BIGINT(20) UNSIGNED COMMENT '最后修改人',
    `deleted_at`         DATETIME COMMENT '删除时间',
    `deleted_by`         BIGINT(20) UNSIGNED COMMENT '删除人',
    CONSTRAINT `pk_sys_dictionary_item` PRIMARY KEY (`id`)
);

ALTER TABLE `sys_dictionary_item`
    COMMENT '字典明细表';

CREATE INDEX `ix_sys_dictionary_item__dictionary_type_id` ON `sys_dictionary_item` (`dictionary_type_id`);
CREATE INDEX `ix_sys_dictionary_item__active` ON `sys_dictionary_item` (`active`);
CREATE INDEX `ix_sys_dictionary_item__source` ON `sys_dictionary_item` (`source`);

/* 字典关联 */
CREATE TABLE `sys_dictionary_relation`
(
    `id`                 BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `dictionary_type_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '字典类型ID',
    `dictionary_item_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '字典明细ID',
    `target_type`        VARCHAR(50) COMMENT '目标类型',
    `target_entity_id`   BIGINT(20) UNSIGNED COMMENT '目标实体',
    `created_at`         DATETIME COMMENT '创建时间',
    `created_by`         BIGINT(20) UNSIGNED COMMENT '创建人',
    CONSTRAINT `pk_sys_dictionary_relation` PRIMARY KEY (`id`)
);

ALTER TABLE `sys_dictionary_relation`
    COMMENT '字典关联表';

CREATE INDEX `ix_sys_dictionary_relation__dictionary_type_id` ON `sys_dictionary_relation` (`dictionary_type_id`);
CREATE INDEX `ix_sys_dictionary_relation__dictionary_item_id` ON `sys_dictionary_relation` (`dictionary_item_id`);
CREATE INDEX `ix_sys_dictionary_relation__target_type` ON `sys_dictionary_relation` (`target_type`);
CREATE INDEX `ix_sys_dictionary_relation__target_entity_id` ON `sys_dictionary_relation` (`target_entity_id`);

-- =====================================================================================================================
-- 通用标签相关
-- =====================================================================================================================

-- ---------------------------------------------------------------------------------------------------------------------
-- 标签类型表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_tag_type`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `code`        VARCHAR(100) COMMENT '编号',
    `label`       VARCHAR(150) COMMENT '文本',
    `description` VARCHAR(250) COMMENT '备注',
    CONSTRAINT `pk_sys_tag_type` PRIMARY KEY (`id`)
);

ALTER TABLE `sys_tag_type`
    COMMENT '标签类型表';

CREATE INDEX `ix_sys_tag_type__code` ON `sys_tag_type` (`code`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 标签表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_tag`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `tag_type_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '标签类型ID',
    `title`            VARCHAR(150) COMMENT '标题',
    `description`      VARCHAR(255) COMMENT '备注说明',
    `index_`           INT(2) COMMENT '序号',
    `source`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '数据来源',
    `active`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `created_at`       DATETIME COMMENT '创建时间',
    `created_by`       BIGINT(20) UNSIGNED COMMENT '创建人',
    `last_modified_at` DATETIME COMMENT '最后修改时间',
    `last_modified_by` BIGINT(20) UNSIGNED COMMENT '最后修改人',
    `deleted_at`       DATETIME COMMENT '删除时间',
    `deleted_by`       BIGINT(20) UNSIGNED COMMENT '删除人',
    CONSTRAINT `pk_sys_tag` PRIMARY KEY (`id`)
);

ALTER TABLE `sys_tag`
    COMMENT '标签表';

CREATE INDEX `ix_sys_tag__tag_type_id` ON `sys_tag` (`tag_type_id`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 标签关联表
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_tag_relation`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `tag_type_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '标签类型ID',
    `tag_id`           BIGINT(20) UNSIGNED NOT NULL COMMENT '标签ID',
    `target_type`      VARCHAR(50) COMMENT '目标类型',
    `target_entity_id` BIGINT(20) UNSIGNED COMMENT '目标实体ID',
    `created_at`       DATETIME COMMENT '创建时间',
    `created_by`       BIGINT(20) UNSIGNED COMMENT '创建人',
    CONSTRAINT `pk_sys_tag_relation` PRIMARY KEY (`id`)
);

ALTER TABLE `sys_tag_relation`
    COMMENT '标签关联表';

CREATE INDEX `ix_sys_tag_relation__tag_type_id` ON `sys_tag_relation` (`tag_type_id`);
CREATE INDEX `ix_sys_tag_relation__tag_id` ON `sys_tag_relation` (`tag_id`);
CREATE INDEX `ix_sys_tag_relation__target_type` ON `sys_tag_relation` (`target_type`);
CREATE INDEX `ix_sys_tag_relation__target_entity_id` ON `sys_tag_relation` (`target_entity_id`);

-- ---------------------------------------------------------------------------------------------------------------------
-- 测试用的数据表，无实际业务应用
-- ---------------------------------------------------------------------------------------------------------------------

CREATE TABLE `sys_test`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `code`        VARCHAR(150) COMMENT '编号',
    `description` VARCHAR(255) COMMENT '备注',
    `active`      TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '启用状态',
    `datetime`    DATETIME COMMENT 'DateTime',
    `timestamp`   TIMESTAMP COMMENT 'Timestamp',
    CONSTRAINT `pk_sys_test` PRIMARY KEY (`id`)
);
ALTER TABLE `sys_test`
    COMMENT '客户端';

CREATE INDEX `ix_sys_test__code` ON `sys_test` (`code`);
