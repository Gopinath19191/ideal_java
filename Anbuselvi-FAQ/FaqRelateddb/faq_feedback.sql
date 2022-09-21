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

/*Table structure for table `faq_feedback` */

DROP TABLE IF EXISTS `faq_feedback`;

CREATE TABLE `faq_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `feedback_rating_comments` text NOT NULL,
  `is_sastisfied` enum('y','n') NOT NULL,
  `unit_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `created_date` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `modified_date` varchar(45) DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `unit_id` (`unit_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `faq_feedback_ibfk_1` FOREIGN KEY (`unit_id`) REFERENCES `faq_function_unit` (`id`),
  CONSTRAINT `faq_feedback_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `faq_question_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `faq_feedback` */

insert  into `faq_feedback`(`id`,`feedback_rating_comments`,`is_sastisfied`,`unit_id`,`category_id`,`created_date`,`created_by`,`modified_date`,`modified_by`) values (1,'4','y',2,10,'2018-10-09 15:32:05','3083',NULL,NULL),(2,'In sufficient faq','n',3,12,'2018-10-09 15:42:59','3083',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
