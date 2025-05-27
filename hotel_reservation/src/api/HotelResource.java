package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public static Customer getCustomer(String email) { return; }

    public static void createACustomer(String email, String firstName, String lastName) {}

    public static IRoom getRoom(String roomNumber) { return; }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) { return; }*/

    public static Collection<Reservation> getCustomersReservations(String customerEmail) { return ReservationService.reservationCollection; }

    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut) { return; }
}
