package service;

import model.*;

import java.util.*;

public class ReservationService {
    // Collections to store & retrieve reservations
    public static List<Customer> customerCollection = new ArrayList<>();

    // Room Collections
    public static List<IRoom> roomCollection = new ArrayList<>();
    public static Collection<String> roomNumberCollection = new HashSet<String>(); // holds ONLY Room Numbers of rooms created

    // Reservation Collection
    public static List<Reservation> reservationCollection = new LinkedList<>();

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
            if (checkInDate.equals(reservation.getCheckInDate())) { // Checks if "checkInDate" in Reservation Matches ANY "checkInDate" in "reservationCollection"
                if (room.getRoomNumber().equals(reservation.getRoom().getRoomNumber())) { // Checks if "roomNumber" in Reservation Matches "roomNumber" in "reservationCollection"
                    throw new IllegalArgumentException("Room cannot be reserved");
                }
            }
        }
        // calls "Reservation" constructor to Create & Return WHOLE "Reservation"
        return new Reservation(customer, room, checkInDate, checkOutDate);
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) { return roomCollection; }

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
        Reservation.sortReservations();
        
        // Display reservation information inside "reservationCollection"
        for (Reservation reservation : reservationCollection) {
            System.out.println(reservation + "\n");
        }
    }
}
