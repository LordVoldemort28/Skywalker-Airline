package com.example.student.skywalkerairlines;

public class Employee {
    private String firstName;
    private String lastName;
    private String userName;
    private String type;
    private String location;

    public Employee(String firstName, String lastName, String userName, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.type = type;
    }

    public Employee(String firstName, String lastName, String userName, String type, String location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.type = type;
        this.location = location;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return firstName+" "+lastName;
    }
}
