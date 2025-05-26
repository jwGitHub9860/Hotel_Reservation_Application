import model.Customer;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Scanner;

public class AdminMenu {
    public static void Admin() {
        // Admin Menu
        boolean runAdmin = true;
        try {
            while (runAdmin) {
                try {
                    Scanner scanner = new Scanner(System.in); // allows user input to be read & Must Be Put Here To PREVENT INFINITE WHILE LOOP

                    // Admin Menu Items
                    System.out.println("\nAdmin Menu");
                    System.out.println("-------------------------------------------------");
                    System.out.println("1. See all Customers");
                    System.out.println("2. See all Rooms");
                    System.out.println("3. See all Reservations");
                    System.out.println("4. Add a Room");
                    System.out.println("5. Add Test Data");
                    System.out.println("6. Back to Main Menu");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Please select a number for the menu option");

                    // Takes User Input
                    int adminUserInput = Integer.parseInt(scanner.nextLine()); // reads User Input & takes ONLY INTEGER from full line of user input
                    if (adminUserInput == 1) {
                        Collection<Customer> customerList = CustomerService.getAllCustomers(); // calls "getAllCustomers()" method

                        // Display customer information inside "customerList" collection
                        for (Customer customer : customerList) {
                            System.out.println(customer);
                        }
                    } else if (adminUserInput == 2) {
                        System.out.println("ADMINadminUserInput 2 WORKS"); // TESTING CODE
                    } else if (adminUserInput == 3) {
                        ReservationService.printAllReservation(); // calls "printAllReservation()" method
                    } else if (adminUserInput == 4) {
                        String addRoomRepeat = "y"; // initial "addRoomRepeat" value
                        while (addRoomRepeat.equals("y")) {
                            // Takes User Input for Room Information
                            System.out.println("Enter room number: ");
                            String roomNumber = scanner.nextLine(); // takes User Input for "roomNumber"

                            // ensures that user inputted "roomNumber"
                            if (roomNumber.isEmpty()) { roomNumber = scanner.nextLine(); }

                            System.out.println("Enter price per night: ");
                            Double roomPrice = scanner.nextDouble(); // takes User Input for "roomPrice"

                            // Checks if user inputted "1" or "2"
                            System.out.println("Enter room type (1 for single bed, 2 for double bed): ");
                            while (true) {
                                int roomTypeInput = scanner.nextInt(); // takes User Input for "roomTypeInput"

                                switch (roomTypeInput) {
                                    case 1:
                                        Room.roomType = RoomType.SINGLE;
                                        break;
                                    case 2:
                                        Room.roomType = RoomType.DOUBLE;
                                        break;
                                    default:
                                        System.out.println("Please enter 1 or 2: ");
                                        break;
                                }

                                if (roomTypeInput == 1 || roomTypeInput == 2) { break; } // exits while loop if "roomTypeInput" is "1" or "2"
                            }

                            // Checks if user inputted "y" or "n"
                            System.out.println("Would you like to add another room (y/n): ");
                            while (true) {
                                try {
                                    addRoomRepeat = scanner.next(); // takes User Input to confirm if user wants to add another room
                                    if (addRoomRepeat.equals("y") || addRoomRepeat.equals("n")) { // Valid answers
                                        break;
                                    } else { // INVALID answer
                                        throw new IllegalArgumentException("Answer must be \"y\" or \"n\"");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Please enter y (yes) or n (n): ");
                                }
                            }
                        }
                    } else if (adminUserInput == 5) {
                        System.out.println("ADMINadminUserInput 5 WORKS"); // TESTING CODE
                    } else if (adminUserInput == 6) {
                        runAdmin = false;
                        System.out.println("\n");
                    } else {
                        System.out.println("Please enter an integer between 1 and 6");
                    }
                } catch (Exception e) { // if user does NOT ENTER A NUMBER
                    System.out.println("\nPlease enter a number");
                }
            }
        } catch (Exception ex) {
            ex.getLocalizedMessage(); // prints any messages or exceptions to console
        }
    }
}
