
/* createdept.sql: MySQL file for table creation in Practical-6 */

-- create dept table
DROP TABLE IF EXISTS Dept;
CREATE TABLE Dept(
            deptno   CHAR(3),
            deptname VARCHAR(36) NOT NULL,
            mgrno    CHAR(6),
            admrdept CHAR(3) NOT NULL,
        	PRIMARY KEY(DEPTNO)
);
