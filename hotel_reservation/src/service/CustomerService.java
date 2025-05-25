package service;

import model.Customer;

import java.util.Collection;

public class CustomerService {
    public static void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email); // calls "Customer" constructor

        ReservationService.customerCollection.add(customer); // add "customer" to "customerCollection"
    }

    //public Customer getCustomer(String customerEmail) { return; } // ADD CORRECT CODE

    public static Collection<Customer> getAllCustomers() { return ReservationService.customerCollection; }
}
