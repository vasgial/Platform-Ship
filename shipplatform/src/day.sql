-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: ship_platform
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auto`
--

DROP TABLE IF EXISTS `auto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `auto` (
  `auto_id` int(5) NOT NULL AUTO_INCREMENT,
  `lane_id` int(2) NOT NULL,
  `length` decimal(6,2) NOT NULL,
  `number_plate` varchar(10) NOT NULL,
  `auto_type` int(1) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  PRIMARY KEY (`auto_id`),
  KEY `lane_fk_auto` (`lane_id`),
  KEY `auto_fk_type` (`auto_type`),
  CONSTRAINT `auto_fk_type` FOREIGN KEY (`auto_type`) REFERENCES `auto_type` (`auto_type`),
  CONSTRAINT `lane_fk_auto` FOREIGN KEY (`lane_id`) REFERENCES `lanes` (`lane_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto`
--

LOCK TABLES `auto` WRITE;
/*!40000 ALTER TABLE `auto` DISABLE KEYS */;
INSERT INTO `auto` VALUES (1,1,4.49,'QA1204',1,15.00),(2,2,4.09,'WS9174',1,15.00),(3,3,4.95,'GD5178',1,15.00),(4,4,5.23,'SG8405',1,15.00),(5,2,4.03,'DQ7555',1,15.00);
/*!40000 ALTER TABLE `auto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auto_passengers`
--

DROP TABLE IF EXISTS `auto_passengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `auto_passengers` (
  `passenger_id` int(3) NOT NULL AUTO_INCREMENT,
  `auto_id` int(5) NOT NULL,
  `passenger_type` int(1) NOT NULL,
  PRIMARY KEY (`passenger_id`),
  KEY `auto_passengers_fk_passenger_type` (`passenger_type`),
  KEY `auto_fk_auto_passenger` (`auto_id`),
  CONSTRAINT `auto_fk_auto_passenger` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`auto_id`),
  CONSTRAINT `auto_passengers_fk_passenger_type` FOREIGN KEY (`passenger_type`) REFERENCES `passenger_type` (`passenger_type`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto_passengers`
--

LOCK TABLES `auto_passengers` WRITE;
/*!40000 ALTER TABLE `auto_passengers` DISABLE KEYS */;
INSERT INTO `auto_passengers` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,5),(5,1,5),(6,2,4),(7,2,2),(8,2,1),(9,2,2),(10,3,4),(11,3,1),(12,3,5),(13,4,1),(14,4,5),(15,4,2),(16,4,5),(17,5,2),(18,5,5),(19,5,3);
/*!40000 ALTER TABLE `auto_passengers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auto_type`
--

DROP TABLE IF EXISTS `auto_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `auto_type` (
  `auto_type` int(1) NOT NULL AUTO_INCREMENT,
  `passenger_capacity` int(3) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  PRIMARY KEY (`auto_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto_type`
--

LOCK TABLES `auto_type` WRITE;
/*!40000 ALTER TABLE `auto_type` DISABLE KEYS */;
INSERT INTO `auto_type` VALUES (1,5,15.00),(2,2,45.00);
/*!40000 ALTER TABLE `auto_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lanes`
--

DROP TABLE IF EXISTS `lanes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lanes` (
  `lane_id` int(1) NOT NULL AUTO_INCREMENT,
  `ship_id` int(1) NOT NULL,
  `lane_index` int(1) NOT NULL,
  PRIMARY KEY (`lane_id`),
  KEY `ship_fk_lane` (`ship_id`),
  CONSTRAINT `ship_fk_lane` FOREIGN KEY (`ship_id`) REFERENCES `ship` (`ship_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lanes`
--

LOCK TABLES `lanes` WRITE;
/*!40000 ALTER TABLE `lanes` DISABLE KEYS */;
INSERT INTO `lanes` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,1),(6,2,2),(7,2,3),(8,2,4),(9,3,1),(10,3,2),(11,3,3);
/*!40000 ALTER TABLE `lanes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moto`
--

DROP TABLE IF EXISTS `moto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `moto` (
  `moto_id` int(2) NOT NULL AUTO_INCREMENT,
  `ship_id` int(1) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  `number_plate` varchar(10) NOT NULL,
  `passenger_capacity` int(1) NOT NULL,
  PRIMARY KEY (`moto_id`),
  KEY `moto_fk_ship` (`ship_id`),
  CONSTRAINT `moto_fk_ship` FOREIGN KEY (`ship_id`) REFERENCES `ship` (`ship_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moto`
--

LOCK TABLES `moto` WRITE;
/*!40000 ALTER TABLE `moto` DISABLE KEYS */;
INSERT INTO `moto` VALUES (1,1,5.00,'CI2136',2),(2,1,5.00,'RH2098',2),(3,1,5.00,'PD9701',2),(4,1,5.00,'RA1514',2),(5,1,5.00,'OW2125',2);
/*!40000 ALTER TABLE `moto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moto_passengers`
--

DROP TABLE IF EXISTS `moto_passengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `moto_passengers` (
  `passenger_id` int(3) NOT NULL AUTO_INCREMENT,
  `moto_id` int(2) NOT NULL,
  `passenger_type` int(1) NOT NULL,
  PRIMARY KEY (`passenger_id`),
  KEY `moto_passengers_fk_passenger_type` (`passenger_type`),
  KEY `moto_passengers_fk_moto` (`moto_id`),
  CONSTRAINT `moto_passengers_fk_moto` FOREIGN KEY (`moto_id`) REFERENCES `moto` (`moto_id`),
  CONSTRAINT `moto_passengers_fk_passenger_type` FOREIGN KEY (`passenger_type`) REFERENCES `passenger_type` (`passenger_type`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moto_passengers`
--

LOCK TABLES `moto_passengers` WRITE;
/*!40000 ALTER TABLE `moto_passengers` DISABLE KEYS */;
INSERT INTO `moto_passengers` VALUES (1,1,3),(2,2,3),(3,3,5),(4,4,2),(5,4,2),(6,5,4),(7,5,5);
/*!40000 ALTER TABLE `moto_passengers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger_type`
--

DROP TABLE IF EXISTS `passenger_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `passenger_type` (
  `passenger_type` int(1) NOT NULL AUTO_INCREMENT,
  `price` decimal(6,2) NOT NULL,
  PRIMARY KEY (`passenger_type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger_type`
--

LOCK TABLES `passenger_type` WRITE;
/*!40000 ALTER TABLE `passenger_type` DISABLE KEYS */;
INSERT INTO `passenger_type` VALUES (1,5.00),(2,2.50),(3,2.00),(4,2.00),(5,0.00);
/*!40000 ALTER TABLE `passenger_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passengers`
--

DROP TABLE IF EXISTS `passengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `passengers` (
  `passenger_id` int(3) NOT NULL AUTO_INCREMENT,
  `ship_id` int(1) NOT NULL,
  `passenger_type` int(1) NOT NULL,
  PRIMARY KEY (`passenger_id`),
  KEY `passengers_fk_passenger_type` (`passenger_type`),
  KEY `passengers_fk_ship` (`ship_id`),
  CONSTRAINT `passengers_fk_passenger_type` FOREIGN KEY (`passenger_type`) REFERENCES `passenger_type` (`passenger_type`),
  CONSTRAINT `passengers_fk_ship` FOREIGN KEY (`ship_id`) REFERENCES `ship` (`ship_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passengers`
--

LOCK TABLES `passengers` WRITE;
/*!40000 ALTER TABLE `passengers` DISABLE KEYS */;
INSERT INTO `passengers` VALUES (1,1,1),(2,1,2),(3,1,5),(4,1,2),(5,1,3);
/*!40000 ALTER TABLE `passengers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ship`
--

DROP TABLE IF EXISTS `ship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ship` (
  `ship_id` int(1) NOT NULL AUTO_INCREMENT,
  `name` varchar(7) NOT NULL,
  `moto_capacity` int(2) NOT NULL,
  `length` decimal(6,2) NOT NULL,
  `total_lanes` int(1) NOT NULL,
  `passenger_capacity` int(3) NOT NULL,
  PRIMARY KEY (`ship_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ship`
--

LOCK TABLES `ship` WRITE;
/*!40000 ALTER TABLE `ship` DISABLE KEYS */;
INSERT INTO `ship` VALUES (1,'Myrto',30,50.00,4,300),(2,'Artemis',20,40.00,4,220),(3,'Eirini',14,37.00,3,170);
/*!40000 ALTER TABLE `ship` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-16  2:41:01
