package com.hadeya.tabonhandapp.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hadeya.tabonhandapp.app.AppController;
import com.hadeya.tabonhandapp.json.Parser;
import com.hadeya.tabonhandapp.models.Invoice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shaimaa Derbaz on 4/19/2018.
 */

public  class makeRequest {
    static List<Invoice> dataSet;

    public static List<Invoice>  getAllInvoices(Context mContext)
    {
       // List<Invoice> dataSet=new ArrayList<>();
        dataSet=new ArrayList<>();
       // RequestQueue queue = Volley.newRequestQueue(mContext);
        String Url="http://toh.hadeya.net/api/TOHInvoices/allTOHInvoices/13007";

        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                Log.d("response", response);
                List<Invoice> list= Parser.parseAllInvoices(response);
                for(int i=0;i<list.size();i++)
                {
                    Invoice invoice = list.get(i);
                    dataSet.add(invoice);


               }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Stop the refreshing indicator
                Log.d("response", error.toString());
            }
        });
       // queue.add(strReq);
        // Adding request to volley request queue
         AppController.getInstance().addToRequestQueue(strReq);

         return dataSet;
    }

   }
