package org.example;

abstract class Customer {
    private String customerId;
    private String name;
    private int age;
    private String contact;
    private String address;

    private static int customerCounter= 0;


    public Customer(String name, int age, String contact, String address){
        customerCounter++;
        this.customerId= "CUS" + String.format("%03d",customerCounter);
        this.name=name;
        this.age= age;
        this.contact=contact;
        this.address=address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public abstract String getCustomerType();

    public abstract void displayCustomerDetails();
}
