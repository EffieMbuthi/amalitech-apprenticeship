# Collections & Concurrency Architecture

This document explains the key data-structure, functional-programming,
persistence, and concurrency decisions made in Week 3, and the reasoning
behind each one.

## 1. AccountManager: HashMap<String, Account>

**Before:** `Account[] accounts` with a fixed size of 50 and a manual
`accountCount` field, requiring an O(n) linear scan in `findAccount()`.

**After:** `Map<String, Account> accounts`, keyed by `accountNumber`.

**Why:** Accounts are almost always accessed by a unique identifier — a
user types in an account number and expects that one specific account
back. A `HashMap` gives O(1) average-case lookup by key, versus the
array's O(n) scan. The key is the account's own `accountNumber` (e.g.
`"ACC001"`), since that's exactly what `findAccount(accountNumber)` is
searching by. `getAccountCount()` now simply returns `accounts.size()`
instead of maintaining a separate, error-prone manual counter — a single
source of truth instead of two values that could drift out of sync.

## 2. TransactionManager: List<Transaction>

**Before:** `Transaction[] transactions` with a fixed cap of 200 — a
correctness bug waiting to happen, since the 201st transaction would
crash the program with an `ArrayIndexOutOfBoundsException`.

**After:** `List<Transaction> transactions` (backed by `ArrayList`).

**Why:** Transactions are never looked up individually by ID — they're
processed as a group: filtered by account, summed, or listed in order.
That access pattern (iterate over everything, in order) fits a `List`
better than a key-based `Map`. `ArrayList` also removes the fixed-size
cap entirely; it grows dynamically, trading an occasional, amortized
O(1)-average resize cost for the correctness guarantee of never running
out of room — a worthwhile trade at this scale.

## 3. Functional Programming: Streams in calculateTotalDeposits/Withdrawals

The original array-based versions of these methods tangled three
distinct operations into one loop with a mutable accumulator:
**filter** (this account, this transaction type), **extract** (pull out
the `amount`), and **combine** (sum them). That shape maps directly onto
three Stream operations:

```java
public double calculateTotalDeposits(String accountNumber) {
    return transactions.stream()
            .filter(t -> t.getAccountNumber().equals(accountNumber) &&
                    t.getType().equalsIgnoreCase("DEPOSIT"))
            .mapToDouble(Transaction::getAmount)
            .sum();
}
```

- `.filter()` — keeps only transactions matching this account and type.
- `.mapToDouble(Transaction::getAmount)` — extracts just the numeric
  `amount` field from each surviving `Transaction`, converting the
  stream from `Stream<Transaction>` to a `DoubleStream` (the only stream
  type with a built-in `.sum()`).
- `.sum()` — combines everything into one total.

**Why `viewTransactionsByAccount` was deliberately NOT converted to
Streams:** that method needs newest-first ordering (a real user-facing
requirement) and performs a side effect (printing), not a computed
result. Streams have no natural "iterate backward" primitive, and
forcing this method into a Stream would obscure the ordering logic for
no real gain. The rule applied throughout this project: Streams are a
good fit when a loop is really a filter→transform→combine pipeline
producing one result; a plain loop is often clearer when order matters
or the "transformation" is just a side effect.

## 4. File Persistence: Object ↔ CSV line, and the ID-counter problem

`FilePersistenceService` owns all file reading/writing — a new, distinct
responsibility, kept separate from `AccountManager`/`TransactionManager`
(which manage in-memory collections) and separate from the model
classes (which represent data, not file I/O).

**Design decisions:**
- Each file (`customers.txt`, `accounts.txt`, `transactions.txt`) stores
  only fields that *vary per instance and can't be derived*. Hardcoded
  constants (e.g. `SavingsAccount`'s `interestRate = 0.035`) are never
  persisted — the constructor re-establishes them every time.
- `accounts.txt` references customers by `customerId` rather than
  duplicating customer details — the same "foreign key" idea a real
  database would use.
- Each persisted class (`Account`, `Customer`, `Transaction`) gained a
  **second constructor** — one for restoring already-known data (no
  auto-generation), alongside the original "create new" constructor
  which auto-generates IDs via a static counter.
- **The counter-catchup problem:** static ID counters (`accountCounter`,
  `customerCounter`, `transactionCounter`) reset to 0 on every program
  restart. Without correction, a freshly created account after a reload
  could collide with (and silently overwrite) an already-loaded ID.
  `FilePersistenceService` solves this by, after loading *all* records
  from a file, finding the highest numeric ID seen and calling a public
  static setter (e.g. `Account.setAccountCounter(maxId)`) once — never
  per-line, since the true maximum can only be known after every line
  has been read.

## 5. Concurrency: synchronized deposit/withdraw

Two threads calling `deposit()` on the same account at the same time can
interleave mid-operation (`balance = amount + balance` is really a
read-then-write, not one atomic step), causing one thread's update to be
silently lost — a classic race condition. Marking `deposit()` and each
subclass's `withdraw()` as `synchronized` ensures only one thread can
execute that method on a given account at a time, preventing the
interleaving that causes lost updates.

`ConcurrencyUtils.runConcurrentSimulation()` demonstrates this: three
`Runnable` tasks (deposit $500, deposit $300, withdraw $200), each
wrapped in its own `Thread` and started concurrently, then joined before
printing the final balance — proving the result is mathematically
correct regardless of thread interleaving order.