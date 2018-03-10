package com.hadeya.tabonhandapp.Handlers;

import com.hadeya.tabonhandapp.Helpers.JSONParser;
import com.hadeya.tabonhandapp.Helpers.StaticMethods;
import com.hadeya.tabonhandapp.Models.Item;

import org.apache.http.NameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class ItemsJSONHandler
{
    public static JSONParser jParser = new JSONParser();
    public static ArrayList response;
    static Item ItemData ;
    public static ArrayList<Item> getData()
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        String ARTICLES_URL = StaticMethods.API_SERVICE_URL ;
        String url = ARTICLES_URL + "getalldata/TOHItems/13007";
        JSONArray json = null;
        json = new JSONArray();
        response = null ;
        json = jParser.makeHttpRequestArray(url, "GET", params);
        if (json != null) {
            if (json.length() > 0)
            {
                try {
                    response = new ArrayList<>();
                    for (int i = 0 ; i <json.length();i++)
                    {
                        String ItemCode = json.getJSONObject(i).getString("ItemCode").trim();
                        String ItemName = json.getJSONObject(i).getString("ItemName");
                        String ItemNameLat = json.getJSONObject(i).getString("ItemNameLat");
                        int UnitCode = json.getJSONObject(i).getInt("UnitCode");
                        String TaxSet = json.getJSONObject(i).getString("TaxSet");
                        String SelPrice1Default = json.getJSONObject(i).getString("SelPrice1Default");
                        boolean NotActive = json.getJSONObject(i).getBoolean("NotActive");

                        ItemData = new Item();

                        ItemData.setItemCode(ItemCode);
                        ItemData.setItemName(ItemName);
                        ItemData.setItemNameLat(ItemNameLat);
                        ItemData.setUnitCode(UnitCode);
                        ItemData.setTaxSet(TaxSet);
                        ItemData.setSelPrice1Default(SelPrice1Default);
                        ItemData.setNotActive(NotActive);
                        response.add(ItemData);
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    response = null;
                }
            }
        }
        return response;
    }
}
