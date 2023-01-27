-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 19, 2023 at 04:19 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `condoapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `BookingID` int(5) NOT NULL,
  `FacilityName` varchar(30) NOT NULL,
  `BookingDate` date NOT NULL,
  `BookingTime` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`BookingID`, `FacilityName`, `BookingDate`, `BookingTime`) VALUES
(5, 'Gym 1', '2023-01-19', '1pm-3pm'),
(6, 'Sauna 1', '2023-01-21', '5pm-7pm'),
(7, 'Public Hall', '2023-01-23', '7pm-9pm'),
(8, 'Public Hall', '2023-01-19', '1pm-3pm'),
(9, 'Sauna 1', '2023-01-18', '9am-11am'),
(10, 'Gym 1', '2023-01-20', '3pm-5pm');

-- --------------------------------------------------------

--
-- Table structure for table `facility`
--

CREATE TABLE `facility` (
  `FacilityID` int(10) NOT NULL,
  `FacilityName` varchar(30) NOT NULL,
  `Location` varchar(25) NOT NULL,
  `Capacity` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `facility`
--

INSERT INTO `facility` (`FacilityID`, `FacilityName`, `Location`, `Capacity`) VALUES
(1, 'Sauna 1', 'Block A, Level 1', 3),
(2, 'Sauna 2', 'Block B, Level 2', 3),
(3, 'Sauna 3', 'Block C, Level 1', 3),
(4, 'Gym 1', 'Community Building', 25),
(5, 'Gym 2', 'Block B, Level G', 25),
(6, 'Swimming Pool', 'Community Building', 30),
(7, 'Public Hall', 'Community Building', 200),
(8, 'PingPong Room', 'Community Building', 8);

-- --------------------------------------------------------

--
-- Table structure for table `maintenance`
--

CREATE TABLE `maintenance` (
  `MaintenanceID` int(10) NOT NULL,
  `FacilityName` varchar(30) NOT NULL,
  `MaintenanceTime` varchar(30) NOT NULL,
  `MaintenanceDate` date NOT NULL,
  `MaintenanceReason` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `maintenance`
--

INSERT INTO `maintenance` (`MaintenanceID`, `FacilityName`, `MaintenanceTime`, `MaintenanceDate`, `MaintenanceReason`) VALUES
(1, 'Gym 1', '11am-1pm', '2023-01-20', 'Renovation Work'),
(2, 'Sauna 1', '9am-11am', '2023-01-20', 'Cleaning, Sanitizing and Disin'),
(3, 'Sauna 2', '11am-1pm', '2023-01-20', 'Cleaning, Sanitizing and Disin'),
(4, 'Sauna 3', '1pm-3pm', '2023-01-20', 'Cleaning, Sanitizing and Disin'),
(5, 'Swimming Pool', '3pm-5pm', '2023-01-20', 'Cleaning, Sanitizing and Disin'),
(6, 'Public Hall', '1pm-3pm', '2023-01-20', 'Pest Control');

-- --------------------------------------------------------

--
-- Table structure for table `parcel`
--

CREATE TABLE `parcel` (
  `parcelID` int(5) NOT NULL,
  `collectorName` varchar(30) NOT NULL,
  `parcelUnit` varchar(5) NOT NULL,
  `expressBrand` varchar(15) NOT NULL,
  `trackingNumber` varchar(20) NOT NULL,
  `deliveredDate` date NOT NULL,
  `collectStatus` varchar(20) NOT NULL,
  `collectedDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parcel`
--

INSERT INTO `parcel` (`parcelID`, `collectorName`, `parcelUnit`, `expressBrand`, `trackingNumber`, `deliveredDate`, `collectStatus`, `collectedDate`) VALUES
(1, 'Siti Hajar', '1', 'Courier Type', 'MS12E8H9', '2023-01-12', 'Collected', '2023-01-17'),
(4, 'Zubaidah', '7', 'Courier Type', 'GVYT689VD', '2023-01-13', 'Collect Status', '2023-01-15'),
(6, 'Ong Qiao Hui', '3A', 'GD Express', 'GF64FNK8', '2023-01-10', 'Available', NULL),
(7, 'Gugan', '14', 'DHL Ecommerce', 'TDE976HN', '2023-01-15', 'Collected', '2023-01-18');

-- --------------------------------------------------------

--
-- Table structure for table `residents`
--

CREATE TABLE `residents` (
  `residentID` int(10) NOT NULL,
  `username` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `unitno` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `residents`
--

INSERT INTO `residents` (`residentID`, `username`, `email`, `password`, `unitno`) VALUES
(1, 'hajar', 'hjr.raven@gmail.com', '123', 12);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staffID` int(5) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staffID`, `username`, `password`, `email`) VALUES
(1, 'admin', '1234', 'abc@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `visitor`
--

CREATE TABLE `visitor` (
  `visitorID` int(5) NOT NULL,
  `visitorName` varchar(30) NOT NULL,
  `phoneNumber` int(12) NOT NULL,
  `checkInDate` varchar(25) NOT NULL,
  `checkOutDate` varchar(25) NOT NULL,
  `noOfVisitors` int(3) NOT NULL,
  `vehicleType` varchar(10) NOT NULL,
  `plateNumber` varchar(8) NOT NULL,
  `licenseNumber` varchar(15) NOT NULL,
  `parkingNumber` varchar(10) NOT NULL,
  `approveParking` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `visitor`
--

INSERT INTO `visitor` (`visitorID`, `visitorName`, `phoneNumber`, `checkInDate`, `checkOutDate`, `noOfVisitors`, `vehicleType`, `plateNumber`, `licenseNumber`, `parkingNumber`, `approveParking`) VALUES
(3, 'siti', 12, '11-1-2023', '13-1-2023', 12, 'CAR', 'sdwde', '22w2w', 'A1 C104', 'Approved'),
(4, 'adam', 122, '11-1-2023', '13-1-2023', 1, 'CAR', 'zzsxxe1', 'sxddc233', 'A1 M103', 'Approved'),
(6, 'tiha', 11, '14-1-2023', '15-1-2023', 2, 'MOTORCYCLE', 'tuhff', '192.168.146.86', 'A1 C100', 'Approved'),
(7, 'fadlihayati', 1127539217, '14-1-2023', '15-1-2023', 2, 'CAR', 'WWG6753', '731120140052', 'A1 C108', 'Approved'),
(8, 'israt', 123456, '16-1-2023', '16-1-2023', 2, 'MOTORCYCLE', 'wrtguy', 'cghyyy31233', 'A1 M102', 'Approved'),
(9, 'Steven', 129260763, '19-1-2023', '21-1-2023', 2, 'CAR', 'VJP 1578', '64940478', 'A1 C107', 'Approved'),
(10, 'hui', 12394040, '18-1-2023', '18-1-2023', 3, 'CAR', 'VJP1375', '499494', 'Rejected', 'Rejected'),
(11, 'xx', 12946504, '18-1-2023', '22-1-2023', 2, 'CAR', 'MSB3949', '848484', 'None', 'Pending');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`BookingID`);

--
-- Indexes for table `facility`
--
ALTER TABLE `facility`
  ADD PRIMARY KEY (`FacilityID`);

--
-- Indexes for table `maintenance`
--
ALTER TABLE `maintenance`
  ADD PRIMARY KEY (`MaintenanceID`);

--
-- Indexes for table `parcel`
--
ALTER TABLE `parcel`
  ADD PRIMARY KEY (`parcelID`);

--
-- Indexes for table `residents`
--
ALTER TABLE `residents`
  ADD PRIMARY KEY (`residentID`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staffID`);

--
-- Indexes for table `visitor`
--
ALTER TABLE `visitor`
  ADD PRIMARY KEY (`visitorID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `BookingID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `facility`
--
ALTER TABLE `facility`
  MODIFY `FacilityID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `maintenance`
--
ALTER TABLE `maintenance`
  MODIFY `MaintenanceID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `parcel`
--
ALTER TABLE `parcel`
  MODIFY `parcelID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `residents`
--
ALTER TABLE `residents`
  MODIFY `residentID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staffID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `visitor`
--
ALTER TABLE `visitor`
  MODIFY `visitorID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
