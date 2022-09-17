package com.example.boutique.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.boutique.adapter.PaymentAdapter;
import com.example.boutique.adapter.TransactionAdapter;
import com.example.boutique.database.DatabaseHelper;
import com.example.boutique.model.PaymentModel;
import com.example.boutique.model.TransactionModel;
import com.example.boutique.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TransactionModel> syncList = new ArrayList<>();
    private DatabaseHelper databaseHelper ;
    private TransactionAdapter adapter;
    private RelativeLayout progressBar;
    private TextView txtEmpty;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int sortBy = 0;
    private boolean phone = true;
    private String phoneValue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        adapter = new TransactionAdapter(this);
        txtEmpty = findViewById(R.id.txtEmpty);
        progressBar = findViewById(R.id.progressBar);
//        ImageView imgHome = findViewById(R.id.imgHome);
//        imgHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(v.getContext(),DashboardActivity.class));
//            }
//        });
        //Clear Database
//        ImageView imgClear = findViewById(R.id.imgSale);
//
//        imgClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(v.getContext(),TransactionActivity.class));
//            }
//        });
        Bundle b = getIntent().getExtras();

        List<TransactionModel> t  =null;
        if (b!=null && b.containsKey("phone")) {
            phone = true;
            phoneValue = b.getString("phone");
            Log.d("PHONE", phoneValue + "");
            t  = databaseHelper.getTransactionListByPhone(phoneValue,null);
            adapter.addList(t);
        }else{
            phone = false;
            t  = databaseHelper.getTransactionList(null);
        }
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
        if (phone)
        adapter.addList(databaseHelper.getTransactionListByPhone(phoneValue,null));
        else
        adapter.addList(databaseHelper.getTransactionList(null));
    }
}
