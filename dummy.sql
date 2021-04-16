USE project;

-- user inserts
INSERT INTO user(role,firstName,lastName,password,Email_or_username) VALUES("F","Stan","Smith","password","professor@rit.edu");
INSERT INTO user(role,firstName,lastName,password,Email_or_username) VALUES("S","Jessica","Torres","password","student@rit.edu");
INSERT INTO user(role,firstName,lastName,password,Email_or_username) VALUES("P","Tom","Hernandez","password","person@gmail.com");

-- contact inserts 
INSERT INTO contact(userId,phoneNumber,email) VALUES("1","1234567890","professor@rit.edu");
INSERT INTO contact(userId,phoneNumber,email) VALUES("2","0987654321","student@rit.edu");
INSERT INTO contact(userId,phoneNumber,email) VALUES("3","1643827592","person@gmail.com");

-- interests inserts 
INSERT INTO interests(interests) VALUES("This is some interest");
INSERT INTO interests(interests) VALUES("Interest Number 2");
INSERT INTO interests(interests) VALUES("A third interest");

-- user interest inserts 
INSERT INTO user_interests(userId,interestId) VALUES("1","2");
INSERT INTO user_interests(userId,interestId) VALUES("2","3");
INSERT INTO user_interests(userId,interestId) VALUES("3","1");

CREATE TABLE works (
	worksId INT AUTO_INCREMENT, -- PK,
	userId INT, -- FK
	Abstract TEXT,
	Date DATE,
	PRIMARY KEY (worksId),
	CONSTRAINT worksUserId FOREIGN KEY (userId) REFERENCES user(userId) ON DELETE CASCADE
);
-- works inserts
INSERT INTO works(userId,Abstract,Date) VALUES("1","Abstract of a work","2017-06-15");
INSERT INTO works(userId,Abstract,Date) VALUES("2","A different abstract","2019-07-08");
INSERT INTO works(userId,Abstract,Date) VALUES("3","Third abstract","2020-09-06");