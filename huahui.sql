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
-- Table structure for table `analysisintermediatetable`
--

DROP TABLE IF EXISTS `analysisintermediatetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `analysisintermediatetable` (
  `category` varchar(11) DEFAULT NULL,
  `category2` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `analysisintermediatetable`
--

LOCK TABLES `analysisintermediatetable` WRITE;
/*!40000 ALTER TABLE `analysisintermediatetable` DISABLE KEYS */;
INSERT INTO `analysisintermediatetable` VALUES ('实操类','美容'),('实操类','美体'),('实操类','仪器'),('实操类','总计'),('产品类','卡扣产品'),('产品类','现金产品'),('产品类','赠送产品'),('产品类','总计'),('现金类','现金产品'),('现金类','现金卡'),('现金类','现金实操'),('现金类','总计'),('实耗类','卡扣产品'),('实耗类','卡扣实操'),('实耗类','现金产品'),('实耗类','现金实操'),('实耗类','赠送实操'),('实耗类','总计'),('赠送类','赠送产品'),('赠送类','赠送实操'),('赠送类','总计');
/*!40000 ALTER TABLE `analysisintermediatetable` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `brand` VALUES (11,'testbrand','no'),(15,'testbrand1des',NULL),(16,'1234567',NULL),(17,'123',NULL),(18,'1111',NULL),(19,'1111',NULL),(20,'qaqa',NULL),(21,'esdfgdf','/file/img/2a32928e-cd81-4f46-8ac2-09b9a93fe89c.jpg'),(34,'qwerty','/file/img/30cc1d0b-4105-420a-8427-c49ad4deff40.jpg'),(64,'qqqqqq','/file/img/ed3e3290-6a25-4997-aff2-908a9c5ee0ac.png'),(65,'qqqqqq','/file/img/43133ee4-6a52-428d-b652-6aa425ffc5de.png'),(66,'asasas','/file/img/b128d1b2-4114-418b-9e81-237bbdcc6985.png'),(69,'sdafdfsadfsa','/file/img/bfc9450b-5f87-45ae-887b-69eebd212d46.png'),(70,'爱对方水电费','/file/img/1f34e023-4621-41db-8800-64132f132c6e.png'),(75,'打发水电费第三方','/file/img/1a906fa8-19b8-4631-9c75-040f4b939524.jpg'),(76,'qqqqqq','/file/img/3d996342-4c02-4147-8b3e-c481d4ae61e0.png'),(77,'qqqqqq','/file/img/e2f09e69-749f-4fec-b105-9882797015ff.png'),(79,'qqqqqq','/file/img/e646c9c3-253b-406e-8858-a23f726a6b7b.png'),(80,'撒地方撒旦','/file/img/36280d4c-be48-47be-b4ea-162c84534b44.jpg'),(88,'花惠建设大街花惠建设大街','/file/img/9f0eab67-8050-4b87-937e-8ffdc9adbca8.jpg'),(89,'说的','/file/img/1a406f6f-6919-4f69-9246-e951db11d8c5.png'),(94,'sdfa','/file/img/67f935a6-0b20-4595-954c-3e907f952883.png'),(95,'sdfsadfds','/file/img/54f9c8fa-f2cf-4d21-b2e2-aff970713849.png'),(98,'adfsd','/file/img/07010fd1-f69e-4c8c-9382-6719fd436682.png'),(99,'sdfdsf','/file/img/593ca593-c720-4a30-bb0f-8e42cf869f12.png'),(102,'sdfsdf','/file/img/124d9e50-2507-40e0-b7d8-c9b550399f1b.png'),(104,'dsfsdf','/file/img/4fc1233d-d844-4523-b647-f1a931a72d2c.png'),(105,'erwt','/file/img/fe6bee39-a475-490d-95cd-b20da6f59162.png'),(115,'123','/file/img/ec5a1baa-8575-429b-98bc-53664299c42f.jpg'),(117,'sdfsdfdsf','/file/img/624da062-e631-44b1-a3a7-82a781b49d64.png'),(118,'adsfasdf','/file/img/effaa22e-a1e0-44a3-acfa-46b61475b281.jpg'),(120,'管理','/file/img/af8e9a3e-d14b-49f6-9f8f-da95b74bbede.jpg'),(122,'brand','/file/img/804e58d4-5bc8-4165-9f03-a79ada1be9c1.jpg'),(123,'阿斯蒂芬','/file/img/6c6ffe69-e339-4906-a5eb-35730323f1e0.png'),(125,'品牌','/file/img/71441409-6a33-4c80-96ea-d7b3e6e1010b.jpg'),(129,'brand','/file/img/1672525d-2bf2-4c59-bf76-4a148dbe5972.jpg'),(130,'asdf','/file/img/968c89c5-a0de-4d7b-9510-582157d6d785.png'),(131,'sdf','/file/img/6e516b93-dd0c-4929-91d4-61229194877c.png'),(132,'sdfsd','/file/img/4fde57f8-68cb-4629-93ff-3903e3c44aac.png'),(133,'sdfsd','/file/img/58d4a06d-8218-4561-8e18-61b5c2505ce3.png'),(154,'花惠','/file/img/578a04bc-5249-432d-81f2-772b157d5fb7.png'),(157,'dsfsd','/file/img/019c41b4-ad9b-48f1-8f49-028c209c6471.png'),(163,'sdf','/file/img/7ca2f020-3dac-40bd-b7e2-0eefb373faa7.png'),(164,'asdfasdfasdf','/file/img/8f43a86d-1358-4939-bcdb-f29f9e4c92da.png'),(170,'dfeasdfgsd','/file/img/26438a70-8846-485d-9d15-1ce419c57f5d.png'),(171,'asdgasdgf','/file/img/5e01593b-a435-4ab2-bfdc-0f2d2fc3848a.png'),(173,'sdfgsadfg','/file/img/2632b58e-e02d-4214-8fae-6b0abbb72b7f.png'),(184,'ddddddddddddd','/file/img/25f87ea9-e27a-4436-a0a7-a8074b3dd864.jpg'),(187,'asdg','/file/img/a12dea1e-749f-428d-8837-6f29fbf18ce6.jpg'),(188,'tttttttttt','/file/img/afb35afc-416b-4680-94da-2ae28f997932.jpg'),(189,'asdfgasdg阿萨德','/file/img/fc61e4ed-f1e6-45f0-950d-e00bfc66b4da.jpg'),(190,'asdfgasdgasdfg','/file/img/939b3725-67f7-4061-ab1c-1c2cf0504df7.png');
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
) ENGINE=InnoDB AUTO_INCREMENT=280 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (50,11,'实操类'),(51,11,'产品类'),(52,11,'现金类'),(53,11,'实耗类'),(54,11,'赠送类'),(55,64,'实操类'),(56,64,'产品类'),(57,64,'现金类'),(58,64,'实耗类'),(59,64,'赠送类'),(60,65,'实操类'),(61,65,'产品类'),(62,65,'现金类'),(63,65,'实耗类'),(64,65,'赠送类'),(65,66,'实操类'),(66,66,'产品类'),(67,66,'现金类'),(68,66,'实耗类'),(69,66,'赠送类'),(70,69,'实操类'),(71,69,'产品类'),(72,69,'现金类'),(73,69,'实耗类'),(74,69,'赠送类'),(75,70,'实操类'),(76,70,'产品类'),(77,70,'现金类'),(78,70,'实耗类'),(79,70,'赠送类'),(80,75,'实操类'),(81,75,'产品类'),(82,75,'现金类'),(83,75,'实耗类'),(84,75,'赠送类'),(85,76,'实操类'),(86,76,'产品类'),(87,76,'现金类'),(88,76,'实耗类'),(89,76,'赠送类'),(90,77,'实操类'),(91,77,'产品类'),(92,77,'现金类'),(93,77,'实耗类'),(94,77,'赠送类'),(95,79,'实操类'),(96,79,'产品类'),(97,79,'现金类'),(98,79,'实耗类'),(99,79,'赠送类'),(100,80,'实操类'),(101,80,'产品类'),(102,80,'现金类'),(103,80,'实耗类'),(104,80,'赠送类'),(105,88,'实操类'),(106,88,'产品类'),(107,88,'现金类'),(108,88,'实耗类'),(109,88,'赠送类'),(110,89,'实操类'),(111,89,'产品类'),(112,89,'现金类'),(113,89,'实耗类'),(114,89,'赠送类'),(115,94,'实操类'),(116,94,'产品类'),(117,94,'现金类'),(118,94,'实耗类'),(119,94,'赠送类'),(120,95,'实操类'),(121,95,'产品类'),(122,95,'现金类'),(123,95,'实耗类'),(124,95,'赠送类'),(125,98,'实操类'),(126,98,'产品类'),(127,98,'现金类'),(128,98,'实耗类'),(129,98,'赠送类'),(130,99,'实操类'),(131,99,'产品类'),(132,99,'现金类'),(133,99,'实耗类'),(134,99,'赠送类'),(135,102,'实操类'),(136,102,'产品类'),(137,102,'现金类'),(138,102,'实耗类'),(139,102,'赠送类'),(140,104,'实操类'),(141,104,'产品类'),(142,104,'现金类'),(143,104,'实耗类'),(144,104,'赠送类'),(145,105,'实操类'),(146,105,'产品类'),(147,105,'现金类'),(148,105,'实耗类'),(149,105,'赠送类'),(150,115,'实操类'),(151,115,'产品类'),(152,115,'现金类'),(153,115,'实耗类'),(154,115,'赠送类'),(155,117,'实操类'),(156,117,'产品类'),(157,117,'现金类'),(158,117,'实耗类'),(159,117,'赠送类'),(160,118,'实操类'),(161,118,'产品类'),(162,118,'现金类'),(163,118,'实耗类'),(164,118,'赠送类'),(165,120,'实操类'),(166,120,'产品类'),(167,120,'现金类'),(168,120,'实耗类'),(169,120,'赠送类'),(170,122,'实操类'),(171,122,'产品类'),(172,122,'现金类'),(173,122,'实耗类'),(174,122,'赠送类'),(175,123,'实操类'),(176,123,'产品类'),(177,123,'现金类'),(178,123,'实耗类'),(179,123,'赠送类'),(180,125,'实操类'),(181,125,'产品类'),(182,125,'现金类'),(183,125,'实耗类'),(184,125,'赠送类'),(185,129,'实操类'),(186,129,'产品类'),(187,129,'现金类'),(188,129,'实耗类'),(189,129,'赠送类'),(190,130,'实操类'),(191,130,'产品类'),(192,130,'现金类'),(193,130,'实耗类'),(194,130,'赠送类'),(195,131,'实操类'),(196,131,'产品类'),(197,131,'现金类'),(198,131,'实耗类'),(199,131,'赠送类'),(200,132,'实操类'),(201,132,'产品类'),(202,132,'现金类'),(203,132,'实耗类'),(204,132,'赠送类'),(205,133,'实操类'),(206,133,'产品类'),(207,133,'现金类'),(208,133,'实耗类'),(209,133,'赠送类'),(210,154,'实操类'),(211,154,'产品类'),(212,154,'现金类'),(213,154,'实耗类'),(214,154,'赠送类'),(215,157,'实操类'),(216,157,'产品类'),(217,157,'现金类'),(218,157,'实耗类'),(219,157,'赠送类'),(220,163,'实操类'),(221,163,'产品类'),(222,163,'现金类'),(223,163,'实耗类'),(224,163,'赠送类'),(225,164,'实操类'),(226,164,'产品类'),(227,164,'现金类'),(228,164,'实耗类'),(229,164,'赠送类'),(230,170,'实操类'),(231,170,'产品类'),(232,170,'现金类'),(233,170,'实耗类'),(234,170,'赠送类'),(235,171,'实操类'),(236,171,'产品类'),(237,171,'现金类'),(238,171,'实耗类'),(239,171,'赠送类'),(240,173,'实操类'),(241,173,'产品类'),(242,173,'现金类'),(243,173,'实耗类'),(244,173,'赠送类'),(245,184,'实操类'),(246,184,'产品类'),(247,184,'现金类'),(248,184,'实耗类'),(249,184,'赠送类'),(250,185,'实操类'),(251,185,'产品类'),(252,185,'现金类'),(253,185,'实耗类'),(254,185,'赠送类'),(255,186,'实操类'),(256,186,'产品类'),(257,186,'现金类'),(258,186,'实耗类'),(259,186,'赠送类'),(260,187,'实操类'),(261,187,'产品类'),(262,187,'现金类'),(263,187,'实耗类'),(264,187,'赠送类'),(265,188,'实操类'),(266,188,'产品类'),(267,188,'现金类'),(268,188,'实耗类'),(269,188,'赠送类'),(270,189,'实操类'),(271,189,'产品类'),(272,189,'现金类'),(273,189,'实耗类'),(274,189,'赠送类'),(275,190,'实操类'),(276,190,'产品类'),(277,190,'现金类'),(278,190,'实耗类'),(279,190,'赠送类');
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
) ENGINE=InnoDB AUTO_INCREMENT=785 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category2`
--

