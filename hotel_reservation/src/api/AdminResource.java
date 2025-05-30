package api;

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

public class AdminResource {
    //public static Customer getCustomer(String email) { return; }

    public static void addRoom(List<IRoom> rooms) {
        Scanner scanner = new Scanner(System.in); // allows user input to be read

        String addRoomRepeat = "y"; // initial "addRoomRepeat" value
        while (addRoomRepeat.equals("y")) {
            // Calls "Room" constructor
            IRoom room = new Room(); // allows access to "IRoom" interface

            // Calls "addRoom()" method from SERVICE file
            ReservationService.addRoom(room);

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
    }

    public static Collection<IRoom> getAllRooms() { return ReservationService.roomCollection; }

    public static Collection<Customer> getAllCustomers() { return CustomerService.getAllCustomers(); } // calls "getAllCustomers()" method from SERVICE file

    public static void displayAllReservations() { ReservationService.printAllReservation(); } // calls "printAllReservation()" method from SERVICE file
}
