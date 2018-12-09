drop database if exists car_dealership_db;
create database car_dealership_db;

use car_dealership_db;

create or replace table customers (
	id int not null primary key auto_increment,
	first_name varchar(50) not null, 
	last_name varchar(50) not null,
	email varchar(50) not null unique key,
	phone varchar(15) not null unique key,
	index (first_name, last_name)
);

create or replace table cars (
	id int not null primary key auto_increment,
	name varchar(50) not null,
	engine_displacement decimal(4,2) not null,
	time0to100kmph decimal (4,2) not null,
	fuel varchar(20) not null,
	description varchar(300) not null,
	price decimal(10,2) not null

);

create or replace table car_dealerships (
	id int not null primary key,
	name varchar(50) not null unique key,
	city varchar(50) not null,
	street varchar(50) not null,
	building_number varchar(10) not null,
	postal_code varchar(15) not null,
	phone varchar(15) not null,
	email varchar(50) not null
);

create or replace table purchases (
	id int not null primary key,
	car_id int not null,
	customer_id int not null,
	car_dealership_id int not null,
	purchase_date date not null,
	price decimal(10, 2) not null,
	
	foreign key (car_id) references cars(id),
	foreign key (customer_id) references customers(id),
	foreign key (car_dealership_id) references car_dealerships(id)
);


create or replace table repairs (
	id int not null primary key,
	
	car_id int not null,
	car_dealership_id int not null,
	customer_id int not null,
	
	price decimal(10, 2) not null,
	submission_date date not null,
	end_date date not null,
	customers_description varchar(300) not null,
	mechanics_description varchar(300) not null,
	
	foreign key (car_id) references cars(id),
	foreign key (customer_id) references customers(id),
	foreign key (car_dealership_id) references car_dealerships(id)
);

create or replace table stock (
	car_dealership_id int not null,
	car_id int not null,
	available_number int not null,
	
	primary key (car_dealership_id, car_id),
	foreign key (car_id) references cars(id),
	foreign key (car_dealership_id)  references car_dealerships(id)

);

/*
create or replace table car_images (
	id int not null,
	car_id int not null,
	img longblob not null,
	
	foreign key(car_id) references cars(id)
);
*/

delimiter $$$
create or replace procedure decrease_prices(in precentage decimal(5,2))
begin
	update cars set price = price * (100-precentage) / 100;
end;
$$$

delimiter $$$
create or replace procedure increase_prices(in precentage decimal(5,2))
begin
	update cars set price = price * (100+precentage) / 100;
end;
$$$

create or replace view cars_purchases_num as select car_id, count(*) as num_of_purchases from purchases group by car_id;
create or replace view cars_repairs_num as select car_id, count(*) as num_of_repairs from repairs group by car_id;


delimiter $$$
create or replace function get_cars_sorted_by_repairs_purchases_ratio() returns varchar(100)
begin
/*	declare result varchar(100);
		select group_concat(car_id order by coalesce(num_of_repairs, 0) / num_of_purchases asc separator ',' ) into result
		from cars_purchases_num left join cars_repairs_num using car_id;
	return result;
*/
declare result varchar(100);
		select group_concat(car_id order by coalesce(num_of_repairs, 0) / num_of_purchases separator ',' ) into result
		from cars_purchases_num left join cars_repairs_num on cars_purchases_num.car_id = cars_repairs_num.car_id;
	return result;


end;
$$$


select * from customers;
select * from cars;
#select * from car_images;

insert into customers values (1, "john", "travolta", "aaaa@dasda.pl", "123321123");
insert into cars values (1,"Renault Laguna",1.2, 12, 'AUTOGAS', "asdsadasd", 123.31);
insert into car_dealerships values(1, "car dealership no 1", "Warsaw", "Slowackiego", "12", "21-123", "123321123", "cardlrshp1@gmail.com");
insert into purchases values(1, 1, 1, 1, "2010-02-04", 4542.21);
