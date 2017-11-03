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


--Store Procedure
create or replace procedure add_user(
p_username IN jpausers_yash.username%type,
p_password IN jpausers_yash.password%type,
p_role IN jpausers_yash.role%type)
as
begin
insert into jpausers_yash(username,password,role)
values(p_username,p_password,p_role);
commit;
end;
/

--EXECUTE ADD_USER('pro','pro','pro');


--Store Procedure
create or replace procedure update_user(
p_username IN jpausers_yash.username%type,
p_password IN jpausers_yash.password%type,
p_role IN users_yash.role%type)
as
begin
update jpausers_yash set password=p_password,role=p_role
where username=p_username;
commit;
end;
/

--EXECUTE UPDATE_USER('pro','change_pro','change_pro');

select * from jpausers_yash;