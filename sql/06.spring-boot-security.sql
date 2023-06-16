-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          varchar(36) NOT NULL COMMENT '主键id',
    `username`    varchar(100) NULL DEFAULT NULL COMMENT '登录账号',
    `realname`    varchar(100) NULL DEFAULT NULL COMMENT '真实姓名',
    `password`    varchar(255) NULL DEFAULT NULL COMMENT '密码',
    `salt`        varchar(45) NULL DEFAULT NULL COMMENT 'md5密码盐',
    `avatar`      varchar(255) NULL DEFAULT NULL COMMENT '头像',
    `birthday`    varchar(64) NULL DEFAULT NULL COMMENT '生日',
    `sex`         tinyint(1) NULL DEFAULT NULL COMMENT '性别(0-默认未知,1-男,2-女)',
    `email`       varchar(45) NULL DEFAULT NULL COMMENT '电子邮件',
    `phone`       varchar(45) NULL DEFAULT NULL COMMENT '电话',
    `status`      tinyint(1) NULL DEFAULT NULL COMMENT '性别(1-正常,2-冻结)',
    `del_flag`    tinyint(1) NULL DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
    `create_by`   varchar(32) NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(32) NULL DEFAULT NULL COMMENT '更新人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_username`(`username`) USING BTREE,
    INDEX         `idx_status`(`status`) USING BTREE,
    INDEX         `idx_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB COMMENT = '用户表';

-- ----------------------------
-- 角色表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          varchar(36)  NOT NULL COMMENT 'ID',
    `code`        varchar(255) NOT NULL COMMENT '角色编码',
    `name`        varchar(255) NOT NULL COMMENT '名称',
    `description` varchar(255) NULL DEFAULT NULL COMMENT '描述',
    `create_by`   varchar(255) NULL DEFAULT NULL COMMENT '创建者',
    `update_by`   varchar(255) NULL DEFAULT NULL COMMENT '更新者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '角色表';

-- ----------------------------
-- 用户角色表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_role`
(
    `id`      varchar(36) NOT NULL COMMENT 'ID',
    `user_id` varchar(36) NOT NULL COMMENT '用户ID',
    `role_id` varchar(36) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '用户角色表';


