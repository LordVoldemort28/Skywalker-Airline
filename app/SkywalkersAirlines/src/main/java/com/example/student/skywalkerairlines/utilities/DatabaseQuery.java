package com.example.student.skywalkerairlines.utilities;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.student.skywalkerairlines.Airport;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DatabaseQuery {

        private FirebaseFirestore mDatabase;
        private CollectionReference mReference;

        private ArrayList<Airport> airportsList;



        public void getAiportList(){

            airportsList = new ArrayList<>();

        mDatabase = FirebaseFirestore.getInstance();
        mReference = mDatabase.collection("airport");

        mReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        Airport airport = documentSnapshot.toObject(Airport.class);
                        airportsList.add(airport);
                    }
                }else {
                    Log.d("DatabaseQuery", "Unable to fetch airports");
                }
            }
        });
        }

    public ArrayList<Airport> getAirportsList() {
        getAiportList();
        return airportsList;
    }

    public void setAirportsList(ArrayList<Airport> airportsList) {

        this.airportsList = airportsList;
    }
}