LOCK TABLES `category2` WRITE;
/*!40000 ALTER TABLE `category2` DISABLE KEYS */;
INSERT INTO `category2` VALUES (49,50,'美容'),(50,50,'美体'),(51,50,'仪器'),(52,51,'卡扣产品'),(53,51,'现金产品'),(54,51,'赠送产品'),(55,52,'现金产品'),(56,52,'现金卡'),(57,52,'现金实操'),(58,53,'卡扣产品'),(59,53,'卡扣实操'),(60,53,'现金产品'),(61,53,'现金实操'),(62,53,'赠送实操'),(63,54,'赠送产品'),(64,54,'赠送实操'),(65,55,'美容'),(66,55,'美体'),(67,55,'仪器'),(68,56,'卡扣产品'),(69,56,'现金产品'),(70,56,'赠送产品'),(71,57,'现金产品'),(72,57,'现金卡'),(73,57,'现金实操'),(74,58,'卡扣产品'),(75,58,'卡扣实操'),(76,58,'现金产品'),(77,58,'现金实操'),(78,58,'赠送实操'),(79,59,'赠送产品'),(80,59,'赠送实操'),(81,60,'美容'),(82,60,'美体'),(83,60,'仪器'),(84,61,'卡扣产品'),(85,61,'现金产品'),(86,61,'赠送产品'),(87,62,'现金产品'),(88,62,'现金卡'),(89,62,'现金实操'),(90,63,'卡扣产品'),(91,63,'卡扣实操'),(92,63,'现金产品'),(93,63,'现金实操'),(94,63,'赠送实操'),(95,64,'赠送产品'),(96,64,'赠送实操'),(97,65,'美容'),(98,65,'美体'),(99,65,'仪器'),(100,66,'卡扣产品'),(101,66,'现金产品'),(102,66,'赠送产品'),(103,67,'现金产品'),(104,67,'现金卡'),(105,67,'现金实操'),(106,68,'卡扣产品'),(107,68,'卡扣实操'),(108,68,'现金产品'),(109,68,'现金实操'),(110,68,'赠送实操'),(111,69,'赠送产品'),(112,69,'赠送实操'),(113,70,'美容'),(114,70,'美体'),(115,70,'仪器'),(116,71,'卡扣产品'),(117,71,'现金产品'),(118,71,'赠送产品'),(119,72,'现金产品'),(120,72,'现金卡'),(121,72,'现金实操'),(122,73,'卡扣产品'),(123,73,'卡扣实操'),(124,73,'现金产品'),(125,73,'现金实操'),(126,73,'赠送实操'),(127,74,'赠送产品'),(128,74,'赠送实操'),(129,75,'美容'),(130,75,'美体'),(131,75,'仪器'),(132,76,'卡扣产品'),(133,76,'现金产品'),(134,76,'赠送产品'),(135,77,'现金产品'),(136,77,'现金卡'),(137,77,'现金实操'),(138,78,'卡扣产品'),(139,78,'卡扣实操'),(140,78,'现金产品'),(141,78,'现金实操'),(142,78,'赠送实操'),(143,79,'赠送产品'),(144,79,'赠送实操'),(145,80,'美容'),(146,80,'美体'),(147,80,'仪器'),(148,81,'卡扣产品'),(149,81,'现金产品'),(150,81,'赠送产品'),(151,82,'现金产品'),(152,82,'现金卡'),(153,82,'现金实操'),(154,83,'卡扣产品'),(155,83,'卡扣实操'),(156,83,'现金产品'),(157,83,'现金实操'),(158,83,'赠送实操'),(159,84,'赠送产品'),(160,84,'赠送实操'),(161,85,'美容'),(162,85,'美体'),(163,85,'仪器'),(164,86,'卡扣产品'),(165,86,'现金产品'),(166,86,'赠送产品'),(167,87,'现金产品'),(168,87,'现金卡'),(169,87,'现金实操'),(170,88,'卡扣产品'),(171,88,'卡扣实操'),(172,88,'现金产品'),(173,88,'现金实操'),(174,88,'赠送实操'),(175,89,'赠送产品'),(176,89,'赠送实操'),(177,90,'美容'),(178,90,'美体'),(179,90,'仪器'),(180,91,'卡扣产品'),(181,91,'现金产品'),(182,91,'赠送产品'),(183,92,'现金产品'),(184,92,'现金卡'),(185,92,'现金实操'),(186,93,'卡扣产品'),(187,93,'卡扣实操'),(188,93,'现金产品'),(189,93,'现金实操'),(190,93,'赠送实操'),(191,94,'赠送产品'),(192,94,'赠送实操'),(193,95,'美容'),(194,95,'美体'),(195,95,'仪器'),(196,96,'卡扣产品'),(197,96,'现金产品'),(198,96,'赠送产品'),(199,97,'现金产品'),(200,97,'现金卡'),(201,97,'现金实操'),(202,98,'卡扣产品'),(203,98,'卡扣实操'),(204,98,'现金产品'),(205,98,'现金实操'),(206,98,'赠送实操'),(207,99,'赠送产品'),(208,99,'赠送实操'),(209,100,'美容'),(210,100,'美体'),(211,100,'仪器'),(212,101,'卡扣产品'),(213,101,'现金产品'),(214,101,'赠送产品'),(215,102,'现金产品'),(216,102,'现金卡'),(217,102,'现金实操'),(218,103,'卡扣产品'),(219,103,'卡扣实操'),(220,103,'现金产品'),(221,103,'现金实操'),(222,103,'赠送实操'),(223,104,'赠送产品'),(224,104,'赠送实操'),(225,105,'美容'),(226,105,'美体'),(227,105,'仪器'),(228,106,'卡扣产品'),(229,106,'现金产品'),(230,106,'赠送产品'),(231,107,'现金产品'),(232,107,'现金卡'),(233,107,'现金实操'),(234,108,'卡扣产品'),(235,108,'卡扣实操'),(236,108,'现金产品'),(237,108,'现金实操'),(238,108,'赠送实操'),(239,109,'赠送产品'),(240,109,'赠送实操'),(241,110,'美容'),(242,110,'美体'),(243,110,'仪器'),(244,111,'卡扣产品'),(245,111,'现金产品'),(246,111,'赠送产品'),(247,112,'现金产品'),(248,112,'现金卡'),(249,112,'现金实操'),(250,113,'卡扣产品'),(251,113,'卡扣实操'),(252,113,'现金产品'),(253,113,'现金实操'),(254,113,'赠送实操'),(255,114,'赠送产品'),(256,114,'赠送实操'),(257,115,'美容'),(258,115,'美体'),(259,115,'仪器'),(260,116,'卡扣产品'),(261,116,'现金产品'),(262,116,'赠送产品'),(263,117,'现金产品'),(264,117,'现金卡'),(265,117,'现金实操'),(266,118,'卡扣产品'),(267,118,'卡扣实操'),(268,118,'现金产品'),(269,118,'现金实操'),(270,118,'赠送实操'),(271,119,'赠送产品'),(272,119,'赠送实操'),(273,120,'美容'),(274,120,'美体'),(275,120,'仪器'),(276,121,'卡扣产品'),(277,121,'现金产品'),(278,121,'赠送产品'),(279,122,'现金产品'),(280,122,'现金卡'),(281,122,'现金实操'),(282,123,'卡扣产品'),(283,123,'卡扣实操'),(284,123,'现金产品'),(285,123,'现金实操'),(286,123,'赠送实操'),(287,124,'赠送产品'),(288,124,'赠送实操'),(289,125,'美容'),(290,125,'美体'),(291,125,'仪器'),(292,126,'卡扣产品'),(293,126,'现金产品'),(294,126,'赠送产品'),(295,127,'现金产品'),(296,127,'现金卡'),(297,127,'现金实操'),(298,128,'卡扣产品'),(299,128,'卡扣实操'),(300,128,'现金产品'),(301,128,'现金实操'),(302,128,'赠送实操'),(303,129,'赠送产品'),(304,129,'赠送实操'),(305,130,'美容'),(306,130,'美体'),(307,130,'仪器'),(308,131,'卡扣产品'),(309,131,'现金产品'),(310,131,'赠送产品'),(311,132,'现金产品'),(312,132,'现金卡'),(313,132,'现金实操'),(314,133,'卡扣产品'),(315,133,'卡扣实操'),(316,133,'现金产品'),(317,133,'现金实操'),(318,133,'赠送实操'),(319,134,'赠送产品'),(320,134,'赠送实操'),(321,135,'美容'),(322,135,'美体'),(323,135,'仪器'),(324,136,'卡扣产品'),(325,136,'现金产品'),(326,136,'赠送产品'),(327,137,'现金产品'),(328,137,'现金卡'),(329,137,'现金实操'),(330,138,'卡扣产品'),(331,138,'卡扣实操'),(332,138,'现金产品'),(333,138,'现金实操'),(334,138,'赠送实操'),(335,139,'赠送产品'),(336,139,'赠送实操'),(337,140,'美容'),(338,140,'美体'),(339,140,'仪器'),(340,141,'卡扣产品'),(341,141,'现金产品'),(342,141,'赠送产品'),(343,142,'现金产品'),(344,142,'现金卡'),(345,142,'现金实操'),(346,143,'卡扣产品'),(347,143,'卡扣实操'),(348,143,'现金产品'),(349,143,'现金实操'),(350,143,'赠送实操'),(351,144,'赠送产品'),(352,144,'赠送实操'),(353,145,'美容'),(354,145,'美体'),(355,145,'仪器'),(356,146,'卡扣产品'),(357,146,'现金产品'),(358,146,'赠送产品'),(359,147,'现金产品'),(360,147,'现金卡'),(361,147,'现金实操'),(362,148,'卡扣产品'),(363,148,'卡扣实操'),(364,148,'现金产品'),(365,148,'现金实操'),(366,148,'赠送实操'),(367,149,'赠送产品'),(368,149,'赠送实操'),(369,150,'美容'),(370,150,'美体'),(371,150,'仪器'),(372,151,'卡扣产品'),(373,151,'现金产品'),(374,151,'赠送产品'),(375,152,'现金产品'),(376,152,'现金卡'),(377,152,'现金实操'),(378,153,'卡扣产品'),(379,153,'卡扣实操'),(380,153,'现金产品'),(381,153,'现金实操'),(382,153,'赠送实操'),(383,154,'赠送产品'),(384,154,'赠送实操'),(385,155,'美容'),(386,155,'美体'),(387,155,'仪器'),(388,156,'卡扣产品'),(389,156,'现金产品'),(390,156,'赠送产品'),(391,157,'现金产品'),(392,157,'现金卡'),(393,157,'现金实操'),(394,158,'卡扣产品'),(395,158,'卡扣实操'),(396,158,'现金产品'),(397,158,'现金实操'),(398,158,'赠送实操'),(399,159,'赠送产品'),(400,159,'赠送实操'),(401,160,'美容'),(402,160,'美体'),(403,160,'仪器'),(404,161,'卡扣产品'),(405,161,'现金产品'),(406,161,'赠送产品'),(407,162,'现金产品'),(408,162,'现金卡'),(409,162,'现金实操'),(410,163,'卡扣产品'),(411,163,'卡扣实操'),(412,163,'现金产品'),(413,163,'现金实操'),(414,163,'赠送实操'),(415,164,'赠送产品'),(416,164,'赠送实操'),(417,165,'美容'),(418,165,'美体'),(419,165,'仪器'),(420,166,'卡扣产品'),(421,166,'现金产品'),(422,166,'赠送产品'),(423,167,'现金产品'),(424,167,'现金卡'),(425,167,'现金实操'),(426,168,'卡扣产品'),(427,168,'卡扣实操'),(428,168,'现金产品'),(429,168,'现金实操'),(430,168,'赠送实操'),(431,169,'赠送产品'),(432,169,'赠送实操'),(433,170,'美容'),(434,170,'美体'),(435,170,'仪器'),(436,171,'卡扣产品'),(437,171,'现金产品'),(438,171,'赠送产品'),(439,172,'现金产品'),(440,172,'现金卡'),(441,172,'现金实操'),(442,173,'卡扣产品'),(443,173,'卡扣实操'),(444,173,'现金产品'),(445,173,'现金实操'),(446,173,'赠送实操'),(447,174,'赠送产品'),(448,174,'赠送实操'),(449,175,'美容'),(450,175,'美体'),(451,175,'仪器'),(452,176,'卡扣产品'),(453,176,'现金产品'),(454,176,'赠送产品'),(455,177,'现金产品'),(456,177,'现金卡'),(457,177,'现金实操'),(458,178,'卡扣产品'),(459,178,'卡扣实操'),(460,178,'现金产品'),(461,178,'现金实操'),(462,178,'赠送实操'),(463,179,'赠送产品'),(464,179,'赠送实操'),(465,180,'美容'),(466,180,'美体'),(467,180,'仪器'),(468,181,'卡扣产品'),(469,181,'现金产品'),(470,181,'赠送产品'),(471,182,'现金产品'),(472,182,'现金卡'),(473,182,'现金实操'),(474,183,'卡扣产品'),(475,183,'卡扣实操'),(476,183,'现金产品'),(477,183,'现金实操'),(478,183,'赠送实操'),(479,184,'赠送产品'),(480,184,'赠送实操'),(481,185,'美容'),(482,185,'美体'),(483,185,'仪器'),(484,186,'卡扣产品'),(485,186,'现金产品'),(486,186,'赠送产品'),(487,187,'现金产品'),(488,187,'现金卡'),(489,187,'现金实操'),(490,188,'卡扣产品'),(491,188,'卡扣实操'),(492,188,'现金产品'),(493,188,'现金实操'),(494,188,'赠送实操'),(495,189,'赠送产品'),(496,189,'赠送实操'),(497,190,'美容'),(498,190,'美体'),(499,190,'仪器'),(500,191,'卡扣产品'),(501,191,'现金产品'),(502,191,'赠送产品'),(503,192,'现金产品'),(504,192,'现金卡'),(505,192,'现金实操'),(506,193,'卡扣产品'),(507,193,'卡扣实操'),(508,193,'现金产品'),(509,193,'现金实操'),(510,193,'赠送实操'),(511,194,'赠送产品'),(512,194,'赠送实操'),(513,195,'美容'),(514,195,'美体'),(515,195,'仪器'),(516,196,'卡扣产品'),(517,196,'现金产品'),(518,196,'赠送产品'),(519,197,'现金产品'),(520,197,'现金卡'),(521,197,'现金实操'),(522,198,'卡扣产品'),(523,198,'卡扣实操'),(524,198,'现金产品'),(525,198,'现金实操'),(526,198,'赠送实操'),(527,199,'赠送产品'),(528,199,'赠送实操'),(529,200,'美容'),(530,200,'美体'),(531,200,'仪器'),(532,201,'卡扣产品'),(533,201,'现金产品'),(534,201,'赠送产品'),(535,202,'现金产品'),(536,202,'现金卡'),(537,202,'现金实操'),(538,203,'卡扣产品'),(539,203,'卡扣实操'),(540,203,'现金产品'),(541,203,'现金实操'),(542,203,'赠送实操'),(543,204,'赠送产品'),(544,204,'赠送实操'),(545,205,'美容'),(546,205,'美体'),(547,205,'仪器'),(548,206,'卡扣产品'),(549,206,'现金产品'),(550,206,'赠送产品'),(551,207,'现金产品'),(552,207,'现金卡'),(553,207,'现金实操'),(554,208,'卡扣产品'),(555,208,'卡扣实操'),(556,208,'现金产品'),(557,208,'现金实操'),(558,208,'赠送实操'),(559,209,'赠送产品'),(560,209,'赠送实操'),(561,210,'美容'),(562,210,'美体'),(563,210,'仪器'),(564,211,'卡扣产品'),(565,211,'现金产品'),(566,211,'赠送产品'),(567,212,'现金产品'),(568,212,'现金卡'),(569,212,'现金实操'),(570,213,'卡扣产品'),(571,213,'卡扣实操'),(572,213,'现金产品'),(573,213,'现金实操'),(574,213,'赠送实操'),(575,214,'赠送产品'),(576,214,'赠送实操'),(577,215,'美容'),(578,215,'美体'),(579,215,'仪器'),(580,216,'卡扣产品'),(581,216,'现金产品'),(582,216,'赠送产品'),(583,217,'现金产品'),(584,217,'现金卡'),(585,217,'现金实操'),(586,218,'卡扣产品'),(587,218,'卡扣实操'),(588,218,'现金产品'),(589,218,'现金实操'),(590,218,'赠送实操'),(591,219,'赠送产品'),(592,219,'赠送实操'),(593,220,'美容'),(594,220,'美体'),(595,220,'仪器'),(596,221,'卡扣产品'),(597,221,'现金产品'),(598,221,'赠送产品'),(599,222,'现金产品'),(600,222,'现金卡'),(601,222,'现金实操'),(602,223,'卡扣产品'),(603,223,'卡扣实操'),(604,223,'现金产品'),(605,223,'现金实操'),(606,223,'赠送实操'),(607,224,'赠送产品'),(608,224,'赠送实操'),(609,225,'美容'),(610,225,'美体'),(611,225,'仪器'),(612,226,'卡扣产品'),(613,226,'现金产品'),(614,226,'赠送产品'),(615,227,'现金产品'),(616,227,'现金卡'),(617,227,'现金实操'),(618,228,'卡扣产品'),(619,228,'卡扣实操'),(620,228,'现金产品'),(621,228,'现金实操'),(622,228,'赠送实操'),(623,229,'赠送产品'),(624,229,'赠送实操'),(625,230,'美容'),(626,230,'美体'),(627,230,'仪器'),(628,231,'卡扣产品'),(629,231,'现金产品'),(630,231,'赠送产品'),(631,232,'现金产品'),(632,232,'现金卡'),(633,232,'现金实操'),(634,233,'卡扣产品'),(635,233,'卡扣实操'),(636,233,'现金产品'),(637,233,'现金实操'),(638,233,'赠送实操'),(639,234,'赠送产品'),(640,234,'赠送实操'),(641,235,'美容'),(642,235,'美体'),(643,235,'仪器'),(644,236,'卡扣产品'),(645,236,'现金产品'),(646,236,'赠送产品'),(647,237,'现金产品'),(648,237,'现金卡'),(649,237,'现金实操'),(650,238,'卡扣产品'),(651,238,'卡扣实操'),(652,238,'现金产品'),(653,238,'现金实操'),(654,238,'赠送实操'),(655,239,'赠送产品'),(656,239,'赠送实操'),(657,240,'美容'),(658,240,'美体'),(659,240,'仪器'),(660,241,'卡扣产品'),(661,241,'现金产品'),(662,241,'赠送产品'),(663,242,'现金产品'),(664,242,'现金卡'),(665,242,'现金实操'),(666,243,'卡扣产品'),(667,243,'卡扣实操'),(668,243,'现金产品'),(669,243,'现金实操'),(670,243,'赠送实操'),(671,244,'赠送产品'),(672,244,'赠送实操'),(673,245,'美容'),(674,245,'美体'),(675,245,'仪器'),(676,246,'卡扣产品'),(677,246,'现金产品'),(678,246,'赠送产品'),(679,247,'现金产品'),(680,247,'现金卡'),(681,247,'现金实操'),(682,248,'卡扣产品'),(683,248,'卡扣实操'),(684,248,'现金产品'),(685,248,'现金实操'),(686,248,'赠送实操'),(687,249,'赠送产品'),(688,249,'赠送实操'),(689,250,'美容'),(690,250,'美体'),(691,250,'仪器'),(692,251,'卡扣产品'),(693,251,'现金产品'),(694,251,'赠送产品'),(695,252,'现金产品'),(696,252,'现金卡'),(697,252,'现金实操'),(698,253,'卡扣产品'),(699,253,'卡扣实操'),(700,253,'现金产品'),(701,253,'现金实操'),(702,253,'赠送实操'),(703,254,'赠送产品'),(704,254,'赠送实操'),(705,255,'美容'),(706,255,'美体'),(707,255,'仪器'),(708,256,'卡扣产品'),(709,256,'现金产品'),(710,256,'赠送产品'),(711,257,'现金产品'),(712,257,'现金卡'),(713,257,'现金实操'),(714,258,'卡扣产品'),(715,258,'卡扣实操'),(716,258,'现金产品'),(717,258,'现金实操'),(718,258,'赠送实操'),(719,259,'赠送产品'),(720,259,'赠送实操'),(721,260,'美容'),(722,260,'美体'),(723,260,'仪器'),(724,261,'卡扣产品'),(725,261,'现金产品'),(726,261,'赠送产品'),(727,262,'现金产品'),(728,262,'现金卡'),(729,262,'现金实操'),(730,263,'卡扣产品'),(731,263,'卡扣实操'),(732,263,'现金产品'),(733,263,'现金实操'),(734,263,'赠送实操'),(735,264,'赠送产品'),(736,264,'赠送实操'),(737,265,'美容'),(738,265,'美体'),(739,265,'仪器'),(740,266,'卡扣产品'),(741,266,'现金产品'),(742,266,'赠送产品'),(743,267,'现金产品'),(744,267,'现金卡'),(745,267,'现金实操'),(746,268,'卡扣产品'),(747,268,'卡扣实操'),(748,268,'现金产品'),(749,268,'现金实操'),(750,268,'赠送实操'),(751,269,'赠送产品'),(752,269,'赠送实操'),(753,270,'美容'),(754,270,'美体'),(755,270,'仪器'),(756,271,'卡扣产品'),(757,271,'现金产品'),(758,271,'赠送产品'),(759,272,'现金产品'),(760,272,'现金卡'),(761,272,'现金实操'),(762,273,'卡扣产品'),(763,273,'卡扣实操'),(764,273,'现金产品'),(765,273,'现金实操'),(766,273,'赠送实操'),(767,274,'赠送产品'),(768,274,'赠送实操'),(769,275,'美容'),(770,275,'美体'),(771,275,'仪器'),(772,276,'卡扣产品'),(773,276,'现金产品'),(774,276,'赠送产品'),(775,277,'现金产品'),(776,277,'现金卡'),(777,277,'现金实操'),(778,278,'卡扣产品'),(779,278,'卡扣实操'),(780,278,'现金产品'),(781,278,'现金实操'),(782,278,'赠送实操'),(783,279,'赠送产品'),(784,279,'赠送实操');
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
  `name` varchar(200) DEFAULT NULL,
  `companyname` varchar(200) DEFAULT NULL,
  `relationship` varchar(200) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`memberid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familyrelationship`
