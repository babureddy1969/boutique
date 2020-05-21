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
import com.example.employeelist.model.ReportModel;
import com.example.employeelist.model.TransactionModel;
import com.example.employeelist.ui.TransactionActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ItemHolder> {

    private Context context;
    private List<ReportModel> list = new ArrayList<>();

    public ReportAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<ReportModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ReportAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_report, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        final ReportModel t = list.get(position);
        holder.mTxtName.setText(t.getMonth());
        holder.mTxtSalary.setText(String.valueOf(t.getTotal()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mItem;
        private TextView mTxtName, mTxtId, mTxtSalary, mTxtAge;

        ItemHolder(View itemView) {
            super(itemView);
            mItem = itemView.findViewById(R.id.item);
            mTxtName = itemView.findViewById(R.id.txtMonth);
            mTxtSalary = itemView.findViewById(R.id.txtTotal);
        }
    }
}
