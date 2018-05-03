package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.activities.transaction.TransactionMain;
import com.hadeya.tabonhandapp.adapters.InvoiceFirstAdapter;
import com.hadeya.tabonhandapp.adapters.transactionItemAdapter;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.store.AreaTable;
import com.hadeya.tabonhandapp.store.DataBaseHelper;
import com.hadeya.tabonhandapp.store.InvoiceTable;
import com.hadeya.tabonhandapp.store.ReadDataFromDB;
import com.hadeya.tabonhandapp.store.WriteDataToDB;
import com.hadeya.tabonhandapp.utils.NetworkConnect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstInvoiceList extends AppCompatActivity implements InvoiceFirstAdapter.InvoicesListAdapterListener {
    @BindView(R.id.recyclerView18)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh18)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.update_invoices_button)
    FloatingActionButton fabUpdateInvoices;

    Context mContext;
    List<Invoice> dataSet;
    protected InvoiceFirstAdapter itemAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity18_first_invoice_main);
        mContext = this;
        dataSet = new ArrayList<>();
        dataSet = ReadDataFromDB.getAllInvoices(this);
        ButterKnife.bind(this);
        fabUpdateInvoices = (FloatingActionButton) findViewById(R.id.update_invoices_button);
        fabUpdateInvoices.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                AlertDialog diaBox =AskOption();
                diaBox.show();

            }
        }

        );

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView18);
            mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh18);
            mRecyclerView.setHasFixedSize(true);
            itemAdapter = new InvoiceFirstAdapter(this, dataSet);
            mRecyclerView.setAdapter(itemAdapter);


        mSwipeRefreshLayout.setColorScheme(
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.colorAccent, R.color.colorPrimaryDark);


        mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if (!mSwipeRefreshLayout.isRefreshing()) {
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
                    Intent main = new Intent("CustomerList");
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

    }

    private void onRefreshComplete()
    {
        mSwipeRefreshLayout.setRefreshing(false);

    }


    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Update")
                .setMessage("Do you want to Update Invoice List")
                .setIcon(R.mipmap.logo)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        try {
                            if(NetworkConnect.isConnected()==true) {
                                DataBaseHelper dataBaseHelper=new DataBaseHelper(mContext);
                                WriteDataToDB.mdatabase=dataBaseHelper;
                                SQLiteDatabase sqlDB = dataBaseHelper.getWritableDatabase();
                                DataBaseHelper.resetInvoice(sqlDB);
                                WriteDataToDB.StoreAllInvoices(mContext);
                                dataSet = ReadDataFromDB.getAllInvoices(mContext);
                                mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView18);
                                mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh18);
                                mRecyclerView.setHasFixedSize(true);
                                itemAdapter = new InvoiceFirstAdapter(mContext, dataSet);
                                mRecyclerView.setAdapter(itemAdapter);

                            }
                            else
                            {
                                Toast.makeText(FirstInvoiceList.this, "No Internet , Please connect", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDeleteButtonClicked (int id)
    {
        String invId=dataSet.get(id).getId();
        deleteItem(invId);
        dataSet.remove(id);
        itemAdapter.notifyItemRemoved(id);
        itemAdapter.notifyItemRangeChanged(id,dataSet.size());


    }

    public void deleteItem(String invId)
    {
        DataBaseHelper dataBaseHelper=new DataBaseHelper(mContext);
        SQLiteDatabase sqlDB = dataBaseHelper.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+ InvoiceTable.InvoiceTable +" WHERE Id = "+invId;
        sqlDB.execSQL(clearDBQuery);
    }
    @Override
    public void onEditButtonClicked (int id)
    {
        String invId=dataSet.get(id).getId();
        Invoice invoice = ReadDataFromDB.getInvoice(Integer.parseInt(invId));
        List<InvoiceItem> invItems=ReadDataFromDB.getItemInvoice(mContext,invId);
        invoice.setInvoiceItems(invItems);

        Intent main = new Intent("AddInvoice");
        main.putExtra("invoiceEdit",invoice);
        startActivity(main);
        finish();

    }

}
