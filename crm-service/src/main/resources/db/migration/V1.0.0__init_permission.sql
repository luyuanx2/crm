-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `code` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '权限码',
  `level` varchar(200) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '权限层级',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级权限模块id',
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
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，0：删除，1：正常',
  `remark` varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

insert into sys_dept (name, level,operator,operate_ip) values('部门列表','0','system','192.168.1.1');
insert into sys_dept (name, level,operator,operate_ip) values('技术部','0.1','system','192.168.1.1');
insert into sys_user (username, telephone, mail, password, dept_id, status,operator,operate_ip) values('admin','15675504181', '694436921@qq.com', '$2a$10$J3MRsSnmdp/0bW7FE2MMGeoMrI4XdTYJdSWebusywDEsuGs0dshdS',1, 1,'system','192.168.1.1');

