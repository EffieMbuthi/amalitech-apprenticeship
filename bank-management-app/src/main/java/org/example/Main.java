package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        TransactionManager transactionManager = new TransactionManager();


        RegularCustomer c1 = new RegularCustomer("Kimmy Otieno", 40, "0700000001", "Mombasa");
        SavingsAccount a1 = new SavingsAccount(c1, 52500.00);
        accountManager.addAccount(a1);

        RegularCustomer c2 = new RegularCustomer("Sarah Muthoni", 35, "0700000002", "Kiambu");
        CheckingAccount a2 = new CheckingAccount(c2, 34500.00);
        accountManager.addAccount(a2);

        PremiumCustomer c3 = new PremiumCustomer("Jamal Kamau", 50, "0700000003", "Nairobi");
        SavingsAccount a3 = new SavingsAccount(c3, 157500.00);
        accountManager.addAccount(a3);

        RegularCustomer c4 = new RegularCustomer("Amara Pendo", 29, "0700000004", "Nakuru");
        CheckingAccount a4 = new CheckingAccount(c4, 8900.00);
        accountManager.addAccount(a4);

        PremiumCustomer c5 = new PremiumCustomer("David Wainaina", 60, "0700000005", "Nyahururu");
        SavingsAccount a5 = new SavingsAccount(c5, 253000.00);
        accountManager.addAccount(a5);

        boolean running = true;
        while (running) {
            System.out.println("\n1. Create Account\n2. View Accounts\n3. Process Transaction\n4. View Transaction History\n5. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter customer age: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter customer contact: ");
                    String contact = scanner.nextLine();
                    System.out.print("Enter customer address: ");
                    String address = scanner.nextLine();

                    System.out.print("Customer type (1-Regular, 2-Premium): ");
                    int custType = Integer.parseInt(scanner.nextLine());
                    Customer customer = (custType == 2)
                            ? new PremiumCustomer(name, age, contact, address)
                            : new RegularCustomer(name, age, contact, address);

                    System.out.print("Account type (1-Savings, 2-Checking): ");
                    int accType = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter initial deposit amount: ");
                    double initialDeposit = Double.parseDouble(scanner.nextLine());

                    Account newAccount = (accType == 2)
                            ? new CheckingAccount(customer, initialDeposit)
                            : new SavingsAccount(customer, initialDeposit);

                    accountManager.addAccount(newAccount);
                    System.out.println("Account created successfully!");
                    newAccount.displayAccountDetails();
                    break;

                case 2:
                    accountManager.viewAllAccounts();
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine();
                    Account account = accountManager.findAccount(accNum);
                    if (account == null) {
                        System.out.println("Account not found.");
                        break;
                    }
                    account.displayAccountDetails();
                    System.out.print("Transaction type (1-Deposit, 2-Withdrawal): ");
                    int txnType = Integer.parseInt(scanner.nextLine());
                    String type = (txnType == 2) ? "WITHDRAWAL" : "DEPOSIT";
                    System.out.print("Enter amount: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    boolean success = account.processTransaction(amount, type);
                    if (success) {
                        Transaction txn = new Transaction(accNum, type, amount, account.getBalance());
                        transactionManager.addTransaction(txn);
                        System.out.println("Transaction completed successfully!");
                        txn.displayTransactionDetails();
                    } else {
                        System.out.println("Transaction failed. Check balance/limits.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    String histAccNum = scanner.nextLine();
                    transactionManager.viewTransactionsByAccount(histAccNum);
                    System.out.println("Total Deposits: " + transactionManager.calculateTotalDeposits(histAccNum));
                    System.out.println("Total Withdrawals: " + transactionManager.calculateTotalWithdrawals(histAccNum));
                    break;

                case 5:
                    running = false;
                    System.out.println("Thank you for using Banking with us! Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}