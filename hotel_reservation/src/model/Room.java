package model;

public class Room {
    String roomNumber;
    Double price;



    @Override
    public String toString(){ return "Room Number is " + roomNumber + " and Price is $" + price; }
}
