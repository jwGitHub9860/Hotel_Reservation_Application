package model;

public class Room implements IRoom {
    String roomNumber;
    Double price;
    public RoomType roomType; // Enum declaration variable

    @Override
    public String toString(){ return "Room Number is " + roomNumber + " and Price is $" + price; }
}
