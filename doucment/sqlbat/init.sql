# 创建用户
CREATE USER 'WDS'@'localhost' IDENTIFIED BY '@WDS1024';
# 创建数据库
CREATE DATABASE WDS CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
# 分配权限
GRANT ALL PRIVILEGES ON WDS.* TO 'WDS'@'localhost';
# 选择默认数据
USE WDS;
# 创建用户表
CREATE TABLE user
(
    id INT AUTO_INCREMENT,
    username   VARCHAR(30) NOT NULL,
    password   VARCHAR(50) NOT NULL,
    nickname VARCHAR(20),
    email VARCHAR(30),
    portrait VARCHAR(100),

    create_time datetime NOT NULL,
    update_time datetime NOT NULL,
    UNIQUE KEY unique_username (username),
    PRIMARY KEY (id)
);


