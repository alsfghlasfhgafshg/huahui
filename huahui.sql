-- MySQL dump 10.15  Distrib 10.0.38-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: huahui
-- ------------------------------------------------------
-- Server version	10.0.38-MariaDB-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand` (
  `brandid` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`brandid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (11,'testbrand','no'),(15,'testbrand1des',NULL),(16,'1234567',NULL),(17,'123',NULL),(18,'1111',NULL),(19,'1111',NULL),(20,'qaqa',NULL),(21,'esdfgdf','/file/img/2a32928e-cd81-4f46-8ac2-09b9a93fe89c.jpg'),(34,'qwerty','/file/img/30cc1d0b-4105-420a-8427-c49ad4deff40.jpg');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brandid` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (18,19,'面部'),(19,19,'身体'),(20,19,'产品'),(21,19,'现金卡项目'),(22,20,'面部'),(23,20,'身体'),(24,20,'产品'),(25,20,'现金卡项目'),(26,21,'面部'),(27,21,'身体'),(28,21,'产品'),(29,21,'现金卡项目'),(30,11,'1'),(31,11,'test'),(32,11,'test2'),(33,11,'test2e'),(39,121,'实操类'),(40,121,'产品类'),(41,121,'现金类'),(42,121,'实耗类'),(43,121,'赠送类'),(45,34,'实操类'),(46,34,'产品类'),(47,34,'现金类'),(48,34,'实耗类'),(49,34,'赠送类');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category2`
--

