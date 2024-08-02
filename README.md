# Bank-Management-System-

This project is a simple Bank Management System implemented in Java. It allows users to create accounts, deposit and withdraw money, and view account details and transaction history. The data is stored using MySQL and accessed via JDBC.

## Features
- User registration and login
- Account creation
- Deposit and withdraw money
- View account balance and details
- View transaction history

## Requirements
- Java Development Kit (JDK) 8 or higher
- MySQL Server
- Eclipse IDE (optional, but recommended)

## Setup Instructions
### Step 1: Set Up Your Database

1. Install MySQL Server if you haven't already. You can download it from the [MySQL official website](https://dev.mysql.com/downloads/installer/).
2. Create a new database and tables:

```sql
CREATE DATABASE bank_management;

USE bank_management;

CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE accounts (
    account_number VARCHAR(50) PRIMARY KEY,
    account_holder_name VARCHAR(50) NOT NULL,
    balance DOUBLE NOT NULL,
    username VARCHAR(50),
    FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(50),
    type VARCHAR(50),
    amount DOUBLE,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);

### Step 1: Set Up Your Database
