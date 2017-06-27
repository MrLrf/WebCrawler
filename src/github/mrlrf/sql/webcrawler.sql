/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 5.6.31 : Database - webcrawler
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`webcrawler` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `webcrawler`;

/*Table structure for table `dblp_conference` */

DROP TABLE IF EXISTS `dblp_conference`;

CREATE TABLE `dblp_conference` (
  `conference_id` varchar(50) NOT NULL,
  `conference_name` varchar(50) DEFAULT NULL COMMENT '会议名',
  `conference_title` varchar(500) DEFAULT NULL COMMENT '会议标题',
  `conference_titlech` varchar(500) DEFAULT NULL COMMENT '会议标题(中文)',
  `conference_date` varchar(50) DEFAULT NULL,
  `content_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`conference_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `dblp_paper` */

DROP TABLE IF EXISTS `dblp_paper`;

CREATE TABLE `dblp_paper` (
  `paper_id` varchar(50) NOT NULL,
  `conference_id` varchar(50) DEFAULT NULL,
  `paper_topical` varchar(500) DEFAULT NULL COMMENT '论文主题',
  `paper_name` varchar(500) DEFAULT NULL COMMENT '论文名字',
  `paper_namech` varchar(500) DEFAULT NULL COMMENT '论文名(中文)',
  `page_num` varchar(50) DEFAULT NULL COMMENT '论文页码',
  `paper_url` varchar(200) DEFAULT NULL COMMENT '论文URL',
  PRIMARY KEY (`paper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `zhihu_answer` */

DROP TABLE IF EXISTS `zhihu_answer`;

CREATE TABLE `zhihu_answer` (
  `answer_id` varchar(50) NOT NULL,
  `question_id` varchar(50) DEFAULT NULL COMMENT '对应的问题ID',
  `answer_content` text COMMENT '答案内容',
  PRIMARY KEY (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `zhihu_question` */

DROP TABLE IF EXISTS `zhihu_question`;

CREATE TABLE `zhihu_question` (
  `question_id` varchar(50) NOT NULL,
  `question_title` varchar(200) DEFAULT NULL COMMENT '知乎问题标题',
  `question_description` varchar(500) DEFAULT NULL COMMENT '知乎问题描述',
  `question_url` varchar(200) DEFAULT NULL COMMENT '问题链接',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
