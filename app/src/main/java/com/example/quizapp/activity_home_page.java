package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.quizapp.databinding.ActivityHomePageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;


public class activity_home_page extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    ActivityHomePageBinding binding;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        /*
         drawer layout instance to toggle the menu icon to open
         drawer and back button to close drawer
                drawerLayout = findViewById(R.id.my_drawer_layout);
                actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
         pass the Open and Close toggle for the drawer layout listener
         to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        */


        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.content, new fragment_home());
//        transaction.commit();

//        binding.bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                switch (item.getItemId()) {
//                    case androidx.appcompat.R.id.home:
//                        //transaction.replace(R.id.content, new fragment_user());
//                        loadFragment(new fragment_home());
//                        break;
//                    case androidx.appcompat.R.id.:
//                        transaction.replace(R.id.content, new fragment_home());
//                        transaction.commit();
//                        break;
//                    case androidx.appcompat.R.id.home:
//                        transaction.replace(R.id.content, new fragment_setting());
//                        transaction.commit();
//                        break;
//                }
//                return true;
//            }
//        });
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomBar);
        bottomNavigationView.setSelectedItemId(R.id.ic_baseline_home_24);

        loadFragment(new fragment_home());

        binding.bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_baseline_person_24:
                        loadFragment(new fragment_user());
                        break;
                    case R.id.ic_baseline_home_24:
                        loadFragment(new fragment_home());
                        break;
                    case R.id.ic_baseline_settings_24:
                        loadFragment(new fragment_setting());
                        break;
                }
                return true;
            }
        });
    }
//    fragment_home firstFragment = new fragment_home();
//    fragment_user secondFragment = new fragment_user();
//    fragment_setting thirdFragment = new fragment_setting();

    //@Override
    //public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//        switch (item.getItemId()) {
//            case R.id.person:
//                getSupportFragmentManager().beginTransaction().replace(R.id.renderText, firstFragment).commit();
//                return true;
//
//            case R.id.home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.renderText, secondFragment).commit();
//                return true;
//
//            case R.id.settings:
//                getSupportFragmentManager().beginTransaction().replace(R.id.renderText, thirdFragment).commit();
//                return true;
//        }
      //  return false;
    //}


    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void loadFragment(Fragment fragment){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, fragment);
        ft.commit();
    }



}


