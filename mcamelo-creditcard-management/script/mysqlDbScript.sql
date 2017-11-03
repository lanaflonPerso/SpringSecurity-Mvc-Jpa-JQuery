/*
Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : test
Target Server Type    : MYSQL
Date: 2017-07-24 21:07:25
*/

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

INSERT INTO users(username,password,enabled)
VALUES ('admin','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);
INSERT INTO users(username,password,enabled)
VALUES ('mauro','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);

INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('mauro', 'ROLE_USER');

CREATE TABLE creditcard (
  number varchar(16) NOT NULL,
  expiryDate date NOT NULL,
  cvv int(3) NOT NULL,
  cardName varchar(30) NOT NULL,
  username varchar(45) NOT NULL,
  PRIMARY KEY (number, username),
  UNIQUE KEY uni_creditcard (number),
  KEY username (username),
  CONSTRAINT username FOREIGN KEY (username) REFERENCES users (username));