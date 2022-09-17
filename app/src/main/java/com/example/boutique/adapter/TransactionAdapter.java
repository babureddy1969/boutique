package com.example.boutique.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.boutique.model.TransactionModel;
import com.example.boutique.R;
import com.example.boutique.ui.PaymentActivity;
import com.example.boutique.ui.TransactionActivity;
import com.example.boutique.util.DateTimeUtil;

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
        holder.mTxtDate.setText(DateTimeUtil.getDateTime(t.getCreatedDate()));
        holder.mTxtBalance.setText(""+t.getBalance());
        holder.mTxtPaid.setText("Paid Rs. " +t.getPaid());
        holder.mTxtPhone.setText("Ph:"+t.getPhone());
        holder.mItem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), TransactionActivity.class);
            intent.putExtra("id", t.getId());
            context.startActivity(intent);
            }
    });
        holder.mTxtPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), PaymentActivity.class);
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
        private TextView mTxtName, mTxtPaid, mTxtDate, mTxtShowPayments, mTxtBalance, mTxtPayments, mTxtPhone;

        ItemHolder(View itemView) {
            super(itemView);
            mItem = itemView.findViewById(R.id.item);
            mTxtName = itemView.findViewById(R.id.txtName);
            mTxtBalance = itemView.findViewById(R.id.txtBalance);
            mTxtPaid = itemView.findViewById(R.id.txtPayment);
            mTxtDate = itemView.findViewById(R.id.txtDate);
            mTxtPayments = itemView.findViewById(R.id.txtPayments);
            mTxtPhone = itemView.findViewById(R.id.txtPhone);
        }
    }
}
