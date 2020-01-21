package com.example.striblood;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ActivityPrincipale extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    Button b;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);
        loadFragment(new HomeFragment());

        //b = (Button) findViewById(R.id.invite);
        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_dashboard:
                fragment = new DashboardFragment();
                break;

            case R.id.navigation_notifications:
                fragment = new NotificationsFragment();
                break;

        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    public void reuest(View view) {
    }

    public void setting(View view) {
        startActivity(new Intent(this, SettingActivity.class));
    }

    public void invite(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello friends, I hope u r doing OK,");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void search(View view) {
        startActivity(new Intent(ActivityPrincipale.this, SearchActivity.class));
    }

    public void request(View view) {
        startActivity(new Intent(ActivityPrincipale.this, ActivityRequest.class));
    }

    public void pic(View view) {

    }

    public void listRequest(View view) {
        startActivity(new Intent(ActivityPrincipale.this, ListeRequestActivity.class));
    }
}
