package com.hadeya.tabonhandapp.activities.items;

import android.app.Activity;
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
import android.view.Window;
import android.widget.EditText;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.adapters.CustomerAdapter;
import com.hadeya.tabonhandapp.adapters.ItemsAdapter;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.models.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllCustomerForSalesPerson;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllItems;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.StoreItems;

/**
 * Created by AyaAli on 19/03/2018.
 */

public class ItemsActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewItem)
    RecyclerView mRecyclerView;


    @BindView(R.id.swipeRefreshItem)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private Menu menu;
    protected ItemsAdapter itemAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Item> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_item_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // navigationView.setNavigationItemSelectedListener(this);

  //      StoreItems();
        dataSet = new ArrayList<>();
       // dataSet=getAllItems(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewItem);
        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefreshItem);
        mRecyclerView.setHasFixedSize(true);
        itemAdapter = new ItemsAdapter(this,dataSet);
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

       // final EditText search=(EditText)findViewById(R.id.search);
       /* search.addTextChangedListener(new TextWatcher() {
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
*/
//        initiateRefresh(1);
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



    public  void initiateRefresh(int i)
    {

        List<Item> dataSet1= getAllItems(this);
        itemAdapter.filterList(dataSet1);


    }
}
