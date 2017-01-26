DROP SCHEMA IF EXISTS `trading`;
CREATE SCHEMA `trading`;
DROP TABLE IF EXISTS `trading`.`user`;
DROP TABLE IF EXISTS `trading`.`stocks`;
CREATE TABLE `trading`.`stocks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) DEFAULT NULL,
  `current_price` varchar(45) DEFAULT NULL,
  `purchase_price` varchar(45) DEFAULT NULL,
  `net_profit` varchar(45) DEFAULT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `trading`.`user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `trading`.`user` (`id`,`username`) VALUES ('1', 'Biju');