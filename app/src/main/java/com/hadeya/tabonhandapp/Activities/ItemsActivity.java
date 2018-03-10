package com.hadeya.tabonhandapp.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hadeya.tabonhandapp.Helpers.StaticMethods;
import com.hadeya.tabonhandapp.Handlers.ItemsJSONHandler;
import com.hadeya.tabonhandapp.Adapters.ItemsAdapter;
import com.hadeya.tabonhandapp.Models.Item;
import com.hadeya.tabonhandapp.R;

import java.util.ArrayList;

public class ItemsActivity extends Activity
{

    ListView listItems ;
    EditText Search;
    Context context;
    ArrayList<Item> ItemsList , itemListFromDb;
    ItemsAdapter itemsAdapter;
    ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_items);

        context = ItemsActivity.this;
        Search =(EditText)findViewById(R.id.search);
        listItems = (ListView)findViewById(R.id.listItems);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        if (StaticMethods.HaveNetworkConnection(context))
        {
            progressBar.setVisibility(View.VISIBLE);
            GetData();
            progressBar.setVisibility(View.GONE);
        }
        else
        {
            Toast.makeText(context , getResources().getString(R.string.error_connection_eng), Toast.LENGTH_SHORT).show();
        }
    }
    public void GetData()
    {
        ItemsList = new ArrayList<>();
        GetItemsList getItemsList = new GetItemsList();
        getItemsList.execute();
    }
    class GetItemsList extends AsyncTask<Void , Void , Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
            ItemsList = ItemsJSONHandler.getData();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            itemsAdapter = new ItemsAdapter(context , ItemsList);
            listItems.setAdapter(itemsAdapter);
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            startActivity(new Intent(ItemsActivity.this , MainActivity.class));
            ItemsActivity.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(ItemsActivity.this , MainActivity.class));
        ItemsActivity.this.finish();
    }
}
