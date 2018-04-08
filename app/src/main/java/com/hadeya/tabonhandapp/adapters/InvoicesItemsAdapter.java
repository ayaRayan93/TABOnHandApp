package com.hadeya.tabonhandapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.ItemInvoice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shaimaa Derbaz on 08/04/2018.
 */

public class InvoicesItemsAdapter extends RecyclerView.Adapter<InvoicesItemsAdapter.ViewHolder> {

    private List<InvoiceItem> DataSet;
    private static Context context;

    public InvoicesItemsAdapter(Context cont, List<InvoiceItem> dataSet)
    {
        context=cont;
        DataSet = dataSet;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.itemName)TextView itemName;
        //@BindView(R.id.itemCode)TextView itemCode;
        //@BindView(R.id.taxSet)TextView taxSet;
        @BindView(R.id.price)TextView price;
        @BindView(R.id.value)TextView value;
        @BindView(R.id.qty)TextView qty;
        //@BindView(R.id.discount)TextView discount;
        //@BindView(R.id.tax)TextView tax;
        //@BindView(R.id.net)TextView net;

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

        public TextView getPrice() {
            return price;
        }

        public void setPrice(TextView price) {
            this.price = price;
        }

        public TextView getValue() {
            return value;
        }

        public void setValue2(TextView value2) {
            this.value = value;
        }

        public TextView getQty() {
            return qty;
        }

        public void setQty(TextView qty) {
            this.qty = qty;
        }

    }

    @Override
    public InvoicesItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity13_invoice_item, parent, false);

        return  new InvoicesItemsAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final InvoicesItemsAdapter.ViewHolder holder, int position)
    {
        if (DataSet.get(position) != null) {
            Log.d("", "Element " + position + " set.");
          //  holder.getItemName().setText(DataSet.get(position).getInvoiceNo());
            holder.getPrice().setText(DataSet.get(position).getPrice());
           // holder.getValue2().setText(DataSet.get(position).getValue());
            holder.getQty().setText(DataSet.get(position).getQuantity());
        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }

    public void filterList(List<InvoiceItem> filterdNames) {
        this.DataSet = filterdNames;
        notifyDataSetChanged();
    }


}
