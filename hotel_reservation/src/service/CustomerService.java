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

        // Separates "lastName" User Input & Main Menu on Console
        System.out.println("\n");
    }

    public static Customer getCustomer(String customerEmail) {
        boolean emailNotFound = false; // initial "emailNotFound" value
        
        // Checks if "customerCollection" is Empty or Not
        if (!ReservationService.customerCollection.isEmpty()) {
            // Searches for Customer Information with "customerEmail"
            for (Customer email : ReservationService.customerCollection) {
                // Checks if "customerEmail" matches "email" of CURRENT "customer" within "customerCollection"
                if (customerEmail.equals(email.getEmail())) {
                    return email;
                } else {
                    emailNotFound = true;
                }
            }
        } else { // if "customerCollection" is Empty
            emailNotFound = true;
        }

        // Indicates if Email Exists Or Not & Throws Exception if Email does NOT Exist
        if (emailNotFound) {
            System.out.println("Email does not exist.\n");
            throw new NullPointerException("Email does not exist");
        }
        return null;
    }

    public static Collection<Customer> getAllCustomers() { return ReservationService.customerCollection; }
}
