package model;

import service.ReservationService;

import java.text.DecimalFormat;
import java.util.Comparator;
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
    
    // Room Info to Sort By
    private static String obtainRoomInfo;
    private static String[] hotelRoom;
    private static int firstHotelRoom;
    private static int secondHotelRoom;

    // Allows User Input to be Read in ALL Methods WITHIN "Room" class
    final static Scanner scanner = new Scanner(System.in);

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
        // Takes User Input for Room Number
        System.out.println("Enter room number: ");
        while (!isValid) { // ensures that user inputted "roomNumberInput"
            try {
                roomNumberInput = String.valueOf(scanner.nextInt()); // takes User Input for "roomNumberInput"

                // Ensures User Inputs "roomNumberInput" BEFORE Inputting "priceInput" to Prevent Infinite While Loop
                if (roomNumberInput.isEmpty()) { roomNumberInput = String.valueOf(scanner.nextInt()); } // takes User Input for "roomNumberInput"

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
                } else {
                    isValid = true;
                }
                ReservationService.roomNumberCollection.add(roomNumberInput); // adds "roomNumberInput" to "roomNumberCollection"
            } catch (IllegalArgumentException e) { // if user enters Room Number that is the SAME AS PREVIOUS HOTEL NUMBER
                System.out.println("Please enter a different room number: ");
                isValid = false; // ensures that user inputted "roomNumberInput"
            } catch (Exception e) { // if user does NOT ENTER INTEGER FOR HOTEL NUMBER
                System.out.println("Please enter an integer for the room number: ");
                isValid = false; // ensures that user inputted "roomNumberInput"
            }
        }

        // Reverts "isValid" back to "false"
        isValid = false; // "isValid" was CHANGED TO "true" After "inputRoomNumber()" method FINISHED

        return roomNumberInput;
    }
    public static Double inputRoomPrice() {
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
                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
            }
        }
        return priceInput;
    }
    public static RoomType inputRoomType() {
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
                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                isValid = false;
            }
        }

        // Reverts "isValid" back to "false"
        isValid = false; // "isValid" was CHANGED TO "true" After "inputRoomNumber()" method FINISHED

        return roomTypeInput;
    }

    // Sorts "roomCollection" by USER'S CHOICE, organizes String Numbers by FIRST DIGIT in Number
    public static void sortRooms() {
        System.out.println("How would you like to sort reservations (room number, room price, or room type): ");
        while (true) {
            try {
                // Takes User Input for "sortChoice" AS STRING
                String sortChoice = scanner.nextLine();

                // Ensures user inputs "sortChoice" BEFORE "roomList" is Sorted
                if (sortChoice.isEmpty()) { sortChoice = scanner.nextLine(); }

                // Takes final "sortChoice" and inputs "sortChoice" into "finalSortChoice" to Prevent "sortChoice" from being corrupted and/or changed & to Prevent Infinite While Loop
                String finalSortChoice = sortChoice;

                // Checks if User chose "Room Price"
                if (finalSortChoice.equals("room price")) {
                    ReservationService.roomCollection.sort(Comparator.comparingDouble(IRoom::getRoomPrice)); // sorts "roomCollection" by "price"
                } else {
                    // Sorts "roomCollection" by Room Numbers OR Room Types & ONLY WORKS FOR "STRING NUMBERS", organizes String Numbers by WHOLE NUMBER
                    ReservationService.roomCollection.sort(Comparator.comparing(iRoom -> {
                        // Checks if User chose "Room Number" or "Room Type"
                        switch (finalSortChoice) {
                            case "room number":
                                obtainRoomInfo = iRoom.getRoomNumber(); // obtains "roomNumber"
                                break;
                            case "room type":
                                RoomType roomTypeAnswer = iRoom.getRoomType(); // obtains "roomType"
                                if (roomTypeAnswer.equals(RoomType.SINGLE)) {
                                    obtainRoomInfo = "1";
                                } else {
                                    obtainRoomInfo = "2";
                                }
                                break;
                            default:
                                throw new IllegalArgumentException("Choice must be either Room Number, Room Price, or Room Type");
                        }
                        hotelRoom = obtainRoomInfo.split("\\."); // "\\." - match the character
                        firstHotelRoom = Integer.parseInt(hotelRoom[0]); // obtains 1st Room Number
                        secondHotelRoom = hotelRoom.length > 1 ? Integer.parseInt(hotelRoom[1]) : 0; // finds which Room Number is greater
                        return firstHotelRoom * 1000 + secondHotelRoom; // returns "roomNumber1" and "roomNumber2" in Ascending Order
                    }));
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter Room Number, Room Type, or Room Price: ");
            }
        }
    }

    @Override
    public String toString(){ return "Room Number is " + roomNumber + ", " + roomType + " bed, Room Price is $" + String.format("%.2f", price); }
}