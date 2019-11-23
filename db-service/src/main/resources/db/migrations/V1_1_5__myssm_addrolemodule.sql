CREATE TABLE IF NOT EXISTS  `sys_m_module`  (
  `module_sid` varchar(32)  NOT NULL COMMENT '模块SID',
  `module_name` varchar(128)  NOT NULL COMMENT '模块名称',
  `module_url` varchar(256) DEFAULT NULL COMMENT '模块URL',
  `module_icon_url` varchar(256) DEFAULT NULL COMMENT '模块图标URL',
  `parent_sid` varchar(32)  DEFAULT NULL COMMENT '父模块SID',
  `permission` varchar(32) DEFAULT NULL COMMENT '功能权限',
  `module_type` int(1) NOT NULL COMMENT '模块类型(0:目录; 1:菜单;2:按钮)',
  `display_flag` int(1) NOT NULL DEFAULT 1 COMMENT '是否显示 0:否 1:是',
  `sort_rank` int(4) NULL DEFAULT NULL COMMENT '显示顺序',
  `module_category` varchar(16)  NOT NULL COMMENT '前后台标识',
  `description` varchar(255) DEFAULT '' COMMENT '模块功能描述',
  PRIMARY KEY (`module_sid`) USING BTREE
)   COMMENT = '菜单表';

CREATE TABLE IF NOT EXISTS  `sys_m_role_module`  (
  `role_sid` bigint(15) NOT NULL COMMENT '角色SID',
  `module_sid` varchar(32)  NOT NULL COMMENT '模块SID',
  PRIMARY KEY (`role_sid`, `module_sid`) USING BTREE
)  COMMENT = '角色权限表';

