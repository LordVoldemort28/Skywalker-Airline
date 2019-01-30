package com.example.student.skywalkerairlines;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityLogs extends AppCompatActivity {


    private TextView logDisplay;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        logDisplay = (TextView) findViewById(R.id.tv_log_display);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        updateLogs();
    }

    public void updateLogs() {
       logDisplay.setText("");
        mFirebaseDatabase.getReference("logs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot each_logs : dataSnapshot.getChildren()){
                    String log = (String)each_logs.getValue();
                    logDisplay.append(log+"\n");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logs_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.logs_page_refresh:{
                updateLogs();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
