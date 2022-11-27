/* createproj.sql: MySQL fle for table creation in Practical-6 */
DROP TABLE IF EXISTS Proj;
CREATE TABLE Proj (
    projno		CHAR(6) NOT NULL,
    projname	VARCHAR(24) NOT NULL,
    deptno       	CHAR(3) NOT NULL,
    respemp		CHAR(6)	NOT NULL,
    prstdate	DATE,
    prendate	DATE,
    majproj		CHAR(6),
    PRIMARY KEY(projno),
    FOREIGN KEY(deptno) REFERENCES Dept(deptno),
    FOREIGN KEY(respemp) REFERENCES Emp(empno)
);
