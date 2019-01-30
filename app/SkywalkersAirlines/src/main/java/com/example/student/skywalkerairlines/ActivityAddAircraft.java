package com.example.student.skywalkerairlines;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityAddAircraft extends AppCompatActivity implements View.OnClickListener{

    private Spinner sLocation;
    private Spinner sType;
    private Button bAddAircraft;

    private ArrayList<String> aircraftList;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private DateTimeFormatter formatter;
    private DateTime logDate;
    private String logCount;

    private Aircraft aircraft;

    private ArrayList<String>  airportList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aircraft);

        sLocation = (Spinner) findViewById(R.id.add_aircraft_location);
        sType = (Spinner) findViewById(R.id.add_aircraft_type);
        bAddAircraft = (Button) findViewById(R.id.button_add_aircraft);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        airportList = new ArrayList<>();
        logCount = "";

        airportList.add("Select airport");

        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, R.layout.spinner_item, getType());
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sType.setAdapter(adapterType);

        getAirportList();

        ArrayAdapter<String> adapterAirport = new ArrayAdapter<String>(this, R.layout.spinner_item, airportList);
        adapterAirport.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sLocation.setAdapter(adapterAirport);

        bAddAircraft.setOnClickListener(this);


    }

    public ArrayList<String> getType(){
        ArrayList<String> type = new ArrayList<>();
        type.add("GBR-10");
        type.add("NU-150");
        return type;
    }

    public void getAirportList(){



        mFirebaseDatabase.getReference("airport").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot each_airport : dataSnapshot.getChildren()){

                    String airportName = (String) each_airport.child("locationName").getValue();
                    String airportCode = (String) each_airport.child("airportCode").getValue();

                    airportList.add(airportName+" : "+airportCode);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void addAircraft(){
        String type = sType.getSelectedItem().toString();
        String location = sLocation.getSelectedItem().toString();

        String key = mFirebaseDatabase.getReference("aircraft").push().getKey();
        String aircraftId = key.substring(key.length()-6);

        aircraft = new Aircraft(aircraftId, location.substring(location.length()-3), type);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put( key, aircraft);

        mFirebaseDatabase.getReference("aircraft").updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Toast.makeText(getBaseContext(), "Successfully Added!!!", Toast.LENGTH_LONG).show();
                updatelog();
            }
        });

    }

    public void updatelog(){


        mFirebaseDatabase.getReference("logs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               int count = 1;
                for (DataSnapshot each : dataSnapshot.getChildren()){
                    count++;
                }
                logCount = Integer.toString(count);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        formatter = DateTimeFormat.forPattern("MM-YYYY");
        logDate = DateTime.now();

        String monthAndYear = (String)formatter.print(logDate);

        String logUpdate = logCount+"-"+monthAndYear+ ": Created Aircraft "+ aircraft.toString();
        mFirebaseDatabase.getReference("logs").push().setValue(logUpdate);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.button_add_aircraft:{
                addAircraft();
            }
        }
    }//End of onClick
}


