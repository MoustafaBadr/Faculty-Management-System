-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: faculty
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `cost` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `f8_idx` (`department_id`),
  CONSTRAINT `f8` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=395 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (377,1,'java','10'),(378,3,'java','10'),(380,3,'Computer Language','20'),(381,1,'Software Engineering','30'),(382,3,'Software Engineering','30'),(383,2,'Software Engineering','30'),(384,1,'Modeling and Semulation','90'),(385,3,'Modeling and Semulation','90'),(386,2,'Modeling and Semulation','90'),(387,4,'Modeling and Semulation','90'),(388,1,'Network 1','70'),(389,3,'Network 1','70'),(390,2,'Network 1','70'),(391,1,'Database 1','120'),(392,3,'Database 1','120'),(393,2,'Database 1','120'),(394,4,'Database 1','120');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'cs'),(2,'it'),(3,'is'),(4,'or');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `phone_num` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `salary` varchar(45) NOT NULL,
  `department_id` int(11) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (132,'Mostafa','Mohamed','Gharbia','Santa','Tanta','01149866799','Mostafa@gmail.com','1000',1),(133,'Mostafa','Mahmoud','Gharbia','Santa','Tanta','01149866799','Mahmoud@gmail.com','1000',1),(134,'Ahmed','Mahmoud','Gharbia','Santa','Tanta','01149866799','Ahmed@gmail.com','1000',1),(137,'Mostafa','Ali','Menofia','Ashmoun','shibin','01006366749','Mostsa@gmail.com','2500',1),(138,'Mostafa','Ali','Menofia','Ashmoun','shibin','01006366749','AmirEla7zan@gmail.com','2500',1),(139,'Hana','Ali','Menofia','Ashmoun','shibin','01006366749','Tito@gmail.com','2500',1),(140,'Hana','Ali','Menofia','sadat','sadat','01006366749','Kiko@gmail.com','2500',1),(141,'Hana','Mahmoud','Menofia','sadat','sadat','01006366749','Kiko1234@gmail.com','2500',1),(142,'Esraa','Ali','Cairo','Shobra','Masr El Gedida','0114578868','Soso@gmail.com','1500',1),(143,'Eman','Ali','Cairo','Shobra','Masr El Gedida','0114578868','Emi@gmail.com','1500',1),(144,'Mona','Ali','Cairo','Shobra','Masr El Gedida','0114578868','Mona@gmail.com','1500',1),(145,'Reem','Ali','Cairo','Shobra','Masr El Gedida','0114578868','Reem@gmail.com','1500',1);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `phone_num` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `graduation_year` varchar(45) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_num_UNIQUE` (`phone_num`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (14,'ibraheem','elfakharany','tanta','cityTanta','siingapr','egypt','01127788472','ibraheemelfa5arany@gmail.com','2018'),(21,'sfsfs','ffsfsfs','sfsfsfsfsf','sfsf','fsdfs','xfxf','kokomk','sfsfs@fhf','sfsf');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'faculty'
--

--
-- Dumping routines for database 'faculty'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-30 16:30:13
