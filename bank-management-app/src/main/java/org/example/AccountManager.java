package org.example;

public class AccountManager {
    private Account[] accounts;
    private int accountCount;

    public AccountManager() {
        accounts = new Account[50];
        accountCount = 0;
    }

    public void addAccount(Account account) {
        accounts[accountCount] = account;
        accountCount++;
    }

    // Linear search through the accounts array, O(n) time complexity.
    // Acceptable here since the array is capped at 50 accounts (fixed,
    // small size per spec), so performance impact is negligible.
    public Account findAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }

    public void viewAllAccounts() {
        for (int i = 0; i < accountCount; i++) {
            accounts[i].displayAccountDetails();
            System.out.println("---------------------------");
        }
        System.out.println("Total Accounts: " + accountCount);
        System.out.println("Total Bank Balance: " + getTotalBalance());
    }

    public double getTotalBalance() {
        double total = 0;
        for (int i = 0; i < accountCount; i++) {
            total += accounts[i].getBalance();
        }
        return total;
    }

    public int getAccountCount() {
        return accountCount;
    }
}