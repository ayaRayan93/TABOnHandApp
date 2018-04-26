package com.hadeya.tabonhandapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.hadeya.tabonhandapp.activities.customers.CustomerInvoices;
import com.hadeya.tabonhandapp.activities.items.ItemsInvoices;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getLoginUser;
import static com.hadeya.tabonhandapp.store.WriteDataToDB.storeCustomerInvoice;

/**
 * Created by AyaAli on 05/03/2018.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private List<Customer> DataSet;
    private static Context context;

    public CustomerAdapter(Context cont, List<Customer> dataSet)
    {
        context=cont;
        DataSet = dataSet;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
         @BindView(R.id.custName1)TextView title;
         @BindView(R.id.balance)TextView balance;

        public ViewHolder(View v)
        {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Log.d(TAG, "Element " + getPosition() + " clicked.");
                //storeCustomerInvoice(getLoginUser().get(0).getRepCodId(),DataSet.get(getPosition()).getId());
                Context context2 = v.getContext();
                Intent intent = new Intent(context2, CustomerInvoices.class);
                intent.putExtra("customer", DataSet.get(getPosition()));
                context2.startActivity(intent);

                }
            });
            ButterKnife.bind(this,v);

        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getText() {
            return balance;
        }

        public void setText(TextView text) {
            this.balance = text;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity4_1_customer, parent, false);

        return  new CustomerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        if (DataSet.get(position) != null)
        {
            Log.d("", "Element " + position + " set.");
            holder.getTitle().setText(DataSet.get(position).getCustName());
            holder.getText().setText(DataSet.get(position).getBalance());

        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }

    public void filterList(List<Customer> filterdNames) {
        this.DataSet = filterdNames;
        notifyDataSetChanged();
    }
}


