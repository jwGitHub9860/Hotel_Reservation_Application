package api;

import model.Customer;
import model.IRoom;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    //public Customer getCustomer(String email) { return; }

    public void addRoom(List<IRoom> rooms) {}

    /*public Collection<IRoom> getAllRooms() { return; }

    public Collection<Customer> getAllCustomers() { return; }*/

    public void displayAllReservations() { ReservationService.printAllReservation(); } // calls "printAllReservation()" method from "ReservationService.java" SERVICE file
}
