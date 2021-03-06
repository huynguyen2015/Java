-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema qlqa
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema qlqa
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `qlqa` DEFAULT CHARACTER SET utf8 ;
USE `qlqa` ;

-- -----------------------------------------------------
-- Table `qlqa`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`status` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`status` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`role` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`role` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(100) NULL DEFAULT NULL,
  `Type` VARCHAR(45) NULL DEFAULT NULL,
  `StatusId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `Role_Status_ForeignKey_idx` (`StatusId` ASC),
  CONSTRAINT `Role_Status_ForeignKey`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`employee` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`employee` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `FullName` VARCHAR(100) NULL DEFAULT NULL,
  `Phone` VARCHAR(15) NULL DEFAULT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `Password` VARCHAR(255) NULL DEFAULT NULL,
  `Birthday` DATETIME NULL DEFAULT NULL,
  `Address` VARCHAR(255) NULL DEFAULT NULL,
  `IdentityNumber` VARCHAR(15) NULL DEFAULT NULL,
  `RoleId` INT(11) NOT NULL,
  `StartDate` DATETIME NULL DEFAULT NULL,
  `StatusId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Employee_Role_idx` (`RoleId` ASC),
  INDEX `fk_Employee_Status1_idx` (`StatusId` ASC),
  CONSTRAINT `fk_Employee_Role`
    FOREIGN KEY (`RoleId`)
    REFERENCES `qlqa`.`role` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_Status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`stable`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`stable` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`stable` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(100) NULL DEFAULT NULL,
  `StatusId` INT(11) NOT NULL,
  `NumberSlot` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Table_Status1_idx` (`StatusId` ASC),
  CONSTRAINT `fk_Table_Status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`bill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`bill` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`bill` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `EmployeeId` INT(11) NOT NULL,
  `TableId` INT(11) NOT NULL,
  `CreatedAt` DATETIME NULL DEFAULT NULL,
  `NumberOfCustomer` INT(11) NULL DEFAULT NULL,
  `StatusId` INT(11) NOT NULL,
  `Amount` DOUBLE NULL DEFAULT NULL,
  `CustomerPay` DOUBLE NULL DEFAULT NULL,
  `CustomerReturn` DOUBLE NULL DEFAULT NULL,
  `MergeBillId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Bill_Employee1_idx` (`EmployeeId` ASC),
  INDEX `fk_Bill_Table1_idx` (`TableId` ASC),
  INDEX `fk_Bill_Status1_idx` (`StatusId` ASC),
  CONSTRAINT `fk_Bill_Employee1`
    FOREIGN KEY (`EmployeeId`)
    REFERENCES `qlqa`.`employee` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bill_Status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bill_Table1`
    FOREIGN KEY (`TableId`)
    REFERENCES `qlqa`.`stable` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`category` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`category` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(100) NULL DEFAULT NULL,
  `StatusId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Category_Status1_idx` (`StatusId` ASC),
  CONSTRAINT `fk_Category_Status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`food`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`food` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`food` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NULL DEFAULT NULL,
  `Price` DOUBLE NULL DEFAULT NULL,
  `CategoryId` INT(11) NOT NULL,
  `StatusId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Food_Category1_idx` (`CategoryId` ASC),
  INDEX `fk_Food_Status1_idx` (`StatusId` ASC),
  CONSTRAINT `fk_Food_Category1`
    FOREIGN KEY (`CategoryId`)
    REFERENCES `qlqa`.`category` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Food_Status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`billdetail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`billdetail` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`billdetail` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `FoodId` INT(11) NOT NULL,
  `StatusId` INT(11) NOT NULL,
  `BillId` INT(11) NOT NULL,
  `Quantum` INT(11) NOT NULL DEFAULT '1',
  `Description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_BillDetail_Food1_idx` (`FoodId` ASC),
  INDEX `fk_BillDetail_Status1_idx` (`StatusId` ASC),
  INDEX `fk_BillDetail_Bill1_idx` (`BillId` ASC),
  CONSTRAINT `fk_BillDetail_Bill1`
    FOREIGN KEY (`BillId`)
    REFERENCES `qlqa`.`bill` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BillDetail_Food1`
    FOREIGN KEY (`FoodId`)
    REFERENCES `qlqa`.`food` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BillDetail_Status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`certificate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`certificate` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`certificate` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`employeecertificates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`employeecertificates` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`employeecertificates` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `EmployeeId` INT(11) NULL DEFAULT NULL,
  `CertificateId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `Employee_ForeignKey_idx` (`EmployeeId` ASC),
  INDEX `Certificate_ForeignKey_idx` (`CertificateId` ASC),
  CONSTRAINT `EmployeeCertificates_Certificate_ForeignKey`
    FOREIGN KEY (`CertificateId`)
    REFERENCES `qlqa`.`certificate` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EmployeeCertificates_Employee_ForeignKey`
    FOREIGN KEY (`EmployeeId`)
    REFERENCES `qlqa`.`employee` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`item` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`item` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`employeeitems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`employeeitems` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`employeeitems` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `EmployeeId` INT(11) NOT NULL,
  `ItemId` INT(11) NOT NULL,
  `StatusId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Employee_has_Item_Item1_idx` (`ItemId` ASC),
  INDEX `fk_Employee_has_Item_Employee1_idx` (`EmployeeId` ASC),
  INDEX `fk_Employee_has_Item_Status1_idx` (`StatusId` ASC),
  CONSTRAINT `fk_Employee_has_Item_Employee1`
    FOREIGN KEY (`EmployeeId`)
    REFERENCES `qlqa`.`employee` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_has_Item_Item1`
    FOREIGN KEY (`ItemId`)
    REFERENCES `qlqa`.`item` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_has_Item_Status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`shift`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`shift` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`shift` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL DEFAULT NULL,
  `Salary` DECIMAL(10,0) NULL DEFAULT NULL,
  `MinServicers` TINYINT(4) NULL DEFAULT NULL,
  `MaxServicers` TINYINT(4) NULL DEFAULT NULL,
  `MinBartenders` TINYINT(4) NULL DEFAULT NULL,
  `MaxBartenders` TINYINT(4) NULL DEFAULT NULL,
  `MinManagers` TINYINT(4) NULL DEFAULT NULL,
  `MaxManagers` TINYINT(4) NULL DEFAULT NULL,
  `StatusId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `Shift_Status_ForeignKey_idx` (`StatusId` ASC),
  CONSTRAINT `Shift_Status_ForeignKey`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`schedule` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`schedule` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `ShiftId` INT(11) NOT NULL,
  `WorkingDate` DATETIME NULL DEFAULT NULL,
  `StatusId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Employee_has_Shift_Shift1_idx` (`ShiftId` ASC),
  INDEX `fk_Schedule_Status1_idx` (`StatusId` ASC),
  CONSTRAINT `fk_Schedule_Shift`
    FOREIGN KEY (`ShiftId`)
    REFERENCES `qlqa`.`shift` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Schedule_Status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`employeeschedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`employeeschedule` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`employeeschedule` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `EmployeeId` INT(11) NULL DEFAULT NULL,
  `ScheduleId` INT(11) NULL DEFAULT NULL,
  `IsWorked` BIT(1) NULL DEFAULT NULL,
  `MinutesLate` TINYINT(4) NULL DEFAULT NULL,
  `StatusId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `Employee_ForeignKey_idx` (`EmployeeId` ASC),
  INDEX `Schedule_ForeignKey_idx` (`ScheduleId` ASC),
  CONSTRAINT `Employee_ForeignKey`
    FOREIGN KEY (`EmployeeId`)
    REFERENCES `qlqa`.`employee` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Schedule_ForeignKey`
    FOREIGN KEY (`ScheduleId`)
    REFERENCES `qlqa`.`schedule` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`menu` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`menu` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NULL DEFAULT NULL,
  `CreatedAt` DATETIME NULL DEFAULT NULL,
  `StatusId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Schedule_Status1_idx` (`StatusId` ASC),
  CONSTRAINT `fk_Menu_Status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`menufood`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`menufood` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`menufood` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `MenuId` INT(11) NOT NULL,
  `FoodId` INT(11) NOT NULL,
  `StatusId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Menu_has_Food_Food1_idx` (`FoodId` ASC),
  INDEX `fk_Menu_has_Food_Menu1_idx` (`MenuId` ASC),
  INDEX `fk_Menu_has_Food_StatusId_idx` (`StatusId` ASC),
  CONSTRAINT `fk_Menu_has_Food_Food1`
    FOREIGN KEY (`FoodId`)
    REFERENCES `qlqa`.`food` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Menu_has_Food_Menu1`
    FOREIGN KEY (`MenuId`)
    REFERENCES `qlqa`.`menu` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Menu_has_Food_StatusId`
    FOREIGN KEY (`StatusId`)
    REFERENCES `qlqa`.`status` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`permission` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`permission` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `qlqa`.`rolepermissions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qlqa`.`rolepermissions` ;

CREATE TABLE IF NOT EXISTS `qlqa`.`rolepermissions` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `RoleId` INT(11) NOT NULL,
  `PermissionId` INT(11) NOT NULL,
  `IsEnabled` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Role_has_Permission_Permission1_idx` (`PermissionId` ASC),
  INDEX `fk_Role_has_Permission_Role1_idx` (`RoleId` ASC),
  CONSTRAINT `fk_Role_has_Permission_Permission1`
    FOREIGN KEY (`PermissionId`)
    REFERENCES `qlqa`.`permission` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Role_has_Permission_Role1`
    FOREIGN KEY (`RoleId`)
    REFERENCES `qlqa`.`role` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 52
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
