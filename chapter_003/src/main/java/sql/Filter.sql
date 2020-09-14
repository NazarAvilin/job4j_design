--CREATE DATABASE 'Filter';
create table type(
     id serial primary key,
     name text
);

create table product(
    id serial primary key,
    name text,
    type_id int references type(id),
    expired_date date,
    price int
);



insert into "type" (name) values
    ('Сырный'), ('Хлебо-Булочный'),
    ('Мясной'), ('Рыбный'),
    ('Овощной'), ('Молочный');

insert into product(name, type_id, expired_date, price) VALUES
    ('Адыгейский', 1, '2020-09-14', 1100),
    ('Пармизан', 1, '2020-09-12', 1300),
    ('Мраморный', 1, '2020-09-14', 850),
    ('Бородинский', 2, '2020-09-14', 29),
    ('Рижский', 2, '2020-09-14', 32),
    ('Батон', 2, '2020-09-13', 15),
    ('Свинина', 3, '2020-09-14', 280),
    ('Говядина', 3, '2020-09-13', 300),
    ('Баранина', 3, '2020-09-14', 320),
    ('Карп', 4, '2020-09-14', 80),
    ('Карась', 4, '2020-07-14', 100),
    ('Креветки', 4, '2020-08-14', 200),
    ('Огурец', 5, '2020-09-12', 40),
    ('Томат', 5, '2020-09-11', 60),
    ('Молоко', 6, '2020-10-20', 40),
    ('Сметана', 6, '2020-09-20', 100),
    ('Мороженое пломбир', 6, '2020-10-14', 40),
    ('Мороженое планета ссср', 6, '2020-10-14', 50);

update product set expired_date = '2020-09-30' where id = 1;
update product set expired_date = '2020-10-01' where id = 2;
update product set expired_date = '2020-10-02' where id = 3;
update product set expired_date = '2020-09-18' where id = 4;
update product set expired_date = '2020-09-18' where id = 5;
update product set expired_date = '2020-09-16' where id = 6;
update product set expired_date = '2020-11-14' where id = 7;
update product set expired_date = '2020-11-14' where id = 8;
update product set expired_date = '2020-11-14' where id = 9;
update product set expired_date = '2020-12-14' where id = 10;
update product set expired_date = '2020-12-12' where id = 11;
update product set expired_date = '2020-12-14' where id = 12;
update product set expired_date = '2020-09-18' where id = 13;
update product set expired_date = '2020-09-16' where id = 14;

--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product as p inner join type as t on p.type_id = t.id where t.name like 'Сыр%';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product where name like '%рожен%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product where expired_date >= '2020-10-01' and expired_date < '2020-11-01';

--4. Написать запрос, который выводит самый дорогой продукт.
select max(price) from product;
select * from product where price = (select max(price) from product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select * from product as p inner join type as t on p.type_id = t.id where t.name like 'Мяс%';

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product as p inner join type as t on p.type_id = t.id
where t.name like 'Сыр%' or t.name like 'Моло%';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name from product as p inner join type as t on p.type_id = t.id
group by t.name having count(t.id) < 10;

--8. Вывести все продукты и их тип.
select * from product as p inner join type as t on p.type_id = t.id;
