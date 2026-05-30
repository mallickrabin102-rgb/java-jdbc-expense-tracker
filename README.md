# Java JDBC Expense Tracker

A console-based Expense Tracker application built using Java, JDBC, and MySQL.

## Features

* Add Expense
* View Expenses
* Search Expense by ID
* Delete Expense
* Calculate Total Spending

## Technologies Used

* Java
* JDBC
* MySQL
* Git & GitHub

## Database Schema

```sql
CREATE TABLE expenses(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    amount DOUBLE,
    category VARCHAR(50),
    expense_date DATE
);
```

## How to Run

1. Create MySQL database `expensedb`
2. Create the `expenses` table
3. Update database credentials in `DBconnection.java`
4. Compile and run the project

## Project Structure

* ExpenseTracker.java
* DBconnection.java

## Author

Rabin Mallick
