-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `code` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '权限码',
  `level` varchar(200) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '权限层级',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级权限id',
  `name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '权限名称',
  `icon` varchar(30) COLLATE utf8mb4_bin DEFAULT '' COMMENT '目录图标',
  `url` varchar(100) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '请求url，可以填正则表达式',
  `type` int(11) NOT NULL DEFAULT '4' COMMENT '类型，1：目录，2：菜单，3：按钮，4：其他',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：冻结',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '权限在当前层级的顺序，由小到大',
  `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级部门id',
  `level` varchar(200) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '部门层级',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '部门在当前层级下的顺序，由小到大',
  `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operate_ip` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '最后一次更新操作者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限操作日志表',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '权限更新类型，1：部门，2：用户，3：权限，4：角色，5：角色用户关系，6：角色权限关系',
  `target_id` int(11) NOT NULL COMMENT '基于type后指定的对象id，比如用户、权限、角色等表的主键',
  `old_value` text COLLATE utf8mb4_bin COMMENT '旧值',
  `new_value` text COLLATE utf8mb4_bin COMMENT '新值',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '当前是否复原过，0：没有，1：复原过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '角色名称',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '角色类型，1：管理员角色，2：其他',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：可用，0：冻结',
  `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限关联表id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `acl_id` int(11) NOT NULL COMMENT '权限id',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '最后一次更新者ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色关联表id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `real_name` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '真实姓名',
  `username` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `job_num` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '工号',
  `telephone` varchar(13) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '手机号',
  `mail` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(64) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '加密后的密码',
  `dept_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户所在部门的id',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，2：冻结',
  `usable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，0：删除，1：正常',
  `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '部门列表', '0', '0', '0', '', 'system', '2018-02-27 13:41:50', '192.168.1.1');
