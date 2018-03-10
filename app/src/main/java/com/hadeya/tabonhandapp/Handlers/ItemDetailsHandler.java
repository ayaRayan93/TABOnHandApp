package com.hadeya.tabonhandapp.Handlers;

import com.hadeya.tabonhandapp.Helpers.JSONParser;
import com.hadeya.tabonhandapp.Helpers.StaticMethods;
import com.hadeya.tabonhandapp.Models.InvoiceDetails;
import com.hadeya.tabonhandapp.Models.Item;
import com.hadeya.tabonhandapp.Models.ItemDetails;

import org.apache.http.NameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class ItemDetailsHandler
{
    public static JSONParser jParser = new JSONParser();
    public static ArrayList<ItemDetails> ArraylistItemDetails;
    public static ArrayList<InvoiceDetails> ArraylistInvoiceDetails;
    static ItemDetails ItemDetailData ;
    static InvoiceDetails InvoiceDetailData ;
    public static ArrayList<ItemDetails> getData()
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        String ARTICLES_URL = StaticMethods.API_SERVICE_URL ;
        String url = ARTICLES_URL + "TOHInvoices/repCodeTOHInvoices/13007";
        JSONArray json = null;
        json = new JSONArray();
        ArraylistItemDetails = null ;
        ArraylistInvoiceDetails = null ;
        json = jParser.makeHttpRequestArray(url, "GET", params);
        if (json != null) {
            if (json.length() > 0)
            {
                try {
                    ArraylistItemDetails = new ArrayList<>();
                    ArraylistInvoiceDetails = new ArrayList<>();

                    ItemDetailData = new ItemDetails();

                    String InvoiceDate = json.getJSONObject(0).getString("InvoiceDate").trim();
                    String RefNO = json.getJSONObject(0).getString("RefNO");
                    String Notes = json.getJSONObject(0).getString("Notes");
                    String InvoiceNo = json.getJSONObject(0).getString("InvoiceNo");
                    int InvoiceTypeId = json.getJSONObject(0).getInt("InvoiceTypeId");
                    int CustmerId = json.getJSONObject(0).getInt("CustmerId");
                    int Id = json.getJSONObject(0).getInt("Id");
                    int PayementTypeId = json.getJSONObject(0).getInt("PayementTypeId");
                    int RepCodeId = json.getJSONObject(0).getInt("RepCodeId");

                    ItemDetailData.setCustmerId(CustmerId);
                    ItemDetailData.setId(Id);
                    ItemDetailData.setPayementTypeId(PayementTypeId);
                    ItemDetailData.setRepCodeId(RepCodeId);
                    ItemDetailData.setInvoiceTypeId(InvoiceTypeId);
                    ItemDetailData.setNotes(Notes);
                    ItemDetailData.setRefNO(RefNO);
                    ItemDetailData.setInvoiceDate(InvoiceDate);
                    ItemDetailData.setInvoiceNo(InvoiceNo);

                    int size = json.getJSONObject(0).getJSONArray("TOHInvoiceDetails").length() ;

                    for (int i = 0 ; i < size ;i++)
                    {
                        InvoiceDetailData = new InvoiceDetails();

                        String ItemCode = json.getJSONObject(0).getJSONArray("TOHInvoiceDetails").getJSONObject(i).getString("ItemCode");
                        String ItemName = json.getJSONObject(0).getJSONArray("TOHInvoiceDetails").getJSONObject(i).getString("ItemName");
                        String ExpityDate = json.getJSONObject(0).getJSONArray("TOHInvoiceDetails").getJSONObject(i).getString("ExpityDate");

                        int id = json.getJSONObject(0).getJSONArray("TOHInvoiceDetails").getJSONObject(i).getInt("Id");
                        int InvoiceId = json.getJSONObject(0).getJSONArray("TOHInvoiceDetails").getJSONObject(i).getInt("InvoiceId");
                        int Tax = json.getJSONObject(0).getJSONArray("TOHInvoiceDetails").getJSONObject(i).getInt("Tax");
                        int Price = json.getJSONObject(0).getJSONArray("TOHInvoiceDetails").getJSONObject(i).getInt("Price");
                        int DiscountPercent = json.getJSONObject(0).getJSONArray("TOHInvoiceDetails").getJSONObject(i).getInt("DiscountPercent");
                        int DiscountAmount = json.getJSONObject(0).getJSONArray("TOHInvoiceDetails").getJSONObject(i).getInt("DiscountAmount");

                        InvoiceDetailData.setItemCode(ItemCode);
                        InvoiceDetailData.setItemName(ItemName);
                        InvoiceDetailData.setExpityDate(ExpityDate);
                        InvoiceDetailData.setId(id);
                        InvoiceDetailData.setInvoiceId(InvoiceId);
                        InvoiceDetailData.setTax(Tax);
                        InvoiceDetailData.setPrice(Price);
                        InvoiceDetailData.setDiscountPercent(DiscountPercent);
                        InvoiceDetailData.setDiscountAmount(DiscountAmount);

                        ArraylistInvoiceDetails.add(InvoiceDetailData);
                        ItemDetailData.setTOHInvoiceDetails(ArraylistInvoiceDetails);
                    }
                    ArraylistItemDetails.add(ItemDetailData);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    ArraylistItemDetails = null;
                }
            }
        }
        return ArraylistItemDetails;
    }
}
