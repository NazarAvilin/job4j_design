-- CREATE DATABASE Garage;

 create table body (
     id serial primary key,
     type varchar(200)
 );

 create table engine (
     id serial primary key,
     type varchar(200)
 );

 create table gearbox (
     id serial primary key,
     type varchar(200)
 );

 create table car (
     id serial primary key,
     name varchar(200),
     body_id int references body(id),
     engine_id int references engine(id),
     gearbox_id int references gearbox(id)
 );

 insert into body(type) values ('Sedan'), ('Station'), ('hatchback'), ('SUV');

 insert into engine(type) values ('gasoline'), ('diesel'), ('gas');

 insert into gearbox(type) values ('manual'), ('auto'), ('robot');

 insert into car (name, body_id, engine_id, gearbox_id) values
     ('BMW', 1, 2, 2),
     ('Lada', 2, 1, 1),
     ('Toyota', 3, 1, 2);


--1. Вывести список всех машин и все привязанные к ним детали.
 select * from car as c
 left join body as b on c.body_id = b.id
 left join engine as e on c.engine_id = e.id
 left join gearbox as g on c.gearbox_id = g.id;

--2. Вывести отдельно детали, которые не используются в (ни в одной машине) машине, кузова, двигатели, коробки передач.

 select * from body as b left join car as c on b.id = c.body_id
 where c.id is null;

 select * from engine as e left join car as c on e.id = c.engine_id
 where c.id is null;

 select * from gearbox as g left join car as c on g.id = c.gearbox_id
 where c.id is null;