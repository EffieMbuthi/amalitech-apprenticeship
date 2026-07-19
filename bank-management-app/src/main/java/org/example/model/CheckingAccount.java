package org.example.model;

import org.example.exceptions.InvalidAmountException;
import org.example.exceptions.OverdraftExceededException;

public class CheckingAccount extends Account {
    private double overdraftLimit;
    private double monthlyFee;

    public CheckingAccount(Customer customer, double balance) {
        super(customer, balance);
        this.overdraftLimit = 1000.0;
        this.monthlyFee = 10.0;
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }

    @Override
    public void displayAccountDetails() {
        displayBaseAccountDetails();
        System.out.println("Overdraft limit: " + overdraftLimit);
        if (getCustomer() instanceof PremiumCustomer) {
            System.out.println("Monthly Fee: $0.00 (WAIVED - Premium Customer)");
        } else {
            System.out.println("Monthly Fee: " + monthlyFee);
        }
    }

    // Checking accounts allow the balance to go negative, down to
    // -overdraftLimit. E.g. with a $1,000 limit, balance can reach
    // -1000 but not lower. Using >= -overdraftLimit as the boundary.
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("The amount must be greater than 0");
        }
        if ((getBalance() - amount) < -overdraftLimit) {
            throw new OverdraftExceededException("Overdraft limit exceeded. Current balance: $" + getBalance());
        }
        setBalance(getBalance()-amount);
        }

    // Premium customers have monthly fees waived per business rules.
    // instanceof checks the real runtime type of the linked Customer
    // object to decide whether the fee applies.
    public void applyMonthlyFee() {
        if (getCustomer() instanceof PremiumCustomer) {
            return;
        }
        setBalance(getBalance() - monthlyFee);
    }
}