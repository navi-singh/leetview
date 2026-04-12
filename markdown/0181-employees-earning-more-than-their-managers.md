---
description: EASY
---

# 181. Employees Earning More Than Their Managers

**SQL Schema**

```sql
Table: Employee
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| salary      | int     |
| managerId   | int     |
+-------------+---------+
id is the primary key column for this table.
Each row indicates the ID of an employee, their name, salary, and the ID of their manager.
```

Write an SQL query to find the employees who earn more than their managers.

Return the result table in **any order**.

**Example 1:**

```text
Input:
Employee table:
+----+-------+--------+-----------+
| id | name  | salary | managerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | Null      |
| 4  | Max   | 90000  | Null      |
+----+-------+--------+-----------+

Output:
+----------+
| Employee |
+----------+
| Joe      |
+----------+
Explanation: Joe is the only employee who earns more than his manager.
```

**Constraints:**

- The table holds every employee, including those who are also managers (managers have `NULL` as their managerId).

---

## Approach: Self-Join on Manager Relationship

Join the `Employee` table with itself, matching each employee's `ManagerId` to the manager's `Id`. Filter the result to rows where the employee's salary is greater than the manager's salary.

#### Complexity Analysis

- **Time complexity: O(n^2) worst case** without indices. With an index on `id`, the join runs in O(n).
- **Space complexity: O(k).** Where `k` is the number of employees earning more than their manager.

```sql
SELECT a.NAME AS Employee
FROM Employee AS a
JOIN Employee AS b
WHERE a.ManagerId = b.Id
  AND a.salary > b.salary;
```

**Key insight:** A self-join on the same table — treating one alias as the employee and the other as the manager — is the natural way to compare two rows that share a parent-child relationship within a single table.
