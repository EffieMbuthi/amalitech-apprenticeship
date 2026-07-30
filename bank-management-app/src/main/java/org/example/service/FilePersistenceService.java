package org.example.service;

import org.example.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class FilePersistenceService {

    private static final String CUSTOMERS_FILE = "data/customers.txt";
    private static final String ACCOUNTS_FILE = "data/accounts.txt";
    private static final String TRANSACTIONS_FILE = "data/transactions.txt";

    // SAVE
    public void saveCustomers(Collection<Customer> customers) {
        List<String> lines = customers.stream()
                .map(c -> c.getCustomerId() + "," + c.getCustomerType() + "," + c.getName() + ","
                        + c.getAge() + "," + c.getContact() + "," + c.getAddress())
                .collect(Collectors.toList());
        writeLines(CUSTOMERS_FILE, lines, "customers");
    }

    public void saveAccounts(Collection<Account> accounts) {
        List<String> lines = accounts.stream()
                .map(a -> a.getAccountNumber() + "," + a.getAccountType() + "," + a.getCustomer().getCustomerId() + ","
                        + a.getBalance() + "," + a.getStatus())
                .collect(Collectors.toList());
        writeLines(ACCOUNTS_FILE, lines, "accounts"); // this is where Files.write actually happens
    }

    public void saveTransactions(List<Transaction> transactions) {
        List<String> lines = transactions.stream()
                .map(t -> t.getTransactionId() + "," + t.getAccountNumber() + "," + t.getType() + ","
                        + t.getAmount() + "," + t.getBalanceAfter() + "," + t.getTimestamp())
                .collect(Collectors.toList());
        writeLines(TRANSACTIONS_FILE, lines, "transactions");
    }

    private void writeLines(String filePath, List<String> lines, String label) {
        try {
            Path path = Path.of(filePath);
            Files.createDirectories(path.getParent()); // ensure "data/" exists; creates "data/" folder if missing
            Files.write(path, lines); // creates the FILE if missing, writes lines into it
            System.out.println("✓ " + label + " saved to " + filePath);
        } catch (IOException e) {
            System.out.println("❌ Error saving " + label + ": " + e.getMessage());
        }
    }


    // Loading
    public Map<String, Customer> loadCustomers() {
        Map<String, Customer> customers = new HashMap<>();
        List<String> lines = readLines(CUSTOMERS_FILE, "customers");

        int maxId = 0;
        for (String line : lines) {
            String[] p = line.split(",");
            String customerId = p[0];
            String customerType = p[1];
            String name = p[2];
            int age = Integer.parseInt(p[3]);
            String contact = p[4];
            String address = p[5];

            Customer customer = customerType.equalsIgnoreCase("Premium")
                    ? new PremiumCustomer(customerId, name, age, contact, address)
                    : new RegularCustomer(customerId, name, age, contact, address);

            customers.put(customerId, customer);
            maxId = Math.max(maxId, extractNumber(customerId));
        }
        Customer.setCustomerCounter(maxId);
        return customers;
    }

    public Map<String, Account> loadAccounts(Map<String, Customer> customers) {
        Map<String, Account> accounts = new HashMap<>();
        List<String> lines = readLines(ACCOUNTS_FILE, "accounts");

        int maxId = 0;
        for (String line : lines) {
            String[] p = line.split(",");
            String accountNumber = p[0];
            String accountType = p[1];
            String customerId = p[2];
            double balance = Double.parseDouble(p[3]);
            String status = p[4];

            Customer customer = customers.get(customerId);
            if (customer == null) {
                System.out.println("⚠ Skipping account " + accountNumber + ": customer " + customerId + " not found.");
                continue;
            }

            Account account = accountType.equalsIgnoreCase("Savings")
                    ? new SavingsAccount(accountNumber, customer, balance, status)
                    : new CheckingAccount(accountNumber, customer, balance, status);

            accounts.put(accountNumber, account);
            maxId = Math.max(maxId, extractNumber(accountNumber));
        }
        Account.setAccountCounter(maxId);
        return accounts;
    }

    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        List<String> lines = readLines(TRANSACTIONS_FILE, "transactions");

        int maxId = 0;
        for (String line : lines) {
            String[] p = line.split(",");
            String transactionId = p[0];
            String accountNumber = p[1];
            String type = p[2];
            double amount = Double.parseDouble(p[3]);
            double balanceAfter = Double.parseDouble(p[4]);
            String timestamp = p[5];

            Transaction txn = new Transaction(transactionId, accountNumber, type, amount, balanceAfter, timestamp);
            transactions.add(txn);
            maxId = Math.max(maxId, extractNumber(transactionId));
        }
        Transaction.setTransactionCounter(maxId);
        return transactions;
    }

    private List<String> readLines(String filePath, String label) {
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {    // ← THIS check if files (.txt) exists: "does data/accounts.txt currently exist on disk?"
            System.out.println("No existing " + label + " file found....starting fresh.");
            return new ArrayList<>();   // ← empty list, no crash
        }
        try {
            return Files.readAllLines(path).stream()
                    .filter(line -> !line.isBlank())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("❌ Error loading " + label + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Strips a fixed 3-letter prefix (ACC/CUS/TXN) and parses the numeric remainder.
    private int extractNumber(String id) {
        return Integer.parseInt(id.substring(3));
    }
}