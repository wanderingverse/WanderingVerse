START TRANSACTION;

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
    login_ipv4_decimal INT UNSIGNED COMMENT '最后一次登录 IPv4整型值',
    create_user_id     BIGINT UNSIGNED COMMENT '创建用户 id',
    update_user_id     BIGINT UNSIGNED COMMENT '更新用户 id',
    remark             TEXT COMMENT '备注',
    create_time        DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='用户表';


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
COMMIT;