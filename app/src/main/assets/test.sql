BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `Orders` (
	`orderID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`orderSum`	INTEGER NOT NULL,
	`deliveryTime`	INTEGER,
	`delivered`	INTEGER,
	`fk_clientID`	INTEGER,
	FOREIGN KEY(`fk_clientID`) REFERENCES `Clients`(`clientID`)
);
INSERT INTO `Orders` (orderID,orderSum,deliveryTime,delivered,fk_clientID) VALUES (1,242,171125,0,1);
INSERT INTO `Orders` (orderID,orderSum,deliveryTime,delivered,fk_clientID) VALUES (2,12349,180225,0,2);
INSERT INTO `Orders` (orderID,orderSum,deliveryTime,delivered,fk_clientID) VALUES (3,5463,171202,0,3);
INSERT INTO `Orders` (orderID,orderSum,deliveryTime,delivered,fk_clientID) VALUES (4,729,171205,0,4);
INSERT INTO `Orders` (orderID,orderSum,deliveryTime,delivered,fk_clientID) VALUES (5,192847,171223,0,5);
INSERT INTO `Orders` (orderID,orderSum,deliveryTime,delivered,fk_clientID) VALUES (6,2549,171126,0,6);
INSERT INTO `Orders` (orderID,orderSum,deliveryTime,delivered,fk_clientID) VALUES (7,108573,1801202,0,7);
INSERT INTO `Orders` (orderID,orderSum,deliveryTime,delivered,fk_clientID) VALUES (8,666,171115,0,8);
INSERT INTO `Orders` (orderID,orderSum,deliveryTime,delivered,fk_clientID) VALUES (9,35482,180105,0,9);
INSERT INTO `Orders` (orderID,orderSum,deliveryTime,delivered,fk_clientID) VALUES (10,96849,171224,0,10);
INSERT INTO `Orders` (orderID,orderSum,deliveryTime,delivered,fk_clientID) VALUES (11,453628,171110,1,11);
CREATE TABLE IF NOT EXISTS `Clients` (
	`clientID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`clientName`	TEXT,
	`contactPerson`	TEXT,
	`contactNumber`	INTEGER,
	`contactEmail`	TEXT,
	`clientAdress`	TEXT,
	`clientZipCode`	INTEGER,
	`clientCity`	TEXT
);
INSERT INTO `Clients` (clientID,clientName,contactPerson,contactNumber,contactEmail,clientAdress,clientZipCode,clientCity) VALUES (1,'Sven Svensson','Sven Svensson','46 738144093 ','sven.svensson@gmail.com','Södra vägen 17','411 35','Göteborg');
INSERT INTO `Clients` (clientID,clientName,contactPerson,contactNumber,contactEmail,clientAdress,clientZipCode,clientCity) VALUES (2,'Spotify','Glen Hysén','031 131313','glen@spotify.com','Ullevi vägen 45','411 34','Göteborg');
INSERT INTO `Clients` (clientID,clientName,contactPerson,contactNumber,contactEmail,clientAdress,clientZipCode,clientCity) VALUES (3,'Volvo','Per Person','031 590000','per@volvo.se','Volvo gatan 6','423 56','Torslanda');
INSERT INTO `Clients` (clientID,clientName,contactPerson,contactNumber,contactEmail,clientAdress,clientZipCode,clientCity) VALUES (4,'Birgit Nilsson','Birgit Nilsson','0322 621225','birgit.nilsson@hotmail.com','Kyrkogatan 1','447 33','Vårgårda');
INSERT INTO `Clients` (clientID,clientName,contactPerson,contactNumber,contactEmail,clientAdress,clientZipCode,clientCity) VALUES (5,'Interflora','Carin Svensson','46 324567890','carin@interflora.se','Postgatan 26','411 06','Göteborg');
INSERT INTO `Clients` (clientID,clientName,contactPerson,contactNumber,contactEmail,clientAdress,clientZipCode,clientCity) VALUES (6,'Ica Maxi','Mackan Maxi','46 764538294','mackan@maxi.se','Gymnasiegatan 1','442 34','Kungälv');
INSERT INTO `Clients` (clientID,clientName,contactPerson,contactNumber,contactEmail,clientAdress,clientZipCode,clientCity) VALUES (7,'Emma Lidell','Emma Lidell ','46 734891304','emmisen@live.se','Sörgårdsvägen 34','445 37','Bohus');
INSERT INTO `Clients` (clientID,clientName,contactPerson,contactNumber,contactEmail,clientAdress,clientZipCode,clientCity) VALUES (8,'Ria Buhlin','Felix','46 738144093','rialia@live.se','Södra vägen 17','46331','Lilla Edet');
INSERT INTO `Clients` (clientID,clientName,contactPerson,contactNumber,contactEmail,clientAdress,clientZipCode,clientCity) VALUES (9,'IT HÖGSKOLAN','Marcus Andersson','46 705169513','marcus@iths.se','Ebbe Lieberathsgatan 18C','412 65','Göteborg');
INSERT INTO `Clients` (clientID,clientName,contactPerson,contactNumber,contactEmail,clientAdress,clientZipCode,clientCity) VALUES (10,'Facebook','Mark Zuckerberg','46 234567890','facebook@facebook.com','Pixbovägen 123','432 36','Mölndal');
INSERT INTO `Clients` (clientID,clientName,contactPerson,contactNumber,contactEmail,clientAdress,clientZipCode,clientCity) VALUES (11,'Santa Claus','Tomten','031 24 24 24','santa@claus.com','Nordpolen 1','124 24','Grönland');
COMMIT;
