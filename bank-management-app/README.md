# Bank Account Management System

A console-based Java application for managing bank accounts, customers, and transactions.

## Features
- Create Savings or Checking accounts for Regular or Premium customers
- View all accounts with balances and details
- Process deposits and withdrawals with business rule validation
- View transaction history per account, with total deposits/withdrawals/net change
- Custom exceptions for invalid input and failed transactions, with clear console error messages
- Simple console menu navigation

## Tech Stack
- Java 17
- Maven
- JUnit 5 (Jupiter)

## Project Structure

```
src/main/java/org/example/
├── Main.java
├── Transactable.java
├── model/
│   ├── Customer.java (abstract), RegularCustomer.java, PremiumCustomer.java
│   ├── Account.java (abstract), SavingsAccount.java, CheckingAccount.java
│   └── Transaction.java
├── service/
│   ├── AccountManager.java
│   └── TransactionManager.java
└── exceptions/
    ├── InvalidAmountException.java
    ├── InsufficientFundsException.java
    ├── OverdraftExceededException.java
    └── InvalidAccountException.java

src/test/java/org/example/
├── model/AccountTest.java
├── service/TransactionManagerTest.java
└── exceptions/ExceptionsTest.java

docs/
└── git-workflow.md
```

## OOP Concepts Demonstrated
- Encapsulation: private fields with public getters/setters
- Inheritance: Customer and Account hierarchies
- Abstraction: abstract classes and interface (Transactable)
- Polymorphism: method overriding (withdraw, displayAccountDetails, getAccountType)
- Composition: AccountManager holds Account[], TransactionManager holds Transaction[]
- Static fields: used for auto-generating unique IDs (CUS, ACC, TXN)

## Exception Handling

Invalid operations throw custom, unchecked exceptions rather than returning `false`/`null`. `Main` catches each type and prints a clear, user-facing error message instead of crashing:

| Exception | Thrown when |
|---|---|
| `InvalidAmountException` | A deposit or withdrawal amount is zero or negative |
| `InsufficientFundsException` | A Savings withdrawal would drop the balance below the $500 minimum |
| `OverdraftExceededException` | A Checking withdrawal would exceed the $1,000 overdraft limit |
| `InvalidAccountException` | The entered account number doesn't match any existing account |

## How to Run

```bash
mvn compile
mvn exec:java -Dexec.mainClass="org.example.Main"
```
Or run `Main.java` directly from IntelliJ.

## How to Run Tests

```bash
mvn test
```
Or right-click `src/test/java` (or an individual test class) in IntelliJ and choose **Run Tests**.

Test coverage includes:
- `AccountTest` -  deposit/withdraw behavior and boundary conditions for both account types
- `TransactionManagerTest` - transaction recording and deposit/withdrawal totals
- `ExceptionsTest`- correct messages on each custom exception

## Business Rules
- Savings accounts: 3.5% annual interest, $500 minimum balance enforced on withdrawal
- Checking accounts: $1,000 overdraft limit, $10 monthly fee (waived for Premium customers)
- Premium customers: $10,000 minimum balance, waived fees, priority service

## Git Workflow

Development followed a feature-branch workflow: each phase of work (refactor, exceptions, testing, bug fixes) was built on its own branch, then merged into `main`. `git cherry-pick` was also used to demonstrate selectively applying a single commit across branches.

Full details, commands, and commit history: see [`docs/git-workflow.md`](docs/git-workflow.md).