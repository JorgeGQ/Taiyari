-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: taiyari
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `idAdmin` varchar(20) NOT NULL COMMENT 'Identificador único del administrador. Referencia a usuarios.nombreUsuario.',
  `nombres` varchar(20) DEFAULT NULL COMMENT 'Nombres del administrador.',
  `apellidoP` varchar(15) DEFAULT NULL COMMENT 'Apellido paterno del administrador.',
  `apellidoM` varchar(15) DEFAULT NULL COMMENT 'Apellido materno del administrador.',
  `edad` int DEFAULT NULL COMMENT 'Edad del administrador.',
  `correo` varchar(50) DEFAULT NULL COMMENT 'Correo electrónico del administrador.',
  PRIMARY KEY (`idAdmin`),
  CONSTRAINT `usuarioAdministrador` FOREIGN KEY (`idAdmin`) REFERENCES `usuario` (`nombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Contiene los datos personales y de contacto de los usuarios con rol de administrador. Cada registro está vinculado a la tabla usuarios mediante una clave foránea, lo que permite gestionar credenciales y roles de forma centralizada.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES ('Olaf','Jorge Olaf','Garcia','Quiroga',18,'jorgegarcia@utxicotepec');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumno` (
  `matricula` varchar(20) NOT NULL COMMENT 'Identificador único del alumno. Referencia a usuarios.nombreUsuario.',
  `tutor` varchar(20) DEFAULT NULL COMMENT 'Clave foránea que indica el tutor grupal asignado. Referencia a tutor.noTrabajador.',
  `nombres` varchar(20) DEFAULT NULL COMMENT 'Nommbres del alumno.',
  `apellidoP` varchar(15) DEFAULT NULL COMMENT 'Apellido Paterno del Alumno',
  `apellidoM` varchar(15) DEFAULT NULL COMMENT 'Apellido Materno del Alumno',
  `edad` int DEFAULT NULL COMMENT 'Edad del Alumpo',
  `telefono` varchar(12) DEFAULT NULL COMMENT 'Número de contacto del alumno.',
  `correo` varchar(50) DEFAULT NULL COMMENT 'Correo electrónico del alumno.',
  `grupo` varchar(5) DEFAULT NULL COMMENT 'Grupo académico al que pertenece el alumno.',
  `area` varchar(80) DEFAULT NULL COMMENT 'Área académica en la que está inscrito el alumno.',
  `carrera` varchar(80) DEFAULT NULL COMMENT 'Carrera o programa académico que cursa el alumno.',
  PRIMARY KEY (`matricula`),
  KEY `tutorGrupal_idx` (`tutor`),
  CONSTRAINT `tutorGrupal` FOREIGN KEY (`tutor`) REFERENCES `tutor` (`noTrabajador`),
  CONSTRAINT `usuarioAlumno` FOREIGN KEY (`matricula`) REFERENCES `usuario` (`nombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Registra la información personal, académica y de contacto de los alumnos del sistema. Cada alumno está vinculado a un usuario del sistema y a un tutor grupal responsable de su seguimiento académico.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES ('240463','00981','Juan','Pérez','López',20,'5551234567','juan.perez@utxicotepec','3A','Tic','DSM');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bitacora`
--

DROP TABLE IF EXISTS `bitacora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bitacora` (
  `idBitacora` int NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la entrada en la bitácora.',
  `fechaHora` datetime DEFAULT NULL COMMENT 'Fecha y hora en que se realizó la anotación.',
  `emocion` varchar(15) DEFAULT NULL COMMENT 'Descripción breve del estado emocional del alumno al momento de escribir.',
  `anotaciones` text COMMENT 'Entrada tipo diario escrita por el alumno.',
  `alumno` varchar(20) DEFAULT NULL COMMENT 'Clave foránea que identifica al alumno autor. Referencia a alumno.matricula.',
  PRIMARY KEY (`idBitacora`),
  KEY `alummnoRedactor_idx` (`alumno`),
  CONSTRAINT `alummnoRedactor` FOREIGN KEY (`alumno`) REFERENCES `alumno` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Registra entradas personales realizadas por alumnos, incluyendo la fecha, emociones expresadas y anotaciones tipo diario. Cada registro está vinculado a un alumno específico que lo redactó.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bitacora`
--

LOCK TABLES `bitacora` WRITE;
/*!40000 ALTER TABLE `bitacora` DISABLE KEYS */;
INSERT INTO `bitacora` VALUES (1,'2025-08-19 12:23:00','Feliz','Se encuentra feliz','240463'),(2,'2025-08-01 07:12:00','Triste','Se encuentra triste','240463');
/*!40000 ALTER TABLE `bitacora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cita`
--

DROP TABLE IF EXISTS `cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cita` (
  `idCita` int NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la cita.',
  `solicitadaPor` varchar(15) DEFAULT NULL COMMENT 'Indica quién solicitó la cita: alumno, tutor o psicólogo.',
  `motivo` text COMMENT 'Razón por la cual se solicita la cita.',
  `fechaHora` datetime DEFAULT NULL COMMENT 'Fecha y hora programada para la cita.',
  `estado` varchar(15) DEFAULT NULL COMMENT 'Estado actual de la cita: pendiente, realizada, cancelada, etc.',
  `Paciente` varchar(20) DEFAULT NULL COMMENT 'Clave foránea que identifica al alumno atendido. Referencia a alumno.matricula.',
  `tutorGrupal` varchar(20) DEFAULT NULL COMMENT 'Clave foránea del tutor involucrado. Referencia a tutor.noTrabajadorr.',
  `psicologo` varchar(20) DEFAULT NULL COMMENT 'Clave foránea del psicólogo que atenderá la cita. Referencia a psicologo.noPsicologo.',
  PRIMARY KEY (`idCita`),
  KEY `alumnoPaciente_idx` (`Paciente`),
  KEY `tutorGrupo_idx` (`tutorGrupal`),
  KEY `psicologoAsignado_idx` (`psicologo`),
  CONSTRAINT `alumnoPaciente` FOREIGN KEY (`Paciente`) REFERENCES `alumno` (`matricula`),
  CONSTRAINT `psicologoAsignado` FOREIGN KEY (`psicologo`) REFERENCES `psicologo` (`noPsicologo`),
  CONSTRAINT `tutorGrupo` FOREIGN KEY (`tutorGrupal`) REFERENCES `tutor` (`noTrabajador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Registra las citas agendadas en el sistema, incluyendo quién la solicitó, el motivo, el estado actual y los participantes involucrados (paciente, tutor y psicólogo). Permite trazabilidad de atención y seguimiento.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `psicologo`
--

DROP TABLE IF EXISTS `psicologo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `psicologo` (
  `noPsicologo` varchar(20) NOT NULL COMMENT 'Identificador único del psicólogo. Referencia a usuarios.nombreUsuario.',
  `cedulaProfesional` varchar(10) DEFAULT NULL COMMENT 'Número de cédula profesional del psicólogo.',
  `ingresadoPor` varchar(20) DEFAULT NULL COMMENT 'Usuario administrador que registró al psicólogo. Referencia a administrador.idAdministrador.',
  `nombres` varchar(20) DEFAULT NULL COMMENT 'Nombres del Psicologo',
  `apellidoP` varchar(15) DEFAULT NULL COMMENT 'Apellido Paterno del psicologo',
  `apellidoM` varchar(15) DEFAULT NULL COMMENT 'Apellido Materno del psicologo',
  `edad` int DEFAULT NULL COMMENT 'Edad del psicologo',
  `telefono` varchar(12) DEFAULT NULL COMMENT 'Número de contacto del psicólogo.',
  `correo` varchar(50) DEFAULT NULL COMMENT 'Correo Electronico del psicologo',
  PRIMARY KEY (`noPsicologo`),
  KEY `registroPsicologo_idx` (`ingresadoPor`),
  CONSTRAINT `registroPsicologo` FOREIGN KEY (`ingresadoPor`) REFERENCES `administrador` (`idAdmin`),
  CONSTRAINT `usuarioPsicologo` FOREIGN KEY (`noPsicologo`) REFERENCES `usuario` (`nombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Registra la información personal, profesional y de contacto de los psicólogos del sistema. Cada psicólogo está vinculado a un usuario registrado y a un administrador responsable de su alta.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `psicologo`
--

LOCK TABLES `psicologo` WRITE;
/*!40000 ALTER TABLE `psicologo` DISABLE KEYS */;
/*!40000 ALTER TABLE `psicologo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor`
--

DROP TABLE IF EXISTS `tutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutor` (
  `noTrabajador` varchar(20) NOT NULL COMMENT 'Identificador único del tutor. Referencia a usuarios.nombreUsuario.',
  `registradoPor` varchar(20) DEFAULT NULL COMMENT 'Usuario administrador que registró al tutor. Referencia a administrador.idAdministrador.',
  `nombres` varchar(20) DEFAULT NULL COMMENT 'Nombres del tutor',
  `apellidoP` varchar(15) DEFAULT NULL COMMENT 'Apellido paterno de tutor',
  `apellidoM` varchar(15) DEFAULT NULL COMMENT 'Apellido materno deltutor',
  `edad` int DEFAULT NULL COMMENT 'Edad del tutor',
  `telefono` varchar(12) DEFAULT NULL COMMENT 'Número de contacto del tutor.',
  `correo` varchar(50) DEFAULT NULL COMMENT 'Correo electrónico del tutor.',
  `grupoTutorado` varchar(5) DEFAULT NULL COMMENT 'Grupo académico asignado al tutor.',
  `area` varchar(20) DEFAULT NULL COMMENT 'Área académica en la que el tutor desempeña funciones.',
  PRIMARY KEY (`noTrabajador`),
  KEY `registroPor_idx` (`registradoPor`),
  CONSTRAINT `registroPor` FOREIGN KEY (`registradoPor`) REFERENCES `administrador` (`idAdmin`),
  CONSTRAINT `usuarioTutor` FOREIGN KEY (`noTrabajador`) REFERENCES `usuario` (`nombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Almacena la información personal, académica y de contacto de los tutores registrados en el sistema. Cada tutor está vinculado a un usuario del sistema y a un administrador responsable de su registro.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutor`
--

LOCK TABLES `tutor` WRITE;
/*!40000 ALTER TABLE `tutor` DISABLE KEYS */;
INSERT INTO `tutor` VALUES ('00981','Olaf','Marco','Ramirez','Hernandez',40,'556773542','marco.ram@utxicotepec','3B','Tic');
/*!40000 ALTER TABLE `tutor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `nombreUsuario` varchar(20) NOT NULL COMMENT 'Identificador único del usuario. Se utiliza como login.',
  `contrasena` varchar(25) DEFAULT NULL COMMENT 'Contraseña del usuario para autenticación.',
  `rol` varchar(15) DEFAULT NULL COMMENT 'Tipo de usuario (Administrador, Psicologo, Tutor, Alumno).',
  PRIMARY KEY (`nombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Almacena las credenciales y el tipo de acceso de cada usuario registrado en el sistema. Esta tabla es clave para la autenticación y autorización de funciones dentro de la aplicación.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('00981','1234','Tutor'),('240463','1234','Alumno'),('Olaf','1234','Administrador');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-19 15:11:46
