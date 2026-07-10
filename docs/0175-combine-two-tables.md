---
description: EASY
---

# 175. Combine Two Tables

**SQL Schema**

```sql
Table: Person
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| personId    | int     |
| lastName    | varchar |
| firstName   | varchar |
+-------------+---------+
personId is the primary key column for this table.

Table: Address
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| addressId   | int     |
| personId    | int     |
| city        | varchar |
| state       | varchar |
+-------------+---------+
addressId is the primary key column for this table.
```

Write an SQL query to report the first name, last name, city, and state of each person in the `Person` table. If the address of a `personId` is not present in the `Address` table, report `null` instead.

Return the result table in **any order**.

**Example 1:**

```text
Input:
Person table:
+----------+----------+-----------+
| personId | lastName | firstName |
+----------+----------+-----------+
| 1        | Wang     | Allen     |
| 2        | Alice    | Bob       |
+----------+----------+-----------+

Address table:
+-----------+----------+---------------+------------+
| addressId | personId | city          | state      |
+-----------+----------+---------------+------------+
| 1         | 2        | New York City | New York   |
| 2         | 3        | Leetcode      | California |
+-----------+----------+---------------+------------+

Output:
+-----------+----------+---------------+----------+
| firstName | lastName | city          | state    |
+-----------+----------+---------------+----------+
| Allen     | Wang     | Null          | Null     |
| Bob       | Alice    | New York City | New York |
+-----------+----------+---------------+----------+
```

**Constraints:**

- The `Person` table contains unique `personId` values.
- The `Address` table may not have an entry for every person.

---

## Approach: LEFT JOIN

Use a `LEFT JOIN` from `Person` to `Address` on `personId`. A `LEFT JOIN` ensures every row from `Person` appears in the result even when there is no matching row in `Address`, producing `NULL` for the city and state columns in that case.

#### Complexity Analysis

- **Time complexity: O(m * n) worst case** (without indices). With an index on `Address.personId` the join runs in O(m + n).
- **Space complexity: O(m).** The result set contains exactly one row per person.

```sql
SELECT FirstName, LastName, City, State
FROM Person AS p
LEFT JOIN Address AS a ON p.PersonId = a.PersonId;
```

**Key insight:** A `LEFT JOIN` (rather than an `INNER JOIN`) is required so that persons without a matching address row still appear in the output with `NULL` city and state values.
