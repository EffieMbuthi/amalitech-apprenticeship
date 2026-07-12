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


    // Withdrawal is only allowed if the balance AFTER subtraction is still
    // >= the $500 minimum. Using >= (not >) so a withdrawal that leaves
    // exactly $500 is accepted, not rejected.
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

    // Calculates one year's interest on the current balance without
    // modifying the balance directly. Applying the interest (if needed)
    // is left to whatever calls this method, keeping the calculation
    // itself a pure, side-effect-free operation.
    protected double calculateInterest(){
        double interestAmount= getBalance() * interestRate;
        return interestAmount;
    }
}
