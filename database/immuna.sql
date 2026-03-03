CREATE DATABASE  IF NOT EXISTS `immuna` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `immuna`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: immuna
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `disease`
--

DROP TABLE IF EXISTS `disease`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disease` (
  `disease_id` bigint NOT NULL AUTO_INCREMENT,
  `disease_name` varchar(45) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`disease_id`),
  UNIQUE KEY `disease_name_UNIQUE` (`disease_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disease`
--

LOCK TABLES `disease` WRITE;
/*!40000 ALTER TABLE `disease` DISABLE KEYS */;
INSERT INTO `disease` VALUES (1,'COVID-19','A contagious respiratory illness caused by the SARS-CoV-2 virus, which can lead to mild symptoms or severe complications affecting the lungs and other organs.'),(2,'Influenza (Flu)','A viral infection that attacks the respiratory system and spreads easily through droplets from coughs and sneezes.'),(3,'Hepatitis B','A viral liver infection that can become chronic and lead to liver failure, cirrhosis, or liver cancer.'),(4,'Measles','A highly contagious viral disease characterized by fever, cough, and a red rash that spreads over the body.'),(5,'Tetanus','A serious bacterial infection caused by Clostridium tetani, leading to painful muscle stiffness and spasms.'),(6,'Rabies','A viral disease transmitted through animal bites, affecting the central nervous system and almost always fatal if untreated.'),(7,'Human Papillomavirus (HPV)','A common viral infection that can lead to cervical cancer and other cancers in both men and women.'),(8,'Poliomyelitis (Polio)','A viral disease that can cause paralysis and permanent disability, primarily affecting young children.');
/*!40000 ALTER TABLE `disease` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `immunization_records`
--

DROP TABLE IF EXISTS `immunization_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `immunization_records` (
  `record_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `schedule_id` bigint DEFAULT NULL,
  `date_taken` date DEFAULT NULL,
  `next_due_date` date DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_record_user` (`user_id`),
  KEY `fk_record_schedule` (`schedule_id`),
  CONSTRAINT `fk_record_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `vaccine_schedules` (`schedule_id`),
  CONSTRAINT `fk_record_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immunization_records`
--

LOCK TABLES `immunization_records` WRITE;
/*!40000 ALTER TABLE `immunization_records` DISABLE KEYS */;
INSERT INTO `immunization_records` VALUES (1,2,1,'2025-01-01','2025-01-01'),(2,2,2,'2025-02-01','2025-03-03'),(3,2,3,'2025-08-05','2026-02-01'),(4,2,6,'2014-02-01','2024-01-30'),(5,2,4,'2025-10-01','2026-10-01'),(6,3,7,'2026-02-01','2026-02-01'),(7,3,16,'2025-12-01','2025-12-01'),(8,3,17,'2026-02-05','2026-04-06'),(9,3,12,'2024-05-01','2024-05-01'),(10,3,13,'2024-05-04','2024-05-07'),(11,3,14,'2024-05-08','2024-05-15');
/*!40000 ALTER TABLE `immunization_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `role` enum('USER','ADMIN') DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'System Admin',NULL,'admin@immuna.com','$2a$10$dDqe/eaD5Dndv1URwgwRNeexkO7RMgn0grWzHMIDirbxltsoKltWG','ADMIN',_binary ''),(2,'John Doe','2000-06-11','john@gmail.com','$2a$10$V9qBSHBgARDhxODc00lDjujg/fEucZCTr1B4U74ttQ48jz05zsmf6','USER',_binary ''),(3,'ragil','2001-02-13','ragil@gmail.com','$2a$10$dG8TFo7vbX/QWo748jDN7uJMjpgwcrrQQxPGeckQa1B10Txb.PvWy','USER',_binary '');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaccine`
--

DROP TABLE IF EXISTS `vaccine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vaccine` (
  `vaccine_id` bigint NOT NULL AUTO_INCREMENT,
  `vaccine_name` varchar(100) DEFAULT NULL,
  `description` text,
  `manufacturer` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`vaccine_id`),
  UNIQUE KEY `vaccine_name_UNIQUE` (`vaccine_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaccine`
--

LOCK TABLES `vaccine` WRITE;
/*!40000 ALTER TABLE `vaccine` DISABLE KEYS */;
INSERT INTO `vaccine` VALUES (1,'COVAX-21','An mRNA-based vaccine designed to provide immunity against COVID-19 by training the immune system to recognize and fight the virus.','BioNova Pharmaceuticals'),(2,'FluShield-Q','A quadrivalent influenza vaccine that protects against four major strains of seasonal flu viruses.','Medigen Biotech'),(3,'Hepavax-B','A recombinant vaccine that stimulates long-term immunity against the Hepatitis B virus.','GlobalVax Ltd.'),(4,'MMR-Protect','A combined live-attenuated vaccine offering protection against measles, mumps, and rubella.','HealthCore Biologics'),(5,'TetaGuard','An inactivated toxoid vaccine that provides immunity against tetanus by neutralizing bacterial toxins.','Vaxline Industries'),(6,'Rabivax-R','A post-exposure prophylactic vaccine that prevents the rabies virus from infecting the nervous system when administered early.','SafeLife Vaccines'),(7,'HPV-Guard9','A 9-valent vaccine that protects against the most dangerous strains of HPV linked to cancer.','NexGen Biopharma'),(8,'PolioSafe-IPV','A viral disease that can cause paralysis and permanent disability, primarily affecting young children.','Immunex Global');
/*!40000 ALTER TABLE `vaccine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaccine_diseases`
--

DROP TABLE IF EXISTS `vaccine_diseases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vaccine_diseases` (
  `vaccine_disease_id` bigint NOT NULL AUTO_INCREMENT,
  `vaccine_id` bigint DEFAULT NULL,
  `disease_id` bigint DEFAULT NULL,
  PRIMARY KEY (`vaccine_disease_id`),
  UNIQUE KEY `uc_vaccine_disease` (`vaccine_id`,`disease_id`),
  KEY `fk_vd_d` (`disease_id`),
  CONSTRAINT `fk_vd_d` FOREIGN KEY (`disease_id`) REFERENCES `disease` (`disease_id`),
  CONSTRAINT `fk_vd_v` FOREIGN KEY (`vaccine_id`) REFERENCES `vaccine` (`vaccine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaccine_diseases`
--

LOCK TABLES `vaccine_diseases` WRITE;
/*!40000 ALTER TABLE `vaccine_diseases` DISABLE KEYS */;
INSERT INTO `vaccine_diseases` VALUES (1,1,1),(5,2,2),(3,3,3),(4,4,4),(6,5,5),(7,6,6),(8,7,7),(9,8,8);
/*!40000 ALTER TABLE `vaccine_diseases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaccine_schedules`
--

DROP TABLE IF EXISTS `vaccine_schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vaccine_schedules` (
  `schedule_id` bigint NOT NULL AUTO_INCREMENT,
  `vaccine_id` bigint NOT NULL,
  `dose_no` int NOT NULL,
  `gap_days` int NOT NULL,
  `dose_type` enum('PRIMARY','BOOSTER') NOT NULL,
  `is_recurring` bit(1) NOT NULL,
  `max_occurrences` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`schedule_id`),
  UNIQUE KEY `uq_vaccine_dose` (`vaccine_id`,`dose_no`),
  CONSTRAINT `fk_schedule_vaccine` FOREIGN KEY (`vaccine_id`) REFERENCES `vaccine` (`vaccine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaccine_schedules`
--

LOCK TABLES `vaccine_schedules` WRITE;
/*!40000 ALTER TABLE `vaccine_schedules` DISABLE KEYS */;
INSERT INTO `vaccine_schedules` VALUES (1,3,1,0,'PRIMARY',_binary '\0',1),(2,3,2,30,'PRIMARY',_binary '\0',1),(3,3,3,180,'PRIMARY',_binary '\0',1),(4,2,1,365,'BOOSTER',_binary '',50),(5,5,1,0,'PRIMARY',_binary '\0',1),(6,5,2,3650,'BOOSTER',_binary '',5),(7,4,1,0,'PRIMARY',_binary '\0',1),(8,4,2,28,'PRIMARY',_binary '\0',1),(9,8,1,0,'PRIMARY',_binary '\0',1),(10,8,2,60,'PRIMARY',_binary '\0',1),(11,8,3,365,'BOOSTER',_binary '\0',1),(12,6,1,0,'PRIMARY',_binary '\0',1),(13,6,2,3,'PRIMARY',_binary '\0',1),(14,6,3,7,'PRIMARY',_binary '\0',1),(15,6,4,14,'PRIMARY',_binary '\0',1),(16,7,1,0,'PRIMARY',_binary '\0',1),(17,7,2,60,'PRIMARY',_binary '\0',1),(18,7,3,180,'PRIMARY',_binary '\0',1);
/*!40000 ALTER TABLE `vaccine_schedules` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-03  5:53:19
