package com.hadeya.tabonhandapp.activities.customers;

import android.content.ContentValues;
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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.hadeya.tabonhandapp.adapters.CustomerAdapter;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.store.CustomerContentProvider;
import com.hadeya.tabonhandapp.store.CustomerTable;
import com.hadeya.tabonhandapp.store.DataBaseHelper;
import com.hadeya.tabonhandapp.store.WriteDataToDB;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hadeya.tabonhandapp.store.DataBaseHelper.resetCustomers;
import static com.hadeya.tabonhandapp.store.DataBaseHelper.resetDataBase;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllCustomerForSalesPerson;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getLoginUser;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.downloadCustomer;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.downloadData;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.uploade;

public class CustomerMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.balanceTotal)
    TextView balanceTotal;

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
        navigationView.setNavigationItemSelectedListener(this);

        dataSet = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mRecyclerView.setHasFixedSize(true);
        itemAdapter = new CustomerAdapter(this,dataSet);
        mRecyclerView.setAdapter(itemAdapter);

        // Set the color scheme of the SwipeRefreshLayout by providing 4 color resource ids
        //noinspection ResourceAsColor
        mSwipeRefreshLayout.setColorScheme(
                R.color.colorPrimaryDark, R.color.thirdColor,
                R.color.thirdColor, R.color.colorPrimaryDark);


        mLayoutManager = new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mSwipeRefreshLayout.setRefreshing(false);
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
        initiateRefresh();

        final EditText search=(EditText)findViewById(R.id.searchCustomers);
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

                uploade(getBaseContext(),getLoginUser().get(0).getRepCodId());
                SQLiteDatabase sqlDB = WriteDataToDB.mdatabase.getWritableDatabase();
                resetCustomers(sqlDB);
                downloadCustomer();

            }
        });
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
           /* else if(s.getCustomerCode().toLowerCase().contains(text.toLowerCase()))
            {
                filterdNames.add(s);
            }*/
        }
        itemAdapter.filterList(filterdNames);
    }

    public  void initiateRefresh()
    {

        dataSet= getAllCustomerForSalesPerson(getLoginUser().get(0).getRepCodId());


        if (dataSet.size()==0)
        {
            AlertDialog diaBox = AskOption(this);
            diaBox.show();
        }
        else {
            itemAdapter.filterList(dataSet);
            onRefreshComplete();
        }

    }
    public  void initiateList()
    {

        dataSet = getAllCustomerForSalesPerson(getLoginUser().get(0).getRepCodId());
        itemAdapter.filterList(dataSet);

        onRefreshComplete();

    }
    private void onRefreshComplete()
    {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    private AlertDialog AskOption(final CustomerMainActivity customerMainActivity)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Update")
                .setMessage("Do you want to Update Customer List")
                .setIcon(R.mipmap.logo)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        uploade(getBaseContext(),getLoginUser().get(0).getRepCodId());
                        mSwipeRefreshLayout.setRefreshing(true);
                        SQLiteDatabase sqlDB = WriteDataToDB.mdatabase.getWritableDatabase();
                        resetCustomers(sqlDB);
                        downloadCustomer(customerMainActivity);
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

    public void calTotalBalance()
    {
        double total=0;
        for (int i=0;i<dataSet.size();i++)
        {
            total+=Double.parseDouble(dataSet.get(i).getBalance());
        }
        balanceTotal.setText(total+"");
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
