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
import com.hadeya.tabonhandapp.activities.transaction.invoices.AddItemsInvoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.ItemInvoice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by shaimaa Derbaz on 08/04/2018.
 */

public class InvoicesItemsAdapter extends RecyclerView.Adapter<InvoicesItemsAdapter.ViewHolder> {

    private List<InvoiceItem> DataSet;
    private static Context context;
    private InvoicesItemsAdapterListener mInvoicesAdapterListener;
    public interface InvoicesItemsAdapterListener {
        void onDeleteButtonClicked (int id);

    }

    public InvoicesItemsAdapter(Context cont, List<InvoiceItem> dataSet)
    {
        context=cont;
        DataSet = dataSet;
        if (context instanceof InvoicesItemsAdapterListener)
            mInvoicesAdapterListener = (InvoicesItemsAdapterListener) context;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.itemName)TextView itemName;
        @BindView(R.id.itemCode)TextView itemCode;
        @BindView(R.id.tax)TextView tax;
        @BindView(R.id.price)TextView price;
        @BindView(R.id.qty)TextView qty;
        @BindView(R.id.discount)TextView discount;
        @BindView(R.id.net)TextView net;
        //@BindView(R.id.net)TextView net;

        public ViewHolder(View v)
        {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getPosition() + " clicked.");
                    if(mInvoicesAdapterListener != null)
                        mInvoicesAdapterListener.onDeleteButtonClicked(getPosition());

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

        public TextView getPrice() {
            return price;
        }

        public void setPrice(TextView price) {
            this.price = price;
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


        public TextView getQty() {
            return qty;
        }

        public void setQty(TextView qty) {
            this.qty = qty;
        }

        public void setItemCode(TextView itemCode) {
            this.itemCode = itemCode;
        }
        public TextView getItemCode() {
            return itemCode;
        }
        public void setNet(TextView net) {
            this.net = net;
        }
        public TextView getNet() {
            return net;
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
            holder.getItemName().setText(DataSet.get(position).getItemName());
            holder.getItemCode().setText(DataSet.get(position).getItemCode());
            holder.getDiscount().setText(DataSet.get(position).getDiscountAmount());
            holder.getQty().setText(DataSet.get(position).getQuantity());
            holder.getTax().setText(DataSet.get(position).getTax());
            holder.getNet().setText(DataSet.get(position).getNet());
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
