CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '文章名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '1-原创 2-外部分享链接',
  `content` text COMMENT '文章内容',
  `creator_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modifier_id` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `component` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '组件名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '组件类型：1-vue 2-其它（默认1）',
  `content` text COMMENT '组件内容',
  `creator_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modifier_id` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `resource_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) DEFAULT NULL COMMENT '资源id',
  `view_count` bigint(20) DEFAULT NULL COMMENT '查看数',
  `like_count` bigint(20) DEFAULT NULL COMMENT '喜欢数',
  `collect_count` bigint(20) DEFAULT NULL COMMENT '收藏数',
  `share_count` bigint(20) DEFAULT NULL COMMENT '分享数',
  `use_count` bigint(20) DEFAULT NULL COMMENT '使用/采纳数',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `resource_operated_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT NULL COMMENT '资源被操作类型：1-查看 2-赞 3-收藏 4-分享 5-采纳/使用',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `creator_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modifier_id` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `to_db_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '解析名称（如没有设置，自动生成）',
  `content` text COMMENT '解析生成内容',
  `creator_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modifier_id` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `employee_number` varchar(32) DEFAULT NULL COMMENT '工号',
  `account` varchar(32) DEFAULT NULL COMMENT '账号',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话号码',
  `create_time` datetime DEFAULT NULL,
  `modifier_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
