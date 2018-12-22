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
		from cars_purchases_num p left join cars_repairs_num r on p.car_id = r.car_id;
	return result;


end;
$$$

select get_cars_sorted_by_repairs_purchases_ratio();

insert into customers values (1, "john", "travolta", "aaaa@dasda.pl", "123321123");
insert into customers values (2, "jim", "hendrix", "aaaassdad@dasda.pl", "1321321123");
insert into customers values (3, "george", "bush", "g@b.org", "124321123");
insert into customers values (4, "donald", "trump", "donald@trump.com", "166621123");

insert into cars values (1,"Renault Laguna",1.2, 12, 'AUTOGAS', "asdsadasd", 123.31);
insert into cars values (2,"Skoda Octavia",1.3, 16, 'PETROL', "ggggadsdsadasd", 5523.31);
insert into cars values (3,"Fiat 126p",1.8, 1, 'WATER', "eeeeeee", 333.31);
insert into cars values (4,"Ford focus", 2.0, 1, 'PETROL', "eewqeq", 223.31);

insert into car_dealerships values(1, "car dealership no 1", "Warsaw", "Slowackiego", "12", "21-123", "123321123", "cardlrshp1@gmail.com");
insert into car_dealerships values(2, "car dealership no 2", "Poznan", "Mickiewicza", "15", "22-223", "155555555", "cardlrshp2@gmail.com");

insert into stock values (1, 1, 123);
insert into stock values (1, 2, 444);
insert into stock values (1, 3, 55);
insert into stock values (2, 2, 223);

insert into purchases values(1, 1, 1, 1, "2010-02-04", 4542.21);
insert into purchases values(2, 2, 1, 1, "2011-02-04", 1232.21);
insert into purchases values(3, 2, 1, 1, "2012-02-04", 3213122.21);
insert into purchases values(4, 3, 1, 1, "2013-02-04", 453211.21);
insert into purchases values(5, 3, 1, 1, "2014-02-04", 553412.21);
insert into purchases values(6, 3, 1, 1, "2014-02-04", 553412.21);
insert into purchases values(7, 3, 1, 1, "2014-02-04", 553412.21);
insert into purchases values(8, 3, 1, 1, "2014-02-04", 553412.21);
insert into purchases values(9, 3, 1, 1, "2014-02-04", 553412.21);


insert into repairs values(1, 1, 1, 1, null, 1235, "2010-02-04", "2010-02-06", "broken", "fixed");
insert into repairs values(2, 1, 1, 1, null, 1523, "2010-02-04", "2010-02-06", "please fix", "fixed");
/*insert into repairs values(3, 1, 1, 1, 1523, "2010-02-04", "2010-02-06", "broken", "fixed");
insert into repairs values(4, 2, 1, 1, 1235, "2010-02-04", "2010-02-06", "broken", "fixed");
insert into repairs values(5, 2, 1, 1, 3123, "2010-02-04", "2010-02-06", "broken", "fixed");
insert into repairs values(6, 3, 1, 1, 13223, "2010-02-04", "2010-02-06", "broken", "fixed");
insert into repairs values(7, 4, 1, 1, 1231, "2010-02-04", "2010-02-06", "broken", "fixed");
*/
