package org.example;

import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.InvalidAccountException;
import org.example.exceptions.InvalidAmountException;
import org.example.exceptions.OverdraftExceededException;
import org.example.model.*;
import org.example.service.*;

import java.util.Scanner;

public class Main {

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
            }
        }
    }

    private static String readLine(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            } else {
                return input;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        TransactionManager transactionManager = new TransactionManager();

        RegularCustomer c1 = new RegularCustomer("Amari Mutiso", 40, "0700000001", "Nairobi");
        accountManager.addAccount(new SavingsAccount(c1, 5250.00));

        RegularCustomer c2 = new RegularCustomer("John Hefari", 35, "0700000002", "Nakuru");
        accountManager.addAccount(new CheckingAccount(c2, 3450.00));

        PremiumCustomer c3 = new PremiumCustomer("Michael Muhoro", 50, "0700000003", "Kiambu");
        accountManager.addAccount(new SavingsAccount(c3, 15750.00));

        RegularCustomer c4 = new RegularCustomer("Emma Njonjo", 29, "0700000004", "Mombasa");
        accountManager.addAccount(new CheckingAccount(c4, 890.00));

        PremiumCustomer c5 = new PremiumCustomer("Kimanzi Oti", 60, "0700000005", "Nairobi");
        accountManager.addAccount(new SavingsAccount(c5, 25300.00));

        boolean running = true;
        while (running) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║   BANK ACCOUNT MANAGEMENT - MAIN MENU      ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Process Transaction");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");

            int choice = readInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1: {
                    String name = readLine(scanner, "Enter customer name: ");
                    int age = readInt(scanner, "Enter customer age: ");
                    String contact = readLine(scanner, "Enter customer contact: ");
                    String address = readLine(scanner, "Enter customer address: ");

                    System.out.println("Customer type:\n1. Regular Customer\n2. Premium Customer");
                    int custType = readInt(scanner, "Select type (1-2): ");
                    Customer customer = (custType == 2)
                            ? new PremiumCustomer(name, age, contact, address)
                            : new RegularCustomer(name, age, contact, address);

                    System.out.println("Account type:\n1. Savings Account\n2. Checking Account");
                    int accType = readInt(scanner, "Select type (1-2): ");
                    double initialDeposit = readDouble(scanner, "Enter initial deposit amount: $");

                    if (initialDeposit <= 0) {
                        System.out.println("Initial deposit must be positive. Account not created.");
                        break;
                    }

                    Account newAccount = (accType == 2)
                            ? new CheckingAccount(customer, initialDeposit)
                            : new SavingsAccount(customer, initialDeposit);

                    accountManager.addAccount(newAccount);
                    System.out.println("\n✓ Account created successfully!");
                    newAccount.displayAccountDetails();
                    break;
                }

                case 2:
                    accountManager.viewAllAccounts();
                    break;

                case 3: {
                    String accNum = readLine(scanner, "Enter Account Number: ");

                    try{
                        Account account = accountManager.findAccount(accNum);
                        account.displayAccountDetails();

                        System.out.println("Transaction type:\n1. Deposit\n2. Withdrawal");
                        int txnType = readInt(scanner, "Select type (1-2): ");
                        String type = (txnType == 2) ? "WITHDRAWAL" : "DEPOSIT";
                        double amount = readDouble(scanner, "Enter amount: $");

                        if (amount <= 0) {
                            System.out.println("Amount must be positive.");
                            break;
                        }

                        double previousBalance = account.getBalance();
                        System.out.println("\nTRANSACTION CONFIRMATION");
                        System.out.println("Account: " + accNum);
                        System.out.println("Type: " + type);
                        System.out.println("Amount: " + amount);
                        System.out.println("Previous Balance: " + previousBalance);
                        String confirm = readLine(scanner, "Confirm transaction? (Y/N): ");
                        if (!confirm.equalsIgnoreCase("Y")) {
                            System.out.println("Transaction cancelled.");
                            break;
                        }
                        account.processTransaction(amount, type);

                        Transaction txn = new Transaction(accNum, type, amount, account.getBalance());
                        transactionManager.addTransaction(txn);
                        System.out.println("\nTRANSACTION CONFIRMATION");
                        System.out.println("Transaction ID: " + txn.getTransactionId());
                        System.out.println("Previous Balance: " + previousBalance);
                        System.out.println("New Balance: " + account.getBalance());
                        System.out.println("✓ Transaction completed successfully!");
                    }
                    catch (InvalidAccountException e){
                        System.out.println("❌ ERROR: " + e.getMessage());
                    }
                    catch (InvalidAmountException e){
                        System.out.println("❌ ERROR: " + e.getMessage());
                    }
                    catch (InsufficientFundsException e){
                        System.out.println("❌ TRANSACTION FAILED! " + e.getMessage());
                    }
                    catch (OverdraftExceededException e){
                        System.out.println("❌ TRANSACTION FAILED! " + e.getMessage());
                    }
                    break;
                }

                case 4: {
                    String histAccNum = readLine(scanner, "Enter Account Number: ");

                    try {
                        accountManager.findAccount(histAccNum);

                        transactionManager.viewTransactionsByAccount(histAccNum);
                        double deposits = transactionManager.calculateTotalDeposits(histAccNum);
                        double withdrawals = transactionManager.calculateTotalWithdrawals(histAccNum);
                        System.out.println("Total Deposits: " + deposits);
                        System.out.println("Total Withdrawals: " + withdrawals);
                        System.out.println("Net Change: " + (deposits - withdrawals));
                    }
                    catch(InvalidAccountException e){
                        System.out.println("❌ ERROR: " + e.getMessage());
                    }
                    break;
                }

                case 5:
                    running = false;
                    System.out.println("Thank you for Banking with us!\nGoodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-5.");
            }
        }
        scanner.close();
    }
}