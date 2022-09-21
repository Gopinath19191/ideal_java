/*
SQLyog Community v9.61 
MySQL - 5.5.25a : Database - faq_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`faq_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `faq_db`;

/*Table structure for table `faq_function_unit` */

DROP TABLE IF EXISTS `faq_function_unit`;

CREATE TABLE `faq_function_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `faq_unit` varchar(100) NOT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `created_date` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `modified_date` varchar(45) DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `faq_function_unit` */

insert  into `faq_function_unit`(`id`,`faq_unit`,`deleted`,`created_date`,`created_by`,`modified_date`,`modified_by`) values (1,'ideal',0,NULL,NULL,NULL,NULL),(2,'hr',0,NULL,NULL,NULL,NULL),(3,'sales',0,NULL,NULL,NULL,NULL),(4,'finance',0,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
