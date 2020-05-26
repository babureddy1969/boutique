package com.example.employeelist.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.employeelist.R;
import com.example.employeelist.model.PaymentModel;

import java.util.ArrayList;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ItemHolder> {

    private Context context;
    private List<PaymentModel> list = new ArrayList<>();

    public PaymentAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<PaymentModel> list) {
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
    public PaymentAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_payment, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        final PaymentModel t = list.get(position);
        Log.d("PAYMENT DETAILS",t.toString());
        holder.mTxtAmount.setText(t.getAmount());
        holder.mTxtCreatedDate.setText(t.getCreated_date());
//        holder.mItem.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(v.getContext(), MainActivity.class);
//            intent.putExtra("phone", t.getPhone());
//            context.startActivity(intent);
//        }
//    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mItem;
        private TextView mTxtCreatedDate,  mTxtAmount;

        ItemHolder(View itemView) {
            super(itemView);
            mItem = itemView.findViewById(R.id.relativeID);
            mTxtAmount = itemView.findViewById(R.id.textAmount);
            mTxtCreatedDate = itemView.findViewById(R.id.textCreatedDate);
        }
    }
}
