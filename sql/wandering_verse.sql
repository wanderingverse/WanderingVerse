drop database if exists wandering_verse;
CREATE DATABASE IF NOT EXISTS wandering_verse CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE wandering_verse;

drop table if exists user;
CREATE TABLE user
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '用户表主键 id',
    username           VARCHAR(16) COMMENT '用户名',
    password           VARCHAR(128) COMMENT '密码',
    nickname           VARCHAR(16) COMMENT '昵称',
    email              VARCHAR(64) COMMENT '邮箱',
    phone_number       VARCHAR(11) COMMENT '手机号',
    gender             TINYINT UNSIGNED COMMENT '性别',
    avatar             VARCHAR(128) COMMENT '头像地址',
    user_status        TINYINT COMMENT '用户状态',
    delete_status      TINYINT COMMENT '删除状态',
    login_time         DATETIME COMMENT '最后一次登录时间',
    login_ipv4         VARCHAR(16) COMMENT '最后一次登录 IPv4',
    login_ipv4_decimal INT UNSIGNED COMMENT '最后一次登录 IPv4 整型值',
    create_user_id     BIGINT UNSIGNED COMMENT '创建用户 id',
    update_user_id     BIGINT UNSIGNED COMMENT '更新用户 id',
    remark             TEXT COMMENT '备注',
    create_time        DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='用户表';


drop table if exists role;
CREATE TABLE role
(
    id             BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '角色表主键 id',
    role_name      VARCHAR(16) COMMENT '角色名称',
    description    VARCHAR(128) COMMENT '角色描述',
    delete_status  TINYINT COMMENT '删除状态',
    create_user_id BIGINT UNSIGNED COMMENT '创建用户 id',
    update_user_id BIGINT UNSIGNED COMMENT '更新用户 id',
    remark         TEXT COMMENT '备注',
    create_time    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='角色表';

drop table if exists user_role;
CREATE TABLE user_role
(
    id          bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '用户-角色关系表主键 id',
    user_id     bigint unsigned COMMENT '用户表主键 id',
    role_id     bigint unsigned COMMENT '角色表主键 id',
    remark      text COMMENT '备注',
    create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='用户-角色关系表';

drop table if exists user_permission;
CREATE TABLE user_permission
(
    id            bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '角色-权限关系表主键 id',
    role_id       bigint unsigned COMMENT '角色表主键 id',
    permission_id bigint unsigned COMMENT '权限表主键 id',
    remark        text COMMENT '备注',
    create_time   datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time   datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='角色-权限关系表';

drop table if exists permission;
CREATE TABLE permission
(
    id              bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '权限表主键 id',
    permission_name varchar(16) COMMENT '权限名称',
    description     varchar(128) COMMENT '权限描述',
    remark          text COMMENT '备注',
    create_time     datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time     datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='权限表';

drop table if exists blog_category;
CREATE TABLE blog_category
(
    id            bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '文章分类表主键 id',
    category_name varchar(16)     NOT NULL UNIQUE COMMENT '分类名称',
    sort_order    bigint unsigned NOT NULL UNIQUE COMMENT '排序',
    create_time   datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time   datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='文章分类表 blog_category';

drop table if exists category_post;
CREATE TABLE category_post
(
    id               bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '分类-文章关联表主键 id',
    blog_category_id bigint unsigned NOT NULL COMMENT '文章分类表主键 id',
    blog_post_id     bigint unsigned NOT NULL COMMENT '博客文章表主键 id',
    create_time      datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time      datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='分类-文章关联表 category_post';


drop table if exists blog_post;
CREATE TABLE blog_post
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '博客文章表主键 id',
    title              TEXT NOT NULL COMMENT '文章标题',
    summary            TEXT COMMENT '文章摘要',
    author_id          BIGINT UNSIGNED COMMENT '作者 id',
    content_id         BIGINT UNSIGNED COMMENT '正文 id',
    toponym_id         BIGINT UNSIGNED COMMENT '写作地点 id',
    cover_picture_url  VARCHAR(256) COMMENT '封面图片 url',
    cover_picture_name VARCHAR(64) COMMENT '封面图片名',
    delete_status      TINYINT COMMENT '删除状态',
    create_time        DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='博客文章表';

drop table if exists blog_post_content;
CREATE TABLE blog_post_content
(
    id      BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '博客文章内容表主键 id',
    content MEDIUMTEXT COMMENT '正文内容'
) COMMENT ='博客文章内容表';

drop table if exists daily_life;
CREATE TABLE daily_life
(
    id                 bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '日常生活表主键 id',
    user_id            bigint unsigned COMMENT '用户 id',
    living_state_id    bigint unsigned COMMENT '生活状态 id',
    beginning_time     datetime COMMENT '开始时间',
    finishing_time     datetime COMMENT '结束时间',
    expenditure_amount decimal(7, 2) COMMENT '花费金额（元）',
    income_amount      decimal(7, 2) COMMENT '收入金额（元）',
    remark             varchar(1024) COMMENT '备注',
    create_time        datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time        datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='日常生活表';

drop table if exists living_state;
CREATE TABLE living_state
(
    id                bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '生活状态表主键 id',
    living_state_name varchar(16) COMMENT '生活状态名称',
    description       varchar(1024) COMMENT '生活状态描述',
    parent_id         bigint unsigned COMMENT '父节点 id',
    create_time       datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time       datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='生活状态表';

drop table if exists toponym;
CREATE TABLE toponym
(
    id        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '地名表主键 id',
    parent_id BIGINT UNSIGNED COMMENT '父级地理位置 id',
    name      VARCHAR(256) COMMENT '地名'
) COMMENT ='地名表';

drop table if exists poetry;
CREATE TABLE poetry
(
    id          bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '诗词表主键 id',
    title       varchar(64) NOT NULL COMMENT '诗词名',
    author      varchar(16) NOT NULL COMMENT '诗词作者',
    content     text        NOT NULL COMMENT '诗词正文',
    category    text        NOT NULL COMMENT '诗词标签',
    sha_256     char(64)    NOT NULL UNIQUE COMMENT 'SHA-256',
    create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='诗词表';


drop table if exists sys_operation_log;
CREATE TABLE sys_operation_log
(
    id                 INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '操作日志表主键',
    user_id            INT UNSIGNED COMMENT '用户 id',
    operation_name     VARCHAR(16) COMMENT '操作名称',
    operation_type     VARCHAR(8) COMMENT '操作类型',
    operation_result   TINYINT COMMENT '操作结果',
    operation_time     DATETIME COMMENT '操作时间',
    change_value       JSON COMMENT '变动值',
    operation_path     VARCHAR(512) COMMENT '请求路径',
    request_parameter  JSON COMMENT '请求参数',
    response_parameter JSON COMMENT '响应参数',
    user_ipv4          VARCHAR(16) COMMENT '用户 IPv4 地址',
    user_ipv4_decimal  INT UNSIGNED COMMENT '用户 IPv4 地址整型值',
    operating_system   VARCHAR(16) COMMENT '操作系统名称',
    device_type        VARCHAR(16) COMMENT '访问设备类型',
    browser_info       VARCHAR(256) COMMENT '浏览器信息（名称、版本）',
    create_time        DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='操作日志表';