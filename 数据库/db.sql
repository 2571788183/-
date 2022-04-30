/*
SQLyog Professional v13.1.1 (64 bit)
MySQL - 5.1.72-community : Database - w10901wlpbxt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`w10901wlpbxt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `w10901wlpbxt`;

/*Table structure for table `t_expertreview` */

DROP TABLE IF EXISTS `t_expertreview`;

CREATE TABLE `t_expertreview` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专家评审id,主键',
  `production` int(11) DEFAULT NULL COMMENT '作品id,外键',
  `settime` datetime DEFAULT NULL COMMENT '发布时间',
  `yijiancontent` text COMMENT '专家意见',
  `score` int(11) DEFAULT NULL COMMENT '评分',
  `jieluncontent` text COMMENT '结论',
  `judge` int(11) DEFAULT NULL COMMENT '评委id,外键',
  `member` int(11) DEFAULT NULL COMMENT '参赛者id,外键',
  PRIMARY KEY (`id`),
  KEY `fk_expertReview_production` (`production`),
  KEY `fk_expertReview_judge` (`judge`),
  KEY `fk_expertReview_member` (`member`),
  CONSTRAINT `fk_expertReview_member` FOREIGN KEY (`member`) REFERENCES `t_member` (`id`),
  CONSTRAINT `fk_expertReview_judge` FOREIGN KEY (`judge`) REFERENCES `t_judge` (`id`),
  CONSTRAINT `fk_expertReview_production` FOREIGN KEY (`production`) REFERENCES `t_production` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_expertreview` */

insert  into `t_expertreview`(`id`,`production`,`settime`,`yijiancontent`,`score`,`jieluncontent`,`judge`,`member`) values 
(1,1,'2021-04-09 11:47:37',NULL,123,NULL,1,1);

/*Table structure for table `t_judge` */

DROP TABLE IF EXISTS `t_judge`;

CREATE TABLE `t_judge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评委id,主键',
  `name` varchar(200) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(200) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `tel` varchar(200) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(200) DEFAULT NULL COMMENT '住址',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `users` int(11) DEFAULT NULL COMMENT '登录账号id,外键',
  PRIMARY KEY (`id`),
  KEY `fk_judge_users` (`users`),
  CONSTRAINT `fk_judge_users` FOREIGN KEY (`users`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_judge` */

insert  into `t_judge`(`id`,`name`,`sex`,`birthday`,`tel`,`address`,`email`,`users`) values 
(1,'judge','男','2021-04-09 11:47:37','111','测试','11@qq.com',3);

/*Table structure for table `t_member` */

DROP TABLE IF EXISTS `t_member`;

CREATE TABLE `t_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id,主键',
  `img` varchar(200) DEFAULT NULL COMMENT '头像',
  `name` varchar(200) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(200) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `tel` varchar(200) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(200) DEFAULT NULL COMMENT '住址',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `idcardimg` varchar(200) DEFAULT NULL COMMENT '身份证',
  `users` int(11) DEFAULT NULL COMMENT '登录账号id,外键',
  PRIMARY KEY (`id`),
  KEY `fk_member_users` (`users`),
  CONSTRAINT `fk_member_users` FOREIGN KEY (`users`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_member` */

insert  into `t_member`(`id`,`img`,`name`,`sex`,`birthday`,`tel`,`address`,`email`,`idcardimg`,`users`) values 
(1,'202104091149594320.png','member','男','2021-04-09 00:00:00','111','测试','11@qq.com','202104091150059630.jpg',2);

/*Table structure for table `t_production` */

DROP TABLE IF EXISTS `t_production`;

CREATE TABLE `t_production` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '作品id,主键',
  `name` varchar(200) DEFAULT NULL COMMENT '作品名称',
  `settime` datetime DEFAULT NULL COMMENT '发布时间',
  `descp` varchar(200) DEFAULT NULL COMMENT '作品简介',
  `content` text COMMENT '作品详情',
  `member` int(11) DEFAULT NULL COMMENT '参赛者id,外键',
  `docts` varchar(200) DEFAULT NULL COMMENT '作品文件',
  PRIMARY KEY (`id`),
  KEY `fk_production_member` (`member`),
  CONSTRAINT `fk_production_member` FOREIGN KEY (`member`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_production` */

insert  into `t_production`(`id`,`name`,`settime`,`descp`,`content`,`member`,`docts`) values 
(1,'测试','2021-04-09 00:00:00','测试','<p><img src=\"http://localhost:8080/\\upload_image\\202104091153472370.png\" style=\"height:819px; width:1120px\" /></p>\r\n',1,'202104091152429280.docx');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id,主键',
  `name` varchar(200) DEFAULT NULL COMMENT '权限名称',
  `descp` varchar(200) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`name`,`descp`) values 
(1,'系统管理员','超级管理员权限'),
(2,'用户','用户权限'),
(3,'评委','评委权限');

/*Table structure for table `t_shenhe` */

DROP TABLE IF EXISTS `t_shenhe`;

CREATE TABLE `t_shenhe` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报名审核id,主键',
  `name` varchar(200) DEFAULT NULL COMMENT '状态名称',
  `descp` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_shenhe` */

insert  into `t_shenhe`(`id`,`name`,`descp`) values 
(1,'待审核','测试'),
(2,'审核通过',NULL),
(3,'审核未通过',NULL);

/*Table structure for table `t_signup` */

DROP TABLE IF EXISTS `t_signup`;

CREATE TABLE `t_signup` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报名id,主键',
  `name` varchar(200) DEFAULT NULL COMMENT '姓名',
  `settime` datetime DEFAULT NULL COMMENT '报名时间',
  `tel` varchar(200) DEFAULT NULL COMMENT '电话',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `idno` varchar(200) DEFAULT NULL COMMENT '身份证号',
  `birthday` varchar(200) DEFAULT NULL COMMENT '出生日期',
  `content` text COMMENT '其他报名材料',
  `shenhe` int(11) DEFAULT NULL COMMENT '审核id,外键',
  `member` int(11) DEFAULT NULL COMMENT '报名账号id,外键',
  PRIMARY KEY (`id`),
  KEY `fk_signup_shenhe` (`shenhe`),
  KEY `fk_signup_member` (`member`),
  CONSTRAINT `fk_signup_member` FOREIGN KEY (`member`) REFERENCES `t_member` (`id`),
  CONSTRAINT `fk_signup_shenhe` FOREIGN KEY (`shenhe`) REFERENCES `t_shenhe` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_signup` */

insert  into `t_signup`(`id`,`name`,`settime`,`tel`,`email`,`idno`,`birthday`,`content`,`shenhe`,`member`) values 
(1,'测试','2021-04-09 00:00:00','测试','测试','123123','测试','',1,1);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id,主键',
  `username` varchar(200) DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '登录密码',
  `role` int(11) DEFAULT NULL COMMENT '权限id,外键',
  PRIMARY KEY (`id`),
  KEY `fk_user_role` (`role`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`role`) values 
(1,'admin','admin',1),
(2,'member','member',2),
(3,'judge','judge',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
