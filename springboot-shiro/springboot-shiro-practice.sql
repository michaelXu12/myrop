/*
DROP DATABASE IF EXISTS springboot_shiro_practice;
CREATE DATABASE springboot_shiro_practice DEFAULT CHARACTER SET UTF8;
*/

/*
用户表
*/
DROP TABLE IF EXISTS users;
CREATE TABLE users (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'自增id',
name VARCHAR(32) NOT NULL COMMENT'用户名',
password VARCHAR(128) NOT NULL COMMENT'密码',
remark VARCHAR(64) COMMENT '备注'
) COMMENT='用户表';

INSERT INTO users(name,password,remark) VALUES("苍老师",MD5("111"),"你懂的");
INSERT INTO users(name,password,remark) VALUES("小炮同学",MD5("666"),"无");

/*
角色表
*/
DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'自增id',
name VARCHAR(64) NOT NULL COMMENT'角色名称',
remark VARCHAR(64) COMMENT'备注'
) COMMENT = '角色表';

INSERT INTO roles (name,remark) VALUES ("TEACHER",'老师');
INSERT INTO roles (name,remark) VALUES ("STUDENT",'学生');


/*
权限表
*/
DROP TABLE IF EXISTS privilege;
CREATE TABLE privilege (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'自增id',
name VARCHAR(64) NOT NULL COMMENT'权限名称',
remark VARCHAR(64) COMMENT'备注' 
) COMMENT = '权限表';

INSERT INTO privilege (name,remark) VALUES("DRINKING_WINE","喝酒");
INSERT INTO privilege (name,remark) VALUES("SMOKING","抽烟");
INSERT INTO privilege (name,remark) VALUES("PLAY_GAME","玩游戏");

/*
用户-角色关联表
*/
DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
user_id INT NOT NULL COMMENT '用户id',
role_id INT NOT NULL COMMENT '角色id'
) COMMENT = '用户角色关联表';

INSERT INTO user_role (user_id,role_id) VALUES(1,1);
INSERT INTO user_role (user_id,role_id) VALUES(2,2);

/*
角色-权限表
*/
DROP TABLE IF EXISTS role_privilege;
CREATE TABLE role_privilege(
role_id INT NOT NULL COMMENT'角色id',
privilege_id INT NOT NULL COMMENT'权限表id'
)COMMENT ='角色权限表';

INSERT INTO role_privilege (role_id,privilege_id) VALUES(1,1);
INSERT INTO role_privilege (role_id,privilege_id) VALUES(1,2);
INSERT INTO role_privilege (role_id,privilege_id) VALUES(2,3);
















