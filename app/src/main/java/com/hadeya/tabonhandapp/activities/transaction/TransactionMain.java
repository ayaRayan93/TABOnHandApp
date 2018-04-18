package com.hadeya.tabonhandapp.activities.transaction;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.Toast;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.store.DataBaseHelper;
import com.hadeya.tabonhandapp.store.WriteDataToDB;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.downloadData;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.uploadInvoice;

/**
 * Created by AyaAli on 27/03/2018.
 */

public class TransactionMain  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9_transaction_main);
        final Context mContext=this;
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
        navigationView.setNavigationItemSelectedListener(this);

        //CustomerContentProvider c=new CustomerContentProvider(this);

        ImageButton updateInvoice=(ImageButton)findViewById(R.id.updateInvoice);
        updateInvoice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               try {

                   uploadInvoice(getBaseContext(), "13007");
                   DataBaseHelper dataBaseHelper=new DataBaseHelper(mContext);
                   WriteDataToDB.mdatabase=dataBaseHelper;
                   SQLiteDatabase sqlDB = dataBaseHelper.getWritableDatabase();
                   DataBaseHelper.resetInvoice(sqlDB);
                   Toast.makeText(TransactionMain.this, "Upload Done", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(TransactionMain.this, "There is no invoice to uploade", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button btn=(Button)findViewById(R.id.btn_addInvoice);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent main = new Intent("CustomerList");
                startActivity(main);
            }
        });
        Button btn1=(Button)findViewById(R.id.btn_transfer);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent main = new Intent("Transfer");
                startActivity(main);
            }
        });
        Button btn2=(Button)findViewById(R.id.btn_receipt);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent main = new Intent("Receipt");
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
        }
        return true;
    }
}
