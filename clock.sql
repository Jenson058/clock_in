-- MySQL dump 10.13  Distrib 8.0.37, for Linux (x86_64)
--
-- Host: localhost    Database: clock_in
-- ------------------------------------------------------
-- Server version	8.0.37-0ubuntu0.22.04.3

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
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `finish` bit(1) DEFAULT NULL,
  `md_key` varchar(255) DEFAULT NULL,
  `old_file_name` varchar(255) DEFAULT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (1,'2024-06-25 08:42:59.494000','冯新杰',_binary '\0','2024-06-25 08:42:59.561000','冯新杰','/upload/clock_in/5bdb1c9a3886687d6f16d29e4354c4f9.jpg',_binary '','5bdb1c9a3886687d6f16d29e4354c4f9','fengchu.jpg','jpg','image/jpeg');
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `day` int DEFAULT NULL,
  `mouth` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `year` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (14,'2024-07-01 14:39:12.150000','冯新杰',_binary '\0','2024-07-01 14:39:12.150000','冯新杰',1,7,2,2024),(15,'2024-07-01 14:46:27.699000','刘岩',_binary '\0','2024-07-01 14:46:27.699000','刘岩',1,7,3,2024),(20,'2024-07-02 14:06:19.009000','冯新杰',_binary '\0','2024-07-02 14:06:19.009000','冯新杰',2,7,2,2024),(21,'2024-07-02 14:06:19.845000','刘岩',_binary '\0','2024-07-02 14:06:19.845000','刘岩',2,7,3,2024),(22,'2024-07-02 23:43:32.977000','冯新杰',_binary '\0','2024-07-02 23:43:32.977000','冯新杰',3,7,2,2024),(23,'2024-07-03 23:38:09.119000','冯新杰',_binary '\0','2024-07-03 23:38:09.119000','冯新杰',4,7,2,2024),(24,'2024-07-04 02:04:52.088000','刘岩',_binary '\0','2024-07-04 02:04:52.088000','刘岩',4,7,3,2024),(25,'2024-07-04 23:55:30.494000','冯新杰',_binary '\0','2024-07-04 23:55:30.494000','冯新杰',5,7,2,2024),(26,'2024-07-08 00:38:46.669000','刘岩',_binary '\0','2024-07-08 00:38:46.669000','刘岩',8,7,3,2024),(27,'2024-07-08 00:59:26.999000','冯新杰',_binary '\0','2024-07-08 00:59:26.999000','冯新杰',8,7,2,2024),(28,'2024-07-09 00:44:27.939000','刘岩',_binary '\0','2024-07-09 00:44:27.939000','刘岩',9,7,3,2024),(29,'2024-07-09 03:50:51.295000','冯新杰',_binary '\0','2024-07-09 03:50:51.295000','冯新杰',9,7,2,2024),(30,'2024-07-10 00:33:06.372000','冯新杰',_binary '\0','2024-07-10 00:33:06.372000','冯新杰',10,7,2,2024),(31,'2024-07-10 01:09:16.505000','刘岩',_binary '\0','2024-07-10 01:09:16.505000','刘岩',10,7,3,2024),(32,'2024-07-11 00:03:14.769000','冯新杰',_binary '\0','2024-07-11 00:03:14.769000','冯新杰',11,7,2,2024),(33,'2024-07-12 00:43:10.335000','冯新杰',_binary '\0','2024-07-12 00:43:10.335000','冯新杰',12,7,2,2024),(34,'2024-07-15 00:34:49.384000','冯新杰',_binary '\0','2024-07-15 00:34:49.384000','冯新杰',15,7,2,2024),(35,'2024-07-15 05:34:23.025000','刘岩',_binary '\0','2024-07-15 05:34:23.025000','刘岩',15,7,3,2024),(36,'2024-07-05 07:35:07.871000','刘岩',_binary '\0','2024-07-05 07:35:07.871000','刘岩',5,7,3,2024),(37,'2024-07-03 07:35:07.871000','刘岩',_binary '\0','2024-07-03 07:35:07.871000','刘岩',3,7,3,2024),(38,'2024-07-11 07:35:07.871000','刘岩',_binary '\0','2024-07-11 07:35:07.871000','刘岩',11,7,3,2024),(39,'2024-07-12 07:35:07.871000','刘岩',_binary '\0','2024-07-12 07:35:07.871000','刘岩',12,7,3,2024),(40,'2024-07-15 23:44:12.799000','冯新杰',_binary '\0','2024-07-15 23:44:12.799000','冯新杰',16,7,2,2024),(41,'2024-07-16 00:42:11.396000','刘岩',_binary '\0','2024-07-16 00:42:11.396000','刘岩',16,7,3,2024),(42,'2024-07-16 09:16:25.857000','刘岩',_binary '\0','2024-07-16 09:16:25.857000','刘岩',16,7,NULL,2024),(43,'2024-07-17 00:19:17.907000','冯新杰',_binary '\0','2024-07-17 00:19:17.907000','冯新杰',17,7,2,2024),(44,'2024-07-17 00:19:19.035000','冯新杰',_binary '\0','2024-07-17 00:19:19.035000','冯新杰',17,7,2,2024),(45,'2024-07-17 00:47:09.297000','刘岩',_binary '\0','2024-07-17 00:47:09.297000','刘岩',17,7,3,2024),(46,'2024-07-17 23:43:38.640000','冯新杰',_binary '\0','2024-07-17 23:43:38.640000','冯新杰',18,7,2,2024),(47,'2024-07-19 00:35:33.680000','冯新杰',_binary '\0','2024-07-19 00:35:33.680000','冯新杰',19,7,2,2024),(48,'2024-07-19 00:49:03.006000','刘岩',_binary '\0','2024-07-19 00:49:03.006000','刘岩',19,7,3,2024),(49,'2024-07-22 07:40:45.693000','冯新杰',_binary '\0','2024-07-22 07:40:45.693000','冯新杰',22,7,2,2024),(50,'2024-07-22 11:15:38.998000','刘岩',_binary '\0','2024-07-22 11:15:38.998000','刘岩',22,7,3,2024);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renewal`
--

DROP TABLE IF EXISTS `renewal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renewal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `file_id` int DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renewal`
--

LOCK TABLES `renewal` WRITE;
/*!40000 ALTER TABLE `renewal` DISABLE KEYS */;
/*!40000 ALTER TABLE `renewal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `auth_all` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'2024-06-28 15:42:41.240000','admin',_binary '\0','2024-06-28 15:42:49.940000','admin','clock:retrieve','外包打卡'),(2,'2024-06-28 16:03:49.032000','admin',_binary '\0','2024-06-28 16:04:18.553000','admin','user:edit;user:add;user:delete;user:retrieve;auth:edit;auth:retrieve;role:add;role:edit;role:delete;role:retrieve;clock:retrieve','管理员');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sharding`
--

DROP TABLE IF EXISTS `sharding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sharding` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `count` int DEFAULT NULL,
  `file_id` int DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `md_key` varchar(255) DEFAULT NULL,
  `number` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sharding`
