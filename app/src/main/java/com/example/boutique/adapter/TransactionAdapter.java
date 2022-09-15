package com.example.boutique.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.boutique.model.TransactionModel;
import com.example.boutique.R;
import com.example.boutique.ui.TransactionActivity;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ItemHolder> {

    private Context context;
    private List<TransactionModel> list = new ArrayList<>();

    public TransactionAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<TransactionModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItem() {
        try {
            list.clear();
            notifyDataSetChanged();
        } catch (Exception e) {
        }
    }

    @Override
    public TransactionAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        final TransactionModel t = list.get(position);
        holder.mTxtName.setText(t.getCustomerName());
        holder.mTxtDate.setText("Purchase date: " + t.getCreatedDate());
        holder.mTxtBalance.setText("Balance Rs. " + t.getBalance());
        holder.mTxtPaid.setText("Paid Rs. " +t.getPaid());
        holder.mItem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), TransactionActivity.class);
            intent.putExtra("id", t.getId());
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mItem;
        private TextView mTxtName, mTxtBalance, mTxtPaid, mTxtDate;

        ItemHolder(View itemView) {
            super(itemView);
            mItem = itemView.findViewById(R.id.item);
            mTxtName = itemView.findViewById(R.id.txtName);
            mTxtBalance = itemView.findViewById(R.id.txtBalance);
            mTxtPaid = itemView.findViewById(R.id.txtPayment);
            mTxtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
