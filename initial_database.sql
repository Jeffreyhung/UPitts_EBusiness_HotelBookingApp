-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: roomdb
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookinginfo`
--

DROP TABLE IF EXISTS `bookinginfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bookinginfo` (
  `BID` int(11) NOT NULL,
  `CID` int(11) DEFAULT NULL,
  `RID` int(11) DEFAULT NULL,
  `Start_Date` date DEFAULT NULL,
  `End_Date` date DEFAULT NULL,
  PRIMARY KEY (`BID`),
  KEY `CID_idx` (`CID`),
  KEY `RID_idx` (`RID`),
  CONSTRAINT `CID` FOREIGN KEY (`CID`) REFERENCES `customer` (`cid`),
  CONSTRAINT `RID` FOREIGN KEY (`RID`) REFERENCES `room` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookinginfo`
--

LOCK TABLES `bookinginfo` WRITE;
/*!40000 ALTER TABLE `bookinginfo` DISABLE KEYS */;
INSERT INTO `bookinginfo` VALUES (1,1,1,'2019-04-01','2019-04-05'),(2,2,11,'2019-04-02','2019-04-09'),(3,3,21,'2019-04-01','2019-04-03'),(4,5,2,'2019-04-06','2019-04-07'),(5,6,3,'2019-04-06','2019-04-08'),(6,7,12,'2019-04-07','2019-04-10'),(7,4,14,'2019-04-02','2019-04-07');
/*!40000 ALTER TABLE `bookinginfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `CID` int(11) NOT NULL,
  `BookingName` varchar(45) DEFAULT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `PhoneNum` varchar(10) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Jeffrey','Carol','Chang','5700 Centre Ave.','4121234567','oim@gmail.com'),(2,'Paul','David','Lin','498 Penn Ave','1349380788','dvl@yahoo.com.tw'),(3,'Beth','Kimberly','Wang','87 Liberty Ave','7886273566','kww87@gmail.com'),(4,'Ashley','Ashley','Chao','186 Webster Ave','2918883076','ashley@gmail.com'),(5,'Ben','Ben','Affleck','76 Wylie Ave','2866668779','08batman@hotmail.com'),(6,'Isaiah Hall','Isaiah','Hall','381 Bryant St.','4871036830','hall87@hotmail.com'),(7,'Kathy Burton','Kevin','Burton','72 Hampton St.','9173336940','nottim@yahoo.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `room` (
  `RID` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Capacity` int(11) DEFAULT NULL,
  `NumOfBeds` int(11) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  PRIMARY KEY (`RID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'Deluxe Double',2,1,420),(2,'Deluxe Double',2,1,420),(3,'Deluxe Double',2,1,420),(4,'Deluxe Double',2,1,420),(5,'Deluxe Double',2,1,420),(6,'Deluxe Double',2,1,420),(7,'Deluxe Double',2,1,420),(8,'Deluxe Double',2,1,420),(9,'Deluxe Double',2,1,420),(10,'Deluxe Double',2,1,420),(11,'Business Room',2,2,450),(12,'Business Room',2,2,450),(13,'Business Room',2,2,450),(14,'Business Room',2,2,450),(15,'Business Room',2,2,450),(16,'Business Room',2,2,450),(17,'Business Room',2,2,450),(18,'Business Room',2,2,450),(19,'Business Room',2,2,450),(20,'Business Room',2,2,450),(21,'King Suite',4,2,550),(22,'King Suite',4,2,550),(23,'King Suite',4,2,550),(24,'King Suite',4,2,550),(25,'King Suite',4,2,550),(26,'Premuim King ',4,4,600),(27,'Premuim King ',4,4,600),(28,'Premuim King ',4,4,600),(29,'Premuim King ',4,4,600),(30,'Premuim King ',4,4,600);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-27 16:28:06
