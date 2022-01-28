CREATE TABLE `flowsys_user`
(
    `id`        bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `mis_id`    varchar(50)         NOT NULL COMMENT 'mis_id',
    `user_type` int(10)             NOT NULL COMMENT '用户类型:1品控组;2产品实验组',
    `ctime`     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='流量运营用户表';

CREATE TABLE `flowsys_config_task`
(
    `id`        bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `mis_id`    varchar(50)         NOT NULL COMMENT 'mis_id',
    `user_type` int(10)             NOT NULL COMMENT '用户类型:1品控组;2产品实验组',
    `s3_url`    varchar(1000)       NOT NULL COMMENT '上传的excel文件在S3的路径',
    `status`    int(10)             NOT NULL DEFAULT 0 COMMENT '任务状态.0未处理，1解析成功，2解析失败，3处理中,4处理完成',
    `ctime`     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime`     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='配置任务表';

create table `flowsys_config_task_item`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `config_task_id`  bigint(20) unsigned NOT NULL COMMENT '',
    `mis_id`          varchar(50)         NOT NULL COMMENT 'mis_id',
    `spu_id`          bigint(20)          NOT NULL COMMENT '待操作的SPU',
    `poi_id`          bigint(20)          NOT NULL DEFAULT 0 COMMENT '商家id',
    `action_text`     varchar(10)         NOT NULL COMMENT '操作动作.清空,置顶,屏蔽',
    `channel_text`    varchar(10)         NOT NULL COMMENT '操作渠道.全部,推荐,搜索',
    `start_date_text` varchar(20)         NOT NULL COMMENT '生效开始时间',
    `end_date_text`   varchar(20)         NOT NULL COMMENT '生效结束时间',
    `ctime`           timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='配置Excel文件明细表';

create table `flowsys_config_command`
(
    `id`                  bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `config_task_id`      bigint(20) unsigned NOT NULL COMMENT '冗余字段',
    `config_task_item_id` bigint(20) unsigned NOT NULL COMMENT '关联的操作项id',
    `mis_id`              varchar(50)         NOT NULL COMMENT 'mis_id',
    `user_type`           int(10)             NOT NULL COMMENT '用户类型:1品控组;2产品实验组',
    `spu_id`              bigint(20)          NOT NULL COMMENT '待操作的SPU',
    `operation_type`      varchar(10)         NOT NULL COMMENT '操作动作.1清空,2置顶,3屏蔽',
    `channel_type`        int(10)             NOT NULL COMMENT '操作渠道.1搜索,2推荐,',
    `start_time`          timestamp           NULL COMMENT '生效开始时间',
    `end_time`            timestamp           NULL COMMENT '生效结束时间',
    `status`              int(10)             NOT NULL DEFAULT 0 COMMENT '操作结果.0未操作,1成功,2错误',
    `ctime`               timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime`               timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='配置操作命令表';

create table `flowsys_config_operation_event`
(
    `id`                bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `config_command_id` bigint(20) unsigned NOT NULL COMMENT '关联的操作明细id',
    `event_type`        int(10)             NOT NULL COMMENT '1取消规则,2新增规则,操作失败',
    `detail`            varchar(1000)       NOT NULL COMMENT '',
    `ctime`             timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='配置操作事件表';

create table `flowsys_rule`
(
    `id`                bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `config_command_id` bigint(20) unsigned NOT NULL COMMENT '关联的操作命令id',
    `mis_id`            varchar(50)         NOT NULL COMMENT '冗余字段mis_id',
    `source_type`       int(10)             NOT NULL COMMENT '规则来源类型:1品控组;2产品实验组',
    `spu_id`            bigint(20)          NOT NULL COMMENT '待操作的SPU',
    `rule_type`         int(10)             NOT NULL COMMENT '规则类型.1屏蔽2置顶',
    `start_time`        timestamp           NULL COMMENT '生效开始时间',
    `end_time`          timestamp           NULL COMMENT '生效结束时间',
    `channel_type`      int(10)             NOT NULL COMMENT '生效渠道.1搜索,2推荐',
    `status`            int(10)             NOT NULL DEFAULT 0 COMMENT '0未生效,1生效中',
    `valid`             int(10)             NOT NULL DEFAULT 1 COMMENT '是否有效.1有效,2被取消',
    `ctime`             timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='干预规则表';


create table `flowsys_spu_status`
(
    `spu_id`       bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品SPU ID',
    `rule_id`      bigint(20) unsigned NOT NULL COMMENT '关联的干预规则id',
    `rule_type`    int(10)             NOT NULL COMMENT '规则类型.1屏蔽2置顶',
    `channel_type` int(10)             NOT NULL COMMENT '生效渠道.1搜索,2推荐',
    `ctime`        timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime`        timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`spu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='商品当前干预状态表';

create table `flowsys_indicator`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        bigint(20) unsigned NOT NULL COMMENT '指标名称',
    `dimension`   int(10)             NOT NULL COMMENT '指标维度.1商品',
    `description` varchar(100)        NOT NULL DEFAULT '' COMMENT '指标描述',
    `valid`       int(10)             NOT NULL DEFAULT 1 COMMENT '1生效,0无效',
    `ctime`       timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime`       timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='指标表';