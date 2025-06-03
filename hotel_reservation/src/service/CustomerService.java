package service;

import model.Customer;

import java.util.Collection;
import java.util.Comparator;

public class CustomerService {
    public static void addCustomer(String email, String firstName, String lastName) {
        // Calls "Customer" constructor to obtain WHOLE Customer Information
        Customer customer = new Customer(firstName, lastName, email);

        // Adds "customer" to "customerCollection"
        ReservationService.customerCollection.add(customer);

        // Sorts "customerCollection" by First Names in Alphabetical Order & ONLY WORKS FOR "STRING WORDS", organizes String Numbers by FIRST DIGIT in Number
        ReservationService.customerCollection.sort((person1, person2) -> person1.getFirstName().compareTo(person2.getFirstName()));
    }

    public static Customer getCustomer(String customerEmail) {
        // ADD CORRECT CODE

        return test;
    }

    public static Collection<Customer> getAllCustomers() { return ReservationService.customerCollection; }
}
