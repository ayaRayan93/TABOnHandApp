package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.view.MenuItem;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.activities.customers.AddNewCustomer;
import com.hadeya.tabonhandapp.adapters.ItemsListData;
import com.hadeya.tabonhandapp.app.spinnerAdapter;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.store.CustomerContentProvider;
import com.hadeya.tabonhandapp.store.CustomerTable;
import com.hadeya.tabonhandapp.store.InvoiceContentProvider;
import com.hadeya.tabonhandapp.store.InvoiceTable;
import com.hadeya.tabonhandapp.store.ReadDataFromDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllCustomerClassification;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;

/**
 * Created by AyaAli on 28/03/2018.
 */



public class AddInvoice extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.invoicetno)EditText InvoiceNo;
    @BindView(R.id.invoiceDate)EditText InvoiceDate;
    @BindView(R.id.notes)EditText Notes;
    @BindView(R.id.refno)EditText RefNo;
    @BindView(R.id.type)Spinner type;
    @BindView(R.id.invoiceType)Spinner invoiceType;
    @BindView(R.id.customer)Spinner spinnerCustomers;

    @BindView(R.id.invoiceButtonDate)ImageButton invoiceButtonDate;

    private int year;
    private int month;
    private int day;

    Invoice newInvoice;
    HashMap<Integer, String> spinnerCustomersMap;
    HashMap<Integer, String> spinnerMapType;
    HashMap<Integer, String> spinnerMapInvoiceType;
    List<Customer> allCustomers;



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
        navigationView.setNavigationItemSelectedListener(this);


    ButterKnife.bind(this);

    Button addInvoice = (Button) findViewById(R.id.save);
    addInvoice.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
       // addNewInvoice();
            try {

               // ItemsListData.invoice=null;
                ItemsListData.itemsList.clear();
                ItemsListData.itemsListData.clear();
                Invoice invoice= getNewInvoice(allCustomers);
                Toast.makeText(AddInvoice.this, "Done ", Toast.LENGTH_SHORT).show();
                Intent main = new Intent("InvoiceItemsList");
                main.putExtra("invoice",invoice);
                startActivity(main);
            }
            catch (Exception e)
            {
                Toast.makeText(AddInvoice.this, "Enter Required Data", Toast.LENGTH_SHORT).show();
            }
    }
    }

    );

    String[] spinnerArrayType = new String[2];
    spinnerMapType=new HashMap<Integer, String>();

    spinnerMapType.put(0,"1");
    spinnerArrayType[0]="Check";
    spinnerMapType.put(1,"2");
    spinnerArrayType[1]="Cash";

        spinnerAdapter adapter = new spinnerAdapter(AddInvoice.this, android.R.layout.simple_list_item_1);
        adapter.addAll(spinnerArrayType);
        adapter.add("Select Payment Type");
        type.setAdapter(adapter);
        type.setSelection(adapter.getCount());

    String[] spinnerArrayInvoicType = new String[2];
    spinnerMapInvoiceType=new HashMap<Integer, String>();

    spinnerMapInvoiceType.put(0,"1");
    spinnerArrayInvoicType[0]="Check";
    spinnerMapInvoiceType.put(1,"2");
    spinnerArrayInvoicType[1]="Cash";

   /* ArrayAdapter<String> adapterInvoiceT = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArrayInvoicType);
    adapterInvoiceT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    invoiceType.setAdapter(adapterInvoiceT);*/
        spinnerAdapter adapter1 = new spinnerAdapter(AddInvoice.this, android.R.layout.simple_list_item_1);
        adapter1.addAll(spinnerArrayInvoicType);
        adapter1.add("Select Invoice Type");
        invoiceType.setAdapter(adapter1);
        invoiceType.setSelection(adapter1.getCount());


    //List<Customer>allCustomers= selectAllCustomers();
     allCustomers = ReadDataFromDB.getAllCustomerForSalesPerson(this);
    String[] customersArray = new String[allCustomers.size()];
    spinnerCustomersMap=new HashMap<Integer, String>();
    for(int i = 0;i<allCustomers.size();i++)

    {
        spinnerCustomersMap.put(i, allCustomers.get(i).getId());
        customersArray[i] = allCustomers.get(i).getCustName();
    }

        spinnerAdapter adapterc = new spinnerAdapter(AddInvoice.this, android.R.layout.simple_list_item_1);
        adapterc.addAll(customersArray);
        adapterc.add("Select Customer");
        spinnerCustomers.setAdapter(adapterc);
        spinnerCustomers.setSelection(adapterc.getCount());

        setCurrentDateOnView();
        invoiceButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });
}
    public void setCurrentDateOnView() {


        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        InvoiceDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into datepicker
        // dpResult.init(year, month, day, null);

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        return new DatePickerDialog(this, datePickerListener,
                        year, month,day);


    }
    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            InvoiceDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            // set selected date into datepicker also
            // dpResult.init(year, month, day, null);

        }
    };
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

    public Invoice getNewInvoice(List<Customer> allCustomers)
    {

        String invoiceNo=InvoiceNo.getText().toString();
        String invoiceDate=InvoiceDate.getText().toString();
        String notes=Notes.getText().toString();
        String refNo=RefNo.getText().toString();

        newInvoice=new Invoice();
        newInvoice.setInvoiceNo(invoiceNo);
        newInvoice.setInvoiceDate(invoiceDate);
        newInvoice.setNotes(notes);
        newInvoice.setRefNO(refNo);
        newInvoice.setPayementTypeId(spinnerMapType.get(type.getSelectedItemPosition()));
        newInvoice.setInvoiceTypeId(spinnerMapInvoiceType.get(invoiceType.getSelectedItemPosition()));
        newInvoice.setCustmerId(spinnerCustomersMap.get(spinnerCustomers.getSelectedItemPosition()));
        Customer customer=allCustomers.get(spinnerCustomers.getSelectedItemPosition());
        newInvoice.setCustomer(customer);

        return newInvoice;
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

