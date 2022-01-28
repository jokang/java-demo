CREATE TABLE `domain_template`
(
  `id`      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid`     BIGINT          NOT NULL COMMENT '用户ID',
  `title`   VARCHAR(50)     NULL COMMENT '标题',
  `content` VARCHAR(200)    NULL COMMENT '内容',
  `is_valid` INT(10) UNSIGNED         NOT NULL DEFAULT 1 COMMENT '0逻辑删除;1有效',
  `ctime`   TIMESTAMP       NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `mtime`   TIMESTAMP       NOT NULL DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`uid`),
  unique `uidx_uid_title` (`uid`, `title`)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  COLLATE = UTF8MB4_UNICODE_CI COMMENT ='SQL建表语句模板';