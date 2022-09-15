package com.example.employeelist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.employeelist.R;
import com.example.employeelist.model.TransactionModel;
import com.example.employeelist.ui.TransactionActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
//            Intent shareIntent = new Intent();
//            shareIntent.setAction(Intent.ACTION_SEND);
//            shareIntent.putExtra(Intent.EXTRA_TEXT, "Id: " + t.getId() +
//                    " Name: " + t.getCustomerName() +
//                    " Balance: " + t.getBalance() +
//                    " Date: " + t.getCreatedDate());
//            shareIntent.setType("text/plain");
//            context.startActivity(Intent.createChooser(shareIntent, "Share Employee Details"));
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
