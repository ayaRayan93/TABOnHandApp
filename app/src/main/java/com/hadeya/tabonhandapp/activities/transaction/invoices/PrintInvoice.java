package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.adapters.InvoicesItemsAdapter;
import com.hadeya.tabonhandapp.adapters.ItemsListData;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;

public class PrintInvoice extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Invoice invoice;
    @BindView(R.id.invoiceNo)TextView invoiceNo;
    @BindView(R.id.customerName)TextView customerName;
    @BindView(R.id.date)TextView date;
    @BindView(R.id.totalPrint)TextView totalPrint;
    @BindView(R.id.recyclerViewItem13)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshItem13)
    SwipeRefreshLayout mSwipeRefreshLayout;

    protected List<InvoiceItem> dataSet;
    protected InvoicesItemsAdapter itemAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity16_print_invoice_main);

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
        dataSet= ItemsListData.itemsListData;
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewItem13);
        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefreshItem13);
        mRecyclerView.setHasFixedSize(true);
        itemAdapter = new InvoicesItemsAdapter(this,dataSet);
        mRecyclerView.setAdapter(itemAdapter);

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
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            invoice = extras.getParcelable("invoice");
            invoiceNo.setText(invoice.getInvoiceNo());
            customerName.setText(invoice.getCustomer().getCustName());
            date.setText(invoice.getInvoiceDate());

            initiateRefresh();
            calTotal();
        }
        Button exit=(Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent("TransactionMain");
                // main.putExtra("searchWord",searchResult);
                startActivity(main);
            }
        });
    }


    public  void initiateRefresh()
    {

        dataSet= ItemsListData.itemsListData;
        //dataSet=invoice.getInvoiceItems();
        itemAdapter.filterList(dataSet);
        onRefreshComplete();

    }
    private void onRefreshComplete()
    {
        mSwipeRefreshLayout.setRefreshing(false);

    }
    public void calTotal()
    {
        double totalv=0;
        for (int i=0;i<ItemsListData.itemsListData.size();i++)
        {
            totalv+=Double.parseDouble(ItemsListData.itemsListData.get(i).getNet());
        }
        totalPrint.setText(totalv+"");
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
