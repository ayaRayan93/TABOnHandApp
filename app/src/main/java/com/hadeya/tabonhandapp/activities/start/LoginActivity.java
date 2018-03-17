package com.hadeya.tabonhandapp.activities.start;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.hadeya.tabonhandapp.R;

public class LoginActivity extends Activity
{
    Button btn_lgn;
    String result , data , userName , password , LoggedUserName ,LoggedPassword ;
    int status , LoggedUserId , RepCodeId;
    EditText UserName , Password ;
    Context context ;
    public static String PREFS_FILE="UserData";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity2_login);

        context = LoginActivity.this;
        UserName = (EditText)findViewById(R.id.etUsername);
        Password = (EditText)findViewById(R.id.etPassword);

        btn_lgn=(Button)findViewById(R.id.btnLogin);
        btn_lgn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }


}
