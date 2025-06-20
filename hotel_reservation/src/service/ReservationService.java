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

    // Sorts "availableRoomCollection" by Room Numbers
    private static void sortAvailableRoomCollection() {
        // Sorts "availableRoomCollection" by Room Numbers & ONLY WORKS FOR "STRING NUMBERS", organizes String Numbers by WHOLE NUMBER
        availableRoomCollection.sort(Comparator.comparing(iRoom -> {
            String obtainRoomInfo = iRoom.getRoomNumber(); // obtains "roomNumber"
            String[] hotelRoom = obtainRoomInfo.split("\\."); // "\\." - match the character
            int firstHotelRoom = Integer.parseInt(hotelRoom[0]); // obtains 1st Room Number
            int secondHotelRoom = hotelRoom.length > 1 ? Integer.parseInt(hotelRoom[1]) : 0; // finds which Room Number is greater
            return firstHotelRoom * 1000 + secondHotelRoom; // returns "roomNumber1" and "roomNumber2" in Ascending Order
        }));
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
        // Initializes "roomFound" to Indicate if "room" was Found Inside "availableRoomCollection"
        boolean roomFound = false;

        // Checks if Room is Already Reserved
        for (IRoom availableRoom : availableRoomCollection) {
            // Checks if Room Number of "room" is NOT inside "availableRoomCollection"
            if (room.getRoomNumber().equals(availableRoom.getRoomNumber())) {
                roomFound = true;
            }
        }

        // Throws Exception if "room" was NOT inside "availableRoomCollection"
        if (!roomFound) {
            throw new IllegalArgumentException("Room must be an available room");
        }

        // Calls "getInstance()" method from "Reservation.java" to Create & Return WHOLE "Reservation"
        return Reservation.getInstance(customer, room, checkInDate, checkOutDate);
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        try {
            // Clears ALL Rooms Inside "availableRoomCollection"
            availableRoomCollection.clear();

            // Adds ALL Rooms Inside "roomCollection" to "availableRoomCollection"
            availableRoomCollection.addAll(roomCollection);

            // Checks if "reservationCollection" is Empty
            if (!reservationCollection.isEmpty()) {
                // Finds what Rooms are Available for Reservation
                for (IRoom availableRoom : availableRoomCollection) {
                    for (Reservation reservation : reservationCollection) {
                        // Indicates if Room is Available & Removes Room from "availableRoomCollection" if Room is NOT Available
                        if (reservation.getRoom().getRoomNumber().equals(availableRoom.getRoomNumber())) { // Checks if "roomNumber" in Reservation Matches "roomNumber" in "roomCollection"
                            // Checks if "checkInDate" Input is AFTER "checkInDate" in "reservationCollection" & BEFORE "checkOutDate" in "reservationCollection", because "checkOutDate" Can Only Be LATER THAN "checkInDate"
                            if (checkInDate.after(reservation.getCheckInDate()) && (checkInDate.before(reservation.getCheckOutDate()))) { // Ex.1: Reservation1: 1/5/2020-3/8/2020, Reservation2: 2/2/2020-2/20/2020, Ex.1.1: Reservation1: 1/5/2020-3/8/2020, Reservation2: 2/2/2020-4/20/2020 (INSIDE or Somewhat Inside)
                                availableRoomCollection.remove(availableRoom);
                            }
                            // Checks if "checkInDate" Input is BEFORE "checkInDate" in "reservationCollection" & AFTER "checkOutDate" in "reservationCollection"
                            else if (checkInDate.before(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckOutDate())) { // Ex.2: Reservation1: 2/5/2020-2/8/2020, Reservation2: 1/2/2020-4/20/2020 (OUTSIDE)
                                availableRoomCollection.remove(availableRoom);
                            }
                            // Checks if "checkInDate" Input is EQUAL TO "checkOutDate" in Reservation, because "checkOutDate" Can Only Be LATER THAN "checkInDate"
                            else if (checkInDate.equals(reservation.getCheckOutDate())) { // Ex.3: Reservation1: 1/2/2020-1/4/2020, Reservation2: 1/1/2020-1/2/2020 (LEFT)
                                availableRoomCollection.remove(availableRoom);
                            }
                            // Checks if "checkInDate" Input is EQUAL TO "checkInDate" in Reservation, because "checkOutDate" Can Only Be LATER THAN "checkInDate"
                            else if (checkInDate.equals(reservation.getCheckInDate())) { // Ex.4: Reservation1: 1/2/2020-1/4/2020, Reservation2: 1/2/2020-1/2/2020 (EQUAL "checkInDate")
                                availableRoomCollection.remove(availableRoom);
                            }
                            // Checks if "checkOutDate" Input is EQUAL TO "checkInDate" in Reservation
                            else if (checkOutDate.equals(reservation.getCheckInDate())) { // Ex.5: Reservation1: 2/1/2020-2/8/2020, Reservation2: 2/8/2020-2/10/2020 (RIGHT)
                                availableRoomCollection.remove(availableRoom);
                            }
                            // Checks if "checkOutDate" Input is EQUAL TO "checkOutDate" in Reservation
                            else if (checkOutDate.equals(reservation.getCheckOutDate())) { // Ex.5: Reservation1: 2/8/2020-2/9/2020, Reservation2: 2/1/2020-2/9/2020 (EQUAL "checkOutDate")
                                availableRoomCollection.remove(availableRoom);
                            }
                        }
                    }
                }
            }
        } catch (ConcurrentModificationException e) { // "ConcurrentModificationException" is UNAVOIDABLE
            // Calls "sortAvailableRoomCollection()" method to Sort "availableRoomCollection"
            sortAvailableRoomCollection();
        }

        // Calls "sortAvailableRoomCollection()" method to Sort "availableRoomCollection"
        sortAvailableRoomCollection();

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
