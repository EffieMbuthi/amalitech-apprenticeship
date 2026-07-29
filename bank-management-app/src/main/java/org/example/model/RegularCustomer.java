package org.example.model;

public class RegularCustomer extends Customer {
    public RegularCustomer(String name, int age, String contact, String address) {
        super(name, age, contact, address);
    }

    public RegularCustomer(String customerId, String name, int age, String contact, String address){
        super(customerId, name, age, contact, address);
    }

    @Override
    public String getCustomerType() {
        return "Regular";
    }

    @Override
    public void displayCustomerDetails() {
        displayBaseCustomerDetails();
    }

}
