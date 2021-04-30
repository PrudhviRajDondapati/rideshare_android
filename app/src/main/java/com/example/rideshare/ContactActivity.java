package com.example.rideshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class ContactActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    private EditText contactFullName;
    private EditText contactEmail;
    private EditText contactPhone, contactSubject;
    private EditText contactMessage;
    private String name, email, phone, subject, message;
    Button btnSubmit;
    ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        contactFullName = findViewById(R.id.contactFullName);
        contactEmail = findViewById(R.id.contactEmail);
        contactPhone = findViewById(R.id.contactPhone);
        contactSubject = findViewById(R.id.contactSubject);
        contactMessage = findViewById(R.id.contactMessage);
        btnSubmit = findViewById(R.id.btnSubmit);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                contactPhone.setText(documentSnapshot.getString("number"));
                contactFullName.setText(documentSnapshot.getString("name"));
                contactEmail.setText(documentSnapshot.getString("email"));
            }
        });
        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ContactActivity.this, NavActivity.class);
                startActivity(myIntent);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact(); // Call when button is clicked
            }
        });
    }

    public void contact(){
        intialize(); //intialize the input to stringvariables
        if(!validate()){
            Toast.makeText(this,"Please enter all details",Toast.LENGTH_SHORT).show();
        }
        else {
            onSubmit();
        }
    }
    public void onSubmit(){
        //TODO what will go after the valid input
        Toast.makeText(this,"Submited Successfully",Toast.LENGTH_SHORT).show();
        Intent contactsubmit = new Intent(ContactActivity.this,RidesActivity.class);
        startActivity(contactsubmit);


    }
    public boolean validate(){
        boolean valid = true;
        if(name.isEmpty()|| name.length()>20){
            contactFullName.setError("Please enter valid name");
            valid =false;
        }
        if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            contactEmail.setError("Please Enter Valid Email Address");
            valid = false;
        }
        if(phone.isEmpty()|| phone.length()>10){
            contactPhone.setError("Please Enter Valid Phone Number");
            valid=false;
        }
        if(subject.isEmpty()){
            contactSubject.setError("Please Enter Subject");
            valid = false;
        }
        if(message.isEmpty()){
            contactMessage.setError("Please enter Message");
            valid = false;
        }
        return valid;
    }
    public void intialize(){

        name = contactFullName.getText().toString().trim();
        email = contactEmail.getText().toString().trim();
        phone = contactPhone.getText().toString().trim();
        subject = contactSubject.getText().toString().trim();
        message = contactMessage.getText().toString().trim();
    }

}