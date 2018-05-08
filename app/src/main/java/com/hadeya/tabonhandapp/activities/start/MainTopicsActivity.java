package com.hadeya.tabonhandapp.activities.start;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.store.CustomerContentProvider;
import com.hadeya.tabonhandapp.store.DataBaseHelper;
import com.hadeya.tabonhandapp.store.WriteDataToDB;

import static com.hadeya.tabonhandapp.activities.start.StarterActivity.languageType;
import static com.hadeya.tabonhandapp.store.DataBaseHelper.resetDataBase;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.downloadData;

/**
 * Created by AyaAli on 20/03/2018.
 */

public class MainTopicsActivity  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_1main_topic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Button btn=(Button)findViewById(R.id.btn_BasicData);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent main = new Intent("MainActivity");
                startActivity(main);
            }
        });
        Button btn1=(Button)findViewById(R.id.btn_transaction);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent main = new Intent("TransactionMain");
                startActivity(main);
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
            case R.id.changePassword:
            {
                logout();
                Intent main = new Intent("ChangePassword");
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
