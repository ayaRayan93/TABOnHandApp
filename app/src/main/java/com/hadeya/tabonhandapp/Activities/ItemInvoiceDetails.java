package com.hadeya.tabonhandapp.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hadeya.tabonhandapp.Adapters.ItemDetailsAdapter;
import com.hadeya.tabonhandapp.Adapters.ItemsAdapter;
import com.hadeya.tabonhandapp.Handlers.ItemDetailsHandler;
import com.hadeya.tabonhandapp.Handlers.ItemsJSONHandler;
import com.hadeya.tabonhandapp.Helpers.StaticMethods;
import com.hadeya.tabonhandapp.Models.InvoiceDetails;
import com.hadeya.tabonhandapp.Models.Item;
import com.hadeya.tabonhandapp.Models.ItemDetails;
import com.hadeya.tabonhandapp.R;

import java.util.ArrayList;

public class ItemInvoiceDetails extends Activity
{
    ListView listItems ;
    TextView invoiceNumber , customerName ;
    Context context;
    ArrayList<ItemDetails> ItemsDetailsList;
    ArrayList<InvoiceDetails> InvoiceDetailsList;
    ProgressBar progressBar;
    ItemDetailsAdapter itemsDetailsAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_item_invoice_details);

        SetUp();
    }
    public  void SetUp()
    {
        context = ItemInvoiceDetails.this;
        invoiceNumber = (TextView)findViewById(R.id.invoiceNumber);
        customerName = (TextView)findViewById(R.id.customerName);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listItems = (ListView) findViewById(R.id.listItems);

        if (StaticMethods.HaveNetworkConnection(context))
        {
            progressBar.setVisibility(View.VISIBLE);
            GetData();
            progressBar.setVisibility(View.GONE);
        }
        else
        {
            Toast.makeText(context , getResources().getString(R.string.error_connection_eng), Toast.LENGTH_SHORT).show();
        }
    }

    public void GetData()
    {
        ItemsDetailsList = new ArrayList<>();
        InvoiceDetailsList = new ArrayList<>();

        GetItemsDetailsList getItemsList = new GetItemsDetailsList();
        getItemsList.execute();
    }
    class GetItemsDetailsList extends AsyncTask<Void , Void , Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
            ItemsDetailsList = ItemDetailsHandler.getData();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

          invoiceNumber.setText("Invoice  No : "+ItemsDetailsList.get(0).getInvoiceNo());
          customerName.setText("Customer Name : "+ItemsDetailsList.get(0).getCustmerId());
          InvoiceDetailsList = ItemsDetailsList.get(0).getTOHInvoiceDetails();
          itemsDetailsAdapter = new ItemDetailsAdapter(context , InvoiceDetailsList);
          listItems.setAdapter(itemsDetailsAdapter);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            startActivity(new Intent(ItemInvoiceDetails.this , ItemsActivity.class));
            ItemInvoiceDetails.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(ItemInvoiceDetails.this , ItemsActivity.class));
        ItemInvoiceDetails.this.finish();
    }
}
