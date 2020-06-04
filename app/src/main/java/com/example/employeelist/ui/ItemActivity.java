package com.example.employeelist.ui;

import android.content.Intent;
import android.graphics.Picture;
import android.os.Bundle;

import com.example.employeelist.R;
import com.example.employeelist.database.DatabaseHelper;
import com.example.employeelist.model.ItemModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        if(b != null && b.containsKey("id")){
            int val = b.getInt("id");
            if (val>=0){
                ItemModel item = dbHelper.getItem(val);
                Log.d("SELECTED ITEM"+val,item.toString());
                setData(item);
            }
        }else
        clear();
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

        ImageView home = findViewById(R.id.imgHome);
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
        editId.setText(String.valueOf(t.getId()));
        editName.setText(t.getItemName());
        editCode.setText(t.getItemCode());
        editDesc.setText(t.getItemDesc());
    }
    private void saveData(){
        ItemModel t = new ItemModel();
        EditText editId = findViewById(R.id.editTextItemID);
        TextView editName = findViewById(R.id.editTextItemName);
        EditText editCode = findViewById(R.id.editTextItemCode);
        TextView editDesc = findViewById(R.id.editTextItemDesc);

        t.setId(Integer.parseInt(editId.getText().toString()));
        t.setItemName(editName.getText().toString());
        t.setItemCode(editCode.getText().toString());
        t.setItemDesc(editDesc.getText().toString());
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
        editId.setText("0");
        editName.setText("");
        editCode.setText("");
        editDesc.setText("");

    }
    private String validate(){
        TextView editName = findViewById(R.id.editTextItemName);
        EditText editCode = findViewById(R.id.editTextItemCode);
        TextView editDesc = findViewById(R.id.editTextItemDesc);
        if (TextUtils.isEmpty(editName.getText().toString().trim())){
            return "Enter Name";
        }
        if (TextUtils.isEmpty(editCode.getText().toString().trim())){
            return "Enter item code";
        }
        if (TextUtils.isEmpty(editDesc.getText().toString().trim())){
            return "Enter item desc";
        }
        return "";
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }
}
