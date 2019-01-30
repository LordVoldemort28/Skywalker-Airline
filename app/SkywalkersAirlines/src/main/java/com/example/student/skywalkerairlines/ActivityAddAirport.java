package com.example.student.skywalkerairlines;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ActivityAddAirport extends AppCompatActivity implements View.OnClickListener {

    private EditText airportName;
    private EditText airportCode;
    private EditText airportCoordinates;
    private Button addAirport;
    private String code;
    private boolean alreadyExist;
    private Airport airport;
    private String airportCount;

    private DateTimeFormatter formatter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private DateTime logDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_airport);

        airportName = (EditText) findViewById(R.id.add_airport_name);
        airportCode = (EditText) findViewById(R.id.add_airport_code);
        airportCoordinates = (EditText) findViewById(R.id.add_airport_coordinates);
        addAirport = (Button) findViewById(R.id.b_add_airport);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        addAirport.setOnClickListener(this);



    }

    public void addAirportToDatabase(){

        alreadyExist = false;

        String name = airportName.getText().toString().trim();
        code = airportCode.getText().toString().trim();
        String coordinate = airportCoordinates.getText().toString().trim();

        if(name.isEmpty()){
            airportName.setError("Invalid name or empty");
            airportName.requestFocus();
        }
        if(code.isEmpty()){
            airportCode.setError("Invalid code or empty");
            airportCode.requestFocus();
        }
        if(coordinate.isEmpty()){
            airportCoordinates.setError("Empty codes");
            airportCoordinates.requestFocus();

        }


        airport = new Airport(name, code, coordinate);

        mFirebaseDatabase.getReference("airport").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot each_airport : dataSnapshot.getChildren()){


                    String airport_code = (String)each_airport.child("airportCode").getValue();
                    if(airport_code.equalsIgnoreCase(code)){
                        airportCode.setError("Airport already exist");
                        airportCode.requestFocus();
                        alreadyExist = true;
                        return;
                    }
                }

                if(alreadyExist != true){
                    Toast.makeText(getBaseContext(), "Successfully Added!!!", Toast.LENGTH_LONG).show();

                    mFirebaseDatabase.getReference("airport").push().setValue(airport).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d("AddAirport", "Successfully added");
                            Toast.makeText(getBaseContext(), "Successfully Added!!!", Toast.LENGTH_LONG).show();
                            updatelog();
                            airportName.setText("");
                            airportCode.setText("");
                            airportCoordinates.setText("");
                        }

                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void updatelog(){


        mFirebaseDatabase.getReference("logs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long count = dataSnapshot.getChildrenCount()+1;
                airportCount = Long.toString(count);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        formatter = DateTimeFormat.forPattern("MM-YYYY");
        logDate = DateTime.now();

        String monthAndYear = (String)formatter.print(logDate);

        String logUpdate = airportCount+"-"+monthAndYear+ ": Created "+ airport.toString();
        mFirebaseDatabase.getReference("logs").push().setValue(logUpdate);
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){
            case R.id.b_add_airport:{
                addAirportToDatabase();
            }

        }
    }


}