DROP TABLE IF EXISTS `category2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category2` (
  `category2id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryid` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`category2id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category2`
--

LOCK TABLES `category2` WRITE;
/*!40000 ALTER TABLE `category2` DISABLE KEYS */;
INSERT INTO `category2` VALUES (12,33,'为杀人犯'),(13,39,'美容'),(14,39,'美体'),(15,39,'仪器'),(16,40,'美容'),(17,40,'美容'),(18,40,'美容'),(19,41,'现金产品'),(20,41,'现金卡'),(21,41,'现金实操'),(22,42,'卡扣产品'),(23,42,'卡扣实操'),(24,42,'现金产品'),(25,42,'现金实操'),(26,42,'赠送实操'),(27,43,'赠送产品'),(28,43,'赠送实操'),(32,45,'美容'),(33,45,'美体'),(34,45,'仪器'),(35,46,'卡扣产品'),(36,46,'现金产品'),(37,46,'赠送产品'),(38,47,'现金产品'),(39,47,'现金卡'),(40,47,'现金实操'),(41,48,'卡扣产品'),(42,48,'卡扣实操'),(43,48,'现金产品'),(44,48,'现金实操'),(45,48,'赠送实操'),(46,49,'赠送产品'),(47,49,'赠送实操'),(48,33,'tttttt');
/*!40000 ALTER TABLE `category2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familyrelationship`
--

DROP TABLE IF EXISTS `familyrelationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `familyrelationship` (
  `memberid` int(11) NOT NULL AUTO_INCREMENT,
  `staffid` int(11) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `companyname` varchar(20) DEFAULT NULL,
  `relationship` varchar(5) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`memberid`),
  KEY `familyrelationship_staff_staffid_fk` (`staffid`),
  CONSTRAINT `familyrelationship_staff_staffid_fk` FOREIGN KEY (`staffid`) REFERENCES `staff` (`staffid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familyrelationship`
--

LOCK TABLES `familyrelationship` WRITE;
/*!40000 ALTER TABLE `familyrelationship` DISABLE KEYS */;
INSERT INTO `familyrelationship` VALUES (1,28,'dewitt','王潭公司','爸爸',20);
/*!40000 ALTER TABLE `familyrelationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentmethod`
--

DROP TABLE IF EXISTS `paymentmethod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentmethod` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentmethod`
--

LOCK TABLES `paymentmethod` WRITE;
/*!40000 ALTER TABLE `paymentmethod` DISABLE KEYS */;
INSERT INTO `paymentmethod` VALUES (1,'卡扣实操'),(2,'现金实操'),(3,'赠送实操'),(4,'卡扣中胚层'),(5,'现金中胚层'),(6,'赠送中胚层'),(7,'现金卡'),(8,'现金产品'),(9,'卡扣卡');
/*!40000 ALTER TABLE `paymentmethod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) DEFAULT NULL,
  `categoryid` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category2id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,12,'project1'),(2,48,'project2');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reporter`
--

DROP TABLE IF EXISTS `reporter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reporter` (
  `reporterid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `shopid` int(11) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`reporterid`),
  KEY `reporter_shop_shopid_fk` (`shopid`),
  CONSTRAINT `reporter_shop_shopid_fk` FOREIGN KEY (`shopid`) REFERENCES `shop` (`shopid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporter`
--

LOCK TABLES `reporter` WRITE;
/*!40000 ALTER TABLE `reporter` DISABLE KEYS */;
/*!40000 ALTER TABLE `reporter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_BRAND'),(3,'ROLE_SHOP'),(4,'ROLE_STAFF');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopid` int(11) NOT NULL,
  `roomname` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,12,'room1');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settlement`
--

DROP TABLE IF EXISTS `settlement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settlement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopid` int(11) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `customername` varchar(10) DEFAULT NULL,
  `paymentmethod` int(11) DEFAULT NULL,
  `roomname` varchar(20) DEFAULT NULL,
  `consultantid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settlement`
--

LOCK TABLES `settlement` WRITE;
/*!40000 ALTER TABLE `settlement` DISABLE KEYS */;
INSERT INTO `settlement` VALUES (2,12,'2019-09-26 07:00:02','dsf',1,'1',32),(3,12,'2019-07-05 07:00:00','112',NULL,NULL,NULL),(4,11,'2019-07-05 07:00:00','11',NULL,NULL,NULL),(5,12,'2019-07-07 07:00:00','11',1,'1',32);
/*!40000 ALTER TABLE `settlement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settlementitem`
--

DROP TABLE IF EXISTS `settlementitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settlementitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `settlementid` int(11) NOT NULL,
  `category2id` int(11) NOT NULL,
  `times` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `staff1` int(11) DEFAULT NULL,
  `staff2` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settlementitem`
--

LOCK TABLES `settlementitem` WRITE;
/*!40000 ALTER TABLE `settlementitem` DISABLE KEYS */;
INSERT INTO `settlementitem` VALUES (2,2,12,2,2,28,28),(3,2,12,23,23,28,28),(4,5,48,156,45,28,28);
/*!40000 ALTER TABLE `settlementitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop` (
  `shopid` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `geo` varchar(200) DEFAULT NULL,
  `brandid` int(11) NOT NULL,
  PRIMARY KEY (`shopid`,`brandid`),
  KEY `shop_brand_id_fk` (`brandid`),
  CONSTRAINT `shop_brand_id_fk` FOREIGN KEY (`brandid`) REFERENCES `brand` (`brandid`),
  CONSTRAINT `shop_user_id_fk` FOREIGN KEY (`shopid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (12,'erawer','asdf',11),(22,'dewitttest','baoding',11),(29,'testaddshop','testgeo',11);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `staffid` int(11) NOT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `male` tinyint(4) NOT NULL DEFAULT '0',
  `birthday` date DEFAULT NULL,
  `nation` varchar(50) DEFAULT NULL,
  `party` varchar(20) DEFAULT NULL,
  `healthy` varchar(20) DEFAULT NULL,
  `nativeplace` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `emergencyphone` varchar(50) DEFAULT NULL,
  `p1name` varchar(255) DEFAULT NULL,
  `p1male` tinyint(4) DEFAULT '0',
  `p1company` varchar(255) DEFAULT NULL,
  `p1relationship` varchar(255) DEFAULT NULL,
  `p2name` varchar(255) DEFAULT NULL,
  `p2male` tinyint(4) DEFAULT '0',
  `p2company` varchar(255) DEFAULT NULL,
  `p2relationship` varchar(255) DEFAULT NULL,
  `shopid` int(11) NOT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`staffid`,`shopid`),
  KEY `staff_shop_id_fk` (`shopid`),
  CONSTRAINT `staff_shop_id_fk` FOREIGN KEY (`shopid`) REFERENCES `shop` (`shopid`),
  CONSTRAINT `staff_user_id_fk` FOREIGN KEY (`staffid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (28,'123456','dewittshop',0,'1998-02-20','zhongguo','共产党','良好','河北','保定','15603382139','15603382139',NULL,0,NULL,NULL,NULL,0,NULL,NULL,22,'beautician'),(32,NULL,'java11',0,'1998-02-10','zhongguo','共产党','良好','河北','保定','15603382139','15603382139','baba',0,'gongsi','fuzi','mama',1,'gongsi','muzi',22,'consultant');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `password` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$04$ZbL9Wvz59CIw4spk9flVcuKZvWCsFNZpuQUdqfDEvYlpWJp/Ppk9u'),(11,'brand','$2a$04$d/hKg5I7TREQWHPON49m4eyTaa.fjE5UOOpHxXBbRdgt2RNXA.r9.'),(12,'shop','$2a$04$ZbL9Wvz59CIw4spk9flVcuKZvWCsFNZpuQUdqfDEvYlpWJp/Ppk9u'),(19,'1111','$2a$04$oSNRe5GMB0.Bg/Z2CWjwhOhjCZp4IxWGG62U0SZyWkTRGkJH4Rnr2'),(20,'qaqa','$2a$04$Fwdg2Wzrf6CJtiQw2aebseYhb0sOiykv.E04g/j0r.VY8sSMXsHO6'),(21,'qw','$2a$04$CNvmfrVYz/R5ARnx8ZzTnu2/MPxNt8rpBOkw177rOtMgvh.R6GoCO'),(22,'dewitt','$2a$04$0vZDXb.C1iFz/zAmayKzD.O0fB3CmuDwo4dkBxDAI1LqnEd0JEdDq'),(28,'dewittdeshop','$2a$04$Tv0ZJr2eQ5TwZdunZM1Y8.Qb7ba8TwMO9/vhcLdN.SAi1wc9ytG8q'),(29,'testname','$2a$04$.bB4ZvGS.nLfR9lah3Z37udq2gVRCTPF9ZT/JgcONtYjrQlurGKSG'),(30,'testname2','$2a$04$OHBeVsNZUFeEJdmp9.SQseFFVwFMLDIPPMvlSKn0dSy1u2HIY18re'),(31,'MIKE',''),(32,'MIKE11',''),(33,'testbrand111','$2a$04$8blOKKby1l2PnPf53fwLY.To.E/IvwhSxTulP5LyDuPLC4AsnDrym'),(34,'qwerty','$2a$04$PNHccaZfXJ7VFoh/46X35.BxljkPGDjC5JJJSpdw9IlSd4fLwO7Ra');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(11,2),(12,3),(19,2),(20,2),(21,2),(22,3),(28,4),(29,3),(30,3),(31,4),(32,4),(33,2),(34,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_user`
--

DROP TABLE IF EXISTS `wx_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_user` (
  `userid` int(11) NOT NULL,
  `wxopenid` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`userid`,`wxopenid`),
  CONSTRAINT `wx_user_user_id_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_user`
--

LOCK TABLES `wx_user` WRITE;
/*!40000 ALTER TABLE `wx_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-17 11:07:28
