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
        Bundle b = getIntent().getExtras();
        if (b!=null && b.containsKey("id")) {
            int value = b.getInt("id");
            Log.d("ID", value + "");
            TransactionModel t = dbHelper.getTransaction(value);
            setData(t);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = validate();
                if (message == "" ){
                    saveData();
                    startActivity(new Intent(view.getContext(),MainActivity.class));
                }else {
                    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
//                clear();
            }
        });

    }
    private void setData(TransactionModel t){
        EditText editId = findViewById(R.id.editId);
        EditText editName = findViewById(R.id.editName);
        EditText editPhone = findViewById(R.id.editPhone);
        EditText editSaree = findViewById(R.id.editSaree);
        EditText editFall = findViewById(R.id.editFall);
        EditText editKutchu = findViewById(R.id.editKutchu);
        EditText editBlouse = findViewById(R.id.editBlouse);
        EditText editOther = findViewById(R.id.editOther);
        EditText editTotal = findViewById(R.id.editTotal);
        EditText editAdvance = findViewById(R.id.editAdvance);
        EditText editDiscount = findViewById(R.id.editDiscount);
        EditText editPaid = findViewById(R.id.editPaid);
        EditText editBalance = findViewById(R.id.editBalance);
        editId.setText(String.valueOf(t.getId()));
        editName.setText(t.getCustomerName());
        editPhone.setText(t.getPhone());
        editTotal.setText(String.valueOf(t.getTotal()));
        editSaree.setText(String.valueOf(t.getSaree()));
        editFall.setText(String.valueOf(t.getFall()));
        editKutchu.setText(String.valueOf(t.getKutchu()));
        editBlouse.setText(String.valueOf(t.getBlouse()));
        editOther.setText(String.valueOf(t.getOther()));
        editAdvance.setText(String.valueOf(t.getAdvance()));
        editPaid.setText(String.valueOf(t.getPaid()));
        editBalance.setText(String.valueOf(t.getBalance()));
        editDiscount.setText(String.valueOf(t.getDiscount()));
    }
    private void saveData(){
        TransactionModel t = new TransactionModel();
        EditText editId = findViewById(R.id.editId);
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

        t.setId(Integer.parseInt(editId.getText().toString()));
        t.setTotal(Integer.parseInt(editTotal.getText().toString()));
        t.setPhone(editPhone.getText().toString());
        t.setCustomerName(editName.getText().toString().toUpperCase());
        int temp = 0;
        if (!TextUtils.isEmpty(editSaree.getText().toString()))
            temp = Integer.parseInt(editSaree.getText().toString());
        t.setSaree(temp);
        temp = 0;
        if (!TextUtils.isEmpty(editFall.getText().toString()))
            temp = Integer.parseInt(editFall.getText().toString());
        t.setFall(temp);
        temp = 0;
        if (!TextUtils.isEmpty(editKutchu.getText().toString()))
            temp = Integer.parseInt(editKutchu.getText().toString());
        t.setKutchu(temp);
        temp = 0;
        if (!TextUtils.isEmpty(editBlouse.getText().toString()))
            temp = Integer.parseInt(editBlouse.getText().toString());
        t.setBlouse(temp);
        temp = 0;
        if (!TextUtils.isEmpty(editAdvance.getText().toString()))
            temp = Integer.parseInt(editAdvance.getText().toString());
        t.setAdvance(temp);
        temp = 0;
        if (!TextUtils.isEmpty(editDiscount.getText().toString()))
            temp = Integer.parseInt(editDiscount.getText().toString());
        t.setDiscount(temp);
        temp = 0;
        if (!TextUtils.isEmpty(editBalance.getText().toString()))
            temp = Integer.parseInt(editBalance.getText().toString());
        t.setBalance(temp);
        temp = 0;
        if (!TextUtils.isEmpty(editPaid.getText().toString()))
            temp = Integer.parseInt(editPaid.getText().toString());
        t.setPaid(temp);
        temp = 0;
        if (!TextUtils.isEmpty(editOther.getText().toString()))
            temp = Integer.parseInt(editOther.getText().toString());
        t.setOther(temp);
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
        EditText editId = findViewById(R.id.editId);
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
        editId.setText("0");
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
    private String validate(){
        EditText editTotal = findViewById(R.id.editTotal);
        EditText editName = findViewById(R.id.editName);
        EditText editPhone = findViewById(R.id.editPhone);
        if (TextUtils.isEmpty(editName.getText().toString().trim())){
            return "Enter Name";
        }
        if (editPhone.getText().toString().trim().length()<10){
            return "Enter 10 digit phone";
        }
        if (Integer.parseInt(editTotal.getText().toString().trim())<=0){
            return "Enter price";
        }
        return "";
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
            editBalance.setText(String.valueOf(total-advance-discount-paid));
        }
        if (!TextUtils.isEmpty (editPaid.getText().toString())){
            paid = Integer.parseInt(editPaid.getText().toString());
            editBalance.setText(String.valueOf(total-advance-discount-paid));
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        calculateTotal();
        return super.dispatchKeyEvent(event);
    }
}
