package com.example.student.skywalkerairlines.data;


public class Person {
    private String firstName;
    private String lastName;
    private Boolean isEmployee;
    private String userName;
    private String type;
    private String location;
//    private DateTime clockinTime;
//    private DateTime clockoutTime;

    public Person(String firstName, String lastName, Boolean isEmployee, String userName, String type){
        this.firstName = firstName;
        this.lastName = lastName;
        this.isEmployee =  isEmployee;
        this.userName = userName;
        this.type = type;
    }

    //Type Co-Pilot - CP, Pilot - PI, Flight Attentent - FA
    public Person(String firstName, String lastName, Boolean isEmployee, String userName, String type, String location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isEmployee = isEmployee;
        this.userName = userName;
        this.type = type;
        this.location = location;
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

//    public DateTime getClockinTime() {
//        return clockinTime;
//    }
//
//    public void setClockinTime(DateTime clockinTime) {
//        this.clockinTime = clockinTime;
//    }
//
//    public DateTime getClockoutTime() {
//        return clockoutTime;
//    }
//
//    public void setClockoutTime(DateTime clockoutTime) {
//        this.clockoutTime = clockoutTime;
//    }

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

    public Boolean getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(Boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
