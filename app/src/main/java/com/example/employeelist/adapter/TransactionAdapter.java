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
import com.example.employeelist.model.TransactionModel;
import com.example.employeelist.ui.MainActivity;
import com.example.employeelist.ui.TransactionActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ItemHolder> {

    private Context context;
    private List<TransactionModel> list = new ArrayList<>();
    private int[] images = {R.drawable.ic_profile1, R.drawable.ic_profile2, R.drawable.ic_profile3, R.drawable.ic_profile4, R.drawable.ic_profile5, R.drawable.ic_profile6};
    private Random random = new Random();

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        final TransactionModel t = list.get(position);
        holder.mTxtName.setText(t.getCustomerName());
        holder.mTxtId.setText(String.valueOf(t.getId()));
        holder.mTxtSalary.setText(t.getCreatedDate());
//        Log.d("CREATED DATE",t.toString());
        holder.mTxtAge.setText(String.valueOf(t.getBalance()));
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
        private CircleImageView mImgView;
        private TextView mTxtName, mTxtId, mTxtSalary, mTxtAge;

        ItemHolder(View itemView) {
            super(itemView);
            mItem = itemView.findViewById(R.id.item);
            mImgView = itemView.findViewById(R.id.imgProfile);
            mTxtName = itemView.findViewById(R.id.txtName);
            mTxtId = itemView.findViewById(R.id.txtId);
            mTxtSalary = itemView.findViewById(R.id.txtSalary);
            mTxtAge = itemView.findViewById(R.id.txtAge);
        }
    }
}
