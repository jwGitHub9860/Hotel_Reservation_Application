import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class Main {
    // Allows User Input to be Read in ALL Methods WITHIN "Main" class
    static Scanner scanner = new Scanner(System.in);

    private static Date inputCheckInAndCheckOutDates() {
        // Allows user to input "Date" as input
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        // Sets "lenient" to "false" to apply strict Date Parsing
        simpleDateFormat.setLenient(false);

        // Checks if user inputted "checkInDateInput" in "MM/dd/yyyy" format
        while (true) {
            try {
                String checkInDateInput = scanner.nextLine(); // takes User Input for "checkInDateInput" AS STRING
                return simpleDateFormat.parse(checkInDateInput); // checks if "checkInDateInput" is in "MM/dd/yyyy" format
            } catch (Exception e) {
                System.out.println("Please enter date in \"MM/dd/yyyy\" format: ");
            }
        }
    }

    private static String inputYOrN() {
        // Checks if user inputted "y" or "n"
        while (true) {
            try {
                String answer = scanner.next();
                if (answer.equals("y") || answer.equals("n")) { // Valid answer
                    return answer;
                } else { // INVALID answer
                    throw new IllegalArgumentException("Answer must be \"y\" or \"n\"");
                }
            } catch (Exception e) {
                System.out.println("Please enter y (yes) or n (n): ");
            }
        }
    }

    public static void main(String[] args) {
        // Main Menu
        boolean runApplication = true;
        try {
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
                        Date checkInDate = inputCheckInAndCheckOutDates(); // initial "checkInDate" value & allows "findARoom()" method access to "checkInDate"
                        System.out.println("Enter Check-Out Date (ex. 02/01/2020): ");
                        Date checkOutDate = inputCheckInAndCheckOutDates(); // initial "checkOutDate" value & allows "findARoom()" method access to "checkOutDate"

                        Collection<IRoom> roomSearch = HotelResource.findARoom(checkInDate, checkOutDate); // calls "findARoom()" method

                        // Display all rooms created
                        for (IRoom room : roomSearch) {
                            System.out.println(room);
                        }

                        System.out.println("Would you like to book a room (y/n): ");
                        String bookRoomAnswer = null;


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
                            System.out.println("Enter email (format: name@domain.com): ");
                            String email = Customer.inputEmail(); // calls "inputEmail()" method to take User Input for "email"
                            System.out.println("\nEnter first name: ");
                            String firstName = scanner.nextLine(); // takes User Input for "firstName"

                            // Ensures User Inputs "firstName" BEFORE Inputting "lastName"
                            if (firstName.isEmpty()) {
                                firstName = scanner.nextLine(); // takes User Input for "firstName"
                            }

                            System.out.println("\nEnter last name: ");
                            String lastName = scanner.nextLine(); // takes User Input for "lastName"

                            HotelResource.createACustomer(email, firstName, lastName); // calls "createACustomer()" method
                        }

                        // Books Hotel Room
                        if (bookRoomAnswer.equals("y")) {
                            // Takes User Input for Account Information
                            System.out.println("Enter email (format: name@domain.com): ");
                            String email = Customer.inputEmail(); // calls "inputEmail()" method to take User Input for "email"
                            System.out.println("\nChoose room number to reserve: ");
                            String chosenRoom = scanner.nextLine(); // takes User Input for "chosenRoom"

                            IRoom chosenRoomNumber = HotelResource.getRoom(chosenRoom); // calls "getRoom()" method to find "chosenRoom" in "roomCollection"

                            Reservation customerReservation = HotelResource.bookARoom(email, chosenRoomNumber, checkInDate, checkOutDate); // calls "bookARoom()" method

                            ReservationService.reservationCollection.add(customerReservation); // adds "customerReservation" to "reservationCollection"
                        }
                    } else if (userInput == 2) {
                        // Takes User Input for "customerEmail"
                        System.out.println("Enter email (format: name@domain.com): ");
                        String customerEmail = scanner.nextLine(); // takes User Input for "customerEmail"

                        // Displays "reservationCollection" collection
                        ReservationService.reservationCollection = HotelResource.getCustomersReservations(customerEmail); // calls "getCustomersReservations()" method
                        for (Reservation reservation : ReservationService.reservationCollection) {
                            System.out.println(reservation);
                        }
                    } else if (userInput == 3) {
                        // Takes User Input for Account Information
                        while (true) {
                            try {
                                // Obtains User Input for "email", "firstName", and "lastName" for "HotelResource" constructor & "createACustomer()" method
                                System.out.println("Enter email (format: name@domain.com): ");
                                String email = Customer.inputEmail(); // calls "inputEmail()" method to take User Input for "email"
                                System.out.println("\nEnter first name: ");
                                String firstName = scanner.nextLine(); // takes User Input for "firstName"
                                System.out.println("\nEnter last name: ");
                                String lastName = scanner.nextLine(); // takes User Input for "lastName"

                                HotelResource.createACustomer(email, firstName, lastName); // calls "createACustomer()" method
                                break;
                            } catch (StringIndexOutOfBoundsException e) {
                                System.out.println("String Index is out of bounds: ");
                                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                System.out.println("Array is out of bounds: ");
                                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Collection is out of bounds: ");
                                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                            } catch (Exception e) {
                                System.out.println("Please re-enter account information: ");
                                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                            }
                        }
                    } else if (userInput == 4) {
                        AdminMenu.Admin(); // calls "Admin()" method
                    } else if (userInput == 5) {
                        runApplication = false;
                        scanner.close(); // avoid memory leaks
                    } else {
                        System.out.println("Please enter an integer between 1 and 5");
                    }
                } catch (Exception e) { // if user does NOT ENTER A NUMBER
                    System.out.println("\nPlease enter an integer between 1 and 5\n");
                    scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                }
            }
        } catch (Exception ex) {
            ex.getLocalizedMessage(); // prints any messages or exceptions to console
            scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
        }
    }
}