package api;

import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;
import service.SortByRoomNumber;

import java.text.DecimalFormat;
import java.util.*;

public class AdminResource {
    //public static Customer getCustomer(String email) { return; }

    public static void addRoom(List<IRoom> rooms) {
        Scanner scanner = new Scanner(System.in); // allows user input to be read

        String addRoomRepeat = "y"; // initial "addRoomRepeat" value
        while (addRoomRepeat.equals("y")) {
            // Calls "inputRoomNumber()" method
            String roomNumberUserInput = Room.inputRoomNumber();

            // Calls "inputRoomPrice()" method
            Double roomPriceUserInput = Room.inputRoomPrice();

            // Calls "inputRoomType()" method
            RoomType roomTypeUserInput = Room.inputRoomType();

            // Calls "Room" constructor
            IRoom room = new Room(roomNumberUserInput, roomPriceUserInput, roomTypeUserInput); // allows access to "IRoom" interface

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
                    scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                }
            }
        }

        // Sorts "roomList" with "SortByRoomNumber" class from SERVICE file
        rooms.sort(Comparator.comparing(iRoom -> {
            String string = iRoom.getRoomNumber(); // obtains "roomNumber"
            String[] roomRoomNumbers = string.split("\\."); // "\\." - match the character
            int firstRoomNumber = Integer.parseInt(roomRoomNumbers[0]); // obtains 1st Room Number
            int secondRoomNumber = roomRoomNumbers.length > 1 ? Integer.parseInt(roomRoomNumbers[1]) : 0; // finds which Room Number is greater
            return firstRoomNumber * 1000 + secondRoomNumber; // returns "roomNumber1" and "roomNumber2" in Ascending Order
        }));

        System.out.println("\nroomList:\n"); // TESTING CODE
        for (IRoom data : ReservationService.roomList){ //
            System.out.printf(data + "\n"); // TESTING CODE
        }
        System.out.println("\nroomCollection:"); // TESTING CODE
        for (IRoom data : ReservationService.roomCollection){ //
            System.out.printf(data + "\n"); // TESTING CODE
        }
    }

    public static Collection<IRoom> getAllRooms() { return ReservationService.roomCollection; }

    public static Collection<Customer> getAllCustomers() { return CustomerService.getAllCustomers(); } // calls "getAllCustomers()" method from SERVICE file

    public static void displayAllReservations() { ReservationService.printAllReservation(); } // calls "printAllReservation()" method from SERVICE file
}
