use wds;
# 创建用户表
CREATE TABLE user
(
    id INT AUTO_INCREMENT,
    username   VARCHAR(30) NOT NULL,
    password   VARCHAR(50) NOT NULL,
    nickname VARCHAR(20),
    email VARCHAR(50),
    portrait VARCHAR(100),

    create_time datetime NOT NULL,
    update_time datetime NOT NULL,
    UNIQUE KEY unique_username (username),
    UNIQUE KEY unique_email (email),
    PRIMARY KEY (id)
);