package com.example.employeelist.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.employeelist.R;
import com.example.employeelist.model.ItemModel;
import com.example.employeelist.model.MyListData;
import com.example.employeelist.ui.ItemActivity;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private Context context;

    private List<Integer> selectedIds = new ArrayList<>();
    private List<ItemModel> listdata;
    public ItemAdapter(List<ItemModel> listdata) {
        this.listdata = listdata;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public List<Integer> getSelectedIds(){
        return selectedIds;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemModel myListData = listdata.get(position);
//        final MyListData myListData = listdata[position];
//        holder.textItemId.setText(String.valueOf(myListData.getId()));
        holder.textItemName.setText(myListData.getItemName());
        holder.textItemCode.setText(myListData.getItemCode());
        holder.textItemPrice.setText(String.valueOf(myListData.getItemPrice()));
//        holder.imageView.setImageResource(listdata.get(position).getItemImage());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getItemName(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), ItemActivity.class);
                intent.putExtra("id", myListData.getId());
                context.startActivity(intent);
            }
        });
        holder.selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on checkbox: "+myListData.getId(),Toast.LENGTH_LONG).show();
                if (selectedIds.contains(myListData.getId()))
                    selectedIds.remove(selectedIds.indexOf(myListData.getId()));
                else
                    selectedIds.add(myListData.getId());
                Log.d("selected ids",String.valueOf(selectedIds));

            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView imageView;
        public TextView textItemId, textItemName, textItemCode, textItemDesc, textItemPrice;
        public CheckBox selection ;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
//            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
//            this.textItemId = (TextView) itemView.findViewById(R.id.textListItemId);
            this.textItemName = (TextView) itemView.findViewById(R.id.textListItemName);
            this.textItemCode = (TextView) itemView.findViewById(R.id.textListItemCode);
//            this.textItemDesc = (TextView) itemView.findViewById(R.id.textListItemDesc);
            this.textItemPrice = (TextView) itemView.findViewById(R.id.textListItemPrice);
            this.selection = (CheckBox) itemView.findViewById(R.id.checkBoxSelectSaree);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}