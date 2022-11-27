
/* createemp.sql: MySQL file for table creation in Practical-6 */

-- create Emp table
DROP TABLE IF EXISTS Emp;
CREATE TABLE Emp(
        empno     CHAR(6)	 ,
        firstname VARCHAR(12)           ,
        midinit	  CHAR(1)                ,
        lastname  VARCHAR(15)            ,
        workdept  CHAR(3)                ,
        phoneno   CHAR(4)                ,
        hiredate  DATE                   ,
        job	  CHAR(10)               ,
        edlevel	  INT(2)              	 ,
        gender    CHAR(1)                ,
        birthdate DATE                   ,
        salary    DECIMAL(8,2)           ,
        bonus     DECIMAL(8,2)           ,
        comm      DECIMAL(8,2)		 ,
	PRIMARY KEY(empno),
        FOREIGN KEY(workdept) REFERENCES Dept(deptno)
);