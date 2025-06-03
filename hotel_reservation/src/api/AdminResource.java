package api;

import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.text.DecimalFormat;
import java.util.*;

public class AdminResource {
    // Allows User Input to be Read in ALL Methods WITHIN "AdminResource" class
    final static Scanner scanner = new Scanner(System.in);

    public static String inputYOrN() {
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

    //public static Customer getCustomer(String email) { return; }

    public static void addRoom(List<IRoom> rooms) {
        String addRoomRepeat = "y"; // initial "addRoomRepeat" value
        while (addRoomRepeat.equals("y")) {
            // Obtains User Input for "roomNumber", "price", and "roomType" for "Room" constructor
            String roomNumberUserInput = Room.inputRoomNumber(); // calls "inputRoomNumber()" method
            Double roomPriceUserInput = Room.inputRoomPrice(); // calls "inputRoomPrice()" method
            RoomType roomTypeUserInput = Room.inputRoomType(); // calls "inputRoomType()" method

            // Calls "Room" constructor
            IRoom room = new Room(roomNumberUserInput, roomPriceUserInput, roomTypeUserInput); // allows access to "IRoom" interface

            // Calls "addRoom()" method from SERVICE file
            ReservationService.addRoom(room);

            // Confirms if User wants to Add Another Room
            System.out.println("Would you like to add another room (y/n): ");
            addRoomRepeat = inputYOrN(); // calls "inputYOrN()" method to take User Input
        }

        // Sorts "roomList" by Room Numbers & ONLY WORKS FOR "STRING NUMBERS", organizes String Numbers by WHOLE NUMBER
        rooms.sort(Comparator.comparing(iRoom -> {
            String string = iRoom.getRoomNumber(); // obtains "roomNumber"
            String[] roomRoomNumbers = string.split("\\."); // "\\." - match the character
            int firstRoomNumber = Integer.parseInt(roomRoomNumbers[0]); // obtains 1st Room Number
            int secondRoomNumber = roomRoomNumbers.length > 1 ? Integer.parseInt(roomRoomNumbers[1]) : 0; // finds which Room Number is greater
            return firstRoomNumber * 1000 + secondRoomNumber; // returns "roomNumber1" and "roomNumber2" in Ascending Order
        }));
    }

    public static Collection<IRoom> getAllRooms() { return ReservationService.roomCollection; }

    public static Collection<Customer> getAllCustomers() { return CustomerService.getAllCustomers(); } // calls "getAllCustomers()" method from SERVICE file

    public static void displayAllReservations() { ReservationService.printAllReservation(); } // calls "printAllReservation()" method from SERVICE file
}
