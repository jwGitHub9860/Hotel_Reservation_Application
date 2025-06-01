package model;

import service.ReservationService;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Room implements IRoom {
    String roomNumber;
    Double price;
    RoomType roomType; // Enum declaration variable
    private static boolean isValid = false;

    // Variables for Methods that Take User Input for Room Information
    private static String roomNumberInput;
    static Double priceInput;
    private static RoomType roomTypeInput;

    // Constructor
    public Room(String roomNumber, Double price, RoomType roomType) {
        // Sets "roomNumber", "price", "roomType" to CURRENT "roomNumber", "price", "roomType"
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    // Room Method Definitions
    public String getRoomNumber() { return roomNumber; }
    public Double getRoomPrice() { return price; }
    public RoomType getRoomType() { return roomType; }
    public boolean isFree() { return true; }

    // Takes User Input for Room Information
    public static String inputRoomNumber() {
        Scanner scanner = new Scanner(System.in); // allows user input to be read

        // Takes User Input for Room Number
        System.out.println("Enter room number: ");
        while (!isValid) { // ensures that user inputted "roomNumberInput"
            try {
                roomNumberInput = String.valueOf(scanner.nextInt()); // takes User Input for "roomNumberInput"

                // Checks if "roomNumberCollection" is NOT empty
                if (!ReservationService.roomNumberCollection.isEmpty()) {
                    // Prevents user from creating two Hotel Rooms with the Same Room Number
                    for (String number : ReservationService.roomNumberCollection) {
                        // Checks if user inputted "roomNumberInput" that is the Same as a Previous "roomNumberInput"
                        if (roomNumberInput.equals(number)) {
                            throw new IllegalArgumentException("There cannot be two hotel rooms with the same room number.");
                        }
                        else {
                            isValid = true;
                        }
                    }
                    //ReservationService.roomNumberCollection.add(roomNumberInput); // adds "roomNumberInput" to "roomNumberCollection"
                } else {
                    //ReservationService.roomNumberCollection.add(roomNumberInput); // adds "roomNumberInput" to "roomNumberCollection"
                    isValid = true;
                }
            } catch (IllegalArgumentException e) { // if user enters Room Number that is the SAME AS PREVIOUS HOTEL NUMBER
                System.out.println("Please enter a different room number: ");
                // scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                isValid = false; // ensures that user inputted "roomNumberInput"
            } catch (Exception e) { // if user does NOT ENTER INTEGER FOR HOTEL NUMBER
                System.out.println("Please enter an integer for the room number: ");
                // scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                isValid = false; // ensures that user inputted "roomNumberInput"
            }
        }
        return roomNumberInput;
    }
    public static Double inputRoomPrice() {
        Scanner scanner = new Scanner(System.in); // allows user input to be read

        // Checks if user inputted "Double" value for Room "priceInput"
        System.out.println("Enter price per night: ");
        while (true) {
            try {
                // Ensures that Room "priceInput" is in "#.##" format
                DecimalFormat decimalFormat = new DecimalFormat("#.00"); // creates "DecimalFormat" instance with "#.##" format
                priceInput = Double.valueOf(decimalFormat.format(scanner.nextDouble())); // takes User Input for Room "priceInput" & rounds "priceInput" off to "#.##" format
                break;
            } catch (Exception e) {
                System.out.println("Please enter price in \"#.##\" format: ");
                // scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
            }
        }
        return priceInput;
    }
    public static RoomType inputRoomType() {
        Scanner scanner = new Scanner(System.in); // allows user input to be read

        // Takes User Input for Room Type
        System.out.println("Enter room type (1 for single bed, 2 for double bed): ");
        while (!isValid) { // ensures that user inputted "roomTypeInput"
            try {
                int roomTypeUserInput = scanner.nextInt(); // takes User Input for "roomTypeUserInput"

                // Checks if user inputted "1" or "2"
                switch (roomTypeUserInput) {
                    case 1:
                        roomTypeInput = RoomType.SINGLE;
                        isValid = true;
                        break;
                    case 2:
                        roomTypeInput = RoomType.DOUBLE;
                        isValid = true;
                        break;
                    default:
                        System.out.println("Please enter 1 or 2: ");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter 1 or 2: ");
                // scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                isValid = false;
            }
        }
        return roomTypeInput;
    }

    @Override
    public String toString(){ return "Room Number is " + roomNumber + ", " + roomType + " bed, Room Price is $" + String.format("%.2f", price); }
}