package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.adapters.ItemsAdapter;
import com.hadeya.tabonhandapp.adapters.transactionItemAdapter;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllItems;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;

/**
 * Created by AyaAli on 05/04/2018.
 */

public class ItemList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.recyclerViewTransactionItem)
    RecyclerView mRecyclerView;


    @BindView(R.id.swipeRefreshTransactionItem)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private Menu menu;
    protected transactionItemAdapter itemAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Item> dataSet;
    Invoice invoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity15_invoice_items_list_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            invoice = extras.getParcelable("invoice");
        }

        dataSet = new ArrayList<>();
        // dataSet=getAllItems(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewTransactionItem);
        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefreshTransactionItem);
       mRecyclerView.setHasFixedSize(true);
        itemAdapter = new transactionItemAdapter(this,dataSet,invoice,this);
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

        Button back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent("InvoiceItemsList");
                main.putExtra("invoice",invoice);
                startActivity(main);
            }
        });

        final EditText search=(EditText)findViewById(R.id.searchItem);
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
        List<Item> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (Item s : dataSet) {
            //if the existing elements contains the search input
            if (s.getItemName().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);

            }
        }
        itemAdapter.filterList(filterdNames);
    }



    public  void initiateRefresh()
    {

        dataSet= getAllItems(this);
        itemAdapter.filterList(dataSet);
        onRefreshComplete();

    }
    private void onRefreshComplete()
    {
        mSwipeRefreshLayout.setRefreshing(false);

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
