package model;

public class FreeRoom extends Room {
    // Constructor
    public FreeRoom(String roomNumber, Double price, RoomType roomType) {
        super(roomNumber, price, roomType);
        this.price = 0.00;
    }

    @Override
    public String toString() { return "The room is free!"; }
}
