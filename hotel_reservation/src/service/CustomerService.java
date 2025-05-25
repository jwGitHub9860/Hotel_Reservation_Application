package service;

import model.Customer;

import java.util.Collection;

public class CustomerService {
    public static void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email); // calls "Customer" constructor

        //System.out.printf(String.valueOf(customer)); // TESTING CODE
        System.out.printf("\n\nTest 2:\n"); // TESTING CODE
        /*Customer customer2 = new Customer("first", "second", "email");*/ // TESTING CODE
    }

    /*public Customer getCustomer(String customerEmail) { return; } // ADD CORRECT CODE

    public Collection<Customer> getAllCustomers() { return; }*/ // ADD CORRECT CODE
}
