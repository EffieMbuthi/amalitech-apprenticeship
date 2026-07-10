package org.example;

public class SavingsAccount extends Account{
    private double interestRate;
    private double minimumBalance;

    public SavingsAccount(Customer customer, double balance) {
        super(customer, balance);
        this.interestRate=0.035;
        this.minimumBalance=500.0;
    }

    @Override
    public void displayAccountDetails() {
        displayBaseAccountDetails();
        System.out.println("Interest rate: " + interestRate);
        System.out.println("The minimum balance required: " + minimumBalance);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    @Override
    public boolean withdraw(double amount) {
        if ((getBalance()-amount) >= minimumBalance){
            double newBalance= getBalance()- amount;
            setBalance(newBalance);
            return true;
        }
        else{
            return false;
        }
    }

    protected double calculateInterest(){
        double interestAmount= getBalance() * interestRate;
        return interestAmount;
    }
}
