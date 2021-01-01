package com.example.nba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.NavigableSet;

public class Page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static String serverAddress = "192.168.10.88:580";
    static String id = "";
    static String password = "";
    static String response = "";
    static int win = 0;
    static int lose = 0;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        /*----------------------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        /*----------------------------------------*/
        setSupportActionBar(toolbar);
        /*----------------------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        /* got intent */
        Intent myIntent = getIntent(); // gets the previously created intent
        Page.id = myIntent.getStringExtra("id");
        Page.password = myIntent.getStringExtra("password");
        Page.win = myIntent.getIntExtra("win", -1);
        Page.lose = myIntent.getIntExtra("lose", -1);

        Log.d("KK", Page.id);
        Log.d("KK", Page.password);
        Log.d("KK", String.valueOf(Page.win));
        Log.d("KK", String.valueOf(Page.lose));


        /*Game*/
        ImageButton btnA = findViewById(R.id.btnA);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page.this,Calendar.class);
                startActivity(intent);
            }
        });
        /*Player*/
        ImageButton btnB = findViewById(R.id.btnB);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page.this,Depot.class);


                intent.putExtra("id", Page.id);
                intent.putExtra("password", Page.password);
                intent.putExtra("win", Page.win);
                intent.putExtra("lose", Page.lose);

                startActivity(intent);
            }
        });
        /*Draw*/
        ImageButton btnC = findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page.this,Drawcard.class);

                intent.putExtra("id", Page.id);
                intent.putExtra("password", Page.password);
                intent.putExtra("win", Page.win);
                intent.putExtra("lose", Page.lose);

                startActivity(intent);
            }
        });
        /*Developer*/
        ImageButton btnD = findViewById(R.id.btnD);
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page.this,Developer.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer((GravityCompat.START));
        }
        else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.nav_home){
            Intent intent = new Intent(Page.this,Page.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.nav_profile){
            Intent intent = new Intent(Page.this,Profile.class);

            intent.putExtra("id", Page.id);
            intent.putExtra("password", Page.password);
            intent.putExtra("win", Page.win);
            intent.putExtra("lose", Page.lose);

            startActivity(intent);
            return true;
        }else if(id==R.id.nav_save){
            Intent intent = new Intent(Page.this,Save.class);
            startActivity(intent);
            return true;
        }
        return true;
    }
}