--

LOCK TABLES `familyrelationship` WRITE;
/*!40000 ALTER TABLE `familyrelationship` DISABLE KEYS */;
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
INSERT INTO `paymentmethod` VALUES (1,'卡扣实操'),(2,'现金实操'),(3,'赠送实操');
/*!40000 ALTER TABLE `paymentmethod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodreport`
--

DROP TABLE IF EXISTS `periodreport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodreport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staffid` int(11) DEFAULT NULL,
  `shopid` int(11) DEFAULT NULL,
  `txt` text,
  `period` varchar(50) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `periodreport_shopid__fk` (`shopid`),
  KEY `periodreport_staff_staffid_fk` (`staffid`),
  CONSTRAINT `periodreport_shopid__fk` FOREIGN KEY (`shopid`) REFERENCES `shop` (`shopid`),
  CONSTRAINT `periodreport_staff_staffid_fk` FOREIGN KEY (`staffid`) REFERENCES `staff` (`staffid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodreport`
--

LOCK TABLES `periodreport` WRITE;
/*!40000 ALTER TABLE `periodreport` DISABLE KEYS */;
/*!40000 ALTER TABLE `periodreport` ENABLE KEYS */;
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
  `shortname` varchar(255) DEFAULT NULL,
  `productbrand` varchar(255) DEFAULT NULL,
  `price` float(5,2) DEFAULT NULL,
  `fixedhand` float(5,2) DEFAULT NULL,
  `percentagemethod` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (3,50,'meirong1',NULL,NULL,NULL,NULL,NULL),(4,50,'meirong2',NULL,NULL,NULL,NULL,NULL),(5,51,'yiqi1',NULL,NULL,NULL,NULL,NULL);
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
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopid` int(11) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `review_shop_shopid_fk` (`shopid`),
  CONSTRAINT `review_shop_shopid_fk` FOREIGN KEY (`shopid`) REFERENCES `shop` (`shopid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
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
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settings` (
  `k` varchar(20) NOT NULL,
  `v` varchar(200) DEFAULT NULL,
  UNIQUE KEY `settings_k_uindex` (`k`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES ('bgimg','/file/img/51d35774-50f6-42e0-9270-1f0471134517.png'),('websitename','花惠健康美学');
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
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
  `customername` varchar(30) DEFAULT NULL,
  `paymentmethod` int(11) DEFAULT NULL,
  `roomname` varchar(20) DEFAULT NULL,
  `consultantid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settlement`
--

LOCK TABLES `settlement` WRITE;
/*!40000 ALTER TABLE `settlement` DISABLE KEYS */;
INSERT INTO `settlement` VALUES (2,12,'2019-09-26 07:00:02','c1',1,'1',33),(3,12,'2019-07-05 07:00:00','c2',1,'1',32),(4,11,'2019-07-05 07:00:00','11',NULL,NULL,NULL),(5,12,'2019-07-07 07:00:00','c1',1,'1',32),(7,12,'2019-10-17 12:29:33','testcustomname',1,'testroomname',32),(10,12,'2019-10-17 12:33:41','testcustomname',1,'testroomname',31);
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
  `projectid` int(11) NOT NULL,
  `times` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `staff1` int(11) DEFAULT NULL,
  `staff2` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settlementitem`
