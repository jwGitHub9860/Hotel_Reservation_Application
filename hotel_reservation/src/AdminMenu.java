import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
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
                        // Calls "getAllCustomers()" method from "AdminResource.java"
                        Collection<Customer> customerList = AdminResource.getAllCustomers();

                        // Display customer information inside "customerList" collection
                        for (Customer customer : customerList) {
                            System.out.println(customer);
                        }
                    } else if (adminUserInput == 2) {
                        // Calls "getAllRooms()" method from "AdminResource.java"
                        Collection<IRoom> roomList = AdminResource.getAllRooms();

                        // Display room information inside "roomCollection" collection
                        for (IRoom room : ReservationService.roomCollection) {
                            System.out.println(room);
                        }
                    } else if (adminUserInput == 3) {
                        AdminResource.displayAllReservations(); // calls "displayAllReservations()" method from "AdminResource.java"
                    } else if (adminUserInput == 4) {
                        // Allows access to "IRoom" interface
                        Room roomInstance = new Room(); // creates "Room" instance


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
