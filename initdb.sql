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

create or replace table fuels (
	id int not null primary key,
	name varchar(50) not null
);

create or replace table cars (
	id int not null primary key,
	name varchar(50) not null,
	engine_displacement decimal(4,2) not null,
	acceleration decimal (4,2) not null,
	fuel_id int not null,
	release_date date not null,
	description varchar(300) not null,
	price decimal(10,2) not null,
	
	foreign key (fuel_id) references fuels(id)
);

create or replace table car_dealerships (
	id int not null primary key,
	name varchar(50) not null unique key,
	city varchar(50) not null,
	street varchar(50) not null,
	building_number varchar(10) not null,
	postal_code varchar(15) not null,
	phone varchar(15) not null
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

select * from customers;
select * from cars;

insert into customers values (1, "john", "travolta", "aaaa@dasda.pl", "123321123");
insert into cars values (1, "Renault Laguna", "2010-09-09", "asdsadasd", 123.31);
insert into car_dealerships values(1, "car dealership no 1", "Warsaw", "Slowackiego", "12", "21-123", "123321123");
insert into purchases values(1, 1, 1, 1, "2010-02-04", 4542.21);
insert into fuels values(1, "petrol")