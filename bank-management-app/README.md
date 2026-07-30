# Bank Account Management System

A console-based Java application for managing bank accounts, customers, and transactions.

## Features
- Create Savings or Checking accounts for Regular or Premium customers
- View all accounts with balances and details
- Process deposits and withdrawals with business rule validation
- View transaction history per account, with total deposits/withdrawals/net change
- Custom exceptions for invalid input and failed transactions, with clear console error messages
- Persistent storage: accounts, customers, and transactions are saved to file on exit and reloaded automatically on startup
- Regex-based validation for phone numbers, emails, and account number format
- Thread-safe deposits/withdrawals, with a concurrent transaction simulation to demonstrate it
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
- Composition: AccountManager holds a Map of Account objects, TransactionManager holds a List of Transaction objects
- Static fields: used for auto-generating unique IDs (CUS, ACC, TXN)
- Constructor overloading: each persisted model class (Account, Customer, Transaction) has a "create new" constructor and a "restore from file" constructor

## Collections & Functional Programming

- `AccountManager` uses `HashMap<String, Account>`, keyed by account number, for O(1) average-case lookup — replacing the original O(n) array scan.
- `TransactionManager` uses `List<Transaction>` (`ArrayList`), replacing a fixed-size 200-transaction array that would previously crash once exceeded.
- `calculateTotalDeposits`/`calculateTotalWithdrawals` use Streams (`filter` → `mapToDouble` → `sum`) instead of manual loops with accumulator variables.

Full reasoning behind each data-structure choice: see [`docs/collections-architecture.md`](docs/collections-architecture.md).

## File Persistence

On exit, all customers, accounts, and transactions are saved as plain-text CSV files under `data/`. On startup, the app reads these files (via `java.nio.file.Files`/`Path`) and rebuilds the full in-memory state — if no saved data exists yet (first run), the app seeds a set of default sample accounts instead.

`accounts.txt` references customers by ID rather than duplicating customer data — the same idea as a foreign key in a database. Auto-incrementing ID counters (account/customer/transaction numbers) are caught up to the correct value after loading, so newly created records after a reload never collide with restored ones.

## Regex Validation

Centralized in `ValidationUtils`, using `java.util.regex.Pattern`:
- Account number: `ACC\d{3}`
- Email: `^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$`
- Phone number: 10 digits, starting with 0

Invalid input during account creation is rejected with a clear error message, and the user is re-prompted until valid input is given.

## Concurrency

`deposit()` and each subclass's `withdraw()` are marked `synchronized`, preventing race conditions where two threads modifying the same account's balance at once could cause a lost update (one thread's change silently overwritten by another).

`ConcurrencyUtils.runConcurrentSimulation()` demonstrates this: three `Runnable` tasks (two deposits, one withdrawal) run as separate `Thread`s against the same account simultaneously, then join before printing the final balance — proving the result is correct regardless of thread interleaving. Available from the main menu as "Run Concurrent Simulation."

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

Development followed a feature-branch workflow: each phase of work (refactor, exceptions, testing, bug fixes, collections migration, file persistence, regex validation, concurrency) was built on its own branch, then merged into `main`. `git cherry-pick` was also used to demonstrate selectively applying a single commit across branches.

Full details, commands, and commit history: see [`docs/git-workflow.md`](docs/git-workflow.md).