package com.example.student.skywalkerairlines.data;

import org.joda.time.DateTime;

public class Flight {
    private String id = "FL";
    private String sourceID;
    private String destinationID;
    private String pilotID;
    private String copilotID;
    private String flightAttID;
    private Boolean isFlying;
    private String aircraftID;
    private DateTime scheduleTakeoffTime;
    private DateTime actualTakeoffTime;
    private DateTime estimatedTakeoffTime;
    private DateTime scheduleTouchDownTime;
    private DateTime actualTouchDownTime;
    private DateTime estimatedTouchDownTime;

    public Flight(String id, String sourceID, String destinationID, String pilotID, String copilotID,
                  String flightAttID, Boolean isFlying, String aircraftID, DateTime scheduleTakeoffTime,
                  DateTime actualTakeoffTime, DateTime estimatedTakeoffTime, DateTime scheduleTouchDownTime,
                  DateTime actualTouchDownTime, DateTime estimatedTouchDownTime) {
        this.id += id;
        this.sourceID = sourceID;
        this.destinationID = destinationID;
        this.pilotID = pilotID;
        this.copilotID = copilotID;
        this.flightAttID = flightAttID;
        this.isFlying = isFlying;
        this.aircraftID = aircraftID;
        this.scheduleTakeoffTime = scheduleTakeoffTime;
        this.actualTakeoffTime = actualTakeoffTime;
        this.estimatedTakeoffTime = estimatedTakeoffTime;
        this.scheduleTouchDownTime = scheduleTouchDownTime;
        this.actualTouchDownTime = actualTouchDownTime;
        this.estimatedTouchDownTime = estimatedTouchDownTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(String destinationID) {
        this.destinationID = destinationID;
    }

    public String getPilotID() {
        return pilotID;
    }

    public void setPilotID(String pilotID) {
        this.pilotID = pilotID;
    }

    public String getCopilotID() {
        return copilotID;
    }

    public void setCopilotID(String copilotID) {
        this.copilotID = copilotID;
    }

    public String getFlightAttID() {
        return flightAttID;
    }

    public void setFlightAttID(String flightAttID) {
        this.flightAttID = flightAttID;
    }

    public Boolean getIsFlying() {
        return isFlying;
    }

    public void setIsFlying(Boolean isFlying) {
        this.isFlying = isFlying;
    }

    public String getAircraftID() {
        return aircraftID;
    }

    public void setAircraftID(String aircraftID) {
        this.aircraftID = aircraftID;
    }

    public DateTime getScheduleTakeoffTime() {
        return scheduleTakeoffTime;
    }

    public void setScheduleTakeoffTime(DateTime scheduleTakeoffTime) {
        this.scheduleTakeoffTime = scheduleTakeoffTime;
    }

    public DateTime getActualTakeoffTime() {
        return actualTakeoffTime;
    }

    public void setActualTakeoffTime(DateTime actualTakeoffTime) {
        this.actualTakeoffTime = actualTakeoffTime;
    }

    public DateTime getEstimatedTakeoffTime() {
        return estimatedTakeoffTime;
    }

    public void setEstimatedTakeoffTime(DateTime estimatedTakeoffTime) {
        this.estimatedTakeoffTime = estimatedTakeoffTime;
    }

    public DateTime getScheduleTouchDownTime() {
        return scheduleTouchDownTime;
    }

    public void setScheduleTouchDownTime(DateTime scheduleTouchDownTime) {
        this.scheduleTouchDownTime = scheduleTouchDownTime;
    }

    public DateTime getActualTouchDownTime() {
        return actualTouchDownTime;
    }

    public void setActualTouchDownTime(DateTime actualTouchDownTime) {
        this.actualTouchDownTime = actualTouchDownTime;
    }

    public DateTime getEstimatedTouchDownTime() {
        return estimatedTouchDownTime;
    }

    public void setEstimatedTouchDownTime(DateTime estimatedTouchDownTime) {
        this.estimatedTouchDownTime = estimatedTouchDownTime;
    }
}
