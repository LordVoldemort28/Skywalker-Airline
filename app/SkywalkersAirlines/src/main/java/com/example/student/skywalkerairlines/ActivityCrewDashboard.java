package com.example.student.skywalkerairlines;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityCrewDashboard extends AppCompatActivity {

    private TextView mUserName;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_crew_dasboard);

        mUserName = findViewById(R.id.crew_dashboard_username_check);

        mUserName.setText(mFirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.crew_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        //Completed:  Implement the logout button to reach to login page
        switch (id){
            case R.id.logout:{
                mFirebaseAuth.getInstance().signOut();
                Intent backToLogin = new Intent(ActivityCrewDashboard.this, ActivityLoginPage.class);
                startActivity(backToLogin);
                finish();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
