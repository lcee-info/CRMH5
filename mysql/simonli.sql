/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.24 : Database - simonli
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`simonli` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `simonli`;

/*Table structure for table `s_logins` */

DROP TABLE IF EXISTS `s_logins`;

CREATE TABLE `s_logins` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名为手机号',
  `series` varchar(300) DEFAULT NULL,
  `token` varchar(500) DEFAULT NULL,
  `validTime` datetime DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `s_logins` */

insert  into `s_logins`(`login_id`,`username`,`series`,`token`,`validTime`) values (3,'15923363417','e02f5865-0afa-48ab-9064-ff18c7899cf1','f20f34e612f7f87c356decad606dc872143de54c11c03a9ed301c35c96d702b7','2018-07-06 10:29:25');

/*Table structure for table `s_user` */

DROP TABLE IF EXISTS `s_user`;

CREATE TABLE `s_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `country_name` varchar(100) DEFAULT NULL,
  `province` varchar(10) DEFAULT NULL,
  `province_name` varchar(50) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `city_name` varchar(100) DEFAULT NULL,
  `openid` varchar(50) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `token` varchar(200) DEFAULT NULL COMMENT '验签token',
  `finish` varchar(2) DEFAULT NULL COMMENT '是否完成',
  `headimgurl` varchar(500) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `subscribe` varchar(2) DEFAULT NULL COMMENT '是否关注公众号',
  `completion` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `s_user` */

insert  into `s_user`(`id`,`mobile`,`name`,`sex`,`birthday`,`country`,`country_name`,`province`,`province_name`,`city`,`city_name`,`openid`,`remark`,`token`,`finish`,`headimgurl`,`nickname`,`subscribe`,`completion`) values (14,'15923363417','1234去','2','2018-06-12','2',NULL,NULL,NULL,'110000,110000,110112','北京,北京市,通州区','',NULL,'f20f34e612f7f87c356decad606dc872143de54c11c03a9ed301c35c96d702b7','1','','',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
