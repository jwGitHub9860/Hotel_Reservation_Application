package service;

import model.*;

import java.text.DecimalFormat;
import java.util.*;

public class ReservationService {
    // Collections to store & retrieve reservations
    public static Collection<Customer> customerCollection = new LinkedList<Customer>();

    // Room Collections
    public static Collection<IRoom> roomCollection = new HashSet<>();
    public static HashMap<String, Double> roomPriceCollection = new HashMap<>();
    public static HashMap<String, RoomType> roomTypeCollection = new HashMap<>();

    public static Collection<Reservation> reservationCollection = new LinkedList<>();

    public static void addRoom(IRoom room) {
        Scanner scanner = new Scanner(System.in); // allows user input to be read

        // Allows access to "IRoom" interface
        Room roomInstance = new Room(); // creates "Room" instance

        String addRoomRepeat = "y"; // initial "addRoomRepeat" value
        while (addRoomRepeat.equals("y")) {
            // Takes User Input for Room Information
            System.out.println("Enter room number: ");
            Room.roomNumber = scanner.nextLine(); // takes User Input for "roomNumber"

            // Ensures that user inputted "roomNumber"
            if (Room.roomNumber.isEmpty()) { Room.roomNumber = scanner.nextLine(); }

            // Checks if user inputted "Double" value for Room "price"
            System.out.println("Enter price per night: ");
            while (true) {
                try {
                    // Ensures that Room "price" is in "#.##" format
                    DecimalFormat decimalFormat = new DecimalFormat("#.##"); // creates "DecimalFormat" instance with "#.##" format
                    Room.price = Double.valueOf(decimalFormat.format(scanner.nextDouble())); // takes User Input for Room "price" & rounds Room "price" off to "#.##" format
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter price in \"#.##\" format: ");
                }
            }

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
            roomCollection.add(roomInstance); // adds "roomNumber", "price", and "roomType" to "roomCollection"
        }
    }

    /*public static IRoom getARoom(String roomId) { return; }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) { return; }*/

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) { return; }

    public static Collection<Reservation> getCustomersReservation(Customer customer) { return ReservationService.reservationCollection; }

    public static void printAllReservation() {
        System.out.println("\nADMINadminUserInput 3 WORKS"); // TESTING CODE
    }
}
