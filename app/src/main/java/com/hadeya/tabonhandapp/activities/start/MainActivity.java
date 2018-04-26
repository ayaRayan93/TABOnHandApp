package com.hadeya.tabonhandapp.activities.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.support.design.widget.NavigationView;
import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.activities.customers.CustomerMainActivity;
import com.hadeya.tabonhandapp.activities.items.ItemsActivity;

import static com.hadeya.tabonhandapp.activities.start.StarterActivity.languageType;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    Button btn_customers , btn_items;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btn_customers=(Button)findViewById(R.id.btn_customers);
        btn_customers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CustomerMainActivity.class));

            }
        });

        btn_items=(Button)findViewById(R.id.btn_items);
        btn_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ItemsActivity.class));

            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
            {
                logout();
                Intent main = new Intent("login");
                startActivity(main);
            }


            break;
            case R.id.aboutus:
            {
                if(languageType) {
                    Intent main = new Intent("aboutusAR");
                    startActivity(main);
                }
                else
                {
                    Intent main = new Intent("aboutusEN");
                    startActivity(main);
                }
            }


            break;
            case R.id.contact:
            {
                if(languageType) {
                    Intent main = new Intent("contactusAR");
                    startActivity(main);
                }
                else
                {
                    Intent main = new Intent("contactusEN");
                    startActivity(main);
                }
            }


            break;
            case R.id.arabic:
            {
                if(languageType)
                languageType=false;
                else
                    languageType=true;

            }


            break;
        }
        return true;
    }
}