package model;

public class Customer {
    String firstName, lastName, email;

    @Override
    public String toString() {
        return "Customer Name: " + firstName + " " + lastName + " and Customer Email: " + email;
    }
}
