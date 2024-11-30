/**
 * SELECT a.NAME As Employee from Employee As a JOIN Employee AS b where a.ManagerId = b.Id AND
 * a.salary > b.salary;
 */

/** 182 select email from Person group by email having count(email) > 1; */
/**
 * 183 Select name as 'Customers' from customers where Id not in (select customerId from Orders);
 */

/**
 * select department.name as Department, employee.name as Employee, Salary from Employee JOIN
 * Department ON employee.departmentid = department.id where (Employee.departmentid, Salary) IN
 * (select departmentid, max(salary) from Employee group by departmentid);
 */

/**
 * Select d1.Name as 'Department',e1.name as 'Employee', e1.salary as 'Salary' from Employee e1 JOIN
 * Department d1 ON e1.departmentid = d1.id where 3 > ( Select count(distinct e2.salary ) from
 * Employee e2 where e2.salary > e1.salary and e1.departmentid = e2.departmentid);
 */
/**
 * 196. Delete Duplicate Emails Delete p1 from Person p1, Person p2 where p1.email = p2.email and
 * p1.Id > p2.Id;
 */

/**
 * 197. Rising Temperature Select w1.id as 'Id' from Weather w1 JOIN weather w2 on
 * DateDiff(w1.recordDate, w2.recordDate) = 1 And w1.Temperature > w2.Temperature;
 */
public class LC_0181_SQLEmployeeEarning {}
