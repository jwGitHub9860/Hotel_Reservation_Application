package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    //public Customer getCustomer(String email) { return; }

    public static void addRoom(List<IRoom> rooms) { ReservationService.addRoom((IRoom) ReservationService.roomCollection); } // calls "addRoom()" method from SERVICE file

    //public Collection<IRoom> getAllRooms() { return; }

    public static Collection<Customer> getAllCustomers() { return CustomerService.getAllCustomers(); } // calls "getAllCustomers()" method from SERVICE file

    public static void displayAllReservations() { ReservationService.printAllReservation(); } // calls "printAllReservation()" method from SERVICE file
}
