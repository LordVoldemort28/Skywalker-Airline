package com.example.student.skywalkerairlines;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.student.skywalkerairlines.utilities.DatabaseQuery;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfileRegister extends AppCompatActivity implements View.OnClickListener {

    private static final String MANAGER = "1";

    private EditText mFirstName;
    private EditText mLastName;
    private Spinner mPosition;
    private Spinner mLocation;
    private Button btRegister;
    private DatabaseQuery databaseQuery;

    private FirebaseDatabase mFirebaseFirestore;
    private DatabaseReference mCollectionReference;
    private FirebaseAuth mFirebaseAuth;


    private static String manager = null;
    private static String email = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);


        mFirstName = (EditText) findViewById(R.id.firstName);
        mLastName = (EditText) findViewById(R.id.lastName);
        mPosition = (Spinner) findViewById(R.id.position);
        mLocation = (Spinner) findViewById(R.id.location);
        btRegister = (Button) findViewById(R.id.register);

        Intent registerPage = getIntent();

        if(registerPage.hasExtra("MANAGER")){
            manager = registerPage.getStringExtra("MANAGER");
        }

        if(registerPage.hasExtra("EMAIL")){
            email = registerPage.getStringExtra("EMAIL");
        }


        if(manager.equalsIgnoreCase(MANAGER)){

            mPosition.setVisibility(View.INVISIBLE);
            mLocation.setVisibility(View.INVISIBLE);

        }else{
            ArrayAdapter<String> adapterPosition = new ArrayAdapter<String>(this, R.layout.spinner_item, getPostion());
            adapterPosition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mPosition.setAdapter(adapterPosition);


            ArrayAdapter<String> adapterLocation = new ArrayAdapter<String>(this, R.layout.spinner_item, getAiportsName());
            adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mLocation.setAdapter(adapterLocation);

        }

        btRegister.setOnClickListener(this);


    }


    public void pushDetail(){
        mFirebaseFirestore = FirebaseDatabase.getInstance();
        mCollectionReference = mFirebaseFirestore.getReference("employee");

        String userFirstName = mFirstName.getText().toString().trim();
        String userLastName = mLastName.getText().toString().trim();

        if(userFirstName.equalsIgnoreCase("First Name") || userFirstName.isEmpty()){
            mFirstName.setError("Enter valid first name");
            mFirstName.requestFocus();
            return;
        }

        if(userLastName.equalsIgnoreCase("Last Name") || userLastName.isEmpty()){
            mLastName.setError("Enter valid Last Name");
            mLastName.requestFocus();
            return;
        }


        //Register Manager
        if(manager.equalsIgnoreCase(MANAGER)){
            Employee addManager = new Employee(userFirstName, userLastName, email, "Manager");
            mCollectionReference.push().setValue(addManager).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d("ProfileRegister", "Successfully added");
                }

            });

            Intent dashboard = new Intent(ProfileRegister.this, MainActivity.class);
            startActivity(dashboard);
            finish();

            Toast.makeText(this, addManager.toString(), Toast.LENGTH_SHORT).show();

        }

        //Register Crew
        else{
            String userPosition = mPosition.getSelectedItem().toString();
            String userLocation = mLocation.getSelectedItem().toString();

            Employee addCrew = new Employee(userFirstName, userLastName, email, userPosition, userLocation );
            mCollectionReference.push().setValue(addCrew)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d("ProfileRegister", "Successfully added");
                        }

                    });
            Toast.makeText(this, addCrew.toString(), Toast.LENGTH_SHORT).show();


            Intent crewdashBoard = new Intent(ProfileRegister.this, ActivityCrewDashboard.class);
            startActivity(crewdashBoard);
            finish();
            //TODO: Implement intent to show Crew Detail page from here
        }


    }



    public ArrayList<String> getPostion(){
        ArrayList<String> position = new ArrayList<>();
        position.add("Flight Attendent");
        position.add("Co Pilot");
        position.add("Pilot");

        return position;
    }

    //TODO:Attach query into the adapter to give list of Airport and give arguement to select Airport object and select name
    public ArrayList<String> getAiportsName(){
        ArrayList<String> airportsName = new ArrayList<>();

        airportsName.add("Omaha");
        airportsName.add("Lincoln");
        airportsName.add("Newark");
        airportsName.add("Denver");


        return airportsName;
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.register:{
                pushDetail();
                break;
            }
        }
    }//End onClick
}


