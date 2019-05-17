/*
 Navicat Premium Data Transfer

 Source Server         : 本地docker
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : 192.168.93.132:3306
 Source Schema         : yxssm

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 24/03/2019 17:51:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_m_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_m_config`;
CREATE TABLE `sys_m_config`  (
  `CONFIG_SID` bigint(15) NOT NULL COMMENT '配置SID',
  `CONFIG_TYPE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置类型',
  `CONFIG_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置名称',
  `CONFIG_KEY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置Key',
  `CONFIG_VALUE` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DATA_TYPE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据类型',
  `DISPLAY_TYPE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示类型',
  `UNIT` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性能单位',
  `VALUE_DOMAIN` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '取值区域',
  `VALUE_INCREMENT` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '取值增量',
  `SORT_RANK` int(11) NULL DEFAULT NULL COMMENT '显示顺序',
  `DESCRIPTION` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置描述',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_DT` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `VERSION` bigint(9) NOT NULL DEFAULT 1 COMMENT '版本号',
  PRIMARY KEY (`CONFIG_SID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_m_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_m_role`;
CREATE TABLE `sys_m_role`  (
  `role_sid` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '角色SID',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `role_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '角色状态 (0:禁用，1:有效，9:锁定)',
  `role_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色类型(platform:平台角色;project:项目角色)',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据范围',
  `is_sys` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否系统内置角色',
  `org_sid` bigint(15) NULL DEFAULT NULL COMMENT '组织SID',
  `module_category` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认访问模块',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_dt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` bigint(9) NOT NULL DEFAULT 1 COMMENT '版本号',
  PRIMARY KEY (`role_sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 226 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_m_role
-- ----------------------------
INSERT INTO `sys_m_role` VALUES (100, '系统管理员', 'SYS_ADMIN', '系统内置角色，不可删除', '1', 'system', '1', '1', NULL, 'sys', 'admin', '2018-09-30 03:54:06', 'admin', '2018-09-30 03:54:06', 1);
INSERT INTO `sys_m_role` VALUES (101, '组织管理员', 'ORG_ADMIN', '系统内置角色，不可删除', '1', 'platform', '2', '1', NULL, 'mgt', 'admin', '2018-09-30 03:54:06', 'admin', '2018-09-30 03:54:06', 1);
INSERT INTO `sys_m_role` VALUES (203, '项目管理员', 'PROJECT_ADMIN', '系统内置角色，不可删除', '1', 'project', '3', '1', NULL, 'mgt', 'admin', '2018-09-30 03:54:06', 'admin', '2018-09-30 03:54:06', 1);
INSERT INTO `sys_m_role` VALUES (204, '项目用户', 'ORDINARY_USER ', '系统内置角色，不可删除', '1', 'project', '8', '1', NULL, 'self', 'admin', '2018-09-30 03:54:06', 'admin', '2018-09-30 03:54:06', 1);
INSERT INTO `sys_m_role` VALUES (206, '流程管理员', 'process_admin', '测试', '1', 'platform', '2', '0', 66666739, 'mgt', 'admin', '2018-12-18 16:36:06', 'admin', '2018-12-28 17:13:30', 1);
INSERT INTO `sys_m_role` VALUES (207, '测试主机列表', 'test_', '', '1', 'platform', '2', '0', 66666739, 'mgt', 'admin', '2018-12-18 17:03:00', 'admin', '2018-12-18 17:03:00', 1);
INSERT INTO `sys_m_role` VALUES (208, '自定义-组织管理员', 'zcq', '', '1', 'platform', '2', '0', 66666739, 'anas', 'admin', '2018-12-20 14:41:35', 'admin', '2019-03-07 14:12:33', 1);
INSERT INTO `sys_m_role` VALUES (209, '新项目管理', 'zcqa', '', '1', 'platform', '2', '0', 66666739, 'mgt', 'admin', '2018-12-21 11:01:45', 'admin', '2018-12-21 11:01:45', 1);
INSERT INTO `sys_m_role` VALUES (210, 'tesyldeng', 'tesyldeng', '', '1', 'platform', '2', '0', 1, 'mgt', 'admin', '2018-12-24 09:58:29', 'admin', '2018-12-24 09:58:29', 1);
INSERT INTO `sys_m_role` VALUES (211, '控制助手、作业编排', 'TEST_GWWF', '测试控制助手、作业编排', '1', 'platform', '2', '0', 66666739, 'mgt', 'admin', '2018-12-24 16:11:35', 'admin', '2018-12-24 16:11:35', 1);
INSERT INTO `sys_m_role` VALUES (212, '子验证组织角色', 'zyzrole', '', '1', 'platform', '2', '0', 66666916, 'mgt', 'admin', '2018-12-24 17:19:56', 'admin', '2018-12-24 17:19:56', 1);
INSERT INTO `sys_m_role` VALUES (213, 'qx项目', 'qxxm', '', '1', 'platform', '2', '0', 66666934, 'mgt', 'admin', '2018-12-25 20:21:32', 'admin', '2018-12-25 20:21:32', 1);
INSERT INTO `sys_m_role` VALUES (214, 'qx项目2', 'qxxmm', '', '1', 'platform', '3', '0', 66666934, 'mgt', 'admin', '2018-12-25 20:22:00', 'admin', '2018-12-25 20:22:00', 1);
INSERT INTO `sys_m_role` VALUES (215, '我的审批专员', 'my_process', '', '1', 'platform', '2', '0', 66667026, 'mgt', 'admin', '2018-12-28 17:14:09', 'admin', '2018-12-28 17:14:09', 1);
INSERT INTO `sys_m_role` VALUES (216, 'aaa', 'aaa', '', '1', 'platform', '2', '0', 1, 'mgt', 'admin', '2019-01-02 10:50:17', 'admin', '2019-01-02 10:50:17', 1);
INSERT INTO `sys_m_role` VALUES (218, '审批专员0103', 'sony_process', '', '1', 'platform', '2', '0', 66667013, 'mgt', 'admin', '2019-01-03 11:10:50', 'admin', '2019-01-03 11:10:50', 1);
INSERT INTO `sys_m_role` VALUES (219, '审批专员B', 'processB', '', '1', 'platform', '2', '0', 66667013, 'mgt', 'admin', '2019-01-03 11:22:43', 'admin', '2019-01-03 11:22:43', 1);
INSERT INTO `sys_m_role` VALUES (220, '审批专员C', 'processC', '', '1', 'platform', '2', '0', 66667013, 'mgt', 'admin', '2019-01-03 11:56:52', 'admin', '2019-01-03 11:56:52', 1);
INSERT INTO `sys_m_role` VALUES (221, '自定义角色3', 'processD', '', '1', 'platform', '2', '0', 66667013, 'mgt', 'admin', '2019-01-03 12:47:47', 'admin', '2019-01-03 12:47:47', 1);
INSERT INTO `sys_m_role` VALUES (222, '自定义审批1', 'Approve_A', '', '1', 'platform', '2', '0', 66666962, 'mgt', 'admin', '2019-01-04 10:36:22', 'admin', '2019-01-04 10:36:22', 1);
INSERT INTO `sys_m_role` VALUES (223, '自定义审批B', 'Approve_B', '', '1', 'platform', '2', '0', 66666962, 'mgt', 'admin', '2019-01-04 10:36:40', 'admin', '2019-01-04 10:36:40', 1);
INSERT INTO `sys_m_role` VALUES (224, 'testswq', 'testswq', '', '1', 'platform', '2', '0', 66667103, 'mgt', 'admin', '2019-01-23 17:29:18', 'admin', '2019-01-23 17:29:18', 1);
INSERT INTO `sys_m_role` VALUES (225, '0122角色', 'read_only', '', '1', 'platform', '2', '0', 66667085, 'self', 'admin', '2019-02-18 18:20:32', 'admin', '2019-02-28 15:41:07', 1);

-- ----------------------------
-- Table structure for sys_m_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_m_user`;
CREATE TABLE `sys_m_user`  (
  `user_sid` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '用户SID',
  `user_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户类型',
  `user_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户编码',
  `account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `password64` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'BASE64密码',
  `real_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别 0:男 1:女',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职务头衔',
  `company_id` bigint(15) NULL DEFAULT NULL COMMENT '企业ID',
  `project_id` bigint(15) NULL DEFAULT NULL COMMENT '项目ID',
  `org_sid` bigint(15) NULL DEFAULT NULL COMMENT '组织ID',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '用户状态（0:禁用，1:有效，2:锁定）',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `error_count` int(2) NULL DEFAULT 0 COMMENT '密码错误次数',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次登录IP地址',
  `service_limit_quantity` int(6) NULL DEFAULT NULL COMMENT '服务限制数量',
  `apply_reason` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请理由',
  `sms_max` int(5) NULL DEFAULT NULL COMMENT '最大短信数',
  `uuid` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `skin_theme` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户偏好主题',
  `auth_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方认证绑定ID',
  `auth_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户认证类型 1. local 2. ad',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_dt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` bigint(9) NULL DEFAULT 1 COMMENT '版本号',
  `head_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 828 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_m_user
-- ----------------------------
INSERT INTO `sys_m_user` VALUES (100, '03', NULL, 'admin', 'c7eac214f657fa2ddd7360cce91a32be', 'MXEydzNlNHI=', '超级管理员', NULL, '', '15123919362', NULL, NULL, NULL, NULL, '1', NULL, 0, '2019-03-21 10:09:22', '192.168.93.1', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '', '2018-09-04 11:26:58', 'admin', '2019-03-21 10:09:22', 1, 'default/irbvvdzt.jpg');
INSERT INTO `sys_m_user` VALUES (410, '03', NULL, 'dengyanan', 'd95ad6a2c6456cb33fd1d671515b7fcc', 'MXEydzNlNHI=', 'dengyanan', NULL, 'dengyanan@cloud-star.com.cn', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2019-03-05 11:08:07', '10.102.10.131', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-19 10:12:29', 'admin', '2019-03-05 11:08:07', 1, NULL);
INSERT INTO `sys_m_user` VALUES (411, '03', NULL, 'baiwei', 'e0c3db3d472c8de671d11cc6a62a8892', 'MXEydzNlNHI=', 'baiwei', NULL, 'baiwei@cloud-star.com.cn', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2019-02-14 15:09:38', '10.102.10.130', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-19 10:42:36', 'admin', '2019-02-14 15:09:38', 1, NULL);
INSERT INTO `sys_m_user` VALUES (413, '03', NULL, 'tys', '08f5965140998e724c07666562936569', 'MXEydzNlNHI=', 'taoyongshan', NULL, 'tys@qq.com', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, '2019-01-24 13:38:43', '10.102.10.114', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-19 14:43:34', 'admin', '2019-01-24 13:38:43', 1, NULL);
INSERT INTO `sys_m_user` VALUES (415, '03', NULL, 'rctest1', 'e24404f08aa23f933f0cb57bd1fee518', 'MXEydzNlNHI=', 'rctest1', NULL, 'rctest1@admin.com', '13333333333', NULL, 66666704, NULL, NULL, '1', NULL, 0, '2019-01-09 18:44:29', '10.102.10.119', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-20 14:35:07', 'admin', '2019-03-24 13:04:19', 1, NULL);
INSERT INTO `sys_m_user` VALUES (422, '03', NULL, 'wangyu', '4d77402893f1bf0c858eb8fdb950d2f0', 'MXEydzNlNHI=', 'wangyu', NULL, 'wangyu@cloud-star.com.cn', '15123919362', NULL, 1, NULL, NULL, '1', NULL, 0, '2019-02-22 15:49:40', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-22 13:56:16', 'admin', '2019-02-22 15:49:40', 1, NULL);
INSERT INTO `sys_m_user` VALUES (443, '03', NULL, 'zcq5', '6854ad8e5fe7bc2e0b266a5960cd0c1f', 'MXEydzNlNHI=', 'zcq5', NULL, 'zcq5@qq.com', '13232323232', NULL, 66666706, NULL, NULL, '1', NULL, 0, '2019-02-12 11:14:02', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'zcq1', '2018-11-24 17:13:20', 'admin', '2019-03-24 13:10:16', 1, NULL);
INSERT INTO `sys_m_user` VALUES (446, '03', NULL, 'zcq1', '4f832e95bbde103e8954c31c6cf4af0c', 'MXEydzNlNHI=', 'zcq1', NULL, 'zcq1@qq.com', '13223232323', NULL, 66666726, NULL, NULL, '1', NULL, 0, '2019-02-22 16:42:15', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'zcq5', '2018-11-26 10:17:51', 'admin', '2019-03-24 13:23:28', 1, NULL);
INSERT INTO `sys_m_user` VALUES (447, '03', NULL, 'wangchao', '4480a2f02a8ebcf1266895e4ec54c29f', 'MXEydzNlNHI=', 'wangchao', NULL, 'wangchao@cloud-star.com.cn', '17772450134', NULL, 1, NULL, NULL, '1', NULL, 0, '2019-03-19 18:22:44', '192.168.93.1', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-26 10:21:47', 'admin', '2019-03-24 13:31:54', 1, NULL);
INSERT INTO `sys_m_user` VALUES (448, '03', NULL, 'huanghuajun', '2e0c2f5fef320d20dc6c3ab7c2afd886', 'MXEydzNlNHI=', 'huanghuajun', NULL, 'huanghuajun@cloud-star.com.cn', NULL, NULL, 66666949, NULL, NULL, '1', NULL, 0, '2019-01-21 11:18:22', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-26 10:35:32', 'admin', '2019-01-21 11:18:22', 1, NULL);
INSERT INTO `sys_m_user` VALUES (449, '03', NULL, 'ddd', 'b35d5341ab97458e12678f565dfaa107', 'MXEydzNlNHI=', 'ddd', NULL, 'ddd@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2019-01-09 11:17:19', '10.102.10.104', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-26 11:47:43', 'admin', '2019-01-09 11:17:19', 1, NULL);
INSERT INTO `sys_m_user` VALUES (450, '03', NULL, 'yldeng', '0bd4abb29406c6ade4c20ffcd460a4fd', 'MXEydzNlNHI=', 'yldeng', NULL, 'yldeng@qq.com', NULL, NULL, 66666795, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'wangchao', '2018-11-26 12:44:33', 'wangchao', '2018-11-26 12:44:33', 1, NULL);
INSERT INTO `sys_m_user` VALUES (451, '03', NULL, 'tangtt1126', '27da6db4a819fcde606617ff98e6c3e9', 'MXEydzNlNHI=', 'tangtt1126', NULL, 'tangtt1126@qq.com', '', NULL, 66666722, 217, NULL, '1', NULL, 0, '2019-01-14 10:30:36', '10.102.10.113', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-26 16:20:48', 'admin', '2019-03-24 13:42:02', 1, NULL);
INSERT INTO `sys_m_user` VALUES (467, '03', NULL, 'autoops', 'f8535671202731e9108a7cefb2b43398', 'MXEydzNlNHI=', 'autoops', NULL, 'autoops@qq.com', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, '2019-02-21 16:09:31', '10.102.10.122', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-29 13:49:45', 'admin', '2019-02-21 16:09:31', 1, NULL);
INSERT INTO `sys_m_user` VALUES (468, '03', NULL, 'sindy', '0bbc98bd6068e634b0d6bb5ff9ee17b0', 'MXEydzNlNHI=', 'sindy', NULL, 'sindy@qq.com', '13435353531', NULL, 1, 217, NULL, '1', NULL, 0, '2019-01-29 10:00:51', '10.102.10.104', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-29 14:34:22', 'admin', '2019-01-29 10:00:51', 1, NULL);
INSERT INTO `sys_m_user` VALUES (469, '03', NULL, 'yhw1130', '1f8b72873486e0f152bc3ecc2fca2cd6', 'MXEydzNlNHI=', 'yhw1130', NULL, 'yhw1130@qq.com', '18680896213', NULL, 1, NULL, NULL, '1', NULL, 0, '2019-01-04 13:23:53', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-11-30 10:26:41', 'admin', '2019-01-04 13:23:53', 1, NULL);
INSERT INTO `sys_m_user` VALUES (474, '03', NULL, 'yhw1203A', '32d4ce66bdf5f169009e6ac534b5c07e', 'MXEydzNlNHI=', 'yhw1203A', NULL, 'yhw1203A@qq.com', '18797856473', NULL, 66666722, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1130', '2018-12-03 15:13:27', 'admin', '2018-12-04 20:07:31', 1, NULL);
INSERT INTO `sys_m_user` VALUES (475, '03', NULL, 'yhw1203B', 'd3c5cfbcff62c7df511243bbeeb27f30', 'MXEydzNlNHI=', 'yhw1203B', NULL, 'yhw1203B@qq.com', '18975647897', NULL, 66666722, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1130', '2018-12-03 15:31:26', 'admin', '2018-12-04 20:07:19', 1, NULL);
INSERT INTO `sys_m_user` VALUES (477, '03', NULL, 'yhw1205', '728cd35ef08802f658e9afb766a7f3b3', 'MXEydzNlNHI=', 'yhw1205', NULL, 'yhw1205@qq.com', '18976574834', NULL, 66666722, NULL, NULL, '1', NULL, 0, '2018-12-26 15:50:00', '10.102.10.121', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'tangtt1126', '2018-12-05 09:49:57', 'admin', '2018-12-26 15:50:00', 1, NULL);
INSERT INTO `sys_m_user` VALUES (480, '03', NULL, 'zz123', 'bedb43e42777ef77d200867a665d3651', 'MXEydzNlNHI=', 'zz123', NULL, 'zz123@qq.com', NULL, NULL, 66666726, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy', '2018-12-05 14:43:47', 'sindy', '2018-12-05 14:43:47', 1, NULL);
INSERT INTO `sys_m_user` VALUES (482, '03', NULL, 'sh', 'de1a2d41a1ba4b1e7cf7bb57abcda5cc', 'MXEydzNlNHI=', 'www', NULL, 'sh@qq.com', '', NULL, 1, 66666727, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy', '2018-12-05 14:54:49', 'sindy', '2018-12-05 14:54:49', 1, NULL);
INSERT INTO `sys_m_user` VALUES (483, '03', NULL, 'eee', '08c86f7af3a540ac4ed14e371f290369', 'MXEydzNlNHI=', 'eeee', NULL, 'eee@qq.com', '', NULL, 66666795, 66666727, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy', '2018-12-05 14:55:10', 'sindy', '2018-12-05 14:55:10', 1, NULL);
INSERT INTO `sys_m_user` VALUES (484, '03', NULL, 'rrrQ', '66fd170b25e48bea20c1ea9ae6a8f9c2', 'MXEydzNlNHI=', 'rrrr', NULL, 'rrrQ@qq.com', '', NULL, 1, 66666727, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy', '2018-12-05 14:55:39', 'sindy', '2018-12-05 14:55:39', 1, NULL);
INSERT INTO `sys_m_user` VALUES (485, '03', NULL, 'ww', '47f5090f9c7c6fb06fe1777e8a0b5eeb', 'MXEydzNlNHI=', 'wwww', NULL, 'ww@qq.com', '', NULL, 1, 66666727, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy', '2018-12-05 14:58:02', 'sindy', '2018-12-05 14:58:02', 1, NULL);
INSERT INTO `sys_m_user` VALUES (487, '03', NULL, 'zengyong', '457c0c19e72d822e03221ce8587a4cd8', 'MXEydzNlNHI=', 'zengyong', NULL, 'zengyong@qq.com', '13299999999', NULL, 66666722, NULL, NULL, '1', NULL, 0, '2019-01-21 16:32:36', '10.102.10.100', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-05 17:15:52', 'admin', '2019-01-21 16:32:36', 1, NULL);
INSERT INTO `sys_m_user` VALUES (488, '03', NULL, '940787285', '381340850d29e449d98fb684cf4625d2', 'MXEydzNlNHI=', 'test-jpg', NULL, '940787285@qq.com', '17701314977', NULL, 66666726, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-05 17:33:31', 'admin', '2018-12-05 17:33:31', 1, NULL);
INSERT INTO `sys_m_user` VALUES (489, '03', NULL, 'testjpg', '999f6643634a632b916655d80bdb2622', 'MXEydzNlNHI=', 'test-jpg', NULL, 'testjpg@hp.com', '', NULL, 1, 66666727, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy', '2018-12-05 17:37:25', 'sindy', '2018-12-05 17:37:25', 1, NULL);
INSERT INTO `sys_m_user` VALUES (497, '03', NULL, 'hy', '1160d63e776a869d6324b9babb9c1b04', 'aHVhbmd5YW4xMjM0', 'hyy', NULL, '397040843@qq.com', '15683995958', NULL, 66666726, NULL, NULL, '1', NULL, 0, '2019-01-18 09:37:27', '10.102.10.190', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-06 14:56:35', 'admin', '2019-01-18 09:37:27', 1, NULL);
INSERT INTO `sys_m_user` VALUES (500, '03', NULL, 'zhucaiqian', '164c2f481064855804325497797f486c', 'MXEydzNlNHI=', 'zhucaiqian', NULL, 'zhucaiqian@cloud-star.com.cn', '13222111111', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2019-03-04 11:47:20', '10.102.10.103', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-06 15:20:35', 'admin', '2019-03-04 11:47:20', 1, NULL);
INSERT INTO `sys_m_user` VALUES (502, '03', NULL, 'yhw1206', '511bb1d807d39f9748ef8d2c86ff8d7b', 'MXEydzNlNHI=', 'yhw1206', NULL, 'yhw1206@qq.com', '18975647834', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-28 19:17:37', '10.102.10.169', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'zhucaiqian', '2018-12-06 19:31:15', 'admin', '2018-12-28 19:17:37', 1, NULL);
INSERT INTO `sys_m_user` VALUES (504, '03', NULL, '123', '5babdb4dfdcf6b77c78937cc8014a97a', 'MXEydzNlNHI=', 'hy123', NULL, '123@qq.com', NULL, NULL, 66666726, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy', '2018-12-07 11:37:05', 'sindy', '2018-12-07 11:37:05', 1, NULL);
INSERT INTO `sys_m_user` VALUES (506, '03', NULL, 'yangqin1207', '0666d32b35bca706db66a5c65d5283b1', 'MXEydzNlNHI=', 'yangqin', NULL, 'yangqin1207@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-28 10:23:12', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-07 11:43:13', 'admin', '2018-12-28 10:23:12', 1, NULL);
INSERT INTO `sys_m_user` VALUES (508, '03', NULL, 'zhucaiqian1', 'df4d281a76d2dded5285fc428cbd4c4d', 'MXEydzNlNHI=', 'zhucaiqian1', NULL, 'zhucaiqian1@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-12 10:02:33', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-07 13:24:52', 'admin', '2018-12-12 10:02:33', 1, NULL);
INSERT INTO `sys_m_user` VALUES (509, '03', NULL, 'zhucaiqian2', '310301b7d58f0050f1f408b0178f5a29', 'MXEydzNlNHI=', 'zhucaiqian2', NULL, 'zhucaiqian2@qq.com', NULL, NULL, 66666743, NULL, NULL, '1', NULL, 0, '2018-12-21 15:00:25', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-07 13:34:59', 'admin', '2018-12-21 15:00:25', 1, NULL);
INSERT INTO `sys_m_user` VALUES (513, '03', NULL, 'cc123', '434f31e545a44dfdb9bca72568156b6e', 'MXEydzNlNHI=', 'cc123', NULL, 'cc123@qq.com', NULL, NULL, 66666739, NULL, NULL, '0', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-08 17:33:39', 'admin', '2018-12-08 17:34:16', 1, NULL);
INSERT INTO `sys_m_user` VALUES (518, '03', NULL, 'yangqin', '76e700d4d21917f5e294dced40bb2ca8', 'MXEydzNlNHI=', 'yangqin', NULL, 'yangqin@126.com', '', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2019-03-07 10:41:26', '10.102.10.163', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-11 10:12:03', 'admin', '2019-03-07 10:41:26', 1, NULL);
INSERT INTO `sys_m_user` VALUES (519, '03', NULL, 'yhw1211A', '5a5b29c3e7872aa1291872e24158e001', 'MXEydzNlNHI=', 'yhw1211A', NULL, 'yhw1211A@qq.com', '18975647854', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2019-02-18 14:08:45', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1206', '2018-12-11 10:14:58', 'admin', '2019-02-18 14:08:45', 1, NULL);
INSERT INTO `sys_m_user` VALUES (520, '03', NULL, 'yhw1211PA', 'c4dad49620a9dc20e1054395c1a7d937', 'MXEydzNlNHI=', 'yhw1211PA', NULL, 'yhw1211PA@qq.com', '18975647584', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-27 19:40:27', '10.102.10.131', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1206', '2018-12-11 10:15:30', 'admin', '2018-12-27 19:40:27', 1, NULL);
INSERT INTO `sys_m_user` VALUES (521, '03', NULL, 'yhw1211U', '11624ce4ea68ce0c8408d05cb1836753', 'MXEydzNlNHI=', 'yhw1211U', NULL, 'yhw1211U@qq.com', '18865748395', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-27 19:40:12', '10.102.10.131', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1206', '2018-12-11 10:16:04', 'admin', '2018-12-27 19:40:12', 1, NULL);
INSERT INTO `sys_m_user` VALUES (523, '03', NULL, 'link', '3e915fc17277c540e29e431c5a76af7c', 'MXEydzNlNHI=', '12月11日用户', NULL, 'link@qq.com', '13322221111', NULL, 66666750, NULL, NULL, '1', NULL, 0, '2018-12-12 15:44:06', '10.102.10.127', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-11 18:00:40', 'admin', '2018-12-12 15:44:06', 1, NULL);
INSERT INTO `sys_m_user` VALUES (525, '03', NULL, 'sindy2', '8a989bd864bcdd5ecae76282b8fec282', 'MXEydzNlNHI=', 'sindy2', NULL, 'sindy2@qq.com', '', NULL, 66666739, 66666744, NULL, '1', NULL, 0, '2018-12-15 02:46:36', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'zhucaiqian', '2018-12-12 10:03:48', 'admin', '2018-12-15 02:46:36', 1, NULL);
INSERT INTO `sys_m_user` VALUES (533, '03', NULL, 'huangyan2', '0fe875f794e3f9b432b146378ca813fa', 'MXEydzNlNHI=', 'hy1', NULL, 'huangyan@qq.com', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, '2018-12-19 14:14:41', '10.102.10.190', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy', '2018-12-12 13:44:49', 'admin', '2018-12-19 14:14:41', 1, NULL);
INSERT INTO `sys_m_user` VALUES (534, '03', NULL, 'huangyan3', '7af3f7cbc677f7cadf9594edf49bf817', 'MXEydzNlNHI=', 'huangyan', NULL, 'huangyan@qq.com', '13299867450', NULL, 1, 66666727, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy', '2018-12-12 13:46:47', 'sindy', '2018-12-13 10:01:32', 1, NULL);
INSERT INTO `sys_m_user` VALUES (536, '03', NULL, 'z123', '4ebcce2d7301d12cad69422753e8f347', 'MXEydzNlNHI=', 'z123', NULL, 'z123@qq.com', '13232323238', NULL, 66666726, NULL, NULL, '1', NULL, 0, '2018-12-12 15:18:21', '10.102.10.190', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-12 14:34:57', 'sindy', '2018-12-13 10:09:12', 1, NULL);
INSERT INTO `sys_m_user` VALUES (538, '03', NULL, 'test1212', '30a4de2e6e54891fdcb056ef7bb6023a', 'MXEydzNlNHI=', 'test1212', NULL, 'test1212@qq.com', '13322221111', NULL, 66666759, NULL, NULL, '1', NULL, 0, '2019-03-05 09:32:49', '10.102.10.149', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-12 15:43:25', 'admin', '2019-03-05 09:32:49', 1, NULL);
INSERT INTO `sys_m_user` VALUES (544, '03', NULL, 'h123', '6f8d6c7207c516cd007b0cc22aa212b8', 'MXEydzNlNHI=', 'h123', NULL, 'h123@qq.com', NULL, NULL, 66666722, NULL, NULL, '1', NULL, 0, '2018-12-13 10:26:13', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy', '2018-12-13 10:26:03', 'admin', '2018-12-13 10:26:13', 1, NULL);
INSERT INTO `sys_m_user` VALUES (549, '03', NULL, 'testa', 'feb0ca1832670729af2dca5aaf847780', 'MXEydzNlNHI=', 'testa', NULL, 'testa@qq.com', NULL, NULL, 66666762, NULL, NULL, '1', NULL, 0, '2018-12-24 16:03:17', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-13 10:35:41', 'admin', '2018-12-24 16:03:17', 1, NULL);
INSERT INTO `sys_m_user` VALUES (550, '03', NULL, 'testb', 'b6d6fa85243318c0d3dab449946639f2', 'MXEydzNlNHI=', 'testb', NULL, 'testb@qq.com', NULL, NULL, 66666762, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'testa', '2018-12-13 10:57:48', 'testa', '2018-12-13 10:57:48', 1, NULL);
INSERT INTO `sys_m_user` VALUES (551, '03', NULL, 'testc', 'c2c01e61f6a62dc9f8487d4eb3ffa3c2', 'MXEydzNlNHI=', 'testc', NULL, 'testc@qq.com', NULL, NULL, 66666722, NULL, NULL, '1', NULL, 0, '2018-12-13 11:13:12', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-13 11:01:06', 'admin', '2018-12-13 11:13:12', 1, NULL);
INSERT INTO `sys_m_user` VALUES (554, '03', NULL, 'zjsadmin', '9e294bef165ec47342d433dd310bee68', 'MXEydzNlNHI=', 'zjsadmin', NULL, 'zjsadmin@cloud-star.com.cn', NULL, NULL, 66666778, NULL, NULL, '1', NULL, 0, '2018-12-13 18:01:42', '10.102.10.108', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'wxb', '2018-12-13 15:25:10', 'admin', '2018-12-13 18:01:42', 1, NULL);
INSERT INTO `sys_m_user` VALUES (555, '03', NULL, 'zjsuser', 'bcf77b72b1695ce4e98f48136cac81e2', 'MXEydzNlNHI=', 'zjsuser', NULL, 'zjsuser@cloud-star.com.cn', NULL, NULL, 66666778, NULL, NULL, '1', NULL, 0, '2018-12-13 17:50:11', '10.102.10.121', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'wxb', '2018-12-13 15:25:40', 'zjsadmin', '2018-12-13 17:50:11', 1, NULL);
INSERT INTO `sys_m_user` VALUES (556, '03', NULL, '222', '2e69f9a9967170b128d47a45eeb9047c', 'MXEydzNlNHI=', '222', NULL, '222@qq.com', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, '2018-12-14 10:19:57', '10.102.10.190', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-14 10:19:42', 'admin', '2018-12-14 10:19:57', 1, NULL);
INSERT INTO `sys_m_user` VALUES (558, '03', NULL, 'test1214', '5c962aacdf5318738d756b2db4532ed8', 'MXEydzNlNHI=', 'test1214', NULL, 'test1214@qq.com', '13344442222', NULL, 66666783, NULL, NULL, '1', NULL, 0, '2019-01-10 00:44:45', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-14 14:24:41', 'admin', '2019-01-10 00:44:45', 1, NULL);
INSERT INTO `sys_m_user` VALUES (559, '03', NULL, 'sindy3', '5ad2d09255043bb6be850096f7a80e40', 'MXEydzNlNHI=', 'sindy3', NULL, 'sindy3@qq.com', '', NULL, 66666739, 66666785, NULL, '1', NULL, 0, '2018-12-15 02:47:47', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy2', '2018-12-15 02:47:38', 'admin', '2018-12-15 02:47:47', 1, NULL);
INSERT INTO `sys_m_user` VALUES (561, '03', NULL, 'test1215', '481259118fbfd1fb6750d817a7792aca', 'MXEydzNlNHI=', 'test1215', NULL, 'test1215@qq.com', '13322221111', NULL, 66666788, NULL, NULL, '1', NULL, 0, '2018-12-15 13:35:54', '10.102.10.127', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-15 13:35:48', 'admin', '2018-12-15 13:35:54', 1, NULL);
INSERT INTO `sys_m_user` VALUES (564, '03', NULL, 'hl1', 'daa29a487de1168a3d96973d41824e8e', 'MXEydzNlNHI=', 'hl1', NULL, 'hl1@qq.com', '', NULL, 66666720, 66666730, NULL, '1', NULL, 0, '2018-12-15 18:53:55', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'hello', '2018-12-15 18:48:44', 'admin', '2018-12-15 18:53:55', 1, NULL);
INSERT INTO `sys_m_user` VALUES (566, '03', NULL, 'test1217', '7f2644d4aeff7df833f8220a6d8e2fd6', 'MXEydzNlNHI=', 'test1217', NULL, 'test1217@qq.com', '13322224444', NULL, 66666790, NULL, NULL, '1', NULL, 0, '2018-12-17 17:51:00', '10.102.10.145', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-17 11:48:41', 'admin', '2018-12-17 17:51:00', 1, NULL);
INSERT INTO `sys_m_user` VALUES (568, '03', NULL, 'yhw1217U', '42b9f3b29a1f83f245889e38e8b952e2', 'MXEydzNlNHI=', 'yhw1217U', NULL, 'yhw1217U@qq.com', '18965748543', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-27 19:39:48', '10.102.10.131', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1211A', '2018-12-17 14:52:45', 'admin', '2018-12-27 19:39:48', 1, NULL);
INSERT INTO `sys_m_user` VALUES (569, '03', NULL, 'yhw1217PA', 'b0f1b4f5d13bc76433c2f22052b9a470', 'MXEydzNlNHI=', 'yhw1217PA', NULL, 'yhw1217PA@qq.com', '18975846375', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-27 19:39:29', '10.102.10.131', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1211A', '2018-12-17 14:53:13', 'admin', '2018-12-27 19:39:29', 1, NULL);
INSERT INTO `sys_m_user` VALUES (570, '03', NULL, 'yhw1217A', '72549ab246b71247a8f0c0ee0b02c240', 'MXEydzNlNHI=', 'yhw1217A', NULL, 'yhw1217A@qq.com', '18675846754', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-27 18:40:58', '10.102.10.154', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1211A', '2018-12-17 15:40:26', 'admin', '2018-12-27 18:40:58', 1, NULL);
INSERT INTO `sys_m_user` VALUES (571, '03', NULL, 'huangyan4', '31b2c69169a4b630af3ebcc03e9e49ca', 'MXEydzNlNHI=', 'huangyan', NULL, 'huangyan@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-19 14:15:16', '10.102.10.190', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'zhucaiqian', '2018-12-17 16:23:36', 'admin', '2018-12-25 14:56:31', 1, NULL);
INSERT INTO `sys_m_user` VALUES (572, '03', NULL, 'ORGuser', '716a5c2e4bc73284e92c7eb823fb4743', 'MXEydzNlNHI=', 'ORGuser', NULL, 'ORGuser@12.com', '', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-24 19:23:07', '10.102.10.108', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yangqin', '2018-12-17 16:29:57', 'admin', '2018-12-24 19:23:07', 1, NULL);
INSERT INTO `sys_m_user` VALUES (573, '03', NULL, 'tangtt1217', 'a4b477b79b451addc8dbb26b22bfc29e', 'MXEydzNlNHI=', 'tangtt1217', NULL, 'tangtt1217@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-29 11:51:07', '10.102.10.107', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-17 17:10:20', 'admin', '2018-12-29 11:51:07', 1, NULL);
INSERT INTO `sys_m_user` VALUES (574, '03', NULL, 'ttt1217A', '91901bfa4567b7c911f9ceed8fbb3c9a', 'MXEydzNlNHI=', 'ttt1217A', NULL, 'ttt1217A@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-27 18:45:57', '10.102.10.154', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'tangtt1217', '2018-12-17 17:53:12', 'admin', '2018-12-27 18:45:57', 1, NULL);
INSERT INTO `sys_m_user` VALUES (577, '03', NULL, 'test1218', 'dbf5f9e69d86eeabc970e31ee0f3629b', 'MXEydzNlNHI=', 'test1218', NULL, 'test1218@qq.com', '13322224444', NULL, 66666795, NULL, NULL, '1', NULL, 0, '2018-12-28 17:15:33', '10.102.10.147', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-18 16:31:35', 'admin', '2019-02-20 10:59:33', 1, NULL);
INSERT INTO `sys_m_user` VALUES (578, '03', NULL, '287495160', '8c0f1abdf69d6d7972c1f65da9a557a7', 'MXEydzNlNHI=', 'yhw1218A', NULL, '287495160@qq.com', '18680896213', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-20 17:34:27', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-18 16:37:03', 'admin', '2018-12-20 17:34:27', 1, NULL);
INSERT INTO `sys_m_user` VALUES (579, '03', NULL, 'c12', '430087f193011120b7d1cf450de23c05', 'MXEydzNlNHI=', 'c12', NULL, 'c12@qq.com', NULL, NULL, 66666777, NULL, NULL, '1', NULL, 0, '2018-12-18 16:54:27', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-18 16:53:05', 'admin', '2018-12-18 16:54:27', 1, NULL);
INSERT INTO `sys_m_user` VALUES (580, '03', NULL, 'yhw1218P', '0383b03e02a59dd25600552788e691fe', 'MXEydzNlNHI=', 'yhw1218P', NULL, 'yhw1218P@qq.com', '18965748343', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-28 17:17:31', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1217A', '2018-12-18 17:40:08', 'admin', '2018-12-28 17:17:31', 1, NULL);
INSERT INTO `sys_m_user` VALUES (581, '03', NULL, 'zcq18', '2aaaccb4a5c145ab76695a4931a4877d', 'MXEydzNlNHI=', 'zcq18', NULL, 'zcq18@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-26 13:45:19', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-18 20:00:18', 'admin', '2018-12-26 13:45:19', 1, NULL);
INSERT INTO `sys_m_user` VALUES (582, '03', NULL, 'bug', 'e3cfea7dcc9a675f0fcda28eb86c1773', 'MXEydzNlNHI=', 'bug', NULL, 'bug@12.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-19 11:53:20', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yangqin', '2018-12-19 11:51:37', 'admin', '2018-12-19 11:53:20', 1, NULL);
INSERT INTO `sys_m_user` VALUES (583, '03', NULL, 'yhw1220PA', '123b59140290170657767ff26e7be3b0', 'MXEydzNlNHI=', 'yhw1220PA', NULL, 'yhw1220PA@qq.com', '18976574834', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-20 20:33:02', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1217A', '2018-12-20 13:55:10', 'yhw1220PA', '2018-12-20 20:33:02', 1, NULL);
INSERT INTO `sys_m_user` VALUES (584, '03', NULL, 'yhw1220U', '315f58aaa8902136276592c2378e6132', 'MXEydzNlNHI=', 'yhw1220U', NULL, 'yhw1220U@qq.com', '18965748343', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-26 10:01:38', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1217A', '2018-12-20 13:55:35', 'admin', '2018-12-26 10:01:38', 1, NULL);
INSERT INTO `sys_m_user` VALUES (585, '03', NULL, 'zcq10', 'b6429e7caf6765431546d94bdd5d0eab', 'MXEydzNlNHI=', 'zcq10', NULL, 'zcq10@qq.com', '', NULL, 66666739, 66666744, NULL, '1', NULL, 0, '2018-12-21 14:59:03', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'zhucaiqian', '2018-12-20 13:56:28', 'admin', '2018-12-21 14:59:03', 1, NULL);
INSERT INTO `sys_m_user` VALUES (586, '03', NULL, 'zcq11', 'cda10de5c6cffc60ba617fde67c810e7', 'MXEydzNlNHI=', 'zcq11', NULL, 'zcq11@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-21 14:58:45', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-20 14:49:59', 'zcq18', '2018-12-21 14:58:45', 1, NULL);
INSERT INTO `sys_m_user` VALUES (587, '03', NULL, 'jin002', '351d5d57c0aba4f7577500af99b58459', 'MXEydzNlNHI=', 'jin002', NULL, 'jin002@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-20 16:37:06', '10.102.10.113', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'tangtt1126', '2018-12-20 16:25:36', 'admin', '2018-12-20 16:37:06', 1, NULL);
INSERT INTO `sys_m_user` VALUES (588, '03', NULL, 'zcq15', 'b37d4275fa7b3431b072cd6c9f9d0c74', 'MXEydzNlNHI=', 'zcq15', NULL, 'zcq15@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-22 09:51:46', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-21 11:02:38', 'admin', '2018-12-22 09:51:46', 1, NULL);
INSERT INTO `sys_m_user` VALUES (589, '03', NULL, 'zcq20', 'c894cf79afae0eb2ccdd2f1f7e0da0dc', 'MXEydzNlNHI=', 'zcq20', NULL, 'zcq20@qq.com', NULL, NULL, 66666795, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-22 09:50:53', 'admin', '2018-12-22 09:50:53', 1, NULL);
INSERT INTO `sys_m_user` VALUES (591, '03', NULL, 'wuxiaobing', '46684e2ba8978e7e37cd31ad996d25c5', 'MXEydzNlNHI=', 'wuxiaobing', NULL, 'wuxiaobing@cloud-star.com.cn', '15823571244', NULL, 66666805, NULL, NULL, '1', NULL, 0, '2019-02-28 16:26:10', '10.102.10.119', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-22 12:30:17', 'admin', '2019-02-28 16:26:10', 1, NULL);
INSERT INTO `sys_m_user` VALUES (592, '03', NULL, 'wxb', '1752adf50e31e2389992a6c0479c15dc', 'MXEydzNlNHI=', 'wxb', NULL, 'wxb@cloud-star.com.cn', '13322221111', NULL, 66666805, NULL, NULL, '1', NULL, 0, '2019-02-21 14:13:01', '10.102.10.126', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-22 12:31:38', 'admin', '2019-02-21 14:13:01', 1, NULL);
INSERT INTO `sys_m_user` VALUES (593, '03', NULL, 'rc-admin', 'c18f83a6dfe2dcce847b5173f88bdc50', 'MXEydzNlNHI=', 'rc-admin', NULL, 'rc-admin@cloud-star.com.cn', NULL, NULL, 66666805, NULL, NULL, '1', NULL, 0, '2018-12-25 15:37:03', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'wuxiaobing', '2018-12-22 12:46:40', 'admin', '2018-12-25 15:37:03', 1, NULL);
INSERT INTO `sys_m_user` VALUES (594, '03', NULL, 'rc-user', 'b9808617a3a0fa8393ed3b848cb04bcb', 'MXEydzNlNHI=', 'rc-user', NULL, 'rc-user@cloud-star.com.cn', NULL, NULL, 66666805, NULL, NULL, '1', NULL, 0, '2018-12-25 11:41:40', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'wuxiaobing', '2018-12-22 12:47:12', 'zhucaiqian', '2018-12-25 11:41:40', 1, NULL);
INSERT INTO `sys_m_user` VALUES (595, '03', NULL, 'zcq21', '5fabac64153f84d6a340903d3b5f8d53', 'MXEydzNlNHI=', 'zcq21', NULL, 'zcq21@qq.com', NULL, NULL, 66666805, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'wxb', '2018-12-22 13:41:18', 'wxb', '2018-12-22 13:41:18', 1, NULL);
INSERT INTO `sys_m_user` VALUES (596, '03', NULL, 'sdhadmin', '7f80f2bc56d5427ae9c2163ec8d92904', 'MXEydzNlNHI=', 'sdhadmin', NULL, 'sdhadmin@cloud-star.com.cn', '15823332323', NULL, 66666809, NULL, NULL, '1', NULL, 0, '2018-12-25 08:46:46', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-22 23:28:29', 'yzjkadmin', '2018-12-25 08:46:46', 1, NULL);
INSERT INTO `sys_m_user` VALUES (600, '03', NULL, 'tangttA', '34c78b953ead52eba4827134ea3266da', 'MXEydzNlNHI=', 'tangttA', NULL, 'tangttA@qq.com', '', NULL, 66666954, NULL, NULL, '1', NULL, 0, '2019-03-04 18:41:40', '10.102.10.123', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-23 23:18:08', 'admin', '2019-03-04 18:41:40', 1, NULL);
INSERT INTO `sys_m_user` VALUES (601, '03', NULL, 'tangttB', 'f89bc82b5208b07f87d9d05cd1267a20', 'MXEydzNlNHI=', 'tangttB', NULL, 'tangttB@qq.com', '', NULL, 66666954, NULL, NULL, '1', NULL, 0, '2019-01-17 15:16:26', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-23 23:19:02', 'admin', '2019-01-17 15:16:26', 1, NULL);
INSERT INTO `sys_m_user` VALUES (602, '03', NULL, 'tangttC', '27007cfac87eedbe3b94c96ae1e72115', 'MXEydzNlNHI=', 'tangttC', NULL, 'tangttC@qq.com', '', NULL, 66666954, NULL, NULL, '1', NULL, 0, '2019-02-13 14:56:53', '10.102.10.153', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-23 23:48:04', 'admin', '2019-02-13 14:56:53', 1, NULL);
INSERT INTO `sys_m_user` VALUES (603, '03', NULL, 'huangyan', '05d9e5c4c71277addbb9e53ad9a9b661', 'MXEydzNlNHI=', 'huangyan', NULL, 'huangyan@qq.com', '13299867450', NULL, 1, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'sindy', '2018-12-24 10:17:28', 'sindy', '2018-12-24 10:17:28', 1, NULL);
INSERT INTO `sys_m_user` VALUES (607, '03', NULL, '2572985418', '4821fb3f70e886c28bde9e700ca31fb2', 'MXEydzNlNHI=', 'anjun1', NULL, 'huangyan@cloud-star.com.cn', '13368755623', NULL, 66666726, NULL, NULL, '1', NULL, 0, '2019-02-27 09:48:02', '10.102.10.190', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-24 11:37:26', '2572985418', '2019-02-27 09:48:02', 1, NULL);
INSERT INTO `sys_m_user` VALUES (613, '03', NULL, 'zcq22', 'aaa76f1fdad5a165e1fced14baf48c39', 'MXEydzNlNHI=', 'zcq22', NULL, 'zcq22@qq.com', NULL, NULL, 66666809, NULL, NULL, '1', NULL, 0, '2018-12-29 09:53:32', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-24 11:55:07', 'admin', '2018-12-29 09:53:32', 1, NULL);
INSERT INTO `sys_m_user` VALUES (615, '03', NULL, 'a1', 'a16be11680e97d8e2c4892e8233b1fca', 'MXEydzNlNHI=', 'a11', NULL, 'a1@qq.com', NULL, NULL, 66666910, NULL, NULL, '1', NULL, 0, '2018-12-24 15:05:24', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-24 14:16:56', 'admin', '2018-12-24 15:05:24', 1, NULL);
INSERT INTO `sys_m_user` VALUES (616, '03', NULL, 'test122414', 'ec038f6dd5736f5c52fb54ede6eec32c', 'MXEydzNlNHI=', 'test122414', NULL, 'test122414@qq.com', '13322221212', NULL, 66666912, NULL, NULL, '1', NULL, 0, '2019-03-05 11:06:24', '10.102.10.131', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-24 14:37:04', 'admin', '2019-03-05 11:06:24', 1, NULL);
INSERT INTO `sys_m_user` VALUES (621, '03', NULL, 'zz1231', '59ca13b6dd2f45592efd4731cf40f70a', 'MXEydzNlNHI=', 'zz123', NULL, 'zz123@qq.com', NULL, NULL, 66666726, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'hy', '2018-12-24 16:23:23', 'hy', '2018-12-24 16:23:23', 1, NULL);
INSERT INTO `sys_m_user` VALUES (622, '03', NULL, 'yangsentest121414', '3f7e16cc52480dde1f3fcb3d80f6be0a', 'MXEydzNlNHI=', 'yangsentest121414', NULL, 'yangsentest121414@qq.com', '13322221112', NULL, 66666912, NULL, NULL, '1', NULL, 0, '2018-12-24 16:53:14', '10.102.10.127', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-24 16:52:29', 'admin', '2018-12-24 16:53:14', 1, NULL);
INSERT INTO `sys_m_user` VALUES (623, '03', NULL, 'yzadmin', '4860ff0fd0a1e9ab647fcc1459069d85', 'MXEydzNlNHI=', 'yzadmin', NULL, 'yzadmin@cloud-star.com.cn', NULL, NULL, 66666915, NULL, NULL, '1', NULL, 0, '2018-12-25 17:32:08', '10.102.10.126', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-24 17:18:49', 'admin', '2018-12-25 17:32:08', 1, NULL);
INSERT INTO `sys_m_user` VALUES (624, '03', NULL, 'zyzadmin', '6b36c3861519324395d8f707c83ad267', 'MXEydzNlNHI=', 'zyzadmin', NULL, 'zyzadmin@cloud-star.com.cn', NULL, NULL, 66666916, NULL, NULL, '1', NULL, 0, '2018-12-24 17:42:14', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-24 17:21:20', 'admin', '2018-12-24 17:42:14', 1, NULL);
INSERT INTO `sys_m_user` VALUES (625, '03', NULL, 'zyz_pj_admin', 'c895e0f22517b7c4cce1f5758df03fd2', 'MXEydzNlNHI=', 'zyz_pj_admin', NULL, 'zyz_pj_admin@cloud-star.com.cn', NULL, NULL, 66666915, NULL, NULL, '1', NULL, 0, '2018-12-24 17:33:07', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yzadmin', '2018-12-24 17:26:49', 'admin', '2018-12-24 17:33:07', 1, NULL);
INSERT INTO `sys_m_user` VALUES (626, '03', NULL, 'zyz_pj_user', '0b8d4545dfe9293c24536235c01738bd', 'MXEydzNlNHI=', 'zyz_pj_user', NULL, 'zyz_pj_user@cloud-star.com.cn', NULL, NULL, 66666915, NULL, NULL, '1', NULL, 0, '2018-12-24 17:33:53', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yzadmin', '2018-12-24 17:31:16', 'admin', '2018-12-24 17:33:53', 1, NULL);
INSERT INTO `sys_m_user` VALUES (628, '03', NULL, 'org_user', '75b672c216dea4baba99f68fa7168236', 'MXEydzNlNHI=', 'org_user', NULL, 'org_user@cc.cc', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-24 19:25:48', 'admin', '2018-12-24 19:25:48', 1, NULL);
INSERT INTO `sys_m_user` VALUES (629, '03', NULL, 'yzjkadmin', 'bb81186f72e863653ccb7a03065913d9', 'MXEydzNlNHI=', 'yzjkadmin', NULL, 'yzjkadmin@cloud-star.com.cn', NULL, NULL, 66666922, NULL, NULL, '1', NULL, 0, '2019-01-21 11:14:30', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-24 19:54:55', 'admin', '2019-01-21 11:14:30', 1, NULL);
INSERT INTO `sys_m_user` VALUES (630, '03', NULL, 'yzjk1admin', 'dc89be4b515daeb4409750a897c240d8', 'MXEydzNlNHI=', 'yzjk1admin', NULL, 'yzjk1admin@cloud-star.com.cn', NULL, NULL, 66666922, NULL, NULL, '1', NULL, 0, '2018-12-25 11:46:53', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-25 11:46:15', 'admin', '2018-12-25 11:46:53', 1, NULL);
INSERT INTO `sys_m_user` VALUES (631, '03', NULL, 'wanxin', '909d42aa5538331c85b6bc03f75c9a39', 'MXEydzNlNHI=', 'wanxin', NULL, 'wanxin@cloud-star.com.cn', NULL, NULL, 66666922, NULL, NULL, '1', NULL, 0, '2019-01-04 13:05:34', '10.101.10.21', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-25 13:03:28', 'admin', '2019-01-04 13:05:34', 1, NULL);
INSERT INTO `sys_m_user` VALUES (632, '03', NULL, 'zhuyanlong', '8a99ec48fe379420fce14b6bb864a69e', 'MXEydzNlNHI=', 'zhuyanlong', NULL, 'zhuyanlong@cloud-star.com.cn', NULL, NULL, 66666922, NULL, NULL, '1', NULL, 0, '2018-12-25 15:07:16', '10.102.10.152', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-25 15:06:53', 'admin', '2018-12-25 15:07:16', 1, NULL);
INSERT INTO `sys_m_user` VALUES (633, '03', NULL, 'yhw1225', 'aaac7d149931c58f18bbae22aec05b9a', 'MXEydzNlNHI=', 'yhw1225', NULL, 'yhw1225@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2019-01-03 09:37:32', '10.102.10.154', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1217A', '2018-12-25 15:13:23', 'admin', '2019-01-03 09:37:32', 1, NULL);
INSERT INTO `sys_m_user` VALUES (634, '03', NULL, 'shiwenqiang', '13581fdd3ee61cd2e29874b09867bf11', 'MXEydzNlNHI=', 'shiwenqiang', NULL, 'shiwenqiang@qq.com', NULL, NULL, 66666926, NULL, NULL, '1', NULL, 0, '2018-12-25 17:10:17', '10.102.10.106', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-25 17:07:39', 'admin', '2018-12-25 17:10:17', 1, NULL);
INSERT INTO `sys_m_user` VALUES (635, '03', NULL, 'poc', 'b708b4a54233aa5da3d4347f5259a10f', 'MXEydzNlNHI=', 'poc', NULL, 'poc@cloud-star.com.cn', NULL, NULL, 66666922, NULL, NULL, '1', NULL, 0, '2019-02-21 14:12:51', '10.102.10.126', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-25 17:27:54', 'admin', '2019-02-21 14:12:51', 1, NULL);
INSERT INTO `sys_m_user` VALUES (636, '03', NULL, 'poc-pj-admin', 'c986f324d18a2c23d01109195d4db2a1', 'MXEydzNlNHI=', 'poc-pj-admin', NULL, 'poc-pj-admin@cloud-star.com.cn', NULL, NULL, 66666922, NULL, NULL, '1', NULL, 0, '2019-01-04 10:14:30', '10.102.10.121', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'poc', '2018-12-25 17:46:21', 'admin', '2019-01-04 10:14:30', 1, NULL);
INSERT INTO `sys_m_user` VALUES (637, '03', NULL, 'poc-pj-user', '8b67b634ea8da4167d0b9da7c89912cb', 'MXEydzNlNHI=', 'poc-pj-user', NULL, 'poc-pj-user@cloud-star.com.cn', NULL, NULL, 66666922, NULL, NULL, '1', NULL, 0, '2018-12-28 19:18:36', '10.102.10.169', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'poc', '2018-12-25 17:46:48', 'admin', '2018-12-28 19:18:36', 1, NULL);
INSERT INTO `sys_m_user` VALUES (638, '03', NULL, 'yzlc', '77623efa1cdd2ad22f2191456986a314', 'MXEydzNlNHI=', 'yzlc', NULL, 'yzlc@cloud-star.com.cn', NULL, NULL, 66666932, NULL, NULL, '1', NULL, 0, '2019-01-23 14:23:57', '192.168.236.1', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-25 20:12:32', 'admin', '2019-01-23 14:23:57', 1, NULL);
INSERT INTO `sys_m_user` VALUES (639, '03', NULL, 'qx1', '8b7c45077c34ac6d796cb33ff3405b3d', 'MXEydzNlNHI=', 'qx1', NULL, 'qx1@qq.com', NULL, NULL, 66666934, NULL, NULL, '1', NULL, 0, '2018-12-26 18:58:18', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-25 20:20:19', 'admin', '2018-12-26 18:58:18', 1, NULL);
INSERT INTO `sys_m_user` VALUES (640, '03', NULL, 'yzlc-pj-admin', 'dbc4d891d95950a3067948552b01e081', 'MXEydzNlNHI=', 'yzlc-pj-admin', NULL, 'yzlc-pj-admin@cloud-star.com.cn', NULL, NULL, 66666932, NULL, NULL, '1', NULL, 0, '2018-12-27 17:45:13', '10.102.10.127', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yzlc', '2018-12-25 20:21:28', 'admin', '2018-12-27 17:45:13', 1, NULL);
INSERT INTO `sys_m_user` VALUES (641, '03', NULL, 'yzlc-pj-user', '563db579ba94c9c38102cc4d80a18a0c', 'MXEydzNlNHI=', 'yzlc-pj-user', NULL, 'yzlc-pj-user@cloud-star.com.cn', NULL, NULL, 66666932, NULL, NULL, '1', NULL, 0, '2018-12-26 22:42:29', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yzlc', '2018-12-25 20:22:12', 'admin', '2018-12-26 22:42:29', 1, NULL);
INSERT INTO `sys_m_user` VALUES (642, '03', NULL, 'qx2', '9bb2badbf96150ef63ff1c19d1eaff70', 'MXEydzNlNHI=', 'qx2', NULL, 'qx2@qq.com', NULL, NULL, 66666934, NULL, NULL, '1', NULL, 0, '2018-12-26 14:53:45', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'qx1', '2018-12-25 20:27:40', 'admin', '2018-12-26 14:53:45', 1, NULL);
INSERT INTO `sys_m_user` VALUES (643, '03', NULL, 'qx4', '4f66dfc9a9a8da577621b07b2e68ba45', 'MXEydzNlNHI=', 'qx4', NULL, 'qx4@qq.com', '', NULL, 66666934, 66666937, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'qx2', '2018-12-26 09:49:31', 'qx2', '2018-12-26 09:49:31', 1, NULL);
INSERT INTO `sys_m_user` VALUES (644, '03', NULL, 'qx3', '7d0618eea41eaedda9598d3c95d52ba5', 'MXEydzNlNHI=', 'qx3', NULL, 'qx3@qq.com', NULL, NULL, 66666934, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'qx1', '2018-12-26 09:51:02', 'qx1', '2018-12-26 09:51:02', 1, NULL);
INSERT INTO `sys_m_user` VALUES (647, '03', NULL, 'zcqad', '6581e7629b1f9c3c97892aacc901c231', 'MTIzNDU2', 'zcqad', NULL, 'zcqad@qq.com', '13368988673', NULL, 66666934, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'zcqad', 'ad', 'qx1', '2018-12-26 13:41:45', 'admin', '2019-01-09 11:55:35', 1, NULL);
INSERT INTO `sys_m_user` VALUES (648, '03', NULL, 'qx5', 'e0347a95ef136287459c61c481236726', 'MXEydzNlNHI=', 'qx5', NULL, 'qx5@qq.com', NULL, NULL, 66666934, NULL, NULL, '1', NULL, 0, '2018-12-26 13:43:14', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-26 13:42:49', 'admin', '2018-12-26 13:43:14', 1, NULL);
INSERT INTO `sys_m_user` VALUES (649, '03', NULL, 'zcq23', 'd5b4788ffcf507f2fb421a6e56776545', 'MXEydzNlNHI=', 'zcq23', NULL, 'zcq23@qq.com', NULL, NULL, 66666797, NULL, NULL, '1', NULL, 0, '2018-12-26 13:48:28', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-26 13:46:03', 'admin', '2018-12-26 13:53:16', 1, NULL);
INSERT INTO `sys_m_user` VALUES (650, '03', NULL, 'sdwes', '0dfee463ca94aa745eb4d85ec0532312', 'MXEydzNlNHI=', 'testsdsd', NULL, 'sdwes@hp.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'zhucaiqian', '2018-12-26 13:59:08', 'zhucaiqian', '2018-12-26 13:59:08', 1, NULL);
INSERT INTO `sys_m_user` VALUES (651, '03', NULL, 'zs', '0f02caec9b3cd8e30396eb55d9fac23f', 'MXEydzNlNHI=', 'zss', NULL, 'zs@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-26 14:23:30', '10.102.10.190', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1225', '2018-12-26 14:23:05', 'admin', '2018-12-26 14:23:30', 1, NULL);
INSERT INTO `sys_m_user` VALUES (652, '03', NULL, 'qx10', '36dc2529e69b178630abef80fda3a907', 'MXEydzNlNHI=', 'qx10', NULL, 'qx10@qq.com', '13222221212', NULL, 66666934, 66666937, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'qx2', '2018-12-26 15:03:01', 'qx2', '2018-12-26 15:03:01', 1, NULL);
INSERT INTO `sys_m_user` VALUES (654, '03', NULL, 'yzlc1', '2a23d3f3daabc70bbd5ae56608b78911', 'MXEydzNlNHI=', 'yzlc1', NULL, 'yzlc1@cloud-star.com.cn', NULL, NULL, 66666932, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yzlc', '2018-12-26 18:00:24', 'yzlc', '2018-12-26 18:00:24', 1, NULL);
INSERT INTO `sys_m_user` VALUES (655, '03', NULL, 'yzlc27', 'bb6639dc7f6d2b446a16432f7ba0ae05', 'MXEydzNlNHI=', 'yzlc27', NULL, 'yzlc27@cloud-star.com.cn', NULL, NULL, 66666955, NULL, NULL, '1', NULL, 0, '2019-01-02 14:10:26', '10.102.10.154', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-27 08:46:07', 'admin', '2019-01-02 14:10:26', 1, NULL);
INSERT INTO `sys_m_user` VALUES (658, '03', NULL, 'yzlc27-pj-admin', 'd721deda8baee1583c4d34156cb66151', 'MXEydzNlNHI=', 'yzlc27-pj-admin', NULL, 'yzlc27-pj-admin@cloud-star.com.cn', NULL, NULL, 66666955, NULL, NULL, '1', NULL, 0, '2018-12-27 20:29:16', '10.101.10.45', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yzlc27', '2018-12-27 09:29:39', 'admin', '2018-12-27 20:29:16', 1, NULL);
INSERT INTO `sys_m_user` VALUES (659, '03', NULL, 'yzlc27-pj-user', '230437a5501572115bc0eacbcb68fa87', 'MXEydzNlNHI=', 'yzlc27-pj-user', NULL, 'yzlc27-pj-user@cloud-star.com.cn', NULL, NULL, 66666955, NULL, NULL, '1', NULL, 0, '2018-12-27 10:03:13', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yzlc27', '2018-12-27 09:30:09', 'admin', '2018-12-27 10:03:13', 1, NULL);
INSERT INTO `sys_m_user` VALUES (660, '03', NULL, 'google', '5d6488b03d8f9b178920b5affb67f7bb', 'MXEydzNlNHI=', 'google', NULL, 'google@cloudstar.com.cn', NULL, NULL, 66666957, NULL, NULL, '1', NULL, 0, '2018-12-27 09:58:26', '10.102.10.117', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-27 09:57:49', 'admin', '2018-12-27 09:58:26', 1, NULL);
INSERT INTO `sys_m_user` VALUES (661, '03', NULL, 'yzlc2701', 'bee3782968c08b3e96aa26a7270f1301', 'MXEydzNlNHI=', 'yzlc2701', NULL, 'yzlc2701@cloud-star.com.cn', NULL, NULL, 66666958, NULL, NULL, '1', NULL, 0, '2018-12-27 10:00:15', '10.100.4.200', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-27 10:00:05', 'admin', '2018-12-27 10:00:15', 1, NULL);
INSERT INTO `sys_m_user` VALUES (662, '03', NULL, 'zcq1227', '4d22b027661149816504691ac9dad8fb', 'MXEydzNlNHI=', 'zcq1227', NULL, 'zcq1227@qq.com', NULL, NULL, 66666961, NULL, NULL, '1', NULL, 0, '2018-12-28 09:56:20', '10.102.10.190', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-27 14:03:23', 'admin', '2018-12-28 09:56:20', 1, NULL);
INSERT INTO `sys_m_user` VALUES (664, '03', NULL, 'c1admin', 'abe8d9cc4b90b5c1a9a9dc8cc0b7dc5f', 'MXEydzNlNHI=', 'c1admin', NULL, 'c1admin@alcc.com', NULL, NULL, 66666963, NULL, NULL, '1', NULL, 0, '2019-03-04 11:20:18', '10.102.10.106', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-27 15:18:38', 'admin', '2019-03-04 11:20:18', 1, NULL);
INSERT INTO `sys_m_user` VALUES (665, '03', NULL, 't1admin', 'bb2c38010097bad0725c5e33d77bc0fa', 'MXEydzNlNHI=', 't1admin', NULL, 't1admin@revenco.com', NULL, NULL, 66666964, NULL, NULL, '1', NULL, 0, '2018-12-29 11:11:01', '10.102.10.108', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-27 15:25:07', 'admin', '2018-12-29 11:11:01', 1, NULL);
INSERT INTO `sys_m_user` VALUES (666, '03', NULL, 't1user', '9dfae84be21cf83181338dab684b6a0b', 'MXEydzNlNHI=', 't1user', NULL, 't1user@revenco.com', NULL, NULL, 66666965, NULL, NULL, '1', NULL, 0, '2018-12-29 11:12:07', '10.102.10.108', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-27 15:25:43', 'admin', '2018-12-29 11:12:07', 1, NULL);
INSERT INTO `sys_m_user` VALUES (667, '03', NULL, 'PMuser1', 'cb070669999a35a325194481c4209eac', 'MXEydzNlNHI=', 'PMuser', NULL, 'PMuser@qq.com', NULL, NULL, 66666962, NULL, NULL, '1', NULL, 0, '2019-01-14 17:51:21', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yangqin', '2018-12-27 15:34:09', 'admin', '2019-01-14 17:51:21', 1, NULL);
INSERT INTO `sys_m_user` VALUES (668, '03', NULL, 'normalUSER1', '59d7aeea369c1c5e86194eaf108efbd8', 'MXEydzNlNHI=', 'normalUSER', NULL, 'normalUSER@qq.com', NULL, NULL, 66666962, NULL, NULL, '1', NULL, 0, '2019-01-04 13:42:44', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yangqin', '2018-12-27 15:34:44', 'admin', '2019-01-04 13:42:44', 1, NULL);
INSERT INTO `sys_m_user` VALUES (670, '03', NULL, 'hu', 'c52c7a9e5a2b41bf8c70dfbfe3f94b53', 'MXEydzNlNHI=', 'huangyan5', NULL, 'hu@qq.com', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, '2018-12-27 17:29:13', '10.102.10.190', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-27 17:27:54', 'admin', '2018-12-27 17:29:13', 1, NULL);
INSERT INTO `sys_m_user` VALUES (671, '03', NULL, 'newPM', 'bca39a5282f29e4ae30de49f8667d374', 'MXEydzNlNHI=', 'newPM', NULL, 'newPM@qq.com', NULL, NULL, 66666962, NULL, NULL, '1', NULL, 0, '2018-12-27 17:43:51', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yangqin', '2018-12-27 17:43:25', 'admin', '2018-12-27 17:43:51', 1, NULL);
INSERT INTO `sys_m_user` VALUES (674, '03', NULL, 'lcj', 'ba30c5c14affe295d6eeb974be3030dd', 'MXEydzNlNHI=', 'lcj', NULL, 'lichuanjiang@cloud-star.com.cn', '15051961597', NULL, 66667085, NULL, NULL, '1', NULL, 0, '2019-03-05 13:30:40', '10.102.10.131', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-27 18:56:23', 'admin', '2019-03-05 13:30:40', 1, NULL);
INSERT INTO `sys_m_user` VALUES (675, '03', NULL, 'tangttD', 'b82fa084737e5ea8705cc5ff53a6ed11', 'MXEydzNlNHI=', 'tangttD', NULL, 'tangttD@qq.com', '', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2019-01-29 09:58:16', '10.102.10.104', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-28 09:55:54', 'admin', '2019-01-29 09:58:16', 1, NULL);
INSERT INTO `sys_m_user` VALUES (676, '03', NULL, 'zcq1228', '43a6f348860e33c6062fbc8b5d7c75e8', 'MXEydzNlNHI=', 'zcq1228', NULL, 'zcq1228@qq.com', NULL, NULL, 66667004, NULL, NULL, '1', NULL, 0, '2018-12-28 11:01:22', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-28 10:21:45', 'cq1228', '2018-12-28 11:01:22', 1, NULL);
INSERT INTO `sys_m_user` VALUES (677, '03', NULL, 'test1228-1', '4dcc8935077d699b71b820be8faf90e7', 'MXEydzNlNHI=', 'test1228-1', NULL, 'test1228-1@qq.com', NULL, NULL, 66667005, NULL, NULL, '1', NULL, 0, '2018-12-28 10:57:29', '10.102.10.127', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-28 10:57:22', 'admin', '2018-12-28 10:57:29', 1, NULL);
INSERT INTO `sys_m_user` VALUES (678, '03', NULL, 'cq1228', '12ba720d9133b48d638adda809456b17', 'MXEydzNlNHI=', 'cq1228', NULL, 'cq1228@qq.com', NULL, NULL, 66667006, NULL, NULL, '1', NULL, 0, '2018-12-28 14:33:33', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-28 10:59:05', 'cq1228', '2018-12-28 14:33:33', 1, NULL);
INSERT INTO `sys_m_user` VALUES (679, '03', NULL, 'q1228', '0582f93a6bab7e30bc4ac386c311db6e', 'MXEydzNlNHI=', 'q1228', NULL, 'q1228@qq.com', NULL, NULL, 66667008, NULL, NULL, '1', NULL, 0, '2018-12-28 20:27:49', '10.102.10.127', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-28 14:46:24', 'admin', '2018-12-28 20:27:49', 1, NULL);
INSERT INTO `sys_m_user` VALUES (680, '03', NULL, 'c1228', '2fd67d454253d4d68fc00a69d8a8029a', 'MXEydzNlNHI=', 'c1228', NULL, 'c1228@qq.com', NULL, NULL, 66667012, NULL, NULL, '1', NULL, 0, '2018-12-28 18:39:17', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-28 15:05:29', 'admin', '2018-12-28 18:39:17', 1, NULL);
INSERT INTO `sys_m_user` VALUES (681, '03', NULL, 'a1228', 'cf3e1d1a49ddb947967eb7c356f6f2a5', 'MXEydzNlNHI=', 'a1228', NULL, 'a1228@qq.com', '17623261991', NULL, 1, NULL, NULL, '1', NULL, 0, '2019-02-18 16:07:18', '10.102.10.145', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-28 15:58:22', 'a1228', '2019-02-18 16:07:18', 1, NULL);
INSERT INTO `sys_m_user` VALUES (682, '03', NULL, 'yhw1228', '8290527374e03a6294cb91bf7120931f', 'MXEydzNlNHI=', 'yhw1228', NULL, '1293263126@qq.com', '18956473853', NULL, 66667026, NULL, NULL, '1', NULL, 0, '2019-02-19 15:57:26', '10.102.10.133', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-28 16:46:01', 'admin', '2019-02-19 15:57:26', 1, NULL);
INSERT INTO `sys_m_user` VALUES (683, '03', NULL, 'yhw1228B', '95479192fcccbb94ba4637650403393a', 'MXEydzNlNHI=', 'yhw1228B', NULL, 'yhw1228B@qq.com', NULL, NULL, 66667026, NULL, NULL, '1', NULL, 0, '2018-12-28 19:19:19', '10.102.10.169', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2018-12-28 16:46:54', 'admin', '2018-12-28 19:19:19', 1, NULL);
INSERT INTO `sys_m_user` VALUES (684, '03', NULL, 'yhw1228C', '3d9f1a760bb5cc8a12a59837c34e62db', 'MXEydzNlNHI=', 'yhw1228C', NULL, 'yhw1228C@qq.com', NULL, NULL, 66667026, NULL, NULL, '1', NULL, 0, '2018-12-28 16:59:50', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2018-12-28 16:47:13', 'admin', '2018-12-28 16:59:50', 1, NULL);
INSERT INTO `sys_m_user` VALUES (685, '03', NULL, 'yhw1228A', 'de5e9f72f179530d93d6bd6048dcc000', 'MXEydzNlNHI=', 'yhw1228a', NULL, 'yhw1228A@qq.com', NULL, NULL, 66667026, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2018-12-28 16:47:48', 'yhw1228', '2018-12-28 16:47:48', 1, NULL);
INSERT INTO `sys_m_user` VALUES (686, '03', NULL, 'yhw1228P', '67edffa87463a7df22f1a28cb6d2c8b9', 'MXEydzNlNHI=', 'yhw1228P', NULL, 'yhw1228P@qq.com', NULL, NULL, 66667026, NULL, NULL, '1', NULL, 0, '2018-12-28 19:53:49', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2018-12-28 17:14:58', 'admin', '2018-12-28 19:53:49', 1, NULL);
INSERT INTO `sys_m_user` VALUES (687, '03', NULL, 'ys1228', 'b279b37bd4c0bd6fdc44e29d76f4cd85', 'MXEydzNlNHI=', 'ys1228', NULL, 'ys1228@qq.com', NULL, NULL, 66667030, NULL, NULL, '1', NULL, 0, '2018-12-28 21:56:36', '10.102.10.127', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-28 21:56:21', 'admin', '2018-12-28 21:56:36', 1, NULL);
INSERT INTO `sys_m_user` VALUES (688, '03', NULL, 'zhangsan', 'cbdc404ee2aef3021ed660823b6fd70f', 'MXEydzNlNHI=', 'zhangsan', NULL, 'zhangsan@cloud-star.com.cn', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, '2019-01-07 11:22:10', '10.102.10.154', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2018-12-29 13:58:18', 'admin', '2019-01-07 11:22:10', 1, NULL);
INSERT INTO `sys_m_user` VALUES (689, '03', NULL, 'yhw1229', 'db6d6d316522a36b89c689a5493254aa', 'MXEydzNlNHI=', 'yhw1229', NULL, 'yhw1229@qq.com', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, '2019-01-07 11:21:26', '10.102.10.154', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2018-12-29 14:46:20', 'admin', '2019-01-07 11:21:26', 1, NULL);
INSERT INTO `sys_m_user` VALUES (690, '03', NULL, 'A', '9b107dd8e1c193565a08c41c93cf7db9', 'MXEydzNlNHI=', 'huangyanA', NULL, 'A@qq.com', NULL, NULL, 66666739, NULL, NULL, '1', NULL, 0, '2018-12-29 16:58:04', '10.102.10.190', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'zhucaiqian', '2018-12-29 16:56:56', 'admin', '2018-12-29 16:58:04', 1, NULL);
INSERT INTO `sys_m_user` VALUES (691, '03', NULL, 'zcq0102', 'b43692667f3def25ea6a9274974c0e78', 'MXEydzNlNHI=', 'zcq0102', NULL, 'zcq0102@qq.com', NULL, NULL, 66666962, NULL, NULL, '1', NULL, 0, '2019-01-02 11:50:47', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-02 11:50:23', 'admin', '2019-01-02 11:50:47', 1, NULL);
INSERT INTO `sys_m_user` VALUES (692, '03', NULL, '010201', 'd6ce9f83d817bad4e7d24771d3d5f0d2', 'MXEydzNlNHI=', '010201', NULL, '010201@qq.com', NULL, NULL, 66667039, NULL, NULL, '1', NULL, 0, '2019-01-03 14:39:37', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-02 11:51:50', 'admin', '2019-01-03 14:39:37', 1, NULL);
INSERT INTO `sys_m_user` VALUES (693, '03', NULL, '0102', '1fc772a3bc5db833488388da662647fa', 'MXEydzNlNHI=', '0102', NULL, '0102@qq.com', NULL, NULL, 66667038, NULL, NULL, '1', NULL, 0, '2019-01-02 11:52:48', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-02 11:52:41', 'admin', '2019-01-02 11:52:48', 1, NULL);
INSERT INTO `sys_m_user` VALUES (694, '03', NULL, 'yhw0102A', '7e43ae91b803a2cb2e54dc5d67030e8c', 'MXEydzNlNHI=', 'yhw0102A', NULL, 'yhw0102A@qq.com', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, '2019-01-03 14:56:57', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2019-01-02 15:51:01', 'admin', '2019-01-03 14:56:57', 1, NULL);
INSERT INTO `sys_m_user` VALUES (695, '03', NULL, 'yhw0102B', '30e26c37302aa5b441c3f280adadf06a', 'MXEydzNlNHI=', 'yhw0102B', NULL, 'yhw0102B@qq.com', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, '2019-01-04 14:50:06', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2019-01-02 15:51:21', 'admin', '2019-01-04 14:50:06', 1, NULL);
INSERT INTO `sys_m_user` VALUES (696, '03', NULL, 'yhw0102C', 'c3e8ef8867bb1883d20f2a83d334acfa', 'MXEydzNlNHI=', 'yhw0102C', NULL, 'yhw0102C@qq.com', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2019-01-02 15:51:35', 'yhw1228', '2019-01-03 11:13:03', 1, NULL);
INSERT INTO `sys_m_user` VALUES (697, '03', NULL, '704844966', '449816d8a32190cf8c245547db35ed9a', 'MXEydzNlNHI=', '704844966', NULL, '471154629@qq.com', '15051961592', NULL, 66667013, NULL, NULL, '1', NULL, 0, '2019-02-22 16:25:38', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-02 17:39:12', '704844966', '2019-02-22 16:30:45', 1, NULL);
INSERT INTO `sys_m_user` VALUES (702, '03', NULL, 'yhw0103A', '42a1d52bd7bb2cabd8adec9dcc6ce100', 'MXEydzNlNHI=', 'yhw0103A', NULL, 'yhw0103A@qq.com', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, '2019-01-03 14:07:41', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2019-01-03 11:18:48', 'admin', '2019-01-03 14:07:41', 1, NULL);
INSERT INTO `sys_m_user` VALUES (703, '03', NULL, 'yhw0103C', '7a01b1145c2364c03b12739c277e40ad', 'MXEydzNlNHI=', 'yhw0103C', NULL, 'yhw0103C@qq.com', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2019-01-03 11:57:18', 'yhw1228', '2019-01-03 11:57:18', 1, NULL);
INSERT INTO `sys_m_user` VALUES (704, '03', NULL, 'shenpi1', 'c2c76d63eaa04d2b771ea075ffe11c5a', 'MXEydzNlNHI=', 'shenpi1', NULL, 'shenpi1@qq.com', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, '2019-01-03 15:44:24', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2019-01-03 13:31:48', 'admin', '2019-01-03 15:44:24', 1, NULL);
INSERT INTO `sys_m_user` VALUES (705, '03', NULL, 'shenpi2', 'bcc7cd301e10b7ef4add72b6a4f0b198', 'MXEydzNlNHI=', 'shenpi2', NULL, 'shenpi2@qq.com', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, '2019-01-03 16:02:06', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2019-01-03 13:32:10', 'admin', '2019-01-03 16:02:06', 1, NULL);
INSERT INTO `sys_m_user` VALUES (706, '03', NULL, 'zhiding1', '61eb42bd1bde0eff5fd5defb68dd1b28', 'MXEydzNlNHI=', 'zhiding1', NULL, 'zhiding1@qq.com', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, '2019-01-03 16:01:50', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2019-01-03 13:35:25', 'admin', '2019-01-03 16:01:50', 1, NULL);
INSERT INTO `sys_m_user` VALUES (707, '03', NULL, 'zhiding2', 'f7b7a51dc6add6f824b423e632bc43fe', 'MXEydzNlNHI=', 'zhiding2', NULL, 'zhiding2@qq.com', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, '2019-01-03 15:52:39', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2019-01-03 13:35:40', 'admin', '2019-01-03 15:52:39', 1, NULL);
INSERT INTO `sys_m_user` VALUES (708, '03', NULL, 'zhiding3', 'd973d62413c1881ab11c6dea1d39e4e8', 'MXEydzNlNHI=', 'zhiding3', NULL, 'zhiding3@qq.com', NULL, NULL, 66667013, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yhw1228', '2019-01-03 13:42:57', 'yhw1228', '2019-01-03 13:42:57', 1, NULL);
INSERT INTO `sys_m_user` VALUES (709, '03', NULL, 'yhw104A', '94666573b8f14818183be438d8c31d58', 'MXEydzNlNHI=', 'yhw104A', NULL, 'yhw104A@qq.com', NULL, NULL, 66666962, NULL, NULL, '1', NULL, 0, '2019-01-10 09:53:35', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yangqin', '2019-01-04 10:33:22', 'admin', '2019-01-10 09:53:35', 1, NULL);
INSERT INTO `sys_m_user` VALUES (710, '03', NULL, 'yhw104B', 'af4626fce0fafd1aed57b30fcb6055a4', 'MXEydzNlNHI=', 'yhw104B', NULL, 'yhw104B@qq.com', NULL, NULL, 66666962, NULL, NULL, '1', NULL, 0, '2019-01-04 14:12:19', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yangqin', '2019-01-04 10:33:41', 'admin', '2019-01-04 14:12:19', 1, NULL);
INSERT INTO `sys_m_user` VALUES (711, '03', NULL, 'yhw104C', 'eb477a9b87eab230649195986f04f051', 'MXEydzNlNHI=', 'yhw104C', NULL, 'yhw104C@qq.com', NULL, NULL, 66666962, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yangqin', '2019-01-04 10:33:57', 'yangqin', '2019-01-04 11:10:09', 1, NULL);
INSERT INTO `sys_m_user` VALUES (712, '03', NULL, 'yhw104D', 'ee03269fbd36f4cce9d577e056652658', 'MXEydzNlNHI=', 'yhw104D', NULL, 'yhw104D@qq.com', NULL, NULL, 66666962, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yangqin', '2019-01-04 10:34:12', 'yangqin', '2019-01-04 11:10:13', 1, NULL);
INSERT INTO `sys_m_user` VALUES (713, '03', NULL, 'yhw104E', 'f73e22f4cbb21b3d79cc07974b694d89', 'MXEydzNlNHI=', 'yhw104E', NULL, 'yhw104E@qq.com', NULL, NULL, 66666962, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yangqin', '2019-01-04 10:34:30', 'yangqin', '2019-01-04 11:10:20', 1, NULL);
INSERT INTO `sys_m_user` VALUES (714, '03', NULL, 'yhw104F', '79411a2536be3ee44df57d45df848a14', 'MXEydzNlNHI=', 'yhw104F', NULL, 'yhw104F@qq.com', NULL, NULL, 66666962, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'yangqin', '2019-01-04 10:34:51', 'yangqin', '2019-01-04 11:10:24', 1, NULL);
INSERT INTO `sys_m_user` VALUES (715, '03', NULL, 'nick104A', '457e369cc9f81615db532b049071eba1', 'MXEydzNlNHI=', 'nick104A', NULL, 'nick104A@qq.com', NULL, NULL, 66667049, NULL, NULL, '1', NULL, 0, '2019-02-18 14:13:03', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-04 11:58:59', 'admin', '2019-02-18 14:13:03', 1, NULL);
INSERT INTO `sys_m_user` VALUES (716, '03', NULL, 'nick104B', '090c94a949b37fc21069ec1fdb964e0f', 'MXEydzNlNHI=', 'nick104B', NULL, 'nick104B@qq.com', NULL, NULL, 66667049, NULL, NULL, '1', NULL, 0, '2019-01-04 15:36:11', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'nick104A', '2019-01-04 12:43:09', 'admin', '2019-01-04 15:36:11', 1, NULL);
INSERT INTO `sys_m_user` VALUES (717, '03', NULL, 'nick104C', '61962c437466b62ceff998e0162f5954', 'MXEydzNlNHI=', 'nick104C', NULL, 'nick104C@qq.com', NULL, NULL, 66667049, NULL, NULL, '1', NULL, 0, '2019-01-04 13:15:40', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'nick104A', '2019-01-04 12:43:29', 'admin', '2019-01-04 13:15:40', 1, NULL);
INSERT INTO `sys_m_user` VALUES (718, '03', NULL, '0104', '5e74c5bab27a36c1d661bf59cace74ee', 'MXEydzNlNHI=', '0104', NULL, '0104@qq.com', NULL, NULL, 66667054, NULL, NULL, '1', NULL, 0, '2019-01-21 14:31:44', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-04 16:12:34', 'admin', '2019-01-21 14:31:44', 1, NULL);
INSERT INTO `sys_m_user` VALUES (719, '03', NULL, 'tengxunyunagent', '3794c33ab2ce9e221272839b70fe638f', 'MXEydzNlNHI=', 'tengxunyunagent', NULL, 'tengxunyunagent@qq.com', '13322121112', NULL, 66667026, NULL, NULL, '1', NULL, 0, '2019-01-08 17:57:15', '10.102.10.127', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-07 16:33:36', 'a1228', '2019-01-08 17:57:15', 1, NULL);
INSERT INTO `sys_m_user` VALUES (720, '03', NULL, '0108', '678733204dddd709905fe756826e286f', 'MXEydzNlNHI=', '0108', NULL, '0108@qq.com', NULL, NULL, 66667055, NULL, NULL, '1', NULL, 0, '2019-02-22 14:51:27', '10.102.10.119', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-08 15:57:44', 'admin', '2019-02-22 14:51:29', 1, NULL);
INSERT INTO `sys_m_user` VALUES (722, '03', NULL, 'liwenhong', '37bb731019170569a9d9315bf5224f64', 'MXEydzNlNHI=', 'liwenhong', NULL, 'liwenhong@qq.com', NULL, NULL, 66667058, NULL, NULL, '1', NULL, 0, '2019-01-09 11:40:53', '10.102.10.104', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-09 11:39:28', 'admin', '2019-01-09 11:42:26', 1, NULL);
INSERT INTO `sys_m_user` VALUES (726, '03', NULL, 'lidage', '6a5a4e6b82c7025bd3d82e1bf1acc9cb', 'MXEydzNlNHI=', 'lidage', NULL, 'lidage@qq.com', '13368359972', NULL, 66666739, NULL, NULL, '1', NULL, 0, '2019-01-09 14:21:12', '10.102.10.190', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'zhucaiqian', '2019-01-09 14:16:12', 'admin', '2019-01-09 14:21:12', 1, NULL);
INSERT INTO `sys_m_user` VALUES (728, '03', NULL, 'yhw110A', '974bba56e47d93d1c18e8290184caea5', 'MXEydzNlNHI=', 'yhw110A', NULL, 'yhw110A@qq.com', NULL, NULL, 66666954, NULL, NULL, '1', NULL, 0, '2019-01-14 10:50:02', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'tangttA', '2019-01-10 14:17:17', 'admin', '2019-01-14 10:50:02', 1, NULL);
INSERT INTO `sys_m_user` VALUES (729, '03', NULL, 'yhw110B', 'b1c00c27a616b15b51382ed02352e38e', 'MXEydzNlNHI=', 'yhw110B', NULL, 'yhw110B@qq.com', NULL, NULL, 66666954, NULL, NULL, '1', NULL, 0, '2019-02-11 11:31:38', '10.102.10.153', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'tangttA', '2019-01-10 14:17:31', 'admin', '2019-02-11 11:31:38', 1, NULL);
INSERT INTO `sys_m_user` VALUES (730, '03', NULL, 'liuxiaolu', '6d4e2fceb995626aa4ca6dcf75f91db3', 'MXEydzNlNHI=', 'liuxiaolu', NULL, 'liuxiaolu@qq.com', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, '2019-03-07 10:11:23', '10.102.10.123', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-11 11:23:29', 'admin', '2019-03-07 10:11:23', 1, NULL);
INSERT INTO `sys_m_user` VALUES (731, '03', NULL, 'aaaa', '15b8e0d803de69437d9a75f0b6d44629', 'MXEydzNlNHI=', 'aaaa', NULL, 'aaaa@qq.com', NULL, NULL, 66667055, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0108', '2019-01-11 12:07:21', '0108', '2019-01-11 12:07:21', 1, NULL);
INSERT INTO `sys_m_user` VALUES (733, '03', NULL, 'wuhong', '3185b49dbc083f613945259baf999c60', 'MXEydzNlNHI=', 'wwwww', NULL, 'wuhong@qq.com', NULL, NULL, 66667073, NULL, NULL, '1', NULL, 0, '2019-01-11 15:24:22', '10.102.10.107', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-11 15:23:37', 'admin', '2019-01-11 15:24:22', 1, NULL);
INSERT INTO `sys_m_user` VALUES (734, '03', NULL, '0114', 'f5a597c28fc341ab0ae747f60b4e5634', 'MXEydzNlNHI=', '0114', NULL, 'gqin@ctar.com.cn', '', NULL, 66667074, NULL, NULL, '1', NULL, 0, '2019-01-29 15:31:10', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-14 13:30:29', 'admin', '2019-01-29 15:31:10', 1, NULL);
INSERT INTO `sys_m_user` VALUES (735, '03', NULL, 'PM0114', '7a63575e0807eb239b701eea216c5868', 'MXEydzNlNHI=', 'PM0114', NULL, 'PM0114@12.com', '', NULL, 66666962, 66666973, NULL, '1', NULL, 0, '2019-01-17 18:28:25', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'PMuser1', '2019-01-14 17:32:34', 'admin', '2019-01-17 18:28:25', 1, NULL);
INSERT INTO `sys_m_user` VALUES (736, '03', NULL, 'test', '69242746fe34e392bad326ac9cc6324d', 'MXEydzNlNHI=', 'test', NULL, 'test@12.com', '', NULL, 66666962, 66666973, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'PMuser1', '2019-01-14 17:51:44', 'PMuser1', '2019-01-14 17:51:44', 1, NULL);
INSERT INTO `sys_m_user` VALUES (737, '03', NULL, 'test0114', 'fbdd1cb8d16389c05c1b03b576a964f0', 'MXEydzNlNHI=', 'test0114', NULL, 'test0114@12.com', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-14 17:54:32', 'admin', '2019-01-14 17:54:32', 1, NULL);
INSERT INTO `sys_m_user` VALUES (739, '03', NULL, 'user0114', '08884d6b1a9f3465070c9dbd92d461fe', 'MXEydzNlNHI=', 'user0114', NULL, 'yaqin@cloud-star.com.cn', '', NULL, 66667074, NULL, NULL, '1', NULL, 0, '2019-01-21 10:41:18', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0114', '2019-01-15 10:08:31', 'admin', '2019-01-21 10:41:18', 1, NULL);
INSERT INTO `sys_m_user` VALUES (740, '03', NULL, 'testrole', 'e10a632f21ec739ae256f89a1cbc21a6', 'MXEydzNlNHI=', 'testrole', NULL, 'testrole@qw.com', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-15 10:13:33', 'admin', '2019-01-15 10:13:33', 1, NULL);
INSERT INTO `sys_m_user` VALUES (741, '03', NULL, '0114pm', 'b66b5842f0b1cc5cc032672af79d3078', 'MXEydzNlNHI=', '0114pm', NULL, '3061411211@qq.com', '', NULL, 66667074, NULL, NULL, '1', NULL, 0, '2019-01-18 16:04:06', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0114', '2019-01-15 11:31:53', 'admin', '2019-01-18 16:04:06', 1, NULL);
INSERT INTO `sys_m_user` VALUES (744, '03', NULL, 'user0117', 'c304cfa6489f0ddb00fd6a23fcf7bd20', 'MXEydzNlNHI=', 'user0117', NULL, 'user0117@qw.com', NULL, NULL, 66667074, NULL, NULL, '1', NULL, 0, '2019-01-18 16:02:08', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0114', '2019-01-17 16:46:57', 'admin', '2019-01-18 16:02:08', 1, NULL);
INSERT INTO `sys_m_user` VALUES (746, '03', NULL, 'diyiying', '44c33cccac5a1c5e3c6459024cb1a3fd', 'MXEydzNlNHI=', '0117pm', NULL, 'diyiying@126.com', NULL, NULL, 66667074, NULL, NULL, '1', NULL, 0, '2019-01-17 18:31:27', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0114', '2019-01-17 17:05:35', 'admin', '2019-01-17 18:31:27', 1, NULL);
INSERT INTO `sys_m_user` VALUES (747, '03', NULL, 'project0117', 'bb791c54d2f75d943ccc489d3dac84ab', 'MXEydzNlNHI=', 'project0117', NULL, 'project0117@qw.com', NULL, NULL, 66667074, NULL, NULL, '1', NULL, 0, '2019-01-17 18:30:58', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0114', '2019-01-17 17:35:50', 'admin', '2019-01-17 18:30:58', 1, NULL);
INSERT INTO `sys_m_user` VALUES (748, '03', NULL, '0117', '0a2beaabefa86285cd4e3edc358a5d10', 'MXEydzNlNHI=', '0117', NULL, '0117@12.com', NULL, NULL, 66667074, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0114', '2019-01-21 10:38:12', '0114', '2019-01-21 10:38:12', 1, NULL);
INSERT INTO `sys_m_user` VALUES (751, '03', NULL, '0121', 'bf60644f58387c43b3d628e3d80ab616', 'MXEydzNlNHI=', '0121网络管理', NULL, '0121@12.com', NULL, NULL, 66667084, NULL, NULL, '1', NULL, 0, '2019-01-21 18:10:30', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-21 11:00:06', 'admin', '2019-01-21 18:10:30', 1, NULL);
INSERT INTO `sys_m_user` VALUES (752, '03', NULL, '0122', '0ecc6413d33a0c7ffc04914fe855f878', 'MXEydzNlNHI=', '0122', NULL, '306141121@qq.com', '', NULL, 66667085, NULL, NULL, '1', NULL, 0, '2019-03-22 09:55:09', '192.168.93.1', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-21 14:27:31', 'admin', '2019-03-22 09:55:09', 1, NULL);
INSERT INTO `sys_m_user` VALUES (753, '03', NULL, '0122pm', '3c05e9c65351d129d2aaf78642051b1a', 'MXEydzNlNHI=', '0122pm', NULL, '0122pm@12.com', '', NULL, 66667085, NULL, NULL, '1', NULL, 0, '2019-02-25 09:49:32', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-01-21 18:09:05', 'admin', '2019-02-25 09:49:32', 1, NULL);
INSERT INTO `sys_m_user` VALUES (755, '03', NULL, '0121pm', '365a1de0e9052294668ffc80f638c8d0', 'MXEydzNlNHI=', '0121pm', NULL, '0121pm@qq.com', '15124234324', NULL, 66667085, NULL, NULL, '1', NULL, 0, '2019-01-30 12:25:42', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-01-22 10:14:49', '0122', '2019-02-25 11:38:44', 1, NULL);
INSERT INTO `sys_m_user` VALUES (756, '03', NULL, '0122user', 'a063a3030aac065227cd023da82919c7', 'MXEydzNlNHI=', '0122user', NULL, '0122user@123.com', NULL, NULL, 66667085, NULL, NULL, '1', NULL, 0, '2019-03-01 10:14:19', '10.102.10.117', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-01-22 10:19:56', 'admin', '2019-03-01 10:14:19', 1, NULL);
INSERT INTO `sys_m_user` VALUES (765, '03', NULL, '你好123hello', '24da454fb3bd96b7fa94a524bdf881c6', '5L2g5aW9MTIzaGVsbG8=', '你好123hello', NULL, '你好123hello@123.com', '', NULL, NULL, NULL, 66667105, '1', NULL, 0, '2019-01-23 17:25:30', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2019-01-23 17:19:11', 'admin', '2019-01-23 17:25:30', 1, NULL);
INSERT INTO `sys_m_user` VALUES (768, '03', NULL, '123456789', '1edb9bb868518ac2cd32ce56328956ed', 'MXEydzNlNHI=', 'swqtest', NULL, '123456789@qq.com', '', NULL, 66667110, NULL, 66667110, '1', NULL, 0, '2019-01-23 17:57:18', '10.102.10.106', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2019-01-23 17:56:31', '123456789', '2019-01-23 17:57:18', 1, NULL);
INSERT INTO `sys_m_user` VALUES (769, '03', NULL, 'swq666', '559c46c12a332effecfa96420ca555cb', 'MXEydzNlNHI=', 'swq666', NULL, 'swq666@qq.com', '15823512727', NULL, 66667111, NULL, 66667111, '1', NULL, 0, '2019-01-23 18:27:10', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2019-01-23 18:15:21', 'admin', '2019-01-23 18:27:10', 1, NULL);
INSERT INTO `sys_m_user` VALUES (770, '03', NULL, 'shiwenqiang1', 'ce211af42d654cfd61cf6e78ed3cad99', 'MXEydzNlNHI1dDZ5', 'swq', NULL, 'shiwenqiang@cloud-star.com.cn', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, '2019-01-25 14:49:14', '10.102.10.106', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-25 14:46:58', 'admin', '2019-01-25 14:49:14', 1, NULL);
INSERT INTO `sys_m_user` VALUES (772, '03', NULL, 'zcq00', '00a37d2771052c482d04b8f7b11cc9d1', 'MXEydzNlNHI=', 'zcq000', NULL, 'zcq00@qq.com', '', NULL, 66667118, NULL, 66667118, '2', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2019-01-25 17:51:44', NULL, NULL, 1, NULL);
INSERT INTO `sys_m_user` VALUES (773, '03', NULL, 'caiqian', '9adb9ebae1fb4a090adadacc38cde7c8', 'MXEydzNlNHI=', '彩茜', NULL, 'caiqian@qq.com', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-01-28 10:01:23', 'admin', '2019-01-28 10:01:23', 1, NULL);
INSERT INTO `sys_m_user` VALUES (774, '03', NULL, '12', '08ca4daf9c42f88613df4089e6ca1f82', 'MXEydzNlNHI=', '一二', NULL, '12@qq.com', NULL, NULL, 66667085, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-01-28 10:01:52', '0122', '2019-02-18 16:09:19', 1, NULL);
INSERT INTO `sys_m_user` VALUES (775, '03', NULL, '34', 'f5d0ea55c9ab33e2b51dd46ef9a5af5f', 'MXEydzNlNHI=', '三四', NULL, '34@qq.com', '', NULL, 66667085, 66667086, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122pm', '2019-01-28 10:02:49', '0122pm', '2019-02-18 09:40:33', 1, NULL);
INSERT INTO `sys_m_user` VALUES (776, '03', NULL, 'yangqin1', 'fc66452e40824d52812692c093ad56ea', 'MXEydzNlNHI=', '杨芹1', NULL, 'yangqin@12.com', NULL, NULL, 66667085, NULL, NULL, '1', NULL, 0, '2019-01-30 17:11:15', '10.102.10.134', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-01-28 15:25:07', 'admin', '2019-01-30 17:11:15', 1, NULL);
INSERT INTO `sys_m_user` VALUES (777, '03', NULL, 'jerry', '6581e7629b1f9c3c97892aacc901c231', 'MTIzNDU2', 'liyi ly', NULL, 'liyi@qq.com', '15310866234', NULL, NULL, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'jerry', 'ad', 'admin', '2019-01-28 17:42:44', 'admin', '2019-01-28 17:42:44', 1, NULL);
INSERT INTO `sys_m_user` VALUES (778, '03', NULL, 'yhw0128A', '33c5b993e2bc336f803d1aa8c4827952', 'MXEydzNlNHI=', 'yhw0128A', NULL, 'yhw0128A@qq.com', NULL, NULL, 66667085, NULL, NULL, '1', NULL, 0, '2019-02-18 17:51:31', '10.102.10.138', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-01-28 17:58:00', 'admin', '2019-02-18 17:51:31', 1, NULL);
INSERT INTO `sys_m_user` VALUES (779, '03', NULL, 'yangqin2', '1390001c0aa2315fef119969b032a64b', 'MXEydzNlNHI=', 'betty', NULL, 'yangqin@cloud-star.com', '15111824528', NULL, 66667120, NULL, 66667120, '1', NULL, 0, '2019-01-31 13:55:54', '10.102.10.139', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2019-01-29 14:24:13', 'admin', '2019-01-31 13:55:54', 1, NULL);
INSERT INTO `sys_m_user` VALUES (781, '03', NULL, 'yangqin3', '1d1831b6fde3885a920da96131914d0f', 'MXEydzNlNHI=', 'yangqin', NULL, 'yangqin@cloud-star.com.cn', '', NULL, 66667121, NULL, 66667121, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2019-01-30 18:07:54', 'admin', '2019-02-22 11:36:20', 1, NULL);
INSERT INTO `sys_m_user` VALUES (782, '03', NULL, 'tangtaotao', '0aefc6c5be9f58915972fd9d17f8679e', 'MXEydzNlNHI=', 'aaa', NULL, 'tangtaotao@cloud-star.com.cn', '15111824526', NULL, 66667122, NULL, 66667122, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2019-01-31 14:05:32', 'admin', '2019-01-31 14:06:50', 1, NULL);
INSERT INTO `sys_m_user` VALUES (784, '03', NULL, 'liuxiaolu1', '4d4016d46217726c4cf032ad248e8fe8', 'MXEydzNlNHI=', 'liuxiaolu', NULL, 'liuxiaolu@cloud-star.com.cn', '18996360418', NULL, 66667124, NULL, 66667124, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2019-02-11 10:50:36', 'admin', '2019-02-11 10:51:23', 1, NULL);
INSERT INTO `sys_m_user` VALUES (785, '03', NULL, '2121', '33f47f82733d5c5f293a3e96a1e656e9', 'MXEydzNlNHI=', '2121', NULL, '2121@qq.com', NULL, NULL, 66667121, NULL, NULL, '1', NULL, 0, '2019-02-20 18:42:58', '10.102.10.154', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-02-12 11:17:46', 'admin', '2019-02-20 18:42:58', 1, NULL);
INSERT INTO `sys_m_user` VALUES (786, '03', NULL, 'test001', '6581e7629b1f9c3c97892aacc901c231', 'MTIzNDU2', 'test001', NULL, 'test001@qq.com', '18890978909', NULL, 66667085, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'test001', 'ad', '0122', '2019-02-13 14:45:01', '0122', '2019-02-13 14:45:01', 1, NULL);
INSERT INTO `sys_m_user` VALUES (787, '03', NULL, '214', '35b3ea7e4c2bd0834cb113364fc973d9', 'MXEydzNlNHI=', '214', NULL, '214@qq.com', '15111824528', NULL, 66667125, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-02-14 11:37:44', 'admin', '2019-02-15 10:50:56', 1, NULL);
INSERT INTO `sys_m_user` VALUES (788, '03', NULL, 'qincai', 'f6b5bbae887672244b381a1e5b69003b', 'MXEydzNlNHI=', '芹菜1', NULL, 'qincai@123.com', '15111824522', NULL, 66666720, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-02-15 10:28:37', 'admin', '2019-02-18 09:46:13', 1, NULL);
INSERT INTO `sys_m_user` VALUES (790, '03', NULL, 'j021500', '17fe7fe795f6cc0ea0ab9e2b7baacdeb', 'MXEydzNlNHI=', 'j021500', NULL, 'j021500@qq.com', '', NULL, 66667085, 66667119, NULL, '1', NULL, 0, '2019-02-20 18:42:33', '10.102.10.154', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-02-15 14:19:54', 'admin', '2019-02-20 18:42:33', 1, NULL);
INSERT INTO `sys_m_user` VALUES (792, '03', NULL, 'ewfrre', 'b903e643638dc77ce4274fdcd9ad687d', 'MXEydzNlNHI=', 'ewfrre', NULL, 'ewfrre@qq.com', NULL, NULL, 66667085, 66667086, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-02-15 17:26:51', '0122', '2019-02-15 17:26:51', 1, NULL);
INSERT INTO `sys_m_user` VALUES (793, '03', NULL, 'wererw', '32a96316ab350fe7ea37447126ae3f25', 'MXEydzNlNHI=', '123233', NULL, 'wererw@qq.com', NULL, NULL, 66667085, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-02-15 17:35:27', '0122', '2019-02-15 17:35:27', 1, NULL);
INSERT INTO `sys_m_user` VALUES (797, '03', NULL, 'orguser1', '9ef235b0898d462b844b4fcdeaeafc4b', 'MXEydzNlNHI=', '组织用户测试', NULL, 'orguser@12345.com', NULL, NULL, 66667085, 66667086, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-02-18 18:23:54', '0122', '2019-02-18 18:23:54', 1, NULL);
INSERT INTO `sys_m_user` VALUES (798, '03', NULL, 'orguser', '716a5c2e4bc73284e92c7eb823fb4743', 'MXEydzNlNHI=', '在组织创建用户', NULL, 'orguser@12qw.com', NULL, NULL, 66667085, 66667086, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-02-18 18:25:03', '0122', '2019-02-18 18:25:03', 1, NULL);
INSERT INTO `sys_m_user` VALUES (799, '03', NULL, 'yhw219', 'c9d9e48fcb04886d0fd28507084c196e', 'MXEydzNlNHI=', 'yhw219', NULL, 'yhw219@qq.com', NULL, NULL, 66667085, NULL, NULL, '1', NULL, 0, '2019-02-20 18:43:24', '10.102.10.154', NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-02-19 10:31:20', 'admin', '2019-02-20 18:43:24', 1, NULL);
INSERT INTO `sys_m_user` VALUES (801, '03', NULL, 'zcq66', '628cbf3937a749ee1cfce6fc69cc30e7', 'MXEydzNlNHI=', 'zcq66', NULL, 'zcq66@qq.com', NULL, NULL, NULL, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-02-20 17:25:55', 'admin', '2019-02-20 17:25:55', 1, NULL);
INSERT INTO `sys_m_user` VALUES (805, '03', NULL, '704844965', '77750af9040b81aec5d6d8a13fefd099', 'MXEydzNlNHI=', '704844966', NULL, '704844965@qq.com', '17623261998', NULL, 1, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-02-22 15:37:46', 'admin', '2019-02-22 15:37:46', 1, NULL);
INSERT INTO `sys_m_user` VALUES (806, '03', NULL, '7048449661', '83a4c90d98c8e448a058a651c37f5db5', 'MXEydzNlNHI=', '704844966', NULL, '704844966@qq.com', '17623262222', NULL, 1, NULL, NULL, '1', NULL, 0, '2019-02-22 16:15:03', '10.102.10.129', NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-02-22 15:38:44', '7048449661', '2019-02-22 16:15:03', 1, NULL);
INSERT INTO `sys_m_user` VALUES (808, '03', NULL, 'tets-lili', '0e22b3edff9d36c6b4502873064dd782', 'MXEydzNlNHI=', 'hjgkgkjg', NULL, 'tets-lili@qq.com', '15051961591', NULL, 66667085, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', '0122', '2019-02-22 17:34:44', '0122', '2019-02-22 17:34:44', 1, NULL);
INSERT INTO `sys_m_user` VALUES (823, '03', NULL, 'eeee', 'a7e0b886932149a69468a2e687a2f036', 'MXEydzNlNHI=', 'www', NULL, 'eeee@qq.com', NULL, NULL, 1, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-02-26 10:46:51', 'admin', '2019-02-26 10:46:51', 1, NULL);
INSERT INTO `sys_m_user` VALUES (826, '03', NULL, 'cccc1', 'f15854db245a9d4763b46ca492e21a06', 'MXEydzNlNHI=', 'cccc1', NULL, 'cccc1@qq.com', NULL, NULL, 66667125, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-03-05 09:31:20', 'admin', '2019-03-05 09:31:20', 1, NULL);
INSERT INTO `sys_m_user` VALUES (827, '03', NULL, '0306', '58d79e01a6a808a9fc2bb25d892e7992', 'MXEydzNlNHI=', '0306', NULL, '0306@qw.com', NULL, NULL, NULL, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'local', 'admin', '2019-03-06 09:24:46', 'admin', '2019-03-06 09:24:46', 1, NULL);

-- ----------------------------
-- Table structure for sys_m_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_m_user_role`;
CREATE TABLE `sys_m_user_role`  (
  `user_sid` bigint(15) NOT NULL COMMENT '用户SID',
  `role_sid` bigint(15) NOT NULL COMMENT '角色SID',
  `org_sid` bigint(15) NOT NULL COMMENT '组织SID',
  PRIMARY KEY (`user_sid`, `role_sid`, `org_sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_m_user_role
-- ----------------------------
INSERT INTO `sys_m_user_role` VALUES (100, 100, 1);
INSERT INTO `sys_m_user_role` VALUES (410, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (410, 203, 66666941);
INSERT INTO `sys_m_user_role` VALUES (410, 203, 66666942);
INSERT INTO `sys_m_user_role` VALUES (411, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (411, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (411, 206, 66666739);
INSERT INTO `sys_m_user_role` VALUES (411, 207, 66666739);
INSERT INTO `sys_m_user_role` VALUES (411, 208, 66666739);
INSERT INTO `sys_m_user_role` VALUES (411, 209, 66666739);
INSERT INTO `sys_m_user_role` VALUES (411, 211, 66666739);
INSERT INTO `sys_m_user_role` VALUES (413, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (413, 101, 217);
INSERT INTO `sys_m_user_role` VALUES (413, 101, 66666706);
INSERT INTO `sys_m_user_role` VALUES (413, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (415, 101, 66666704);
INSERT INTO `sys_m_user_role` VALUES (415, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (422, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (422, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (422, 203, 217);
INSERT INTO `sys_m_user_role` VALUES (422, 203, 66666713);
INSERT INTO `sys_m_user_role` VALUES (422, 204, 217);
INSERT INTO `sys_m_user_role` VALUES (422, 204, 66666713);
INSERT INTO `sys_m_user_role` VALUES (443, 101, 66666706);
INSERT INTO `sys_m_user_role` VALUES (443, 101, 66666759);
INSERT INTO `sys_m_user_role` VALUES (443, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (443, 203, 66666707);
INSERT INTO `sys_m_user_role` VALUES (446, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (446, 203, 66666707);
INSERT INTO `sys_m_user_role` VALUES (447, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (447, 101, 217);
INSERT INTO `sys_m_user_role` VALUES (447, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (447, 101, 66667013);
INSERT INTO `sys_m_user_role` VALUES (447, 101, 66667085);
INSERT INTO `sys_m_user_role` VALUES (447, 203, 66666728);
INSERT INTO `sys_m_user_role` VALUES (447, 204, 66666728);
INSERT INTO `sys_m_user_role` VALUES (447, 225, 66667085);
INSERT INTO `sys_m_user_role` VALUES (448, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (448, 101, 66666949);
INSERT INTO `sys_m_user_role` VALUES (449, 101, 66666726);
INSERT INTO `sys_m_user_role` VALUES (449, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (449, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (450, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (451, 101, 217);
INSERT INTO `sys_m_user_role` VALUES (451, 101, 66666722);
INSERT INTO `sys_m_user_role` VALUES (451, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (451, 101, 66666759);
INSERT INTO `sys_m_user_role` VALUES (451, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (451, 203, 66666723);
INSERT INTO `sys_m_user_role` VALUES (451, 203, 66666744);
INSERT INTO `sys_m_user_role` VALUES (451, 204, 66666723);
INSERT INTO `sys_m_user_role` VALUES (451, 204, 66666733);
INSERT INTO `sys_m_user_role` VALUES (452, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (452, 101, 217);
INSERT INTO `sys_m_user_role` VALUES (452, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (452, 203, 217);
INSERT INTO `sys_m_user_role` VALUES (452, 204, 217);
INSERT INTO `sys_m_user_role` VALUES (454, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (458, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (461, 101, 66666704);
INSERT INTO `sys_m_user_role` VALUES (461, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (462, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (467, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (467, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (468, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (468, 101, 217);
INSERT INTO `sys_m_user_role` VALUES (468, 101, 66666722);
INSERT INTO `sys_m_user_role` VALUES (468, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (468, 203, 66666727);
INSERT INTO `sys_m_user_role` VALUES (468, 203, 66666737);
INSERT INTO `sys_m_user_role` VALUES (468, 204, 66666727);
INSERT INTO `sys_m_user_role` VALUES (468, 204, 66666735);
INSERT INTO `sys_m_user_role` VALUES (468, 204, 66666737);
INSERT INTO `sys_m_user_role` VALUES (469, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (469, 101, 66666722);
INSERT INTO `sys_m_user_role` VALUES (469, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (469, 204, 66666733);
INSERT INTO `sys_m_user_role` VALUES (474, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (474, 203, 66666733);
INSERT INTO `sys_m_user_role` VALUES (475, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (475, 204, 66666733);
INSERT INTO `sys_m_user_role` VALUES (477, 101, 66666722);
INSERT INTO `sys_m_user_role` VALUES (477, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (477, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (480, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (480, 204, 66666727);
INSERT INTO `sys_m_user_role` VALUES (482, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (483, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (484, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (485, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (487, 101, 66666722);
INSERT INTO `sys_m_user_role` VALUES (487, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (488, 101, 66666726);
INSERT INTO `sys_m_user_role` VALUES (488, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (489, 101, 66666727);
INSERT INTO `sys_m_user_role` VALUES (489, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (497, 101, 66666726);
INSERT INTO `sys_m_user_role` VALUES (497, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (500, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (500, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (500, 203, 66666744);
INSERT INTO `sys_m_user_role` VALUES (500, 203, 66666747);
INSERT INTO `sys_m_user_role` VALUES (500, 203, 66666748);
INSERT INTO `sys_m_user_role` VALUES (500, 203, 66667001);
INSERT INTO `sys_m_user_role` VALUES (500, 203, 66667036);
INSERT INTO `sys_m_user_role` VALUES (500, 204, 66666744);
INSERT INTO `sys_m_user_role` VALUES (500, 204, 66666747);
INSERT INTO `sys_m_user_role` VALUES (500, 204, 66666748);
INSERT INTO `sys_m_user_role` VALUES (502, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (502, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (504, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (506, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (506, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (506, 203, 66666744);
INSERT INTO `sys_m_user_role` VALUES (506, 203, 66666781);
INSERT INTO `sys_m_user_role` VALUES (506, 203, 66666782);
INSERT INTO `sys_m_user_role` VALUES (506, 204, 66666744);
INSERT INTO `sys_m_user_role` VALUES (506, 204, 66666781);
INSERT INTO `sys_m_user_role` VALUES (506, 204, 66666782);
INSERT INTO `sys_m_user_role` VALUES (508, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (508, 204, 66666744);
INSERT INTO `sys_m_user_role` VALUES (509, 101, 66666743);
INSERT INTO `sys_m_user_role` VALUES (509, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (513, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (513, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (518, 101, 66666962);
INSERT INTO `sys_m_user_role` VALUES (518, 203, 66667044);
INSERT INTO `sys_m_user_role` VALUES (518, 203, 66667045);
INSERT INTO `sys_m_user_role` VALUES (518, 203, 66667046);
INSERT INTO `sys_m_user_role` VALUES (518, 203, 66667047);
INSERT INTO `sys_m_user_role` VALUES (519, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (519, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (519, 203, 66666792);
INSERT INTO `sys_m_user_role` VALUES (519, 204, 66666792);
INSERT INTO `sys_m_user_role` VALUES (520, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (521, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (523, 101, 66666750);
INSERT INTO `sys_m_user_role` VALUES (523, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (525, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (525, 204, 66666744);
INSERT INTO `sys_m_user_role` VALUES (533, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (533, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (534, 101, 66666726);
INSERT INTO `sys_m_user_role` VALUES (534, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (536, 101, 66666751);
INSERT INTO `sys_m_user_role` VALUES (536, 101, 66666755);
INSERT INTO `sys_m_user_role` VALUES (536, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (538, 101, 66666759);
INSERT INTO `sys_m_user_role` VALUES (538, 101, 66666761);
INSERT INTO `sys_m_user_role` VALUES (538, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (538, 203, 66666760);
INSERT INTO `sys_m_user_role` VALUES (538, 204, 66666760);
INSERT INTO `sys_m_user_role` VALUES (544, 101, 66666722);
INSERT INTO `sys_m_user_role` VALUES (544, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (549, 101, 66666762);
INSERT INTO `sys_m_user_role` VALUES (549, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (549, 203, 66666766);
INSERT INTO `sys_m_user_role` VALUES (549, 204, 66666766);
INSERT INTO `sys_m_user_role` VALUES (550, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (550, 203, 66666766);
INSERT INTO `sys_m_user_role` VALUES (551, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (551, 203, 66666766);
INSERT INTO `sys_m_user_role` VALUES (554, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (555, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (556, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (557, 101, 66666759);
INSERT INTO `sys_m_user_role` VALUES (558, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (559, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (561, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (564, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (564, 204, 66666730);
INSERT INTO `sys_m_user_role` VALUES (566, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (568, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (568, 204, 66666792);
INSERT INTO `sys_m_user_role` VALUES (569, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (569, 203, 66666792);
INSERT INTO `sys_m_user_role` VALUES (570, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (570, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (571, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (571, 203, 66666913);
INSERT INTO `sys_m_user_role` VALUES (571, 204, 66666913);
INSERT INTO `sys_m_user_role` VALUES (571, 204, 66667061);
INSERT INTO `sys_m_user_role` VALUES (573, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (573, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (574, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (574, 204, 66666792);
INSERT INTO `sys_m_user_role` VALUES (577, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (578, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (578, 206, 66666739);
INSERT INTO `sys_m_user_role` VALUES (579, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (580, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (580, 203, 66666799);
INSERT INTO `sys_m_user_role` VALUES (580, 204, 66666799);
INSERT INTO `sys_m_user_role` VALUES (580, 206, 66666739);
INSERT INTO `sys_m_user_role` VALUES (581, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (581, 101, 66666797);
INSERT INTO `sys_m_user_role` VALUES (582, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (582, 204, 66666744);
INSERT INTO `sys_m_user_role` VALUES (583, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (583, 203, 66666799);
INSERT INTO `sys_m_user_role` VALUES (584, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (584, 204, 66666799);
INSERT INTO `sys_m_user_role` VALUES (585, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (585, 204, 66666744);
INSERT INTO `sys_m_user_role` VALUES (586, 208, 66666739);
INSERT INTO `sys_m_user_role` VALUES (587, 204, 66666744);
INSERT INTO `sys_m_user_role` VALUES (588, 209, 66666739);
INSERT INTO `sys_m_user_role` VALUES (589, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (591, 101, 66666805);
INSERT INTO `sys_m_user_role` VALUES (591, 101, 66666922);
INSERT INTO `sys_m_user_role` VALUES (591, 203, 66666806);
INSERT INTO `sys_m_user_role` VALUES (592, 101, 66666805);
INSERT INTO `sys_m_user_role` VALUES (593, 203, 66666806);
INSERT INTO `sys_m_user_role` VALUES (593, 204, 66666806);
INSERT INTO `sys_m_user_role` VALUES (594, 204, 66666806);
INSERT INTO `sys_m_user_role` VALUES (596, 101, 66666795);
INSERT INTO `sys_m_user_role` VALUES (596, 101, 66666809);
INSERT INTO `sys_m_user_role` VALUES (596, 101, 66666810);
INSERT INTO `sys_m_user_role` VALUES (596, 101, 66666811);
INSERT INTO `sys_m_user_role` VALUES (600, 101, 66666954);
INSERT INTO `sys_m_user_role` VALUES (600, 203, 66667070);
INSERT INTO `sys_m_user_role` VALUES (601, 203, 66666959);
INSERT INTO `sys_m_user_role` VALUES (601, 203, 66666960);
INSERT INTO `sys_m_user_role` VALUES (601, 203, 66667070);
INSERT INTO `sys_m_user_role` VALUES (602, 204, 66666959);
INSERT INTO `sys_m_user_role` VALUES (607, 101, 66666726);
INSERT INTO `sys_m_user_role` VALUES (613, 101, 66666809);
INSERT INTO `sys_m_user_role` VALUES (613, 101, 66666810);
INSERT INTO `sys_m_user_role` VALUES (615, 101, 66666910);
INSERT INTO `sys_m_user_role` VALUES (616, 101, 66666912);
INSERT INTO `sys_m_user_role` VALUES (616, 101, 66667002);
INSERT INTO `sys_m_user_role` VALUES (616, 203, 66666947);
INSERT INTO `sys_m_user_role` VALUES (616, 203, 66667003);
INSERT INTO `sys_m_user_role` VALUES (622, 101, 66666912);
INSERT INTO `sys_m_user_role` VALUES (623, 101, 66666915);
INSERT INTO `sys_m_user_role` VALUES (623, 101, 66666916);
INSERT INTO `sys_m_user_role` VALUES (623, 101, 66666918);
INSERT INTO `sys_m_user_role` VALUES (623, 204, 66666917);
INSERT INTO `sys_m_user_role` VALUES (624, 204, 66666917);
INSERT INTO `sys_m_user_role` VALUES (624, 212, 66666916);
INSERT INTO `sys_m_user_role` VALUES (625, 203, 66666917);
INSERT INTO `sys_m_user_role` VALUES (626, 204, 66666917);
INSERT INTO `sys_m_user_role` VALUES (629, 101, 66666922);
INSERT INTO `sys_m_user_role` VALUES (631, 101, 66666922);
INSERT INTO `sys_m_user_role` VALUES (631, 203, 66666923);
INSERT INTO `sys_m_user_role` VALUES (631, 204, 66666923);
INSERT INTO `sys_m_user_role` VALUES (633, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (633, 101, 66666924);
INSERT INTO `sys_m_user_role` VALUES (633, 203, 66666951);
INSERT INTO `sys_m_user_role` VALUES (633, 203, 66666952);
INSERT INTO `sys_m_user_role` VALUES (633, 203, 66666953);
INSERT INTO `sys_m_user_role` VALUES (634, 101, 66666926);
INSERT INTO `sys_m_user_role` VALUES (634, 101, 66666927);
INSERT INTO `sys_m_user_role` VALUES (634, 101, 66666928);
INSERT INTO `sys_m_user_role` VALUES (634, 203, 66666929);
INSERT INTO `sys_m_user_role` VALUES (634, 203, 66666930);
INSERT INTO `sys_m_user_role` VALUES (635, 101, 66666922);
INSERT INTO `sys_m_user_role` VALUES (635, 203, 66666931);
INSERT INTO `sys_m_user_role` VALUES (636, 203, 66666931);
INSERT INTO `sys_m_user_role` VALUES (637, 204, 66666931);
INSERT INTO `sys_m_user_role` VALUES (638, 101, 66666932);
INSERT INTO `sys_m_user_role` VALUES (638, 203, 66666950);
INSERT INTO `sys_m_user_role` VALUES (639, 101, 66666934);
INSERT INTO `sys_m_user_role` VALUES (639, 101, 66666935);
INSERT INTO `sys_m_user_role` VALUES (639, 203, 66666937);
INSERT INTO `sys_m_user_role` VALUES (640, 203, 66666936);
INSERT INTO `sys_m_user_role` VALUES (641, 204, 66666936);
INSERT INTO `sys_m_user_role` VALUES (642, 203, 66666937);
INSERT INTO `sys_m_user_role` VALUES (643, 204, 66666937);
INSERT INTO `sys_m_user_role` VALUES (647, 101, 66666934);
INSERT INTO `sys_m_user_role` VALUES (648, 101, 66666934);
INSERT INTO `sys_m_user_role` VALUES (649, 101, 66666797);
INSERT INTO `sys_m_user_role` VALUES (650, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (651, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (652, 204, 66666937);
INSERT INTO `sys_m_user_role` VALUES (655, 101, 66666955);
INSERT INTO `sys_m_user_role` VALUES (655, 203, 66666956);
INSERT INTO `sys_m_user_role` VALUES (658, 203, 66666956);
INSERT INTO `sys_m_user_role` VALUES (659, 204, 66666956);
INSERT INTO `sys_m_user_role` VALUES (660, 101, 66666957);
INSERT INTO `sys_m_user_role` VALUES (661, 101, 66666958);
INSERT INTO `sys_m_user_role` VALUES (662, 101, 66666961);
INSERT INTO `sys_m_user_role` VALUES (662, 203, 66666977);
INSERT INTO `sys_m_user_role` VALUES (664, 101, 66666963);
INSERT INTO `sys_m_user_role` VALUES (664, 203, 66666974);
INSERT INTO `sys_m_user_role` VALUES (664, 203, 66667031);
INSERT INTO `sys_m_user_role` VALUES (665, 203, 66666974);
INSERT INTO `sys_m_user_role` VALUES (665, 203, 66667031);
INSERT INTO `sys_m_user_role` VALUES (666, 204, 66666974);
INSERT INTO `sys_m_user_role` VALUES (666, 204, 66667031);
INSERT INTO `sys_m_user_role` VALUES (667, 203, 66666973);
INSERT INTO `sys_m_user_role` VALUES (668, 204, 66666973);
INSERT INTO `sys_m_user_role` VALUES (670, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (671, 203, 66666975);
INSERT INTO `sys_m_user_role` VALUES (674, 101, 66666722);
INSERT INTO `sys_m_user_role` VALUES (674, 101, 66667013);
INSERT INTO `sys_m_user_role` VALUES (674, 101, 66667085);
INSERT INTO `sys_m_user_role` VALUES (674, 203, 217);
INSERT INTO `sys_m_user_role` VALUES (674, 204, 217);
INSERT INTO `sys_m_user_role` VALUES (675, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (675, 101, 66666954);
INSERT INTO `sys_m_user_role` VALUES (675, 203, 66667036);
INSERT INTO `sys_m_user_role` VALUES (676, 101, 66667004);
INSERT INTO `sys_m_user_role` VALUES (678, 101, 66667006);
INSERT INTO `sys_m_user_role` VALUES (679, 101, 66667008);
INSERT INTO `sys_m_user_role` VALUES (680, 101, 66667012);
INSERT INTO `sys_m_user_role` VALUES (681, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (681, 101, 66667013);
INSERT INTO `sys_m_user_role` VALUES (681, 203, 66667037);
INSERT INTO `sys_m_user_role` VALUES (681, 203, 66667040);
INSERT INTO `sys_m_user_role` VALUES (682, 101, 66667013);
INSERT INTO `sys_m_user_role` VALUES (682, 101, 66667026);
INSERT INTO `sys_m_user_role` VALUES (683, 203, 66667027);
INSERT INTO `sys_m_user_role` VALUES (684, 204, 66667027);
INSERT INTO `sys_m_user_role` VALUES (685, 203, 66667027);
INSERT INTO `sys_m_user_role` VALUES (686, 215, 66667026);
INSERT INTO `sys_m_user_role` VALUES (687, 101, 66667030);
INSERT INTO `sys_m_user_role` VALUES (688, 101, 66667013);
INSERT INTO `sys_m_user_role` VALUES (689, 203, 66667032);
INSERT INTO `sys_m_user_role` VALUES (690, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (691, 101, 66666962);
INSERT INTO `sys_m_user_role` VALUES (694, 204, 66667040);
INSERT INTO `sys_m_user_role` VALUES (695, 203, 66667040);
INSERT INTO `sys_m_user_role` VALUES (697, 101, 66666962);
INSERT INTO `sys_m_user_role` VALUES (697, 101, 66667013);
INSERT INTO `sys_m_user_role` VALUES (702, 218, 66667013);
INSERT INTO `sys_m_user_role` VALUES (703, 220, 66667013);
INSERT INTO `sys_m_user_role` VALUES (704, 218, 66667013);
INSERT INTO `sys_m_user_role` VALUES (705, 219, 66667013);
INSERT INTO `sys_m_user_role` VALUES (706, 220, 66667013);
INSERT INTO `sys_m_user_role` VALUES (707, 220, 66667013);
INSERT INTO `sys_m_user_role` VALUES (709, 203, 66666973);
INSERT INTO `sys_m_user_role` VALUES (709, 203, 66667047);
INSERT INTO `sys_m_user_role` VALUES (710, 204, 66666973);
INSERT INTO `sys_m_user_role` VALUES (710, 204, 66667047);
INSERT INTO `sys_m_user_role` VALUES (711, 222, 66666962);
INSERT INTO `sys_m_user_role` VALUES (712, 223, 66666962);
INSERT INTO `sys_m_user_role` VALUES (713, 222, 66666962);
INSERT INTO `sys_m_user_role` VALUES (714, 223, 66666962);
INSERT INTO `sys_m_user_role` VALUES (715, 101, 66667049);
INSERT INTO `sys_m_user_role` VALUES (715, 203, 66667050);
INSERT INTO `sys_m_user_role` VALUES (716, 203, 66667050);
INSERT INTO `sys_m_user_role` VALUES (717, 204, 66667050);
INSERT INTO `sys_m_user_role` VALUES (718, 101, 66667054);
INSERT INTO `sys_m_user_role` VALUES (719, 101, 66667026);
INSERT INTO `sys_m_user_role` VALUES (720, 101, 66667055);
INSERT INTO `sys_m_user_role` VALUES (722, 101, 66667058);
INSERT INTO `sys_m_user_role` VALUES (726, 101, 66666739);
INSERT INTO `sys_m_user_role` VALUES (726, 203, 66667061);
INSERT INTO `sys_m_user_role` VALUES (728, 203, 66667070);
INSERT INTO `sys_m_user_role` VALUES (729, 204, 66667070);
INSERT INTO `sys_m_user_role` VALUES (730, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (733, 101, 66667073);
INSERT INTO `sys_m_user_role` VALUES (734, 101, 66667074);
INSERT INTO `sys_m_user_role` VALUES (734, 203, 66667082);
INSERT INTO `sys_m_user_role` VALUES (734, 203, 66667083);
INSERT INTO `sys_m_user_role` VALUES (735, 203, 66666973);
INSERT INTO `sys_m_user_role` VALUES (739, 204, 66667075);
INSERT INTO `sys_m_user_role` VALUES (740, 223, 66666962);
INSERT INTO `sys_m_user_role` VALUES (741, 203, 66667075);
INSERT INTO `sys_m_user_role` VALUES (744, 204, 66667081);
INSERT INTO `sys_m_user_role` VALUES (746, 203, 66667081);
INSERT INTO `sys_m_user_role` VALUES (747, 203, 66667081);
INSERT INTO `sys_m_user_role` VALUES (748, 101, 66667074);
INSERT INTO `sys_m_user_role` VALUES (751, 101, 66667084);
INSERT INTO `sys_m_user_role` VALUES (752, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (752, 101, 66667085);
INSERT INTO `sys_m_user_role` VALUES (752, 203, 66667086);
INSERT INTO `sys_m_user_role` VALUES (752, 203, 66667119);
INSERT INTO `sys_m_user_role` VALUES (753, 203, 66667086);
INSERT INTO `sys_m_user_role` VALUES (756, 204, 66667086);
INSERT INTO `sys_m_user_role` VALUES (765, 101, 66667105);
INSERT INTO `sys_m_user_role` VALUES (768, 101, 66667110);
INSERT INTO `sys_m_user_role` VALUES (769, 101, 66667111);
INSERT INTO `sys_m_user_role` VALUES (770, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (772, 101, 66667118);
INSERT INTO `sys_m_user_role` VALUES (776, 101, 66667085);
INSERT INTO `sys_m_user_role` VALUES (778, 204, 66667119);
INSERT INTO `sys_m_user_role` VALUES (779, 101, 66667120);
INSERT INTO `sys_m_user_role` VALUES (782, 101, 66667122);
INSERT INTO `sys_m_user_role` VALUES (784, 101, 66667124);
INSERT INTO `sys_m_user_role` VALUES (785, 101, 66667121);
INSERT INTO `sys_m_user_role` VALUES (788, 101, 66666720);
INSERT INTO `sys_m_user_role` VALUES (790, 204, 66667119);
INSERT INTO `sys_m_user_role` VALUES (797, 203, 66667086);
INSERT INTO `sys_m_user_role` VALUES (798, 204, 66667086);
INSERT INTO `sys_m_user_role` VALUES (799, 203, 66667127);
INSERT INTO `sys_m_user_role` VALUES (799, 203, 66667129);
INSERT INTO `sys_m_user_role` VALUES (799, 203, 66667132);
INSERT INTO `sys_m_user_role` VALUES (805, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (806, 101, 1);
INSERT INTO `sys_m_user_role` VALUES (823, 101, 1);

-- ----------------------------
-- Table structure for sys_m_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_m_user_token`;
CREATE TABLE `sys_m_user_token`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'uuid',
  `user_sid` bigint(15) NULL DEFAULT NULL COMMENT '用户SID',
  `access_token` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '访问TOKEN',
  `access_expire` bigint(20) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22630 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户Token表' ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;
