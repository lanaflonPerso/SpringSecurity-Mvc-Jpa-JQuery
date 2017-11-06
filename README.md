# creditcard
Credit Card Management Java EE/DB driven WebbApp

Used technology  : <strong>Spring Core, Spring Mvc, Spring Security, BCrypt, Spring Tag, Jstl, Html5, JQuery, Creditly.js PlugIn (M.i.t. license), Typeahead.js Twitter PlugIn, Hibernate, MySql Database, Logback, Maven, Git, Web Server Target : Apache-Tomcat-7.0.53.</strong>

Objective : Simple and smart credit card web form ; Crypted user & password login security ; User registration ; Data validation ; Save/Search/Display data ;
________________________________

Please note :

- 2 active users already exist (Administrator unsername=admin | password=123456 ; Normal User : unsername=mauro | password=123456)

- Inside "script" folder you can find mysqlDbScript.sql to generate MySql database tables. Please note that the Db name is "test".

- Inside "resources" folder you can find the db-configuration.properties, please set correct Database username and password
(now it's unsername=??? | password=???)

- For the user registration section, in order to show how works a page in case of error, I voluntarily induce an exception
if you first register a user and after you try to register the same user again.
__________________________________
July 2017 - @author : Mauro Camelo
