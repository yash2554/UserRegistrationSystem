--Drop Table
DROP TABLE users_yash;
COMMIT;
--Create Table
CREATE TABLE users_yash
 (
  username VARCHAR2(20) PRIMARY KEY,
  password VARCHAR2(20),
  role VARCHAR2(20)
 );
 COMMIT;
 --View Table Contents
SELECT * FROM users_yash;


INSERT INTO users_yash(USERNAME, PASSWORD, ROLE)
VALUES ('yash','pass','admin');


 --View Table Contents
SELECT * FROM users_yash;


--Store Procedure
create or replace procedure add_user(
p_username IN users_yash.username%type,
p_password IN users_yash.password%type,
p_role IN users_yash.role%type)
as
begin
insert into users_yash(username,password,role)
values(p_username,p_password,p_role);
commit;
end;
/

EXECUTE ADD_USER('pro','pro','pro');


--Store Procedure
create or replace procedure update_user(
p_username IN users_yash.username%type,
p_password IN users_yash.password%type,
p_role IN users_yash.role%type)
as
begin
update users_yash set password=p_password,role=p_role
where username=p_username;
commit;
end;
/

EXECUTE UPDATE_USER('pro','change_pro','change_pro');

select * from USERS_YASH;