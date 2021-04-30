package com.example.rideshare;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class SingleListActivity extends AppCompatActivity{
    private TextView ride_desc;
    private TextView from;
    private TextView to;
    private TextView seats;
    private TextView rating;
    private TextView price;
    Button book;
    FirebaseFirestore fStore;
    private Bundle extras;
    String ri_desc,ri_from,ri_to,ri_seats,ri_rating,ri_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_list);
        ride_desc = findViewById(R.id.ride_desc);
        from = findViewById(R.id.from);
        seats = findViewById(R.id.seats);
        to = findViewById(R.id.to);
        rating = findViewById(R.id.rating);
        price = findViewById(R.id.price);
        book = findViewById(R.id.book);


        ri_desc=getIntent().getStringExtra("ride_desc");
        ri_from=getIntent().getStringExtra("from");
        ri_to=getIntent().getStringExtra("to");
        ri_seats=getIntent().getStringExtra("seats");
        ri_rating=getIntent().getStringExtra("rating");
        ri_price=getIntent().getStringExtra("price");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> favmap = new HashMap<>();
                favmap.put("ride_desc",ri_desc);
                favmap.put("rating",ri_rating);
                favmap.put("price",ri_price);
                favmap.put("seats",ri_seats);
                favmap.put("from",ri_from);
                favmap.put("to",ri_to);
                FirebaseDatabase.getInstance().getReference().child("ridesopted").push()
                        .setValue(favmap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Ride Booked", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Inserted fail", Toast.LENGTH_SHORT).show();

                            }
                        });


            }
        });

        ride_desc.setText(ri_desc);
        from.setText(ri_from);
        to.setText(ri_to);
        seats.setText(ri_seats);
        rating.setText(ri_rating);
        price.setText(ri_price);
    }
}
