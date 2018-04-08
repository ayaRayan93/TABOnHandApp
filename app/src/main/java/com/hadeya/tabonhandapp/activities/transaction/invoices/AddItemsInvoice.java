package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.adapters.ItemsListData;
import com.hadeya.tabonhandapp.json.Parser;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;

public class AddItemsInvoice extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Item invoiceItem;
    Invoice invoice;

    @BindView(R.id.invoiceNo1)TextView invoiceNo;
    @BindView(R.id.customerName1)TextView customerName;
    @BindView(R.id.itemName1)TextView itemName;
    @BindView(R.id.itemCode1)TextView itemCode;
    @BindView(R.id.price1)TextView price;

    @BindView(R.id.discount)TextView discount;
    @BindView(R.id.qty)TextView qty;
    @BindView(R.id.tax)TextView tax;
    @BindView(R.id.date)TextView date;
    @BindView(R.id.net)TextView net;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity14_add_item_to_invoice_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            invoice = extras.getParcelable("invoice");
            invoiceItem = extras.getParcelable("item");
            invoiceNo.setText(invoice.getInvoiceNo());
            customerName.setText(invoice.getCustomer().getCustName());
            itemName.setText(invoiceItem.getItemName());
            itemCode.setText(invoiceItem.getItemCode());
            price.setText(invoiceItem.getSelPrice1Default());
        }

        Button saveInvoice = (Button) findViewById(R.id.save);
        saveInvoice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent main = new Intent("ItemList");
                ItemsListData.invoice=invoice;
                ItemsListData.itemsListData.add(getItemInvoice());
                ItemsListData.itemsList.add(invoiceItem);
                //invoice=null;
               // invoiceItem=null;
                startActivity(main);
            }
        }

        );


        Button close=(Button)findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent("InvoiceItemsList");
                ItemsListData.invoice=invoice;
                ItemsListData.itemsListData.add(getItemInvoice());
                ItemsListData.itemsList.add(invoiceItem);
                 main.putExtra("invoice",invoice);
                startActivity(main);
            }
        });


    }

    public InvoiceItem getItemInvoice()
    {


        InvoiceItem invoiceItem=new InvoiceItem();
        invoiceItem.setItemName(itemName.getText().toString());
        invoiceItem.setItemCode(itemCode.getText().toString());
        invoiceItem.setPrice(price.getText().toString());
        invoiceItem.setDiscountAmount(discount.getText().toString());
        invoiceItem.setQuantity(qty.getText().toString());
        invoiceItem.setTax(tax.getText().toString());
        invoiceItem.setExpityDate(date.getText().toString());
        double price1=Double.parseDouble(price.getText().toString());
        double dis=Double.parseDouble(discount.getText().toString());
        double tax1=Double.parseDouble(tax.getText().toString());
        int quantity1=Integer.parseInt(qty.getText().toString());
        double net1=((price1*quantity1)-dis)*tax1;
        invoiceItem.setNet(String.valueOf(net1));
        net.setText(net1+"");

        return invoiceItem;

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
