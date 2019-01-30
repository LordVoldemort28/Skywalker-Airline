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

import java.util.ArrayList;

public class ActivityAircraftPage extends AppCompatActivity {

    private ProgressBar pbAircraft;
    private ListView lvAircraftList;
    private FloatingActionButton fabAddAircraft;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private static AircraftListAdapter adapter;

    private ArrayList<Aircraft> aircraftList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_aircraft);

        pbAircraft = (ProgressBar) findViewById(R.id.pb_aircraft);
        lvAircraftList = (ListView) findViewById(R.id.lv_aircraft_list);
        fabAddAircraft = (FloatingActionButton) findViewById(R.id.fab_add_aircraft);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("aircraft");

        aircraftList = new ArrayList<Aircraft>();

        new FetchAircraftDatabase().execute("");


        fabAddAircraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addAircraft = new Intent(ActivityAircraftPage.this, ActivityAddAircraft.class);
                startActivity(addAircraft);
            }
        });

    }

    public class FetchAircraftDatabase extends AsyncTask<String, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbAircraft.setVisibility(View.VISIBLE);
            adapter = new AircraftListAdapter(aircraftList, getApplicationContext());

        }

        @Override
        protected Void doInBackground(String... strings) {
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot each_aircraft : dataSnapshot.getChildren()){

                        String aircraftName = (String) each_aircraft.child("aircraftName").getValue();
                        String location = (String) each_aircraft.child("location").getValue();
                        String type = (String) each_aircraft.child("type").getValue();
                        aircraftList.add(new Aircraft(aircraftName, location, type));

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
            pbAircraft.setVisibility(View.INVISIBLE);
            lvAircraftList.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.aircraft_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.aircraft_page_refresh:{
                aircraftList.clear();
                new FetchAircraftDatabase().execute("");
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
