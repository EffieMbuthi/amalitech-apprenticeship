package org.example.util;

import org.example.model.Account;

public class ConcurrencyUtils {

    public static void runConcurrentSimulation(Account account) {
        System.out.println("Running concurrent transaction simulation...\n");

        Runnable deposit500 = () -> {
            System.out.println(Thread.currentThread().getName() + ": Depositing $500 to " + account.getAccountNumber());
            account.deposit(500);
        };

        Runnable deposit300 = () -> {
            System.out.println(Thread.currentThread().getName() + ": Depositing $300 to " + account.getAccountNumber());
            account.deposit(300);
        };

        Runnable withdraw200 = () -> {
            System.out.println(Thread.currentThread().getName() + ": Withdrawing $200 from " + account.getAccountNumber());
            account.withdraw(200);
        };

        Thread t1 = new Thread(deposit500, "Thread-1");
        Thread t2 = new Thread(deposit300, "Thread-2");
        Thread t3 = new Thread(withdraw200, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Simulation interrupted: " + e.getMessage());
        }

        System.out.println("\n✓ Thread-safe operations completed successfully.");
        System.out.println("Final Balance for " + account.getAccountNumber() + ": $" + account.getBalance());
    }
}