-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 03:35 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `feedback_it20120320`
--

-- --------------------------------------------------------

--
-- Table structure for table `tablefeedaback`
--

CREATE TABLE `tablefeedaback` (
  `F_ID` int(11) NOT NULL,
  `F_Name` varchar(50) NOT NULL,
  `F_ContactNo` varchar(10) NOT NULL,
  `F_Email` varchar(255) NOT NULL,
  `F_Message` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tablefeedaback`
--

INSERT INTO `tablefeedaback` (`F_ID`, `F_Name`, `F_ContactNo`, `F_Email`, `F_Message`) VALUES
(1, 'Lihini', '0112794246', 'lihinilekani@gmail.com', 'Something I really appreciate about you is your aptitude for problem-solving.'),
(2, 'Thimira', '0724688175', 'thimiraperera@gmail.com', 'Easy to handle'),
(3, 'Milinda', '0703984905', 'milindaperera@gmail.com', 'Quick and fast billing generation is the best service.'),
(4, 'Perera', '0724688175', 'perera@gmail.com', 'user-friendly');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tablefeedaback`
--
ALTER TABLE `tablefeedaback`
  ADD PRIMARY KEY (`F_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tablefeedaback`
--
ALTER TABLE `tablefeedaback`
  MODIFY `F_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
