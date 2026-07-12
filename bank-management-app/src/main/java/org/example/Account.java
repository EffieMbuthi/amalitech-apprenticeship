package org.example;

abstract class Account {
    private String accountNumber;
    private Customer customer;
    private double balance;
    private String status;

    private static int accountCounter= 0;

    public Account(Customer customer, double balance) {
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


    public boolean deposit(double amount) {
        if (amount < 0) {
            return false;
        } else {
            balance = amount + balance;
            return true;
        }
    }

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
