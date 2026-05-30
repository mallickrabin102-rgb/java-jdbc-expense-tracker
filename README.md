# Java JDBC Expense Tracker

A console-based Expense Tracker application built using Java, JDBC, and MySQL. The application allows users to manage daily expenses efficiently through a menu-driven interface.

## Features

* Add Expense
* View All Expenses
* Search Expense by ID
* Search Expense by Category
* Update Expense Details
* Delete Expense
* Calculate Total Spending
* Category Wise Spending Summary

## Technologies Used

* Java
* JDBC
* MySQL
* SQL
* Git
* GitHub

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

## Functionalities

### Add Expense

Stores expense details in MySQL database.

### View Expenses

Displays all stored expenses.

### Search Expense

Search expenses using ID or Category.

### Update Expense

Modify existing expense records.

### Delete Expense

Remove expenses from the database.

### Total Spending

Calculate total amount spent.

### Category Wise Spending

Display total spending grouped by category.

## How to Run

1. Create MySQL database `expensedb`
2. Create the `expenses` table
3. Update database credentials in `DBconnection.java`
4. Add MySQL Connector JAR
5. Compile and run the project

## Author

Rabin Mallick
