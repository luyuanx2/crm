DROP TABLE IF EXISTS `sys_visitor`;
CREATE TABLE `sys_visitor` (
  `ip` varchar(20) NOT NULL COMMENT '访客ip地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;