package com.example.employeelist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.employeelist.R;
import com.example.employeelist.adapter.CustomerAdapter;
import com.example.employeelist.adapter.TransactionAdapter;
import com.example.employeelist.database.DatabaseHelper;
import com.example.employeelist.model.TransactionModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    private List<TransactionModel> syncList = new ArrayList<>();
    private DatabaseHelper databaseHelper ;
    private CustomerAdapter adapter;
    private RelativeLayout progressBar;
    private TextView txtEmpty;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int sortBy = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        adapter = new CustomerAdapter(this);
        txtEmpty = findViewById(R.id.txtEmpty);
//        progressBar = findViewById(R.id.progressBar);

        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.invalidate();
        //Check RecyclerView is empty
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {

            @Override
            public void onChanged() {
                super.onChanged();
                checkEmpty();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                checkEmpty();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                checkEmpty();
            }

            void checkEmpty() {
                txtEmpty.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
            }
        });
        populateList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtEmpty.setVisibility(View.GONE);
        populateList();
    }

    /*
     * Every time activity start, Check table and sync data.
     * Otherwise load data from server.
     * */
    private void populateList() {
        adapter.addList(databaseHelper.getCustomerList(null));
    }
}
