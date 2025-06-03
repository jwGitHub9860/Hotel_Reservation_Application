package service;

import model.Customer;

import java.util.Collection;
import java.util.Comparator;

public class CustomerService {
    public static void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email); // calls "Customer" constructor
        System.out.println("BEFORE:\n"); // TESTING CODE
        for (Customer customer1 : ReservationService.customerCollection) { // TESTING CODE
            System.out.println(customer1); // TESTING CODE
        } // TESTING CODE
        ReservationService.customerCollection.add(customer); // add "customer" to "customerCollection"
        System.out.println("AFTER:\n"); // TESTING CODE
        for (Customer customer1 : ReservationService.customerCollection) { // TESTING CODE
            System.out.println(customer1); // TESTING CODE
        } // TESTING CODE
        // Sorts "customerCollection" by First Names in Alphabetical Order
        ReservationService.customerCollection.sort(Comparator.comparing(person -> Customer.getFirstName())); // finds which Room Number is greater
        
        /*ReservationService.customerCollection.sort(Comparator.comparing(person -> {
            String string = person.getFirstName(); // obtains "roomNumber"
            String[] people = string.split("\\."); // "\\." - match the character
            int firstPerson = Integer.parseInt(people[0]); // obtains 1st Room Number
            int secondPerson = people.length > 1 ? Integer.parseInt(people[1]) : 0; // finds which Room Number is greater
            return firstPerson * 1000 + secondPerson; // returns "roomNumber1" and "roomNumber2" in Ascending Order
        }));*/
    }

    public static Customer getCustomer(String customerEmail) {
        // ADD CORRECT CODE
        Customer test = new Customer("firstName", "lastName", "email"); // calls "Customer" constructor
        return test;
    }

    public static Collection<Customer> getAllCustomers() { return ReservationService.customerCollection; }
}
