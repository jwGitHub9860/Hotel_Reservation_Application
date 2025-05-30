package api;

import model.Customer;
import model.IRoom;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    //public static Customer getCustomer(String email) { return; }

    public static void addRoom(List<IRoom> rooms) {
        // Calls "Room" constructor
        IRoom room = new Room(); // allows access to "IRoom" interface
        ReservationService.addRoom((IRoom) rooms); // calls "addRoom()" method from SERVICE file
    }

    public static Collection<IRoom> getAllRooms() { return ReservationService.roomCollection; }

    public static Collection<Customer> getAllCustomers() { return CustomerService.getAllCustomers(); } // calls "getAllCustomers()" method from SERVICE file

    public static void displayAllReservations() { ReservationService.printAllReservation(); } // calls "printAllReservation()" method from SERVICE file
}
