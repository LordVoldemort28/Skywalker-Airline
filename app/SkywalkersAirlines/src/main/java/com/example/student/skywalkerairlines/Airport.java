package com.example.student.skywalkerairlines;

public class Airport {

    private String airportCode;
    private String locationName;
    private String coordinates;

    public Airport( String locationName, String airportCode, String coordinates) {

        this.airportCode = airportCode;
        this.locationName = locationName;
        this.coordinates = coordinates;
    }


    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinatesl) {
        this.coordinates = coordinates;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }


    @Override
    public String toString() {
        return "Airport - "+locationName+"; Code - "+airportCode;
    }
}
