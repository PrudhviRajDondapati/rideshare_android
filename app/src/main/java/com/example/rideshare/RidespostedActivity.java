package com.example.rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RidespostedActivity extends AppCompatActivity {
    EditText ride_desc,from,to,seats,price,rating;
    Button add;
    ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);
        ride_desc =findViewById(R.id.ride_desc);
        from =findViewById(R.id.from);
        to =findViewById(R.id.to);
        price =findViewById(R.id.price);
        seats =findViewById(R.id.seats);
        rating =findViewById(R.id.rating);

        add = findViewById(R.id.add);
        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RidespostedActivity.this, NavActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Map<String,Object> map=new HashMap<>();
                map.put("ride_desc",ride_desc.getText().toString());
                map.put("from",from.getText().toString());
                map.put("to",to.getText().toString());
                map.put("rating",rating.getText().toString());
                map.put("price",price.getText().toString());
                map.put("seats",seats.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Rides").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                from.setText("");
                                to.setText("");
                                ride_desc.setText("");
                                rating.setText("");
                                price.setText("");
                                seats.setText("");
                                Toast.makeText(getApplicationContext(),"added to Ride list",Toast.LENGTH_LONG).show();
                                Intent add = new Intent(RidespostedActivity.this,RidesActivity.class);
                                startActivity(add);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Not added to property list",Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });


    }
}
