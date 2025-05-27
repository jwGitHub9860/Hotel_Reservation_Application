package model;

public class Room implements IRoom {
    String roomNumber;
    Double price;
    public static RoomType roomType; // Enum declaration variable

    // Room Method Definitions
    public String getRoomNumber(String roomNumber) { return roomNumber; }
    public Double getRoomPrice(Double price) { return price; }
    public RoomType getRoomType(RoomType roomType) { return roomType; }
    public boolean isFree() { return true; }

    @Override
    public String toString(){ return "Room Number is " + roomNumber + " and Price is $" + price; }
}