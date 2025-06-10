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
