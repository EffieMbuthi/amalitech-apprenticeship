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

    // Checking accounts allow the balance to go negative, down to
    // -overdraftLimit. E.g. with a $1,000 limit, balance can reach
    // -1000 but not lower. Using >= -overdraftLimit as the boundary.
    @Override
    public boolean withdraw(double amount) {
        if ((getBalance() - amount) >= -overdraftLimit) {
            setBalance(getBalance() - amount);
            return true;
        } else {
            return false;
        }
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