package com.hadeya.tabonhandapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.activities.items.ItemsInvoices;
import com.hadeya.tabonhandapp.activities.transaction.invoices.AddItemsInvoice;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by AyaAli on 05/04/2018.
 */

public class transactionItemAdapter extends RecyclerView.Adapter<transactionItemAdapter.ViewHolder> {

    private List<Item> DataSet;
    private static Context context;
    Invoice invoice;

    public transactionItemAdapter(Context cont, List<Item> dataSet,Invoice invoice)
    {
        context=cont;
        DataSet = dataSet;
        this.invoice=invoice;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.itemName)TextView itemName;
        @BindView(R.id.itemCode)TextView itemCode;
        @BindView(R.id.taxSet)TextView taxSet;

        public ViewHolder(View v)
        {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getPosition() + " clicked.");

                    Context context2 = v.getContext();
                    Intent intent = new Intent(context2, AddItemsInvoice.class);
                    intent.putExtra("item", DataSet.get(getPosition()));
                    intent.putExtra("invoice",invoice);
                    context2.startActivity(intent);

                }
            });

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
    public transactionItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity15_item, parent, false);

        return  new transactionItemAdapter.ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final transactionItemAdapter.ViewHolder holder, int position)
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
