package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.view.MenuItem;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.adapters.ItemsListData;
import com.hadeya.tabonhandapp.app.spinnerAdapter;
import com.hadeya.tabonhandapp.json.Parser;
import com.hadeya.tabonhandapp.models.AutomticInvoiceNo;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceType;
import com.hadeya.tabonhandapp.store.AutomticInvoiceNoContentProvider;
import com.hadeya.tabonhandapp.store.AutomticInvoiceNoTable;
import com.hadeya.tabonhandapp.store.InvoiceContentProvider;
import com.hadeya.tabonhandapp.store.InvoiceTable;
import com.hadeya.tabonhandapp.store.ReadDataFromDB;
import com.hadeya.tabonhandapp.store.WriteDataToDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.custName)TextView custName;

    @BindView(R.id.invoiceButtonDate)ImageButton invoiceButtonDate;

    private int year;
    private int month;
    private int day;

    Invoice newInvoice;
    HashMap<Integer, String> spinnerCustomersMap;
    HashMap<Integer, String> spinnerMapType;
    HashMap<Integer, String> spinnerMapInvoiceType;
    List<Customer> allCustomers;
    List<InvoiceType> allInvoiceTypes;
    String[] InvoiceTypesArray;
    Context context ;
    Customer customer;

    Invoice InvoiceEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity10_add_invoice_main);
         context = this;
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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            customer = extras.getParcelable("customer");

        }
        InvoiceEdit=null;
        Bundle extrasEdit = getIntent().getExtras();
        if (extrasEdit != null) {
             InvoiceEdit = extrasEdit.getParcelable("invoiceEdit");

        }


        ButterKnife.bind(this);
        if(InvoiceEdit==null)
        {
        custName.setText(customer.getCustName());
        }else
        {
            custName.setText(InvoiceEdit.getCustomer().getCustName());
            InvoiceNo.setText(InvoiceEdit.getInvoiceNo());
            InvoiceDate.setText(InvoiceEdit.getInvoiceDate());
            Notes.setText(InvoiceEdit.getNotes());
            RefNo.setText(InvoiceEdit.getRefNO());
            invoiceType.setSelection(Integer.parseInt(InvoiceEdit.getInvoiceTypeId()));
            try
            {
                type.setSelection(Integer.parseInt(InvoiceEdit.getPayementTypeId()));
            }
            catch (Exception e)
            {

            }
        }

        Button change = (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent("CustomerList");
                startActivity(main);
                finish();
            }
        });
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
                if(InvoiceEdit ==null) {
                    main.putExtra("invoice", invoice);
                }else
                {
                    main.putExtra("InvoiceEdit", InvoiceEdit);
                }
                startActivity(main);
                finish();
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

        //WriteDataToDB.storeAllInvoiceTypes();
        allInvoiceTypes = ReadDataFromDB.getAllInvoicesTypes(this);
        int spinnerSize=0;
        List<InvoiceType> allInvoiceTypesFinal=new ArrayList<>();
        for (int i = 0;i<allInvoiceTypes.size();i++)
        {
            if(allInvoiceTypes.get(i).getTrxType()!=4)
            {
                allInvoiceTypesFinal.add(allInvoiceTypes.get(i));
                spinnerSize++;
            }

        }
       // String[] spinnerArrayInvoicType = new String[allInvoiceTypes.size()];
        spinnerMapInvoiceType=new HashMap<Integer, String>();
        InvoiceTypesArray = new String[allInvoiceTypesFinal.size()];
        spinnerMapInvoiceType=new HashMap<Integer, String>();
        for(int i = 0;i<allInvoiceTypesFinal.size();i++)
        {
                spinnerMapInvoiceType.put(i, allInvoiceTypesFinal.get(i).getTrxType() + "");
                InvoiceTypesArray[i] = allInvoiceTypesFinal.get(i).getTrxArbName();

        }
   /* ArrayAdapter<String> adapterInvoiceT = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArrayInvoicType);
    adapterInvoiceT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    invoiceType.setAdapter(adapterInvoiceT);*/
        spinnerAdapter adapter1 = new spinnerAdapter(AddInvoice.this, android.R.layout.simple_list_item_1);
        adapter1.addAll(InvoiceTypesArray);
        adapter1.add("Select Invoice Type");
        invoiceType.setAdapter(adapter1);
        invoiceType.setSelection(adapter1.getCount());


        String LoginRepCod=ReadDataFromDB.getLoginUser().get(0).getRepCodId();
        String userNum=LoginRepCod.substring(LoginRepCod.length()-3,LoginRepCod.length());
        AutomticInvoiceNo autInv=ReadDataFromDB.getAutomticInvoiceNo(LoginRepCod);
        // int serialInv=ReadDataFromDB.getAutomticInvoiceNo(LoginRepCod).getSerialInvoice();
        ItemsListData.serialInv=ReadDataFromDB.getAutomticInvoiceNo(LoginRepCod).getSerialInvoice();
        String serialInvString=ItemsListData.serialInv+"";
        int numDigits=serialInvString.length();
        if(numDigits<5)
        {
            int numZeroDigits=5-numDigits;
            for(int i=0;i<numZeroDigits;i++)
            {
                userNum=userNum+"0";
            }
        }
        ButterKnife.bind(this);
        if(InvoiceEdit ==null) {
            InvoiceNo.setText(userNum + ItemsListData.serialInv);
        }

        //serialInv++;
        //WriteDataToDB.updateAutomaticInvoiceNo(LoginRepCod,ItemsListData.serialInv);
        AutomticInvoiceNo au=ReadDataFromDB.getAutomticInvoiceNo(LoginRepCod);

        if(InvoiceEdit ==null)
       {
        setCurrentDateOnView();
       }
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

    public Invoice getNewInvoice(List<Customer> allCustomers) throws Exception {

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

        int n=invoiceType.getSelectedItemPosition();
        int size =InvoiceTypesArray.length;
        if(n<size )
        {
            newInvoice.setInvoiceTypeId(spinnerMapInvoiceType.get(invoiceType.getSelectedItemPosition()));
            newInvoice.setInvoiceTypeName(InvoiceTypesArray[n]);

        }
        else {
            Toast.makeText(AddInvoice.this, "Enter Payment Type", Toast.LENGTH_SHORT).show();
            throw new  Exception("Enter Payment Type");
        }

        newInvoice.setCustmerId(customer.getId());
        newInvoice.setCustomer(customer);

        return newInvoice;
    }


    public int createSharedInvoiceNo ( String LoginRepCod)
    {
        SharedPreferences pref = context.getSharedPreferences("prefInviceNo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt( LoginRepCod,0);
        editor.apply();
        int counter=pref.getInt(LoginRepCod, 0);
        return counter;
    }


}

