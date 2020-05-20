package com.example.employeelist.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.employeelist.R;
import com.example.employeelist.database.DatabaseHelper;
import com.example.employeelist.model.TransactionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TransactionActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        dbHelper =  new DatabaseHelper(getApplicationContext());
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        clear();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(view.getContext(),MainActivity.class));

//                clear();
            }
        });

    }
    private void saveData(){
        TransactionModel t = new TransactionModel();
        EditText editTotal = findViewById(R.id.editTotal);
        EditText editName = findViewById(R.id.editName);
        EditText editPhone = findViewById(R.id.editPhone);
        EditText editSaree = findViewById(R.id.editSaree);
        EditText editFall = findViewById(R.id.editFall);
        EditText editKutchu = findViewById(R.id.editKutchu);
        EditText editBlouse = findViewById(R.id.editBlouse);
        EditText editAdvance = findViewById(R.id.editAdvance);
        EditText editDiscount = findViewById(R.id.editDiscount);
        EditText editBalance = findViewById(R.id.editBalance);
        EditText editPaid = findViewById(R.id.editPaid);

        t.setTotal(Integer.parseInt(editTotal.getText().toString()));
        t.setPhone(editPhone.getText().toString());
        t.setCustomerName(editName.getText().toString());
        t.setSaree(Integer.parseInt(editSaree.getText().toString()));
        t.setFall(Integer.parseInt(editFall.getText().toString()));
        t.setKutchu(Integer.parseInt(editKutchu.getText().toString()));
        t.setBlouse(Integer.parseInt(editBlouse.getText().toString()));
        t.setAdvance(Integer.parseInt(editAdvance.getText().toString()));
        t.setDiscount(Integer.parseInt(editDiscount.getText().toString()));
        t.setBalance(Integer.parseInt(editBalance.getText().toString()));
        t.setPaid(Integer.parseInt(editPaid.getText().toString()));
        t.setCreatedDate(getDateTime());
        dbHelper.insert(t);

    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    private void clear(){
        EditText editTotal = findViewById(R.id.editTotal);
        EditText editName = findViewById(R.id.editName);
        EditText editPhone = findViewById(R.id.editPhone);
        EditText editSaree = findViewById(R.id.editSaree);
        EditText editFall = findViewById(R.id.editFall);
        EditText editKutchu = findViewById(R.id.editKutchu);
        EditText editBlouse = findViewById(R.id.editBlouse);
        EditText editAdvance = findViewById(R.id.editAdvance);
        EditText editDiscount = findViewById(R.id.editDiscount);
        EditText editBalance = findViewById(R.id.editBalance);
        EditText editPaid = findViewById(R.id.editPaid);
        EditText editOther = findViewById(R.id.editOther);
        editName.setText("");
        editPhone.setText("");
        editTotal.setText("0");
        editSaree.setText("0");
        editFall.setText("0");
        editKutchu.setText("0");
        editBlouse.setText("0");
        editOther.setText("0");
        editAdvance.setText("0");
        editPaid.setText("0");
        editBalance.setText("0");
        editDiscount.setText("0");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_search) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private void calculateTotal(){
        EditText editTotal = findViewById(R.id.editTotal);
        EditText editSaree = findViewById(R.id.editSaree);
        EditText editFall = findViewById(R.id.editFall);
        EditText editKutchu = findViewById(R.id.editKutchu);
        EditText editBlouse = findViewById(R.id.editBlouse);
        EditText editAdvance = findViewById(R.id.editAdvance);
        EditText editDiscount = findViewById(R.id.editDiscount);
        EditText editBalance = findViewById(R.id.editBalance);
        EditText editPaid = findViewById(R.id.editPaid);
        EditText editOther = findViewById(R.id.editOther);
        int total=0;
        int advance=0;
        int discount=0;
        int paid=0;
        if (!TextUtils.isEmpty (editSaree.getText().toString())){
            total += Integer.parseInt(editSaree.getText().toString());
        }
        if (!TextUtils.isEmpty (editFall.getText().toString())){
            total += Integer.parseInt(editFall.getText().toString());
        }
        if (!TextUtils.isEmpty (editKutchu.getText().toString())){
            total += Integer.parseInt(editKutchu.getText().toString());
        }
        if (!TextUtils.isEmpty (editBlouse.getText().toString())){
            total += Integer.parseInt(editBlouse.getText().toString());
        }
        if (!TextUtils.isEmpty (editOther.getText().toString())){
            total += Integer.parseInt(editOther.getText().toString());
        }
        editTotal.setText(String.valueOf(total));
        if (!TextUtils.isEmpty (editAdvance.getText().toString())){
            advance = Integer.parseInt(editAdvance.getText().toString());
            editBalance.setText(String.valueOf(total-advance));
        }
        if (!TextUtils.isEmpty (editDiscount.getText().toString())){
            discount = Integer.parseInt(editDiscount.getText().toString());
            editBalance.setText(String.valueOf(total-advance-discount));
        }
        if (!TextUtils.isEmpty (editPaid.getText().toString())){
            discount = Integer.parseInt(editPaid.getText().toString());
            editBalance.setText(String.valueOf(total-advance-discount-paid));
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        calculateTotal();
        return super.dispatchKeyEvent(event);
    }
}
