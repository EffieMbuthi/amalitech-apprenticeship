package org.example.model;

public class RegularCustomer extends Customer {
    public RegularCustomer(String name, int age, String contact, String address) {
        super(name, age, contact, address);
    }

    @Override
    public String getCustomerType() {
        return "Regular";
    }

    @Override
    public void displayCustomerDetails() {
        displayBaseCustomerDetails();
    }

    @Override
    public int showOverriding(int a) {
        int f= a * 12;
        return f;
    }

    public int showOverriding(int a, int b) {
        int f= a * 12;
        return f;
    }
}
