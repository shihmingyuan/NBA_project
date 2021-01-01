package com.example.nba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class Drawcard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static String serverAddress = "192.168.10.88:580";
    static String id = "";
    static String password = "";
    static String response = "";



    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawcard);

        /* got intent */
        Intent myIntent = getIntent(); // gets the previously created intent
        id = myIntent.getStringExtra("id");
        password = myIntent.getStringExtra("password");

        /*十連抽*/
        Button oneten = findViewById(R.id.oneten);
        oneten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Drawcard.this,Card.class);
                intent.putExtra("id", id);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
        /*----------------------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout2);
        navigationView = findViewById(R.id.nav_view2);
        toolbar = findViewById(R.id.toolbar2);
        /*----------------------------------------*/
        setSupportActionBar(toolbar);
        /*----------------------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        /*----------------------------------------*/
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
            Intent intent = new Intent(Drawcard.this,Page.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.nav_profile){
            Intent intent = new Intent(Drawcard.this,Profile.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.nav_save){
            Intent intent = new Intent(Drawcard.this,Save.class);
            startActivity(intent);
            return true;
        }
        return true;
    }
}