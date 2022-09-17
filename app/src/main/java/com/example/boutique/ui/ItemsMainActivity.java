package com.example.boutique.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boutique.adapter.ItemAdapter;
import com.example.boutique.database.DatabaseHelper;
import com.example.boutique.model.ItemModel;
import com.example.boutique.R;
//import com.example.employeelist.adapter.MyListAdapter;

import java.util.List;

public class ItemsMainActivity extends AppCompatActivity {
    private ItemAdapter adapter ;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_main);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        List<ItemModel> myListData = db.getItemList(null);
        adapter = new ItemAdapter(myListData);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.itemsRecyclerView);
        adapter.setContext(this);
//        MyListAdapter adapter = new MyListAdapter(myListData);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
