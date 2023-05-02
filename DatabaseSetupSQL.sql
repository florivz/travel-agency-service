-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               10.10.2-MariaDB - mariadb.org binary distribution
-- Server Betriebssystem:        Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- create demo user for database
CREATE USER IF NOT EXISTS `DEMO_USER` IDENTIFIED BY 'PASSWORD';

-- export database structure of travel-agency-service_db
CREATE DATABASE IF NOT EXISTS `travel-agency-service_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `travel-agency-service_db`;

-- export structure of table travel-agency-service_db.address
CREATE TABLE IF NOT EXISTS `address` (
    `address_id` INT(11) NOT NULL COMMENT 'address''s unique identification number',
    `street` VARCHAR(60) COMMENT 'street name',
    `number` VARCHAR(10) COMMENT 'house number',
    `zip` VARCHAR(10) COMMENT 'zip code if existent',
    `town` VARCHAR(50) COMMENT 'town name',
    `country` VARCHAR(50) COMMENT 'country',
PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered addresses';

-- grant select to demo_user
GRANT SELECT ON `address` TO `DEMO_USER`;

-- export data of table travel-agency-service_db.address: 4 rows
DELETE FROM `address`;
INSERT INTO `address` (`address_id`, `street`, `number`, `zip`, `town`, `country`) VALUES
	(1, 'Aachener Straße', '999', '50933', 'Köln', 'Deutschland'),
	(2, 'Hasso-Plattner-Ring', '7', '69190', 'Walldorf', 'Deutschland'),
	(3, 'Pennsylvania Avenue NW', '1600', '20500', 'Washington, DC', 'United States'),
	(4, 'Dongzhimen Outer St', '17', '100027', 'Beijing', 'China'),
	(5, 'Trankgasse', '1-5', '50667', 'Köln', 'Deutschland'),
	(6, 'Dietmar-Hopp-Allee', '15', '69190', 'Walldorf', 'Deutschland'),
    (7, 'W Buena Vista Dr', '1901', '32830', 'Buena Vista', 'United States');

-- export structure of table travel-agency-service_db.personal_data
CREATE TABLE IF NOT EXISTS `personal_data` (
    `personal_data_id` INT(11) NOT NULL COMMENT 'personal_data''s unique identification number',
    `last_name` VARCHAR(40) COMMENT 'last name',
    `first_name` VARCHAR(40) COMMENT 'first name',
    `middle_name` VARCHAR(200) COMMENT 'middle name',
    `date_of_birth` DATE COMMENT 'date of birth',
    `address_id` INT(11) COMMENT 'country',
PRIMARY KEY (`personal_data_id`),
CONSTRAINT `fk_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered personal data';

-- grant select to demo_user
GRANT SELECT ON `personal_data` TO `DEMO_USER`;

-- export data of table travel-agency-service_db.personal_data: 6 rows
DELETE FROM `personal_data`;
INSERT INTO `personal_data` (`personal_data_id`, `last_name`, `first_name`, `middle_name`, `date_of_birth`, `address_id`) VALUES
	(1, 'Köln', '1. FC', '', '1948-02-13', 1),
	(2, 'Hopp', 'Dietmar', '', '1940-04-26', 2),
	(3, 'Plattner', 'Hasso', '', '1944-01-21', 2),
	(4, 'Biden', 'Joseph', 'Robinette', '1942-11-20', 3),
	(5, 'Made-Up', 'Carina', 'Elia Melissa', '1988-04-18', 4),
	(6, 'Extrovagant', 'Markus-Elias', 'Hoyang Phillippe', '1991-12-31', 4);

-- export structure of table travel-agency-service_db.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` INT(11) NOT NULL COMMENT 'customer''s unique identification number',
  `IBAN` CHAR(34) COMMENT 'IBAN',
  `personal_data_id` INT(11) COMMENT 'unique identification number of the personal data',
  `billing_address_id` INT(11) COMMENT 'unique identification number of the billing address',
  PRIMARY KEY (`customer_id`),
  CONSTRAINT `fk_customer_data` FOREIGN KEY (`personal_data_id`) REFERENCES `personal_data` (`personal_data_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_billing_address` FOREIGN KEY (`billing_address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered customer';

-- grant select to demo_user
GRANT SELECT ON `customer` TO `DEMO_USER`;

