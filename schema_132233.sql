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
	id int not null primary key auto_increment,
	name varchar(50) not null unique key,
	city varchar(50) not null,
	street varchar(50) not null,
	building_number varchar(10) not null,
	postal_code varchar(15) not null,
	phone varchar(15) not null,
	email varchar(50) not null
);

create or replace table purchases (
	id int not null primary key auto_increment,
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
	id int not null primary key auto_increment,
	
	car_id int not null,
	car_dealership_id int not null,
	customer_id int not null,
	purchase_id int null,
	
	price decimal(10, 2),
	submission_date date not null,
	end_date date,
	customers_description varchar(300) not null,
	mechanics_description varchar(300),
	
	foreign key (car_id) references cars(id),
	foreign key (customer_id) references customers(id),
	foreign key (car_dealership_id) references car_dealerships(id),
	foreign key (purchase_id) references purchases(id)
);

create or replace table stock (
	car_dealership_id int not null,
	car_id int not null,
	available_number int not null,
	
	primary key (car_dealership_id, car_id),
	foreign key (car_id) references cars(id),
	foreign key (car_dealership_id)  references car_dealerships(id)

);

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

declare result varchar(100);
		select group_concat(concat(p.car_id, ';', coalesce(num_of_repairs, 0) / num_of_purchases) order by coalesce(num_of_repairs, 0) / num_of_purchases separator ',' ) into result
		from cars_purchases_num  p left join cars_repairs_num r on p.car_id = r.car_id;
	return result;
end;
$$$
