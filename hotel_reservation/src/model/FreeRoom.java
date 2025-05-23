package model;

public class FreeRoom extends Room {
    // Constructor
    public FreeRoom(Double price) { price = 0.0; }

    @Override
    public String toString() { return "The room is free!"; }
}
