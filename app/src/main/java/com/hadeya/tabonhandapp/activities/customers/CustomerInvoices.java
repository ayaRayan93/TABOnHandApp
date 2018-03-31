package com.hadeya.tabonhandapp.activities.customers;

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
import android.widget.EditText;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.adapters.CustomerInvoicesAdapter;
import com.hadeya.tabonhandapp.adapters.ItemInvoicesAdapter;
import com.hadeya.tabonhandapp.models.CustomerInvoice;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllCustomerInvoice;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getCustomerInvoices;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getItemInvoices;

/**
 * Created by AyaAli on 21/03/2018.
 */

public class CustomerInvoices extends AppCompatActivity {

    @BindView(R.id.recyclerViewInvoiceCustomer)
    RecyclerView mRecyclerView;


    @BindView(R.id.swipeRefreshInvoiceCustomer)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private Menu menu;
    protected CustomerInvoicesAdapter itemAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<CustomerInvoice> dataSet;

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
        // navigationView.setNavigationItemSelectedListener(this);


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
        //insertCustomersTolocalDB(dataSet);
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

        dataSet= getAllCustomerInvoice(this);
        itemAdapter.filterList(dataSet);
        onRefreshComplete();

    }
    private void onRefreshComplete()
    {
        mSwipeRefreshLayout.setRefreshing(false);

    }
}
