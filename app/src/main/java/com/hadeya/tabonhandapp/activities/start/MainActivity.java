package com.hadeya.tabonhandapp.activities.start;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.hadeya.tabonhandapp.R;

public class MainActivity extends Activity
{
    Button btn_customers , btn_items;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity3_main);
        SetUp();
    }
    public void SetUp()
    {
        btn_customers=(Button)findViewById(R.id.btn_customers);
        btn_customers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  startActivity(new Intent(MainActivity.this,CustomerMainActivity.class));
               // MainActivity.this.finish();
            }
        });

        btn_items=(Button)findViewById(R.id.btn_items);
        btn_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   startActivity(new Intent(MainActivity.this,ItemsActivity.class));
               // MainActivity.this.finish();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed()
    { MainActivity.this.finish(); }
}
