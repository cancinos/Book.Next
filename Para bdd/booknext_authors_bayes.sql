-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: booknext
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `authors_bayes`
--

DROP TABLE IF EXISTS `authors_bayes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors_bayes` (
  `word` varchar(20) NOT NULL,
  `genre1` int(11) DEFAULT NULL,
  `genre2` int(11) DEFAULT NULL,
  `genre3` int(11) DEFAULT NULL,
  `genre4` int(11) DEFAULT NULL,
  `genre5` int(11) DEFAULT NULL,
  PRIMARY KEY (`word`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors_bayes`
--

LOCK TABLES `authors_bayes` WRITE;
/*!40000 ALTER TABLE `authors_bayes` DISABLE KEYS */;
INSERT INTO `authors_bayes` VALUES ('abdul',0,1,0,0,0),('alec',1,0,0,0,0),('alex',1,0,0,0,0),('alice',1,0,0,0,0),('anderson',0,0,1,0,0),('andy',0,1,0,0,0),('anhua',0,1,0,0,0),('anthony',0,1,0,0,0),('arax',0,1,0,0,0),('augusten',0,0,0,1,0),('azuela',1,0,0,0,0),('barbara',0,0,0,1,1),('barbera',0,1,0,0,0),('beam',1,0,0,0,0),('beer',0,1,0,0,0),('benedetto',0,0,0,1,0),('betty',0,1,0,0,0),('bill',0,1,0,0,0),('black',0,1,0,0,0),('bonnie',0,1,0,0,0),('boris',0,0,0,0,1),('boylan',0,1,0,0,0),('brestin',0,0,0,0,1),('brian',0,0,1,0,0),('bruce',1,0,0,0,0),('burkowski',0,1,0,0,0),('burroughs',0,0,0,1,0),('campbell',0,1,0,0,0),('canfield',0,0,0,0,2),('carey',0,1,0,0,0),('carol',0,0,0,0,1),('carolyn',0,0,1,0,0),('catherine',0,0,0,1,1),('catton',1,0,0,0,0),('cees',0,1,0,0,0),('charles',0,0,0,1,0),('chevalier',0,0,0,1,0),('chip',0,1,0,0,0),('christopher',0,1,0,0,0),('cohen',0,0,0,1,0),('colijn',0,1,0,0,0),('collin',1,0,0,0,0),('coman',0,0,1,0,0),('conrad',0,1,0,0,0),('croce',0,0,0,1,0),('cullen',0,1,0,0,0),('curtis',1,0,0,0,0),('cuthbert',0,1,0,0,0),('dailey',1,0,0,0,0),('david',0,3,1,0,0),('delau',0,1,0,0,0),('diane',0,0,1,0,0),('doyle',0,0,1,0,0),('drew',0,1,0,0,0),('duane',0,0,1,0,0),('dubner',0,1,0,0,0),('eberhardt',0,1,0,0,0),('edith',0,1,0,0,0),('ehrenreich',0,0,0,0,1),('elisa',0,1,0,0,0),('elizabeth',0,2,0,0,0),('everitt',0,1,0,0,0),('faolain',0,2,0,0,0),('finney',0,1,0,0,0),('fleetwood',0,1,0,0,0),('foos',0,0,0,0,1),('francine',0,0,1,0,0),('frank',0,3,0,0,0),('frazer',0,0,0,1,0),('gabriel',0,2,0,0,0),('gaiman',0,0,2,0,0),('gallick',0,1,0,0,0),('galvin',0,1,0,0,0),('garcia',0,1,0,0,0),('gary',1,0,0,0,0),('gaskin',0,1,0,0,0),('gazi',1,0,0,0,0),('gellis',0,0,0,1,0),('gildiner',0,0,0,1,0),('giles',0,0,0,1,0),('gillespie',0,1,0,0,0),('goldberg',0,1,0,0,0),('goytisolo',0,1,0,0,0),('griffin',0,0,0,0,1),('hahn',0,1,0,0,0),('halse',0,0,1,0,0),('helen',0,1,0,0,0),('helga',0,1,0,0,0),('hendrickson',1,0,0,0,0),('henley',0,0,0,1,0),('hill',0,1,0,0,0),('hollis',0,1,0,0,0),('horn',0,1,0,0,0),('horwitz',1,0,0,0,0),('hudson',0,1,0,0,0),('irma',0,0,0,1,0),('isabelle',0,1,0,0,0),('jack',0,1,0,0,2),('jacki',0,1,0,0,0),('james',0,1,0,0,0),('jane',0,1,0,0,0),('janet',1,0,0,0,0),('janice',0,1,0,0,0),('jean',0,1,0,0,0),('jennifer',0,1,0,0,0),('jessop',0,1,0,0,0),('jhabvala',0,0,0,1,0),('john',0,1,0,0,0),('jose',0,0,0,0,1),('joseph',0,1,0,0,0),('juan',0,1,0,0,0),('juska',0,1,0,0,0),('kalam',0,1,0,0,0),('kasey',0,0,0,1,0),('kathryn',0,0,1,0,0),('khalil',1,0,0,0,0),('krulak',1,0,0,0,0),('kuralt',0,0,0,1,0),('kurtz',0,0,0,1,0),('lasky',0,0,1,0,0),('laurie',0,0,1,0,1),('layton',0,1,0,0,0),('lefebvre',0,1,0,0,0),('lindberg',1,0,0,0,0),('llywelyn',0,0,0,1,0),('loades',1,0,0,0,0),('loup',1,0,0,0,0),('lucado',0,1,0,0,0),('luis',0,1,0,0,0),('lyden',0,1,0,0,0),('lynne',0,0,0,0,1),('manning',0,1,0,0,0),('marcel',0,1,0,0,0),('margaret',0,0,0,1,0),('margarita',1,0,0,0,0),('mari',0,0,0,0,1),('mariano',1,0,0,0,0),('marie',1,0,0,0,0),('mark',0,1,0,0,0),('marks',1,1,0,0,0),('marquez',0,1,0,0,0),('martha',0,1,0,0,0),('mary',0,1,0,0,0),('matt',0,0,0,1,0),('mayle',0,0,0,1,0),('mcbride',0,1,0,0,0),('mccourt',0,3,0,0,0),('mcnab',0,1,0,0,0),('michael',1,1,0,0,0),('michaels',0,0,0,1,0),('mick',0,1,0,0,0),('milgrim',0,0,1,0,0),('millet',0,0,0,0,1),('milligan',0,1,0,0,0),('miquel',1,0,0,0,0),('moix',1,0,0,0,0),('molly',0,0,0,1,0),('montgomery',0,0,1,0,0),('morgan',0,0,0,1,0),('neil',0,0,2,0,0),('nooteboom',0,1,0,0,0),('nuala',0,2,0,0,0),('olsen',0,1,0,0,0),('orellana',1,0,0,0,0),('paine',1,0,0,0,0),('pascal',0,0,1,0,0),('patricia',0,0,0,1,0),('patrick',0,1,0,0,0),('pelzer',0,2,0,0,0),('peter',0,1,0,1,0),('pierre',0,1,0,0,0),('porter',1,0,0,0,0),('prawer',0,0,0,1,0),('quincey',0,2,0,0,0),('racionero',0,1,0,0,0),('rains',1,0,0,0,0),('ranicki',0,1,0,0,0),('rawicz',0,1,0,0,0),('raymond',0,1,0,0,0),('reich',0,1,0,0,0),('reinhard',0,1,0,0,0),('richard',2,0,0,0,0),('robert',1,0,0,0,0),('roberta',0,0,0,1,0),('roberts',0,0,1,0,0),('robertson',0,1,0,0,0),('rogers',0,0,0,1,0),('rosemary',0,0,0,1,0),('rowling',0,0,3,0,0),('ruth',0,0,0,1,0),('ryan',0,1,0,0,0),('rybczynski',2,0,0,0,0),('sally',1,0,0,0,0),('samir',1,0,0,0,0),('samuel',0,0,0,1,0),('sarah',0,1,0,0,0),('satya',0,1,0,0,0),('shelly',0,0,1,0,0),('shenkman',1,0,0,0,0),('shields',0,0,0,0,1),('silverman',0,1,0,0,0),('slavomir',0,1,0,0,0),('sontheimer',1,0,0,0,0),('sougez',1,0,0,0,0),('spike',0,1,0,0,0),('springer',0,1,0,0,0),('stephen',2,1,0,0,0),('stjepan',1,0,0,0,0),('strait',0,1,0,0,0),('susan',1,0,0,2,0),('swami',0,1,0,0,0),('thomas',1,2,0,0,0),('tippett',1,0,0,0,0),('tiscenko',0,1,0,0,0),('toby',0,1,0,0,0),('tony',1,0,0,0,0),('toohey',0,1,0,0,0),('tracy',0,0,0,1,0),('tripp',0,0,1,0,0),('tucker',0,1,0,0,0),('turner',1,0,0,0,0),('ursula',0,1,0,0,0),('valerie',0,0,1,0,0),('vedant',0,1,0,0,0),('vian',0,0,0,0,1),('vicki',0,0,0,0,3),('victor',1,0,0,0,0),('violet',0,1,0,0,0),('voltes',0,0,0,0,1),('waugh',1,0,0,0,0),('whoopi',0,1,0,0,0),('wiggs',0,0,0,2,0),('william',0,1,0,0,0),('witold',2,0,0,0,0),('wurtzel',0,2,0,0,0),('wylen',1,0,0,0,0),('young',0,1,0,0,0),('zukav',1,0,0,0,0);
/*!40000 ALTER TABLE `authors_bayes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-08 21:38:49
