package org.example.service;

import org.example.model.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactions;

    public TransactionManager() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // Iterates backward (from the most recently added transaction to the earliest)
    // so results display *newest-first*, per spec, without
    // needing a separate sort step — new transactions are always
    // appended at the end of the arraylist.
    public void viewTransactionsByAccount(String accountNumber) {
        boolean found = false;
        for (int i = getTransactionCount() - 1; i >= 0; i--) {
            Transaction transaction= transactions.get(i);
            if (transaction.getAccountNumber().equals(accountNumber)) {
                transaction.displayTransactionDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions recorded for this account.");
        }
    }

    public double calculateTotalDeposits(String accountNumber) {
        double result = transactions.stream()
                .filter(t-> t.getAccountNumber().equals(accountNumber) &&
                        t.getType().equalsIgnoreCase("DEPOSIT"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        return result;
    }

    public double calculateTotalWithdrawals(String accountNumber) {
        double result= transactions.stream()
                .filter(t->t.getAccountNumber().equals(accountNumber) &&
                        t.getType().equalsIgnoreCase("WITHDRAWAL"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        return result;
    }

    public int getTransactionCount() {
        return transactions.size();
    }
}