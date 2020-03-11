package com.example.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private TextView navheaderuser;
    public String user_name="";
    MediaPlayer mp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        mp = MediaPlayer.create(this, R.raw.bgmusic);
        mp.start();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            user_name = bundle.getString("user_name");

        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.draw_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View v = navigationView.getHeaderView(0);
        navheaderuser = v.findViewById(R.id.nav_username);
        navheaderuser.setText(user_name);
        navigationView.setNavigationItemSelectedListener(this);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

                mp.start();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                mp.pause();
                break;

            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                mp.pause();
                break;

            case R.id.nav_exit:
                mp.pause();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);
                builder.setMessage(getString(R.string.sdialogmessage))
                        .setTitle(getString(R.string.sdialogtitle))
                        .setPositiveButton(getString(R.string.leave), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(MainMenu.this, getString(R.string.signout), Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainMenu.this, MainActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                                    finish();

                            }
                        })
                        .setNegativeButton(getString(R.string.stay), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            mp.start();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


        }


        drawer.closeDrawer(GravityCompat.START);

        return true;
        }

    @Override
    public void onBackPressed(){
            if (drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
    }
}