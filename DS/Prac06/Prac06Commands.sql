/*Practical 06
  Date: Tue, 22-09-2021*/
#Task 2
-- create new database "company"
CREATE DATABASE company;
-- verify database was created
SHOW DATABASES;
-- choose company database
USE company;
-- run the rebuild.sql script
SOURCE rebuild.sql;
-- verify tables have been created
SHOW TABLES;
-- verify attributes on each table
DESC Dept;
DESC Emp;
DESC Proj;
DESC Pworks;
-- verify data has been inserted into each table
SELECT * FROM Dept;
SELECT * FROM Emp;
SELECT * FROM Proj;
SELECT * FROM Pworks;

#Task 3
-- Q1. Write a join query to display the last name, job and salary of employees who are responsible for projects where the project name includes the string 'Design'. Display the project names also in the query results to verify the results.
SELECT Emp.lastname, Emp.job, Emp.salary FROM Emp INNER JOIN Proj ON Emp.empno=Proj.respemp WHERE Proj.projname LIKE '%Design%';

-- Q2. Use a join query to show the firstname, lastname job, managing department number and the name of the managing department of each department manager. Use suitable column names for the managing department number and the name of the managing department.
SELECT Emp.firstname, Emp.lastname, Emp.job, Emp.workdept AS Deptno, Dept.deptname FROM Dept INNER JOIN Emp ON Dept.mgrno=Emp.empno;

-- Q3. Revise the above query (Q2) to show the department number, department name, manager’s firstname, lastname and job for each department.
SELECT Dept.deptno, Dept.deptname, Emp.firstname, Emp.lastname, Emp.job,  FROM Dept INNER JOIN Emp ON Dept.mgrno=Emp.empno;

-- Q4. Use a join query to display the last name, job and education level of all employees who have at least the education level of the employee whose job description is ‘Pres’.
SELECT E1.lastname, E1.job, E1.edlevel FROM Emp E1, Emp E2 WHERE E2.job = 'Pres' AND E2.edlevel <= E1.edlevel;

-- Q5. Show the minimum salary, maximum salary and the number of employees in the job category for each job.
SELECT job, MIN(salary), MAX(salary), count(*) FROM Emp GROUP BY job;

-- Q6. For each workdept that has more than 5 employees, find the average salary of employees. Display workdept and the number of employees in each workdept also in the query results.
SELECT workdept, AVG(salary), COUNT(*) FROM Emp GROUP BY workdept HAVING COUNT(*) > 5;
/* this worked somehow:
SELECT Dept.deptno, AVG(Emp.salary), COUNT(*) FROM Dept INNER JOIN Emp ON Dept.deptno = Emp.workdept GROUP BY workdept HAVING COUNT(*) > 5;
*/

-- Q7. For all employees who work on projects, get the empno, lastname, salary, and the total number of hours per week they work on projects. Display the total number of hours per week they work under a column ‘TotalHrs’.
SELECT Emp.empno, Emp.lastname, Emp.salary, Pworks.hours AS TotalHrs FROM Emp NATURAL JOIN Pworks;

-- Q8. For all employees, show the empno, lastname, salary, total number of hours per week they work on projects and the number of projects they work . Display the total number of hours per week they work under a column ‘TotalHrs’. If any employee is not working in projects, total number of hours per week they work on projects should be shown as NULL.
SELECT Emp.empno, Emp.lastname, Emp.salary, Pworks.hours AS TotalHrs FROM Emp LEFT OUTER JOIN Pworks ON Emp.empno=Pworks.empno;

-- Q9. In the query results of Q8, identify by which column the results are sorted in ascending order. ( This is the default ordering) Show the same results of the Q8, sorted by TotalHrs’’ in descending order.
SELECT Emp.empno, Emp.lastname, Emp.salary, Pworks.hours AS TotalHrs FROM Emp LEFT OUTER JOIN Pworks ON Emp.empno=Pworks.empno ORDER BY Pworks.hours DESC;

-- Q10. For each project, list the project number and project name along with the project name and the project number of any sub-projects. All the project, irrespective whether they have sub-projects or not should be listed.
SELECT P1.projno, P1.projname, P2.projno, P2.projname FROM Proj P1 RIGHT OUTER JOIN Proj P2 ON P1.projno=P2.majorproj; 

-- Q11. For projects with sub-projects, list the project number and project name along with the project name and project number of any sub-projects. (Only the projects having sub-projects should be listed)
SELECT P1.projno, P1.projname, P2.projno, P2.projname FROM Proj P1 INNER JOIN Proj P2 ON P1.projno=P2.majproj;

