package org.example;

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

    @Override
    public boolean withdraw(double amount) {
        if ((getBalance() - amount) >= -overdraftLimit) {
            setBalance(getBalance() - amount);
            return true;
        } else {
            return false;
        }
    }

    public void applyMonthlyFee() {
        if (getCustomer() instanceof PremiumCustomer) {
            return;
        }
        setBalance(getBalance() - monthlyFee);
    }
}