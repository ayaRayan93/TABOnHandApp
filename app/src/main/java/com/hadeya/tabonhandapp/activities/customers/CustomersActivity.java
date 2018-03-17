package com.hadeya.tabonhandapp.activities.customers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.hadeya.tabonhandapp.R;

public class CustomersActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity4_customers);

    }
}
