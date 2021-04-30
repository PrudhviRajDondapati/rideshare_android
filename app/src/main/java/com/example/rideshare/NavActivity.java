package com.example.rideshare;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.propertyapp.HelperClasses.adaptertoplist;
//import com.example.propertyapp.HelperClasses.toplisthelper;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class NavActivity extends AppCompatActivity {

    RecyclerView phoneRecycler;
    RecyclerView.Adapter adapter;

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    Button viewlisting;

    //private DrawerLayout drawer;
    //Button logout;
    //private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        viewlisting =findViewById(R.id.viewlisting);
        viewlisting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listing = new Intent (NavActivity.this,RidesActivity.class);
                startActivity(listing);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_Rides: {
                        Intent myIntent = new Intent(NavActivity.this, RidesActivity.class);
                        startActivity(myIntent);
                        navigationView.setCheckedItem(R.id.nav_view);
                        break;
                    }
                    case R.id.nav_contactus:
                        Intent contact = new Intent(NavActivity.this,ContactActivity.class);
                        startActivity(contact);

                        break;
                    case R.id.nav_SelectedRides:
                        Intent fav = new Intent(NavActivity.this,SelectedridesActivity.class);
                        startActivity(fav);
                        break;
                    case R.id.nav_RidesPosted:
                        Intent add = new Intent(NavActivity.this,RidespostedActivity.class);
                        startActivity(add);
                        break;
                    case R.id.nav_About:
                        Intent about = new Intent(NavActivity.this,AboutActivity.class);
                        startActivity(about);
                        break;
                    case R.id.nav_profile:
                        Intent profile = new Intent(NavActivity.this,Myprofile.class);
                        startActivity(profile);


                }
                return true;
            }
        });


    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }




}