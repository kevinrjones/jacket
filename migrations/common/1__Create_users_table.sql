create table USERS (
	ID INT NOT NULL AUTO_INCREMENT,
	FIRSTNAME varchar(100) not null,
    LASTNAME varchar(100) not null,
    EMAIL varchar(100) not null,
    PASSWORD varchar(100) not null,
    PRIMARY KEY (`id`)
);