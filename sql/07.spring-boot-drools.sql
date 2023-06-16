-- ----------------------------
-- 规则表
-- ----------------------------
DROP TABLE IF EXISTS `sys_rule`;
CREATE TABLE `sys_rule`
(
    `id`          varchar(36)  NOT NULL COMMENT 'ID',
    `name`        varchar(255) NOT NULL COMMENT '名称',
    `content`     text NULL DEFAULT NULL COMMENT '描述',
    `create_by`   varchar(255) NULL DEFAULT NULL COMMENT '创建者',
    `update_by`   varchar(255) NULL DEFAULT NULL COMMENT '更新者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '规则表';


