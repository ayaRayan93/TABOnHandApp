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
import com.hadeya.tabonhandapp.Activities.ItemsActivity;
import com.hadeya.tabonhandapp.Models.Item;
import com.hadeya.tabonhandapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class ItemsAdapter extends ArrayAdapter<Item>
{
    private Context context;
    private ArrayList<Item> values;
    View rowView ;
    TextView ItemCode,ItemName, TaxSet;
    LinearLayout itemLinearlayout ;

    public ItemsAdapter(Context context1, ArrayList<Item> values)
    {
        super(context1, R.layout.item_template, values);
        this.context = context1;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = inflater.inflate(R.layout.item_template, parent, false);

        ItemCode = (TextView)rowView.findViewById(R.id.itemCode);
        ItemName = (TextView)rowView.findViewById(R.id.itemName);
        TaxSet = (TextView)rowView.findViewById(R.id.taxSet);

        ItemCode.setText(values.get(position).getItemCode());
        ItemName.setText(values.get(position).getItemName());
        TaxSet.setText(values.get(position).getTaxSet()+"");


        itemLinearlayout = (LinearLayout) rowView.findViewById(R.id.item);
        itemLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                context.startActivity(new Intent(context, ItemInvoiceDetails.class));
                ((Activity)context).finish();
            }
        });

        return rowView;
    }
}
