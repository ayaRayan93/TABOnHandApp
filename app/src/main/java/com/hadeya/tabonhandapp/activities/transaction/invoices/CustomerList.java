package com.hadeya.tabonhandapp.activities.transaction.invoices;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.adapters.TransactionCustomerAdapter;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.store.DataBaseHelper;
import com.hadeya.tabonhandapp.store.ReadDataFromDB;
import com.hadeya.tabonhandapp.store.WriteDataToDB;
import com.hadeya.tabonhandapp.utils.NetworkConnect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllCustomerForSalesPerson;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getLoginUser;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;

/**
 * Created by AyaAli on 2018-04-18.
 */

public class CustomerList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.recyclerViewtransactioncustomer)
    RecyclerView mRecyclerView;


    @BindView(R.id.swipeRefreshtransactioncustomer)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.balanceTotal)
    TextView balanceTotal;

    static Context mContext;

    @BindView(R.id.update_customers_button)
    FloatingActionButton fabUpdateCustomers;

    private Menu menu;
    protected TransactionCustomerAdapter itemAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Customer> dataSet;
    private int flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity17_transaction_customers_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext=this;


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        dataSet = getAllCustomerForSalesPerson(getLoginUser().get(0).getRepCodId());
        ButterKnife.bind(this);
        fabUpdateCustomers.setOnClickListener(new View.OnClickListener()
                                              {
                                                  @Override
                                                  public void onClick (View v){
                                                      AlertDialog diaBox =AskOption();
                                                      diaBox.show();

                                                  }
                                              }

        );


        ButterKnife.bind(this);
        dataSet = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewtransactioncustomer);
        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefreshtransactioncustomer);
        mRecyclerView.setHasFixedSize(true);
        itemAdapter = new TransactionCustomerAdapter(this,dataSet);
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


                initiateRefresh(flag);

            }
        });
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

        dataSet= getAllCustomerForSalesPerson(getLoginUser().get(0).getRepCodId());
        itemAdapter.filterList(dataSet);
        onRefreshComplete();
        calTotalBalance();

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

    public void calTotalBalance()
    {
        double total=0;
        for (int i=0;i<dataSet.size();i++)
        {
            if(dataSet.get(i).getBalance()!=null)
            total+=Double.parseDouble(dataSet.get(i).getBalance());
        }
        balanceTotal.setText(total+"");
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Update")
                .setMessage("Do you want to Update Customer List")
                .setIcon(R.mipmap.logo)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        try {
                            if(NetworkConnect.isConnected()==true) {
                                DataBaseHelper dataBaseHelper=new DataBaseHelper(mContext);
                                WriteDataToDB.mdatabase=dataBaseHelper;
                                SQLiteDatabase sqlDB = dataBaseHelper.getWritableDatabase();
                                DataBaseHelper.resetCustomers(sqlDB);
                                WriteDataToDB.storeCustomer(getLoginUser().get(0).getRepCodId());
                                WriteDataToDB.storeAllInvoiceTypes();
                                mSwipeRefreshLayout.setRefreshing(true);
                                dataSet = ReadDataFromDB.getAllCustomerForSalesPerson((getLoginUser().get(0).getRepCodId()));
                                mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                                mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
                                mRecyclerView.setHasFixedSize(true);
                                itemAdapter = new TransactionCustomerAdapter(mContext,dataSet);
                                mRecyclerView.setAdapter(itemAdapter);
                            }
                            else
                            {
                                Toast.makeText(CustomerList.this, "No Internet , Please connect", Toast.LENGTH_SHORT).show();
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
