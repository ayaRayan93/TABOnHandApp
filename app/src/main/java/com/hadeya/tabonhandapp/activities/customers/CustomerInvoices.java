package com.hadeya.tabonhandapp.activities.customers;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.adapters.CustomerInvoicesAdapter;
import com.hadeya.tabonhandapp.adapters.ItemInvoicesAdapter;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.models.CustomerInvoice;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.store.WriteDataToDB;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hadeya.tabonhandapp.store.DataBaseHelper.resetCustomerInvoices;
import static com.hadeya.tabonhandapp.store.DataBaseHelper.resetCustomers;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllCustomerForSalesPerson;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllCustomerInvoice;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getItemInvoices;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getLoginUser;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.downloadCustomer;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.downloadCustomerInvoices;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.uploade;

/**
 * Created by AyaAli on 21/03/2018.
 */

public class CustomerInvoices extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.recyclerViewInvoiceCustomer)
    RecyclerView mRecyclerView;


    @BindView(R.id.swipeRefreshInvoiceCustomer)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private Menu menu;
    protected CustomerInvoicesAdapter itemAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<CustomerInvoice> dataSet;
    Customer customer=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity8_customer_invoice_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        dataSet = new ArrayList<>();
        // dataSet=getAllItems(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewInvoiceCustomer);
        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefreshInvoiceCustomer);
//        mRecyclerView.setHasFixedSize(true);
        itemAdapter = new CustomerInvoicesAdapter(this,dataSet);
        mRecyclerView.setAdapter(itemAdapter);

        // Set the color scheme of the SwipeRefreshLayout by providing 4 color resource ids
        //noinspection ResourceAsColor
        mSwipeRefreshLayout.setColorScheme(
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent, R.color.colorPrimaryDark);


        mLayoutManager = new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if (!mSwipeRefreshLayout.isRefreshing())
        {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                initiateRefresh();

            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             customer = extras.getParcelable("customer");

        }

        final EditText search=(EditText)findViewById(R.id.searchCustomerInvoice);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        initiateRefresh();
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        List<CustomerInvoice> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (CustomerInvoice s : dataSet) {
            //if the existing elements contains the search input
            if (s.getInvoiceNo().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);

            }
        }
        itemAdapter.filterList(filterdNames);
    }



    public  void initiateRefresh()
    {
        if(customer!=null) {
            dataSet = getAllCustomerInvoice(this, customer.getId());
            if (dataSet.size()==0)
            {
                AlertDialog diaBox = AskOption(customer.getId(),this);
                diaBox.show();
            }
            else {
                itemAdapter.filterList(dataSet);
                onRefreshComplete();
            }
        }

    }
    public  void initiateList()
    {
        if(customer!=null) {
            dataSet = getAllCustomerInvoice(this, customer.getId());
            itemAdapter.filterList(dataSet);

            onRefreshComplete();
        }

    }
    private void onRefreshComplete()
    {
        mSwipeRefreshLayout.setRefreshing(false);

    }
    private AlertDialog AskOption(final String custCode,final CustomerInvoices customerInvoices)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Update")
                .setMessage("Do you want to Update Customer Invoice List")
                .setIcon(R.mipmap.logo)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        uploade(getBaseContext(),getLoginUser().get(0).getRepCodId());
                        mSwipeRefreshLayout.setRefreshing(true);
                        SQLiteDatabase sqlDB = WriteDataToDB.mdatabase.getWritableDatabase();
                        resetCustomerInvoices(sqlDB);
                        downloadCustomerInvoices(custCode,customerInvoices);
                        WriteDataToDB.storeAllInvoiceTypes();

                    }

                })



                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        mSwipeRefreshLayout.setRefreshing(false);
                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

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
