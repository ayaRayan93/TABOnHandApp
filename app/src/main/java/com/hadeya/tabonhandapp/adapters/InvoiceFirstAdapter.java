package com.hadeya.tabonhandapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hadeya.tabonhandapp.R;
import com.hadeya.tabonhandapp.activities.transaction.invoices.AddItemsInvoice;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.ItemInvoice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by shaimaa Derbaz on 18/04/2018.
 */

public class InvoiceFirstAdapter extends RecyclerView.Adapter<InvoiceFirstAdapter.ViewHolder> {

    private List<Invoice> DataSet;
    private static Context context;
    private InvoicesItemsAdapterListener mInvoicesAdapterListener;
    public interface InvoicesItemsAdapterListener {
        void onDeleteButtonClicked (int id);

    }

    public InvoiceFirstAdapter(Context cont, List<Invoice> dataSet)
    {
        context=cont;
        DataSet = dataSet;
        if (context instanceof InvoicesItemsAdapterListener) {
            mInvoicesAdapterListener = (InvoicesItemsAdapterListener) context;
        }
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.invoice_no_value)TextView invoice_no_value;
        @BindView(R.id.cust_name_value)TextView cust_name_value;
        @BindView(R.id.date_value)TextView date_value;
        @BindView(R.id.net_value)TextView net_value;

        public ViewHolder(View v)
        {

            super(v);

            ButterKnife.bind(this,v);

        }

        public TextView getInvoice_no_value() {
            return invoice_no_value;
        }

        public void setInvoice_no_value(TextView invoice_no_value) {
            this.invoice_no_value = invoice_no_value;
        }

        public TextView getCust_name_value() {
            return cust_name_value;
        }

        public void setCust_name_value(TextView cust_name_value) {
            this.cust_name_value = cust_name_value;
        }

        public TextView getDate_value() {
            return date_value;
        }

        public void setDate_value(TextView date_value) {
            this.date_value = date_value;
        }

        public TextView getNet_value() {
            return net_value;
        }

        public void setNet_value(TextView net_value) {
            this.net_value = net_value;
        }


    }

    @Override
    public InvoiceFirstAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity18_first_invoice_item, parent, false);

        return  new InvoiceFirstAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final InvoiceFirstAdapter.ViewHolder holder, int position)
    {
        if (DataSet.get(position) != null) {
            Log.d("", "Element " + position + " set.");
            holder.getInvoice_no_value().setText(DataSet.get(position).getInvoiceNo());
            holder.getCust_name_value().setText(DataSet.get(position).getCustomer().getCustName());
            holder.getDate_value().setText(DataSet.get(position).getInvoiceDate());
            holder.getNet_value().setText(DataSet.get(position).getNet());

        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }

    public void filterList(List<Invoice> filterdNames) {
        this.DataSet = filterdNames;
        notifyDataSetChanged();
    }


}
