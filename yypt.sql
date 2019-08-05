/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : yypt

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-08-05 20:52:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(12) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(200) DEFAULT NULL,
  `order_code` varchar(200) DEFAULT NULL,
  `parentid` int(12) NOT NULL,
  `memo` varchar(2000) DEFAULT NULL,
  `code` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('0', '最高级单位', '0', '-1', '最高级单位不可删除', null, '2019-07-12 17:12:07', '2019-07-14 07:24:23');
INSERT INTO `sys_dept` VALUES ('15', '总公司', '1', '0', null, '2', '2019-07-14 07:23:29', '2019-07-14 07:23:29');
INSERT INTO `sys_dept` VALUES ('17', '总公司2', '2', '0', null, '12112', '2019-07-24 13:32:50', '2019-07-29 09:25:27');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(12) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) DEFAULT NULL,
  `component` varchar(100) DEFAULT NULL,
  `parentid` int(12) NOT NULL,
  `order_code` varchar(5) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `icon` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `permission` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0', '', '', '-1', '0', '顶级目录', 'link', '2019-06-10 18:10:28', '2019-06-24 23:55:00', null);
INSERT INTO `sys_menu` VALUES ('1', '/system', 'Layout', '0', '1', '系统设置', 'setting', '2019-06-10 16:35:07', '2019-06-19 16:35:11', 'aa');
INSERT INTO `sys_menu` VALUES ('2', '/user', 'system/user', '1', '', '人员管理', 'user', '2019-06-09 23:17:43', '2019-07-05 03:53:20', 'aa');
INSERT INTO `sys_menu` VALUES ('3', '/menu', 'system/menu', '1', null, '功能管理', 'menu', '2019-06-10 18:10:28', '2019-07-05 03:53:13', null);
INSERT INTO `sys_menu` VALUES ('11', 'sysrole', 'system/role', '1', null, '角色管理', 'role', '2019-06-25 06:53:38', '2019-06-25 06:58:06', null);
INSERT INTO `sys_menu` VALUES ('12', '/sysdept', 'system/dept', '1', '', '部门管理', 'dept', '2019-07-09 22:40:32', '2019-07-12 03:35:33', null);
INSERT INTO `sys_menu` VALUES ('13', '/syslog', 'Layout', '0', '2', '系统日志', 'syslog', '2019-08-01 01:27:47', '2019-08-01 01:37:19', null);
INSERT INTO `sys_menu` VALUES ('14', '/loginlog', 'systemlog/loginlog', '13', null, '登录日志', 'loginlog', '2019-08-01 01:39:22', '2019-08-02 07:57:02', null);

