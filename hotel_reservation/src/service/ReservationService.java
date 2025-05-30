package service;

import model.*;

import java.util.*;

public class ReservationService {
    // Collections to store & retrieve reservations
    public static Collection<Customer> customerCollection = new LinkedList<Customer>();

    // Room Collections
    public static Collection<IRoom> roomCollection = new ArrayList<>();
    public static List<IRoom> roomList = new ArrayList<>(); // holds User Input for each Individual Room

    public static Collection<Reservation> reservationCollection = new LinkedList<>();

    // Allows access to "IRoom" interface
    static Room roomInstance = new Room(); // creates "Room" instance

    // Allows access to "Reservation" interface
    static Reservation reservationInstance = new Reservation(); // creates "Reservation" instance

    public static void addRoom(IRoom room) {
        ReservationService.roomCollection.add(room); // adds "roomNumber", "price", and "roomType" to "roomCollection"
        // FIGURE OUT HOW TO ADD TO ROOM LIST
        //AdminResource.addRoom((List<IRoom>) ReservationService.roomCollection); // calls "addRoom()" method from "AdminResource.java"
    }

    public static IRoom getARoom(String roomId) { return roomInstance; } // ADD CORRECT CODE

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) { return reservationInstance; } // Creates & Returns WHOLE "Reservation"

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) { return roomCollection; }

    public static Collection<Reservation> getCustomersReservation(Customer customer) { return reservationCollection; }

    public static void printAllReservation() {
        System.out.println("\nADMINadminUserInput 3 WORKS"); // TESTING CODE
    }
}
