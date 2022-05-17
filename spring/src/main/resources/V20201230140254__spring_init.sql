USE `mysql`;

CREATE DATABASE IF NOT EXISTS `spring5` DEFAULT  CHARACTER SET utf8mb4 COLLATE  utf8mb4_general_ci;

CREATE USER IF NOT EXISTS 'caddy'@'%' IDENTIFIED BY 'caddy@415';
GRANT ALL PRIVILEGES ON `spring5`.* TO 'caddy'@'%';
FLUSH PRIVILEGES;

USE `spring5`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create table spring5_user
(
	id int auto_increment,
	name varchar(10) null,
	age int null,
	constraint spring5_user_pk
		primary key (id)
);

