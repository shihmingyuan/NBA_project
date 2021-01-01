package com.example.nba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class Developer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        /*----------------------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout4);
        navigationView = findViewById(R.id.nav_view4);
        toolbar = findViewById(R.id.toolbar4);
        /*----------------------------------------*/
        setSupportActionBar(toolbar);
        /*----------------------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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
            Intent intent = new Intent(Developer.this,Page.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.nav_profile){
            Intent intent = new Intent(Developer.this,Profile.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.nav_save){
            Intent intent = new Intent(Developer.this,Save.class);
            startActivity(intent);
            return true;
        }
        return true;
    }
}