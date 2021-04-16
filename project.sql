DROP DATABASE IF EXISTS project;
CREATE DATABASE project;
use project; 

DROP TABLE IF EXISTS user;
CREATE TABLE user (
userId INT AUTO_INCREMENT, -- PK
	role CHAR(1), -- (faculty, student, public)
	firstName VARCHAR(25),
	lastName VARCHAR(25),
	Password VARCHAR(30),
	Email_or_username VARCHAR(30),
PRIMARY KEY (userId)
);

DROP TABLE IF EXISTS contact; 
CREATE TABLE contact (
	userId INT, -- FK
	phoneNumber VARCHAR(10),
	email VARCHAR (50) NOT NULL,
	CONSTRAINT userIdcontact FOREIGN KEY (userId) REFERENCES user(userId) ON DELETE CASCADE
);


DROP TABLE IF EXISTS interests;
CREATE TABLE interests (
	interestId INT AUTO_INCREMENT, -- PK,
	interests TEXT,
	PRIMARY KEY (interestId)
);

DROP TABLE IF EXISTS user_interests;
CREATE TABLE user_interests (
userId INT, -- FK
interestId INT, -- FK
CONSTRAINT user_interestsUserId FOREIGN KEY (userId) REFERENCES user(userId) ON DELETE CASCADE,
CONSTRAINT user_interestsInterestId FOREIGN KEY (interestId) REFERENCES interests(interestId) ON DELETE CASCADE
);

DROP TABLE IF EXISTS works;
CREATE TABLE works (
	worksId INT AUTO_INCREMENT, -- PK,
	userId INT, -- FK
	Abstract TEXT,
	Date DATE,
	PRIMARY KEY (worksId),
	CONSTRAINT worksUserId FOREIGN KEY (userId) REFERENCES user(userId) ON DELETE CASCADE
);
