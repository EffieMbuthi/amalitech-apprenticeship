# Git Workflow: Bank Account Management System

This document records the actual branching, commit, merge, and cherry-pick
workflow used while building the exception-handling, testing, and
refactoring work for this project (Week 2).

Repository: `amalitech-apprenticeship` (monorepo -this project lives in
the `bank-management-app/` folder)

## Branching Strategy

Each phase of work was developed on its own feature branch, then merged
back into `main` once complete. This kept unfinished or risky work
isolated from the stable branch, and gives a clear commit trail per
feature.

Branches used:

| Branch | Purpose |
|---|---|
| `feature/refactor` | Reorganize the codebase into `model`, `service`, and `exceptions` packages |
| `feature/exceptions` | Introduce custom exceptions and convert `deposit()`/`withdraw()` to throw instead of returning `boolean` |
| `feature/testing` | Add JUnit 5 setup and unit tests (`AccountTest`, `TransactionManagerTest`, `ExceptionsTest`) |
| `feature/invalid-account-exception` | Add `InvalidAccountException` and update `AccountManager.findAccount()` and `Main` to use it |
| `feature/input-validation` | Add blank-input validation to `readLine()` |
| `feature/fix-missing-catch` | Fix a missing `catch (InvalidAccountException e)` block in `Main`'s transaction flow, which had been causing the program to crash instead of showing a graceful error |
| `demo/cherry-pick-hotfix` | Demonstration branch used to show `git cherry-pick` selectively applying a single commit (see below) |

## Commit History (this project's relevant commits)

```
e9e5e6c Fix missing catch block for InvalidAccountException in case 3
10a56db Add blank-input validation to readLine
d9f9988 Add InvalidAccountException and update findAccount, Main to use it
bff9378 Add JUnit tests for custom exception classes   (cherry-picked commit)
3a5c5e8 Add JUnit tests for custom exception classes   (original commit)
e758db8 Add JUnit tests for TransactionManager deposit and withdrawal totals
9cfdd28 Add JUnit 5 dependency, Surefire plugin, and Account tests
798cf70 Refactor project into packages: model, service.
9685ae5 Add comments on Account and Transaction Manager classes
2b3b6ce Fix Premium fee waiver, add input validation, add README
8c2117b Add Main with menu loop, wiring everything together
3030dfe Add TransactionManager
3559059 Add Transaction class
1be4488 Add Transactable interface and implement in Account
d5fabd0 Add checking Account sub-class
```

(Note: this is a shared monorepo, so `git log` also contains unrelated
`habit-tracker:` commits from a different project folder in the same
repository — these are not part of this project's history.)

## Standard Workflow Used Per Feature

Each feature branch followed the same repeatable sequence:

```bash
# 1. Branch off main
git checkout main
git checkout -b feature/<name>

# 2. Do the work, commit as logical chunks are completed
git add <files>
git commit -m "<clear, specific message>"

# 3. Merge back into main
git checkout main
git merge feature/<name>

# 4. Push to GitHub
git push origin main
```

This was repeated for `feature/refactor`, `feature/exceptions`,
`feature/testing`, `feature/invalid-account-exception`,
`feature/input-validation`, and `feature/fix-missing-catch`.

## Cherry-Pick Demonstration

To demonstrate `git cherry-pick` selectively applying a single commit
(rather than a full merge bringing over everything), the following was
done:

```bash
# Create a new branch starting from BEFORE any test commits existed
git checkout -b demo/cherry-pick-hotfix 798cf70

# Cherry-pick only the "ExceptionsTest" commit onto this branch
git cherry-pick 3a5c5e8
# -> created new commit bff9378 with the same content

# Push the branch as evidence
git push origin demo/cherry-pick-hotfix
```

**Result:** on `demo/cherry-pick-hotfix`, `ExceptionsTest.java` was
present, but `AccountTest.java` and `TransactionManagerTest.java`
(from other, non-cherry-picked commits on `feature/testing`) were not —
confirming the cherry-pick pulled in only the one targeted commit's
changes, not the full branch history.

The demo branch was then cleaned up locally (already preserved on
GitHub via the push above):

```bash
git checkout main
git branch -D demo/cherry-pick-hotfix
```

## Verification

After all merges, the application was manually run through its console
menu to confirm each custom exception is triggered and handled correctly
end-to-end (not just at the unit-test level):

- `InvalidAccountException` - entering a non-existent account number
- `InvalidAmountException` - entering a zero/negative transaction amount
- `InsufficientFundsException` - withdrawing a Savings account below the
  $500 minimum balance
- `OverdraftExceededException` - withdrawing a Checking account beyond
  the $1,000 overdraft limit

This process caught a real bug (the missing `catch` block fixed in
`feature/fix-missing-catch`) before final submission.