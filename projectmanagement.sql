-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: projectmanagement
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `projectId` int NOT NULL AUTO_INCREMENT,
  `managerId` int NOT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `dateCreated` date DEFAULT NULL,
  PRIMARY KEY (`projectId`),
  KEY `managerId` (`managerId`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`managerId`) REFERENCES `teammanager` (`managerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectmemberbridge`
--

DROP TABLE IF EXISTS `projectmemberbridge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projectmemberbridge` (
  `memberid` int NOT NULL AUTO_INCREMENT,
  `projectId` int NOT NULL,
  PRIMARY KEY (`memberid`,`projectId`),
  KEY `projectId` (`projectId`),
  CONSTRAINT `projectmemberbridge_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `teammember` (`memberId`),
  CONSTRAINT `projectmemberbridge_ibfk_2` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectmemberbridge`
--

LOCK TABLES `projectmemberbridge` WRITE;
/*!40000 ALTER TABLE `projectmemberbridge` DISABLE KEYS */;
/*!40000 ALTER TABLE `projectmemberbridge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taskbacklog`
--

DROP TABLE IF EXISTS `taskbacklog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taskbacklog` (
  `taskId` int NOT NULL AUTO_INCREMENT,
  `storyId` int DEFAULT NULL,
  `memberId` int DEFAULT NULL,
  `taskDescription` varchar(300) DEFAULT NULL,
  `taskStatus` tinyint(1) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `targetDate` date DEFAULT NULL,
  `completionDate` date DEFAULT NULL,
  PRIMARY KEY (`taskId`),
  KEY `storyId` (`storyId`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `taskbacklog_ibfk_1` FOREIGN KEY (`storyId`) REFERENCES `userstory` (`storyId`),
  CONSTRAINT `taskbacklog_ibfk_2` FOREIGN KEY (`memberId`) REFERENCES `teammember` (`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taskbacklog`
--

LOCK TABLES `taskbacklog` WRITE;
/*!40000 ALTER TABLE `taskbacklog` DISABLE KEYS */;
/*!40000 ALTER TABLE `taskbacklog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teammanager`
--

DROP TABLE IF EXISTS `teammanager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teammanager` (
  `managerId` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `userName` varchar(320) NOT NULL,
  `password` char(60) NOT NULL,
  PRIMARY KEY (`managerId`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teammanager`
--

LOCK TABLES `teammanager` WRITE;
/*!40000 ALTER TABLE `teammanager` DISABLE KEYS */;
/*!40000 ALTER TABLE `teammanager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teammember`
--

DROP TABLE IF EXISTS `teammember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teammember` (
  `memberId` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  PRIMARY KEY (`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teammember`
--

LOCK TABLES `teammember` WRITE;
/*!40000 ALTER TABLE `teammember` DISABLE KEYS */;
/*!40000 ALTER TABLE `teammember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userstory`
--

DROP TABLE IF EXISTS `userstory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userstory` (
  `storyId` int NOT NULL AUTO_INCREMENT,
  `projectId` int DEFAULT NULL,
  `typeOfUser` varchar(255) DEFAULT NULL,
  `task` varchar(200) DEFAULT NULL,
  `priorityLevel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`storyId`),
  KEY `projectId` (`projectId`),
  CONSTRAINT `userstory_ibfk_1` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userstory`
--

LOCK TABLES `userstory` WRITE;
/*!40000 ALTER TABLE `userstory` DISABLE KEYS */;
/*!40000 ALTER TABLE `userstory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-18 10:11:35
