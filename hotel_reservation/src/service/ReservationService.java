package service;

import model.*;

import java.util.*;

public class ReservationService {
    // Collections to store & retrieve reservations
    public static List<Customer> customerCollection = new LinkedList<Customer>();

    // Room Collections
    public static List<IRoom> roomCollection = new ArrayList<>();
    public static Collection<String> roomNumberCollection = new HashSet<String>(); // holds ONLY Room Numbers of rooms created

    public static Collection<Reservation> reservationCollection = new LinkedList<>();

    public static void addRoom(IRoom room) { roomCollection.add(room); } // adds "roomNumber", "price", and "roomType" to "roomList"

    public static IRoom getARoom(String roomId) {
        // ADD CORRECT CODE

        // Allows access to "IRoom" interface
        Room roomInstance = new Room("test", 0.00, RoomType.DOUBLE); // creates "Room" instance         TESTING CODE
        return roomInstance;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) { return new Reservation(customer, room, checkInDate, checkOutDate); } // calls "Reservation" constructor to Create & Return WHOLE "Reservation"

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) { return roomCollection; }

    public static Collection<Reservation> getCustomersReservation(Customer customer) { return reservationCollection; }

    public static void printAllReservation() {
        System.out.println("\nADMINadminUserInput 3 WORKS"); // TESTING CODE
    }
}
