package org.example.model;

public class PremiumCustomer extends Customer {
    private double minimumBalance;

    public PremiumCustomer(String name, int age, String contact, String address) {
        super(name, age, contact, address);
        this.minimumBalance= 10000.0;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    @Override
    public String getCustomerType() {
        return "Premium";
    }

    @Override
    public void displayCustomerDetails() {
        displayBaseCustomerDetails();
        System.out.println("Minimum Balance: " + getMinimumBalance());
        System.out.println("Priority Service: Yes");
    }

    public boolean hasWaivedFees(){
        return true;
    }
}
