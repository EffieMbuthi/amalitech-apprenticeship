package org.example;

abstract class Account {
    private String accountNumber;
    private Customer customer;
    private double balance;
    private String status;

    public int Static accountCounter;




    //Register new bank accounts for customers
    public void createAccount(){}


    //display all accounts with their details
    public void viewAccounts(){}

    //deposit or withdraw money fom accounts
    public void processTransaction(){}


    //display transaction history for an account
    public void viewTransactions(){}


    //navigate through options
    public void simpleMenu(){}
}
