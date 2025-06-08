import api.AdminResource;
import model.*;
import service.CustomerService;
import service.ReservationService;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public static void Admin() {
        // Allows user input to be read
        Scanner scanner = new Scanner(System.in); // Must Be Put Here To PREVENT INFINITE WHILE LOOP

        // Admin Menu
        boolean runAdmin = true;
        try {
            while (runAdmin) {
                try {
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
                    int adminUserInput = scanner.nextInt(); // reads User Input & takes ONLY INTEGER from full line of user input

                    // MUST BE IN "if-else statements" OR CODE WILL JUMP TO ANOTHER CASE WHEN IT IS NOT SUPPOSE TO
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

                        // Display room information inside "roomCollection"
                        for (IRoom room : roomList) {
                            System.out.println(room);
                        }
                    } else if (adminUserInput == 3) {
                        AdminResource.displayAllReservations(); // calls "displayAllReservations()" method from "AdminResource.java"
                    } else if (adminUserInput == 4) {
                        AdminResource.addRoom(ReservationService.roomCollection); // calls "addRoom()" method from "AdminResource.java"
                    } else if (adminUserInput == 5) {
                        // Tests if "Customer" constructor works
                        Customer customerTest = new Customer("firstName", "lastName", "email"); // calls "Customer" constructor
                        System.out.println(customerTest); // calls "toString()" override method from "Customer.java"

                        // FIRST Test if "FreeRoom" constructor works
                        FreeRoom freeRoom = new FreeRoom("100", 0.0, RoomType.DOUBLE); // calls "FreeRoom" constructor
                        System.out.println(freeRoom); // calls "toString()" override method from "FreeRoom.java"

                        // SECOND Test if "FreeRoom" constructor works
                        // Obtains User Input for "roomNumber", "price", and "roomType" for "Room" constructor
                        String roomNumberUserInput = Room.inputRoomNumber(); // calls "inputRoomNumber()" method
                        Double roomPriceUserInput = Room.inputRoomPrice(); // calls "inputRoomPrice()" method
                        RoomType roomTypeUserInput = Room.inputRoomType(); // calls "inputRoomType()" method

                        if (roomPriceUserInput == 0.0) {
                            IRoom room = new FreeRoom(roomNumberUserInput, roomPriceUserInput, roomTypeUserInput); // allows access to "IRoom" interface
                            System.out.println(room); // calls "toString()" override method from "FreeRoom.java"
                        } else {
                            // Calls "Room" constructor
                            IRoom room = new Room(roomNumberUserInput, roomPriceUserInput, roomTypeUserInput); // allows access to "IRoom" interface
                            System.out.println(room); // calls "toString()" override method from "Room.java"
                        }

                        // Tests if "getCustomer()" method from "AdminResource.java" works
                        Customer methodTest = AdminResource.getCustomer("name@domain.com");
                        System.out.println(methodTest);
                    } else if (adminUserInput == 6) {
                        runAdmin = false;
                        System.out.println("\n");
                    } else {
                        System.out.println("Please enter an integer between 1 and 6");
                    }
                } catch (Exception e) { // if user does NOT ENTER A NUMBER
                    System.out.println("\nPlease enter a number");
                    scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                }
            }
        } catch (Exception ex) {
            ex.getLocalizedMessage(); // prints any messages or exceptions to console
            scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
        }
    }
}
