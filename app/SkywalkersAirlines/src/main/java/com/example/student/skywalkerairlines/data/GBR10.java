package com.example.student.skywalkerairlines.data;

public class GBR10 extends Plane {
    private final String type = "GBR";
    private final int capacity = 45;
    public GBR10(String planeID, String location) {
        super(planeID, location);
    }
    public String getType() {
        return type;
    }
    public int getCapacity() {
        return capacity;
    }
}
