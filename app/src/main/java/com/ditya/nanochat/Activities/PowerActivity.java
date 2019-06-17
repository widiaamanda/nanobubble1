package com.ditya.nanochat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.ditya.nanochat.R;
import com.google.firebase.auth.FirebaseAuth;

public class PowerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button tombollogout;
    private Button tombolberanda;
    private Button tombolkeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
        tombollogout =(Button)findViewById(R.id.logoutbutton);
        tombollogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(PowerActivity.this,LoginActivity.class )));
            }
        });
        tombolberanda =(Button)findViewById(R.id.berandabutton);
        tombolberanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(PowerActivity.this,AdminActivity.class )));
            }
        });
        tombolkeluar =(Button)findViewById(R.id.keluarbutton);
        tombolkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                moveTaskToBack(true);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity((new Intent(PowerActivity.this,AdminActivity.class )));
        } else if (id == R.id.nav_profile) {
            startActivity((new Intent(PowerActivity.this,ProfileActivity.class )));
        } else if (id == R.id.nav_settings) {
            startActivity((new Intent(PowerActivity.this,SettingsActivity.class )));
        } else if (id == R.id.nav_power) {
            startActivity((new Intent(PowerActivity.this,PowerActivity.class )));
        } else if (id == R.id.nav_analisa) {
            startActivity((new Intent(PowerActivity.this,Home.class )));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
