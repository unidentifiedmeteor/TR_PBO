-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2025 at 12:07 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tr_pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `kode_dosen` char(10) NOT NULL,
  `nama_dosen` char(100) NOT NULL,
  `password` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`kode_dosen`, `nama_dosen`, `password`) VALUES
('D001', 'Dosen Percobaan', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `dosen_matkul`
--

CREATE TABLE `dosen_matkul` (
  `kode_dosen` char(10) NOT NULL,
  `kode_matkul` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dosen_matkul`
--

INSERT INTO `dosen_matkul` (`kode_dosen`, `kode_matkul`) VALUES
('D001', 'TC212');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `kode_kelas` char(10) NOT NULL,
  `nama_kelas` varchar(100) NOT NULL,
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat','Sabtu','Minggu') NOT NULL,
  `jadwal_mulai` time NOT NULL,
  `jadwal_selesai` time NOT NULL,
  `kode_dosen` char(10) NOT NULL,
  `kode_matkul` char(10) NOT NULL,
  `ruangan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`kode_kelas`, `nama_kelas`, `hari`, `jadwal_mulai`, `jadwal_selesai`, `kode_dosen`, `kode_matkul`, `ruangan`) VALUES
('TC212A', 'Pemrograman Berorientasi Objek A', 'Senin', '12:00:00', '15:00:00', 'D001', 'TC212', 'FTI455'),
('TC212B', 'Pemrograman Berorientasi Objek B', 'Senin', '07:00:00', '10:00:00', 'D001', 'TC212', 'FTI455');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `NIM` char(10) NOT NULL,
  `nama_mahasiswa` char(100) NOT NULL,
  `IPK` decimal(3,2) NOT NULL,
  `password` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`NIM`, `nama_mahasiswa`, `IPK`, `password`) VALUES
('672023001', 'Tes 3', 4.00, 'tes'),
('672024000', 'Mahasiswa Tes', 3.99, 'abcdefg'),
('672024001', 'Tes 2', 1.00, 'lololol');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa_kelas`
--

CREATE TABLE `mahasiswa_kelas` (
  `NIM` char(10) NOT NULL,
  `kode_kelas` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa_kelas`
--

INSERT INTO `mahasiswa_kelas` (`NIM`, `kode_kelas`) VALUES
('672024001', 'TC212A');

-- --------------------------------------------------------

--
-- Table structure for table `matkul`
--

CREATE TABLE `matkul` (
  `kode_matkul` char(10) NOT NULL,
  `nama_matkul` char(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `matkul`
--

INSERT INTO `matkul` (`kode_matkul`, `nama_matkul`) VALUES
('TC212', 'Pemrograman Berbasis Objek'),
('TC223', 'Pemrograman Web');

-- --------------------------------------------------------

--
-- Table structure for table `super_admin`
--

CREATE TABLE `super_admin` (
  `Admin_ID` char(10) NOT NULL,
  `username` char(50) NOT NULL,
  `password` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`kode_dosen`);

--
-- Indexes for table `dosen_matkul`
--
ALTER TABLE `dosen_matkul`
  ADD PRIMARY KEY (`kode_dosen`,`kode_matkul`),
  ADD KEY `kode_matkul` (`kode_matkul`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`kode_kelas`),
  ADD KEY `kode_dosen` (`kode_dosen`),
  ADD KEY `kode_matkul` (`kode_matkul`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`NIM`);

--
-- Indexes for table `mahasiswa_kelas`
--
ALTER TABLE `mahasiswa_kelas`
  ADD PRIMARY KEY (`NIM`,`kode_kelas`),
  ADD KEY `kode_kelas` (`kode_kelas`);

--
-- Indexes for table `matkul`
--
ALTER TABLE `matkul`
  ADD PRIMARY KEY (`kode_matkul`);

--
-- Indexes for table `super_admin`
--
ALTER TABLE `super_admin`
  ADD PRIMARY KEY (`Admin_ID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dosen_matkul`
--
ALTER TABLE `dosen_matkul`
  ADD CONSTRAINT `dosen_matkul_ibfk_1` FOREIGN KEY (`kode_dosen`) REFERENCES `dosen` (`kode_dosen`),
  ADD CONSTRAINT `dosen_matkul_ibfk_2` FOREIGN KEY (`kode_matkul`) REFERENCES `matkul` (`kode_matkul`);

--
-- Constraints for table `kelas`
--
ALTER TABLE `kelas`
  ADD CONSTRAINT `kelas_ibfk_1` FOREIGN KEY (`kode_dosen`) REFERENCES `dosen` (`kode_dosen`),
  ADD CONSTRAINT `kelas_ibfk_2` FOREIGN KEY (`kode_matkul`) REFERENCES `matkul` (`kode_matkul`);

--
-- Constraints for table `mahasiswa_kelas`
--
ALTER TABLE `mahasiswa_kelas`
  ADD CONSTRAINT `mahasiswa_kelas_ibfk_1` FOREIGN KEY (`NIM`) REFERENCES `mahasiswa` (`NIM`),
  ADD CONSTRAINT `mahasiswa_kelas_ibfk_2` FOREIGN KEY (`kode_kelas`) REFERENCES `kelas` (`kode_kelas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
