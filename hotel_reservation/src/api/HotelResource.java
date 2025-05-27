package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public Customer getCustomer(String email) { return; }

    public void createACustomer(String email, String firstName, String lastName) {}

    public IRoom getRoom(String roomNumber) { return; }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) { return; }*/

    public Collection<Reservation> getCustomersReservations(String customerEmail) { return ReservationService.reservationCollection; }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) { return; }
}