INSERT INTO `sys_dept` VALUES ('2', '总经理室', '1', '0.1', '1', '', 'admin', '2018-02-27 13:47:20', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('3', '技术部', '1', '0.1', '2', '技术部备注', 'admin', '2018-02-27 13:47:46', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('4', '前端开发', '3', '0.1.3', '1', '前端开发备注', 'admin', '2018-02-27 13:48:05', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('5', '后端开发', '3', '0.1.3', '2', '后端开发备注', 'admin', '2018-02-27 13:48:26', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('6', '市场部', '1', '0.1', '3', '市场部备注', 'admin', '2018-02-27 13:48:59', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('7', '招生部', '6', '0.1.6', '1', '招生部备注', 'admin', '2018-02-27 13:49:26', '127.0.0.1');

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '', 'admin', '', '15675504181', '694436921@qq.com', '$2a$10$J3MRsSnmdp/0bW7FE2MMGeoMrI4XdTYJdSWebusywDEsuGs0dshdS', '1', '1', '1', '系统管理员账号', 'system', '2018-02-27 13:41:50', '192.168.1.1');
INSERT INTO `sys_user` VALUES ('2', '', 'zongjingli', '', '15689562563', 'zongjingli@qq.com', '$2a$10$EAsCuf/FRUf.kx4rQ381FuP6Rk4Z6OjTmVJW.k0ypboueyAv2uhP2', '2', '1', '1', '这是总经理账', 'admin', '2018-03-01 09:46:13', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('3', '', 'test', '', '18255316459', '694436929@qq.com', '$2a$10$J3MRsSnmdp/0bW7FE2MMGeoMrI4XdTYJdSWebusywDEsuGs0dshdS', '5', '1', '1', '系统测试用户', 'admin', '2018-03-02 15:14:12', '127.0.0.1');


-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '1', '1', '这是系统管理员', 'admin', '2018-02-27 22:22:49', '127.0.0.1');
INSERT INTO `sys_role` VALUES ('2', '订单管理员', '1', '1', '订单管理员备注', 'admin', '2018-02-27 22:52:26', '127.0.0.1');
INSERT INTO `sys_role` VALUES ('3', '测试角色', '1', '1', '用于测试使用的角色', 'admin', '2018-03-02 15:14:48', '127.0.0.1');

-- ----------------------------
-- Records of sys_acl
-- ----------------------------
INSERT INTO `sys_acl` VALUES ('1', '', '0', '0', '菜单目录', '', '', '1', '1', '0', '菜单目录备注', 'system', '2018-02-27 13:56:44', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('2', '', '0.1', '1', '首页', 'dashboard', '/dashboard', '2', '1', '1', '首页备注', 'admin', '2018-02-27 14:13:27', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('3', '', '0.1', '1', '权限管理', 'permission', '', '1', '1', '2', '权限管理备注', 'admin', '2018-02-27 17:19:57', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('4', '', '0.1.3', '3', '用户管理', '', '/acl/user', '2', '1', '1', '用户管理备注', 'admin', '2018-02-27 17:34:34', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('5', '', '0.1.3', '3', '权限管理', '', '/acl/acl', '2', '1', '2', '权限管理备注', 'admin', '2018-02-27 17:35:10', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('6', '', '0.1.3', '3', '角色管理', '', '/acl/role', '2', '1', '3', '角色管理备注', 'admin', '2018-02-27 17:35:42', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('7', '', '0.1.3', '3', '权限更新记录', '', '/acl/log', '2', '1', '4', '权限更新记录备注', 'admin', '2018-03-01 21:07:13', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('8', '20180301005757_63', '0.1', '1', '系统管理', 'system', '', '1', '1', '3', '系统管理备注', 'admin', '2018-03-01 00:57:57', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('9', '20180301005907_23', '0.1.8', '8', 'SQL监控', '', '/sys/sql', '2', '1', '1', 'druid监控', 'admin', '2018-03-01 01:00:20', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('10', '20180301010011_53', '0.1.8', '8', 'Swagger', '', '/sys/swagger', '2', '1', '2', 'swagger-ui restful-api 文档', 'admin', '2018-03-01 01:00:11', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('11', '20180301212539_11', '0.1.3.4', '4', '部门查询', '', '/sys/dept/listDept', '3', '1', '1', '', 'admin', '2018-03-01 21:25:39', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('12', '20180301212630_47', '0.1.3.4', '4', '部门添加', '', '/sys/dept/addDept', '3', '1', '2', '', 'admin', '2018-03-01 21:26:30', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('13', '20180301212716_80', '0.1.3.4', '4', '部门编辑', '', '/sys/dept/update', '3', '1', '3', '', 'admin', '2018-03-01 21:27:16', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('14', '20180301212754_70', '0.1.3.4', '4', '部门删除', '', '/sys/dept/delete', '3', '1', '4', '', 'admin', '2018-03-01 21:27:55', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('15', '20180301212850_40', '0.1.3.4', '4', '用户查询', '', '/sys/user/list', '3', '1', '5', '', 'admin', '2018-03-01 21:28:50', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('16', '20180301212921_72', '0.1.3.4', '4', '用户添加', '', '/sys/user/addUser', '3', '1', '6', '', 'admin', '2018-03-01 21:29:21', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('17', '20180301212957_28', '0.1.3.4', '4', '用户编辑', '', '/sys/user/update', '3', '1', '7', '', 'admin', '2018-03-01 21:29:57', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('18', '20180301214239_81', '0.1.3.4', '4', '用户删除', '', '/sys/user/delete', '3', '1', '8', '', 'admin', '2018-03-01 21:42:39', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('19', '20180301214740_99', '0.1.3.5', '5', '权限查询', '', '/sys/acl/tree', '3', '1', '1', '', 'admin', '2018-03-01 21:47:40', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('20', '20180301214819_19', '0.1.3.5', '5', '权限添加', '', '/sys/acl/save', '3', '1', '2', '', 'admin', '2018-03-01 21:48:20', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('21', '20180301214850_62', '0.1.3.5', '5', '权限编辑', '', '/sys/acl/update', '3', '1', '3', '', 'admin', '2018-03-01 21:48:51', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('22', '20180301214917_94', '0.1.3.5', '5', '权限删除', '', '/sys/acl/delete', '3', '1', '4', '', 'admin', '2018-03-01 21:49:18', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('23', '20180301215106_81', '0.1.3.6', '6', '角色查询', '', '/sys/role/list', '3', '1', '1', '', 'admin', '2018-03-01 21:51:06', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('24', '20180301215140_4', '0.1.3.6', '6', '角色添加', '', '/sys/role/save', '3', '1', '2', '', 'admin', '2018-03-01 21:51:40', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('25', '20180301215205_34', '0.1.3.6', '6', '角色编辑', '', '/sys/role/update', '3', '1', '3', '', 'admin', '2018-03-01 21:52:05', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('26', '20180301215238_6', '0.1.3.6', '6', '角色删除', '', '/sys/role/delete', '3', '1', '4', '', 'admin', '2018-03-01 21:52:39', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('27', '20180301215402_59', '0.1.3.6', '6', '角色与权限查询', '', '/sys/role/roleTree', '3', '1', '5', '', 'admin', '2018-03-01 21:54:03', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('28', '20180301215523_5', '0.1.3.6', '6', '角色与权限关联', '', '/sys/role/changeAcls', '3', '1', '6', '', 'admin', '2018-03-01 21:55:23', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('29', '20180301215605_39', '0.1.3.6', '6', '角色与用户查询', '', '/sys/role/users', '3', '1', '7', '', 'admin', '2018-03-01 21:56:05', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('30', '20180301215647_41', '0.1.3.6', '6', '角色与用户关联', '', '/sys/role/changeUsers', '3', '1', '8', '', 'admin', '2018-03-01 21:56:47', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('31', '20180301215745_52', '0.1.3.7', '7', '权限更新记录查询', '', '/sys/log/list', '3', '1', '1', '', 'admin', '2018-03-01 21:57:45', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('32', '20180301215815_9', '0.1.3.7', '7', '权限更新记录还原', '', '/sys/log/recover', '3', '1', '2', '', 'admin', '2018-03-01 21:58:15', '127.0.0.1');

-- ----------------------------
-- Records of sys_acl
-- ----------------------------
INSERT INTO `sys_acl` VALUES ('1', '', '0', '0', '菜单目录', '', '', '1', '1', '0', '菜单目录备注', 'system', '2018-02-27 13:56:44', '127.0.0.1');

