USE `mysql`;

CREATE DATABASE IF NOT EXISTS `security` DEFAULT  CHARACTER SET utf8mb4 COLLATE  utf8mb4_general_ci;

-- 密码限制
SHOW VARIABLES LIKE 'validate_password%';
SET GLOBAL validate_password_length = 8; -- 最小长度
SET GLOBAL validate_password_policy = LOW; -- LOW、MEDIUM、STRONG

CREATE USER IF NOT EXISTS 'caddy'@'%' IDENTIFIED BY 'caddy@415';
GRANT ALL PRIVILEGES ON `security`.* TO 'caddy'@'%';
-- 当使用flyway时需要查询此表的权限
GRANT SELECT ON `performance_schema`.`user_variables_by_thread` TO 'caddy'@'%';
FLUSH PRIVILEGES;

USE `security`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
