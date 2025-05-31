package service;

import model.Room;

import java.util.Comparator;

public class SortByRoomNumber implements Comparator<Room> {
    // Sorts Collection in Ascending Order of Room Numbers
    @Override
    public int compare(Room o1, Room o2) {
        int roomNumber1 = Integer.parseInt(o1.getRoomNumber()); // changes 1st "roomNumber" from "String" to "int"
        int roomNumber2 = Integer.parseInt(o2.getRoomNumber()); // changes 2nd "roomNumber" from "String" to "int"
        return Integer.compare(roomNumber1, roomNumber2); // returns "roomNumber1" and "roomNumber2" in Ascending Order
    }
}
