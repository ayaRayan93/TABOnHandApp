package com.hadeya.tabonhandapp.activities.customers;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


import com.hadeya.tabonhandapp.adapters.CustomerAdapter;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.store.CustomerContentProvider;
import com.hadeya.tabonhandapp.store.CustomerTable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllCustomerForSalesPerson;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.downloadData;

public class CustomerMainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private Menu menu;
    protected CustomerAdapter itemAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Customer> dataSet;
    private int flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity4_customer_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // navigationView.setNavigationItemSelectedListener(this);

        //downloadData();//for first time do load to database
        ImageButton AddNew=(ImageButton)findViewById(R.id.AddNew);
        AddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("AddNewCustomer");
                startActivity(intent);
            }
        });
        ImageButton update=(ImageButton)findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadData();
            }
        });

        dataSet = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mRecyclerView.setHasFixedSize(true);
        itemAdapter = new CustomerAdapter(this,dataSet);
        mRecyclerView.setAdapter(itemAdapter);

        // Set the color scheme of the SwipeRefreshLayout by providing 4 color resource ids
        //noinspection ResourceAsColor
        mSwipeRefreshLayout.setColorScheme(
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent, R.color.colorPrimaryDark);


        mLayoutManager = new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mSwipeRefreshLayout.setRefreshing(false);
        if (!mSwipeRefreshLayout.isRefreshing())
        {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        final EditText search=(EditText)findViewById(R.id.search);
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

        initiateRefresh(flag);
        //insertCustomersTolocalDB(dataSet);
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        List<Customer> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (Customer s : dataSet) {
            //if the existing elements contains the search input
            if (s.getCustName().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);

            }
        }
        itemAdapter.filterList(filterdNames);
    }



    public  void initiateRefresh(int i)
    {

        List<Customer> dataSet1= getAllCustomerForSalesPerson(this);
        itemAdapter.filterList(dataSet1);
        onRefreshComplete();

    }

    private void onRefreshComplete()
    {
        mSwipeRefreshLayout.setRefreshing(false);

    }
    public void insertCustomersTolocalDB(List<Customer> customers)
    {
        for (int i=0;i<customers.size();i++)
        {
            addCustomer(customers.get(i));
        }
    }

    public void addCustomer(Customer customer)
    {

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CustomerTable.CustomerCode, customer.getCustomerCode());
        values.put(CustomerTable.CustName, customer.getCustName());
        values.put(CustomerTable.StreetAra,customer.getStreetAra());
        values.put(CustomerTable.Classification,customer.getClassification());
        values.put(CustomerTable.PersonToConnect,customer.getPersonToConnect());
        values.put(CustomerTable.Tel,customer.getTel());
        values.put(CustomerTable.TAXID,customer.getTAXID());
        values.put(CustomerTable.Flag,"1");//
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        CustomerContentProvider moviesContentProvider=new CustomerContentProvider(this);
        moviesContentProvider.insert(CustomerContentProvider.CONTENT_URI_add,values);

    }
}
