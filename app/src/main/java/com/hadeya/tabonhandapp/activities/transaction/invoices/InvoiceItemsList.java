package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.models.Invoice;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;

public class InvoiceItemsList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Invoice invoice;
    @BindView(R.id.invoiceNo)TextView invoiceNo;
    @BindView(R.id.customerName)TextView customerName;
    @BindView(R.id.date)TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity13_invoice_item_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            invoice = extras.getParcelable("invoice");
            invoiceNo.setText(invoice.getInvoiceNo());
            customerName.setText(invoice.getCustomer().getCustName());
            date.setText(invoice.getInvoiceDate());
        }

        Button additem = (Button) findViewById(R.id.additem);
        additem.setOnClickListener(new View.OnClickListener()
        {
          @Override
          public void onClick (View v){

          Intent main = new Intent("ItemList");
          main.putExtra("invoice",invoice);
          startActivity(main);
          }
        }

        );

        ButterKnife.bind(this);
        Button saveInvoice = (Button) findViewById(R.id.save);
        saveInvoice.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent main = new Intent("PrintInvoice");
                startActivity(main);
            }
        }

        );

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
