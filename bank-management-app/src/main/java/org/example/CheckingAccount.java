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
        System.out.println("Monthly fee: " + monthlyFee);
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
        setBalance(getBalance() - monthlyFee);
    }
}