-- export data of table travel-agency-service_db.customer: 4 rows
DELETE FROM `customer`;
INSERT INTO `customer` (`customer_id`, `IBAN`, `personal_data_id`, `billing_address_id`) VALUES
	(1, 'DE98451201414511819849156156156156', 1, 1),
	(2, 'DE84868348115184120591512013168888', 2, 2),
	(3, 'DE85156565156561556156156151561515', 4, 3),
	(4, 'DE99964848432000054564230000544654', 6, 4);

-- export structure of table travel-agency-service_db.traveller
CREATE TABLE IF NOT EXISTS `traveller` (
    `passport_id` CHAR(9) NOT NULL COMMENT 'traveller''s unique identification number as from his current valid passport',
    `place_of_birth` CHAR(50) COMMENT 'town name of birth',
    `personal_data_id` INT(11) COMMENT 'unique identification number of the personal data',
    PRIMARY KEY (`passport_id`),
    CONSTRAINT `fk_traveller_data` FOREIGN KEY (`personal_data_id`) REFERENCES `personal_data` (`personal_data_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered traveller';

-- grant select to demo_user
GRANT SELECT ON `traveller` TO `DEMO_USER`;

-- export data of table travel-agency-service_db.traveller: 6 rows
DELETE FROM `traveller`;
INSERT INTO `traveller` (`passport_id`, `place_of_birth`, `personal_data_id`) VALUES
	('C12345678', 'Köln-Müngersdorf', 1),
	('C84615846', 'Hoffenheim', 2),
	('C49519545', 'Berlin', 3),
	('C75424278', 'Scranton, PA', 4),
	('C58757857', 'Entenhausen', 5),
	('C78578578', 'Musterstadt', 6);

-- export structure of table travel-agency-service_db.hotel
CREATE TABLE IF NOT EXISTS `hotel` (
    `hotel_id` INT(11) NOT NULL COMMENT 'hotel''s unique identification number',
    `name` VARCHAR(60) COMMENT 'name of the hotel',
    `price_per_person` DECIMAL (8,2) COMMENT 'price per person per night',
    `currency_key` CHAR(3) COMMENT 'currency to the amount above',
    `address_id` INT(11) COMMENT 'unique identification number of the hotel\'s address',
    PRIMARY KEY (`hotel_id`),
    CONSTRAINT `fk_hotel_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered hotels';

-- grant select to demo_user
GRANT SELECT ON `hotel` TO `DEMO_USER`;

-- export data of table travel-agency-service_db.hotel: 3 rows
DELETE FROM `hotel`;
INSERT INTO `hotel` (`hotel_id`, `name`, `price_per_person`, `currency_key`, `address_id`) VALUES
    (1, 'Excelsior Hotel Ernst', 500.00, 'EUR', 5),
    (2, 'SAP Guesthouse', 10.00, 'EUR', 6),
    (3, 'Disney\'s All-Star Movies Resort', 199.99, 'USD', 7);

-- export structure of table travel-agency-service_db.flight_connection
CREATE TABLE IF NOT EXISTS `flight_connection` (
    `flight_connection_id` INT(11) NOT NULL COMMENT 'flight_connections''s unique identification number',
    `carrier_id` CHAR(2) COMMENT 'airline code',
    `connection_id` CHAR(4) COMMENT 'connection code',
    `departure_airport_code` CHAR(3) COMMENT 'departing airport code',
    `arrival_airport_code` CHAR(3) COMMENT 'arriving airport code',
    PRIMARY KEY (`flight_connection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered flight connections';

-- grant select to demo_user
GRANT SELECT ON `flight_connection` TO `DEMO_USER`;

-- export data of table travel-agency-service_db.flight_connection: 4 rows
DELETE FROM `flight_connection`;
INSERT INTO `flight_connection` (`flight_connection_id`, `carrier_id`, `connection_id`, `departure_airport_code`, `arrival_airport_code`) VALUES
    (1 , 'LH', '1007', 'FRA', 'LAX'),
    (2 , 'LH', '1008', 'LAX', 'FRA'),
    (3 , 'QR', '0005', 'MUC', 'DOH'),
    (4 , 'QR', '0005', 'MUC', 'DOH'),
    (5 , 'DL', '0015', 'FRA', 'ATL'),
    (6 , 'DL', '0016', 'ATL', 'FRA'),
    (7 , 'DL', '2605', 'ATL', 'PNS'),
    (8 , 'DL', '2606', 'PNS', 'ATL'),
    (9 , 'FR', '3221', 'CGN', 'DUB'),
    (10, 'FR', '3222', 'DUB', 'CGN');

-- export structure of table travel-agency-service_db.flight
CREATE TABLE IF NOT EXISTS `flight` (
    `flight_id` INT(11) NOT NULL COMMENT 'flight''s unique identification number',
    `flight_connection_id` INT(11) NOT NULL COMMENT 'flight''s corresponding flight connection',
    `departure_date` DATE COMMENT 'date of departure',
    `departure_time` TIME COMMENT 'time of departure',
    `departure_time_zone` CHAR(9) COMMENT 'time zone of departing airport in format UTC+HH:MM',
    `arrival_date` DATE COMMENT 'date of arrival',
    `arrival_time` TIME COMMENT 'time of arrival',
    `arrival_time_zone` CHAR(9) COMMENT 'time zone of arriving airport in format UTC+HH:MM',
    `price_per_person` DECIMAL (8,2)COMMENT 'price per person per flight',
    `currency_key` CHAR(3) COMMENT 'currency to the amount above',
    PRIMARY KEY (`flight_id`),
    CONSTRAINT `fk_flight_connection` FOREIGN KEY (`flight_connection_id`) REFERENCES `flight_connection` (`flight_connection_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered flights';

-- grant select to demo_user
GRANT SELECT ON `flight` TO `DEMO_USER`;

-- export data of table travel-agency-service_db.flight: 62 rows
DELETE FROM `flight`;
INSERT INTO `flight` (`flight_id`, `flight_connection_id`, `departure_date`, `departure_time`, `departure_time_zone`, `arrival_date`, `arrival_time`, `arrival_time_zone`, `price_per_person`, `currency_key`) VALUES
    (1 , 1, '2023-05-01', '12:45:00', 'UTC+02:00', '2023-05-01', '15:15:00', 'UTC-07:00',  749.99, 'EUR'),
    (2 , 1, '2023-05-08', '12:45:00', 'UTC+02:00', '2023-05-08', '15:15:00', 'UTC-07:00',  749.99, 'EUR'),
    (3 , 1, '2023-05-15', '12:45:00', 'UTC+02:00', '2023-05-15', '15:15:00', 'UTC-07:00',  749.99, 'EUR'),
    (4 , 1, '2023-05-22', '12:45:00', 'UTC+02:00', '2023-05-01', '15:15:00', 'UTC-07:00',  749.99, 'EUR'),
    (5 , 1, '2023-05-29', '12:45:00', 'UTC+02:00', '2023-05-01', '15:15:00', 'UTC-07:00',  749.99, 'EUR'),
    (6 , 1, '2023-06-06', '12:45:00', 'UTC+02:00', '2023-05-01', '15:15:00', 'UTC-07:00',  899.99, 'EUR'),
    (7 , 1, '2023-06-13', '12:45:00', 'UTC+02:00', '2023-05-01', '15:15:00', 'UTC-07:00',  899.99, 'EUR'),
    (8 , 1, '2023-06-20', '12:45:00', 'UTC+02:00', '2023-05-01', '15:15:00', 'UTC-07:00',  899.99, 'EUR'),
    (9 , 1, '2023-06-27', '12:45:00', 'UTC+02:00', '2023-05-01', '15:15:00', 'UTC-07:00',  899.99, 'EUR'),
    (10, 2, '2023-05-01', '17:10:00', 'UTC-07:00', '2023-05-02', '12:50:00', 'UTC+02:00',  699.99, 'EUR'),
    (11, 2, '2023-05-08', '17:10:00', 'UTC-07:00', '2023-05-09', '12:50:00', 'UTC+02:00',  699.99, 'EUR'),
    (12, 2, '2023-05-15', '17:10:00', 'UTC-07:00', '2023-05-16', '12:50:00', 'UTC+02:00',  699.99, 'EUR'),
    (13, 2, '2023-05-22', '17:10:00', 'UTC-07:00', '2023-05-23', '12:50:00', 'UTC+02:00',  699.99, 'EUR'),
    (14, 2, '2023-05-29', '17:10:00', 'UTC-07:00', '2023-05-30', '12:50:00', 'UTC+02:00',  749.99, 'EUR'),
    (15, 2, '2023-06-06', '17:10:00', 'UTC-07:00', '2023-06-07', '12:50:00', 'UTC+02:00',  899.99, 'EUR'),
    (16, 2, '2023-06-13', '17:10:00', 'UTC-07:00', '2023-06-14', '12:50:00', 'UTC+02:00',  899.99, 'EUR'),
    (17, 2, '2023-06-20', '17:10:00', 'UTC-07:00', '2023-06-21', '12:50:00', 'UTC+02:00',  899.99, 'EUR'),
    (18, 2, '2023-06-27', '17:10:00', 'UTC-07:00', '2023-06-28', '12:50:00', 'UTC+02:00',  899.99, 'EUR'),
    (19, 3, '2023-07-17', '08:35:00', 'UTC+02:00', '2023-07-17', '15:10:00', 'UTC+03:00',  229.99, 'EUR'),
    (20, 3, '2023-08-04', '06:45:00', 'UTC+02:00', '2023-08-04', '13:20:00', 'UTC+03:00',  267.99, 'EUR'),
    (21, 3, '2023-08-25', '09:20:00', 'UTC+02:00', '2023-08-25', '15:55:00', 'UTC+03:00',  184.99, 'EUR'),
    (22, 4, '2023-07-17', '16:40:00', 'UTC+03:00', '2023-07-17', '21:50:00', 'UTC+02:00',  249.99, 'EUR'),
    (23, 4, '2023-08-04', '14:50:00', 'UTC+03:00', '2023-08-04', '20:00:00', 'UTC+02:00',  287.99, 'EUR'),
    (24, 4, '2023-08-25', '17:25:00', 'UTC+03:00', '2023-08-25', '22:35:00', 'UTC+02:00',  204.99, 'EUR'),
    (25, 5, '2023-05-10', '11:00:00', 'UTC+02:00', '2023-05-10', '15:15:00', 'UTC-04:00',  299.99, 'EUR'),
    (26, 5, '2023-05-11', '11:00:00', 'UTC+02:00', '2023-05-11', '15:15:00', 'UTC-04:00',  299.99, 'EUR'),
    (27, 5, '2023-05-12', '11:00:00', 'UTC+02:00', '2023-05-12', '15:15:00', 'UTC-04:00',  699.99, 'EUR'),
    (28, 5, '2023-05-13', '11:00:00', 'UTC+02:00', '2023-05-13', '15:15:00', 'UTC-04:00',  899.99, 'EUR'),
    (29, 5, '2023-05-14', '11:00:00', 'UTC+02:00', '2023-05-14', '15:15:00', 'UTC-04:00',  499.99, 'EUR'),
    (30, 5, '2023-05-15', '11:00:00', 'UTC+02:00', '2023-05-15', '15:15:00', 'UTC-04:00',  499.99, 'EUR'),
    (31, 5, '2023-05-16', '11:00:00', 'UTC+02:00', '2023-05-16', '15:15:00', 'UTC-04:00',  499.99, 'EUR'),
    (32, 5, '2023-05-17', '11:00:00', 'UTC+02:00', '2023-05-17', '15:15:00', 'UTC-04:00',  299.99, 'EUR'),
    (33, 5, '2023-05-18', '11:00:00', 'UTC+02:00', '2023-05-18', '15:15:00', 'UTC-04:00',  299.99, 'EUR'),
    (34, 5, '2023-05-19', '11:00:00', 'UTC+02:00', '2023-05-19', '15:15:00', 'UTC-04:00',  299.99, 'EUR'),
    (35, 6, '2023-07-26', '17:45:00', 'UTC-04:00', '2023-07-26', '08:55:00', 'UTC+02:00',  899.99, 'EUR'),
    (36, 6, '2023-07-27', '17:45:00', 'UTC-04:00', '2023-07-27', '08:55:00', 'UTC+02:00',  999.99, 'EUR'),
    (37, 6, '2023-07-28', '17:45:00', 'UTC-04:00', '2023-07-28', '08:55:00', 'UTC+02:00', 1199.99, 'EUR'),
    (38, 6, '2023-07-29', '17:45:00', 'UTC-04:00', '2023-07-29', '08:55:00', 'UTC+02:00', 1199.99, 'EUR'),
    (39, 6, '2023-07-30', '17:45:00', 'UTC-04:00', '2023-07-30', '08:55:00', 'UTC+02:00', 1199.99, 'EUR'),
    (40, 6, '2023-07-31', '17:45:00', 'UTC-04:00', '2023-07-31', '08:55:00', 'UTC+02:00', 1049.99, 'EUR'),
    (41, 6, '2023-08-01', '17:45:00', 'UTC-04:00', '2023-08-01', '08:55:00', 'UTC+02:00',  999.99, 'EUR'),
    (42, 6, '2023-08-02', '17:45:00', 'UTC-04:00', '2023-08-02', '08:55:00', 'UTC+02:00',  899.99, 'EUR'),
    (43, 6, '2023-08-03', '17:45:00', 'UTC-04:00', '2023-08-03', '08:55:00', 'UTC+02:00',  849.99, 'EUR'),
    (44, 7, '2023-05-10', '20:00:00', 'UTC-04:00', '2023-05-10', '20:13:00', 'UTC-05:00',   99.99, 'EUR'),
    (45, 7, '2023-05-11', '20:00:00', 'UTC-04:00', '2023-05-11', '20:13:00', 'UTC-05:00',   99.99, 'EUR'),
    (46, 7, '2023-05-12', '20:00:00', 'UTC-04:00', '2023-05-12', '20:13:00', 'UTC-05:00',  399.99, 'EUR'),
    (47, 7, '2023-05-13', '20:00:00', 'UTC-04:00', '2023-05-13', '20:13:00', 'UTC-05:00',  299.99, 'EUR'),
    (48, 7, '2023-05-14', '20:00:00', 'UTC-04:00', '2023-05-14', '20:13:00', 'UTC-05:00',  199.99, 'EUR'),
    (49, 7, '2023-05-15', '20:00:00', 'UTC-04:00', '2023-05-15', '20:13:00', 'UTC-05:00',  199.99, 'EUR'),
    (50, 7, '2023-05-16', '20:00:00', 'UTC-04:00', '2023-05-16', '20:13:00', 'UTC-05:00',   99.99, 'EUR'),
    (51, 7, '2023-05-17', '20:00:00', 'UTC-04:00', '2023-05-17', '20:13:00', 'UTC-05:00',   99.99, 'EUR'),
    (52, 7, '2023-05-18', '20:00:00', 'UTC-04:00', '2023-05-18', '20:13:00', 'UTC-05:00',   99.99, 'EUR'),
    (53, 7, '2023-05-19', '20:00:00', 'UTC-04:00', '2023-05-19', '20:13:00', 'UTC-05:00',   69.99, 'EUR'),
    (54, 8, '2023-07-26', '14:00:00', 'UTC-05:00', '2023-07-26', '16:15:00', 'UTC-04:00',  249.99, 'EUR'),
    (55, 8, '2023-07-27', '14:00:00', 'UTC-05:00', '2023-07-26', '16:15:00', 'UTC-04:00',  349.99, 'EUR'),
    (56, 8, '2023-07-28', '14:00:00', 'UTC-05:00', '2023-07-26', '16:15:00', 'UTC-04:00',  499.99, 'EUR'),
    (57, 8, '2023-07-29', '14:00:00', 'UTC-05:00', '2023-07-26', '16:15:00', 'UTC-04:00',  499.99, 'EUR'),
    (58, 8, '2023-07-30', '14:00:00', 'UTC-05:00', '2023-07-26', '16:15:00', 'UTC-04:00',  499.99, 'EUR'),
    (59, 8, '2023-07-31', '14:00:00', 'UTC-05:00', '2023-07-26', '16:15:00', 'UTC-04:00',  449.99, 'EUR'),
    (60, 8, '2023-08-01', '14:00:00', 'UTC-05:00', '2023-08-01', '16:15:00', 'UTC-04:00',  449.99, 'EUR'),
    (61, 8, '2023-08-02', '14:00:00', 'UTC-05:00', '2023-08-02', '16:15:00', 'UTC-04:00',  399.99, 'EUR'),
    (62, 8, '2023-08-03', '14:00:00', 'UTC-05:00', '2023-08-03', '16:15:00', 'UTC-04:00',  349.99, 'EUR');

-- export structure of table travel-agency-service_db.booking
CREATE TABLE IF NOT EXISTS `booking` (
    `booking_id` INT(11) NOT NULL COMMENT 'trip''s unique identification number',
    `customer_id` INT(11) NOT NULL COMMENT 'booking associated with this trip',
    `date` DATE NOT NULL COMMENT 'date of the booking',
    PRIMARY KEY (`booking_id`),
    CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered bookings';

-- grant select to demo_user
GRANT SELECT ON `booking` TO `DEMO_USER`;

-- export data of table travel-agency-service_db.booking: 3 rows
DELETE FROM `booking`;
INSERT INTO `booking` (`booking_id`, `customer_id`, `date`) VALUES
    (1, 1, '2023-02-01'),
    (2, 2, '2022-12-05'),
    (3, 3, '2023-01-014'),
    (4, 4, '2023-04-04');

-- export structure of table travel-agency-service_db.trip
CREATE TABLE IF NOT EXISTS `trip` (
    `trip_id` INT(11) NOT NULL COMMENT 'trip''s unique identification number',
    `booking_id` INT(11) COMMENT 'booking associated with this trip',
    PRIMARY KEY (`trip_id`),
    CONSTRAINT `fk_booking_id` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered trips';

-- grant select to demo_user
GRANT SELECT ON `trip` TO `DEMO_USER`;

-- export data of table travel-agency-service_db.trip: 5 rows
DELETE FROM `trip`;
INSERT INTO `trip` (`trip_id`, `booking_id`) VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 3),
    (5, 3),
    (6, 4),
    (7, 4);

-- export structure of table travel-agency-service_db.hotel_booking
CREATE TABLE IF NOT EXISTS `hotel_booking` (
    `hotel_booking_id` INT(11) NOT NULL COMMENT 'hotel_booking''s unique identification number',
    `trip_id` INT(11) NOT NULL COMMENT 'trip associated with the hotel booking',
    `hotel_id` INT(11) NOT NULL COMMENT 'hotel associated with the booking',
    `number_of_guests` SMALLINT NOT NULL COMMENT 'number of hotel guests',
    `number_of_nights` SMALLINT NOT NULL COMMENT 'number of nights spent at the hotel',
    PRIMARY KEY (`hotel_booking_id`),
    CONSTRAINT `fk_hotel_trip_id` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_hotel_id` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered hotel bookings';

-- grant select to demo_user
GRANT SELECT ON `hotel_booking` TO `DEMO_USER`;

-- export data of table travel-agency-service_db.hotel_booking: 5 rows
DELETE FROM `hotel_booking`;
INSERT INTO `hotel_booking` (`hotel_booking_id`, `trip_id`, `hotel_id`, `number_of_guests`) VALUES
    (1, 1, 1, 3),
    (2, 3, 1, 4),
    (3, 4, 2, 2),
    (4, 2, 2, 1),
    (5, 5, 3, 12),
    (6, 6, 3, 1),
    (7, 6, 2, 4),
    (8, 7, 3, 2);

-- export structure of table travel-agency-service_db.flight_booking
CREATE TABLE IF NOT EXISTS `flight_booking` (
    `flight_booking_id` INT(11) NOT NULL COMMENT 'flight_booking''s unique identification number',
    `trip_id` INT(11) NOT NULL COMMENT 'trip associated with the booking',
    `flight_id` INT(11) NOT NULL COMMENT 'flight associated with the booking',
    `number_of_passengers` SMALLINT NOT NULL COMMENT 'number of flight passengers',
    PRIMARY KEY (`flight_booking_id`),
    CONSTRAINT `fk_flight_trip_id` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_flight_id` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`flight_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered flight bookings';

-- grant select to demo_user
GRANT SELECT ON `flight_booking` TO `DEMO_USER`;

-- export data of table travel-agency-service_db.flight_booking: 5 rows
DELETE FROM `flight_booking`;
INSERT INTO `flight_booking` (`flight_booking_id`, `trip_id`, `flight_id`, `number_of_passengers`) VALUES
    (1, 1, 1, 2),
    (2, 5, 18, 4),
    (3, 2, 41, 3),
    (4, 3, 35, 7),
    (5, 2, 29, 20);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
