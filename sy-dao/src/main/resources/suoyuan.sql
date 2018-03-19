/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.18 : Database - db_suoyuan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_suoyuan` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_suoyuan`;

/*Table structure for table `t_diray` */

DROP TABLE IF EXISTS `t_diray`;

CREATE TABLE `t_diray` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '日记id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `content` text NOT NULL COMMENT '内容',
  `write_time` datetime NOT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `email` varchar(30) NOT NULL COMMENT '邮箱',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `nickname` varchar(20) NOT NULL COMMENT '昵称',
  `avator` varchar(250) DEFAULT NULL COMMENT '用户头像',
  `signature` varchar(250) DEFAULT NULL COMMENT '个性签名',
  `is_open` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否公开日记 0:不公开 1:公开',
  `diray_count` int(10) NOT NULL DEFAULT '0' COMMENT '用户日记数',
  `last_diray_date` varchar(10) DEFAULT '0000-00-00' COMMENT '最后一次写日记的日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
