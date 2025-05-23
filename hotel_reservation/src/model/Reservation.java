package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    @Override
    public String toString() { return "Customer is " + customer + "\nRoom is " + room + "\nCheck-in date is " + checkInDate + "\nCheck-out date is " + checkOutDate; }
}
