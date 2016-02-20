CREATE TABLE articles (
  ID INT NOT NULL AUTO_INCREMENT,
  URL VARCHAR(1024) NOT NULL,
  ARCHIVED BIT(1) NULL,
  FAVOURITE BIT(1) NULL,
  PRIMARY KEY (`id`))
;