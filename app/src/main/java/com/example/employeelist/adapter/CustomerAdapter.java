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
import com.example.employeelist.model.CustomerModel;
import com.example.employeelist.model.TransactionModel;
import com.example.employeelist.ui.CustomerActivity;
import com.example.employeelist.ui.TransactionActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ItemHolder> {

    private Context context;
    private List<CustomerModel> list = new ArrayList<>();

    public CustomerAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<CustomerModel> list) {
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
    public CustomerAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_customer, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        final CustomerModel t = list.get(position);
        holder.mTxtPhone.setText(t.getPhone());
        holder.mTxtName.setText(t.getCustomerName());
//        holder.mItem.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(v.getContext(), CustomerActivity.class);
//            intent.putExtra("id", t.getId());
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
        private CircleImageView mImgView;
        private TextView mTxtName, mTxtId, mTxtPhone;

        ItemHolder(View itemView) {
            super(itemView);
            mItem = itemView.findViewById(R.id.item);
            mImgView = itemView.findViewById(R.id.imgProfile);
            mTxtName = itemView.findViewById(R.id.txtName);
            mTxtPhone = itemView.findViewById(R.id.txtPhone);
        }
    }
}
