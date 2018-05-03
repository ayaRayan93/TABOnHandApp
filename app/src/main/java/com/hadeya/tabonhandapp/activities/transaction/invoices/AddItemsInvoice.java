package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.adapters.ItemsListData;
import com.hadeya.tabonhandapp.app.spinnerAdapter;
import com.hadeya.tabonhandapp.json.Parser;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.Item;

import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.logout;

public class AddItemsInvoice extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Item invoiceItem;
    Invoice invoice;

    @BindView(R.id.invoiceNo1)TextView invoiceNo;
    @BindView(R.id.invoiceType1)TextView invoiceType;
    @BindView(R.id.customerName1)TextView customerName;
    @BindView(R.id.itemName1)TextView itemName;
    @BindView(R.id.itemCode1)TextView itemCode;
    @BindView(R.id.unit)TextView unit;
    @BindView(R.id.price1)TextView price;
    @BindView(R.id.valueBeforDis)TextView valueBeforDis;
    @BindView(R.id.valueAfterDiscount)TextView valueAfterDiscount;

    @BindView(R.id.discount)TextView discount;
    @BindView(R.id.discountValue)EditText discountValue;
    @BindView(R.id.qty)EditText qty;
    @BindView(R.id.tax)EditText tax;
    @BindView(R.id.taxValue)TextView taxValue;
    @BindView(R.id.net)TextView net;

    @BindView(R.id.itemInvoicDate)
    EditText date;
    @BindView(R.id.itemInvoicButtoneDate)ImageButton itemInvoicButtoneDate;
     @BindView(R.id.discountType)Spinner DiscountType;
    HashMap<Integer, String> spinnerMapType;
    private int year;
    private int month;
    private int day;
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

        String[] spinnerArrayType = new String[2];
        spinnerMapType=new HashMap<Integer, String>();

        spinnerMapType.put(0,"1");
        spinnerArrayType[0]="Value";
        spinnerMapType.put(1,"2");
        spinnerArrayType[1]="Percent";

         ArrayAdapter<String> adapterInvoiceT = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArrayType);
         adapterInvoiceT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DiscountType.setAdapter(adapterInvoiceT);

        setCurrentDateOnView();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            invoice = extras.getParcelable("invoice");
            invoiceItem = extras.getParcelable("item");
            invoiceNo.setText(invoice.getInvoiceNo());
            invoiceType.setText(ItemsListData.invoice.getInvoiceTypeName());
            customerName.setText(invoice.getCustomer().getCustName());
            itemName.setText(invoiceItem.getItemName());
            itemCode.setText(invoiceItem.getItemCode());
            price.setText(invoiceItem.getSelPrice1Default());
            unit.setText(invoiceItem.getUnitName());
            calValueBeforeDiscount();
            calDiscount();
            calValueAfterDiscount();
            calTaxValue();
            calNet();
        }

        Button saveInvoice = (Button) findViewById(R.id.addAddNew);
        saveInvoice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent main = new Intent("ItemList");
                ItemsListData.invoice=invoice;
                ItemsListData.itemsListData.add(getItemInvoice());
                ItemsListData.itemsList.add(invoiceItem);
                //aya
                invoice.getInvoiceItems().add(getItemInvoice());
                main.putExtra("invoice",invoice);
                startActivity(main);
                finish();
            }
        }
        );

        Button close=(Button)findViewById(R.id.addAndclose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent("InvoiceItemsList");
                ItemsListData.invoice=invoice;
                ItemsListData.itemsListData.add(getItemInvoice());
                ItemsListData.itemsList.add(invoiceItem);
                //aya
                invoice.invoiceItems.add(getItemInvoice());
                main.putExtra("invoice",invoice);
                startActivity(main);
                finish();
            }
        });


        itemInvoicButtoneDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });

        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    calValueBeforeDiscount();
                    calNet();
                }
                catch (Exception e)
                {}
            }
        });
        DiscountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calDiscount();
                calValueAfterDiscount();
                calNet();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        discountValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    calDiscount();
                    calValueAfterDiscount();
                    calNet();
                }
                catch (Exception e)
                {}
            }
        });
        qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    calValueBeforeDiscount();
                    calNet();
                }
                catch (Exception e)
                {}
            }
        });
        tax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    calTaxValue();
                    calNet();
                }
                catch (Exception e)
                {}
            }
        });
    }


    public void calValueBeforeDiscount()
    {
        double Price = Double.parseDouble(price.getText().toString());
        int Qty = Integer.parseInt(qty.getText().toString());
        double res=Price*Qty;
        valueBeforDis.setText(res+"");
    }
    public void calDiscount()
    {
        double Price = Double.parseDouble(price.getText().toString());
        int Qty = Integer.parseInt(qty.getText().toString());
        double DiscountValue = Double.parseDouble(discountValue.getText().toString());
        double res;
        if(DiscountType.getSelectedItemPosition()==0)
        {
            res =(Price * Qty) - ((Price * Qty) - DiscountValue) ;
        }
        else
        {
            if(DiscountValue<=100) {
                res=  ((Price * Qty) * (DiscountValue/100)) ;
            }
            else
            {
                Toast.makeText(this, "Iscount must be less than 100", Toast.LENGTH_SHORT).show();
                discountValue.setText("0");
                DiscountValue=0;
                res= (Price * Qty) * (DiscountValue/100) ;
            }
        }
        discount.setText(res+"");
    }
    public void calValueAfterDiscount()
    {
        double valuebeforedis = Double.parseDouble(valueBeforDis.getText().toString());
        double dis = Double.parseDouble(discount.getText().toString());
        double res=valuebeforedis-dis;
        valueAfterDiscount.setText(res+"");
    }
    public void calTaxValue()
    {
        double Price = Double.parseDouble(price.getText().toString());
        int Qty = Integer.parseInt(qty.getText().toString());
        double Tax = Double.parseDouble(tax.getText().toString());
        double dis=Double.parseDouble(discount.getText().toString());
        double taxV=( (Price * Qty) - dis ) * (Tax/100) ;
        taxValue.setText(taxV+"");
    }
    public void calNet()
    {
        double Price = Double.parseDouble(price.getText().toString());
        double DiscountValue = Double.parseDouble(discountValue.getText().toString());
        double Tax = Double.parseDouble(tax.getText().toString());
        int Qty = Integer.parseInt(qty.getText().toString());
        double Net = ( (Price * Qty) - DiscountValue ) + ( ( (Price * Qty) - DiscountValue ) * Tax );
        if(DiscountType.getSelectedItemPosition()==0)
        {
            Net = ( (Price * Qty) - DiscountValue ) + ( ( (Price * Qty) - DiscountValue ) * (Tax/100) );
        }
        else
        {
            if(DiscountValue<=100) {
                double Net_without_TAX = ( (Price * Qty) - ((Price * Qty) * DiscountValue/100) );
                Net = (Net_without_TAX) + (Net_without_TAX* (Tax/100));
            }
            else
            {
                Toast.makeText(this, "Iscount must be less than 100", Toast.LENGTH_SHORT).show();
                discountValue.setText("0");
                double Net_without_TAX = ( (Price * Qty) - ((Price * Qty) * DiscountValue/100) );
                Net = (Net_without_TAX) + (Net_without_TAX* 0);
            }
        }
        net.setText(Net+"");

    }
    public InvoiceItem getItemInvoice()
    {

        InvoiceItem invoiceItem=new InvoiceItem();
        invoiceItem.setItemName(itemName.getText().toString());
        invoiceItem.setItemCode(itemCode.getText().toString());
        invoiceItem.setPrice(price.getText().toString());
        if(DiscountType.getSelectedItemPosition()==0)
        {
            invoiceItem.setDiscountAmount(discount.getText().toString());
        }
        else
        {
            invoiceItem.setDiscountPercent(discount.getText().toString());
        }

        invoiceItem.setQuantity(qty.getText().toString());
        invoiceItem.setTax(tax.getText().toString());
        invoiceItem.setExpityDate(date.getText().toString());
        calNet();
        invoiceItem.setNet(net.getText().toString());

        return invoiceItem;

    }

    public void setCurrentDateOnView() {


        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        date.setText(new StringBuilder()
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
            date.setText(new StringBuilder().append(month + 1)
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
}
