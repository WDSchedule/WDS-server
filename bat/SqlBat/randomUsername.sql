use wds;
CREATE TABLE random_username(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL ,
    username VARCHAR(30) NOT NULL ,
    is_used enum('Y','N') NOT NULL ,
    create_time datetime NOT NULL ,
    update_time datetime NOT NULL ,
    INDEX index_username(username),
    INDEX index_is_used(is_used)
);