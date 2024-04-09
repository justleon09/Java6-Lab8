-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for j6lab7
CREATE DATABASE IF NOT EXISTS `j6lab7` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `j6lab7`;

-- Dumping structure for table j6lab7.accounts
CREATE TABLE IF NOT EXISTS `accounts` (
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j6lab7.accounts: ~2 rows (approximately)
INSERT INTO `accounts` (`username`, `email`, `full_name`, `password`, `photo`) VALUES
	('AD01', 'tuandc@gmail.com', 'Đỗ Công Tuấn', '123', 'tuan.jpg'),
	('KH01', 'leminhhoang241299@gmail.com', 'Lê Minh Hoàng', '123', 'hoang.jpg');

-- Dumping structure for table j6lab7.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j6lab7.categories: ~3 rows (approximately)
INSERT INTO `categories` (`id`, `name`) VALUES
	('L01', 'Điện thoại'),
	('L02', 'Tivi'),
	('L03', 'Laptop');

-- Dumping structure for table j6lab7.orderdetails
CREATE TABLE IF NOT EXISTS `orderdetails` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhnsosbuy7bhpqpnt3bjr7sh8x` (`order_id`),
  KEY `FK92im1bt9gndrexccag7x5oq92` (`product_id`),
  CONSTRAINT `FK92im1bt9gndrexccag7x5oq92` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKhnsosbuy7bhpqpnt3bjr7sh8x` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j6lab7.orderdetails: ~0 rows (approximately)

-- Dumping structure for table j6lab7.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk3cjfcgb621qhahps1tre43e4` (`username`),
  CONSTRAINT `FKk3cjfcgb621qhahps1tre43e4` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j6lab7.orders: ~0 rows (approximately)

-- Dumping structure for table j6lab7.products
CREATE TABLE IF NOT EXISTS `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j6lab7.products: ~8 rows (approximately)
INSERT INTO `products` (`id`, `available`, `createdate`, `image`, `name`, `price`, `category_id`) VALUES
	(1, b'1', '2024-04-07', 'iphone15.jpg', 'Iphone 15 Promax', 800, 'L01'),
	(2, b'1', '2024-04-07', 'iphone14.jpg', 'Iphone 14 Promax', 600, 'L01'),
	(3, b'1', '2024-04-05', 'galaxys24.jpg', 'Samsung Galaxy S24', 700, 'L01'),
	(4, b'1', '2024-04-06', 'tiviGoogle.jpg', 'Google Tivi TCL 4K', 500, 'L02'),
	(5, b'1', '2024-04-06', 'tiviSamsung.jpg', 'Smart Tivi Samsung 4K', 650, 'L02'),
	(6, b'1', '2024-04-06', 'laptopGaming.jpg', 'Laptop Gaming', 550, 'L03'),
	(7, b'1', '2024-04-04', 'lenovo.jpg', 'Laptop Lenovo', 525, 'L03'),
	(8, b'1', '2024-04-03', 'hp.jpg', 'Laptop HP', 540, 'L03');

-- Dumping structure for table j6lab7.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j6lab7.roles: ~2 rows (approximately)
INSERT INTO `roles` (`id`, `role_name`) VALUES
	(1, 'USER'),
	(2, 'ADMIN');

-- Dumping structure for table j6lab7.users_roles
CREATE TABLE IF NOT EXISTS `users_roles` (
  `username` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`username`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FKi5xreq2yuasfjjg31i7y8wkm6` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j6lab7.users_roles: ~2 rows (approximately)
INSERT INTO `users_roles` (`username`, `role_id`) VALUES
	('AD01', 2),
	('KH01', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
