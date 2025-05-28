package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public static Customer getCustomer(String email) { return CustomerService.getCustomer(email); } // calls "getCustomer()" method from SERVICE file

    public static void createACustomer(String email, String firstName, String lastName) { CustomerService.addCustomer(email, firstName, lastName); } // calls "addCustomer()" method from SERVICE file

    public static IRoom getRoom(String roomNumber) { return ReservationService.getARoom(roomNumber); }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = getCustomer(customerEmail); // calls "getCustomer()" method to find "customer" WITH TYPE "Customer"
        return ReservationService.reserveARoom(customer, room, checkInDate, checkOutDate); // calls "reserveARoom()" method from SERVICE file
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = getCustomer(customerEmail); // calls "getCustomer()" method to find "customer" WITH TYPE "Customer"
        return ReservationService.getCustomersReservation(customer); // calls "getCustomersReservation()" method from SERVICE file
    }

    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut) { return ReservationService.findRooms(checkIn, checkOut); } // calls "findRooms()" method from SERVICE file
}
