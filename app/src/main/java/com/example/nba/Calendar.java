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
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Calendar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CalendarView calendarView;
    TextView myDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        /*----------------------------------------*/

        Button enter = findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendar.this,Game.class);
                startActivity(intent);
            }
        });
        /*----------------------------------------*/
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView)findViewById(R.id.textView14);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view,  int i, int i1, int i2) {
                String date = (i1+1)+ "/" + i2 + "/" + i;
                myDate.setText(date);
            }
        });
        /*----------------------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout6);
        navigationView = findViewById(R.id.nav_view6);
        toolbar = findViewById(R.id.toolbar6);
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
            Intent intent = new Intent(Calendar.this,Page.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.nav_profile){
            Intent intent = new Intent(Calendar.this,Profile.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.nav_save){
            Intent intent = new Intent(Calendar.this,Save.class);
            startActivity(intent);
            return true;
        }
        return true;
    }
}