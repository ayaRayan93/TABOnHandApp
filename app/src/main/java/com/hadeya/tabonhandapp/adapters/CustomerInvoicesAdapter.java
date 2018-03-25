package com.hadeya.tabonhandapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.models.CustomerInvoice;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AyaAli on 17/03/2018.
 */

public class CustomerInvoicesAdapter extends RecyclerView.Adapter<CustomerInvoicesAdapter.ViewHolder> {

    private List<CustomerInvoice> DataSet;
    private static Context context;

    public CustomerInvoicesAdapter(Context cont, List<CustomerInvoice> dataSet)
    {
        context=cont;
        DataSet = dataSet;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.invoiceCustomerNo)TextView invoiceCustomerNo;
        @BindView(R.id.date)TextView date;
        @BindView(R.id.value1)TextView value1;

        public ViewHolder(View v)
        {
            super(v);

            ButterKnife.bind(this,v);

        }

        public TextView getInvoiceCustomerNo() {
            return invoiceCustomerNo;
        }

        public void setInvoiceCustomerNo(TextView invoiceCustomerNo) {
            this.invoiceCustomerNo = invoiceCustomerNo;
        }

        public TextView getDate() {
            return date;
        }

        public void setDate(TextView date) {
            this.date = date;
        }

        public TextView getValue1() {
            return value1;
        }

        public void setValue1(TextView value1) {
            this.value1 = value1;
        }
    }

    @Override
    public CustomerInvoicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity8_1_customer_invoice, parent, false);

        return  new CustomerInvoicesAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final CustomerInvoicesAdapter.ViewHolder holder, int position)
    {
        if (DataSet.get(position) != null) {
            Log.d("", "Element " + position + " set.");
            holder.getInvoiceCustomerNo().setText(DataSet.get(position).getInvoiceNo());
            holder.getDate().setText(DataSet.get(position).getDate());
            holder.getValue1().setText(DataSet.get(position).getValue());
        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }

    public void filterList(List<CustomerInvoice> filterdNames) {
        this.DataSet = filterdNames;
        notifyDataSetChanged();
    }
}
