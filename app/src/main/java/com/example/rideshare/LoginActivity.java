package com.example.rideshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    public EditText email, password;
    Button signin;
    TextView tvsignup;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.user_email);
        password = findViewById(R.id.user_password);
        signin = findViewById(R.id.button);
        tvsignup = findViewById(R.id.textView);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "you are logged in ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, NavActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Please login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emil_id = email.getText().toString();
                String pwd = password.getText().toString();
                if (emil_id.isEmpty()) {
                    email.setError("please enter email id");
                    email.requestFocus();

                } else if (pwd.isEmpty()) {
                    password.setError("plaese enter password");
                    password.requestFocus();

                } else if (emil_id.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(emil_id.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(emil_id, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "error Occurred Please login again", Toast.LENGTH_SHORT).show();
                            } else {
                                /////////111111
                                Intent inToHome = new Intent(LoginActivity.this, NavActivity.class);
                                startActivity(inToHome);
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "error occured", Toast.LENGTH_SHORT).show();
                }

            }

        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intosignup = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intosignup);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}