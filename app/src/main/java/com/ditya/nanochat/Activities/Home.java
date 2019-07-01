package com.ditya.nanochat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ditya.nanochat.Fragments.HomeFragment;
import com.ditya.nanochat.Fragments.ProfileFragment;
import com.ditya.nanochat.Fragments.SettingsFragment;
import com.ditya.nanochat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button tombollogout;
    private Button tombolberanda;
    private Button tombolkeluar;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    TextView mConditionTextView;
    TextView mPemilikTextView;
    TextView mNamaKolamTextView;
    TextView mJumTebaranTextView;
    TextView mLokasiTextView;
    TextView mPHTextView;
    TextView mSuhuTextView;


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mRootRef.child("number");
    DatabaseReference mPemilikRef = mRootRef.child("pemilik");
    DatabaseReference mNamaKolamRef = mRootRef.child("namakolam");
    DatabaseReference mJumTebaranRef = mRootRef.child("jumtebaran");
    DatabaseReference mLokasiRef = mRootRef.child("lokasi");
    DatabaseReference mPHRef = mRootRef.child("ph");
    DatabaseReference mSuhuRef = mRootRef.child("suhu");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        tombollogout =(Button)findViewById(R.id.nav_signout);
        tombollogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity((new Intent(Home.this,LoginActivity.class )));
            }
        });
        tombolberanda =(Button)findViewById(R.id.berandabutton);
        tombolberanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(Home.this,AdminActivity.class )));
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


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ini

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        mConditionTextView = (TextView)findViewById(R.id.textViewData);
        mPemilikTextView = (TextView)findViewById(R.id.textViewPemilik);
        mNamaKolamTextView = (TextView)findViewById(R.id.textViewNamaKolam);
        mJumTebaranTextView = (TextView)findViewById(R.id.textViewJumTebaran);
        mLokasiTextView = (TextView)findViewById(R.id.textViewLokasi);
        mPHTextView = (TextView)findViewById(R.id.textViewPH);
        mSuhuTextView = (TextView)findViewById(R.id.textViewSuhu);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();

        ImageView Pic1 = findViewById(R.id.foto);
        TextView nama1 = findViewById(R.id.textViewPemilik);


        // we will use Glide to load User Image
        // import library

        Glide.with(this).load(currentUser.getPhotoUrl()).into(Pic1);
        nama1.setText(currentUser.getDisplayName());

    }

    @Override
    public void onStart()
    {
        super.onStart();


        mNamaKolamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mNamaKolamTextView.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mJumTebaranRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mJumTebaranTextView.setText(text);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mLokasiRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mLokasiTextView.setText(text);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mPHRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mPHTextView.setText(text);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mSuhuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mSuhuTextView.setText(text);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mConditionTextView.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
            startActivity((new Intent(Home.this,AdminActivity.class )));
        } else if (id == R.id.nav_profile) {
            startActivity((new Intent(Home.this,ProfileActivity.class )));
        } else if (id == R.id.nav_settings) {
            startActivity((new Intent(Home.this,SettingsActivity.class )));
        } else if (id == R.id.nav_power) {
            startActivity((new Intent(Home.this,PowerActivity.class )));
        } else if (id == R.id.nav_analisa) {
            startActivity((new Intent(Home.this,Home.class )));
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


//        Pic1 = navUserPhoto;


    }
}
