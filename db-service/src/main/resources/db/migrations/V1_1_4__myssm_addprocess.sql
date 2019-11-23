CREATE TABLE `process` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流程ID',
  `process_code` varchar(64) DEFAULT NULL COMMENT '流程编号',
  `process_name` varchar(255) DEFAULT NULL COMMENT '流程名称',
  `business_code` varchar(64) DEFAULT NULL COMMENT '业务编号',
  `business_name` varchar(255) DEFAULT NULL COMMENT '流程名称',
  `org_sid` bigint(20) DEFAULT NULL COMMENT '企业ID',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_dt` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '最后变更人',
  `updated_dt` datetime DEFAULT NULL COMMENT '最后变更时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '流程状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `process_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流程版本ID',
  `process_id` bigint(20) DEFAULT NULL COMMENT '流程ID',
  `version_name` varchar(255) DEFAULT NULL COMMENT '版本名称',
  `process_identify` varchar(255) DEFAULT NULL COMMENT '流程标识',
  `deployment_id` varchar(64) DEFAULT NULL COMMENT '流程发布ID',
  `version` int(11) DEFAULT NULL COMMENT '流程版本号',
  `is_default` tinyint(1) DEFAULT NULL COMMENT '是否为流程默认版本',
  `status` tinyint(4) DEFAULT NULL COMMENT '版本状态',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_dt` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `updated_dt` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `process_node` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流程节点ID',
  `node_name` varchar(255) DEFAULT NULL COMMENT '节点名称',
  `process_id` bigint(20) DEFAULT NULL COMMENT '流程ID',
  `version_id` bigint(20) DEFAULT NULL COMMENT '流程版本ID',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `node_type` tinyint(4) DEFAULT NULL COMMENT '节点类型',
  `config_data` text COMMENT '节点配置数据',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_dt` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `updated_dt` datetime DEFAULT NULL COMMENT '最后更新时间',
  `sort_num` float DEFAULT '0' COMMENT '排序序号',
  `status` tinyint(4) DEFAULT NULL COMMENT '节点状态',
  `process_identify` varchar(64) DEFAULT NULL COMMENT '流程标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=591 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `sf_service_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `service_form` varchar(32) DEFAULT NULL COMMENT '服务类别：计算资源、脚本部署、容器集群部署',
  `service_type` varchar(32) DEFAULT NULL COMMENT '服务类型：group（多组合）  unit（单一）',
  `service_class` varchar(32) DEFAULT NULL COMMENT '1.public  2.private',
  `service_owner_id` bigint(20) DEFAULT NULL,
  `service_owner_name` varchar(32) DEFAULT NULL,
  `service_name` varchar(64) DEFAULT NULL,
  `service_desc` text,
  `status` varchar(10) DEFAULT NULL,
  `open_type` varchar(32) DEFAULT NULL,
  `service_icon_path` varchar(128) DEFAULT NULL,
  `service_config` mediumtext COMMENT '服务配置',
  `service_workflow_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(32) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `updated_by` varchar(32) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `version` bigint(9) NOT NULL DEFAULT '1',
  `org_sid` bigint(15) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL COMMENT '服务的额外费用',
  `service_details` text COMMENT '服务描述详情',
  `res_flag` char(1) DEFAULT NULL,
  `service_component` varchar(32) DEFAULT NULL COMMENT 'code表PARENT_CODE_VALUE字段为serviceComponent对应value值',
  `resource_visible` char(1) DEFAULT NULL COMMENT '资源可见性: 0 发布者可见, 1 申请者可见 2 均可见 ',
  `instance_visible` varchar(10) DEFAULT NULL COMMENT '服务实例可见性: true 发布者可见, false 发布者不可见 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=315 DEFAULT CHARSET=utf8mb4;



CREATE TABLE `service_order` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '申请单ID',
  `order_sn` varchar(64) NOT NULL,
  `name` varchar(256) DEFAULT NULL COMMENT '申请单名称',
  `type` varchar(32) DEFAULT NULL COMMENT '申请单类型',
  `org_sid` bigint(15) DEFAULT NULL,
  `owner_id` varchar(32) NOT NULL COMMENT '所有者ID',
  `extra_attr` longtext,
  `status` varchar(16) DEFAULT NULL COMMENT '状态',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_dt` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updated_dt` datetime DEFAULT NULL COMMENT '更新时间',
  `version` bigint(9) NOT NULL DEFAULT '1' COMMENT '版本号',
  `total_amt` decimal(8,2) DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `notice_dt` datetime DEFAULT NULL,
  `order_id` varchar(64) DEFAULT NULL,
  `service_id` varchar(32) DEFAULT NULL,
  `process_flag` varchar(2) DEFAULT NULL COMMENT '01:项目用户申请；02:项目管理员申请; 03: 企业管理员申请',
  `step_name` varchar(64) DEFAULT NULL COMMENT '流程步骤名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1357 DEFAULT CHARSET=utf8mb4 COMMENT='申请单';

CREATE TABLE `service_order_record` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '记录SID',
  `order_id` bigint(15) DEFAULT NULL COMMENT '申请单ID',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '申请单SN',
  `audit_time` datetime NOT NULL COMMENT '处理时间',
  `handler` varchar(32) NOT NULL COMMENT '流程类型',
  `step` varchar(128) NOT NULL COMMENT '处理环节',
  `info` text NOT NULL COMMENT '处理信息',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_dt` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updated_dt` datetime DEFAULT NULL COMMENT '更新时间',
  `version` bigint(9) NOT NULL DEFAULT '1' COMMENT '版本号',
  `notice_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3024 DEFAULT CHARSET=utf8mb4 COMMENT='申请单处理记录表';

CREATE TABLE `sys_exam` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(15) DEFAULT NULL COMMENT 'username',
  `region_cd` varchar(64) DEFAULT NULL COMMENT 'region_cd',
  PRIMARY KEY (`id`) USING BTREE
)  ;