--

LOCK TABLES `sharding` WRITE;
/*!40000 ALTER TABLE `sharding` DISABLE KEYS */;
INSERT INTO `sharding` VALUES (1,'2024-06-25 08:42:59.527000','冯新杰',_binary '','2024-06-25 08:42:59.545000','冯新杰',1,1,'/upload/clock_in/NayOIDSsEtwJGcETPjPObDentlAiFkZk',NULL,1);
/*!40000 ALTER TABLE `sharding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` datetime(6) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `e_mail` varchar(255) DEFAULT NULL,
  `encipher` varchar(255) DEFAULT NULL,
  `is_admin` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role_list` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2024-06-25 07:33:29.532000',NULL,_binary '\0','2024-06-25 07:33:29.532000',NULL,NULL,'2024-06-25 07:33:29.519000','admin',NULL,'StlbkBMEMTIqFwnP',_binary '','admin','admin','3eae18d7bb4ead3baa68b55426ae9dc6',NULL,'',NULL),(2,'2024-06-25 07:55:10.853000','admin',_binary '\0','2024-06-28 16:04:00.085000','admin','1','2002-06-17 00:00:00.000000','J0001','3231953149@qq.com','EYvnWugfWprdlzlO',NULL,'冯新杰','Jenson','3895239d311541ade82433e1a3df249b','15651373050','1','男'),(3,'2024-06-28 09:02:18.467000','admin',_binary '\0','2024-06-28 16:00:50.640000','admin',NULL,'2024-06-12 00:00:00.000000','J0002','1233','fsRKHTbmNVOdPOSh',NULL,'刘岩','Lany','9f846f0cbd517bc6e65735138d5a6b22','13328191509','1','男');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-22 19:48:56
