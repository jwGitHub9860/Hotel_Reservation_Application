package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ReservationService {
    // Collections to store & retrieve reservations         DO I NEED MORE?
    List<String> customerList = new LinkedList<String>();
    Collection<Customer> customerCollection = new LinkedList<Customer>();
    Collection<IRoom> roomCollection = new LinkedList<>();
    Collection<Reservation> reservationCollection = new LinkedList<>();

    public void addRoom(IRoom room) {}

    public IRoom getARoom(String roomId) { return; }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) { return; }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) { return; }

    public Collection<Reservation> getCustomersReservation(Customer customer) { return; }

    public void printAllReservation() {}
}
