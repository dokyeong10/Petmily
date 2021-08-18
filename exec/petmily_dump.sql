-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: 54.180.127.68    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `agency`
--

DROP TABLE IF EXISTS `agency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency` (
  `agencycode` varchar(255) NOT NULL,
  `agencyname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`agencycode`),
  UNIQUE KEY `agencycode_UNIQUE` (`agencycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency`
--

LOCK TABLES `agency` WRITE;
/*!40000 ALTER TABLE `agency` DISABLE KEYS */;
INSERT INTO `agency` VALUES ('3010000','서울특별시 야생동물 구조관리센터','sap03003@naver.com','https://petmily.s3.ap-northeast-2.amazonaws.com/cat-icon.png','$2a$10$nNuR7XJ9XK8/T5QuI/O7s.8CNBNzQq34mXYG.EHbi9.Q5JDpHX7JW','010-0000-0000','김태랑'),('3040000','서울시 야생동물센터','https@hanmail.net','https://petmily.s3.ap-northeast-2.amazonaws.com/152411507666_20180420.jpeg','$2a$10$qtQjJ8l/IQ4dnhMxKa32/OY1BwPwHVZmKEsm/FzyKYzQbsI55UoCW','010-0000-0000','김태랑'),('3700000','울산광역시 야생동물구조센터','ssafy@ssafy.com','https://petmily.s3.ap-northeast-2.amazonaws.com/pngtree-cute-puppy-free-illustration-image_1099242.jpeg','$2a$10$pOzXPoMmlBQQhA.0mla6ROtZ5xjRDf9NYxBp/B4GMbtuFcEaZ35p2','010-4423-5563','김정윤'),('3910000','경기도야생동물구조관리센터','kjyoun0729@gmail.com','https://petmily.s3.ap-northeast-2.amazonaws.com/images%20%2810%29.jpeg','$2a$10$dMqjC9ruAzkbrnGOWyETRuSjCBlDAnL7CIAOGpaG9xWNgI/gHnUey','010-2331-0141','윌리웡카'),('3980000','아마존동물병원','junku8782@gmail.com','https://petmily.s3.ap-northeast-2.amazonaws.com/1869_1742_183.jpg','$2a$10$j0KjahEgLjR9wC94LlPOI.VF5sjqKKE9sJY5EQWW78VRxQS8RQbPy','010-1234-1234','박냥냥'),('4190000','야생생물관리협회 원주지회','ehrud542910@gmail.com','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC2.jpeg','$2a$10$SxLmQr5De4xfZOcatKZ3GOZODEdlbalIIMR8bpRm26LCuF3iOkL02','010-1111-2222','갱박사'),('5180000','영덕야생동물구조센터','dign1570@naver.com','https://petmily.s3.ap-northeast-2.amazonaws.com/house.png','$2a$10$idLkkoQ7Ot35q2Wa8J3jW.GtrkaQdlGSiw4OTFHmhm9lDIbL/tWTi','010-1111-2222','영덕이'),('5220000','제일동물병원','kyinlee@yonsei.ac.kr','https://petmily.s3.ap-northeast-2.amazonaws.com/%EC%A0%9C%EC%9D%BC%EB%8F%99%EB%AC%BC%EB%B3%91%EC%9B%90.jpg','$2a$10$HaDCC5OE5.PrCaewgqUXh..CfNQJ2G6LWIYQF.RIef/DXwZYj2FQi','010-8888-8888','수의사'),('5350000','장유동물병원','kyinle2@gmail.com','https://petmily.s3.ap-northeast-2.amazonaws.com/%EC%9E%A5%EC%9C%A0%EB%8F%99%EB%AC%BC%EB%B3%91%EC%9B%90.jpg','$2a$10$NJFaaZJcIzNOvim7jxObL.7ROzVfHbREbqdh/P7DzSnV6ftjJo9H6','010-9999-9999','김장유');
/*!40000 ALTER TABLE `agency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal` (
  `no` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `species` varchar(45) DEFAULT NULL,
  `find_addr` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `neutered` tinyint(1) DEFAULT NULL,
  `find_date` datetime DEFAULT NULL,
  `agencycode` varchar(50) DEFAULT NULL,
  `profile_img` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `animal_agency_fk` (`agencycode`),
  CONSTRAINT `animal_agency_fk` FOREIGN KEY (`agencycode`) REFERENCES `agency` (`agencycode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKis3u5u00omrcfx6v5epxqxwua` FOREIGN KEY (`agencycode`) REFERENCES `shelter` (`agencycode`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES (56,'강아지','모르겠어요 ㅜㅜ ','집 근처 공원',3,0,0,'2021-08-03 23:19:00','3010000','https://petmily.s3.ap-northeast-2.amazonaws.com/dogyellow.png','귀여워요'),(58,'다람쥐','한국다람쥐','뒷산',1,0,0,'2021-08-04 08:40:00','3040000','https://petmily.s3.ap-northeast-2.amazonaws.com/%EB%8B%A4%EB%9E%8C%EC%A5%90.jpg','작은 다람쥐입니다.'),(59,'고양이','길고양이','집 근처 공원',1,0,0,'2021-07-16 08:48:00','3040000','https://petmily.s3.ap-northeast-2.amazonaws.com/202106181400166246_d.jpg','아직 많이 어린아이라 주사를 못 맞췄습니다.'),(60,'라쿤','라쿤','뒷산에서 발견했어요!',2,1,0,'2021-08-07 08:55:00','3040000','https://petmily.s3.ap-northeast-2.amazonaws.com/AKR20160229042000030_01_i_P2.jpg','라쿤은 활발한 동물입니다. 입양 전 꼭 습성을 확인해주세요'),(61,'강아지','진돗개','시골 할머니집',1,0,0,'2021-06-16 09:14:00','3040000','https://petmily.s3.ap-northeast-2.amazonaws.com/i7364x8765c836h6ee11.jpg','시골에서 자라서 튼튼합니다'),(62,'고양이','노르웨이 숲','노르웨이의 숲',2,0,0,'2021-05-06 09:27:00','3980000','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%82%E1%85%A9%E1%84%85%E1%85%B3%E1%84%8B%E1%85%B0%E1%84%8B%E1%85%B5%E1%84%89%E1%85%AE%E1%87%81%20%E1%84%80%E1%85%A9%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B53.jpeg','노르웨이 숲에서 발견했을 때 이쁜 색이었는데, 한국에 오고 난 뒤에 색이 바뀌었어요ㅠㅠ! 하지만 너무너무 이쁩니다!!'),(63,'고양이','샴','하남 인근',2,1,1,'2021-06-13 09:28:00','3980000','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%A3%E1%86%B7%E1%84%80%E1%85%A9%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5.jpg','너무너무 귀엽지만 외로움을 많이 타요. 오래 함께할 집사가 필요해요!'),(64,'강아지','잡종','장터',2,0,0,'2021-08-08 09:25:00','3040000','https://petmily.s3.ap-northeast-2.amazonaws.com/%EC%9E%A1%EC%A2%85%EA%B0%95%EC%95%84%EC%A7%80.png','장터에서 돌아다니는 강아지를 주워왔습니다.'),(65,'고양이','러시안불루','하남 인근 골목',1,0,1,'2021-07-08 09:29:00','3980000','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%85%E1%85%A5%E1%84%89%E1%85%B5%E1%84%8B%E1%85%A1%E1%86%AB%20%E1%84%87%E1%85%AE%E1%86%AF%E1%84%85%E1%85%AE1.jpg','겁이 많아요! 넓은 마음의 집사가 필요합니다!'),(66,'고양이','페르시안 고양이','충청남도 아산시 산속',2,0,0,'2021-06-09 09:30:00','4190000','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%91%E1%85%A6%E1%84%80%E1%85%A91.jpeg','겁이 많고 사람을 무서워함. 몸이 약함, 애교가 많음 '),(67,'강아지','푸들','집 앞 공원에서 발견했습니다.',2,0,0,'2021-08-16 09:30:00','5180000','https://petmily.s3.ap-northeast-2.amazonaws.com/%ED%91%B8%EB%93%A41.jpg','너무 귀여워요. 얼른 주인 품으로 돌려보내고 싶어요.'),(68,'강아지','사모예드','울산 자동차 정비소 옆 공중 화장실',4,0,1,'2021-02-03 09:31:00','3700000','https://petmily.s3.ap-northeast-2.amazonaws.com/img.jpeg','너무너무 사랑스러워요 ㅜㅠ'),(69,'강아지','비숑','서울특별시 금천구 ',1,0,0,'2021-08-02 09:36:00','4190000','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC1.jpeg','활발하고, 애교가 많음. 사람을 잘 따르고 식탐이 많음'),(70,'거북이','붉은 귀 거북','서울시 구로구 안양천 하수구 옆',12,0,0,'2021-08-03 09:45:00','3700000','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3.png','너무 귀여운 거북이에요! 하수도에 버려져 있었습니다 ㅜㅠ'),(71,'강아지','시츄','길가에서 헤메고 있었습니다.',2,0,0,'2021-08-16 09:39:00','5220000','https://petmily.s3.ap-northeast-2.amazonaws.com/%EC%8B%9C%EC%B8%841.jpg','쫄래쫄래 잘 따라오더라구요'),(72,'토끼','산토끼','충남 태안군 태안읍 군청길 ',5,0,0,'2021-06-30 09:40:00','4190000','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%90%E1%85%A9%E1%84%81%E1%85%B51.jpeg','활발하고, 달리기가 빠름, 민첩하고 식탐이 많음'),(73,'강아지','시바견','인천광역시 남동구 아시아드 호수 옆 ',4,0,0,'2021-08-01 09:48:00','4190000','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%B5%E1%84%87%E1%85%A1%E1%84%80%E1%85%A7%E1%86%AB1.jpeg','짖음이 많음, 사람을 무서워함, 경계가 심함'),(74,'강아지','포메라니안','울산시청 분리수거장 옆',2,1,0,'2021-04-16 10:13:00','3700000','https://petmily.s3.ap-northeast-2.amazonaws.com/99207E3D5D9EE8810C.jpeg','몸통이 주먹만한 아이입니다. 데리고 가주세요!'),(75,'강아지','비글','구리시청 근처',0,0,0,'2021-06-01 10:19:00','3980000','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%80%E1%85%B3%E1%86%AF2.jpeg','프로필은 평소에 뛰는 사진인데 아래 사진은 잘못했을 때 짓는 표정이에요! 최근에는 이런 표정을 많이 짓네요ㅎㅎ;;'),(76,'강아지','퍼그','울산 꼬꼬 삼계탕 집 공영주차장 컨테이너 옆',4,0,1,'2021-02-28 10:19:00','3700000','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3%20%285%29.jpeg','잘 때 코를 좀 골지만 그래도 이쁜 아이입니다!');
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animal_file`
--

DROP TABLE IF EXISTS `animal_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal_file` (
  `no` bigint NOT NULL AUTO_INCREMENT,
  `animalno` bigint DEFAULT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `FK_animal_wait_TO_animal_file_1` (`animalno`),
  CONSTRAINT `FK_animal_wait_TO_animal_file_1` FOREIGN KEY (`animalno`) REFERENCES `animal` (`no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal_file`
--

LOCK TABLES `animal_file` WRITE;
/*!40000 ALTER TABLE `animal_file` DISABLE KEYS */;
INSERT INTO `animal_file` VALUES (64,56,'png','https://petmily.s3.ap-northeast-2.amazonaws.com/dogyellow.png'),(67,59,'jpg','https://petmily.s3.ap-northeast-2.amazonaws.com/202106181400166246_d.jpg'),(71,58,'jpg','https://petmily.s3.ap-northeast-2.amazonaws.com/%EB%8B%A4%EB%9E%8C%EC%A5%90.jpg'),(73,61,'jpg','https://petmily.s3.ap-northeast-2.amazonaws.com/i7364x8765c836h6ee11.jpg'),(74,60,'jpg','https://petmily.s3.ap-northeast-2.amazonaws.com/AKR20160229042000030_01_i_P2.jpg'),(80,64,'png','https://petmily.s3.ap-northeast-2.amazonaws.com/%EC%9E%A1%EC%A2%85%EA%B0%95%EC%95%84%EC%A7%80.png'),(81,62,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%82%E1%85%A9%E1%84%85%E1%85%B3%E1%84%8B%E1%85%B0%E1%84%8B%E1%85%B5%E1%84%89%E1%85%AE%E1%87%81%20%E1%84%80%E1%85%A9%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B51.jpeg'),(82,62,'png','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%82%E1%85%A9%E1%84%85%E1%85%B3%E1%84%8B%E1%85%B0%E1%84%8B%E1%85%B5%E1%84%89%E1%85%AE%E1%87%81%20%E1%84%80%E1%85%A9%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B52.png'),(83,63,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%A3%E1%86%B7%E1%84%80%E1%85%A9%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B52.jpeg'),(84,63,'jpg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%A3%E1%86%B7%E1%84%80%E1%85%A9%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B53.jpg'),(85,65,'jpg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%85%E1%85%A5%E1%84%89%E1%85%B5%E1%84%8B%E1%85%A1%E1%86%AB%20%E1%84%87%E1%85%AE%E1%86%AF%E1%84%85%E1%85%AE1.jpg'),(86,65,'jpg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%85%E1%85%A5%E1%84%89%E1%85%B5%E1%84%8B%E1%85%A1%E1%86%AB%E1%84%87%E1%85%AE%E1%86%AF%E1%84%85%E1%85%AE2.jpg'),(89,67,'jpg','https://petmily.s3.ap-northeast-2.amazonaws.com/%ED%91%B8%EB%93%A42.jpg'),(90,67,'png','https://petmily.s3.ap-northeast-2.amazonaws.com/%ED%91%B8%EB%93%A43.png'),(91,68,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/images%20%282%29.jpeg'),(92,68,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/images%20%281%29.jpeg'),(93,68,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/h97blvlg5f67g8de989d.jpeg'),(98,69,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC3.jpeg'),(99,69,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC4%5C.jpeg'),(100,69,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC2.jpeg'),(101,69,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC5.jpeg'),(105,71,'jpg','https://petmily.s3.ap-northeast-2.amazonaws.com/%EC%8B%9C%EC%B8%842.jpg'),(106,71,'mp4','https://petmily.s3.ap-northeast-2.amazonaws.com/%EC%98%81%EB%A6%AC%ED%95%9C+%EC%8B%9C%EC%B8%84.mp4'),(110,72,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%90%E1%85%A9%E1%84%81%E1%85%B53.jpeg'),(111,72,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%90%E1%85%A9%E1%84%81%E1%85%B52.jpeg'),(112,72,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%90%E1%85%A9%E1%84%81%E1%85%B5%205.jpeg'),(113,72,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%90%E1%85%A9%E1%84%81%E1%85%B54.jpeg'),(114,72,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%90%E1%85%A9%E1%84%81%E1%85%B51.jpeg'),(115,70,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/images%20%283%29.jpeg'),(116,70,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/images%20%284%29.jpeg'),(117,70,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/images%20%285%29.jpeg'),(118,70,'mp4','https://petmily.s3.ap-northeast-2.amazonaws.com/tutle.mp4'),(119,73,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%B5%E1%84%87%E1%85%A1%E1%84%80%E1%85%A7%E1%86%AB%204.jpeg'),(120,73,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%B5%E1%84%87%E1%85%A1%E1%84%80%E1%85%A7%E1%86%AB2.jpeg'),(121,73,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%B5%E1%84%87%E1%85%A1%E1%84%80%E1%85%A7%E1%86%AB1.jpeg'),(122,73,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%B5%E1%84%87%E1%85%A1%E1%84%80%E1%85%A7%E1%86%AB3.jpeg'),(142,66,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%91%E1%85%A63.jpeg'),(143,66,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%91%E1%85%A62.jpeg'),(144,74,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3%20%282%29.jpeg'),(145,74,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3%20%281%29.jpeg'),(146,74,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3.jpeg'),(147,74,'mp4','https://petmily.s3.ap-northeast-2.amazonaws.com/pome.mp4'),(148,75,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%80%E1%85%B3%E1%86%AF3.jpeg'),(149,75,'png','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%80%E1%85%B3%E1%86%AF1.png'),(150,76,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/1566017891_89651200.jpeg'),(151,76,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3%20%284%29.jpeg'),(152,76,'jpeg','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3%20%283%29.jpeg'),(153,76,'mp4','https://petmily.s3.ap-northeast-2.amazonaws.com/pug.mp4');
/*!40000 ALTER TABLE `animal_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animal_like`
--

DROP TABLE IF EXISTS `animal_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal_like` (
  `no` bigint NOT NULL AUTO_INCREMENT,
  `animalno` bigint DEFAULT NULL,
  `userno` bigint DEFAULT NULL,
  `state` bit(1) NOT NULL,
  PRIMARY KEY (`no`),
  KEY `FKpum1imgxlrmmf874g0e2yunw1` (`userno`),
  KEY `FK9lfm37tfs77wplvp1x12np176` (`animalno`),
  CONSTRAINT `FK9lfm37tfs77wplvp1x12np176` FOREIGN KEY (`animalno`) REFERENCES `animal` (`no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKpum1imgxlrmmf874g0e2yunw1` FOREIGN KEY (`userno`) REFERENCES `user` (`no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal_like`
--

LOCK TABLES `animal_like` WRITE;
/*!40000 ALTER TABLE `animal_like` DISABLE KEYS */;
INSERT INTO `animal_like` VALUES (40,56,18,_binary '\0'),(41,59,20,_binary '\0'),(42,58,20,_binary '\0'),(43,61,20,_binary '\0'),(44,63,20,_binary '\0'),(45,62,20,_binary '\0'),(46,64,20,_binary '\0'),(47,63,18,_binary '\0'),(48,64,18,_binary '\0'),(49,69,18,_binary '\0'),(50,75,18,_binary '\0'),(51,61,19,_binary '\0'),(52,76,19,_binary '\0'),(53,74,19,_binary '\0'),(54,73,19,_binary '\0'),(55,59,19,_binary '\0'),(57,76,20,_binary '\0'),(58,73,20,_binary '\0'),(63,73,22,_binary '\0'),(65,71,22,_binary '\0'),(66,76,22,_binary '\0');
/*!40000 ALTER TABLE `animal_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `no` bigint NOT NULL AUTO_INCREMENT,
  `userno` bigint DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `contents` varchar(500) DEFAULT NULL,
  `reg_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `replycnt` int DEFAULT '0',
  `agencycode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `userno_board_fk_idx` (`userno`),
  KEY `fk_board_agency1_idx` (`agencycode`),
  CONSTRAINT `fk_board_agency1` FOREIGN KEY (`agencycode`) REFERENCES `agency` (`agencycode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userno_board_fk` FOREIGN KEY (`userno`) REFERENCES `user` (`no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (21,18,'동물 러버 도갱이 입니다 ~! ','이번에 새로 가입한 동물 러버 입니다! 여러분 잘부탁 드려용용 ㅎㅎ ','2021-08-17 13:59:36',5,NULL),(24,NULL,'안녕하세요','저희 동물들 많이 데려가주세요','2021-08-17 23:46:28',1,'3040000'),(27,20,'치명적인 저의 시베리안 허스키좀 봐주세요!','너무 치명적이지 않나요? 이름은 안마에요! 제 등을 그렇게 주물러 준다구요! 이쁘죠!!?? 근데 윙크 하나는 기가 막히네요ㅋㅋㅋㅋㅋㅋ','2021-08-18 00:37:04',0,NULL),(28,20,'고양이 입양했어요!!','너무 귀엽지 않나요!!??','2021-08-18 00:39:58',3,NULL),(31,NULL,'비숑가족 소개합니다~!','우리 보호소의 자랑 비숑가족을 소개합니다 :) \n원주의 자랑이라고도 불려요 ~! 귀여운 비숑가족이 가족을 찾고 있습니다!! : ?  함께 가족이 되실 분 . . 연락주세요 :) \n??','2021-08-18 00:59:46',1,'4190000'),(32,NULL,'노르웨이 숲 고양이 바로 입양됐습니다!','네 여러분 노르웨이 숲 고양이는 바로 입양됐습니다ㅜㅜ 아쉽지만 다음 기회에!','2021-08-18 01:00:09',4,'3980000'),(34,21,'시츄','시츄','2021-08-18 01:02:07',6,NULL),(35,NULL,'울산 보호소 동물들 예방 접종 시켜주었습니다.','저희 애기들 예방 접종의 날입니다!\n너무너무 이쁜 애기들 건강하게 자라도록 주사 딱콩 놓아주었어요~~\n많이들 저희 보호소에 들리셔서 이쁜 애기들의 가족이 되어주세요~~?','2021-08-18 01:41:11',3,'3700000'),(48,NULL,'저희 보호소 멋쟁이가 무지개 다리를 건넜습니다 ㅜㅠ','저희 보호소 멋쟁이 조랑말 만두가 무지개 다리를 건넜습니다!\n병을 앓고 있었는데요,,, 장장 7시간에 걸친 대수술을 했지만 끝내 이기지 못하고 평온하게 잠들었네요ㅜㅠ\n정말 마음이 아프네요,,,\n아직도 주위에는 가족을 찾지 못한 마음이 아픈 동물들이\n많이 있습니다!!\n꼭 저희 보호소에 들려 이쁜 동물들의 가족이 되어주세요~','2021-08-18 01:58:23',4,'3700000'),(49,19,'우리 짜글이 보고 가세용!!','이번에 입양하게 된 짜글이에요~~~\n만지는 모습 보여드리겠습니다!! \n너무너무 귀여워요~~~\n여러분도 꼭 좋은 가족 만나길 바랄게요~~ ?','2021-08-18 02:08:30',2,NULL),(50,18,'우리집 멍이가 좋아하는 간식 소개! ','안녕하세요~ 저는 오늘 우리집 귀욤둥이 멍이가 좋아하는 간식을 소개해볼까합니당 홍홍 ~! \n\n절대 광고 아닙니다 ?  멍이는 오리 고기랑 닭고기를 좋아해서 이 간식을 많이 줘요~ 영양소도 풍부하고, 맛도 좋고 저렴하기 까지! 지금 이벤트 많이 하는 것 같으니까 다들 한번씩 보고 가세요~! :) ?','2021-08-18 02:40:32',2,NULL),(51,18,'우리 트리 보고가세요~ ? ?','안녕하세요 ~ 펫밀리에서 만난 트리를 소개해드리겠습니다! 트리랑 가족이 된지 3년 째..행복한 시간을 보내고 있습니다~! 하루하루 넘 소중하고 행복해요 :) ?\n펫밀리 덕분에 좋은 가족 찾았어요 ! ! 감사합니다~!\n??? 우리가족! ','2021-08-18 10:54:50',5,NULL),(52,19,'우리 솜털이 좀 찾아주세요ㅜㅠㅠ ?','저희 집 보물 솜털이를 잃어버렸습니다 ㅜㅠㅠ\n구일역 앞 안양천에서 함께 산책하다가 날치기 당했습니다,,,\n혹시라도 보신분 있으시면 \n010-1234-2213 로 꼭 연락 부탁드립니다!!\n제발요 ㅜㅠㅠ','2021-08-18 11:00:36',3,NULL);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board_file`
--

DROP TABLE IF EXISTS `board_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_file` (
  `no` bigint NOT NULL AUTO_INCREMENT,
  `extension` varchar(10) DEFAULT NULL,
  `boardno` bigint DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `boardfile_board_fk_idx` (`boardno`),
  CONSTRAINT `boardfile_board_fk` FOREIGN KEY (`boardno`) REFERENCES `board` (`no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_file`
--

LOCK TABLES `board_file` WRITE;
/*!40000 ALTER TABLE `board_file` DISABLE KEYS */;
INSERT INTO `board_file` VALUES (18,'png',21,'https://petmily.s3.ap-northeast-2.amazonaws.com/t2.png'),(36,'png',27,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%92%E1%85%A5%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B5.png'),(55,'jpeg',31,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC2.jpeg'),(56,'jpeg',31,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC3.jpeg'),(57,'jpeg',31,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC%E1%84%80%E1%85%A1%E1%84%8C%E1%85%A9%E1%86%A8.jpeg'),(58,'jpeg',31,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC4%5C.jpeg'),(59,'jpeg',31,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC5.jpeg'),(60,'jpeg',31,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC%E1%84%80%E1%85%A1%E1%84%8C%E1%85%A9%E1%86%A8.jpeg'),(61,'jpeg',32,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%82%E1%85%A9%E1%84%85%E1%85%B3%E1%84%8B%E1%85%B0%E1%84%8B%E1%85%B5%E1%84%89%E1%85%AE%E1%87%81%20%E1%84%80%E1%85%A9%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B51.jpeg'),(62,'jpeg',32,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%82%E1%85%A9%E1%84%85%E1%85%B3%E1%84%8B%E1%85%B0%E1%84%8B%E1%85%B5%E1%84%89%E1%85%AE%E1%87%81%20%E1%84%80%E1%85%A9%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B53.jpeg'),(63,'png',32,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%82%E1%85%A9%E1%84%85%E1%85%B3%E1%84%8B%E1%85%B0%E1%84%8B%E1%85%B5%E1%84%89%E1%85%AE%E1%87%81%20%E1%84%80%E1%85%A9%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B52.png'),(66,'jpg',34,'https://petmily.s3.ap-northeast-2.amazonaws.com/%EC%8B%9C%EC%B8%842.jpg'),(67,'mp4',34,'https://petmily.s3.ap-northeast-2.amazonaws.com/%EC%98%81%EB%A6%AC%ED%95%9C+%EC%8B%9C%EC%B8%84.mp4'),(68,'jpeg',35,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3%20%288%29.jpeg'),(69,'jpeg',35,'https://petmily.s3.ap-northeast-2.amazonaws.com/images%20%286%29.jpeg'),(70,'jpeg',35,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3%20%287%29.jpeg'),(71,'jpg',28,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%85%E1%85%A5%E1%84%89%E1%85%B5%E1%84%8B%E1%85%A1%E1%86%AB%20%E1%84%87%E1%85%AE%E1%86%AF%E1%84%85%E1%85%AE1.jpg'),(72,'jpg',28,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%85%E1%85%A5%E1%84%89%E1%85%B5%E1%84%8B%E1%85%A1%E1%86%AB%E1%84%87%E1%85%AE%E1%86%AF%E1%84%85%E1%85%AE2.jpg'),(73,'jpeg',48,'https://petmily.s3.ap-northeast-2.amazonaws.com/200911horse1.jpeg'),(74,'jpeg',48,'https://petmily.s3.ap-northeast-2.amazonaws.com/art_15935137675389_5d420f.jpeg'),(75,'jpeg',49,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%83%E1%85%A1%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%85%E1%85%A9%E1%84%83%E1%85%B3%20%286%29.jpeg'),(76,'mp4',49,'https://petmily.s3.ap-northeast-2.amazonaws.com/pug2.mp4'),(77,'jpeg',50,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A1%E1%86%AB%E1%84%89%E1%85%B5%E1%86%A83.jpeg'),(78,'jpeg',50,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A1%E1%86%AB%E1%84%89%E1%85%B5%E1%86%A84.jpeg'),(79,'jpeg',50,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A1%E1%86%AB%E1%84%89%E1%85%B5%E1%86%A82.jpeg'),(80,'jpeg',50,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A1%E1%86%AB%E1%84%89%E1%85%B5%E1%86%A81.jpeg'),(87,'jpeg',52,'https://petmily.s3.ap-northeast-2.amazonaws.com/images%20%287%29.jpeg'),(88,'jpeg',52,'https://petmily.s3.ap-northeast-2.amazonaws.com/images%20%288%29.jpeg'),(89,'jpeg',52,'https://petmily.s3.ap-northeast-2.amazonaws.com/images%20%289%29.jpeg'),(90,'png',51,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A9%E1%86%AF%E1%84%83%E1%85%B3%E1%86%AB2.png'),(91,'png',51,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A9%E1%86%AF%E1%84%83%E1%85%B3%E1%86%AB1.png'),(92,'png',51,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A9%E1%86%AF%E1%84%83%E1%85%B3%E1%86%AB4.png'),(93,'png',51,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A9%E1%86%AF%E1%84%83%E1%85%B3%E1%86%AB6.png'),(94,'png',51,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A9%E1%86%AF%E1%84%83%E1%85%B3%E1%86%AB3.png'),(95,'png',51,'https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A9%E1%86%AF%E1%84%83%E1%85%B3%E1%86%AB5.png');
/*!40000 ALTER TABLE `board_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_check`
--

DROP TABLE IF EXISTS `email_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_check` (
  `no` bigint NOT NULL AUTO_INCREMENT,
  `auth_code` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_check`
--

LOCK TABLES `email_check` WRITE;
/*!40000 ALTER TABLE `email_check` DISABLE KEYS */;
INSERT INTO `email_check` VALUES (8,'Y','dign1571@naver.com'),(9,'Y','sap03003@naver.com'),(10,'Y','junku8782@gmail.com'),(11,'Y','https@hanmail.net'),(12,'Y','kyinle2@gmail.com'),(14,'Y','ehrud542910@gmail.com'),(15,'Y','test@ssafy.com'),(17,'Y','kyinlee@yonsei.ac.kr'),(18,'Y','ssafy@ssafy.com'),(19,'Y','junkuill@naver.com'),(20,'Y','kyinlee@naver.com'),(21,'Y','kjyoun0729@gmail.com'),(22,'Y','dign1570@naver.com'),(23,'Y','akcnjs@naver.com'),(24,'Y','dokk0923@gmail.com');
/*!40000 ALTER TABLE `email_check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live_room`
--

DROP TABLE IF EXISTS `live_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live_room` (
  `no` bigint NOT NULL AUTO_INCREMENT,
  `agencycode` varchar(50) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no`),
  UNIQUE KEY `agencycode_UNIQUE` (`agencycode`),
  CONSTRAINT `agency_live` FOREIGN KEY (`agencycode`) REFERENCES `agency` (`agencycode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live_room`
--

LOCK TABLES `live_room` WRITE;
/*!40000 ALTER TABLE `live_room` DISABLE KEYS */;
INSERT INTO `live_room` VALUES (87,'3980000','하남 동물 병원의 라이브 방송입니다!','2021-08-18 00:45:59','여러분들께 저희 병원 동물을 소개해드릴게요!','https://petmily.s3.ap-northeast-2.amazonaws.com/1509326246-298145.jpg'),(88,'3700000','울산 보호소 동물들을 소개해드려요!','2021-08-18 00:49:00','완전 귀엽고 깜찍한 친구들이 가족을 기다리고 있어요!','https://petmily.s3.ap-northeast-2.amazonaws.com/99C309375EC2427F07.png'),(89,'4190000','귀여운 비숑 가족을 소개합니다','2021-08-18 00:57:44','우리 보호소의 자랑 귀여운 비숑가족을 소개합니다 ! ! ! 한시간 예정!','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%B5%E1%84%89%E1%85%AD%E1%86%BC%E1%84%80%E1%85%A1%E1%84%8C%E1%85%A9%E1%86%A8.jpeg'),(90,'3040000','귀여운 유기동물 보고가세요!','2021-08-18 10:48:26','귀여운 동물들이 있습니다.','https://petmily.s3.ap-northeast-2.amazonaws.com/152411507666_20180420.jpeg'),(106,'3910000','경기도 보호소 라이브','2021-08-18 13:48:13','저희 보호소에 있는 귀요미들 한번 보고 가세요!','https://petmily.s3.ap-northeast-2.amazonaws.com/images%20%2810%29.jpeg'),(107,'5180000','영덕 라이브 시작~!','2021-08-18 13:49:30','영덕 라이브 시작합니다~! ? 귀여운 동물들 소개해 드릴게요~~ 3시간 예정! ?','https://petmily.s3.ap-northeast-2.amazonaws.com/house.png'),(108,'5220000','제일 동물병원입니다!?','2021-08-18 13:49:36','귀여운 유기동물들이 기다리고 있어요?','https://petmily.s3.ap-northeast-2.amazonaws.com/%EC%8B%9C%EC%B8%841.jpg'),(109,'5350000','장유 동물병원에 어서오세요!?','2021-08-18 13:54:29','밝고 귀여운 아이들이 많아요?','https://petmily.s3.ap-northeast-2.amazonaws.com/%EC%9E%A5%EC%9C%A0%EB%8F%99%EB%AC%BC%EB%B3%91%EC%9B%90.jpg');
/*!40000 ALTER TABLE `live_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply` (
  `no` bigint NOT NULL AUTO_INCREMENT,
  `bno` bigint DEFAULT NULL,
  `grp` bigint DEFAULT NULL,
  `grporder` bigint DEFAULT NULL,
  `grpdeep` bigint DEFAULT NULL,
  `userno` bigint DEFAULT NULL,
  `agencycode` varchar(50) DEFAULT NULL,
  `contents` varchar(200) DEFAULT NULL,
  `regdate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`no`),
  KEY `FKa4sphmcrb3vfytm50d3y8li9t` (`agencycode`),
  KEY `FKh5x2ypnqmyjhv28nwv59byocs` (`userno`),
  CONSTRAINT `agencycode` FOREIGN KEY (`agencycode`) REFERENCES `agency` (`agencycode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userno` FOREIGN KEY (`userno`) REFERENCES `user` (`no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (10,21,10,0,0,18,NULL,'우와 ~! 마자용~!','2021-08-17 14:01:16'),(11,21,10,1,1,NULL,'3010000','너무 귀여워요','2021-08-17 14:18:14'),(12,24,12,0,0,20,NULL,'우와! 라이브 방송 기대하겠습니다!! 기억할게요!!','2021-08-18 00:37:53'),(13,21,10,2,1,20,NULL,'잘 부탁드려용!','2021-08-18 00:42:30'),(14,29,14,0,0,NULL,'4190000','하ㅣ이','2021-08-18 00:44:04'),(15,32,15,0,0,NULL,'4190000','귀여운 고양이 ㅎㅎ 만나고 싶네요~ ','2021-08-18 01:00:45'),(16,34,16,0,0,NULL,'3700000','이거 뭔가요?','2021-08-18 01:04:39'),(17,34,16,1,1,NULL,'3700000','뭐가 없어요!','2021-08-18 01:05:05'),(18,34,16,2,1,NULL,'3980000','김불독님의 시츄라고 했는데 불독이었습니다.','2021-08-18 01:05:15'),(19,34,19,0,0,NULL,'4190000','김불독불독~!','2021-08-18 01:05:17'),(20,34,16,3,1,NULL,'4190000','시츄>? 불독? 읭? ㅋㅋㅋ','2021-08-18 01:05:35'),(21,34,21,0,0,NULL,'3980000','불독님 시츄는 어디있나요?','2021-08-18 01:05:45'),(23,32,23,0,0,NULL,'3700000','털 엄청 날리죠?','2021-08-18 01:22:38'),(24,32,15,1,1,NULL,'3700000','고양이 털 많이 날리나요?','2021-08-18 01:22:49'),(25,28,25,0,0,NULL,'3700000','이거 고양이 어딨어요?','2021-08-18 01:23:17'),(26,35,26,0,0,NULL,'3700000','저희 보호소 놀러오세요~~~','2021-08-18 01:41:34'),(27,31,27,0,0,NULL,'3700000','비숑 얼굴이 너무 똥그랗네요~~ 아주 이쁩니당!\n저희 울산 보호소도 놀러와주세요~','2021-08-18 01:42:17'),(28,21,10,3,1,NULL,'3700000','뭐가 맞아용??','2021-08-18 01:42:53'),(29,21,29,0,0,NULL,'3700000','반가워요 도갱님~~ 저희 울산 보호소도 한번 놀러와 보세요!\nhttps://i5a408.p.ssafy.io:8080/shelterdetail/3700000','2021-08-18 01:44:12'),(30,35,26,1,1,NULL,'3700000','https://i5a408.p.ssafy.io:8080/shelterdetail/3700000','2021-08-18 01:44:37'),(31,48,31,0,0,NULL,'3700000','저희 보호소입니다! 한번씩 들려주세요~\nhttps://i5a408.p.ssafy.io:8080/shelterdetail/3700000','2021-08-18 01:58:53'),(32,48,32,0,0,19,NULL,'ㅠㅜㅠㅜ 너무 슬프네요 ㅜㅠㅠ 좋은 곳으로 갔을 거에요! 힘내세요~~','2021-08-18 02:06:50'),(33,49,33,0,0,18,NULL,'귀여운 짜글이 ㅠㅠ 영상이 너무너무 귀여웡요 ?','2021-08-18 02:41:35'),(34,48,34,0,0,18,NULL,'ㅜㅜ 랑말아 좋은곳으로 가렴 ㅠㅠ 보호소님들도 힘내세요!','2021-08-18 02:42:07'),(35,48,31,1,1,18,NULL,'화이팅! ','2021-08-18 02:42:20'),(36,35,36,0,0,18,NULL,'ㅋㅋㅋㅋ 귀여운사진 ㅎㅎ 근데 사진이 넘 옛날 스러워요~~','2021-08-18 02:42:49'),(37,28,37,0,0,18,NULL,'으악 귀여워라 이 고앵이는 이름이 몬가요!? ','2021-08-18 02:43:14'),(38,51,38,0,0,19,NULL,'우와 트리가 정말 귀엽네요!!!\n혹시 저희 솜털이 좀 찾아주실 수 있나요,,, 제가 게시글 남겼으니 한번 꼭 읽어주세요','2021-08-18 11:01:33'),(39,52,39,0,0,19,NULL,'제발요 ㅜㅠㅠㅠ 꼭 연락 부탁드립니다!','2021-08-18 11:04:29'),(52,52,52,0,0,NULL,'4190000','솜털아,, 얼른 집으로  돌아가렴 ㅠㅠ 꼭 찾길..','2021-08-18 11:11:35'),(53,51,53,0,0,NULL,'4190000','으악 넘 귀여워요 !!!! 트리야 ~~❤️❤️❤️❤️','2021-08-18 11:12:12'),(54,50,54,0,0,NULL,'4190000','저희 아가들도 한 번 사줘야겠어요~!?','2021-08-18 11:12:37'),(55,49,55,0,0,NULL,'4190000','ㅋㅋㅋㅋ강하다...','2021-08-18 11:13:44'),(56,28,56,0,0,NULL,'4190000','멍멍님 프사가 강렬하네요 ㅎㅎ ','2021-08-18 11:14:08'),(57,32,57,0,0,NULL,'4190000','복슬복슬 ㅎㅎ ','2021-08-18 11:14:32'),(58,52,58,0,0,NULL,'3910000','솜털이 발견하는 즉시 연락 드리겠습니다!','2021-08-18 13:53:44'),(59,50,54,1,1,NULL,'3910000','오 너무 좋네요','2021-08-18 14:52:47'),(61,51,53,1,1,22,NULL,'트리 ㅠㅠ','2021-08-18 16:51:47'),(62,51,62,0,0,22,NULL,'트리 넘 귀여워요~!','2021-08-18 16:55:37'),(63,51,38,1,1,22,NULL,'솜털이 ㅜㅜ ','2021-08-18 16:55:46');
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shelter`
--

DROP TABLE IF EXISTS `shelter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shelter` (
  `agencycode` varchar(50) NOT NULL,
  `agencyname` varchar(45) NOT NULL,
  `closeday` varchar(45) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `week_open_time` varchar(45) DEFAULT NULL,
  `week_end_time` varchar(45) DEFAULT NULL,
  `addr` varchar(225) DEFAULT NULL,
  `tel` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`agencycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shelter`
--

LOCK TABLES `shelter` WRITE;
/*!40000 ALTER TABLE `shelter` DISABLE KEYS */;
INSERT INTO `shelter` VALUES ('3010000','서울특별시 야생동물 구조관리센터','토요일+일요일',37.468095,126.953151,'09:00','18:00','서울특별시 관악구 관악로1 서울대학교 수의과대학 동물병원 (B1)','02-880-8659'),('3040000','서울시 야생동물센터','연중무휴',37.468192,126.953172,'09:00','08:00','서울 관악구 관악로 1 서울대학교 80동','02-880-8659'),('3070000','서울시 야생동물 구조센터','연중무휴',37.46803851,126.959296,'09:00','18:00','서울특별시 관악구 관악로 1','02-880-8659'),('3120000','서울시 야생동물 구조관리센터','연중무휴',37.467501,126.953611,'09:00','18:00','서울특별시 관악구 관악로1(신림동 서울대학교 수의과대학 야생동물센터)','02-880-8659'),('3220000','서울시야생동물센터','연중무휴',37.46803851,126.959296,'09:00','18:00','서울특별시 관악구 관악로1(신림동 서울대학교 수의과대학 야생동물센터)','02-880-8659'),('3410000','동인동물병원','일요일+국경일',35.8683913178,128.609676,'09:30','18:30','대구광역시 중구 국채보상로 724(동인동4가)','053-424-4311'),('3420000','다사랑종합동물병원','공휴일+국경일',35.878708,128.610019,'09:30','17:30','대구광역시 동구 신암로86-1','053-942-0805'),('3550000','다오동물병원','일요일+공휴일',37.5387816901,126.7358173797,'10:00','18:00','인천광역시 계양구 계산새로 71','032-548-0275'),('3700000','울산광역시 야생동물구조센터','연중무휴',35.52858231,129.2766344,'09:00','18:00','울산광역시 남구 남부순환도로293번길 25-3','052-226-0434'),('3820000','한국동물구조관리협회','일요일 법정공휴일',37.8701158122,126.9835430508,'09:00','18:00','경기도 양주시 남면 감악산로 63-37','031-867-9119'),('3910000','경기도야생동물구조관리센터','토요일+일요일+공휴일',37.13202593,127.1183287,'09:00','18:00','경기도 평택시 진위면 동천길 132-93','031-8008-6212'),('3980000','아마존동물병원','일요일',37.6006439133,127.1294674937,'09:00','18:00','경기도 구리시 경춘로 128','031-564-0075'),('4030000','유동물병원','일요일+공휴일',37.32132115,126.953535,'09:00','20:00','경기도 의왕시 부곡중앙로 41','031-462-8277'),('4040000','하남동물병원','휴일',37.537157,127.204029,'10:30','19:30','경기도 하남시 신평로 37','031-793-7802'),('4080000','이성준동물병원','일요일',37.006555,127.274781,'09:30','19:00','경기도 안성시 중앙로 431-1','031-673-5858'),('4170000','유일동물병원','연중무휴',37.481074,127.534913,'06:00','20:00','경기도 양평군 양평읍 미리내길 96','031-772-2727'),('4180000','(사)한국멸종위기 야생동식물보호협회 춘천시지부','연중무휴',37.8757645229,127.7156174837,'09:00','18:00','강원도 춘천시 공지로 482번길 8(근화동)','033-241-8275'),('4190000','야생생물관리협회 원주지회','휴무없음',37.352559,127.946391,'09:00','24:00','강원도 원주시 원일로 159','033-732-5523'),('4610000','충남야생동물구조센터','공휴일+토요일+일요일+(휴일당직자1인근무)',36.6698883395,126.8594140853,'09:00','18:00','충청남도 예산군 예산읍 대학로 54','041-330-1666'),('4680000','전북야생동물구조관리센터','연중무휴',35.94245239,126.9599065,'09:00','18:00','전라북도 익산시 고봉로 79','063-0850-0983'),('4720000','전북야생동물구조관리센터','연중무휴',35.94245239,126.9599065,'09:00','18:00','전라북도 익산시 마동 고봉로 79 전북대학교익산캠퍼스 3공학관','063-850-0983'),('4820000','전남야생동물관리센터','무휴',34.8901974153,127.4841772361,'09:00','18:00','전라남도 순천시 순천만길 922-15(인월동)','061-749-4800'),('5080000','파랑새동물병원','일요일+공휴일',36.16208508,128.3489706,'09:00','18:00','경상북도 구미시 고아읍 들성로11길 6-5','054-456-4791'),('5120000','제일가축병원','휴무없음',36.601523,128.202174,'09:00','18:00','경상북도 문경시 호서로 82','054-553-3308'),('5180000','영덕야생동물구조센터','토요일+일요일',36.415009,129.3653995835,'09:00','18:00','경상북도 영덕군 영덕읍 군청길 116','054-730-6182'),('5220000','제일동물병원','토요일+일요일+공휴일',35.998451,128.3968911,'09:00','18:00','경상북도 칠곡군 왜관읍 2번도로길 121','054-973-8300'),('5350000','장유동물병원','일요일(휴일종료시각: 5시)',35.1960914,128.7947602,'10:00','19:00','경상남도 김해시 능동로 11','055-313-7582'),('5570000','충북 야생동물센터','토요일+일요일+공휴일',36.728384,127.438086,'09:00','18:00','충청북도 청주시 청원구 오창읍 양청4길 45','043-216-3328'),('5590000','사단법인 한국동물구조관리협회','일요일+공휴일',37.87011581,126.9835431,'09:00','18:00','경기도 양주시 남면 감악산로 63-37','031-867-9119'),('5600000','한국동물구조관리협회','공휴일(단 토요일 16:00)',37.8700725,126.9832252,'09:00','17:00','경기도 양주시 남면 감악산로 63-37','031-867-9119'),('5670000','오케이동물병원','토요일',35.15223916,128.6678617,'09:00','18:00','경상남도 창원시 진해구 충장로 143-1','055-546-7588'),('6260000','부산야생동물치료센터','연중무휴',35.10581585,128.9431594,'09:00','18:00','부산광역시 사하구 낙동남로 1240-2','051-209-2090'),('6290000','광주광역시야생동물구조관리센터','연중무휴',35.16225673,126.8434511,'09:00','18:00','광주광역시 서구 무진대로 576','062-613-6651'),('6300000','대전야생동물구조관리센터','연중무휴',36.36836824,127.3420001,'09:00','18:00','대전광역시 유성구 대학로 99(충남대학교 수의과대학내)','042-821-7930'),('6310000','울산광역시야생동물구조관리센터','연중무휴',35.528777899,129.2768223919,'09:00','18:00','울산광역시 남구 남부순환도로 293번길 25-3','052-256-5322'),('6410000','경기도야생동물구조관리센터','토요일+일요일+공휴일',37.1319753,127.1185099,'09:00','18:00','경기도 평택시 진위면 동천길 132-93','031-8008-6212'),('6440000','충남야생동물구조센터','토요일+일요일+공휴일',36.66988681,126.859415,'09:00','18:00','충청남도 예산군 예산읍 대학로 54','041-330-1666'),('6480000','경남야생동물센터','연중무휴',35.1567151125,128.0980020769,'09:00','18:00','경상남도 진주시 진주대로 501','055-754-9575'),('6500000','제주야생동물구조센터','연중무휴',33.4437015039,126.5647043217,'09:00','22:00','제주특별자치도 제주시 516로 2946','064-752-9982'),('B553560','울산광역시 야생동물구조관리센터','없음',35.5287779,129.2768224,'09:00','18:00','울산광역시 남구 남부순환도로 293번길 25-3','052-256-5322');
/*!40000 ALTER TABLE `shelter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `no` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `img` varchar(500) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'USER',
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (18,'dign1571@naver.com','도경이','$2a$10$0d7sw4JgGDSEQpWR25BfGeeJ1DHHqI3y67Xau/P4JIi0Mjk2etFMe','010-1111-1111','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A9%E1%84%8B%E1%85%A3%E1%86%BC1.png','USER','normal'),(19,'test@ssafy.com','킹보카도','$2a$10$aLJY71yM4qmrmql/PNzhUOiVVMnkzelUqSDEMvVyPtw8CrdvHV6c2','010-8892-4155','https://petmily.s3.ap-northeast-2.amazonaws.com/images.jpeg','USER','normal'),(20,'junkuill@naver.com','박멍멍','$2a$10$OPMRUmtb.2oLOl552itYCe7LmYX5jlQqRHlB1BIVJC3h.gt4PHUuq','010-1234-5678','https://petmily.s3.ap-northeast-2.amazonaws.com/%E1%84%92%E1%85%A5%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B5.png','USER','normal'),(21,'kyinlee@naver.com','김불독','$2a$10$1tAONe5CT/ufINl0qyUZWuhtwbUHNjA21sq/l72Y.ELg5MSBXPHgS','010-4444-4444','https://petmily.s3.ap-northeast-2.amazonaws.com/unnamed.jpg','USER','normal'),(22,'dokk0923@gmail.com','규썹','$2a$10$gcCtpp1slVX9z6E8sk2t6eiiIq.n07J1U/jAgeWe3NAw67uHnikAa','010-1111-2223','https://petmily.s3.ap-northeast-2.amazonaws.com/%EA%B3%A0%EC%96%91%EC%9D%B41.jpg','USER','normal');
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

-- Dump completed on 2021-08-18 17:35:20
