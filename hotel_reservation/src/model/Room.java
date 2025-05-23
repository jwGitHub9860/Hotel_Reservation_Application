package model;

public class Room {
    String roomNumber;
    Double price;
    // Room Types
    enum RoomType{ SINGLE, DOUBLE }

    @Override
    public String toString(){ return "Room Number is " + roomNumber + " and Price is $" + price; }
}
