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
-- Table structure for table `publishers_bayes`
--

DROP TABLE IF EXISTS `publishers_bayes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publishers_bayes` (
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
-- Dumping data for table `publishers_bayes`
--

LOCK TABLES `publishers_bayes` WRITE;
/*!40000 ALTER TABLE `publishers_bayes` DISABLE KEYS */;
INSERT INTO `publishers_bayes` VALUES ('adams',0,0,0,0,1),('adelphi',0,0,0,1,0),('adult',0,1,0,0,0),('alianza',0,1,0,0,0),('amer',1,0,0,0,0),('anaya',1,0,0,0,0),('anstalt',0,1,0,0,0),('applewood',1,0,0,0,0),('arthur',0,0,1,0,0),('assoc',0,1,0,0,0),('astran',0,1,0,0,0),('avon',0,0,0,1,0),('bantam',1,0,0,0,0),('barnes',1,1,0,0,0),('berkley',0,0,0,1,0),('blackwell',1,0,0,0,0),('book',0,1,0,0,1),('books',4,14,4,0,4),('broadway',0,1,0,0,0),('cage',0,0,0,1,0),('canongate',0,1,0,0,0),('carol',0,1,0,0,0),('carpet',0,0,1,0,0),('catalunya',1,0,0,0,0),('catedra',1,0,0,0,0),('chariot',0,0,0,0,1),('cloud',0,1,0,0,0),('comercial',1,0,0,0,0),('communications',1,2,0,0,5),('company',0,1,1,1,0),('consortium',0,0,0,0,1),('corgi',0,1,0,0,0),('corp',1,0,0,0,1),('corporation',0,1,0,1,0),('counterpoint',0,0,0,1,0),('cumberland',1,0,0,0,0),('dacapo',0,1,0,0,0),('dell',0,1,0,0,0),('departament',1,0,0,0,0),('deutsche',0,1,0,0,0),('diamond',1,0,0,0,0),('diffusion',0,1,0,0,0),('dist',0,0,0,0,1),('distributed',0,1,0,0,0),('droemersche',0,0,0,1,0),('ediciones',1,0,0,0,0),('estate',0,1,0,1,0),('fiction',0,0,0,1,0),('fine',0,0,0,0,3),('firefly',0,1,0,0,0),('fourth',0,1,0,1,0),('free',0,1,0,0,0),('front',0,0,1,0,0),('gallimard',0,0,1,0,0),('games',0,0,1,0,0),('generalitat',1,0,0,0,0),('graywolf',0,1,0,0,0),('groundwood',0,0,1,0,0),('group',0,1,0,3,0),('grupo',1,0,0,0,0),('harper',0,1,0,0,0),('harpercollins',0,2,1,1,0),('harpersanfrancisco',0,1,0,0,0),('harvest',0,0,0,0,1),('health',0,2,0,0,2),('houghton',0,1,0,0,0),('house',2,3,0,1,0),('hyperion',0,1,0,0,0),('independent',0,0,0,1,0),('institute',2,0,0,0,0),('island',0,1,0,0,0),('jeunesse',0,0,1,0,0),('kensington',1,0,0,1,0),('knaur',0,0,0,1,0),('laurel',0,0,1,0,0),('leaf',0,0,1,0,0),('levine',0,0,1,0,0),('longman',0,1,0,0,0),('lotus',1,0,0,0,0),('lyons',0,1,0,0,0),('macadam',0,0,0,1,0),('macfarlane',0,1,0,0,0),('magic',0,0,1,0,0),('marino',0,1,0,0,0),('market',0,1,0,0,0),('marsilio',0,1,0,0,0),('mass',0,1,0,0,0),('media',0,0,0,0,1),('mercier',0,1,0,0,0),('metropolitan',0,0,0,0,1),('mifflin',0,1,0,0,0),('mitteldeutscher',0,1,0,0,0),('morrow',0,1,0,0,0),('multnomah',0,1,0,0,0),('naval',1,0,0,0,0),('noble',1,1,0,0,0),('orient',0,1,0,0,0),('overlook',0,2,0,0,0),('paper',0,0,0,1,0),('paperbacks',0,1,0,0,0),('paulist',1,0,0,0,0),('peace',1,0,0,0,0),('penguin',1,2,0,1,1),('perennial',1,2,0,0,0),('picador',0,0,0,1,0),('planeta',0,0,0,0,1),('plaza',0,1,0,0,0),('pleasant',0,0,1,0,0),('pocket',1,0,0,1,0),('press',2,11,0,1,0),('publicaffairs',1,2,0,0,0),('publications',0,0,1,0,0),('publishers',1,1,0,1,0),('publishing',2,2,0,4,1),('putnam',0,0,0,2,0),('random',1,1,0,1,0),('regan',0,1,0,0,0),('riverhead',0,4,0,0,0),('ross',0,1,0,0,0),('rotbuch',1,0,0,0,0),('sales',0,0,0,0,1),('santillana',0,0,0,1,0),('scarborough',0,1,0,0,0),('scholastic',0,0,3,0,0),('schuster',1,0,0,0,0),('scribner',1,2,0,1,0),('sheridan',0,1,0,0,0),('shoal',0,1,0,0,0),('simon',1,0,0,0,0),('speak',0,0,1,0,0),('states',1,0,0,0,0),('strand',0,1,0,0,0),('street',0,0,1,0,0),('studio',0,0,1,0,0),('suhrkamp',0,1,0,0,0),('sweet',0,0,1,0,0),('tamtam',0,0,0,0,1),('temple',0,1,0,0,0),('trade',0,1,0,1,0),('turner',0,1,0,0,0),('united',1,0,0,0,0),('university',0,1,0,0,0),('valley',0,0,1,0,0),('verlag',0,1,0,0,0),('verlags',0,1,0,0,0),('verlagsanstalt',0,0,0,1,0),('victor',0,0,0,0,1),('viking',0,0,1,0,0),('villard',0,1,0,0,0),('vintage',1,0,0,0,0),('virgin',0,2,0,0,0),('walter',0,1,0,0,0),('west',0,1,0,0,0),('white',1,1,1,0,0),('william',0,1,0,0,0),('wolf',0,0,1,0,0);
/*!40000 ALTER TABLE `publishers_bayes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-08 18:17:11
