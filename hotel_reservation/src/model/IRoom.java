package model;

// Room Methods
public interface IRoom {
    public String getRoomNumber(String roomNumber);
    public Double getRoomPrice(Double price);
    public RoomType getRoomType(RoomType roomType);
    public boolean isFree();
}
