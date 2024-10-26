# My First CRUD app in Java

*  Create your MySQL database and fill the information in DBInfo.java file.

*  Create a user table that contains these columns.
   +--------------------+--------------+------+-----+---------+----------------+
   | Field              | Type         | Null | Key | Default | Extra          |
   +--------------------+--------------+------+-----+---------+----------------+
   | id                 | int          | NO   | PRI | NULL    | auto_increment |
   | first_name         | varchar(100) | YES  |     | NULL    |                |
   | last_name          | varchar(100) | YES  |     | NULL    |                |
   | email              | varchar(100) | YES  |     | NULL    |                |
   | encrypted_password | varchar(100) | YES  |     | NULL    |                |
   +--------------------+--------------+------+-----+---------+----------------+

*  Create a post table that contains these columns.
   +--------------+--------------+------+-----+---------+----------------+
   | Field        | Type         | Null | Key | Default | Extra          |
   +--------------+--------------+------+-----+---------+----------------+
   | post_id      | int          | NO   | PRI | NULL    | auto_increment |
   | post_content | text         | YES  |     | NULL    |                |
   | post_date    | date         | YES  |     | NULL    |                |
   | author_id    | int          | YES  | MUL | NULL    |                |
   | post_title   | varchar(100) | YES  |     | NULL    |                |
   +--------------+--------------+------+-----+---------+----------------+

*  Run the project by,

   $ java -cp .:lib/mysql-connector-j-9.0.0.jar:lib/jasypt-1.9.3.jar myPackage/Main.java
