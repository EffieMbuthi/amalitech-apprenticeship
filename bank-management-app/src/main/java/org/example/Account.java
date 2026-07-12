package org.example;

abstract class Account implements Transactable {
    private String accountNumber;
    private Customer customer;
    private double balance;
    private String status;

    private static int accountCounter= 0;

    public Account(Customer customer, double balance) {
        // Auto-generate a unique, sequential account number using the shared
        // static counter. Not passed in as a parameter since the system,
        // not the user, controls this value.
        accountCounter++;
        this.accountNumber= "ACC" + String.format("%03d",accountCounter);
        this.customer = customer;
        this.balance = balance;
        this.status= "Active";
    }

    public abstract void displayAccountDetails();

    public abstract String getAccountType();

    protected void displayBaseAccountDetails(){
        System.out.println("Account no: " + accountNumber);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Account type: " + getAccountType());
        System.out.println("Your balance is: " + balance);
        System.out.println("Account status: " + status);
    }

    // Single entry point required by the Transactable interface.
    // Routes to deposit() or withdraw() based on the type string, so
    // callers (Main) don't need to know which method to call directly.
    // Relies on polymorphism: withdraw() automatically runs whichever
    // subclass's version matches the real object at runtime.
    @Override
    public boolean processTransaction(double amount, String type) {
        if (type.equalsIgnoreCase("DEPOSIT")) {
            return deposit(amount);
        } else if (type.equalsIgnoreCase("WITHDRAWAL")) {
            return withdraw(amount);
        } else {
            return false;
        }
    }


    public boolean deposit(double amount) {
        if (amount < 0) {
            return false;
        } else {
            balance = amount + balance;
            return true;
        }
    }

    // Declared abstract because withdrawal rules differ per account type:
    // SavingsAccount enforces a minimum balance, CheckingAccount allows
    // overdraft up to a limit. Each subclass provides its own implementation.
    public abstract boolean withdraw(double amount);


    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
