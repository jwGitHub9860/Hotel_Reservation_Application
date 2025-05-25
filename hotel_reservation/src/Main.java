import api.HotelResource;
import model.Customer;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Main Menu
        boolean runApplication = true;
        try (Scanner scanner = new Scanner(System.in)) { // allows user input to be read
            while (runApplication) {
                try {
                    // Menu Items
                    System.out.println("Welcome to the Hotel Reservation Application");
                    System.out.println("-------------------------------------------------");
                    System.out.println("1. Find and reserve a room");
                    System.out.println("2. See my reservations");
                    System.out.println("3. Create an Account");
                    System.out.println("4. Admin");
                    System.out.println("5. Exit");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Please select a number for the menu option");

                    // Takes User Input
                    int userInput = Integer.parseInt(scanner.nextLine()); // reads User Input & takes ONLY INTEGER from full line of user input
                    if (userInput == 1) {
                        System.out.println("userInput 1 WORKS"); // TESTING CODE
                    } else if (userInput == 2) {
                        /*ReservationService.reservationCollection = HotelResource.getCustomersReservations(customerEmail); // calls "getCustomersReservations()" method

                        // Displays "reservationCollection" collection
                        for (Reservation reservation : ReservationService.reservationCollection){
                            System.out.println(reservation);
                        }*/
                    } else if (userInput == 3) {
                        // Takes User Input for Account Information
                        System.out.printf("Enter email: ");
                        String email = scanner.nextLine(); // Takes User Input for "email"
                        System.out.printf("\nEnter first name: ");
                        String firstName = scanner.nextLine(); // Takes User Input for "firstName"
                        System.out.printf("\nEnter last name: ");
                        String lastName = scanner.nextLine(); // Takes User Input for "lastName"

                        CustomerService.addCustomer(email, firstName, lastName); // calls "addCustomer()" method
                    } else if (userInput == 4) {
                        AdminMenu.Admin(); // calls "Admin()" method
                    } else if (userInput == 5) {
                        runApplication = false;
                        scanner.close(); // avoid memory leaks
                    } else {
                        System.out.println("Please enter an integer between 1 and 5");
                    }
                } catch (IllegalArgumentException e) { // if user does NOT ENTER EMAIL IN CORRECT FORMAT
                    System.out.println("\nEmail must be in \"name@domain.com\" format\n");
                } catch (Exception e) { // if user does NOT ENTER A NUMBER
                    System.out.println("\nPlease enter a number\n");
                }
            }
        } catch (Exception ex) {
            ex.getLocalizedMessage(); // prints any messages or exceptions to console
        }
    }
}