CREATE TABLE IF NOT EXISTS `sys_m_org` (
  `org_sid` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '机构SID',
  `org_name` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `org_code` varchar(100) DEFAULT NULL COMMENT '机构简称',
  `org_type` varchar(32) DEFAULT NULL COMMENT '机构类型：企业company、部门department、项目project',
  `owner` bigint(15) DEFAULT NULL,
  `tree_path` varchar(500) DEFAULT NULL COMMENT '树路径',
  `parent_id` bigint(15) DEFAULT NULL COMMENT '父机构ID',
  `org_icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `province` bigint(20) DEFAULT NULL COMMENT '所在省份',
  `city` bigint(20) DEFAULT NULL COMMENT '所在城市',
  `area` bigint(20) DEFAULT NULL COMMENT '所在区域',
  `address` varchar(255) DEFAULT NULL COMMENT '机构地址',
  `contact_name` varchar(50) DEFAULT NULL COMMENT '联系人姓名',
  `contact_position` varchar(64) DEFAULT NULL COMMENT '联系人职位',
  `contact_phone` varchar(50) DEFAULT NULL COMMENT '联系人电话',
  `quota_ctrl` char(1) DEFAULT NULL,
  `quota_mode` varchar(50) DEFAULT NULL COMMENT '配额控制方式',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `tenant_ids` varchar(255) DEFAULT NULL COMMENT '租户ID ',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_dt` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updated_dt` datetime DEFAULT NULL COMMENT '更新时间',
  `version` bigint(9) NOT NULL DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`org_sid`) USING BTREE,
  KEY `idx_parent_id` (`parent_id`) USING BTREE,
  KEY `idx_tree_path` (`tree_path`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=227 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='组织架构表';


CREATE TABLE IF NOT EXISTS `sys_m_user_org` (
  `org_sid` bigint(20) NOT NULL COMMENT '组织SID',
  `user_sid` bigint(20) NOT NULL COMMENT '用户SID',
  PRIMARY KEY (`org_sid`,`user_sid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户组织表';