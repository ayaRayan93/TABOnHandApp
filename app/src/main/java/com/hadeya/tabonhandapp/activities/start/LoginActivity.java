package com.hadeya.tabonhandapp.activities.start;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.models.User;
import com.hadeya.tabonhandapp.store.DataBaseHelper;
import com.hadeya.tabonhandapp.store.ReadDataFromDB;
import com.hadeya.tabonhandapp.store.WriteDataToDB;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.LoginLocalUser;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getLoginUser;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.StoreUser;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.addUser;

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
                userName = UserName.getText().toString();
                password = Password.getText().toString();
                try {

                   /* DataBaseHelper dataBaseHelper=new DataBaseHelper(getBaseContext());
                    WriteDataToDB.mdatabase=dataBaseHelper;*/
               /*User user=new User();
                user.setRepCodId("11");
                user.setPassword("123");
                user.setUserName("aya");
                addUser(user);*/
               if(!userName.isEmpty()&&!password.isEmpty()) {
                   List<User> list = LoginLocalUser(userName, password);
                   if (list!=null&&list.size()!= 0) {
                       Intent main = new Intent("MainTopicsActivity");
                       startActivity(main);
                       finish();
                   } else {
                       try {
                           StoreUser(userName, password,context, LoginActivity.this);

                       } catch (Exception e) {
                           Toast.makeText(context, "UserName or Password incorrect", Toast.LENGTH_SHORT).show();
                       }
                   }
               }
               else
               {
                   Toast.makeText(context, "Enter UserName and Password ", Toast.LENGTH_SHORT).show();
               }
                }
                catch(Exception e)
                {
                    Toast.makeText(context, "UserName or Password incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}