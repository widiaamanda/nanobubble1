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
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ditya.nanochat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button tombollogout;
    private Button tombolberanda;
    private Button tombolkeluar;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tombollogout =(Button)findViewById(R.id.logoutbutton);
        tombollogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity((new Intent(ProfileActivity.this,LoginActivity.class )));
            }
        });

//        tombollogout =(Button)findViewById(R.id.logoutbutton);
//        tombollogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity((new Intent(ProfileActivity.this,LoginActivity.class )));
//            }
//        });
        tombolberanda =(Button)findViewById(R.id.berandabutton);
        tombolberanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(ProfileActivity.this,AdminActivity.class )));
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


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();

        ImageView Pic1 = findViewById(R.id.foto);
        ImageView Pic2 = findViewById(R.id.imageView19);
        ImageView Pic3 = findViewById(R.id.imageView20);
        ImageView Pic4 = findViewById(R.id.imageView21);
        TextView nama1 = findViewById(R.id.textViewPemilik);
        TextView nama2 = findViewById(R.id.textView11);

        // we will use Glide to load User Image
        // import library

        Glide.with(this).load(currentUser.getPhotoUrl()).into(Pic1);
        Glide.with(this).load(currentUser.getPhotoUrl()).into(Pic2);
        Glide.with(this).load(currentUser.getPhotoUrl()).into(Pic3);
        Glide.with(this).load(currentUser.getPhotoUrl()).into(Pic4);
        nama1.setText(currentUser.getDisplayName());
        nama2.setText(currentUser.getDisplayName());


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home2_drawer, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity((new Intent(ProfileActivity.this,AdminActivity.class )));
        } else if (id == R.id.nav_profile) {
            startActivity((new Intent(ProfileActivity.this,ProfileActivity.class )));
        } else if (id == R.id.nav_power) {
            startActivity((new Intent(ProfileActivity.this,PowerActivity.class )));
        } else if (id == R.id.nav_analisa) {
            startActivity((new Intent(ProfileActivity.this,Home.class )));
        } else if (id == R.id.nav_bantuan) {
            startActivity((new Intent(ProfileActivity.this,Navigasi2Activity.class )));
        } else if (id == R.id.logoutbutton) {
            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(this, LoginActivity.class);
            startActivity(loginActivity);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateNavHeader()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUserName = headerView.findViewById(R.id.nav_username);
        TextView navUserMail = headerView.findViewById(R.id.nav_user_mail);
        ImageView navUserPhoto = headerView.findViewById(R.id.nav_user_photo);

        navUserMail.setText(currentUser.getEmail());
        navUserName.setText(currentUser.getDisplayName());

        // we will use Glide to load User Image
        // import library

        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);



    }
}