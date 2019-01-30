package com.example.student.skywalkerairlines.data;

public class Plane {

    private String planeID = "PL";
    private String location;

    public Plane(String planeID, String location) {
        this.planeID += planeID;
        this.location = location;
    }
    public String getPlaneID() {
        return planeID;
    }
    public void setPlaneID(String planeID) {
        this.planeID = planeID;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
