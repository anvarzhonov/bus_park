DROP TABLE if exists bus;
DROP TABLE if exists bus_flight;
DROP TABLE if exists bus_driver;
DROP TABLE if exists passenger_insurance_company;

CREATE TABLE bus
(
    id_bus     INTEGER AUTO_INCREMENT,
    bus_number INTEGER     NOT NULL,
    bus_name   VARCHAR(30) NULL,
    id_driver  INTEGER     NOT NULL,
    id_company INTEGER     NOT NULL,
    PRIMARY KEY (id_bus, id_driver, id_company)
)
;
CREATE TABLE bus_driver
(
    id_driver       INTEGER PRIMARY KEY AUTO_INCREMENT,
    name_surname    VARCHAR(20) NOT NULL,
    work_experience INTEGER     NOT NULL,
    driver_license  VARCHAR(30) NOT NULL,
    kol_flight      INTEGER     NULL,
    id_flight       INTEGER     NOT NULL
)
;
create table users
(
    id        bigint not null auto_increment,
    email     varchar(255),
    name      varchar(255),
    password  varchar(255),
    role      varchar(255),
    primary key (id)
);

CREATE TABLE bus_flight
(
    id_flight         INTEGER PRIMARY KEY AUTO_INCREMENT,
    number_flight     INTEGER     NOT NULL,
    name_start_flight VARCHAR(30) NOT NULL,
    name_end_flight   VARCHAR(30) NOT NULL
)
;


CREATE TABLE passenger_insurance_company
(
    id_company             INTEGER PRIMARY KEY AUTO_INCREMENT,
    name_insurance_company VARCHAR(20) NOT NULL,
    contract_number        INTEGER     NOT NULL
)
;


ALTER TABLE bus
    ADD FOREIGN KEY (id_driver) REFERENCES bus_driver (id_driver)
;


ALTER TABLE bus
    ADD FOREIGN KEY (id_company) REFERENCES passenger_insurance_company (id_company)
;

ALTER TABLE bus_driver
    ADD FOREIGN KEY (id_flight) REFERENCES bus_flight (id_flight)
;

insert into bus_flight(name_start_flight, name_end_flight, number_flight)
VALUES ('Медведково', 'Китай-город', '774'),
       ('метро Некрасовка', 'Орехово', '321'),
       ('Шляпково', 'Салатовка', '123'),
       ('Арханельская улица', 'ДЕПО Кирзанов', '21'),
       ('Широкая улица', 'улица Вернадского', '80'),
       ('метро Юго-западная', 'метро Раменки', '664');


insert into bus_driver(name_surname, work_experience, driver_license, id_flight)
VALUES ('Мишутка Алфертов', '30', 'A4141D', '2'),
       ('Али Белокуров', '6', 'B7474', '1'),
       ('Мар Блаженая', '10', 'AA15192H', '3'),
       ('Злат ЗАбрагимов', '9', 'B219955D', '1'),
       ('Марк рылов', '21', 'A1439741D', '4'),
       ('Игорь Индюк', '4', 'AA109203D', '5'),
       ('Евгений Павлов', '7', 'AA43525G', '6'),
       ('Эсвоьд Мирзалиев', '25', 'A150423DD', '6'),
       ('Оскар Джамшутов', '4', 'AA10485W', '2'),
       ('Кирр7илл алал', '30', 'A414132D', '2'),
       ('Али Белокуров', '6', 'B747534', '1'),
       ('Мар Б6лаженая', '10', 'A1A15192H', '3'),
       ('Злат ЗАбрагимов', '9', 'B3219955D', '1'),
       ('Марк 5рылов', '21', '52A1439741D', '4'),
       ('Игорь4 Индюк', '4', '52AA109203D', '5'),
       ('Евгений3 Павлов', '7', '67AA43525G', '6'),
       ('Эсвоьд 2Мирзалиев', '25', '83A150423DD', '6'),
       ('Оскар21 Джамшутов', '4', '94AA10485W', '2');

INSERT INTO passenger_insurance_company(name_insurance_company, contract_number)
VALUES ('АО С2ОГАЗ', '221479'),
       ('АО СОГАЗ', '223231'),
       ('АО ВТБ', '313279'),
       ('БВБК Страхование', '9696432'),
       ('CберСтрахование', '228322');


INSERT INTO bus (bus_number, bus_name, id_driver, id_company)
VALUES ('456', 'Обычный автобус', '6', '1'),
       ('966', 'Электробус', '9', '5'),
       ('453', 'Обычный автобус', '1', '1'),
       ('353', 'Электробус', '1', '3'),
       ('105', 'Обычный автобус', '14', '2'),
       ('553', 'Электробус', '3', '5'),
       ('666', 'Электробус', '6', '2'),
       ('78', 'Электробус', '7', '3');

UPDATE bus_driver
SET kol_flight=30
where work_experience > 0;
