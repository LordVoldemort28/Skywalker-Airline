package com.example.student.skywalkerairlines;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityRegisterPage extends AppCompatActivity implements View.OnClickListener {


    private FirebaseFirestore mFirebaseFirestore;
    private CollectionReference mCollectionReference;
    private FirebaseAuth mFirebaseAuth;

    //View intialization
    private EditText etEmail;
    private EditText etPassword;

    private Switch swIsManager;
    private Button bRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);

        swIsManager = (Switch) findViewById(R.id.switch_isManager);
        bRegister = (Button) findViewById(R.id.next_detail_page);

        //Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();


        bRegister.setOnClickListener(this);
    }


    private void registerUser(){
        //get email and password from input
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        //check validity
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etPassword.setError("Minimum length of password should be 6");
            etPassword.requestFocus();
            return;
        }


        //Registering the user
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Successfull Registered", Toast.LENGTH_SHORT).show();

                    String valueToPass;
                    Intent profilePage = new Intent(ActivityRegisterPage.this, ProfileRegister.class);


                    //Person is Manager
                    if(swIsManager.isChecked() == true){

                        //TODO: Call query to add person
                        valueToPass = "1";

                    }else { valueToPass = "0"; }


                    profilePage.putExtra("MANAGER", valueToPass);
                    profilePage.putExtra("EMAIL", email);
                    startActivity(profilePage);
                    finish();

                }
                else{

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered, sign in", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }//Else for collision damage

            }//End of complete listner
        });

    }

    @Override
    public void onClick(View v) {
       int id = v.getId();

       switch (id){
           case R.id.next_detail_page:{
               registerUser();
               break;
           }
       }
    }
}//End of class
