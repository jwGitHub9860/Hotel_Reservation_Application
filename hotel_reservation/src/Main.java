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
import java.util.ArrayList;
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

        // Checks if user inputted "dateInput" in "MM/dd/yyyy" format
        while (true) {
            try {
                // Takes User Input for "dateInput" AS STRING
                String dateInput = scanner.nextLine();

                // Checks if Month, Day, and Year are ALL Integers
                String[] dateInputArray = dateInput.split("/"); // splits "dateInput" at "/"
                for (String datePart : dateInputArray) {
                    Integer.parseInt(datePart);
                }

                // Checks if "dateInput" Ends With ANYTHING BUT A NUMBER, "0-9"
                if (dateInput.endsWith("[^0-9]")) {
                    throw new IllegalArgumentException("Date cannot end with anything, but a number, 0-9"); // Throws Error to Prevent Malicious Attacks (ex. SQL Injection)
                }

                // Checks if Check-In Date is Later Than Check-Out Date
                String[] dateInput1Array = new String[0]; // holds "checkInDate" IN "String" FORM
                String[] dateInput2Array = new String[0]; // holds "checkOutDate" IN "String" FORM
                if (dateInput1Array == null) {
                    dateInput1Array = dateInput.split("/"); // splits "checkInDate" into "dateInput1Array" IN "String" FORM
                } else {
                    dateInput2Array = dateInput.split("/"); // splits "checkOutDate" into "dateInput2Array" IN "String" FORM
                }

                // Checks if "dateInput" is in "MM/dd/yyyy" format
                return simpleDateFormat.parse(dateInput);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Month, day, and year must all be integers: ");
            } catch (Exception e) {
                System.out.println("Please enter date in \"MM/dd/yyyy\" format: ");
            }
        }
    }

    private static void inputAccountInformation() {
        // Obtains User Input for "email" for "HotelResource" constructor & "createACustomer()" method
        System.out.println("Enter email (format: name@domain.com): ");
        String email = Customer.inputEmail(); // calls "inputEmail()" method to take User Input for "email"

        // Obtains User Input for "firstName" for "HotelResource" constructor & "createACustomer()" method
        System.out.println("\nEnter first name: ");
        String firstName = scanner.nextLine(); // takes User Input for "firstName"

        // Ensures User Inputs "firstName" BEFORE Inputting "lastName"
        if (firstName.isEmpty()) { firstName = scanner.nextLine(); } // takes User Input for "firstName"

        // Obtains User Input for "lastName" for "HotelResource" constructor & "createACustomer()" method
        System.out.println("\nEnter last name: ");
        String lastName = scanner.nextLine(); // takes User Input for "lastName"

        // Calls "createACustomer()" method
        HotelResource.createACustomer(email, firstName, lastName);
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

                            // Takes User Input for Answering if User wants to Book A Room or Already Has Account
                            System.out.println("\nWould you like to book a room (y/n): ");
                            String bookRoomAnswer = AdminResource.inputYOrN(); // calls "inputYOrN()" method to take user input for "bookRoomAnswer"

                            // Double-Checks that User has Account
                            System.out.println("Do you have an account with us (y/n): ");
                            while (true) {
                                try {
                                    String accountAnswer = AdminResource.inputYOrN(); // calls "inputYOrN()" method to take user input for "accountAnswer"

                                    // Creates Customer Account if User does NOT have account
                                    if (accountAnswer.equals("n")) {
                                        inputAccountInformation(); // calls "inputAccountInformation()" method
                                        System.out.println("\nDo you have an account with us (y/n): "); // asks if user now has account
                                    } else {
                                        // Takes User Input for "customerEmail"
                                        System.out.println("Enter email (format: name@domain.com): ");

                                        // Calls "inputEmail()" method to Take User Input for "customerEmail"
                                        String customerEmail = Customer.inputEmail();

                                        // Calls "getCustomer()" method to Check if "customerEmail" EXISTS in "customerCollection"
                                        Customer existingEmail = HotelResource.getCustomer(customerEmail);

                                        // Books Hotel Room
                                        if (bookRoomAnswer.equals("y")) {
                                            // Reserves Room for Customer
                                            System.out.println("\nChoose room number to reserve: ");
                                            while (true) {
                                                try {
                                                    // Takes User Input for Reserving Room Number
                                                    String chosenRoom = scanner.nextLine(); // takes User Input for "chosenRoom"

                                                    // Calls "getRoom()" method to Find "chosenRoomNumber" in "roomCollection"
                                                    IRoom chosenRoomNumber = HotelResource.getRoom(chosenRoom);

                                                    // Calls "bookARoom()" method to Create WHOLE "Reservation"
                                                    Reservation customerReservation = HotelResource.bookARoom(customerEmail, chosenRoomNumber, checkInDate, checkOutDate);

                                                    // Adds "customerReservation" to "reservationCollection"
                                                    ReservationService.reservationCollection.add(customerReservation);

                                                    break;
                                                } catch (Exception e) { // if Room is Already Reserved OR does Not exist
                                                    System.out.println("Please choose a different room number to reserve: ");
                                                }
                                            }
                                        }
                                        break;
                                    }
                                } catch (Exception e) { // in case user mistyped "email" OR answered Yes by mistake
                                    System.out.println("Are you sure that you have an account (y/n): ");
                                }
                            }
                        } else { // if NO Rooms Were CREATED
                            System.out.println("There are no available rooms.\n\nRooms must be created first.\n");
                        }
                    } else if (userInput == 2) {
                        // Takes User Input for "customerEmail"
                        System.out.println("Enter email (format: name@domain.com): ");
                        while (true) {
                            try {
                                // Calls "inputEmail()" method to Take User Input for "customerEmail"
                                String customerEmail = Customer.inputEmail();

                                // Calls "getCustomer()" method to Check if "customerEmail" EXISTS in "customerCollection"
                                Customer existingEmail = HotelResource.getCustomer(customerEmail);

                                // Displays "reservationCollection" collection
                                Collection<Reservation> customerReservations = HotelResource.getCustomersReservations(customerEmail); // calls "getCustomersReservations()" method
                                for (Reservation reservation : customerReservations) {
                                    System.out.println(reservation + "\n");
                                }
                                break;
                            } catch (Exception e) { // Customer should Technically ALREADY HAVE Account
                                System.out.println("Please enter a different email (format: name@domain.com): ");
                            }
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