package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    // Constructor
    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Reservation Method Definitions
    public IRoom getRoom() { return room; }

    @Override
    public String toString() { return "Reservation:\nCustomer: " + customer.getFirstName() + " " + customer.getLastName() + "\nRoom: " + room.getRoomNumber() + " - " + room.getRoomType() + " bed\nPrice: " + room.getRoomPrice() + " price per night\nCheck-In Date: " + checkInDate + "\nCheck-Out Date: " + checkOutDate; }
}
