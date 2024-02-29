drop database if exists customer_db;

create database customer_db;

use customer_db;

create table customer (
	customer_id int,
	emailid varchar(20),
	name varchar(10),
	date_of_birth date,
	constraint ps_customer_id_pk primary key (customer_id)
);

insert into customer(customer_id, emailid, name, date_of_birth) values (1, 'martin@infy.com', 'martin', sysdate()- interval 9136 day);
commit;
select * from customer;