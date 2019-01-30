package com.example.student.skywalkerairlines;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SplashActivity extends AppCompatActivity {
    //variables
    private RelativeLayout rlayout;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private static final String MANAGER = "Manager";

    private final static int SECOND_DELAY = 1000;

    private String email = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //set values to variables
        rlayout = (RelativeLayout) findViewById(R.id.rlay);

        //gif = (GifTextView) findViewById(R.id.gif);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent sharedIntent = new Intent(SplashActivity.this, ActivityLoginPage.class);

                //If user is already logged in then send them to MainActivty
                if(firebaseUser != null){

                   email =  (String)firebaseUser.getEmail();

                    //TODO: Check if the user is manager take it to manager dashboard otherwise move to crew dashboard
                    mFirebaseDatabase = FirebaseDatabase.getInstance();
                    mDatabaseReference = mFirebaseDatabase.getReference("employee");

                        mDatabaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                for (DataSnapshot each : dataSnapshot.getChildren()) {
                                        String userName = (String) each.child("userName").getValue();

                                    if (userName.equalsIgnoreCase(email)) {
                                        String type = (String) each.child("type").getValue();
                                        if (type.equalsIgnoreCase(MANAGER)) {
                                            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                                            startActivity(mainIntent);
                                            finish();
                                        } else {
                                            Intent mainIntent = new Intent(SplashActivity.this, ActivityCrewDashboard.class);
                                            startActivity(mainIntent);
                                            finish();
                                        }

                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getBaseContext(), "Employee not found", Toast.LENGTH_LONG).show();
                            }
                        });


                    //Otherwise go to Welcome Activity
//                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
//                    startActivity(mainIntent);
//                    finish();
                }else {
                    startActivity(sharedIntent);
                    finish();
                }

            }
        }, 3 * SECOND_DELAY);
    }
}