-- ----------------------------
-- Table structure for sys_menu_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_button`;
CREATE TABLE `sys_menu_button` (
  `button_id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT '' COMMENT '按钮名称',
  `request_path` varchar(200) DEFAULT '' COMMENT '请求路径',
  `icon` varchar(200) DEFAULT NULL COMMENT '图标',
  `menu_id` int(12) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `permission` varchar(200) DEFAULT NULL,
  `order_code` int(20) DEFAULT NULL,
  PRIMARY KEY (`button_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_button
-- ----------------------------
INSERT INTO `sys_menu_button` VALUES ('3', '新增', 'aa', 'new', '2', '2019-07-09 15:15:49', '2019-07-24 12:28:03', 'user:add', null);
INSERT INTO `sys_menu_button` VALUES ('4', '修改', '1', 'edit', '2', '2019-07-09 15:16:04', '2019-07-24 12:28:25', 'user:edit', null);
INSERT INTO `sys_menu_button` VALUES ('6', '新增', '', 'new', '11', '2019-07-10 09:40:52', '2019-07-10 09:40:52', 'role:add', null);
INSERT INTO `sys_menu_button` VALUES ('7', '修改', '', 'edit', '11', '2019-07-10 09:41:17', '2019-07-10 09:41:17', 'role:edit', null);
INSERT INTO `sys_menu_button` VALUES ('8', '删除', '', 'delete', '11', '2019-07-10 09:41:56', '2019-07-10 09:41:56', 'role:delete', null);
INSERT INTO `sys_menu_button` VALUES ('9', '授权人员', '', 'auth', '11', '2019-07-10 09:42:29', '2019-07-10 09:42:29', 'role:authuser', null);
INSERT INTO `sys_menu_button` VALUES ('10', '删除角色人员', '', 'delete', '11', '2019-07-11 09:15:01', '2019-07-11 09:15:01', 'role:deleteroleuser', null);
INSERT INTO `sys_menu_button` VALUES ('11', '新增', '', 'new', '12', '2019-07-12 08:46:15', '2019-07-12 08:58:20', 'dept:add', null);
INSERT INTO `sys_menu_button` VALUES ('12', '修改', '', 'edit', '12', '2019-07-12 08:46:44', '2019-07-12 08:46:44', 'dept:edit', null);
INSERT INTO `sys_menu_button` VALUES ('13', '删除', '', 'delete', '12', '2019-07-12 08:47:17', '2019-07-12 08:47:17', 'dept:delete', null);
INSERT INTO `sys_menu_button` VALUES ('14', '删除', '', 'delete', '2', '2019-07-24 12:28:54', '2019-07-24 12:28:54', 'user:delete', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(12) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(60) NOT NULL,
  `role_code` varchar(30) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `memo` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `rol_name_idex` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'gly', '2019-04-02 05:21:03', '2019-07-11 07:50:51', '1212112');
INSERT INTO `sys_role` VALUES ('3', '测试员', 'gly', '2019-04-02 05:22:17', '2019-07-11 08:00:32', '3');

-- ----------------------------
-- Table structure for sys_role_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_button`;
CREATE TABLE `sys_role_button` (
  `role_button_id` int(12) NOT NULL AUTO_INCREMENT,
  `role_id` int(12) DEFAULT NULL,
  `menu_id` int(12) DEFAULT NULL,
  `button_id` int(12) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_button_id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_button
-- ----------------------------
INSERT INTO `sys_role_button` VALUES ('88', '1', '11', '6', '2019-07-13 10:47:32');
INSERT INTO `sys_role_button` VALUES ('89', '1', '11', '7', '2019-07-13 10:47:32');
INSERT INTO `sys_role_button` VALUES ('90', '1', '11', '8', '2019-07-13 10:47:32');
INSERT INTO `sys_role_button` VALUES ('91', '1', '11', '9', '2019-07-13 10:47:32');
INSERT INTO `sys_role_button` VALUES ('92', '1', '11', '10', '2019-07-13 10:47:32');
INSERT INTO `sys_role_button` VALUES ('105', '1', '2', '3', '2019-07-24 12:32:25');
INSERT INTO `sys_role_button` VALUES ('106', '1', '2', '4', '2019-07-24 12:32:25');
INSERT INTO `sys_role_button` VALUES ('107', '1', '2', '14', '2019-07-24 12:32:25');
INSERT INTO `sys_role_button` VALUES ('108', '3', '11', '6', '2019-07-29 08:30:06');
INSERT INTO `sys_role_button` VALUES ('109', '3', '11', '7', '2019-07-29 08:30:06');
INSERT INTO `sys_role_button` VALUES ('110', '3', '11', '8', '2019-07-29 08:30:06');
INSERT INTO `sys_role_button` VALUES ('111', '3', '11', '9', '2019-07-29 08:30:06');
INSERT INTO `sys_role_button` VALUES ('112', '3', '11', '10', '2019-07-29 08:30:06');
INSERT INTO `sys_role_button` VALUES ('121', '1', '12', '11', '2019-07-29 13:50:18');
INSERT INTO `sys_role_button` VALUES ('122', '1', '12', '12', '2019-07-29 13:50:18');
INSERT INTO `sys_role_button` VALUES ('123', '1', '12', '13', '2019-07-29 13:50:18');
INSERT INTO `sys_role_button` VALUES ('124', '3', '2', '3', '2019-07-30 09:03:58');
INSERT INTO `sys_role_button` VALUES ('125', '3', '2', '4', '2019-07-30 09:03:58');
INSERT INTO `sys_role_button` VALUES ('126', '3', '2', '14', '2019-07-30 09:03:58');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_menu_id` int(12) NOT NULL AUTO_INCREMENT,
  `role_id` int(12) NOT NULL,
  `menu_id` int(12) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=610 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('598', '1', '1', '2019-08-01 01:44:56');
INSERT INTO `sys_role_menu` VALUES ('599', '1', '3', '2019-08-01 01:44:56');
INSERT INTO `sys_role_menu` VALUES ('600', '1', '11', '2019-08-01 01:44:56');
INSERT INTO `sys_role_menu` VALUES ('601', '1', '2', '2019-08-01 01:44:56');
INSERT INTO `sys_role_menu` VALUES ('602', '1', '12', '2019-08-01 01:44:56');
INSERT INTO `sys_role_menu` VALUES ('603', '1', '13', '2019-08-01 01:44:56');
INSERT INTO `sys_role_menu` VALUES ('604', '1', '14', '2019-08-01 01:44:56');
INSERT INTO `sys_role_menu` VALUES ('605', '3', '1', '2019-08-01 01:45:15');
INSERT INTO `sys_role_menu` VALUES ('606', '3', '3', '2019-08-01 01:45:15');
INSERT INTO `sys_role_menu` VALUES ('607', '3', '11', '2019-08-01 01:45:15');
INSERT INTO `sys_role_menu` VALUES ('608', '3', '2', '2019-08-01 01:45:15');
INSERT INTO `sys_role_menu` VALUES ('609', '3', '12', '2019-08-01 01:45:15');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `role_user_id` int(12) NOT NULL AUTO_INCREMENT,
  `role_id` int(12) DEFAULT NULL,
  `user_id` int(12) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_user_id`),
  KEY `role_fk` (`role_id`),
  KEY `user_fk` (`user_id`),
  CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1', '18', '2019-04-02 05:21:03');
INSERT INTO `sys_role_user` VALUES ('23', '1', '1', '2019-07-31 11:43:54');
INSERT INTO `sys_role_user` VALUES ('26', '3', '1', '2019-07-31 11:48:29');
INSERT INTO `sys_role_user` VALUES ('27', '1', '20', '2019-08-02 05:46:31');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `avatar` char(255) DEFAULT NULL,
  `mobile` char(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `status` int(1) DEFAULT '1' COMMENT '0 停用  1正常  2 锁定',
  `lock_date` datetime DEFAULT NULL COMMENT '锁定日期',
  `idcard` varchar(200) DEFAULT NULL COMMENT '身份证号',
  `real_name` varchar(200) DEFAULT NULL COMMENT '真实姓名',
  `dept_id` int(12) DEFAULT NULL COMMENT '部门ID',
  `openid` varchar(2000) DEFAULT NULL COMMENT '微信openid',
  `sex` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_idx` (`user_name`) USING BTREE,
  KEY `user_dept_fk` (`dept_id`),
  CONSTRAINT `user_dept_fk` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'zhk', 'ba23ab292d097c20d41ffea422a716ee', null, '18237716257', '2019-04-06 22:27:32', '2019-07-31 12:21:28', '1', null, '787878787878', '张红科', '15', null, '1');
INSERT INTO `sys_user` VALUES ('18', 'kk', 'd24f37b77a107f95a86648eeecf4c228', null, '18237716258', '2019-04-04 22:27:37', '2019-07-31 12:21:02', '1', null, '1', '2121', '17', null, '2');
INSERT INTO `sys_user` VALUES ('20', '123', null, '', '18237716253', '2019-07-31 01:53:19', '2019-07-31 01:58:06', '1', null, '41132819951012336', '张红科', '15', '', '1');

-- ----------------------------
-- Table structure for sys_web_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_web_login_log`;
CREATE TABLE `sys_web_login_log` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `location` varchar(400) DEFAULT NULL,
  `ip` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=527 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_web_login_log
-- ----------------------------
INSERT INTO `sys_web_login_log` VALUES ('1', 'kk', '2019-04-06 06:54:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('2', 'kk', '2019-04-06 07:24:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('3', 'kk', '2019-04-06 07:25:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('4', 'kk', '2019-04-06 07:26:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('5', 'kk', '2019-04-06 07:29:33', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('6', 'kk', '2019-04-06 07:30:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('7', 'kk', '2019-04-06 07:31:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('8', 'kk', '2019-04-06 07:35:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('9', 'kk', '2019-04-06 07:36:16', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('10', 'kk', '2019-04-06 07:39:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('11', 'kk', '2019-04-06 07:44:11', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('12', 'kk', '2019-04-06 07:44:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('13', 'kk', '2019-04-06 07:45:08', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('14', 'kk', '2019-04-06 07:46:05', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('15', 'kk', '2019-04-06 07:46:40', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('16', 'kk', '2019-04-06 07:47:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('17', 'kk', '2019-04-06 07:47:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('18', 'kk', '2019-04-06 08:06:41', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('19', 'kk', '2019-04-06 08:07:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('20', 'kk', '2019-04-06 08:23:44', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('21', 'kk', '2019-04-06 08:25:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('22', 'kk', '2019-04-06 08:27:33', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('23', 'kk', '2019-04-06 08:29:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('24', 'kk', '2019-04-06 08:30:49', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('25', 'kk', '2019-04-06 08:31:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('26', 'kk', '2019-04-06 08:31:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('27', 'kk', '2019-04-06 08:33:12', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('28', 'kk', '2019-04-06 08:33:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('29', 'kk', '2019-04-06 08:34:45', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('30', 'kk', '2019-04-06 08:46:31', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('31', 'kk', '2019-04-06 08:50:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('32', 'kk', '2019-04-06 08:53:33', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('33', 'kk', '2019-04-06 08:55:05', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('34', 'kk', '2019-04-06 11:27:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('35', '123', '2019-04-06 11:45:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('36', 'kk', '2019-04-06 12:30:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('37', 'kk', '2019-04-06 12:56:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('38', 'kk', '2019-04-06 12:59:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('39', 'kk', '2019-04-06 13:19:38', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('40', 'kk', '2019-04-06 14:21:21', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('41', 'kk', '2019-04-06 15:56:32', '内网IP|0|0|内网IP|内网IP', '192.168.31.116');
INSERT INTO `sys_web_login_log` VALUES ('42', 'kk', '2019-04-07 05:01:12', '内网IP|0|0|内网IP|内网IP', '192.168.31.116');
INSERT INTO `sys_web_login_log` VALUES ('43', 'kk', '2019-04-07 05:01:18', '内网IP|0|0|内网IP|内网IP', '192.168.31.116');
INSERT INTO `sys_web_login_log` VALUES ('44', 'kk', '2019-04-07 05:01:23', '内网IP|0|0|内网IP|内网IP', '192.168.31.116');
INSERT INTO `sys_web_login_log` VALUES ('45', 'kk', '2019-04-07 05:32:03', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('46', 'kk', '2019-04-07 06:57:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('47', 'kk', '2019-04-07 07:29:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('48', 'kk', '2019-04-07 08:32:46', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('49', 'kk', '2019-04-07 11:11:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('50', 'kk', '2019-04-07 14:44:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('51', 'kk', '2019-04-08 01:05:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('52', 'kk', '2019-04-08 01:09:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('53', 'kk', '2019-04-08 02:34:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('54', 'kk', '2019-06-06 08:12:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('55', 'kk', '2019-06-06 08:13:05', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('56', 'kk', '2019-06-06 08:13:32', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('57', 'kk', '2019-06-06 08:16:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('58', 'kk', '2019-06-06 08:16:49', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('59', 'kk', '2019-06-06 08:20:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('60', 'kk', '2019-06-06 08:20:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('61', 'kk', '2019-06-06 08:20:54', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('62', 'kk', '2019-06-06 08:20:55', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('63', 'kk', '2019-06-06 08:20:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('64', 'kk', '2019-06-06 08:25:55', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('65', 'kk', '2019-06-06 08:27:08', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('66', 'kk', '2019-06-06 08:29:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('67', 'kk', '2019-06-06 08:33:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('68', 'kk', '2019-06-06 08:38:57', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('69', 'kk', '2019-06-06 08:40:58', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('70', 'kk', '2019-06-08 06:38:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('71', 'kk', '2019-06-08 06:51:46', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('72', 'kk', '2019-06-08 06:52:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('73', 'kk', '2019-06-08 06:52:23', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('74', 'kk', '2019-06-08 06:58:54', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('75', 'kk', '2019-06-08 06:58:57', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('76', 'kk', '2019-06-08 06:59:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('77', 'kk', '2019-06-08 06:59:41', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('78', 'kk', '2019-06-08 07:00:45', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('79', 'kk', '2019-06-08 07:00:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('80', 'kk', '2019-06-08 07:05:41', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('81', 'kk', '2019-06-08 07:06:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('82', 'kk', '2019-06-08 07:08:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('83', 'kk', '2019-06-08 07:09:17', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('84', 'kk', '2019-06-08 07:10:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('85', 'kk', '2019-06-08 07:11:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('86', 'kk', '2019-06-08 07:11:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('87', 'kk', '2019-06-08 07:12:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('88', 'kk', '2019-06-08 07:12:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('89', 'kk', '2019-06-08 07:12:32', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('90', 'kk', '2019-06-08 07:12:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('91', 'kk', '2019-06-08 07:41:46', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('92', 'kk', '2019-06-08 07:42:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('93', 'kk', '2019-06-08 07:45:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('94', 'kk', '2019-06-08 07:46:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('95', 'kk', '2019-06-08 07:49:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('96', 'kk', '2019-06-08 07:53:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('97', 'kk', '2019-06-08 08:03:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('98', 'kk', '2019-06-08 08:03:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('99', 'kk', '2019-06-08 08:03:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('100', 'kk', '2019-06-08 08:03:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('101', 'kk', '2019-06-08 08:03:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('102', 'kk', '2019-06-08 08:03:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('103', 'kk', '2019-06-08 08:03:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('104', 'kk', '2019-06-08 08:03:31', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('105', 'kk', '2019-06-08 08:04:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('106', 'kk', '2019-06-08 09:48:55', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('107', 'kk', '2019-06-08 09:49:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('108', 'kk', '2019-06-08 09:49:52', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('109', 'kk', '2019-06-08 09:49:55', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('110', 'kk', '2019-06-08 09:53:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('111', 'kk', '2019-06-08 14:49:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('112', 'kk', '2019-06-08 14:49:32', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('113', 'kk', '2019-06-09 05:21:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('114', 'kk', '2019-06-09 05:26:40', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('115', 'kk', '2019-06-09 05:29:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('116', 'kk', '2019-06-09 05:31:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('117', 'kk', '2019-06-09 05:31:44', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('118', 'kk', '2019-06-09 05:32:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('119', 'kk', '2019-06-09 05:34:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('120', 'kk', '2019-06-09 05:34:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('121', 'kk', '2019-06-09 05:34:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('122', 'kk', '2019-06-09 05:34:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('123', 'kk', '2019-06-09 05:34:40', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('124', 'kk', '2019-06-09 05:34:41', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('125', 'kk', '2019-06-09 05:35:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('126', 'kk', '2019-06-09 05:38:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('127', 'kk', '2019-06-09 05:39:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('128', 'kk', '2019-06-09 05:44:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('129', 'kk', '2019-06-09 05:49:03', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('130', 'kk', '2019-06-09 06:03:56', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('131', 'kk', '2019-06-09 06:05:45', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('132', 'kk', '2019-06-09 06:06:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('133', 'kk', '2019-06-09 06:06:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('134', 'kk', '2019-06-09 06:06:40', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('135', 'kk', '2019-06-09 06:07:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('136', 'kk', '2019-06-09 06:07:40', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('137', 'kk', '2019-06-09 06:18:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('138', 'kk', '2019-06-09 06:23:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('139', 'kk', '2019-06-09 06:24:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('140', 'kk', '2019-06-09 06:25:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('141', 'kk', '2019-06-09 06:26:11', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('142', 'kk', '2019-06-09 06:27:08', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('143', 'kk', '2019-06-09 06:27:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('144', 'kk', '2019-06-09 06:29:05', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('145', 'kk', '2019-06-09 06:33:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('146', 'kk', '2019-06-09 06:33:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('147', 'kk', '2019-06-09 06:33:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('148', 'kk', '2019-06-09 06:33:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('149', 'kk', '2019-06-09 06:33:32', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('150', 'kk', '2019-06-09 06:33:33', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('151', 'kk', '2019-06-09 06:34:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('152', 'kk', '2019-06-09 06:34:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('153', 'kk', '2019-06-09 06:36:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('154', 'kk', '2019-06-09 06:36:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('155', 'kk', '2019-06-09 06:36:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('156', 'kk', '2019-06-09 06:36:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('157', 'kk', '2019-06-09 06:39:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('158', 'kk', '2019-06-09 06:39:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('159', 'kk', '2019-06-09 06:40:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('160', 'kk', '2019-06-09 06:40:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('161', 'kk', '2019-06-09 06:40:31', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('162', 'kk', '2019-06-09 06:46:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('163', 'kk', '2019-06-09 06:47:57', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('164', 'kk', '2019-06-09 06:50:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('165', 'kk', '2019-06-09 06:50:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('166', 'kk', '2019-06-09 06:51:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('167', 'kk', '2019-06-09 06:51:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('168', 'kk', '2019-06-09 06:52:45', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('169', 'kk', '2019-06-09 06:54:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('170', 'kk', '2019-06-09 06:55:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('171', 'kk', '2019-06-09 06:56:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('172', 'kk', '2019-06-09 06:58:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('173', 'kk', '2019-06-09 06:58:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('174', 'kk', '2019-06-09 07:03:49', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('175', 'kk', '2019-06-09 07:09:33', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('176', 'kk', '2019-06-09 07:26:12', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('177', 'kk', '2019-06-09 07:26:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('178', 'kk', '2019-06-09 07:26:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('179', 'kk', '2019-06-09 07:26:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('180', 'kk', '2019-06-09 07:26:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('181', 'kk', '2019-06-09 07:26:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('182', 'kk', '2019-06-09 07:26:43', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('183', 'kk', '2019-06-09 07:26:44', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('184', 'kk', '2019-06-09 07:26:49', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('185', 'kk', '2019-06-09 07:26:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('186', 'kk', '2019-06-09 07:27:12', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('187', 'kk', '2019-06-09 07:34:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('188', 'kk', '2019-06-09 07:34:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('189', 'kk', '2019-06-09 07:35:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('190', 'kk', '2019-06-09 07:35:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('191', 'kk', '2019-06-09 07:35:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('192', 'kk', '2019-06-09 07:35:32', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('193', 'kk', '2019-06-09 07:36:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('194', 'kk', '2019-06-09 07:37:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('195', 'kk', '2019-06-09 07:41:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('196', 'kk', '2019-06-09 07:42:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('197', 'kk', '2019-06-09 07:45:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('198', 'kk', '2019-06-09 07:47:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('199', 'kk', '2019-06-09 07:53:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('200', 'kk', '2019-06-09 08:05:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('201', 'kk', '2019-06-09 08:09:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('202', 'kk', '2019-06-09 08:09:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('203', 'kk', '2019-06-09 08:09:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('204', 'kk', '2019-06-09 08:09:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('205', 'kk', '2019-06-09 08:09:52', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('206', 'kk', '2019-06-09 08:09:54', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('207', 'kk', '2019-06-09 08:10:57', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('208', 'kk', '2019-06-09 08:31:38', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('209', 'kk', '2019-06-09 08:38:11', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('210', 'kk', '2019-06-09 08:49:49', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('211', 'kk', '2019-06-09 08:54:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('212', 'kk', '2019-06-09 09:17:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('213', 'kk', '2019-06-09 14:43:45', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('214', 'kk', '2019-06-09 14:45:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('215', 'kk', '2019-06-09 14:46:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('216', 'kk', '2019-06-09 14:49:10', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('217', 'kk', '2019-06-09 14:49:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('218', 'kk', '2019-06-09 14:53:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('219', 'kk', '2019-06-09 14:54:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('220', 'kk', '2019-06-09 14:54:43', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('221', 'kk', '2019-06-09 14:55:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('222', 'kk', '2019-06-09 15:06:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('223', 'kk', '2019-06-09 15:15:38', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('224', 'kk', '2019-06-09 15:20:49', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('225', 'kk', '2019-06-09 15:30:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('226', 'kk', '2019-06-09 15:31:01', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('227', 'kk', '2019-06-09 15:31:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('228', 'kk', '2019-06-09 15:31:03', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('229', 'kk', '2019-06-09 15:31:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('230', 'kk', '2019-06-09 15:31:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('231', 'kk', '2019-06-09 15:31:05', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('232', 'kk', '2019-06-09 15:31:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('233', 'kk', '2019-06-09 15:31:09', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('234', 'kk', '2019-06-09 15:31:10', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('235', 'kk', '2019-06-09 15:31:11', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('236', 'kk', '2019-06-09 15:31:12', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('237', 'kk', '2019-06-09 15:31:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('238', 'kk', '2019-06-09 15:31:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('239', 'kk', '2019-06-09 15:31:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('240', 'kk', '2019-06-09 15:31:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('241', 'kk', '2019-06-09 15:31:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('242', 'kk', '2019-06-09 15:31:23', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('243', 'kk', '2019-06-09 15:31:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('244', 'kk', '2019-06-09 15:31:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('245', 'kk', '2019-06-09 15:31:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('246', 'kk', '2019-06-09 15:32:31', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('247', 'kk', '2019-06-09 15:32:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('248', 'kk', '2019-06-09 15:34:17', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('249', 'kk', '2019-06-09 15:38:03', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('250', 'kk', '2019-06-09 15:38:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('251', 'kk', '2019-06-09 15:39:17', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('252', 'kk', '2019-06-09 15:43:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('253', 'kk', '2019-06-09 15:43:11', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('254', 'kk', '2019-06-09 23:54:12', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('255', 'kk', '2019-06-09 23:55:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('256', 'kk', '2019-06-09 23:55:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('257', 'kk', '2019-06-10 01:14:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('258', 'kk', '2019-06-10 08:09:08', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('259', 'kk', '2019-06-10 08:14:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('260', 'kk', '2019-06-10 08:17:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('261', 'kk', '2019-06-10 08:23:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('262', 'kk', '2019-06-10 08:31:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('263', 'kk', '2019-06-10 08:36:09', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('264', 'kk', '2019-06-10 08:38:57', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('265', 'kk', '2019-06-10 09:03:38', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('266', 'kk', '2019-06-10 09:53:51', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('267', 'kk', '2019-06-10 10:02:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('268', 'kk', '2019-06-10 10:12:40', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('269', 'kk', '2019-06-10 10:15:56', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('270', 'kk', '2019-06-14 08:23:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('271', 'kk', '2019-06-14 08:23:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('272', 'kk', '2019-06-15 04:51:02', '', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('273', 'kk', '2019-06-15 04:51:18', '', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('274', 'kk', '2019-06-15 04:52:11', '', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('275', 'kk', '2019-06-15 04:55:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('276', 'kk', '2019-06-15 14:27:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('277', 'kk', '2019-06-15 14:27:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('278', 'kk', '2019-06-17 09:10:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('279', 'kk', '2019-06-17 09:19:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('280', 'kk', '2019-06-18 14:51:58', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('281', 'kk', '2019-06-18 14:52:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('282', 'kk', '2019-06-18 14:52:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('283', 'kk', '2019-06-18 14:52:52', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('284', 'kk', '2019-06-19 00:10:57', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('285', 'kk', '2019-06-19 08:04:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('286', 'kk', '2019-06-19 13:31:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('287', 'kk', '2019-06-19 13:31:43', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('288', 'kk', '2019-06-19 14:00:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('289', 'kk', '2019-06-19 15:09:52', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('290', 'kk', '2019-06-19 15:32:01', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('291', 'kk', '2019-06-20 00:04:55', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('292', 'kk', '2019-06-20 00:26:55', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('293', 'kk', '2019-06-20 01:30:05', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('294', 'kk', '2019-06-20 04:10:32', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('295', 'kk', '2019-06-20 04:47:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('296', 'kk', '2019-06-20 04:57:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('297', 'kk', '2019-06-20 05:50:16', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('298', 'kk', '2019-06-20 08:35:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('299', 'kk', '2019-06-20 08:35:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('300', 'kk', '2019-06-20 14:06:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('301', 'kk', '2019-06-20 14:06:52', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('302', 'kk', '2019-06-20 14:07:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('303', 'kk', '2019-06-20 14:07:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('304', 'kk', '2019-06-20 14:46:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('305', 'kk', '2019-06-20 15:57:44', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('306', 'kk', '2019-06-21 00:15:46', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('307', 'kk', '2019-06-21 00:15:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('308', 'kk', '2019-06-21 01:39:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('309', 'kk', '2019-06-21 03:38:58', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('310', 'kk', '2019-06-21 03:39:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('311', 'kk', '2019-06-21 04:52:23', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('312', 'kk', '2019-06-21 07:47:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('313', 'kk', '2019-06-21 09:08:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('314', 'kk', '2019-06-21 10:21:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('315', 'kk', '2019-06-22 15:01:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('316', 'kk', '2019-06-22 15:01:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('317', 'kk', '2019-06-22 15:10:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('318', 'kk', '2019-06-22 16:16:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('319', 'kk', '2019-06-22 16:16:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('320', 'kk', '2019-06-23 03:40:56', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('321', 'kk', '2019-06-23 09:07:16', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('322', 'kk', '2019-06-23 10:07:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('323', 'kk', '2019-06-23 11:30:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('324', 'kk', '2019-06-23 14:55:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('325', 'kk', '2019-06-23 14:55:51', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('326', 'kk', '2019-06-23 15:08:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('327', 'kk', '2019-06-23 15:08:48', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('328', 'kk', '2019-06-23 15:22:19', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('329', 'kk', '2019-06-23 23:40:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('330', 'kk', '2019-06-24 15:03:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('331', 'kk', '2019-06-24 15:03:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('332', 'kk', '2019-06-24 15:04:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('333', 'kk', '2019-06-24 16:04:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('334', 'kk', '2019-06-24 23:54:52', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('335', 'kk', '2019-06-25 03:15:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('336', 'kk', '2019-06-25 06:51:10', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('337', 'kk', '2019-06-25 07:54:01', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('338', 'kk', '2019-06-25 07:58:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('339', 'kk', '2019-06-25 09:22:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('340', 'kk', '2019-06-25 10:32:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('341', 'kk', '2019-06-26 06:45:43', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('342', 'kk', '2019-06-26 06:47:32', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('343', 'kk', '2019-06-26 08:52:58', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('344', 'kk', '2019-06-26 10:22:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('345', 'kk', '2019-06-26 13:00:23', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('346', 'kk', '2019-06-26 13:00:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('347', 'kk', '2019-06-26 13:08:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('348', 'kk', '2019-06-26 22:57:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('349', 'kk', '2019-06-27 00:13:16', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('350', 'kk', '2019-06-27 04:34:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('351', 'kk', '2019-06-27 09:27:40', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('352', 'kk', '2019-06-27 10:34:43', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('353', 'kk', '2019-06-27 10:49:19', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('354', 'kk', '2019-06-27 11:02:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('355', 'kk', '2019-06-28 07:22:48', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('356', 'kk', '2019-06-28 07:23:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('357', 'kk', '2019-06-28 08:40:32', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('358', 'kk', '2019-06-28 09:47:43', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('359', 'kk', '2019-06-28 10:28:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('360', 'kk', '2019-06-28 10:33:43', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('361', 'kk', '2019-07-03 10:08:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('362', 'kk', '2019-07-03 10:09:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('363', 'kk', '2019-07-04 09:46:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('364', 'kk', '2019-07-04 10:51:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('365', 'kk', '2019-07-04 10:51:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('366', 'kk', '2019-07-05 01:50:46', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('367', 'kk', '2019-07-05 01:50:54', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('368', 'kk', '2019-07-05 02:03:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('369', 'kk', '2019-07-05 02:04:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('370', 'kk', '2019-07-05 02:04:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('371', 'kk', '2019-07-05 02:04:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('372', 'kk', '2019-07-05 02:06:23', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('373', 'kk', '2019-07-05 02:06:32', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('374', 'kk', '2019-07-05 02:08:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('375', 'kk', '2019-07-05 02:11:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('376', 'kk', '2019-07-05 02:12:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('377', 'kk', '2019-07-05 02:17:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('378', 'kk', '2019-07-05 02:21:54', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('379', 'kk', '2019-07-05 02:23:55', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('380', 'kk', '2019-07-05 02:32:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('381', 'kk', '2019-07-05 02:33:10', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('382', 'kk', '2019-07-05 03:00:44', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('383', 'kk', '2019-07-05 03:09:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('384', 'kk', '2019-07-05 03:13:11', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('385', 'kk', '2019-07-05 03:13:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('386', 'kk', '2019-07-05 03:13:45', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('387', 'kk', '2019-07-05 03:14:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('388', 'kk', '2019-07-05 03:14:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('389', 'kk', '2019-07-05 03:16:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('390', 'kk', '2019-07-05 03:55:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('391', 'kk', '2019-07-05 04:31:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('392', 'kk', '2019-07-05 09:50:55', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('393', 'kk', '2019-07-07 10:28:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('394', 'kk', '2019-07-07 10:28:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('395', 'kk', '2019-07-07 22:30:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('396', 'kk', '2019-07-08 02:46:52', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('397', 'kk', '2019-07-08 02:48:01', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('398', 'kk', '2019-07-08 02:48:09', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('399', 'kk', '2019-07-08 02:48:31', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('400', 'kk', '2019-07-08 02:48:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('401', 'kk', '2019-07-08 03:52:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('402', 'kk', '2019-07-08 07:22:31', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('403', 'kk', '2019-07-08 08:31:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('404', 'kk', '2019-07-08 08:32:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('405', 'kk', '2019-07-08 08:32:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('406', 'kk', '2019-07-08 08:32:46', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('407', 'kk', '2019-07-08 08:35:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('408', 'kk', '2019-07-08 09:55:12', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('409', 'kk', '2019-07-09 00:12:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('410', 'kk', '2019-07-09 01:12:57', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('411', 'kk', '2019-07-09 06:55:58', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('412', 'kk', '2019-07-09 06:58:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('413', 'kk', '2019-07-09 09:16:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('414', 'kk', '2019-07-09 14:08:11', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('415', 'kk', '2019-07-09 15:10:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('416', 'kk', '2019-07-09 15:21:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('417', 'kk', '2019-07-09 22:37:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('418', 'kk', '2019-07-10 00:01:38', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('419', 'kk', '2019-07-10 03:41:03', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('420', 'kk', '2019-07-10 06:15:41', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('421', 'kk', '2019-07-10 06:46:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('422', 'kk', '2019-07-10 06:48:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('423', 'kk', '2019-07-10 08:10:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('424', 'kk', '2019-07-10 09:28:19', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('425', 'kk', '2019-07-10 09:53:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('426', 'kk', '2019-07-10 09:54:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('427', 'kk', '2019-07-10 09:55:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('428', 'kk', '2019-07-11 06:22:17', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('429', 'kk', '2019-07-11 07:23:54', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('430', 'kk', '2019-07-11 08:15:57', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('431', 'kk', '2019-07-11 08:21:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('432', 'kk', '2019-07-11 08:40:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('433', 'kk', '2019-07-11 10:21:40', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('434', 'kk', '2019-07-11 14:50:05', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('435', 'kk', '2019-07-11 14:50:17', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('436', 'kk', '2019-07-11 14:50:19', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('437', 'kk', '2019-07-11 14:50:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('438', 'kk', '2019-07-11 14:50:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('439', 'kk', '2019-07-11 14:50:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('440', 'kk', '2019-07-11 14:50:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('441', 'kk', '2019-07-11 14:50:23', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('442', 'kk', '2019-07-11 14:50:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('443', 'kk', '2019-07-11 14:50:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('444', 'kk', '2019-07-11 14:50:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('445', 'kk', '2019-07-11 14:50:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('446', 'kk', '2019-07-11 14:50:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('447', 'kk', '2019-07-11 14:50:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('448', 'kk', '2019-07-11 14:55:33', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('449', 'kk', '2019-07-11 14:55:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('450', 'kk', '2019-07-11 14:55:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('451', 'kk', '2019-07-11 14:57:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('452', 'kk', '2019-07-11 23:45:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('453', 'kk', '2019-07-11 23:45:55', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('454', 'kk', '2019-07-12 02:26:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('455', 'kk', '2019-07-12 03:28:46', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('456', 'kk', '2019-07-12 05:03:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('457', 'kk', '2019-07-12 07:57:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('458', 'kk', '2019-07-12 08:58:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('459', 'kk', '2019-07-12 10:11:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('460', 'kk', '2019-07-12 13:46:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('461', 'kk', '2019-07-13 02:26:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('462', 'kk', '2019-07-13 06:47:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('463', 'kk', '2019-07-13 06:47:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('464', 'kk', '2019-07-13 10:32:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('465', 'kk', '2019-07-13 13:07:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('466', 'kk', '2019-07-13 14:17:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('467', 'kk', '2019-07-13 14:19:03', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('468', 'kk', '2019-07-13 14:20:15', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('469', 'kk', '2019-07-13 14:23:14', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('470', 'kk', '2019-07-13 14:23:20', '内网IP|0|0|内网IP|内网IP', '192.168.31.116');
INSERT INTO `sys_web_login_log` VALUES ('471', 'kk', '2019-07-14 01:23:17', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('472', 'kk', '2019-07-14 02:52:22', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('473', 'kk', '2019-07-14 04:36:35', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('474', 'kk', '2019-07-14 07:16:09', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('475', 'kk', '2019-07-14 07:22:20', '内网IP|0|0|内网IP|内网IP', '192.168.31.116');
INSERT INTO `sys_web_login_log` VALUES ('476', 'kk', '2019-07-14 10:59:38', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('477', 'kk', '2019-07-14 12:20:39', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('478', 'kk', '2019-07-14 15:28:37', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('479', 'kk', '2019-07-14 16:11:15', '内网IP|0|0|内网IP|内网IP', '192.168.31.205');
INSERT INTO `sys_web_login_log` VALUES ('480', 'kk', '2019-07-17 10:50:41', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('481', 'kk', '2019-07-17 11:53:16', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('482', 'kk', '2019-07-18 11:45:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('483', 'kk', '2019-07-24 11:47:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('484', 'kk', '2019-07-24 11:56:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('485', 'kk', '2019-07-24 12:57:08', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('486', 'kk', '2019-07-24 13:57:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('487', 'kk', '2019-07-29 08:02:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('488', 'kk', '2019-07-29 09:06:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('489', 'kk', '2019-07-29 09:39:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('490', 'kk', '2019-07-29 09:46:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('491', 'kk', '2019-07-29 09:59:48', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('492', 'kk', '2019-07-29 10:00:10', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('493', 'kk', '2019-07-29 10:01:08', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('494', 'kk', '2019-07-29 10:04:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('495', 'kk', '2019-07-29 12:54:41', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('496', 'kk', '2019-07-29 13:56:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('497', 'kk', '2019-07-29 14:53:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('498', 'kk', '2019-07-29 23:57:48', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('499', 'kk', '2019-07-29 23:57:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('500', 'kk', '2019-07-30 06:58:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('501', 'kk', '2019-07-30 08:35:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('502', 'kk', '2019-07-30 10:30:16', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('503', 'kk', '2019-07-31 01:33:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('504', 'kk', '2019-07-31 02:07:05', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('505', 'kk', '2019-07-31 03:39:17', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('506', 'kk', '2019-07-31 04:08:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('507', 'kk', '2019-07-31 05:50:31', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('508', 'kk', '2019-07-31 07:06:45', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('509', 'kk', '2019-07-31 08:09:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('510', 'kk', '2019-07-31 09:22:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('511', 'kk', '2019-07-31 09:39:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('512', 'kk', '2019-07-31 11:09:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('513', 'kk', '2019-07-31 12:08:10', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('514', 'kk', '2019-07-31 12:08:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('515', 'kk', '2019-08-01 01:23:58', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('516', 'kk', '2019-08-02 03:56:43', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('517', 'zhk', '2019-08-02 03:57:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('518', 'zhk', '2019-08-02 03:57:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('519', 'kk', '2019-08-02 05:16:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('520', 'kk', '2019-08-02 06:42:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('521', 'zhk', '2019-08-02 07:46:23', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('522', 'kk', '2019-08-02 08:47:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('523', 'kk', '2019-08-02 09:20:55', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('524', 'zhk', '2019-08-02 09:25:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('525', 'zhk', '2019-08-02 10:45:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `sys_web_login_log` VALUES ('526', 'zhk', '2019-08-02 11:27:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');

-- ----------------------------
-- Function structure for findDeptChildren
-- ----------------------------
DROP FUNCTION IF EXISTS `findDeptChildren`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `findDeptChildren`(`rootid` integer) RETURNS varchar(4000) CHARSET utf8
BEGIN
	  DECLARE sTemp VARCHAR(4000);
    DECLARE sTempChd VARCHAR(4000);
    SET sTemp = '$';
    SET sTempChd = CAST(rootId as CHAR);
    WHILE sTempChd is not null DO
    SET sTemp = CONCAT(sTemp,',',sTempChd);
    SELECT GROUP_CONCAT(dept_id) INTO sTempChd FROM sys_dept
    WHERE FIND_IN_SET(parentid,sTempChd)>0;
END WHILE;

	RETURN sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for findMenuChildren
-- ----------------------------
DROP FUNCTION IF EXISTS `findMenuChildren`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `findMenuChildren`(`rootId` int) RETURNS varchar(4000) CHARSET utf8
BEGIN
	  DECLARE sTemp VARCHAR(4000);
    DECLARE sTempChd VARCHAR(4000);
    SET sTemp = '$';
    SET sTempChd = CAST(rootId as CHAR);
    WHILE sTempChd is not null DO
    SET sTemp = CONCAT(sTemp,',',sTempChd);
    SELECT GROUP_CONCAT(menu_id) INTO sTempChd FROM sys_menu
    WHERE FIND_IN_SET(parentid,sTempChd)>0;
END WHILE;

	RETURN sTemp;
END
;;
DELIMITER ;
