
CREATE TABLE BidList (
  BidListId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bidQuantity DOUBLE,
  askQuantity DOUBLE,
  bid DOUBLE ,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bidListDate TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125)
);

CREATE TABLE Trade (
  TradeId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buyQuantity DOUBLE,
  sellQuantity DOUBLE,
  buyPrice DOUBLE ,
  sellPrice DOUBLE,
  tradeDate TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125)
);

CREATE TABLE CurvePoint (
  Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  curveId int,
  asOfDate TIMESTAMP,
  term DOUBLE ,
  valeur DOUBLE ,
  creationDate TIMESTAMP
);

CREATE TABLE Rating (
  Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  moodysRating VARCHAR(125),
  sandPRating VARCHAR(125),
  fitchRating VARCHAR(125),
  orderNumber int
);

CREATE TABLE RuleName (
  Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sqlStr VARCHAR(125),
  sqlPart VARCHAR(125)
);

CREATE TABLE Users (
  Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  fullname VARCHAR(125),
  username VARCHAR(125),
  password VARCHAR(125),
  role VARCHAR(125)
);

INSERT INTO Users(fullname, username, password, role) VALUES('Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ADMIN');
INSERT INTO Users(fullname, username, password, role) VALUES('User', 'user', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'USER');