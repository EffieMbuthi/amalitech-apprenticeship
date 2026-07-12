# Bank Account Management System

A console-based Java application for managing bank accounts, customers, and transactions, built as part of the AmaliTech DEG Java Backend apprenticeship.

## Features
- Create Savings or Checking accounts for Regular or Premium customers
- View all accounts with balances and details
- Process deposits and withdrawals with business rule validation
- View transaction history per account
- Simple console menu navigation

## Tech Stack
- Java 17
- Maven

## Project Structure
- `Customer` (abstract), `RegularCustomer`, `PremiumCustomer`
- `Account` (abstract), `SavingsAccount`, `CheckingAccount`
- `Transactable` (interface)
- `Transaction`, `AccountManager`, `TransactionManager`
- `Main`

## OOP Concepts Demonstrated
- Encapsulation: private fields with public getters/setters
- Inheritance: Customer and Account hierarchies
- Abstraction: abstract classes and interface (Transactable)
- Polymorphism: method overriding (withdraw, displayAccountDetails, getAccountType)
- Composition: AccountManager holds Account[], TransactionManager holds Transaction[]
- Static fields: used for auto-generating unique IDs (CUS, ACC, TXN)

## How to Run
```bash
mvn compile
mvn exec:java -Dexec.mainClass="org.example.Main"
```
Or run `Main.java` directly from IntelliJ.

## Business Rules
- Savings accounts: 3.5% annual interest, $500 minimum balance enforced on withdrawal
- Checking accounts: $1,000 overdraft limit, $10 monthly fee (waived for Premium customers)
- Premium customers: $10,000 minimum balance, waived fees, priority service