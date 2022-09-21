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

/*Table structure for table `faq_question_answer` */

DROP TABLE IF EXISTS `faq_question_answer`;

CREATE TABLE `faq_question_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` text NOT NULL,
  `answer` text NOT NULL,
  `answer_path` varchar(100) DEFAULT NULL,
  `unit_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `created_date` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `modified_date` varchar(45) DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `unit_id` (`unit_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `faq_question_answer_ibfk_1` FOREIGN KEY (`unit_id`) REFERENCES `faq_function_unit` (`id`),
  CONSTRAINT `faq_question_answer_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `faq_question_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `faq_question_answer` */

insert  into `faq_question_answer`(`id`,`question`,`answer`,`answer_path`,`unit_id`,`category_id`,`deleted`,`created_date`,`created_by`,`modified_date`,`modified_by`) values (1,'HrQuestion1','HrQuestion2',NULL,2,10,1,'2018-10-09 15:24:36','3083','2018-10-09 15:25:03','3083'),(2,'HrQuestion1','HrAnswer3',NULL,2,10,0,'2018-10-09 15:25:58','3083','2018-10-12 13:21:47','3083'),(3,'HrQuestion2?','HrAnswer2',NULL,2,10,0,'2018-10-09 15:27:10','3083',NULL,NULL),(4,'HrQuestion3?','HrAnswer3',NULL,2,10,0,'2018-10-09 15:27:10','3083','2018-10-12 13:23:16','3083'),(5,'Sales Question 1?','Sales Answer 2',NULL,3,11,0,'2018-10-09 15:40:16','3083',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
