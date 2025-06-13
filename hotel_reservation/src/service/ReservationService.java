package service;

import model.*;

import java.util.*;

public class ReservationService {
    // Allows User Input to be Read in ALL Methods WITHIN "ReservationService" class
    final static Scanner scanner = new Scanner(System.in);

    // Customer Collection
    public static List<Customer> customerCollection = new ArrayList<>();

    // Room Collections
    public static List<IRoom> roomCollection = new ArrayList<>();
    public static Collection<String> roomNumberCollection = new HashSet<String>(); // holds ONLY Room Numbers of rooms created
    public static List<IRoom> availableRoomCollection = new ArrayList<>(); // holds ONLY Rooms Available for Reservation

    // Reservation Collection
    public static List<Reservation> reservationCollection = new LinkedList<>();

    // Sorts "reservationCollection" by USER'S CHOICE, organizes String Numbers by FIRST DIGIT in Number
    private static void sortReservations() {
        System.out.println("How would you like to sort reservations (customer first name, customer last name, room number, room type, room price, check-in date, or check-out date): ");
        while (true) {
            try {
                // Takes User Input for "sortChoice" AS STRING
                String sortChoice = scanner.nextLine();

                // Checks if User chose "Customer First Name", "Customer Last Name", "Room Number", "Room Type", "Room Price", "Check-in Date", or "Check-out Date"
                switch (sortChoice) {
                    case "customer first name":
                    case "first name":
                        // Sorts "reservationCollection" by First Names in Alphabetical Order & ONLY WORKS FOR "STRING WORDS", organizes String Numbers by FIRST DIGIT in Number
                        ReservationService.reservationCollection.sort(Comparator.comparing(reservation -> reservation.getCustomer().getFirstName()));
                        break;
                    case "customer last name":
                    case "last name":
                        // Sorts "reservationCollection" by Last Names in Alphabetical Order & ONLY WORKS FOR "STRING WORDS", organizes String Numbers by FIRST DIGIT in Number
                        ReservationService.reservationCollection.sort(Comparator.comparing(reservation -> reservation.getCustomer().getLastName()));
                        break;
                    case "room number":
                        // Sorts "reservationCollection" by Room Numbers & ONLY WORKS FOR "STRING NUMBERS", organizes String Numbers by WHOLE NUMBER
                        ReservationService.reservationCollection.sort(Comparator.comparing(reservation -> {
                            String string = reservation.getRoom().getRoomNumber(); // obtains "roomNumber"
                            String[] roomRoomNumbers = string.split("\\."); // "\\." - match the character
                            int firstRoomNumber = Integer.parseInt(roomRoomNumbers[0]); // obtains 1st Room Number
                            int secondRoomNumber = roomRoomNumbers.length > 1 ? Integer.parseInt(roomRoomNumbers[1]) : 0; // finds which Room Number is greater
                            return firstRoomNumber * 1000 + secondRoomNumber; // returns "roomNumber1" and "roomNumber2" in Ascending Order
                        }));
                        break;
                    case "room type":
                        // Sorts "reservationCollection" by Room Type in NUMERIC ORDER (1 -> SINGLE, 2 -> DOUBLE), In ASCENDING ORDER
                        ReservationService.reservationCollection.sort(Comparator.comparing(reservation -> reservation.getRoom().getRoomType()));
                        break;
                    case "room price":
                        // Sorts "reservationCollection" by Room Price in Alphabetical Order & ONLY WORKS FOR "STRING WORDS", organizes String Numbers by FIRST DIGIT in Number
                        ReservationService.reservationCollection.sort(Comparator.comparing(reservation -> reservation.getRoom().getRoomPrice()));
                        break;
                    case "check-in date":
                        // Sorts "reservationCollection" by Check-In "Dates" in Ascending Order
                        ReservationService.reservationCollection.sort(Comparator.comparing(Reservation::getCheckInDate));
                        break;
                    case "check-out date":
                        // Sorts "reservationCollection" by Check-Out "Dates" in Ascending Order
                        ReservationService.reservationCollection.sort(Comparator.comparing(Reservation::getCheckOutDate));
                        break;
                    default:
                        throw new RuntimeException("Choice must be either Customer First Name, Customer Last Name, Room Number, Room Type, Room Price, Check-in Date, or Check-out Date");
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter Customer First Name, Customer Last Name, Room Number, Room Type, Room Price, Check-in Date, or Check-out Date: ");
            }
        }
    }

    public static void addRoom(IRoom room) { roomCollection.add(room); } // adds "roomNumber", "price", and "roomType" to "roomList"

    public static IRoom getARoom(String roomId) {
        boolean roomNotFound = false; // initial "roomNotFound" value

        // Searches for Room Information with "roomId"
        for (IRoom room : roomCollection) {
            // Checks if "roomId" matches "roomNumber" of CURRENT "room" within "roomCollection"
            if (roomId.equals(room.getRoomNumber())) {
                return room;
            } else {
                roomNotFound = true;
            }
        }

        // Indicates if Room Exists Or Not & Throws Exception if Room does NOT Exist
        if (roomNotFound) {
            System.out.println("Room does not exist.\n");
            throw new NullPointerException("Room does not exist");
        }
        return null;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        // Checks if Room is Already Reserved
        for (Reservation reservation : reservationCollection) {
            // Indicates if Room is Available Or Not & Throws Exception if Room is NOT Available
            if (room.getRoomNumber().equals(reservation.getRoom().getRoomNumber())) { // Checks if "roomNumber" in Reservation Matches "roomNumber" in "reservationCollection"
                /*if (!(checkInDate.before(reservation.getCheckInDate()) && (checkOutDate.before(reservation.getCheckOutDate())))) { // Checks if "checkInDate" in Reservation is NOT BEFORE "checkInDate" & if "checkOutDate" in Reservation is NOT BEFORE "checkOutDate" in "reservationCollection"
                    throw new IllegalArgumentException("Room cannot have two reservations with check-in and check-out dates that overlap");
                } else if (!(checkInDate.after(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckOutDate()))) { // Checks if "checkInDate" in Reservation is NOT AFTER "checkInDate" & if "checkOutDate" in Reservation is NOT AFTER "checkOutDate" in "reservationCollection"
                    throw new IllegalArgumentException("Room cannot have two reservations with check-in and check-out dates that overlap");
                }*/
                if (!checkInDate.after(reservation.getCheckInDate())) {
                    if (!checkInDate.after(reservation.getCheckOutDate())) {
                        throw new IllegalArgumentException("Room cannot have two reservations with check-in and check-out dates that overlap");
                    }
                } else if (!checkOutDate.before(reservation.getCheckOutDate())) {
                    if (!checkInDate.before(reservation.getCheckInDate())) {
                        throw new IllegalArgumentException("Room cannot have two reservations with check-in and check-out dates that overlap");
                    }
                }
            }
        }
        // Calls "getInstance()" method from "Reservation.java" to Create & Return WHOLE "Reservation"
        return Reservation.getInstance(customer, room, checkInDate, checkOutDate);
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        // Returns ALL Room Inside "roomCollection" if No Reservations are Made Yet
        if (reservationCollection.isEmpty()) {
            // Adds ALL Rooms Inside "roomCollection" to "availableRoomCollection"
            availableRoomCollection.addAll(roomCollection);
        } else {
            // Checks if Room is Already Reserved
            for (IRoom hotelRoom : roomCollection) {
                for (Reservation reservation : reservationCollection) {
                    // Indicates if Room is Available & Adds Room to "availableRoomCollection" if Room IS Available
                    if (reservation.getRoom().getRoomNumber().equals(hotelRoom.getRoomNumber())) { // Checks if "roomNumber" in Reservation Matches "roomNumber" in "roomCollection"
                        if (checkInDate.before(reservation.getCheckInDate()) && (checkOutDate.before(reservation.getCheckOutDate()))) { // Checks if "checkInDate" in Reservation is BEFORE "checkInDate" & if "checkOutDate" in Reservation is BEFORE "checkOutDate" in "reservationCollection"
                            availableRoomCollection.add(hotelRoom);
                        } else if (checkInDate.after(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckOutDate())) { // Checks if "checkInDate" in Reservation is AFTER "checkInDate" & if "checkOutDate" in Reservation is AFTER "checkOutDate" in "reservationCollection"
                            availableRoomCollection.add(hotelRoom);
                        }
                    } else { // if "roomNumber" is NOT in "reservationCollection"
                        availableRoomCollection.add(hotelRoom);
                    }
                }
            }
        }
        return availableRoomCollection;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        // holds ONLY Reservations of "customer"
        Collection<Reservation> customerReservationCollection = new LinkedList<>(); // declared here to Prevent Having Duplicates

        // Checks if Reservation was Made By "customer"
        for (Reservation reservation : reservationCollection) {
            // Indicates if Reservation was Made By "customer"
            if (customer.equals(reservation.getCustomer())) { // Checks if "customer" in Reservation Matches "customer"
                customerReservationCollection.add(reservation); // adds "reservation" to "customerReservationCollection"
            }
        }
        return customerReservationCollection;
    }

    public static void printAllReservation() {
        // Sorts "reservationCollection" by User's Choice
        sortReservations();
        
        // Display reservation information inside "reservationCollection"
        for (Reservation reservation : reservationCollection) {
            System.out.println(reservation + "\n");
        }
    }
}
