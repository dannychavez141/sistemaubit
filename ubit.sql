-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-07-2019 a las 11:05:06
-- Versión del servidor: 5.5.40
-- Versión de PHP: 5.5.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `ubit`
--
CREATE DATABASE IF NOT EXISTS `ubit` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `ubit`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
`idclientes` int(11) NOT NULL,
  `ruc` varchar(11) DEFAULT NULL,
  `razonsocial` varchar(100) DEFAULT NULL,
  `dir` varchar(100) DEFAULT NULL,
  `telf1` varchar(11) DEFAULT NULL,
  `est` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratos`
--

DROP TABLE IF EXISTS `contratos`;
CREATE TABLE IF NOT EXISTS `contratos` (
`idcontratos` int(11) NOT NULL,
  `idclientes` int(11) NOT NULL,
  `idempresas` int(11) NOT NULL,
  `comentario` varchar(300) DEFAULT NULL,
  `pago` double DEFAULT NULL,
  `fechinicio` date DEFAULT NULL,
  `fechafin` date DEFAULT NULL,
  `idusuario` int(11) NOT NULL,
  `idestado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallescontrato`
--

DROP TABLE IF EXISTS `detallescontrato`;
CREATE TABLE IF NOT EXISTS `detallescontrato` (
  `idcontratos` int(11) NOT NULL,
  `idservicios` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresas`
--

DROP TABLE IF EXISTS `empresas`;
CREATE TABLE IF NOT EXISTS `empresas` (
`idempresas` int(11) NOT NULL,
  `ruc` varchar(11) DEFAULT NULL,
  `razonsocial` varchar(100) DEFAULT NULL,
  `dir` varchar(100) DEFAULT NULL,
  `telf1` varchar(11) DEFAULT NULL,
  `est` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empresas`
--

INSERT INTO `empresas` (`idempresas`, `ruc`, `razonsocial`, `dir`, `telf1`, `est`) VALUES
(1, '15421242121', 'ALCATEL COMUNICACION', 'POR ALLI', '994124554', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

DROP TABLE IF EXISTS `estado`;
CREATE TABLE IF NOT EXISTS `estado` (
`idestado` int(11) NOT NULL,
  `descr` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`idestado`, `descr`) VALUES
(1, 'ACTIVO'),
(2, 'INACTIVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

DROP TABLE IF EXISTS `servicios`;
CREATE TABLE IF NOT EXISTS `servicios` (
`idservicios` int(11) NOT NULL,
  `desc` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`idservicios`, `desc`) VALUES
(1, 'INSTALACION ELECTRICA'),
(2, 'CONEXION A POZO A TIERRA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
`idusuario` int(11) NOT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `nombres` varchar(100) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `dir` varchar(45) DEFAULT NULL,
  `telf` varchar(11) DEFAULT NULL,
  `pass` varchar(45) DEFAULT NULL,
  `est` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `dni`, `nombres`, `apellidos`, `dir`, `telf`, `pass`, `est`) VALUES
(1, '123456', 'FELIPE', 'RAMIREZ NAVARRO', 'CASITA', '1234567', '1234', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
 ADD PRIMARY KEY (`idclientes`);

--
-- Indices de la tabla `contratos`
--
ALTER TABLE `contratos`
 ADD PRIMARY KEY (`idcontratos`), ADD KEY `fk_contratos_clientes_idx` (`idclientes`), ADD KEY `fk_contratos_empresas1_idx` (`idempresas`), ADD KEY `fk_contratos_usuario1_idx` (`idusuario`), ADD KEY `fk_contratos_estado1_idx` (`idestado`);

--
-- Indices de la tabla `detallescontrato`
--
ALTER TABLE `detallescontrato`
 ADD PRIMARY KEY (`idcontratos`,`idservicios`), ADD KEY `fk_contratos_has_servicios_servicios1_idx` (`idservicios`), ADD KEY `fk_contratos_has_servicios_contratos1_idx` (`idcontratos`);

--
-- Indices de la tabla `empresas`
--
ALTER TABLE `empresas`
 ADD PRIMARY KEY (`idempresas`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
 ADD PRIMARY KEY (`idestado`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
 ADD PRIMARY KEY (`idservicios`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
 ADD PRIMARY KEY (`idusuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
MODIFY `idclientes` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `contratos`
--
ALTER TABLE `contratos`
MODIFY `idcontratos` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `empresas`
--
ALTER TABLE `empresas`
MODIFY `idempresas` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
MODIFY `idestado` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `servicios`
--
ALTER TABLE `servicios`
MODIFY `idservicios` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
