package com.example.student.skywalkerairlines;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ActivityLoginPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private final String MANAGER = "Manager";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //declare and initialize variables
        final TextView goToSignUp = findViewById(R.id.gotoSignUp);
        final TextInputLayout email = findViewById(R.id.emailLogin);
        final TextInputLayout password = findViewById(R.id.passwordLogin);
        final Button login = findViewById(R.id.btnLogin);

        //login user with email and password
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get email and password from input
                final String emailText = Objects.requireNonNull(email.getEditText()).getText().toString().trim();
                final String passwordText = Objects.requireNonNull(Objects.requireNonNull(password.getEditText()).getText().toString().trim());

                //check for validity
                if (emailText.isEmpty()) {
                    email.getEditText().setError("Email is required");
                    email.getEditText().requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                    email.getEditText().setError("Please enter a valid email");
                    email.getEditText().requestFocus();
                    return;
                }

                if (passwordText.isEmpty()) {
                    password.getEditText().setError("Password is required");
                    password.getEditText().requestFocus();
                    return;
                }

                //Authenticate Email and Password
                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    //If successful go to main activity

                                    //Completed: Check if the user is manager take it to manager dashboard otherwise move to crew dashboard
                                    mFirebaseDatabase = FirebaseDatabase.getInstance();
                                    mDatabaseReference = mFirebaseDatabase.getReference("employee");

                                    mDatabaseReference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                            for (DataSnapshot each : dataSnapshot.getChildren()) {
                                                String userName = (String) each.child("userName").getValue();

                                                if (userName.equalsIgnoreCase(emailText)) {
                                                    String type = (String) each.child("type").getValue();
                                                    if (type.equalsIgnoreCase(MANAGER)) {
                                                        Intent mainIntent = new Intent(ActivityLoginPage.this, MainActivity.class);
                                                        startActivity(mainIntent);
                                                        finish();
                                                    } else {
                                                        Intent mainIntent = new Intent(ActivityLoginPage.this, ActivityCrewDashboard.class);
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
                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("ActivityWelcome", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(ActivityLoginPage.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        goToSignUp.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Go to register to sign up!
                        Intent registerIntent = new Intent(ActivityLoginPage.this, ActivityRegisterPage.class);
                        startActivity(registerIntent);
                    }
                });
    }
}
