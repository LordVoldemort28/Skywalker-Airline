package com.example.student.skywalkerairlines.data;

public class NU150 extends Plane {

    private final String type = "NU-150";
    private final int capacity = 75;

    public NU150(String planeID, String location) {
        super(planeID, location);

    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

}
