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

/*Table structure for table `faq_question_category` */

DROP TABLE IF EXISTS `faq_question_category`;

CREATE TABLE `faq_question_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_category` varchar(100) NOT NULL,
  `unit_id` int(11) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `created_date` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `modified_date` varchar(45) DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `unit_id` (`unit_id`),
  CONSTRAINT `faq_question_category_ibfk_1` FOREIGN KEY (`unit_id`) REFERENCES `faq_function_unit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `faq_question_category` */

insert  into `faq_question_category`(`id`,`question_category`,`unit_id`,`deleted`,`created_date`,`created_by`,`modified_date`,`modified_by`) values (8,'ideal',1,0,NULL,NULL,NULL,NULL),(9,'ideal',2,0,NULL,NULL,NULL,NULL),(10,'hr',2,0,NULL,NULL,NULL,NULL),(11,'hr',3,0,NULL,NULL,NULL,NULL),(12,'sales',3,0,NULL,NULL,NULL,NULL),(13,'finance',4,0,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
