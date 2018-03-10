package com.hadeya.tabonhandapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hadeya.tabonhandapp.Activities.ItemInvoiceDetails;
import com.hadeya.tabonhandapp.Models.InvoiceDetails;
import com.hadeya.tabonhandapp.Models.Item;
import com.hadeya.tabonhandapp.Models.ItemDetails;
import com.hadeya.tabonhandapp.R;

import java.util.ArrayList;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class ItemDetailsAdapter extends ArrayAdapter<InvoiceDetails>
{
    private Context context;
    private ArrayList<InvoiceDetails> values;
    View rowView ;
    TextView ItemCode,ItemUnit, Price , Quantity , Tax , Discount , Net , Value , ItemName;

    public ItemDetailsAdapter(Context context1, ArrayList<InvoiceDetails> values)
    {
        super(context1, R.layout.item_detail_template, values);
        this.context = context1;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = inflater.inflate(R.layout.item_detail_template, parent, false);

        ItemCode = (TextView)rowView.findViewById(R.id.itemCode);
        ItemName = (TextView)rowView.findViewById(R.id.itemName);
        Quantity = (TextView)rowView.findViewById(R.id.qty);
        Price = (TextView)rowView.findViewById(R.id.price);
        Tax = (TextView)rowView.findViewById(R.id.tax);
        Discount = (TextView)rowView.findViewById(R.id.discount);
        Net = (TextView)rowView.findViewById(R.id.net);
        Value = (TextView)rowView.findViewById(R.id.value);

        ItemCode.setText(values.get(position).getItemCode());
        ItemName.setText(values.get(position).getItemName());
        Quantity.setText(values.get(position).getQuantity()+"");
        Price.setText(values.get(position).getPrice()+"");
        Tax.setText(values.get(position).getTax()+"");
        Discount.setText(values.get(position).getDiscountAmount()+"");
        Net.setText(values.get(position).getDiscountPercent()+"");
        Value.setText(values.get(position).getTax()+"");

        return rowView;
    }
}
