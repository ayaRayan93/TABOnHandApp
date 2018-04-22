package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.adapters.InvoiceFirstAdapter;
import com.hadeya.tabonhandapp.adapters.InvoicesItemsAdapter;
import com.hadeya.tabonhandapp.adapters.ItemsListData;
import com.hadeya.tabonhandapp.app.AppController;
import com.hadeya.tabonhandapp.json.Parser;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.store.ReadDataFromDB;
import com.hadeya.tabonhandapp.utils.makeRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstInvoiceList extends AppCompatActivity {
    @BindView(R.id.recyclerView18)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh18)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.netTotal)
    TextView netTotal;

    Context mContext;
    List<Invoice> dataSet;
    protected InvoiceFirstAdapter itemAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity18_first_invoice_main);
        mContext=this;
        dataSet = new ArrayList<>();
        dataSet= ReadDataFromDB.getAllInvoices(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView18);
        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefresh18);
        mRecyclerView.setHasFixedSize(true);
        itemAdapter = new InvoiceFirstAdapter(this,dataSet);
        mRecyclerView.setAdapter(itemAdapter);
        ButterKnife.bind(this);
        initiateRefresh();
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



        Button addInvoice = (Button) findViewById(R.id.addNewInvoice);
        addInvoice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){

                try {
                    Intent main = new Intent(getBaseContext(), CustomerList.class);
                    startActivity(main);
                    finish();
                }
                catch (Exception e)
                {

                }
            }
        }

        );

    }

    public  void initiateRefresh()
    {

        dataSet= ReadDataFromDB.getAllInvoices(mContext);
        itemAdapter.filterList(dataSet);
        onRefreshComplete();
        calTotalBalance();
    }

    private void onRefreshComplete()
    {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    public void calTotalBalance()
    {
        double total=0;
        for (int i=0;i<dataSet.size();i++)
        {
            total+=Double.parseDouble(dataSet.get(i).getNet());
        }
        netTotal.setText(total+"");
    }
}
