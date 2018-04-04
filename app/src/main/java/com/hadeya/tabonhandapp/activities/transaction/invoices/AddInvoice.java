package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.activities.customers.AddNewCustomer;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.store.CustomerContentProvider;
import com.hadeya.tabonhandapp.store.CustomerTable;
import com.hadeya.tabonhandapp.store.InvoiceContentProvider;
import com.hadeya.tabonhandapp.store.InvoiceTable;
import com.hadeya.tabonhandapp.store.ReadDataFromDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllCustomerClassification;

/**
 * Created by AyaAli on 28/03/2018.
 */

public class AddInvoice extends AppCompatActivity {
    @BindView(R.id.invoicetno)EditText InvoiceNo;
    @BindView(R.id.invoiceDate)EditText InvoiceDate;
    @BindView(R.id.notes)EditText Notes;
    @BindView(R.id.refno)EditText RefNo;
    @BindView(R.id.type)Spinner type;
    @BindView(R.id.invoiceType)Spinner invoiceType;
    @BindView(R.id.customer)Spinner spinnerCustomers;

    Invoice newInvoice;
    HashMap<Integer,String> spinnerCustomersMap;
    HashMap<Integer,String> spinnerMapType;
    HashMap<Integer,String> spinnerMapInvoiceType;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity10_add_invoice_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // navigationView.setBackgroundResource(R.color.customColor);
        // navigationView.setItemTextColor(getColorStateList(11));
        // navigationView.setNavigationItemSelectedListener(this);



        ButterKnife.bind(this);
        Button addInvoice=(Button)findViewById(R.id.save);
        addInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewInvoice();
                Toast.makeText(AddInvoice.this, "Done ", Toast.LENGTH_SHORT).show();
            }
        });

        String[] spinnerArrayType = new String[2];
        spinnerMapType = new HashMap<Integer, String>();

        spinnerMapType.put(0,"1");
        spinnerArrayType[0] = "Check";
        spinnerMapType.put(1,"2");
        spinnerArrayType[1] = "Cash";

        ArrayAdapter<String> adapterT =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerArrayType);
        adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapterT);

        String[] spinnerArrayInvoicType = new String[2];
        spinnerMapInvoiceType = new HashMap<Integer, String>();

        spinnerMapInvoiceType.put(0,"1");
        spinnerArrayInvoicType[0] = "Check";
        spinnerMapInvoiceType.put(1,"2");
        spinnerArrayInvoicType[1] = "Cash";

        ArrayAdapter<String> adapterInvoiceT =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerArrayInvoicType);
        adapterInvoiceT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        invoiceType.setAdapter(adapterInvoiceT);


        //List<Customer>allCustomers= selectAllCustomers();
        List<Customer>allCustomers= ReadDataFromDB.getAllCustomerForSalesPerson(this);
        String[] customersArray = new String[allCustomers.size()];
        spinnerCustomersMap = new HashMap<Integer, String>();
        for (int i = 0; i < allCustomers.size(); i++)
        {
            spinnerCustomersMap.put(i,allCustomers.get(i).getID());
            customersArray[i] = allCustomers.get(i).getCustName();
        }

        ArrayAdapter<String> adapterC =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, customersArray);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCustomers.setAdapter(adapterC);


    }

    public void addNewInvoice()
    {
        String invoiceNo=InvoiceNo.getText().toString();
        String invoiceDate=InvoiceDate.getText().toString();
        String notes=Notes.getText().toString();
        String refNo=RefNo.getText().toString();
        newInvoice=new Invoice("",invoiceNo,invoiceDate,"","",notes,refNo,"");
        newInvoice.setPayementTypeId(spinnerMapType.get(type.getSelectedItemPosition()));
        newInvoice.setInvoiceTypeId(spinnerMapInvoiceType.get(invoiceType.getSelectedItemPosition()));
        newInvoice.setCustmerId(spinnerCustomersMap.get(spinnerCustomers.getSelectedItemPosition()));
        ContentValues values = new ContentValues();
       // values.put(InvoiceTable.Id, newInvoice.getInvoiceNo());
        values.put(InvoiceTable.InvoiceNo, newInvoice.getInvoiceNo());
        values.put(InvoiceTable.InvoiceDate,newInvoice.getInvoiceDate());
        values.put(InvoiceTable.Notes,newInvoice.getNotes());
        values.put(InvoiceTable.RefNO,newInvoice.getRefNO());
        values.put(InvoiceTable.PayementTypeId,newInvoice.getPayementTypeId());
        values.put(InvoiceTable.CustmerId,newInvoice.getCustmerId());
        values.put(InvoiceTable.InvoiceTypeId,newInvoice.getInvoiceTypeId());

        InvoiceContentProvider invoiceContentProvider=new InvoiceContentProvider(this);
        invoiceContentProvider.insert(InvoiceContentProvider.CONTENT_URI_add,values);

    }


}
