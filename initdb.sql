drop database if exists car_dealership_db;
create database car_dealership_db;

use car_dealership_db;

create or replace table addresses (
	address_id int not null primary key,
	country varchar(50) not null,
	city varchar(50) not null,
	street varchar(50),
	building_number varchar(10) not null,
	postal_code varchar(15) not null,
	phone varchar(15) not null,
	constraint address_unique unique(country, city, street, postal_code, building_number)
);

create or replace table customers (
	customer_id int not null primary key,
	address_id int not null,
	first_name varchar(50) not null, 
	last_name varchar(50) not null,
	constraint customer_unique unique (address_id, first_name, last_name),
	foreign key (address_id) references addresses(address_id),
	index (first_name, last_name)
);

create or replace table cars (
	car_id int not null primary key,
	name varchar(50) not null,
	release_date date not null,
	description varchar(300) not null,
	price decimal(10,2) not null

);

create or replace table car_dealerships (
	car_dealership_id int not null primary key,
	address_id int not null unique key,
	foreign key (address_id) references addresses(address_id)
);

create or replace table purchases (
	purchase_id int not null primary key,
	car_id int not null,
	customer_id int not null,
	car_dealership_id int not null,
	purchase_date date not null,
	price decimal(10, 2) not null,
	
	
	constraint purchase_unique unique (car_id, customer_id),
	foreign key (car_id) references cars(car_id),
	foreign key (customer_id) references customers(customer_id),
	foreign key (car_dealership_id) references car_dealerships(car_dealership_id)
);


create or replace table repairs (
	repair_id int not null primary key,
	-- mechanic_id int not null,
	car_id int not null,
	car_dealership_id int not null,
	customer_id int not null,
	price decimal(10, 2) not null,
	submission_date date not null,
	end_date date not null,
	customers_description varchar(300) not null,
	mechanics_description varchar(300) not null,
	
	foreign key (car_id) references cars(car_id),
	foreign key (customer_id) references customers(customer_id),
	foreign key (car_dealership_id) references car_dealerships(car_dealership_id)
);

create or replace table stock (
	car_dealership_id int not null,
	car_id int not null,
	available_number int not null,
	
	primary key (car_dealership_id, car_id),
	foreign key (car_id) references cars(car_id),
	foreign key (car_dealership_id)  references car_dealerships(car_dealership_id)

);

