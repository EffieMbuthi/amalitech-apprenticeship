package org.example.service;

import org.example.exceptions.InvalidAccountException;
import org.example.model.Account;
import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private Map<String, Account> accounts;

    public AccountManager() {
        accounts = new HashMap<>(50);
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(String accountNumber) {
        Account account= accounts.get(accountNumber);
        if (account==null){
            throw new InvalidAccountException("Account not found!");
        }
        return account;
    }

    public void viewAllAccounts() {
        for (Account a: accounts.values()){
            a.displayAccountDetails();
            System.out.println("---------------------------");
        }
        System.out.println("Total Accounts: " + getAccountCount());
        System.out.println("Total Bank Balance: " + getTotalBalance());
    }

    public double getTotalBalance() {
        double total=0;
        for (Account a: accounts.values()){
            total= total+ a.getBalance();
        }
        return total;
    }

    public int getAccountCount() {
        return accounts.size();
    }
}