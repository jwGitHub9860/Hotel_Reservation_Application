import api.AdminResource;
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
    final static Scanner scanner = new Scanner(System.in);

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

    private static void inputAccountInformation() {
        // Obtains User Input for "email", "firstName", and "lastName" for "HotelResource" constructor & "createACustomer()" method
        System.out.println("Enter email (format: name@domain.com): ");
        String email = Customer.inputEmail(); // calls "inputEmail()" method to take User Input for "email"
        System.out.println("\nEnter first name: ");
        String firstName = scanner.nextLine(); // takes User Input for "firstName"

        // Ensures User Inputs "firstName" BEFORE Inputting "lastName"
        if (firstName.isEmpty()) { firstName = scanner.nextLine(); } // takes User Input for "firstName"

        System.out.println("\nEnter last name: ");
        String lastName = scanner.nextLine(); // takes User Input for "lastName"

        HotelResource.createACustomer(email, firstName, lastName); // calls "createACustomer()" method
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
                        Date checkInDate = inputCheckInAndCheckOutDates(); // calls "inputCheckInAndCheckOutDates()" method to take user input for "checkInDate"
                        System.out.println("Enter Check-Out Date (ex. 02/01/2020): ");
                        Date checkOutDate = inputCheckInAndCheckOutDates(); // calls "inputCheckInAndCheckOutDates()" method to take user input for "checkOutDate"

                        // Display all Rooms Available to be Reserved
                        Collection<IRoom> roomSearch = HotelResource.findARoom(checkInDate, checkOutDate); // calls "findARoom()" method
                        if (!roomSearch.isEmpty()) { // indicates if No Rooms are Available
                            // Display all rooms created
                            for (IRoom room : roomSearch) {
                                System.out.println(room);
                            }
                        } else {
                            System.out.println("There are no available rooms.");
                        }

                        // Takes User Input for Answering if User wants to Book A Room or Already Has Account
                        System.out.println("\nWould you like to book a room (y/n): ");
                        String bookRoomAnswer = AdminResource.inputYOrN(); // calls "inputYOrN()" method to take user input for "bookRoomAnswer"
                        System.out.println("Do you have an account with us (y/n): ");
                        String accountAnswer = AdminResource.inputYOrN(); // calls "inputYOrN()" method to take user input for "accountAnswer"

                        // Creates Customer Account if User does NOT have account
                        if (accountAnswer.equals("n")) {
                            inputAccountInformation(); // calls "inputAccountInformation()" method
                        }

                        // Books Hotel Room
                        if (bookRoomAnswer.equals("y")) {
                            // Takes User Input for Email Information
                            System.out.println("Enter email (format: name@domain.com): ");
                            String email; // defines "email"
                            while (true) {
                                try {
                                    // Calls "inputEmail()" method to Take User Input for "email"
                                    email = Customer.inputEmail();

                                    // Calls "getCustomer()" method to Check if "email" EXISTS in "customerCollection"
                                    Customer existingEmail = HotelResource.getCustomer(email);

                                    break;
                                } catch (Exception e) {
                                    System.out.println("Please enter a different email (format: name@domain.com): ");
                                }
                            }

                            // Reserves Room for Customer
                            System.out.println("\nChoose room number to reserve: ");
                            while (true) {
                                try {
                                    // Takes User Input for Reserving Room Number
                                    String chosenRoom = scanner.nextLine(); // takes User Input for "chosenRoom"

                                    // Calls "getRoom()" method to Find "chosenRoomNumber" in "roomCollection"
                                    IRoom chosenRoomNumber = HotelResource.getRoom(chosenRoom);

                                    // Calls "bookARoom()" method to Create WHOLE "Reservation"
                                    Reservation customerReservation = HotelResource.bookARoom(email, chosenRoomNumber, checkInDate, checkOutDate);

                                    // Adds "customerReservation" to "reservationCollection"
                                    ReservationService.reservationCollection.add(customerReservation);

                                    break;
                                } catch (Exception e) { // if Room is Already Reserved OR does Not exist
                                    System.out.println("Please choose a different room number to reserve: ");
                                }
                            }
                        }
                    } else if (userInput == 2) {
                        // Takes User Input for "customerEmail"
                        System.out.println("Enter email (format: name@domain.com): ");
                        String customerEmail = scanner.nextLine(); // takes User Input for "customerEmail"

                        // Displays "reservationCollection" collection
                        ReservationService.reservationCollection = HotelResource.getCustomersReservations(customerEmail); // calls "getCustomersReservations()" method
                        for (Reservation reservation : ReservationService.reservationCollection) {
                            System.out.println(reservation + "\n");
                        }
                    } else if (userInput == 3) {
                        while (true) {
                            try {
                                inputAccountInformation(); // calls "inputAccountInformation()" method
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