package com.example.student.skywalkerairlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {


    private FirebaseFirestore mFirebaseFirestore;

    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;

    private TextView mUsernameDisplay;

    private Button mFlightButton;
    private Button mCrewButton;
    private Button mCreateAircraft;
    private Button mCreateAirport;

    private static String loginEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        mUsernameDisplay = (TextView) findViewById(R.id.dashboard_username_check);
        mFlightButton = (Button) findViewById(R.id.b_flight_page);
        mCrewButton = (Button) findViewById(R.id.b_crew_page);
        mCreateAircraft = (Button) findViewById(R.id.b_add_aircraft);
        mCreateAirport = (Button) findViewById(R.id.b_add_airport_page);


        mFirebaseAuth = FirebaseAuth.getInstance();

        Intent loginPageIntent = getIntent();

        if(loginPageIntent.hasExtra("USERNAME")){
            loginEmail = loginPageIntent.getStringExtra("USERNAME");
            mUsernameDisplay.setText(loginEmail);

        }

        mUsernameDisplay.setText(mFirebaseAuth.getCurrentUser().getEmail());

        mFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent flightIntent  = new Intent(MainActivity.this, FlightPage.class);
                startActivity(flightIntent);
            }
        });

        mCrewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crewIntent = new Intent(MainActivity.this, CrewPage.class);
                startActivity(crewIntent);
            }
        });

        mCreateAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent airportIntent = new Intent(MainActivity.this, ActivityAirportPage.class);
                startActivity(airportIntent);
            }
        });

        mCreateAircraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aircraftIntent = new Intent(MainActivity.this, ActivityAircraftPage.class);
                startActivity(aircraftIntent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.manager_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        //Completed:  Implement the logout button to reach to login page
        switch (id){
            case R.id.logout:{
                mFirebaseAuth.getInstance().signOut();
                Intent backToLogin = new Intent(MainActivity.this, ActivityLoginPage.class);
                startActivity(backToLogin);
                finish();
                return true;
            }

            case R.id.logs:{
                Intent logIntent  = new Intent(MainActivity.this, ActivityLogs.class);
                startActivity(logIntent);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}