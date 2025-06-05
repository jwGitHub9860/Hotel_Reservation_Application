package service;

import model.*;

import java.util.*;

public class ReservationService {
    // Collections to store & retrieve reservations
    public static List<Customer> customerCollection = new ArrayList<>();

    // Room Collections
    public static List<IRoom> roomCollection = new ArrayList<>();
    public static Collection<String> roomNumberCollection = new HashSet<String>(); // holds ONLY Room Numbers of rooms created

    // Reservation Collections
    public static Collection<Reservation> reservationCollection = new LinkedList<>();
    public static Collection<Reservation> customerReservationCollection = new LinkedList<>();

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
        if (roomNotFound) {
            System.out.println("Room does not exist.\n");
            throw new NullPointerException("Room does not exist");
        }
        return null;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) { return new Reservation(customer, room, checkInDate, checkOutDate); } // calls "Reservation" constructor to Create & Return WHOLE "Reservation"

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) { return roomCollection; }

    public static Collection<Reservation> getCustomersReservation(Customer customer) { return reservationCollection; }

    public static void printAllReservation() {
        // Display reservation information inside "reservationCollection"
        for (Reservation reservation : reservationCollection) {
            System.out.println(reservation);
        }
    }
}
