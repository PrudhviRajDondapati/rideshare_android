package com.example.rideshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.LogDescriptor;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public EditText email, password,name,number;
    Button signup;
    TextView tvsignup;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fStore = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.user_email);
        name= findViewById(R.id.user_name);
        password = findViewById(R.id.user_password);
        signup = findViewById(R.id.button);
        tvsignup=findViewById(R.id.textView);
        number = findViewById(R.id.user_phone);

        if(mFirebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this,NavActivity.class));
            finish();
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fName = name.getText().toString();
                final String emil_id = email.getText().toString();
                String pwd = password.getText().toString();
                final String num = number.getText().toString();
                if (fName.isEmpty()){
                    name.setError("please enter name");
                    name.requestFocus();
                }
                else if (emil_id.isEmpty()) {
                    email.setError("please enter email id");
                    email.requestFocus();

                }
                else if (num.isEmpty()) {
                    number.setError("plaese enter phone number");
                    number.requestFocus();

                }
                else if (num.length() < 10) {
                    number.setError("phone number should have 10 numbers");
                    number.requestFocus();

                }
                else if (num.length()  > 10) {
                    number.setError("phone number should have 10 numbers");
                    number.requestFocus();

                }else if (pwd.isEmpty()) {
                    password.setError("plaese enter password");
                    password.requestFocus();

                } else if (emil_id.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(emil_id.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(emil_id,pwd)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(MainActivity.this,"sign up failed try again ",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        userID = mFirebaseAuth.getCurrentUser().getUid();
                                        DocumentReference documentReference = fStore.collection("users").document(userID);
                                        Map<String,Object> user = new HashMap<>();
                                        user.put("name",fName);
                                        user.put("email",emil_id);
                                        user.put("number",num);
                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("TAG", "onSuccess: user created for  "+ userID);
                                            }
                                        });
                                        startActivity(new Intent(MainActivity.this,NavActivity.class));
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(MainActivity.this,"error occured",Toast.LENGTH_SHORT).show();
                }

            }
        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }


}