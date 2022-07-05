-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 20, 2020 at 12:15 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `purplelanebs`
--

-- --------------------------------------------------------

--
-- Table structure for table `carttbl`
--

CREATE TABLE `carttbl` (
  `userId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `productQuantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `producttbl`
--

CREATE TABLE `producttbl` (
  `productId` int(11) NOT NULL,
  `productName` varchar(20) NOT NULL,
  `productAuthor` varchar(20) NOT NULL,
  `productPrice` int(11) NOT NULL,
  `productStock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `producttbl`
--

INSERT INTO `producttbl` (`productId`, `productName`, `productAuthor`, `productPrice`, `productStock`) VALUES
(1, 'Buku Gambar', 'Sukarno', 15000, 30),
(2, 'Buku Tulis', 'Suharto', 20000, 49),
(3, 'Buku Sejarah', 'Jackson', 250000, 15),
(4, 'Buku Biologi', 'William', 300000, 30);

-- --------------------------------------------------------

--
-- Table structure for table `promotbl`
--

CREATE TABLE `promotbl` (
  `promoId` int(11) NOT NULL,
  `promoCode` varchar(20) NOT NULL,
  `promoDiscount` int(11) NOT NULL,
  `promoNote` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promotbl`
--

INSERT INTO `promotbl` (`promoId`, `promoCode`, `promoDiscount`, `promoNote`) VALUES
(1, 'promo', 15000, 'discount 15000'),
(2, 'promobaru', 25000, 'gratis'),
(18, 'promolama', 30000, 'mahal'),
(20, 'rexona', 12000, 'diskon 12000'),
(21, 'pampers', 15000, 'diskon pampers');

-- --------------------------------------------------------

--
-- Table structure for table `roletbl`
--

CREATE TABLE `roletbl` (
  `roleId` int(11) NOT NULL,
  `roleName` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roletbl`
--

INSERT INTO `roletbl` (`roleId`, `roleName`) VALUES
(1, 'User'),
(2, 'Manager'),
(3, 'Promo Team'),
(4, 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `transactiondetailtbl`
--

CREATE TABLE `transactiondetailtbl` (
  `transactionId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `productQuantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactiondetailtbl`
--

INSERT INTO `transactiondetailtbl` (`transactionId`, `productId`, `productQuantity`) VALUES
(1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `transactiontbl`
--

CREATE TABLE `transactiontbl` (
  `transactionId` int(11) NOT NULL,
  `transactionDate` date NOT NULL,
  `paymentType` varchar(20) NOT NULL,
  `cardNumber` bigint(20) NOT NULL,
  `promoCode` varchar(20) NOT NULL,
  `userId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactiontbl`
--

INSERT INTO `transactiontbl` (`transactionId`, `transactionDate`, `paymentType`, `cardNumber`, `promoCode`, `userId`) VALUES
(1, '2020-12-20', 'Cash', 0, 'promo', 20);

-- --------------------------------------------------------

--
-- Table structure for table `usertbl`
--

CREATE TABLE `usertbl` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usertbl`
--

INSERT INTO `usertbl` (`userId`, `roleId`, `username`, `password`) VALUES
(20, 1, 'username', 'passname'),
(21, 1, 'jonathanputra', 'jonathanputra'),
(22, 2, 'manager', 'manager'),
(23, 2, 'testManager', 'testManager'),
(24, 3, 'promo', 'promo'),
(25, 4, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carttbl`
--
ALTER TABLE `carttbl`
  ADD KEY `userId` (`userId`),
  ADD KEY `carttbl_ibfk_1` (`productId`);

--
-- Indexes for table `producttbl`
--
ALTER TABLE `producttbl`
  ADD PRIMARY KEY (`productId`);

--
-- Indexes for table `promotbl`
--
ALTER TABLE `promotbl`
  ADD PRIMARY KEY (`promoId`);

--
-- Indexes for table `roletbl`
--
ALTER TABLE `roletbl`
  ADD PRIMARY KEY (`roleId`);

--
-- Indexes for table `transactiondetailtbl`
--
ALTER TABLE `transactiondetailtbl`
  ADD KEY `transactionId` (`transactionId`);

--
-- Indexes for table `transactiontbl`
--
ALTER TABLE `transactiontbl`
  ADD PRIMARY KEY (`transactionId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `usertbl`
--
ALTER TABLE `usertbl`
  ADD PRIMARY KEY (`userId`),
  ADD KEY `roleId` (`roleId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `producttbl`
--
ALTER TABLE `producttbl`
  MODIFY `productId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `promotbl`
--
ALTER TABLE `promotbl`
  MODIFY `promoId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `roletbl`
--
ALTER TABLE `roletbl`
  MODIFY `roleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `usertbl`
--
ALTER TABLE `usertbl`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `carttbl`
--
ALTER TABLE `carttbl`
  ADD CONSTRAINT `carttbl_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `producttbl` (`productId`) ON DELETE CASCADE,
  ADD CONSTRAINT `carttbl_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `usertbl` (`userId`);

--
-- Constraints for table `transactiondetailtbl`
--
ALTER TABLE `transactiondetailtbl`
  ADD CONSTRAINT `transactiondetailtbl_ibfk_3` FOREIGN KEY (`transactionId`) REFERENCES `transactiontbl` (`transactionId`);

--
-- Constraints for table `transactiontbl`
--
ALTER TABLE `transactiontbl`
  ADD CONSTRAINT `transactiontbl_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `usertbl` (`userId`);

--
-- Constraints for table `usertbl`
--
ALTER TABLE `usertbl`
  ADD CONSTRAINT `usertbl_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `roletbl` (`roleId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
