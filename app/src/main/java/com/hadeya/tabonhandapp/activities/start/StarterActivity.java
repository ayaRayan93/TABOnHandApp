package com.hadeya.tabonhandapp.activities.start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hadeya.tabonhandapp.R;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getLoginUser;

public class StarterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_starter);

        Thread timer=new Thread() {
            public void run() {

                try
                {
                    sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    if(getLoginUser()!=null) {
                        Intent main = new Intent("MainTopicsActivity");
                        startActivity(main);
                    }
                    else
                    {
                        Intent main = new Intent("login");
                        startActivity(main);
                    }
                }
            }

        };
        timer.start();
    }
}