--

LOCK TABLES `settlementitem` WRITE;
/*!40000 ALTER TABLE `settlementitem` DISABLE KEYS */;
INSERT INTO `settlementitem` VALUES (2,2,3,2,2,28,28),(3,2,3,23,23,28,28),(4,5,4,156,45,28,28),(5,7,3,1,12,28,28),(6,7,4,2,234,28,28),(11,10,3,1,12,28,28),(12,10,4,2,234,28,28),(13,10,5,1,345,28,28);
/*!40000 ALTER TABLE `settlementitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settlementnew`
--

DROP TABLE IF EXISTS `settlementnew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settlementnew` (
  `settlementid` int(11) NOT NULL AUTO_INCREMENT,
  `shopid` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `customer` varchar(255) DEFAULT NULL COMMENT '顾客名',
  `classify` varchar(255) DEFAULT NULL COMMENT '分类',
  `category` varchar(255) DEFAULT NULL COMMENT '类别',
  `brandname` varchar(255) DEFAULT NULL,
  `projectname` varchar(255) DEFAULT NULL,
  `times` int(11) DEFAULT NULL COMMENT '数量',
  `hand` int(11) DEFAULT NULL COMMENT '手工费',
  `money` double(20,1) DEFAULT NULL,
  `consumptioncategory` varchar(255) DEFAULT NULL COMMENT '消费类别',
  `consumptionpattern` varchar(255) DEFAULT NULL COMMENT '消费方式',
  `allocate` varchar(255) DEFAULT NULL COMMENT '指非新',
  `beautician1` int(11) DEFAULT NULL,
  `beautician2` int(11) DEFAULT NULL,
  `cardcategory` varchar(255) DEFAULT NULL COMMENT '卡项类别',
  `consultant` varchar(255) DEFAULT NULL COMMENT '顾问',
  `checker` varchar(255) DEFAULT NULL COMMENT '审核人',
  PRIMARY KEY (`settlementid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settlementnew`
--

LOCK TABLES `settlementnew` WRITE;
/*!40000 ALTER TABLE `settlementnew` DISABLE KEYS */;
INSERT INTO `settlementnew` VALUES (1,12,'2016-01-01 08:00:00','testcustomer','classify','美容','brandname','美容1',12,1,12.0,'现金实操','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(3,12,'2019-11-11 08:00:00','testcustomer3','classify','美容','brandname','美容2',23,23,2.0,'consumptioncategory','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(4,12,'2019-01-01 08:00:00','testcustomer1','classify','美体','brandname','projectname',4,1,34.0,'consumptioncategory','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(5,12,'2019-11-13 08:00:00','testcustomer1','classify','仪器','brandname','projectname',34,1,234.0,'现金实操','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(6,12,'2019-11-11 08:00:00','testcustomer1','classify','仪器','brandname','projectname',53,2,53.0,'consumptioncategory','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(7,12,'2019-01-01 08:00:00','testcustomer4','classify','仪器','brandname','projectname',34,4,45.0,'现金实操','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(8,12,'2019-03-01 08:00:00','testcustomer4','classify','美体','brandname','projectname',45,1,42.0,'现金实操','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(9,12,'2019-07-01 08:00:00','testcustomer1','classify','产品','brandname','projectname',1,235,1.0,'现金产品','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(10,12,'2019-06-01 08:00:00','testcustomer1','classify','产品','brandname','projectname',1,1,1.0,'现金产品','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(11,12,'2019-05-01 08:00:00','testcustomer1','classify','产品','brandname','projectname',1,64,1.0,'卡扣产品','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(12,12,'2019-04-01 08:00:00','sadfgsdadsfdsagfd的方式发给s','classify','产品','brandname','projectname',1,1,1.0,'赠送产品','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(13,12,'2019-01-01 08:00:00','testcustomer2','classify','产品','brandname','projectname',1,1,1.0,'赠送产品','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(14,12,'2019-01-01 08:00:00','testcustomer2','classify','产品','brandname','projectname',1,56,1.0,'卡扣产品','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(15,12,'2019-11-01 08:00:00','testcustomer2','classify','卡','brandname','projectname',1,23,1.0,'现金卡','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(16,12,'2019-08-01 08:00:00','testcustomer2','classify','卡','brandname','projectname',1,1,1.0,'现金卡','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(17,12,'2019-09-01 08:00:00','testcustomer2','classify','卡','brandname','projectname',1,234,1.0,'卡扣卡','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(18,12,'2019-10-01 08:00:00','testcustomer2','classify','卡','brandname','projectname',1,2,1.0,'卡扣卡','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(19,12,'2016-01-01 08:00:00','testcustomer1sdafsd','classify','category','brandname','projectname',1,1,1.0,'consumptioncategory','consumptionpattern','sdafg',1,1,'cardcategory','consultant','checker'),(21,12,'2016-01-01 08:00:00','testcustomer1','classify','卡','brandname','测试1',1,1,998.0,'卡扣卡','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(22,12,'2016-01-01 08:00:00','testcustomer1','classify','美容','brandname','测试1',1,1,99.8,'卡扣实操','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(23,12,'2016-01-01 08:00:00','testcustomer1','classify','美容','brandname','测试1',1,1,99.8,'卡扣实操','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(24,12,'2016-01-01 08:00:00','testcustomer1','classify','卡','brandname','测试11',1,1,99.8,'卡扣卡','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(25,12,'2019-11-16 16:00:00','大王','基础','卡','aaaa','aaaa',1,2,33.0,'现金卡','卡扣','指',1,0,'开卡','aaaa','aaaa'),(26,12,'2019-11-16 16:00:00','小王','基础','美容','bbbb','bb',1,2,33.0,'现金实操','现金','非',1,0,'','bbbb','bbb'),(27,12,'2016-01-01 08:00:00','testcustomer1er','classify','卡','brandname','测试11',1,1,99.8,'卡扣卡','consumptionpattern','allocate',1,1,'cardcategory','consultant','checker'),(28,12,'2019-11-16 16:00:00','等等','基础','产品','等等','等等',1,2,34.0,'现金产品','卡扣','指',1,0,'','的','等等'),(29,195,'2019-11-18 16:00:00','爱的方式','基础','美容','啊','但是',1,2,143.0,'现金实操','卡扣','指',2,0,'','地方','盛大'),(31,12,'2019-11-17 16:00:00','asdf','基础','美体','aaaa','aaaaa',1,22,222.0,'现金实操','卡扣','指',1,0,'','aaaa','aaaa');
/*!40000 ALTER TABLE `settlementnew` ENABLE KEYS */;
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
  `brandid` int(11) NOT NULL,
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `district` varchar(20) DEFAULT NULL,
  `geo` varchar(200) DEFAULT NULL,
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
INSERT INTO `shop` VALUES (12,'shop',129,NULL,NULL,NULL,'sadfj'),(128,'test',122,'hb','cd','sl','1234'),(134,'sdfsd',129,NULL,NULL,NULL,'dsf'),(135,'sdfsd',129,NULL,NULL,NULL,'sdfsd'),(145,'kljlkjl',129,'','秦皇岛市','海港区','152'),(147,'第三方',129,'','天津市','河东区','第三方'),(169,'12354',129,'','天津市','东丽区','235'),(194,'aeras阿尔山的如风过',129,'','晋城市','泽州县','asdfg'),(195,'4444444444444444',129,'辽宁省','营口市','西市区','44444444444');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopvip`
--

DROP TABLE IF EXISTS `shopvip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopvip` (
  `vipid` int(11) NOT NULL AUTO_INCREMENT,
  `vipname` varchar(255) DEFAULT NULL,
  `vipnumber` int(11) DEFAULT NULL,
  `male` tinyint(4) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `telephone` int(11) DEFAULT NULL,
  `isvip` tinyint(1) DEFAULT '0',
  `shopid` int(11) DEFAULT NULL,
  `beautician` int(11) DEFAULT NULL,
  `consultant` int(11) DEFAULT NULL,
  `beautician2` int(11) DEFAULT NULL,
  PRIMARY KEY (`vipid`),
  UNIQUE KEY `shopvip_telephone_uindex` (`telephone`),
  UNIQUE KEY `shopvip_vipnumber_uindex` (`vipnumber`),
  KEY `shipvip_shop_shopid_fk` (`shopid`),
  KEY `shipvip_staff_staffid_fk` (`consultant`),
  KEY `shopvip_staff_staffid_fk` (`beautician`),
  CONSTRAINT `shipvip_shop_shopid_fk` FOREIGN KEY (`shopid`) REFERENCES `shop` (`shopid`),
  CONSTRAINT `shipvip_staff_staffid_fk` FOREIGN KEY (`consultant`) REFERENCES `staff` (`staffid`),
  CONSTRAINT `shopvip_staff_staffid_fk` FOREIGN KEY (`beautician`) REFERENCES `staff` (`staffid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopvip`
--

LOCK TABLES `shopvip` WRITE;
/*!40000 ALTER TABLE `shopvip` DISABLE KEYS */;
/*!40000 ALTER TABLE `shopvip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `staffid` int(11) NOT NULL,
  `shopid` int(11) NOT NULL,
  `role` varchar(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `del` tinyint(1) DEFAULT '0',
  `employment` tinyint(1) DEFAULT '1',
  `avatar` varchar(200) DEFAULT NULL,
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
  PRIMARY KEY (`staffid`,`shopid`),
  KEY `staff_shop_id_fk` (`shopid`),
  CONSTRAINT `staff_shop_id_fk` FOREIGN KEY (`shopid`) REFERENCES `shop` (`shopid`),
  CONSTRAINT `staff_user_id_fk` FOREIGN KEY (`staffid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,12,'consultant','臧三',0,1,NULL,0,'1991-10-16','汉族','中共党员','健康','秦皇岛','秦皇岛开发区','15242525986','15242525986','张三',0,'无','朋友','李四',0,'无','朋友'),(2,12,'beautician','阿斯蒂芬',0,1,NULL,0,'2019-11-17','大师傅','阿斯蒂芬','阿萨德','速度','地方','15515154812','11151548251','阿斯蒂芬',0,'无','朋友','李四',0,'无','朋友');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todaywork`
--

DROP TABLE IF EXISTS `todaywork`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `todaywork` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staffid` int(11) DEFAULT NULL,
  `day` datetime DEFAULT NULL,
  `remindcustomers` tinyint(1) DEFAULT NULL,
  `recordingservice` tinyint(1) DEFAULT NULL,
  `returningcustomers` tinyint(1) DEFAULT NULL,
  `servicenote` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todaywork`
--

LOCK TABLES `todaywork` WRITE;
/*!40000 ALTER TABLE `todaywork` DISABLE KEYS */;
INSERT INTO `todaywork` VALUES (4,11,'2019-10-24 00:00:00',NULL,NULL,1,1);
/*!40000 ALTER TABLE `todaywork` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(200) DEFAULT NULL,
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$04$dBXhocvRJeh7Sq7GKRTis.cl59WkbhFMgr8iyCqr9tW7c7GGbocE6','2019-10-21 21:44:36'),(2,'阿萨德飞洒地方','aaaa','2019-11-17 09:01:36'),(12,'shop','$2a$04$ZbL9Wvz59CIw4spk9flVcuKZvWCsFNZpuQUdqfDEvYlpWJp/Ppk9u','2019-10-21 21:44:36'),(22,'dewitt','$2a$04$0vZDXb.C1iFz/zAmayKzD.O0fB3CmuDwo4dkBxDAI1LqnEd0JEdDq','2019-10-21 21:44:36'),(28,'dewittdeshop','$2a$04$Tv0ZJr2eQ5TwZdunZM1Y8.Qb7ba8TwMO9/vhcLdN.SAi1wc9ytG8q','2019-10-21 21:44:36'),(29,'testname','$2a$04$.bB4ZvGS.nLfR9lah3Z37udq2gVRCTPF9ZT/JgcONtYjrQlurGKSG','2019-10-21 21:44:36'),(30,'testname2','$2a$04$OHBeVsNZUFeEJdmp9.SQseFFVwFMLDIPPMvlSKn0dSy1u2HIY18re','2019-10-21 21:44:36'),(31,'MIKE','','2019-10-21 21:44:36'),(32,'MIKE11','','2019-10-21 21:44:36'),(58,'testshagnjia111','$2a$04$.wqXaspIBv/3CB1YS1ZtAu9jnOyF12w3I4Ck2Kep9HZOpdkPt8GOy','2019-10-23 15:22:39'),(60,'12345623451234123','$2a$04$WSF4oJWcVpvx3ZMxWyvb/uDYN4X0VP7G5tJIqNG2R5Ro/2rCJPBdS','2019-10-23 15:29:16'),(72,'asdfsd','$2a$04$tC/54seB2p0KOs45q62zyuDggKWR5jM3NlORehhCZl/uae7xKGDJ2','2019-10-24 10:24:55'),(74,'adfsd','$2a$04$klJg0fpt09FZRll.pJZzF.QkyydIqoSJxADBn0xFWRAbydseF1pGW','2019-10-24 10:30:08'),(82,'sdf的','$2a$04$hOYkUGNPA7Xg340oH02VCenVkghlvVFvFWaX5DDfegKunGffL4ueu','2019-10-24 11:27:59'),(83,'第三方','$2a$04$PC286wgTdEnDyfI0AbfLJO8GSR.8MSiNX/eJ0JpIU8POVpNrQqgiW','2019-10-24 11:38:07'),(84,'撒旦法的','$2a$04$H3ijfQ24eZ0OhdmMQ0zpLuN01irkYrLLDQMURXGxJfXk4HPtGlqoa','2019-10-24 11:41:31'),(85,'阿道夫','$2a$04$MbUZJ/SVHt/2DhfwJcts3.WzJxfBc7cCwKLgfKR1yqfQYseWkEO/K','2019-10-24 11:42:52'),(86,'双方都是','$2a$04$SUBw5JHETTVIEnqEBejpMuJlN4YwDeiHf8zKU6Q47RPLpQ.fhGkPa','2019-10-24 11:58:58'),(87,'asdf1','$2a$04$447nPqM2Yv.XpY8CTIAt3.RqWaY./ZEYX1mPV77KItGwTOQkJUcnK','2019-10-24 13:56:30'),(91,'花惠店1','$2a$04$/bQKpEKcem6bwRjeoj6Mz.wPQhvw0rWGSwH8m/2iHlijsCUA6VTLC','2019-10-25 16:09:18'),(92,'花惠店2','','2019-10-25 16:10:03'),(93,'花惠店3','','2019-10-25 16:11:08'),(96,'sadfas','$2a$04$WsrUJfqRf52GeekUOAeZ6unTkyD.4dDihGIxg0l/QR0tiatyr..ma','2019-10-26 14:58:57'),(97,'adf','$2a$04$SBH4f5.49cWXXzb7MuixjuywAKrjljVTwLPNLmmmyk2km/mDnapk2','2019-10-26 14:59:57'),(112,'134325423','$2a$04$7ic01ISGMN7KLrEJpImyROnBfpTX6leq86p9FKWBv5MeOLC1pRgcK','2019-10-26 17:59:23'),(113,'32','$2a$04$lSRgsxkFsH2SIjnlEoHJyespvK3V6jn/KPRwJDvMoJUcB/HKxr5NO','2019-10-27 20:58:25'),(114,'78','$2a$04$xC1fsfAnkFa5MBVGhY3/vOmLoZ7Rw66DQFbFX6fgo8c43kKzG0kGa','2019-10-27 21:00:58'),(116,'sdfs54','$2a$04$L7P3XWBcEC2Q.OISYU26DeTygWvPplSjk4gng3kvp63JiAXYDpGtq','2019-10-28 12:38:03'),(121,'','','2019-10-29 10:26:29'),(122,'brand1','$2a$04$PjUvbGO4E3k8UEn3V60v8OTSZVMhgInbpZP.lNP3crXCW31UhY6RK','2019-10-31 09:21:36'),(124,'sdf6','$2a$04$KCQuKgsqtstN4Yi3sO5WUemL/arNhaAOUu/buBvTfmVikKZ1jQ/IS','2019-10-29 14:50:44'),(126,'shop1','$2a$04$v2rtwYNIwJQg39vG0UDUX.hXwLqbAFXqiT.N8KcNrg0p9MARp3cnK','2019-10-30 13:51:42'),(127,'s','$2a$04$VTeU2GoSiS1.NvtZ9T262O7hyAc1B9k.339wa0VjkRSHg00KuyDCq','2019-10-30 15:03:31'),(128,'qweqwe','$2a$04$kEKzbS9UonvzfsVFxMlrv.0ISpX4zN4b./nbqKkY0dm.ioYazxvJu','2019-10-30 16:14:51'),(129,'brand','$2a$04$YMt3it423yauHgUpNQAA7ejGun4NiFPRb3rMgAMB9DZFrIxDYgXVC','2019-10-31 10:27:30'),(134,'dfsdf','$2a$04$MNzsisTC.VzgO/cwNZZ6eO/HYkndOYZvrZRzktNm4VDy3GdCtjv46','2019-10-31 11:50:38'),(135,'123','$2a$04$3kksRGw7CEGWKZXSZbRa1.ju1Xx6Nh2Q.vDPTrUULd3LUwXySvs5m','2019-10-31 11:51:12'),(136,'dxv','$2a$04$PTYbkQOOwQSuauBI732AOeaC5DoJSSQqhRHyYNjx95WUsKRW6teo6','2019-10-31 11:52:58'),(137,'er','$2a$04$1c.8w/iXCIrFpl5sTfrrcOqc9pZXMUWOj5DyapXzglnEhpJa9BdtK','2019-10-31 11:53:26'),(138,'12','$2a$04$V537YJ6ZCg4LqnbIUIrxveUYWEgzxJ2ZXm86w3/DZ5ocRjTVx93E6','2019-10-31 11:55:29'),(139,'ssdf','$2a$04$c3Mhb.ITnBME94.QNbmbjO0CYiRdmFqTSMguh6YMkCzHtDZG6w6CC','2019-10-31 11:58:30'),(140,'userdsfgdsname','','2019-10-31 12:25:57'),(141,'userdsghhfgdsname','','2019-10-31 12:28:10'),(142,'12dgfdf','','2019-10-31 12:37:05'),(143,'12dgfdfdfg','','2019-10-31 12:38:11'),(144,'12dgfdfdfgrthy','','2019-10-31 12:57:43'),(145,'jkj','$2a$04$ypIVHq.UviK9lJoKmUUFP.NdZVTWI9ZwpZaRrPG1Mzl0ievH1s1Ie','2019-10-31 13:59:39'),(146,'5412','$2a$04$c29HXtJBU122C1g2AgOG3.enx5RSnGScmG6xAHg/oNDuIqiNL3YGi','2019-10-31 14:08:06'),(147,'是的范德萨','$2a$04$o8nhPOD5d2xCctdMqZDLQuYp4aNG3k5HqG8AAC55EZsjez6GLn0Da','2019-10-31 14:10:32'),(148,'是非得失','$2a$04$KYKS/om7zWvZLwUJzek5CeWsFlAvs6OhPnzeB4wP6fGqtHXFXRkRO','2019-10-31 14:33:35'),(149,'沙发','$2a$04$1j41X.u0JK4g7wMDRqj4CeBCbhp4B0ftg3wjho.kaErfSMeF4jnim','2019-10-31 14:39:08'),(150,'说的','','2019-10-31 14:43:13'),(151,'12dgfdfdfgrthy单方事故','','2019-10-31 15:05:05'),(152,'sfdsdaf','','2019-10-31 15:20:57'),(153,'dsaf','','2019-11-02 09:26:25'),(154,'花惠','$2a$04$bpTaKSKGwM1BhJbIdJrMruAUYQXNd0GaTaLbLRdKrKcybhzFDcJmK','2019-11-02 10:30:54'),(155,'admin1','$2a$04$pmZ.UvN35w/welyvp5yaxOWUAjBUynPU0YlC/NKcwllYdIErmT1wO','2019-11-04 09:02:19'),(156,'admin2','$2a$04$nit/dLKlzGkzujRkb1i.kuCDiwnP.uWX3r2/SaAtLSi0P2dsCTSra','2019-11-04 09:09:10'),(157,'dsf','$2a$04$r97I1MAn/Ag9VJHI9yGneuY0Iqip.yqF2dqzZcC9hHGjKP.5JbRTu','2019-11-04 09:09:38'),(159,'臧三','','2019-11-04 09:43:54'),(160,'xc','$2a$04$s8YoFpGDttNbeziddpqgQOxL7Dm/3A7G956QppiI4w5m3pT62Gc0S','2019-11-04 10:34:57'),(162,'胡志军','','2019-11-11 09:44:43'),(163,'sdf3','$2a$04$FC67lb/SVJvlaCSm01cZ4.y9QZkS5OgPiYGql4ukd8a8bXtcGIki6','2019-11-14 17:26:56'),(167,'uuuuu','$2a$04$.gmLZuZyjNhyPwuzxfgE8ufcZLBtUgnQ6Fq4coh2UoBOFOr.U63ve','2019-11-14 18:59:00'),(168,'mmmmmm','$2a$04$h3jWGthqGwyOhNQiGSGK/unJLNFgb85PKgQa2WLD4iG7AgqEgsZ9u','2019-11-14 19:00:50'),(169,'123465','$2a$04$xi3y5.2n7wQ9DKGxALYS7eHoRXsHr5viV5DKNfg/FnHVzeTCGGH62','2019-11-14 19:01:41'),(178,'adminaaaa','$2a$04$/xsQyfyL/35dyik9WyQru.wNMzrVJ/gMwVeCg8jubi1js0YmSo5ea','2019-11-16 10:59:06'),(188,'tttttttt','$2a$04$KzN2rNjAQjvpseFpoOx5w.bS9vErMpPkY2U3.pg5UJQXwchYU7LVe','2019-11-16 13:59:35'),(189,'asdfsadf','$2a$04$MlAj88trBOTlT9LS4OArEuGTUp6.EJrBo9XEB6uQPT6XeROl4KES.','2019-11-16 14:39:10'),(190,'asdfsadft','$2a$04$fidE2cI1df/H5r9pnD1Q8.qjQrdJKvvC7e10buU1J2IDlNZQZ23zO','2019-11-16 14:40:10'),(191,'asdgasdf','$2a$04$TcJueKe8kIA6J8h5vq6oquN9oox30lLFuUI7DgCXE8bj67qecwHOS','2019-11-16 16:57:24'),(192,'asdgasdfasdf','$2a$04$rcd4jr1X015GVjDYgRZ4suMDkXyORamT.bqm1N9YztKxHKhwBf232','2019-11-16 17:04:47'),(193,'adminasdg','$2a$04$6JeSwwUrzV.gi66Usg3kXe4u6uJ5lL1FNouAeTAyHE3t.tD1lG8ui','2019-11-16 17:07:37'),(194,'asdfgwerfd','$2a$04$VqVM/6q/x22W8EKlj1f07OfLY1gy.LNRE/M6TVKj6zJPrctWH9ltC','2019-11-16 17:41:31'),(195,'11111111111111','$2a$04$G.rFIHpvGBFUObPb3Ukyj.sczdwSPSpBQe8rbGEFxJGhwnGKxDpQG','2019-11-17 08:53:43'),(196,'admin555555555555','$2a$04$ioagp8zjHsM/G7nZjz7EweoPRnrKVAGFIfOrnBF1JwDQeoCIdURyi','2019-11-17 09:50:56'),(197,'sssssss','','2019-11-18 21:29:47'),(198,'admindf','','2019-11-18 21:32:08'),(199,'1111111admin','','2019-11-19 09:22:59'),(200,'asdfsdaaa','','2019-11-19 09:29:36');
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
INSERT INTO `user_role` VALUES (1,1),(12,3),(22,3),(28,4),(29,3),(30,3),(31,4),(32,4),(58,3),(60,3),(72,3),(74,3),(79,2),(82,3),(83,3),(84,3),(85,3),(86,3),(87,3),(91,3),(92,3),(93,3),(96,3),(97,3),(112,3),(113,3),(114,3),(116,3),(120,2),(121,4),(122,2),(124,3),(126,3),(127,3),(128,3),(129,2),(134,3),(135,3),(136,3),(137,3),(138,3),(139,3),(140,4),(141,4),(142,4),(143,4),(144,4),(145,3),(146,3),(147,3),(148,3),(149,3),(150,4),(151,4),(152,4),(153,4),(154,2),(155,1),(156,1),(157,2),(159,4),(160,1),(162,4),(163,2),(167,3),(168,3),(169,3),(178,1),(186,2),(188,2),(189,2),(190,2),(191,3),(192,3),(193,3),(194,3),(195,3),(196,3),(2,4),(197,4),(198,4),(199,4),(200,4);
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

-- Dump completed on 2019-11-19 15:11:20
