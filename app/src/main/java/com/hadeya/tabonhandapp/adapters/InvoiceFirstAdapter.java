package com.hadeya.tabonhandapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
    static int position;
    private InvoicesListAdapterListener mInvoicesAdapterListener;
    public interface InvoicesListAdapterListener {
        void onDeleteButtonClicked (int id);
        void onEditButtonClicked(int id);

    }

    public InvoiceFirstAdapter(Context cont, List<Invoice> dataSet)
    {
        context=cont;
        DataSet = dataSet;
        if (context instanceof InvoicesListAdapterListener) {
            mInvoicesAdapterListener = (InvoicesListAdapterListener) context;
        }
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.invoice_no_value)TextView invoice_no_value;
        @BindView(R.id.cust_name_value)TextView cust_name_value;
        @BindView(R.id.date_value)TextView date_value;
        @BindView(R.id.net_value)TextView net_value;
        @BindView(R.id.deleteItem)ImageView delete;
        @BindView(R.id.editItem)ImageView edit;

        public ViewHolder(View v)
        {

            super(v);
            ButterKnife.bind(this, itemView);
            delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Element " + getPosition() + " clicked.");
                        if(mInvoicesAdapterListener != null){
                            int pos=getPosition();
                            AlertDialog diaBox =AskOption(context ,pos);
                            diaBox.show();
                            // total.setText(calTotal()+"");
                        }

                    }
                });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getPosition() + " clicked.");
                    if(mInvoicesAdapterListener != null){
                        int pos=getPosition();
                        mInvoicesAdapterListener.onEditButtonClicked(pos);

                    }

                }
            });

//            }

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
    public void onBindViewHolder(final InvoiceFirstAdapter.ViewHolder holder, final int position)
    {
        if (DataSet.get(position) != null) {
            Log.d("", "Element " + position + " set.");
            holder.getInvoice_no_value().setText(DataSet.get(position).getInvoiceNo());
            holder.getCust_name_value().setText(DataSet.get(position).getCustomer().getCustName());
            holder.getDate_value().setText(DataSet.get(position).getInvoiceDate());
            holder.getNet_value().setText(DataSet.get(position).getNet());
            try
            {
               if(DataSet.get(position).getFlag().equals("1"))
               {
                   holder.delete.setVisibility(View.VISIBLE);
                   holder.edit.setVisibility(View.VISIBLE);

                }
            else
            {
                holder.delete.setVisibility(View.GONE);
                holder.edit.setVisibility(View.GONE);
            }}
            catch(Exception e)
                {
                    holder.delete.setVisibility(View.GONE);
                    holder.edit.setVisibility(View.GONE);
                }


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

    private AlertDialog AskOption(Context mContext, int pos)
    {
        position =pos;
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(mContext)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Are you Sure You want to delete this Item ? ")
                .setIcon(R.mipmap.logo)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        try
                        {
                            mInvoicesAdapterListener.onDeleteButtonClicked(position);

                        }

                        catch (Exception e)
                        {
                            System.out.println("");
                        }

                    }

                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        // mSwipeRefreshLayout.setRefreshing(false);
                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }
}
