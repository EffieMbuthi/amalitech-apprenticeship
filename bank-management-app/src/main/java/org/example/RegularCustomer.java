package org.example;

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
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Type: " + getCustomerType());
        System.out.println("Address: " + getAddress());
        System.out.println("Contact: " + getContact());
    }
}
