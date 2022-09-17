package com.example.boutique.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boutique.adapter.ReportAdapter;
import com.example.boutique.database.DatabaseHelper;
import com.example.boutique.R;

public class ReportActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper ;
    private ReportAdapter adapter;
    private TextView txtEmpty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        adapter = new ReportAdapter(this);
        txtEmpty = findViewById(R.id.txtEmpty);

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
        adapter.addList(databaseHelper.getReports(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
/*
        case R.id.menuAdd:
            List<Integer> ids = adapter.getSelectedIds();
            //Log.d("SELECTED IDS",String.valueOf(ids));
            Intent i = new Intent(this,ItemActivity.class);
            i.putExtra("ids", String.valueOf(ids));
            startActivity(i);
            return(true);
*/
        case R.id.menuHome:
            startActivity(new Intent(this,DashboardActivity.class));
            return(true);
/*
        case R.id.menuSort:
            startActivity(new Intent(this,ItemActivity.class));
            return(true);
*/
    }
        return(super.onOptionsItemSelected(item));
    }
}
