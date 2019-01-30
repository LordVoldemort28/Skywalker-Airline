package com.example.student.skywalkerairlines;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;

public class ActivityAirportPage extends AppCompatActivity{

    private FloatingActionButton fabAddAirport;
    private ListView listViewAirport;
    private ProgressBar pbAirport;

    private ArrayList<Airport> airportsList;
    private static AirportListAdapter adapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_create_airport);

        fabAddAirport = (FloatingActionButton) findViewById(R.id.fab_add_airport);
        listViewAirport = (ListView) findViewById(R.id.lv_airport_list);
        pbAirport = (ProgressBar) findViewById(R.id.pb_airport);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("airport");
        airportsList = new ArrayList<Airport>();


        //On starting fetching data on notifying changes
        new FetchAirportDatabase().execute("");
        adapter = new AirportListAdapter(airportsList, getApplicationContext());

        fabAddAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAirportPage.this, ActivityAddAirport.class);
                startActivity(intent);
            }
        });


    }


    public class FetchAirportDatabase extends AsyncTask<String, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbAirport.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(String... strings) {

                    mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot each_airport : dataSnapshot.getChildren()){
                    String airportName = (String) each_airport.child("locationName").getValue();
                    String airportCode = (String) each_airport.child("airportCode").getValue();
                    String coordinates = (String) each_airport.child("coordinates").getValue();

                    airportsList.add(new Airport(airportName, airportCode, coordinates));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pbAirport.setVisibility(View.INVISIBLE);
            listViewAirport.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.airport_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.airport_page_refresh:{
                airportsList.clear();
                new FetchAirportDatabase().execute("");
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
