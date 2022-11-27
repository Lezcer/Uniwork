/*Practical 07
  Date: Wed, 13-10-2021*/
#Task 1
/*Question 1
Use a select statement with a subquery to display the names of all departments
that are responsible for at least one project that has already started.*/
SELECT DISTINCT D.deptname FROM Dept D INNER JOIN (SELECT deptno FROM Proj WHERE prstdate <= CURRENT_DATE) P ON D.deptno = P.deptno;

/*Question 2
Find the name, start date and duration in months of projects that have the earliest
end date.*/
SELECT projname, prstdate, TIMESTAMPDIFF(MONTH, prstdate, prendate) AS 'Duration in Months' From Proj WHERE prendate <= ALL(SELECT prendate FROM Proj);

/*Question 3
Produce a list of all female employees whose education level is higher than the
average education level of all employees in their departments. Display employee
number, first name and last name.*/
SELECT empno, firstname, lastname FROM Emp WHERE gender = 'F' AND edlevel > (SELECT AVG(edlevel) from Emp);

/*Question 4
Find the lastname, job and salary of employees who do not work on any project.*/
SELECT lastname, job, salary from Emp WHERE empno NOT IN (SELECT empno FROM Pworks);

/*Question 5
List all employees who have a higher education level than all designers. Display
the first name, last name and edlevel of the employee. Assume that designers are
indicated by job = ‘Designer’.*/
SELECT firstname, lastname, edlevel FROM Emp WHERE edlevel > (SELECT MAX(edlevel) FROM Emp WHERE job = 'Designer');

/*Question 6
Get the names of departments that are currently responsible for only one project
each.*/
SELECT D.deptname FROM Dept D INNER JOIN (SELECT deptno FROM Proj GROUP BY deptno HAVING COUNT(projno) = 1) D1P ON D.deptno = D1P.deptno;

/*Question 7
In the PROJ table, a row with a null value in the MAJPROJ column indicates that
the project represented by that row is not a sub-project of any other.
Display the project number, name and end date of all projects along with a remark
of ‘Sub-project’ if the MAJPROJ value is not null and ‘Not a sub-project’ otherwise.
Order the result by project end date.*/
(SELECT projno, projname, prendate, 'Sub-project' AS remark FROM Proj WHERE MAJPROJ IS NULL)
UNION
(SELECT projno, projname, prendate, 'Not a sub-project' AS remark FROM Proj WHERE MAJPROJ IS NOT NULL)
ORDER BY prendate;

/*Question 8
Look at the SQL scripts which creates tables and identify the order in which these
files are needed to be run to create the four given tables and fill them with sample
data.*/

#Task 2
/*Question 3
Create the table Emp2 from table Emp*/
CREATE TABLE Emp2 AS SELECT empno AS eID, CONCAT(lastname, ',', SUBSTR(firstname, 1, 1), '.') AS eName, TIMESTAMPDIFF(YEAR, birthdate, CURRENT_DATE) AS age, sex AS gender, salary FROM Emp;

/*Question 4
Make eID the primary key of Emp2, by using an ALTER TABLE statement of the
form:
ALTER TABLE <tablename> ADD PRIMARY KEY (attributenames);*/
ALTER TABLE Emp2 ADD PRIMARY KEY (eID);

/*Question 5
Create the table Dept2*/
CREATE TABLE Dept2 (
	dID CHAR(3),
	dName VARCHAR(36),
	budget DECIMAL(12,2),
	managerID CHAR(6),
	PRIMARY KEY(dID),
	FOREIGN KEY (managerID) REFERENCES Emp2 (eID)
);

/*Question 6
Insert data into Dept2 from the Emp and Demt tables of Practical 3. Take the
budget of each department as 20% more than the total salary of all employees in
the department. Doesn't Work!*/
INSERT INTO Dept2 VALUES(dID, dName, managerID, budget)
(SELECT deptno, deptname, mgrno 
FROM Dept D INNER JOIN (SELECT workdept, 1.2*SUM(salary) 
FROM Emp 
GROUP BY workdept HAVING workdept = D.deptno) E;

/*Question 7
Use a CREATE TABLE statement with a subquery to create the table Works2,
assuming that the percentage of time each employee works in his/her workdept is
100.*/
CREATE TABLE Works2 AS SELECT empno AS eID, workdept AS dID, 100 AS pct_time FROM Emp;

/*Question 8
Add an attribute called ‘since’ of TIMESTAMP type to Works2 table, using an
ALTER TABLE statement. Give CURRENT_TIMESTAMP as the default value for
this attribute*/
ALTER TABLE Works2 ADD since TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

/*Question 9
Delete all managers of departments from the Works2 table.
Doesn't work?*/
DELETE FROM Works2 W WHERE EXISTS (SELECT * From Dept WHERE mgrno = W.eID);

/*Question 10
Update the since column of Works2 with the hiredate of each employee from the
EMP table. In the update statement of the form:*/
UPDATE

/*Question 11
Define (eid, did) as the primary key of Works2, using ALTER TABLE in the form:
Remember to separate attribute names of the key by commas*/
ALTER TABLE Works2 ADD PRIMARY KEY eID, dID;

/*Retrying Question 6
SELECT deptno, deptname, mgrno 
FROM Dept D 
INNER JOIN 
(SELECT workdept, 1.2*SUM(salary) AS budget FROM Emp GROUP BY workdept) E
ON D.deptno = E.workdept;


SELECT deptno, deptname, mgrno 
FROM Dept D 
INNER JOIN 
(SELECT workdept, 1.2*SUM(salary) AS budget FROM Emp GROUP BY workdept) E
ON D.deptno = E.workdept;*/























