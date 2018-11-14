package com.example.student.skywalkerairlines;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashActivity extends AppCompatActivity {
    //variables
    private RelativeLayout rlayout;
    private FirebaseUser firebaseUser;

    private static final int SECOND_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //set values to variables
        rlayout = (RelativeLayout) findViewById(R.id.rlay);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent sharedIntent = new Intent(SplashActivity.this, ActivityLogin.class);

                //If user is already logged in then send them to MainActivty
                if(firebaseUser != null){
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                    //Otherwise go to Welcome Activity
                }else {
                    startActivity(sharedIntent);
                    finish();
                }

            }
        }, 3 * SECOND_DELAY);
    }
}
