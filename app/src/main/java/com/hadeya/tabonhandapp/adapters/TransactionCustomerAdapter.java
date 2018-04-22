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
import com.hadeya.tabonhandapp.activities.customers.CustomerInvoices;
import com.hadeya.tabonhandapp.activities.transaction.invoices.AddInvoice;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.models.Customer_Balance;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getLoginUser;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.storeCustomerInvoice;

/**
 * Created by AyaAli on 2018-04-18.
 */

public class TransactionCustomerAdapter  extends RecyclerView.Adapter<TransactionCustomerAdapter.ViewHolder> {

    private List<Customer_Balance> DataSet;
    private static Context context;

    public TransactionCustomerAdapter(Context cont, List<Customer_Balance> dataSet)
    {
        context=cont;
        DataSet = dataSet;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        //@BindView(R.id.image)ImageView poster;
        @BindView(R.id.title)TextView title;
         @BindView(R.id.balance)TextView balance;

        public ViewHolder(View v)
        {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getPosition() + " clicked.");
                    Context context2 = v.getContext();
                    Intent intent = new Intent(context2, AddInvoice.class);
                    intent.putExtra("customer", DataSet.get(getPosition()));
                    context2.startActivity(intent);

                }
            });
            ButterKnife.bind(this,v);

        }

      /*  public ImageView getPoster() {
            return1 poster;
        }*/

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

       public TextView getBalance() {
            return balance;
        }

        public void setBalance(TextView text) {
            this.balance = text;
        }
    }

    @Override
    public TransactionCustomerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity17_transaction_customer, parent, false);

        return  new TransactionCustomerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final TransactionCustomerAdapter.ViewHolder holder, int position)
    {
        if (DataSet.get(position) != null)
        {
            Log.d("", "Element " + position + " set.");
            holder.getTitle().setText(DataSet.get(position).getAraName());
            holder.getBalance().setText(DataSet.get(position).getBalance());
        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }

    public void filterList(List<Customer_Balance> filterdNames) {
        this.DataSet = filterdNames;
        notifyDataSetChanged();
    }
}


