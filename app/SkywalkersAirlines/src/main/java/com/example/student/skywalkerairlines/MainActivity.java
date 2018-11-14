package com.example.student.skywalkerairlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {


    private FirebaseFirestore mFirebaseFirestore;

    private DatabaseReference mDatabaseReference;

    private TextView mUsernameDisplay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsernameDisplay = (TextView) findViewById(R.id.dashboard_username_check);

        Intent loginPageIntent = getIntent();

        if(loginPageIntent.hasExtra(Intent.EXTRA_TEXT)){
            String loginEmail = loginPageIntent.getStringExtra(Intent.EXTRA_TEXT);
            mUsernameDisplay.setText(loginEmail);

        }

    }
}
