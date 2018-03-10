package com.hadeya.tabonhandapp.Activities;

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
import com.hadeya.tabonhandapp.Helpers.StaticMethods;

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
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;
        UserName = (EditText)findViewById(R.id.etUsername);
        Password = (EditText)findViewById(R.id.etPassword);

        btn_lgn=(Button)findViewById(R.id.btnLogin);
        btn_lgn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (StaticMethods.HaveNetworkConnection(context))
                {
                    userName = UserName.getText().toString();
                    password = Password.getText().toString();

                    PostUser postUser = new PostUser();
                    postUser.execute();
                }
                else
                {
                    Toast.makeText(context,getResources().getString(R.string.error_connection_eng), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class PostUser extends AsyncTask<Object, Object, String>
    {
        @Override
        protected String doInBackground(Object... params) {
            String url = StaticMethods.API_SERVICE_URL + "Account/Login";
            InputStream inputStream = null;
            JSONObject json = null;
            JSONObject jsonObject = null;
            HttpURLConnection httpcon;
            try {
                json = new JSONObject();
                json.put("UserName", userName);
                json.put("UserPassword", password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            data = json.toString();

            try {
//Connect
                httpcon = (HttpURLConnection) ((new URL(url).openConnection()));
                httpcon.setDoOutput(true);
                httpcon.setRequestProperty("Content-Type", "application/json");
                httpcon.setRequestProperty("Accept", "application/json");
                httpcon.setRequestMethod("POST");
                httpcon.connect();

//Write
                OutputStream os = httpcon.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(String.valueOf(data));
                writer.close();
                os.close();

                InputStream response1 = httpcon.getInputStream();
                status = httpcon.getResponseCode();

//Read
                BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(), "UTF-8"));

                String line = null;
                StringBuilder sb = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                br.close();
                result = sb.toString();


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPostExecute(String s) {
            try
            {
                JSONObject json = new JSONObject(result);

                RepCodeId = json.getInt("RepCodId");
                for (int i = 0 ; i <json.length();i++)
                {
                    LoggedUserName = json.getJSONObject("TohUser").getString("UserName");
                    LoggedPassword = json.getJSONObject("TohUser").getString("UserPassword");
                    LoggedUserId = json.getJSONObject("TohUser").getInt("Id");
                }

                SaveUserDataInSharedPrefrence();
                startActivity(new Intent(LoginActivity.this ,  MainActivity.class));
                LoginActivity.this.finish();
            } catch (Exception e) {
            }
        }
    }
    public void SaveUserDataInSharedPrefrence()
    {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_FILE, MODE_PRIVATE).edit();
        editor.putString("UserName", LoggedUserName);
        editor.putString("UserPassword", LoggedPassword);
        editor.putInt("Id", LoggedUserId);
        editor.apply();
    }
}
