--activity 1
drop table salesman;
create table salesman(SALESMAN_ID int primary key, salesman_name varchar2(20) not null, salesman_city varchar(20), commission int);
describe salesman;

--activity 2
INSERT INTO salesman
VALUES (5001,'James Hoog','New York',15); 

insert all
	into salesman values (5002,'Nail Knite','Paris',13)
	into salesman values (5005,'Pit Alex','London',11)
	into salesman values (5006,'McLyon','Paris',14)
	into salesman values (5007,'Paul Adam','Rome',13)
	into salesman values (5003,'Lauson Hen','San Jose',125)
select 1 from dual;
select * from salesman;

--activity 3
select salesman_id, salesman_city from salesman;
select * from salesman where salesman_city = 'Paris';
select salesman_id, commission from salesman where SALESMAN_NAME ='Paul Adam';

--activity 4
alter table salesman 
add (grade int);

describe salesman;

update salesman
set grade = 100;

select * from salesman;

--acitivity 5
update salesman set grade = 200 where SALESMAN_CITY = 'Rome';
update salesman set grade = 300 where SALESMAN_name = 'James Hoog';
update salesman set SALESMAN_NAME = 'Pierre' WHERE SALESMAN_NAME = 'McLyon';

select * from salesman;