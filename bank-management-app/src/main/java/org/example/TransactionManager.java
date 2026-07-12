package org.example;

public class TransactionManager {
    private Transaction[] transactions;
    private int transactionCount;

    public TransactionManager() {
        transactions = new Transaction[200];
        transactionCount = 0;
    }

    public void addTransaction(Transaction transaction) {
        transactions[transactionCount] = transaction;
        transactionCount++;
    }

    // Iterates backward (from the most recently added transaction to the
    // earliest) so results display newest-first, per spec, without
    // needing a separate sort step — new transactions are always
    // appended at the end of the array.
    public void viewTransactionsByAccount(String accountNumber) {
        boolean found = false;
        for (int i = transactionCount - 1; i >= 0; i--) {
            if (transactions[i].getAccountNumber().equals(accountNumber)) {
                transactions[i].displayTransactionDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions recorded for this account.");
        }
    }

    public double calculateTotalDeposits(String accountNumber) {
        double total = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getAccountNumber().equals(accountNumber) && transactions[i].getType().equalsIgnoreCase("DEPOSIT")) {
                total += transactions[i].getAmount();
            }
        }
        return total;
    }

    public double calculateTotalWithdrawals(String accountNumber) {
        double total = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getAccountNumber().equals(accountNumber) && transactions[i].getType().equalsIgnoreCase("WITHDRAWAL")) {
                total += transactions[i].getAmount();
            }
        }
        return total;
    }

    public int getTransactionCount() {
        return transactionCount;
    }
}