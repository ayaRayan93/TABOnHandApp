package com.hadeya.tabonhandapp.activities.start;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.store.CustomerContentProvider;
import com.hadeya.tabonhandapp.store.DataBaseHelper;
import com.hadeya.tabonhandapp.store.WriteDataToDB;

import static com.hadeya.tabonhandapp.store.DataBaseHelper.resetDataBase;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.downloadData;

/**
 * Created by AyaAli on 20/03/2018.
 */

public class MainTopicsActivity  extends AppCompatActivity {


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
       // navigationView.setBackgroundResource(R.color.customColor);
       // navigationView.setItemTextColor(getColorStateList(11));
        // navigationView.setNavigationItemSelectedListener(this);

        //CustomerContentProvider c=new CustomerContentProvider(this);

        DataBaseHelper dataBaseHelper=new DataBaseHelper(this);
        WriteDataToDB.mdatabase=dataBaseHelper;
        SQLiteDatabase sqlDB = dataBaseHelper.getWritableDatabase();
        resetDataBase(sqlDB);
        downloadData();
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
}
