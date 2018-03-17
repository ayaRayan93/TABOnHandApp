package com.hadeya.tabonhandapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class ItemInvoicesAdapter extends RecyclerView.Adapter<ItemInvoicesAdapter.ViewHolder> {

    private List<InvoiceItem> DataSet;
    private static Context context;

    public ItemInvoicesAdapter(Context cont, List<InvoiceItem> dataSet)
    {
        context=cont;
        DataSet = dataSet;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.itemName)TextView itemName;
        @BindView(R.id.itemCode)TextView itemCode;
        @BindView(R.id.taxSet)TextView taxSet;
        @BindView(R.id.price)TextView price;
        @BindView(R.id.value)TextView value;
        @BindView(R.id.qty)TextView qty;
        @BindView(R.id.discount)TextView discount;
        @BindView(R.id.tax)TextView tax;
        @BindView(R.id.net)TextView net;

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

        public TextView getPrice() {
            return price;
        }

        public void setPrice(TextView price) {
            this.price = price;
        }

        public TextView getValue() {
            return value;
        }

        public void setValue(TextView value) {
            this.value = value;
        }

        public TextView getQty() {
            return qty;
        }

        public void setQty(TextView qty) {
            this.qty = qty;
        }

        public TextView getDiscount() {
            return discount;
        }

        public void setDiscount(TextView discount) {
            this.discount = discount;
        }

        public TextView getTax() {
            return tax;
        }

        public void setTax(TextView tax) {
            this.tax = tax;
        }

        public TextView getNet() {
            return net;
        }

        public void setNet(TextView net) {
            this.net = net;
        }
    }

    @Override
    public ItemInvoicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity7_1_item_invoice, parent, false);

        return  new ItemInvoicesAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ItemInvoicesAdapter.ViewHolder holder, int position)
    {
        if (DataSet.get(position) != null)
        {
            Log.d("", "Element " + position + " set.");
            holder.getItemName().setText(DataSet.get(position).getItemName());
            holder.getItemCode().setText(DataSet.get(position).getItemCode());
            holder.getTaxSet().setText(DataSet.get(position).getTax());
            holder.getPrice().setText(DataSet.get(position).getPrice());
            holder.getValue().setText(DataSet.get(position).getTax());
            holder.getQty().setText(DataSet.get(position).getQuantity());
            holder.getDiscount().setText(DataSet.get(position).getDiscountAmount());
            holder.getTax().setText(DataSet.get(position).getTax());
            holder.getNet().setText(DataSet.get(position).getDiscountPercent());        }
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
