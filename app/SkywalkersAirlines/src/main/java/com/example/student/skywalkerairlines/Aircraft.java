package com.example.student.skywalkerairlines;

public class Aircraft {

    private String location;
    private String type;
    private int capacity;
    private String aircraftName;

    public Aircraft(String aircraftName, String location, String type){
        this.aircraftName = aircraftName;
        this.location = location;
        this.type = type;
        this.capacity = setCapacity(type);
    }

    public int setCapacity(String type){
        if(type.equalsIgnoreCase("NU-150"))
            return 75;
        else
            return 45;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public String getAircraftName() {
        return aircraftName;
    }


    @Override
    public String toString() {
        return this.aircraftName+" - "+this.location;
    }
}
