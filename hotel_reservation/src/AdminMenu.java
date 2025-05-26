import model.Customer;
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
                        // Takes User Input for Room Information
                        System.out.println("Enter room number: ");
                        String roomNumber = scanner.nextLine(); // takes User Input for "roomNumber"
                        System.out.println("Enter price per night: ");
                        Double roomPrice = scanner.nextDouble(); // takes User Input for "roomPrice"
                        System.out.println("Enter room type (1 for single bed, 2 for double bed): ");
                        int roomType = scanner.nextInt(); // takes User Input for "roomType"
                        System.out.println("Would you like to add another room (y/n): ");
                        addRoomRepeat = scanner.next(); // takes User Input to confirm if user wants to add another room
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
