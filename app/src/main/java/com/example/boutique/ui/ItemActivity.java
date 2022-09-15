package com.example.boutique.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.boutique.database.DatabaseHelper;
import com.example.boutique.model.ItemModel;
import com.example.boutique.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ItemActivity extends AppCompatActivity  {
    private DatabaseHelper dbHelper ;
    private TextView txtEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        dbHelper =  new DatabaseHelper(getApplicationContext());
        Bundle b = getIntent().getExtras();
        if(b != null && b.containsKey("id")) {
            int val = b.getInt("id");
            if (val >= 0) {
                ItemModel item = dbHelper.getItem(val);
                Log.d("SELECTED ITEM" + val, item.toString());
                setData(item);
            }
        }else if(b != null && b.containsKey("ids")){
                String vals = b.getString("ids");
                Log.d("SELECTED ITEMs",vals);
        }else {
            clear();
        }
        Button fab = findViewById(R.id.buttonItemSave);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = validate();
                if (message == "" ){
                    saveData();
                    startActivity(new Intent(view.getContext(),ItemsMainActivity.class));
                }else {
                    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
//                clear();
            }
        });

        ImageView home = findViewById(R.id.buttonHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(view.getContext(),DashboardActivity.class));
            }
        });
    }
    private void setData(ItemModel t){
        Log.d("Setting data",t.toString());
        TextView editId = findViewById(R.id.editTextItemID);
        EditText editCode = findViewById(R.id.editTextItemCode);
        EditText editName = findViewById(R.id.editTextItemName);
        EditText editDesc = findViewById(R.id.editTextItemDesc);
        EditText editPrice = findViewById(R.id.editTextItemPrice);
        editId.setText(String.valueOf(t.getId()));
        editName.setText(t.getItemName());
        editCode.setText(t.getItemCode());
        editDesc.setText(t.getItemDesc());
        editPrice.setText(String.valueOf(t.getItemPrice()));
    }
    private void saveData(){
        ItemModel t = new ItemModel();
        EditText editId = findViewById(R.id.editTextItemID);
        TextView editName = findViewById(R.id.editTextItemName);
        EditText editCode = findViewById(R.id.editTextItemCode);
        TextView editDesc = findViewById(R.id.editTextItemDesc);
        TextView editPrice = findViewById(R.id.editTextItemPrice);
        String tmp = editId.getText().toString().trim();
        if (TextUtils.isEmpty(tmp)) tmp="0";
        t.setId(Integer.parseInt(tmp));
        t.setItemName(editName.getText().toString());
        t.setItemCode(editCode.getText().toString());
        t.setItemDesc(editDesc.getText().toString());
        t.setItemPrice(Integer.parseInt(editPrice.getText().toString()));
        t.setCreated_date(getDateTime());
        dbHelper.insertItem(t);
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    private void clear(){
        EditText editId = findViewById(R.id.editTextItemID);
        TextView editName = findViewById(R.id.editTextItemName);
        EditText editCode = findViewById(R.id.editTextItemCode);
        TextView editDesc = findViewById(R.id.editTextItemDesc);
        TextView editPrice = findViewById(R.id.editTextItemPrice);
        editId.setText("0");
        editName.setText("");
        editCode.setText("");
        editDesc.setText("");
        editPrice.setText("0");
    }
    private String validate(){
        TextView editName = findViewById(R.id.editTextItemName);
        EditText editCode = findViewById(R.id.editTextItemCode);
        TextView editDesc = findViewById(R.id.editTextItemDesc);
        TextView editPrice = findViewById(R.id.editTextItemPrice);
        if (TextUtils.isEmpty(editName.getText().toString().trim())){
            return "Enter Name";
        }
        if (TextUtils.isEmpty(editCode.getText().toString().trim())){
            return "Enter item code";
        }
        if (TextUtils.isEmpty(editDesc.getText().toString().trim())){
            return "Enter item desc";
        }
        if (TextUtils.isEmpty(editPrice.getText().toString().trim())){
            return "Enter item price";
        }
        return "";
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }
}
