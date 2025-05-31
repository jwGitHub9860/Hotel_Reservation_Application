package model;

import service.ReservationService;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Room implements IRoom {
    String roomNumber;
    Double price;
    RoomType roomType; // Enum declaration variable
    boolean isValid = false;

    // Constructor
    public Room() { // Takes User Input for Room Information
        Scanner scanner = new Scanner(System.in); // allows user input to be read

        // Takes User Input for Room Number
        System.out.println("Enter room number: ");
        while (!isValid) { // ensures that user inputted "roomNumber"
            try {
                roomNumber = String.valueOf(scanner.nextInt()); // takes User Input for "roomNumber"

                // Checks if "roomNumberCollection" is NOT empty
                if (!ReservationService.roomNumberCollection.isEmpty()) {
                    // Prevents user from creating two Hotel Rooms with the Same Room Number
                    for (String number : ReservationService.roomNumberCollection) {
                        // Checks if user inputted "roomNumber" that is the Same as a Previous "roomNumber"
                        if (roomNumber.equals(number)) {
                            throw new IllegalArgumentException("There cannot be two hotel rooms with the same room number.");
                        }
                        else {
                            isValid = true;
                        }
                    }
                    ReservationService.roomNumberCollection.add(roomNumber); // adds "roomNumber" to "roomNumberCollection"
                } else {
                    ReservationService.roomNumberCollection.add(roomNumber); // adds "roomNumber" to "roomNumberCollection"
                    isValid = true;
                }
            } catch (IllegalArgumentException e) { // if user enters Room Number that is the SAME AS PREVIOUS HOTEL NUMBER
                System.out.println("Please enter a different room number: ");
                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                isValid = false; // ensures that user inputted "roomNumber"
            } catch (Exception e) { // if user does NOT ENTER INTEGER FOR HOTEL NUMBER
                System.out.println("Please enter an integer for the room number: ");
                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                isValid = false; // ensures that user inputted "roomNumber"
            }
        }

        // Resets "isValid" back to "false"
        isValid = false; // allows "while loop" that takes User Input for Room Type to Run

        // Checks if user inputted "Double" value for Room "price"
        System.out.println("Enter price per night: ");
        while (true) {
            try {
                // Ensures that Room "price" is in "#.##" format
                DecimalFormat decimalFormat = new DecimalFormat("#.00"); // creates "DecimalFormat" instance with "#.##" format
                price = Double.valueOf(decimalFormat.format(scanner.nextDouble())); // takes User Input for Room "price" & rounds Room "price" off to "#.##" format

                // Makes Room a Free Room if user inputs 0 for "price"
                if (price == 0) {
                    FreeRoom freeRoom = new FreeRoom(price); // calls "FreeRoom" constructor
                    System.out.println(freeRoom);
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter price in \"#.##\" format: ");
                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
            }
        }

        // Takes User Input for Room Type
        System.out.println("Enter room type (1 for single bed, 2 for double bed): ");
        while (!isValid) { // ensures that user inputted "roomType"
            try {
                int roomTypeInput = scanner.nextInt(); // takes User Input for "roomTypeInput"

                // Checks if user inputted "1" or "2"
                switch (roomTypeInput) {
                    case 1:
                        roomType = RoomType.SINGLE;
                        isValid = true;
                        break;
                    case 2:
                        roomType = RoomType.DOUBLE;
                        isValid = true;
                        break;
                    default:
                        System.out.println("Please enter 1 or 2: ");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter 1 or 2: ");
                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                isValid = false;
            }
        }
    }

    // Room Method Definitions
    public String getRoomNumber() { return roomNumber; }
    public Double getRoomPrice() { return price; }
    public RoomType getRoomType() { return roomType; }
    public boolean isFree() { return true; }

    @Override
    public String toString(){ return "Room Number is " + roomNumber + ", " + roomType + " bed, Room Price is $" + String.format("%.2f", price); }
}