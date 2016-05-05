DROP SCHEMA IF EXISTS BookNext;

create schema BookNext;
create user 'booknext' identified by 'book';

Grant all privileges on booknext.* to 'booknext';



use BookNext;

DROP TABLE IF EXISTS  user_book;

DROP TABLE IF EXISTS  book;

DROP TABLE IF EXISTS  users;

DROP TABLE IF EXISTS description_bayes;


CREATE TABLE description_bayes (
word varchar(20) primary key,
genre1 int,
genre2 int,
genre3 int,
genre4 int,
genre5 int
);

CREATE TABLE users (

id int auto_increment primary key,
username varchar(20),
fullname varchar(20),
birthday varchar(20),
passwoord varchar(20),
imagen varchar(200),
country varchar(20)

);



CREATE TABLE book (

isbn varchar(20) primary key,
book_name varchar(100),
author varchar (80),
imagen varchar (200),
publish_date varchar(20),
publisher varchar(40),
rating_average varchar(3),
description varchar (500),
genre varchar(100)
);

CREATE TABLE user_book (

   
isbn varchar(20),
id int,	
user_rating int(1),
user_liked int(1),
user_view int(1),
user_saved int(1),

FOREIGN KEY (isbn)
      REFERENCES book(isbn),

FOREIGN KEY (id)
      REFERENCES users(id)   

);



insert into users(username,fullname,birthday,passwoord,imagen,country) values ('Durini','Juan Carlos Duniri','1994/23/08','123pass','https://scontent-atl3-1.xx.fbcdn.net/v/t1.0-9/10306748_10152843932139579_7297473898267847923_n.jpg?oh=fc106c6a6fa384cb595f8dc75d668e28&oe=57AED28C','Guatemala');

insert into users(username,fullname,birthday,passwoord,imagen,country) values ('Cancinos','Pablo Cancinos','1994/23/08','123pass','https://scontent-atl3-1.xx.fbcdn.net/hphotos-xap1/v/t1.0-9/12801640_10153483713100983_577201434795815826_n.jpg?oh=263141cbcffb5d4a22718a3c5f56b4e4&oe=57A57DC9','Guatemala');

insert into users(username,fullname,birthday,passwoord,imagen,country) values ('Queme','Pablo Queme','1994/23/08','123pass','https://scontent-atl3-1.xx.fbcdn.net/v/t1.0-9/12512703_10153892622892726_5037909035613453449_n.jpg?oh=d0ddcdaa27ee81b3b908478feaa689bd&oe=57A6B22C','Guatemala');

insert into users(username,fullname,birthday,passwoord,imagen,country) values ('Ludwing','Ludwing Cano','1994/23/08','123pass','https://scontent-atl3-1.xx.fbcdn.net/t31.0-8/s960x960/12068740_10153524436238100_3708907581614942786_o.jpg','Guatemala');

CREATE TABLE `booknext`.`ann` (
  `idANN` INT NOT NULL AUTO_INCREMENT,
  `weightKey` VARCHAR(45) NOT NULL,
  `value` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`idANN`))
COMMENT = 'To store weights of Artificial neural network';

INSERT INTO `booknext`.`ann`
(`weightKey`,`value`)
VALUES
('weightKey(11, 0)', -4.55),
('weightKey(11, 1)', -13.24),
('weightKey(11, 2)', -2.19),
('weightKey(11, 3)', -2.81),
('weightKey(11, 4)', -1.69),
('weightKey(11, 5)', 6.11),
('weightKey(11, 6)', 12.33),
('weightKey(11, 7)', -10.35),
('weightKey(11, 8)', -7.05),
('weightKey(11, 9)', -8.32),
('weightKey(11, 10)', -.64),
('weightKey(12, 11)', -.65),
('weightKey(12, 12)', -.64),
('weightKey(12, 13)', -1.08),
('weightKey(12, 14)', -9.19),
('weightKey(12, 15)', -2.08),
('weightKey(12, 16)', -1.85),
('weightKey(12, 17)', -.08),
('weightKey(12, 18)', -1.49),
('weightKey(12, 19)', 4.93),
('weightKey(12, 20)', -1.01),
('weightKey(12, 21)', .43),
('weightKey(13, 22)', 8.42),
('weightKey(13, 23)', 2.13),
('weightKey(13, 24)', .85),
('weightKey(13, 25)', 1.07),
('weightKey(13, 26)', .45),
('weightKey(13, 27)', -4.39),
('weightKey(13, 28)', 1.52),
('weightKey(13, 29)', 1.61),
('weightKey(13, 30)', .52),
('weightKey(13, 31)', 1.13),
('weightKey(13, 32)', .16),
('weightKey(14, 33)', -1.2),
('weightKey(14, 34)', -1.09),
('weightKey(14, 35)', -1.48),
('weightKey(14, 36)', -.75),
('weightKey(14, 37)', -9.83),
('weightKey(14, 38)', -.39),
('weightKey(14, 39)', .04),
('weightKey(14, 40)', -.75),
('weightKey(14, 41)', -2.32),
('weightKey(14, 42)', 4.83),
('weightKey(14, 43)', .74),
('weightKey(15, 44)', .94),
('weightKey(15, 45)', -.45),
('weightKey(15, 46)', 1.17),
('weightKey(15, 47)', .98),
('weightKey(15, 48)', .97),
('weightKey(15, 49)', -1.21),
('weightKey(15, 50)', -.11),
('weightKey(15, 51)', -1.22),
('weightKey(15, 52)', -1.33),
('weightKey(15, 53)', -1.17),
('weightKey(15, 54)', -.06),
('weightKey(16, 55)', -2.87),
('weightKey(16, 56)', 14.57),
('weightKey(16, 57)', -5.81),
('weightKey(16, 58)', -4.43),
('weightKey(16, 59)', -4.31),
('weightKey(16, 60)', 2.69),
('weightKey(16, 61)', -19.92),
('weightKey(16, 62)', -2.05),
('weightKey(16, 63)', .16),
('weightKey(16, 64)', -.24),
('weightKey(16, 65)', -.21),
('weightKey(17, 66)', 8.26),
('weightKey(17, 67)', -12.2),
('weightKey(17, 68)', .89),
('weightKey(17, 69)', 1.65),
('weightKey(17, 70)', .27),
('weightKey(17, 71)', -19.46),
('weightKey(17, 72)', 1.68),
('weightKey(17, 73)', 6.67),
('weightKey(17, 74)', 1.44),
('weightKey(17, 75)', 1.76),
('weightKey(17, 76)', .98),
('weightKey(18, 77)', 4.09),
('weightKey(18, 78)', 4.21),
('weightKey(18, 79)', 12.59),
('weightKey(18, 80)', 2.79),
('weightKey(18, 81)', 2.24),
('weightKey(18, 82)', .19),
('weightKey(18, 83)', -.12),
('weightKey(18, 84)', -9.7),
('weightKey(18, 85)', 3.44),
('weightKey(18, 86)', 4.07),
('weightKey(18, 87)', .76),
('weightKey(19, 88)', -20.66),
('weightKey(19, 89)', -33.8),
('weightKey(19, 90)', 36.98),
('weightKey(19, 91)', -32.63),
('weightKey(19, 92)', -33.6),
('weightKey(19, 93)', -30.44),
('weightKey(19, 94)', -21.98),
('weightKey(19, 95)', 30.31),
('weightKey(19, 96)', .08);
