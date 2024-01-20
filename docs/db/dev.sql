--
-- 标签类型
--

TRUNCATE `sys_tag_type`;

INSERT INTO `sys_tag_type` (`id`, `code`, `title`, `label`, `description`, `source`, `active`)
VALUES (1000001, 'SYSTEM', 'SYSTEM', 'SYSTEM', 'SYSTEM', 1, 0);
