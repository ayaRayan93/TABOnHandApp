package com.hadeya.tabonhandapp.activities.transaction;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.models.Classification;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.models.Receipt;
import com.hadeya.tabonhandapp.store.CustomerContentProvider;
import com.hadeya.tabonhandapp.store.CustomerTable;
import com.hadeya.tabonhandapp.store.ReceiptContentProvider;
import com.hadeya.tabonhandapp.store.ReceiptTable;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getAllCustomerForSalesPerson;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getLoginUser;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;

/**
 * Created by AyaAli on 28/03/2018.
 */

public class AddReceipt extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.receiptno)EditText receiptno;
    @BindView(R.id.receiptDate)EditText receiptDate;
    @BindView(R.id.value3)EditText value3;
    @BindView(R.id.Notes)EditText Notes;
    @BindView(R.id.refno)EditText refno;


    @BindView(R.id.type)Spinner type;
    @BindView(R.id.customer)Spinner customer;
    List<Classification> dataClass=null;
    List<Customer> dataCustomer=null;

    HashMap<Integer,String> spinnerMapType;
    HashMap<Integer,String> spinnerMapCustomer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity12_receipt_main);

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
        Button addCustomer=(Button)findViewById(R.id.saveReceipt);
        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewReceipt();
                Toast.makeText(AddReceipt.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });


        // dataClass= getAllCustomerClassification();

        String[] spinnerArray = new String[2];
        spinnerMapType = new HashMap<Integer, String>();

        spinnerMapType.put(0,"1");
        spinnerArray[0] = "Check";
        spinnerMapType.put(1,"2");
        spinnerArray[1] = "Cash";

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);


        dataCustomer=getAllCustomerForSalesPerson(getLoginUser().get(0).getRepCodId());
        String[] spinnerArrayArea = new String[dataCustomer.size()];
        spinnerMapCustomer = new HashMap<Integer, String>();
        for (int i = 0; i < dataCustomer.size(); i++)
        {
            spinnerMapCustomer.put(i,dataCustomer.get(i).getId());
            spinnerArrayArea[i] = dataCustomer.get(i).getCustName();
        }
        ArrayAdapter<String>  adapter1 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerArrayArea);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customer.setAdapter(adapter1);

    }
    public void addNewReceipt()
    {
        // String code=Code.getText().toString();
        Receipt receipt=new Receipt();
        receipt.setRecNo(receiptno.getText().toString());
        receipt.setRecDate(receiptDate.getText().toString());
        receipt.setRecieptTypeId(spinnerMapType.get(type.getSelectedItemPosition()));
        receipt.setCustmerId(spinnerMapCustomer.get(customer.getSelectedItemPosition()));
        receipt.setValue(value3.getText().toString());
        receipt.setNotes(Notes.getText().toString());
        receipt.setRefNO(refno.getText().toString());

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // values.put(CustomerTable.CustomerCode, newCustomer.getCustomerCode());
        values.put(ReceiptTable.RecNo, receipt.getRecNo());
        values.put(ReceiptTable.RecDate, receipt.getRecDate());
        values.put(ReceiptTable.RecieptTypeId, receipt.getRecieptTypeId());
        values.put(ReceiptTable.CustmerId, receipt.getCustmerId());
        values.put(ReceiptTable.Value, receipt.getValue());
        values.put(ReceiptTable.Notes, receipt.getNotes());
        values.put(ReceiptTable.RefNO, receipt.getRefNO());
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        ReceiptContentProvider receiptContentProvider=new ReceiptContentProvider(this);
        receiptContentProvider.insert(ReceiptContentProvider.CONTENT_URI_add,values);

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
