package com.example.employeelist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeelist.R;
import com.example.employeelist.model.PaymentModel;

import java.util.List;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.PersonViewHolder>{

    List<PaymentModel> persons;

    public PayAdapter(List<PaymentModel> persons){
        this.persons = persons;
    }
    @NonNull
    @Override
    public PayAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_sale, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.amount.setText(persons.get(i).getAmount());
        personViewHolder.createdDate.setText(persons.get(i).getCreated_date());
    }
    @Override
    public int getItemCount() {
        return persons.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView amount;
        TextView createdDate;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardPayment);
            amount = (TextView)itemView.findViewById(R.id.textAmount);
            createdDate = (TextView)itemView.findViewById(R.id.textCreatedDate);
        }
    }

}