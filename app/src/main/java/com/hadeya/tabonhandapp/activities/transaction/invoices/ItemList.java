package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.adapters.ItemsAdapter;
import com.hadeya.tabonhandapp.adapters.TransactionCustomerAdapter;
import com.hadeya.tabonhandapp.adapters.transactionItemAdapter;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.Item;
import com.hadeya.tabonhandapp.store.DataBaseHelper;
import com.hadeya.tabonhandapp.store.ReadDataFromDB;
import com.hadeya.tabonhandapp.store.WriteDataToDB;
import com.hadeya.tabonhandapp.utils.NetworkConnect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllItems;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getLoginUser;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;

/**
 * Created by AyaAli on 05/04/2018.
 */

public class ItemList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.recyclerViewTransactionItem)
    RecyclerView mRecyclerView;
    @BindView(R.id.update_items_button)
    FloatingActionButton fabUpdateItems;
    @BindView(R.id.swipeRefreshTransactionItem)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private Menu menu;
    protected transactionItemAdapter itemAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Item> dataSet;
    Invoice invoice;
    static Context mContext;
    static ItemList mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity15_invoice_items_list_main);
        mContext=this;
        mActivity=ItemList.this;
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
        dataSet=getAllItems();
        ButterKnife.bind(this);
        fabUpdateItems.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                AlertDialog diaBox =AskOption();
                diaBox.show();

            }
        }
        );

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
        mSwipeRefreshLayout.setRefreshing(false);
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

        dataSet= getAllItems();
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

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Update")
                .setMessage("Do you want to Update Items List")
                .setIcon(R.mipmap.logo)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        try {
                            if(NetworkConnect.isConnected()==true) {
                                DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
                                WriteDataToDB.mdatabase = dataBaseHelper;
                                SQLiteDatabase sqlDB = dataBaseHelper.getWritableDatabase();
                                DataBaseHelper.resetItems(sqlDB);
                                WriteDataToDB.StoreItems();
                                mSwipeRefreshLayout.setRefreshing(true);
                                dataSet = ReadDataFromDB.getAllItems();
                                mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                                mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
                                mRecyclerView.setHasFixedSize(true);
                                itemAdapter = new transactionItemAdapter(mContext, dataSet, invoice, mActivity);
                                mRecyclerView.setAdapter(itemAdapter);
                            }
                            else
                            {
                                Toast.makeText(ItemList.this, "No Internet , Please connect", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e)
                        {

                        }

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
}
