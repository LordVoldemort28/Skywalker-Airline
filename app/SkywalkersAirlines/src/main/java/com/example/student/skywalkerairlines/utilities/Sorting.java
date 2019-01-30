package com.example.student.skywalkerairlines.utilities;

import com.example.student.skywalkerairlines.data.Flight;

import org.joda.time.Hours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.joda.*;

public class Sorting {
//    public ArrayList<Flight> sortedFlights(ArrayList<Flight> flights, String source, String destination){
//        ArrayList<Flight> sortedFlights = new ArrayList();
//        for(Flight f : flights) {
//            if(f.getSourceID().equalsIgnoreCase(source) && f.getDestinationID().equalsIgnoreCase(destination)) {
//                sortedFlights.add(f);
//                f.getActualTakeoffTime().isBefore(f.getEstimatedTakeoffTime());
//            }
//        }
//        Collections.sort(sortedFlights, new SortByFlightTime());
//        return sortedFlights;
//
//    }
//    public ArrayList<Employee> sortedEmployee(ArrayList<Employee> emp){
//        Collections.sort(emp, new SortByEmpTime());
//        return emp;
//    }
//    class SortByFlightTime implements Comparator<Flight> {
//
//        @Override
//        public int compare(Flight f1, Flight f2) {
//            return f1.getScheduleTakeoffTime().compareTo(f2.getScheduleTakeoffTime());
//        }
//
//    }
//    class SortByEmpTime implements Comparator<Employee>{
//
//        @Override
//        public int compare(Employee e1, Employee e2) {
//
//            return (Hours.hoursBetween(e1.getClockoutTime(), e1.getClockinTime()).getHours()-
//                    Hours.hoursBetween(e2.getClockoutTime(), e2.getClockinTime()).getHours());
//        }
//
//    }
}
