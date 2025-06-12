package service;

import model.Customer;

import java.util.Collection;
import java.util.Comparator;
import java.util.Scanner;

public class CustomerService {
    // Allows User Input to be Read in ALL Methods WITHIN "CustomerService" class
    final static Scanner scanner = new Scanner(System.in);

    // Sorts "customerCollection" by USER'S CHOICE, organizes String Numbers by FIRST DIGIT in Number
    private static void sortCustomers() {
        System.out.println("How would you like to sort customers (first name, last name, or email): ");
        while (true) {
            try {
                // Takes User Input for "sortChoice" AS STRING
                String sortChoice = scanner.nextLine();

                // Checks if User chose "First Name", "Last Name", or "Email"
                switch (sortChoice) {
                    case "first name":
                        // Sorts "customerCollection" by First Names in Alphabetical Order & ONLY WORKS FOR "STRING WORDS", organizes String Numbers by FIRST DIGIT in Number
                        ReservationService.customerCollection.sort(Comparator.comparing(Customer::getFirstName));
                        break;
                    case "last name":
                        // Sorts "customerCollection" by Last Names in Alphabetical Order & ONLY WORKS FOR "STRING WORDS", organizes String Numbers by FIRST DIGIT in Number
                        ReservationService.customerCollection.sort(Comparator.comparing(Customer::getLastName));
                        break;
                    case "email":
                        // Sorts "customerCollection" by Emails in Alphabetical Order & ONLY WORKS FOR "STRING WORDS", organizes String Numbers by FIRST DIGIT in Number
                        ReservationService.customerCollection.sort(Comparator.comparing(Customer::getEmail));
                        break;
                    default:
                        throw new RuntimeException("Choice must be either First Name, Last Name, or Email");
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter First Name, Last Name, or Email: ");
            }
        }
    }

    public static void addCustomer(String email, String firstName, String lastName) {
        // Calls "getInstance()" method from "Customer.java" to obtain WHOLE Customer Information
        Customer customer = Customer.getInstance(firstName, lastName, email);

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

    public static Collection<Customer> getAllCustomers() {
        // Calls "sortCustomers()" method from "Customer.java" to sort "customerList"
        sortCustomers();

        return ReservationService.customerCollection;
    }
}
