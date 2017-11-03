--Drop Table
DROP TABLE jpausers_yash;
COMMIT;
--Create Table
CREATE TABLE jpausers_yash
 (
  username VARCHAR2(20) PRIMARY KEY,
  password VARCHAR2(20),
  role VARCHAR2(20)
 );
 COMMIT;
 --View Table Contents
SELECT * FROM jpausers_yash;


--INSERT INTO jpausers_yash(USERNAME, PASSWORD, ROLE) VALUES ('yash','pass','admin');


 --View Table Contents
SELECT * FROM jpausers_yash;