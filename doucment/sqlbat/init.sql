CREATE USER 'WDS'@'localhost' IDENTIFIED BY '@WDS1024';
CREATE DATABASE WDS CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
GRANT ALL PRIVILEGES ON WDS.* TO 'WDS'@'localhost';
CREAte TABLE account (
  account_no INT AUTO_INCREMENT
  username VCHAR(30) NOT NULL,
  password VCHAR(50) NOT NULL,
  UNIQUE KEY unique_username(username),
  INDEX index_username(username),
  PRIMARY KEY(account_id)
);
