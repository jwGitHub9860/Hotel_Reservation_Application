package model;

import service.ReservationService;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Room implements IRoom {
    String roomNumber;
    Double price;
    RoomType roomType; // Enum declaration variable

    // Constructor
    public Room() {
        Scanner scanner = new Scanner(System.in); // allows user input to be read
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
        }
    }

    // Room Method Definitions
    public String getRoomNumber() { return roomNumber; }
    public Double getRoomPrice() { return price; }
    public RoomType getRoomType() { return roomType; }
    public boolean isFree() { return true; }

    @Override
    public String toString(){ return "Room Number is " + roomNumber + ", " + roomType + " bed, Room Price is $" + price; }
}