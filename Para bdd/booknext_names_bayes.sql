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
-- Table structure for table `names_bayes`
--

DROP TABLE IF EXISTS `names_bayes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `names_bayes` (
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
-- Dumping data for table `names_bayes`
--

LOCK TABLES `names_bayes` WRITE;
/*!40000 ALTER TABLE `names_bayes` DISABLE KEYS */;
INSERT INTO `names_bayes` VALUES ('1895',1,0,0,0,0),('1990',1,0,0,0,0),('abajo',1,0,0,0,0),('action',0,1,0,0,0),('album',0,1,0,0,0),('alienate',0,1,0,0,0),('almost',0,1,0,0,0),('alone',1,0,0,0,0),('ancient',0,0,0,0,1),('angel',0,0,0,1,0),('angela',0,2,0,0,0),('angelas',0,1,0,0,0),('apples',0,1,0,0,0),('ashes',0,3,0,0,0),('attic',1,0,0,0,0),('august',0,1,0,0,0),('awakened',0,1,0,0,0),('baby',0,1,0,0,0),('bangkok',1,0,0,0,0),('beer',0,1,0,0,0),('between',0,1,0,0,0),('bitch',0,1,0,0,0),('bleachy',0,1,0,0,0),('bligh',0,1,0,0,0),('book',0,1,0,0,1),('brain',0,0,1,0,0),('california',0,1,0,0,0),('called',0,1,0,0,0),('captain',0,1,0,0,0),('catalunya',1,0,0,0,0),('catherine',0,0,0,0,1),('charles',0,0,0,1,0),('chicken',0,0,0,0,2),('child',0,1,0,0,0),('cicero',0,1,0,0,0),('cinema',1,0,0,0,0),('circles',0,0,1,0,0),('classics',0,2,0,0,0),('close',0,0,0,1,0),('color',0,1,0,0,0),('comme',0,1,0,0,0),('common',0,0,0,1,0),('confederates',1,0,0,0,0),('confessions',0,2,0,0,0),('contarla',0,1,0,0,0),('coupe',0,0,1,0,0),('creek',0,0,0,1,0),('crime',1,0,0,0,0),('croatia',1,0,0,0,0),('cunning',1,0,0,0,0),('curse',0,0,1,0,0),('cyanide',0,1,0,0,0),('dancing',1,0,0,0,0),('daughter',0,1,0,0,0),('dear',0,0,0,1,0),('decadent',0,1,0,0,0),('delano',0,1,0,0,0),('desarrollo',0,1,0,0,0),('desire',0,0,0,1,0),('diaries',0,0,0,0,1),('diner',0,1,0,0,0),('dirty',0,1,0,0,0),('divinely',0,1,0,0,0),('draussen',0,1,0,0,0),('east',0,0,0,2,0),('eater',0,2,0,0,0),('edge',0,1,0,0,0),('emily',0,0,1,0,0),('english',0,2,0,0,0),('espan',0,0,0,0,1),('falls',0,0,0,1,0),('family',0,1,0,0,0),('fear',1,0,0,0,0),('feel',0,1,0,0,0),('fight',1,0,0,0,0),('final',0,1,0,0,0),('fire',0,1,0,0,0),('first',1,0,0,0,0),('fleetwood',0,1,0,0,0),('fotografi',1,0,0,0,0),('franklin',0,1,0,0,0),('friedens',1,0,0,0,0),('friends',0,1,0,0,0),('from',0,1,0,0,0),('girl',0,1,0,0,0),('global',0,0,0,0,1),('golden',0,1,1,0,0),('goldfish',0,0,1,0,0),('good',1,0,0,0,0),('gracefully',1,0,0,0,0),('graves',0,0,0,0,1),('great',1,0,0,0,0),('ground',1,0,0,0,0),('guys',0,1,0,0,0),('haired',0,1,0,0,0),('hallowed',1,0,0,0,0),('harding',1,0,0,0,0),('harry',0,0,3,0,0),('heart',0,1,1,1,0),('heeled',0,1,0,0,0),('histo',1,0,0,0,0),('historia',1,0,0,0,1),('history',1,0,0,0,0),('honky',0,1,0,0,0),('hotel',0,1,0,0,0),('hrer',0,1,0,0,0),('image',0,1,0,0,0),('immediate',0,1,0,0,0),('insane',1,0,0,0,0),('into',0,0,0,1,0),('irish',0,0,0,1,0),('jesus',0,1,0,0,0),('jokes',0,1,0,0,0),('journey',0,0,1,0,0),('joven',0,0,0,1,0),('junkie',0,1,0,0,0),('king',0,1,0,0,0),('kuralt',0,0,0,1,0),('lang',0,1,0,0,0),('leben',0,1,0,0,0),('leggende',0,0,0,1,0),('leonardo',0,1,0,0,0),('life',0,1,0,0,1),('little',1,0,0,0,0),('liza',0,1,0,0,0),('london',0,0,0,1,0),('long',0,2,0,0,0),('lose',0,1,0,0,0),('lost',0,1,0,0,0),('love',1,0,0,0,0),('magic',0,0,0,1,0),('maharajah',0,1,0,0,0),('maid',1,0,0,0,0),('many',0,0,1,0,0),('mary',1,1,0,0,0),('masters',1,0,0,0,0),('medieval',0,0,0,0,1),('meet',0,0,1,0,0),('mein',0,1,0,0,0),('midwifery',0,1,0,0,0),('mighty',1,0,0,0,0),('minnelli',0,1,0,0,0),('miranda',0,0,0,1,0),('misbegotten',0,1,0,0,0),('moon',0,0,1,0,0),('more',1,0,0,0,0),('motion',0,1,0,0,0),('mujeres',0,0,0,0,1),('napoletane',0,0,0,1,0),('nation',0,2,0,0,0),('native',0,1,0,0,0),('nazi',0,1,0,0,0),('nightmare',0,1,0,0,0),('nomad',0,1,0,0,0),('nootebooms',0,1,0,0,0),('novice',0,0,0,1,0),('officer',0,1,0,0,0),('opium',0,2,0,0,0),('other',0,0,0,1,0),('para',0,1,0,0,0),('passion',0,0,0,1,0),('passionate',0,1,0,0,0),('paul',1,0,0,0,0),('penny',0,1,0,0,0),('people',0,1,0,0,0),('perla',0,0,0,1,0),('portable',0,1,0,0,0),('potter',0,0,3,0,0),('provence',0,0,0,1,0),('prozac',0,2,0,0,0),('queen',0,3,0,0,0),('raggy',0,1,0,0,0),('reinos',0,1,0,0,0),('republic',1,0,0,0,0),('return',1,0,0,0,0),('revere',1,0,0,0,0),('rights',1,0,0,0,0),('road',0,0,0,1,0),('rode',1,0,0,0,0),('roosevelt',0,1,0,0,0),('round',0,1,0,0,0),('running',0,0,0,1,0),('sage',0,1,0,0,0),('scene',1,0,0,0,0),('schatten',1,0,0,0,0),('scissors',0,0,0,1,0),('second',0,0,0,0,2),('settings',1,0,0,0,0),('sexual',0,0,0,0,1),('shadows',0,1,0,0,0),('shakespearean',0,0,0,0,1),('sheba',0,1,0,0,0),('sickness',0,1,0,0,0),('silence',0,1,0,0,0),('silenzio',0,1,0,0,0),('silk',0,1,0,0,0),('silver',1,0,0,0,0),('sisters',0,0,0,0,1),('song',0,1,0,0,0),('sorcerer',0,0,2,0,0),('soul',0,0,0,0,2),('souls',0,1,0,0,0),('soup',0,0,0,0,2),('speak',0,0,1,0,0),('spiritual',0,1,0,0,0),('spit',0,0,0,0,1),('stargazing',0,1,0,0,0),('starke',0,1,0,0,0),('stone',0,0,2,0,1),('stones',0,0,1,0,0),('storie',0,0,0,1,0),('stories',0,0,0,1,0),('strawberries',0,1,0,0,0),('stream',0,0,1,0,0),('survival',0,1,0,0,0),('survivor',0,1,0,0,0),('swapped',0,0,1,0,0),('taifa',0,1,0,0,0),('tale',0,0,0,1,0),('than',1,0,0,0,0),('there',0,2,0,0,0),('this',1,0,0,0,0),('times',0,0,0,0,3),('titanic',0,1,0,0,0),('tonic',0,0,0,1,0),('toons',0,1,0,0,0),('toujours',0,0,0,1,0),('trauma',0,1,0,0,0),('trilogy',0,1,0,0,0),('tudor',1,0,0,0,0),('turbulent',0,1,0,0,0),('turn',1,0,0,0,0),('undercurrents',0,1,0,0,0),('upper',0,0,0,1,0),('uppity',0,0,0,0,3),('utero',0,0,0,0,1),('villa',1,0,0,0,0),('vinci',0,1,0,0,0),('virginia',1,0,0,0,0),('vivi',0,1,0,0,0),('vivir',0,1,0,0,0),('walk',0,1,0,1,0),('walls',0,0,1,0,0),('want',0,0,1,0,0),('warren',1,0,0,0,0),('watching',1,0,0,0,0),('water',0,1,0,0,0),('week',0,1,0,0,0),('whether',1,0,0,0,0),('wife',0,1,0,0,0),('wind',1,0,0,0,0),('wings',0,1,0,0,0),('with',0,1,0,2,0),('wizard',0,0,1,0,0),('wolves',0,0,1,0,0),('woman',0,1,0,0,3),('women',0,0,0,0,4),('wordsworth',0,2,0,0,0),('world',0,0,1,0,0),('worship',0,0,0,1,0),('your',0,0,0,0,1),('zapata',1,0,0,0,0);
/*!40000 ALTER TABLE `names_bayes` ENABLE KEYS */;
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
