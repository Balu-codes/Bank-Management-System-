package com.bank;

import java.util.List;
import java.util.Scanner;

public class Bankmanagementsystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Bank Management System");
            System.out.println("1. Create User");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Thank you for using the Bank Management System.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (User.createUser(username, password)) {
            System.out.println("User created successfully!");
        } else {
            System.out.println("User creation failed. Username might already exist.");
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = User.login(username, password);
        if (user != null) {
            System.out.println("Login successful!");
            userMenu(user);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void userMenu(User user) {
        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Inquiry");
            System.out.println("5. Account Details");
            System.out.println("6. Transaction History");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createAccount(user);
                    break;
                case 2:
                    deposit(user);
                    break;
                case 3:
                    withdraw(user);
                    break;
                case 4:
                    balanceInquiry(user);
                    break;
                case 5:
                    accountDetails(user);
                    break;
                case 6:
                    transactionHistory(user);
                    break;
                case 7:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount(User user) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        if (Account.createAccount(user.getUsername(), accountNumber, accountHolderName, initialBalance)) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Account creation failed. Please try again.");
        }
    }

    private static void deposit(User user) {
        Account account = selectAccount(user);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            account.deposit(amount);
        }
    }

    private static void withdraw(User user) {
        Account account = selectAccount(user);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            account.withdraw(amount);
        }
    }

    private static void balanceInquiry(User user) {
        Account account = selectAccount(user);
        if (account != null) {
            System.out.println("Current Balance: " + account.getBalance());
        }
    }

    private static void accountDetails(User user) {
        Account account = selectAccount(user);
        if (account != null) {
            account.displayAccountDetails();
        }
    }

    private static void transactionHistory(User user) {
        Account account = selectAccount(user);
        if (account != null) {
            List<Transaction> transactions = account.getTransactionHistory();
            if (transactions.isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                System.out.println("Transaction History:");
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            }
        }
    }

    private static Account selectAccount(User user) {
        List<Account> accounts = user.getAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found. Please create an account first.");
            return null;
        }
        System.out.println("Select an account:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).getAccountNumber());
        }
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return accounts.get(choice - 1);
    }
}
