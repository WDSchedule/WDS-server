CREATE USER 'WDS'@'localhost' IDENTIFIED BY '@WDS1024';
CREATE DATABASE WDS CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
GRANT ALL PRIVILEGES ON WDS.* TO 'WDS'@'localhost';
USE WDS;
CREATE TABLE account
(
    account_no INT AUTO_INCREMENT,
    username   VARCHAR(30) NOT NULL,
    password   VARCHAR(50) NOT NULL,
    create_time datetime,
    update_time datetime,
    UNIQUE KEY unique_username (username),
    INDEX index_username (username),
    INDEX index_id(account_no),
    PRIMARY KEY (account_no)
);
ALTER TABLE account ADD nickname VARCHAR(20);
