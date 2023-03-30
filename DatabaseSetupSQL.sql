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


-- export database structure of travel-agency-service_db
CREATE DATABASE IF NOT EXISTS `travel-agency-service_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `travel-agency-service_db`;

-- export structure of table travel-agency-service_db.address
CREATE TABLE IF NOT EXISTS `address` (
    `address_id` INT(11) NOT NULL COMMENT 'address''s unique identification number',
    `street` VARCHAR(60) NOT NULL COMMENT 'street name',
    `number` VARCHAR(10) NOT NULL COMMENT 'house number',
    `zip` VARCHAR(10) COMMENT 'zip code if existent',
    `town` VARCHAR(50) NOT NULL COMMENT 'town name',
    `country` VARCHAR(50) NOT NULL COMMENT 'country',
PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered addresses';

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
    `last_name` VARCHAR(40) NOT NULL COMMENT 'last name',
    `first_name` VARCHAR(40) NOT NULL COMMENT 'first name',
    `middle_names` VARCHAR(200) COMMENT 'middle names',
    `date_of_birth` DATE NOT NULL COMMENT 'date of birth',
    `address_id` INT(11) NOT NULL COMMENT 'country',
PRIMARY KEY (`personal_data_id`),
CONSTRAINT `fk_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered personal data';

-- export data of table travel-agency-service_db.personal_data: 6 rows
DELETE FROM `personal_data`;
INSERT INTO `personal_data` (`personal_data_id`, `last_name`, `first_name`, `middle_names`, `date_of_birth`, `address_id`) VALUES
	(1, 'Köln', '1. FC', '', '1948-02-13', 1),
	(2, 'Hopp', 'Dietmar', '', '1940-04-26', 2),
	(3, 'Plattner', 'Hasso', '', '1944-01-21', 2),
	(4, 'Biden', 'Joseph', 'Robinette', '1942-11-20', 3),
	(5, 'Made-Up', 'Carina', 'Elia Melissa', '1988-04-18', 4),
	(6, 'Extrovagant', 'Markus-Elias', 'Hoyang Phillippe', '1991-12-31', 4);

-- export structure of table travel-agency-service_db.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` INT(11) NOT NULL COMMENT 'customer''s unique identification number',
  `IBAN` CHAR(34) NOT NULL COMMENT 'IBAN',
  `personal_data_id` INT(11) NOT NULL COMMENT 'unique identification number of the personal data',
  `billing_address_id` INT(11) NOT NULL COMMENT 'unique identification number of the billing address',
  PRIMARY KEY (`customer_id`),
  CONSTRAINT `fk_customer_data` FOREIGN KEY (`personal_data_id`) REFERENCES `personal_data` (`personal_data_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_billing_address` FOREIGN KEY (`billing_address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered customer';

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
    `place_of_birth` CHAR(50) NOT NULL COMMENT 'town name of birth',
    `personal_data_id` INT(11) NOT NULL COMMENT 'unique identification number of the personal data',
    PRIMARY KEY (`passport_id`),
    CONSTRAINT `fk_traveller_data` FOREIGN KEY (`personal_data_id`) REFERENCES `personal_data` (`personal_data_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered traveller';

-- export data of table travel-agency-service_db.traveller: 6 rows
DELETE FROM `traveller`;
INSERT INTO `traveller` (`passport_id`, `place_of_birth`, `personal_data_id`) VALUES
	('C12345678', 'Köln-Müngersdorf', 1),
	('C84615846', 'Hoffenheim', 2),
	('C49519545', 'Berlin', 3),
	('C75424278', 'Scranton, PA', 4),
	('C58757857', 'Entenhausen', 5),
	('C78578578', 'Musterstadt', 6);

-- export structure of table travel-agency-service_db.customer
CREATE TABLE IF NOT EXISTS `hotel` (
    `hotel_id` INT(11) NOT NULL COMMENT 'hotel''s unique identification number',
    `name` CHAR(60) NOT NULL COMMENT 'name of the hotel',
    `price_per_person` DECIMAL (8,2) NOT NULL COMMENT 'price per person per night',
    `currency_key` CHAR(3) NOT NULL COMMENT 'currency to the amount above',
    `address_id` INT(11) NOT NULL COMMENT 'unique identification number of the hotel\'s address',
    PRIMARY KEY (`hotel_id`),
    CONSTRAINT `fk_hotel_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='registered hotels';

-- export data of table travel-agency-service_db.customer: 4 rows
DELETE FROM `hotel`;
INSERT INTO `hotel` (`hotel_id`, `name`, `price_per_person`, `currency_key`, `address_id`) VALUES
    (1, 'Excelsior Hotel Ernst', 500.00, 'EUR', 5),
    (2, 'SAP Guesthouse', 10.00, 'EUR', 6),
    (3, 'Disney\'s All-Star Movies Resort', 199.99, 'USD', 7);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
