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

/*Table structure for table `t_article` */

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `url` varchar(50) NOT NULL COMMENT '来源',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `author` varchar(20) NOT NULL COMMENT '作者',
  `content` text NOT NULL COMMENT '内容',
  `emotion_val` double NOT NULL COMMENT '情感值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_diray` */

DROP TABLE IF EXISTS `t_diray`;

CREATE TABLE `t_diray` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '日记id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `content` text NOT NULL COMMENT '内容',
  `write_time` datetime NOT NULL COMMENT '时间',
  `emotion_val` double DEFAULT NULL COMMENT '情感值',
  `po_emotion_val` double DEFAULT NULL COMMENT '积极情感值',
  `ne_emotionVal` double DEFAULT NULL COMMENT '消极情感值',
  `po_count` int(11) DEFAULT NULL COMMENT '积极句子数量',
  `ne_count` int(11) DEFAULT NULL COMMENT '消极句子数量',
  `po_sentences` varchar(510) DEFAULT NULL COMMENT '积极句子,句子间用;隔开',
  `ne_sentences` varchar(510) DEFAULT NULL COMMENT '消极句子,句子间用;隔开',
  `po_sentence_details` varchar(510) DEFAULT NULL COMMENT '积极句子详情,句子间用;隔开',
  `ne_sentence_details` varchar(510) DEFAULT NULL COMMENT '消极句子详情,句子间用;隔开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_notify` */

DROP TABLE IF EXISTS `t_notify`;

CREATE TABLE `t_notify` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `msg` varchar(200) NOT NULL COMMENT '通知内容',
  `topic_id` int(10) NOT NULL COMMENT '在哪个话题之下的通知',
  `is_read` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否已经阅读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_reply` */

DROP TABLE IF EXISTS `t_reply`;

CREATE TABLE `t_reply` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `topic_id` int(10) NOT NULL COMMENT '回复的话题id',
  `title` varchar(200) NOT NULL COMMENT '回复的话题的名称',
  `topic_creater_id` int(10) NOT NULL COMMENT '话题创建者id',
  `from_user_id` int(10) NOT NULL COMMENT '回复者id',
  `nickname` varchar(20) NOT NULL,
  `avator` varchar(200) NOT NULL,
  `to_user_id` int(10) DEFAULT NULL,
  `to_nickname` varchar(50) DEFAULT NULL,
  `to_reply_id` int(10) DEFAULT NULL,
  `content` text NOT NULL COMMENT '回复内容',
  `write_time` datetime DEFAULT NULL,
  `path` varchar(300) DEFAULT NULL COMMENT '路径枚举',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_tag` */

DROP TABLE IF EXISTS `t_tag`;

CREATE TABLE `t_tag` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `creater_id` int(10) NOT NULL COMMENT '标签创建者id',
  `name` varchar(10) NOT NULL COMMENT '标签名',
  `topic_num` int(10) DEFAULT '0' COMMENT '此标签下的话题数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_topic` */

DROP TABLE IF EXISTS `t_topic`;

CREATE TABLE `t_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `title` varchar(20) NOT NULL COMMENT '话题题目',
  `tag` varchar(10) DEFAULT NULL COMMENT '话题标签',
  `content` text NOT NULL COMMENT '话题提出者说的话',
  `write_time` datetime NOT NULL COMMENT '时间',
  `reply_num` int(10) NOT NULL DEFAULT '0' COMMENT '回复数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `email` varchar(30) NOT NULL COMMENT '邮箱',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `nickname` varchar(20) NOT NULL COMMENT '昵称',
  `avator` varchar(250) DEFAULT 'http://localhost:8080/pic/0.jpg' COMMENT '用户头像',
  `signature` varchar(250) DEFAULT NULL COMMENT '个性签名',
  `is_open` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否公开日记 0:不公开 1:公开',
  `diray_count` int(10) NOT NULL DEFAULT '0' COMMENT '用户日记数',
  `last_diray_date` varchar(10) DEFAULT '0000-00-00' COMMENT '最后一次写日记的日期',
  `now_emotion` double DEFAULT '0' COMMENT '当前情感值',
  `last_diray_emotion` double DEFAULT '0' COMMENT '最后一天写日记时的情感值',
  `last_login_date` varchar(10) DEFAULT '0000-00-00' COMMENT '上次登录的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
