package com.example.employeelist.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.employeelist.R;
import com.example.employeelist.adapter.ItemAdapter;
//import com.example.employeelist.adapter.MyListAdapter;
import com.example.employeelist.database.DatabaseHelper;
import com.example.employeelist.model.ItemModel;
import com.example.employeelist.model.MyListData;

import java.util.List;

public class ItemsMainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.menuAdd:
            startActivity(new Intent(this,ItemActivity.class));
            return(true);
        case R.id.menuHome:
            startActivity(new Intent(this,DashboardActivity.class));
            return(true);
        case R.id.menuSort:
            startActivity(new Intent(this,ItemActivity.class));
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_main);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        List<ItemModel> myListData = db.getItemList(null);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.itemsRecyclerView);
        ItemAdapter adapter = new ItemAdapter(myListData);
        adapter.setContext(this);
//        MyListAdapter adapter = new MyListAdapter(myListData);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
