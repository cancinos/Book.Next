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
-- Table structure for table `description_bayes`
--

DROP TABLE IF EXISTS `description_bayes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `description_bayes` (
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
-- Dumping data for table `description_bayes`
--

LOCK TABLES `description_bayes` WRITE;
/*!40000 ALTER TABLE `description_bayes` DISABLE KEYS */;
INSERT INTO `description_bayes` VALUES ('1620',0,0,1,0,0),('1781',1,0,0,0,0),('17th',0,0,0,1,0),('1942',0,1,0,0,0),('1965',2,0,0,0,0),('about',0,1,0,0,0),('above',1,0,0,0,0),('accident',0,1,0,0,0),('account',0,0,1,0,0),('acquired',0,1,0,0,0),('additional',1,0,0,0,0),('adjust',0,0,1,0,0),('adventure',0,0,1,0,0),('adventures',1,0,0,0,0),('africa',0,0,1,0,0),('after',2,0,1,0,0),('ages',1,0,0,0,0),('also',1,0,0,0,0),('although',0,0,1,0,0),('american',0,0,0,1,0),('analysis',1,0,0,0,0),('angela',0,0,0,1,0),('another',0,0,1,0,0),('apart',0,0,0,0,1),('apartment',2,0,0,0,0),('appomattox',2,0,0,0,0),('april',0,0,0,1,0),('around',0,2,0,0,0),('artisans',0,0,0,1,0),('arts',1,0,0,0,0),('asked',0,1,0,0,0),('attending',0,0,1,0,0),('aunt',0,0,2,0,0),('baby',0,0,1,0,0),('back',0,0,1,0,0),('band',0,1,0,0,0),('banged',0,0,0,1,0),('barbed',0,1,0,0,0),('bautista',0,0,0,0,1),('because',0,0,1,0,0),('becomes',0,0,0,1,0),('bedroom',0,0,0,1,0),('been',0,1,0,0,0),('before',0,0,1,0,0),('began',2,0,0,0,0),('begins',0,0,1,0,0),('benefit',1,0,0,0,0),('berry',0,0,1,0,0),('best',0,0,0,1,0),('bigotry',0,0,0,1,0),('bisque',0,2,0,0,0),('bleak',0,1,0,0,0),('blew',0,0,0,1,0),('blood',0,4,0,0,0),('blue',0,2,0,0,0),('boarded',0,1,0,0,0),('bones',0,4,0,0,0),('book',1,0,1,0,0),('born',0,6,0,2,0),('break',0,4,1,0,0),('bridget',0,0,1,0,0),('bristled',0,1,0,0,0),('brooklyn',0,1,0,0,0),('building',0,1,0,0,0),('bullies',0,0,1,0,0),('buried',0,0,1,0,0),('campo',0,2,0,0,0),('cannot',1,0,0,0,0),('carpet',0,2,0,0,0),('case',0,1,0,0,0),('cast',0,0,1,0,0),('catastrophic',0,4,0,0,0),('century',3,0,0,1,0),('chapter',1,0,0,0,0),('chapters',1,0,0,0,0),('characters',0,0,1,0,0),('children',0,0,0,0,1),('christ',0,2,0,0,0),('citizen',0,1,0,0,0),('civil',2,0,0,0,0),('clarity',0,4,0,0,0),('class',0,0,0,1,0),('clatter',0,0,0,1,0),('clinton',0,0,0,0,1),('cold',0,0,0,1,0),('come',0,0,1,0,0),('communications',0,1,0,0,0),('community',0,1,0,0,0),('concept',1,0,0,0,0),('connecticut',2,0,0,0,0),('considered',1,0,0,0,0),('contain',1,0,0,0,0),('contains',0,0,0,1,0),('contemporary',1,0,0,0,0),('cornwallis',1,0,0,0,0),('could',2,0,0,0,0),('couldn',0,1,0,0,0),('course',0,0,1,0,0),('cousin',1,0,0,0,0),('cove',0,0,1,0,0),('covered',0,2,0,0,0),('covering',2,0,0,0,0),('croatian',2,0,0,0,0),('croatias',1,0,0,0,0),('crossroads',0,0,0,1,0),('crucial',1,0,0,0,0),('curse',0,0,1,0,0),('dainty',0,2,0,0,0),('daughter',0,0,0,1,0),('dazzling',0,2,0,0,0),('dealing',1,0,0,0,0),('debut',0,0,0,1,0),('dedication',0,0,1,0,0),('describes',0,0,0,1,0),('destiny',0,0,1,0,0),('destroys',0,0,1,0,0),('devastating',0,0,1,0,0),('diary',0,0,1,0,0),('dies',0,0,1,0,0),('dimension',0,0,1,0,0),('disagree',0,0,1,0,0),('discover',0,0,3,0,0),('dive',0,0,1,0,0),('divided',1,0,0,0,0),('diving',0,0,1,0,0),('documents',1,0,0,0,0),('doors',0,1,0,0,0),('dumb',0,0,1,0,0),('dutch',0,0,0,1,0),('early',0,4,0,1,0),('edward',0,0,1,0,0),('effect',0,0,1,0,0),('eleven',1,0,0,0,0),('elinor',1,0,0,0,0),('elizabeth',0,0,1,0,0),('emilys',0,0,1,0,0),('entangled',0,2,0,0,0),('escorted',0,1,0,0,0),('especially',1,0,0,0,0),('essentially',1,0,0,0,0),('established',1,0,0,0,0),('estate',1,0,0,0,0),('estranged',0,0,1,0,0),('even',0,1,0,1,0),('event',0,0,1,0,0),('explode',0,4,0,0,0),('extensively',1,0,0,0,0),('fall',0,4,0,0,0),('familiar',1,0,0,0,0),('family',0,0,2,0,0),('father',0,6,2,0,0),('fiction',0,0,0,1,0),('fifties',0,1,0,0,0),('fight',0,0,1,0,0),('figures',0,0,1,0,0),('find',0,1,0,0,0),('finds',0,0,1,0,0),('fingers',0,2,0,0,0),('first',1,2,2,0,0),('flowers',0,2,0,0,0),('following',1,0,0,0,0),('freshman',0,0,1,0,0),('from',1,2,1,1,1),('gang',0,0,1,0,0),('general',1,0,0,0,0),('geography',1,0,0,0,0),('girl',0,1,0,0,0),('glass',0,4,0,0,0),('going',0,1,0,0,0),('golden',0,0,2,0,0),('goodwill',0,0,0,0,1),('great',0,0,1,0,0),('growing',0,0,0,0,1),('guides',0,0,1,0,0),('half',0,0,1,0,0),('halfway',0,1,0,0,0),('hand',0,6,0,0,0),('have',0,7,0,0,0),('haven',2,0,0,0,0),('heal',0,0,1,0,0),('heart',0,0,2,0,0),('help',0,0,2,0,0),('helps',0,0,1,0,0),('high',0,0,1,0,0),('historical',2,0,0,0,0),('history',2,0,0,0,0),('hogwarts',0,0,1,0,0),('home',1,0,0,0,0),('house',0,0,1,0,0),('household',0,0,0,1,0),('imitate',1,0,0,0,0),('included',2,0,0,0,0),('including',1,0,0,1,0),('innumerable',0,2,0,0,0),('instantaneous',0,1,0,0,0),('international',0,0,0,0,1),('interpreted',1,0,0,0,0),('into',1,0,1,1,0),('invite',0,4,0,0,0),('island',0,0,1,0,0),('january',0,1,0,0,0),('jesus',0,2,0,0,0),('judaism',2,0,0,0,0),('keep',0,1,0,0,0),('knew',0,2,0,0,0),('know',0,0,0,0,1),('later',0,1,0,0,0),('laura',0,0,1,0,0),('leaving',0,0,1,0,0),('library',0,0,1,0,0),('life',0,0,1,1,0),('like',0,4,0,0,0),('lindsay',0,0,0,1,0),('list',0,2,0,0,0),('listed',1,0,0,0,0),('literature',1,0,0,0,0),('little',0,1,0,0,0),('live',0,0,1,0,0),('lives',0,1,0,0,0),('living',0,0,1,0,0),('loose',0,0,0,1,0),('lord',1,0,0,0,0),('loud',0,0,0,1,0),('love',0,0,2,0,0),('lucy',0,0,1,0,0),('made',0,0,1,0,0),('maid',0,0,0,1,0),('making',0,1,0,0,0),('manner',0,0,0,1,0),('many',0,0,1,0,0),('maps',1,0,0,0,0),('married',0,6,0,0,0),('mayflower',0,0,1,0,0),('melindas',0,0,1,0,0),('memorial',0,0,1,0,0),('mercy',0,0,0,0,1),('middle',1,1,0,0,0),('migrant',0,0,0,0,1),('model',0,0,0,1,0),('modern',2,0,0,0,0),('moments',0,4,0,0,0),('moore',1,0,0,0,0),('more',2,0,0,0,0),('morning',0,2,0,0,0),('most',1,0,0,0,0),('mother',0,9,0,0,1),('motley',0,0,1,0,0),('murdered',0,0,1,0,0),('must',0,0,1,0,0),('musty',2,0,0,0,0),('name',0,0,1,0,1),('narrative',1,0,0,0,0),('nationalism',1,0,0,0,0),('near',1,0,1,0,0),('needs',0,0,1,0,0),('neglect',0,0,1,0,0),('nineteenth',1,0,0,0,0),('nita',0,0,1,0,0),('observation',0,0,0,1,0),('ohio',0,1,0,0,0),('open',0,2,0,0,0),('orphan',0,0,1,0,0),('others',0,0,1,0,0),('outrageous',0,0,1,0,0),('over',0,4,0,1,0),('painter',0,0,0,1,0),('palm',0,2,0,0,0),('parade',0,0,0,1,0),('parents',0,1,0,0,1),('part',1,0,0,0,0),('participate',0,0,1,0,0),('parts',1,0,0,0,0),('past',1,0,0,0,0),('pattern',1,0,0,0,0),('philippines',0,0,0,0,1),('pieces',0,0,0,1,0),('pirate',0,0,1,0,0),('pirates',0,0,1,0,0),('place',0,4,0,0,0),('pledge',0,0,0,0,1),('porcelain',0,2,0,0,0),('power',0,0,0,1,0),('preface',1,0,0,0,0),('presents',0,0,1,0,0),('prince',0,0,1,0,0),('princela',0,0,0,0,1),('prone',1,0,0,0,0),('proves',0,0,1,0,0),('quoted',1,0,0,0,0),('radios',0,1,0,0,0),('real',1,0,0,0,0),('really',0,0,1,0,0),('recipients',0,0,0,0,1),('reflecting',0,2,0,0,0),('religion',2,0,0,0,0),('religious',0,0,0,1,0),('remains',0,0,1,0,0),('report',0,1,0,0,0),('rescued',0,0,1,0,0),('resourcefulness',0,0,1,0,0),('rigid',0,0,0,1,0),('road',0,2,0,1,0),('rose',1,0,0,0,0),('said',0,1,0,0,0),('sand',0,0,1,0,0),('school',0,0,2,0,0),('scrape',0,4,0,0,0),('scuba',0,0,1,0,0),('second',1,0,0,0,0),('secret',0,0,1,0,0),('sent',0,0,1,0,0),('shades',0,2,0,0,0),('share',1,0,0,0,0),('shattered',0,4,0,0,0),('ship',0,0,1,0,0),('shivered',0,0,0,1,0),('should',0,6,0,0,0),('shutter',0,0,0,1,0),('side',0,2,0,0,0),('sister',0,0,1,0,0),('situations',0,4,0,0,0),('skin',0,4,0,0,0),('small',0,0,0,0,1),('smart',0,0,1,0,0),('smash',0,4,0,0,0),('sneakers',0,0,1,0,0),('some',1,12,0,0,0),('south',0,0,1,0,0),('spirit',1,0,0,0,0),('split',0,4,0,0,0),('spring',1,0,1,0,0),('stained',0,4,0,0,0),('standing',0,2,0,0,0),('stayed',0,6,0,0,0),('stern',0,0,1,0,0),('stone',0,0,0,0,1),('streak',0,1,0,0,0),('strong',0,0,0,1,0),('stubborn',0,1,0,0,0),('summer',0,0,1,0,0),('sure',0,0,1,0,0),('surrender',1,0,0,0,0),('system',0,0,0,1,0),('taking',0,0,1,0,0),('talk',0,1,0,0,0),('tells',0,1,0,0,0),('tentacles',0,2,0,0,0),('textbooks',1,0,0,0,0),('than',2,0,0,0,0),('that',0,7,1,1,0),('their',0,2,2,0,1),('then',0,4,0,0,0),('there',0,7,3,0,0),('they',0,8,0,0,1),('thick',0,2,0,0,0),('thirteen',0,0,1,2,0),('those',1,0,0,0,0),('though',0,0,0,1,0),('through',0,5,0,0,0),('thumbs',0,2,0,0,0),('tommy',0,0,1,0,0),('tormented',0,0,1,0,0),('town',0,0,0,0,1),('traumatic',0,0,1,0,0),('travel',0,0,1,0,0),('trip',0,0,1,0,0),('twelve',0,0,1,0,0),('twins',0,0,2,0,0),('uncle',0,0,1,0,0),('vermeer',0,0,0,1,0),('very',0,2,0,0,0),('virginia',1,0,0,0,0),('walls',0,0,1,0,0),('wanted',0,2,0,0,0),('wasn',0,1,0,0,0),('ways',0,0,1,0,0),('were',0,5,0,0,0),('when',0,1,2,2,0),('where',0,12,1,0,0),('whether',0,0,0,0,1),('which',1,2,1,0,0),('while',0,0,1,0,0),('wind',0,0,0,1,0),('window',0,4,0,1,0),('windowpane',0,4,0,0,0),('windows',0,1,0,0,0),('wire',0,1,0,0,0),('witches',0,0,1,0,0),('with',2,8,2,1,0),('witnessing',1,0,0,0,0),('wizardry',0,0,1,0,0),('wizards',0,0,1,0,0),('wolves',0,0,2,0,0),('woman',0,0,0,1,0),('women',0,2,0,0,0),('wont',0,0,1,0,0),('work',0,0,0,1,0),('world',0,0,1,0,0),('worth',0,0,1,0,0),('would',0,1,0,0,0),('writing',0,0,1,0,0),('year',1,0,4,0,0),('york',0,6,0,0,0),('yorktown',1,0,0,0,0),('young',1,2,3,0,0),('your',0,4,0,0,0);
/*!40000 ALTER TABLE `description_bayes` ENABLE KEYS */;
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
