package com.hadeya.tabonhandapp.activities.transaction.invoices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hadeya.tabonhandapp.R;

import butterknife.ButterKnife;

public class AddItemsInvoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity14_add_item_to_invoice_main);
        ButterKnife.bind(this);
        Button saveInvoice = (Button) findViewById(R.id.save);
        saveInvoice.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent main = new Intent("PrintInvoice");
                startActivity(main);
            }
        }

        );
    }
}
