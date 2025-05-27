import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Allows user to input "Date" as input
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");

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
                        // Takes User Input for Check-In and Check-Out Dates
                        System.out.println("Enter Check-In Date (ex. 02/01/2020): ");
                        String checkInDate = scanner.nextLine(); // takes User Input for "checkInDate" AS STRING
                        System.out.println("Enter Check-Out Date (ex. 02/01/2020): ");
                        String checkOutDate = scanner.nextLine(); // takes User Input for "checkOutDate" AS STRING

                        HotelResource.findARoom(checkInDate, checkOutDate); // display all rooms created

                        // Checks if user inputted "y" or "n"
                        System.out.println("Would you like to book a room (y/n): ");
                        String bookRoomAnswer = null;
                        while (true) {
                            try {
                                bookRoomAnswer = scanner.next();
                                if (bookRoomAnswer.equals("y") || bookRoomAnswer.equals("n")) { // Valid answer
                                    break;
                                } else { // INVALID answer
                                    throw new IllegalArgumentException("Answer must be \"y\" or \"n\"");
                                }
                            } catch (Exception e) {
                                System.out.println("Please enter y (yes) or n (n): ");
                            }
                        }

                        // Checks if user inputted "y" or "n"
                        System.out.println("Do you have an account with us (y/n): ");
                        String accountAnswer = null;
                        while (true) {
                            try {
                                accountAnswer = scanner.next();
                                if (accountAnswer.equals("y") || accountAnswer.equals("n")) { // Valid answer
                                    break;
                                } else { // INVALID answer
                                    throw new IllegalArgumentException("Answer must be \"y\" or \"n\"");
                                }
                            } catch (Exception e) {
                                System.out.println("Please enter y (yes) or n (n): ");
                            }
                        }

                        // Creates Customer Account if User does NOT have account
                        if (accountAnswer.equals("n")) {
                            // Takes User Input for Account Information
                            System.out.printf("Enter email (format: name@domain.com): ");
                            String email = scanner.nextLine(); // takes User Input for "email"
                            System.out.printf("\nEnter first name: ");
                            String firstName = scanner.nextLine(); // takes User Input for "firstName"
                            System.out.printf("\nEnter last name: ");
                            String lastName = scanner.nextLine(); // takes User Input for "lastName"

                            HotelResource.createACustomer(email, firstName, lastName); // calls "createACustomer()" method
                        }

                        // Books Hotel Room
                        if (bookRoomAnswer.equals("y")) {
                            // Takes User Input for Account Information
                            System.out.printf("Enter email (format: name@domain.com): ");
                            String email = scanner.nextLine(); // takes User Input for "email"
                            System.out.printf("\nChoose room number to reserve: ");
                            int room = scanner.nextInt(); // takes User Input for "room"

                            HotelResource.bookARoom(email, room, checkInDate, checkOutDate); // calls "bookARoom()" method
                        }
                    } else if (userInput == 2) {
                        /*ReservationService.reservationCollection = HotelResource.getCustomersReservations(customerEmail); // calls "getCustomersReservations()" method

                        // Displays "reservationCollection" collection
                        for (Reservation reservation : ReservationService.reservationCollection){
                            System.out.println(reservation);
                        }*/
                    } else if (userInput == 3) {
                        // Takes User Input for Account Information
                        System.out.printf("Enter email (format: name@domain.com): ");
                        String email = scanner.nextLine(); // takes User Input for "email"
                        System.out.printf("\nEnter first name: ");
                        String firstName = scanner.nextLine(); // takes User Input for "firstName"
                        System.out.printf("\nEnter last name: ");
                        String lastName = scanner.nextLine(); // takes User Input for "lastName"

                        HotelResource.createACustomer(email, firstName, lastName); // calls "createACustomer()" method
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