package com.hadeya.tabonhandapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.models.Item;
import com.hadeya.tabonhandapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

private List<Item> DataSet;
private static Context context;

public ItemsAdapter(Context cont, List<Item> dataSet)
        {
        context=cont;
        DataSet = dataSet;
        }

public  class ViewHolder extends RecyclerView.ViewHolder
{
    @BindView(R.id.itemName)TextView itemName;
    @BindView(R.id.itemCode)TextView itemCode;
     @BindView(R.id.taxSet)TextView taxSet;

    public ViewHolder(View v)
    {
        super(v);

        ButterKnife.bind(this,v);

    }

    public TextView getItemName() {
        return itemName;
    }

    public void setItemName(TextView itemName) {
        this.itemName = itemName;
    }

    public TextView getItemCode() {
        return itemCode;
    }

    public void setItemCode(TextView itemCode) {
        this.itemCode = itemCode;
    }

    public TextView getTaxSet() {
        return taxSet;
    }

    public void setTaxSet(TextView taxSet) {
        this.taxSet = taxSet;
    }
}

    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity6_1_item, parent, false);

        return  new ItemsAdapter.ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final ItemsAdapter.ViewHolder holder, int position)
    {
        if (DataSet.get(position) != null)
        {
            Log.d("", "Element " + position + " set.");
            holder.getItemName().setText(DataSet.get(position).getItemName());
            holder.getItemCode().setText(DataSet.get(position).getItemCode());
            holder.getTaxSet().setText(DataSet.get(position).getTaxSet());

        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }

    public void filterList(List<Item> filterdNames) {
        this.DataSet = filterdNames;
        notifyDataSetChanged();
    }


}
