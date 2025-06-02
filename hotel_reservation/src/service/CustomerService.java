package service;

import model.Customer;

import java.util.Collection;
import java.util.Comparator;

public class CustomerService {
    public static void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email); // calls "Customer" constructor

        ReservationService.customerCollection.add(customer); // add "customer" to "customerCollection"

        // Sorts "roomList" by Room Numbers
        ReservationService.customerCollection.sort(Comparator.comparing(person -> {
            String string = person.getRoomNumber(); // obtains "roomNumber"
            String[] variables = string.split("\\."); // "\\." - match the character
            int firstVariable = Integer.parseInt(variables[0]); // obtains 1st Room Number
            int secondVariable = variables.length > 1 ? Integer.parseInt(variables[1]) : 0; // finds which Room Number is greater
            return firstVariable * 1000 + secondVariable; // returns "roomNumber1" and "roomNumber2" in Ascending Order
        }));
    }

    public static Customer getCustomer(String customerEmail) {
        // ADD CORRECT CODE
        Customer test = new Customer("firstName", "lastName", "email"); // calls "Customer" constructor
        return test;
    }

    public static Collection<Customer> getAllCustomers() { return ReservationService.customerCollection; }
}
