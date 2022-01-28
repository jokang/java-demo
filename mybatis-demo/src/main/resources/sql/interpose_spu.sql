create table `interpose_spu_status`
(
    `id`              bigint(20)  NOT NULL COMMENT 'spuId',
    `current_status`  int(10)     NOT NULL COMMENT '0不扶持，1扶持',
    `detail_status`   int(10)     NOT NULL COMMENT '扶持细分状态',
    `priority_bucket` varchar(20) NOT NULL COMMENT '优先级分桶',
    `custom_priority` varchar(20) NOT NULL COMMENT '自定义优先级',
    `source_tag`      varchar(20) NOT NULL COMMENT '标记是历史回捞还是天级更新的',
    `ctime`           timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime`           timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT ='商品扶持状态表';

create table `interpose_spu_operate_log`
(
    `id`             bigint(20) NOT NULL COMMENT 'spuId',
    `operate_type`   int(10)    NOT NULL COMMENT '操作类型',
    `operate_detail` text       NOT NULL COMMENT '操作明细',
    `operate_result` text       NOT NULL COMMENT '操作结果',
    `operate_status` int(10)    NOT NULL COMMENT '操作结果状态.0成功，1失败',
    `ctime`          timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT ='商品扶操作log表';