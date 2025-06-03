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

    @Override
    public String toString() { return "Reservation:\nCustomer: " + customer.getFirstName() + " " + customer.getLastName() + "\nRoom is " + room + "\nCheck-In Date: " + checkInDate + "\nCheck-Out Date: " + checkOutDate; }
}
