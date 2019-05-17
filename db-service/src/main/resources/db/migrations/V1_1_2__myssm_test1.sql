CREATE TABLE test_table(
	id BIGINT(20)  AUTO_INCREMENT COMMENT '设置主键在后面',
	name VARCHAR(60) UNIQUE NOT NULL COMMENT '不为空约束',
	age INT DEFAULT NULL COMMENT '默认为空',
	price DECIMAL(18,2) DEFAULT NULL COMMENT '价格保留两位有效数字',

	created_dt datetime DEFAULT NULL,
  	created_by varchar(32) DEFAULT NULL,
  	updated_dt datetime DEFAULT NULL,
  	updated_by varchar(32) DEFAULT NULL,
  	version bigint(9) NOT NULL DEFAULT 1,

	PRIMARY KEY (id)
)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='测试表';