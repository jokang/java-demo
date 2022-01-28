CREATE TABLE `blog`
(
    `id`      int(11)     NOT NULL AUTO_INCREMENT,
    `tags`    varchar(50) NOT NULL DEFAULT '',
    `content` varchar(45) NOT NULL,
    `status`  int(11)              DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4