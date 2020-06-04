package com.example.employeelist.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.employeelist.R;
import com.example.employeelist.adapter.PayAdapter;
import com.example.employeelist.adapter.PaymentAdapter;
import com.example.employeelist.database.DatabaseHelper;
import com.example.employeelist.model.PaymentModel;
import com.example.employeelist.model.TransactionModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper ;
    private TextView txtEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        dbHelper =  new DatabaseHelper(getApplicationContext());
        clear();
        Button buttonPayment = findViewById(R.id.buttonPayment);
        Bundle b = getIntent().getExtras();
        if (b!=null && b.containsKey("id")) {
            final int value = b.getInt("id");
            TransactionModel t = dbHelper.getTransaction(value);
            setData(t);
            List<PaymentModel> payments = dbHelper.getPaymentList(value);
            if (t.getBalance()>0) {
                buttonPayment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callLoginDialog(value);
                    }
                });
                buttonPayment.setVisibility(View.VISIBLE);
            }else{
                buttonPayment.setVisibility(View.GONE);
            }
            txtEmpty = findViewById(R.id.txtEmpty);
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

//            Log.d("PAYMENT COUNT",payments.size()+"");
            for(int i=0;i<payments.size();i++){
                if (i == 0) {
                    TextView textAmount = findViewById(R.id.textAmount0);
                    TextView textCreatedDate = findViewById(R.id.textCreatedDate0);
                    textAmount.setText(payments.get(i).getAmount() + "");
                    textCreatedDate.setText(payments.get(i).getCreated_date());
                } else if (i == 1) {
                    TextView textAmount = findViewById(R.id.textAmount1);
                    TextView textCreatedDate = findViewById(R.id.textCreatedDate1);
                    textAmount.setText(payments.get(i).getAmount() + "");
                    textCreatedDate.setText(payments.get(i).getCreated_date());
                } else if (i == 2) {
                    TextView textAmount = findViewById(R.id.textAmount2);
                    TextView textCreatedDate = findViewById(R.id.textCreatedDate2);
                    textAmount.setText(payments.get(i).getAmount() + "");
                    textCreatedDate.setText(payments.get(i).getCreated_date());
                } else if (i == 3) {
                    TextView textAmount = findViewById(R.id.textAmount3);
                    TextView textCreatedDate = findViewById(R.id.textCreatedDate3);
                    textAmount.setText(payments.get(i).getAmount() + "");
                    textCreatedDate.setText(payments.get(i).getCreated_date());
                } else if (i == 4) {
                    TextView textAmount = findViewById(R.id.textAmount4);
                    TextView textCreatedDate = findViewById(R.id.textCreatedDate4);
                    textAmount.setText(payments.get(i).getAmount() + "");
                    textCreatedDate.setText(payments.get(i).getCreated_date());
                } else if (i == 5) {
                    TextView textAmount = findViewById(R.id.textAmount5);
                    TextView textCreatedDate = findViewById(R.id.textCreatedDate5);
                    textAmount.setText(payments.get(i).getAmount() + "");
                    textCreatedDate.setText(payments.get(i).getCreated_date());
                } else if (i == 6) {
                    TextView textAmount = findViewById(R.id.textAmount6);
                    TextView textCreatedDate = findViewById(R.id.textCreatedDate6);
                    textAmount.setText(payments.get(i).getAmount() + "");
                    textCreatedDate.setText(payments.get(i).getCreated_date());
                } else if (i == 7) {
                    TextView textAmount = findViewById(R.id.textAmount7);
                    TextView textCreatedDate = findViewById(R.id.textCreatedDate7);
                    textAmount.setText(payments.get(i).getAmount() + "");
                    textCreatedDate.setText(payments.get(i).getCreated_date());
                } else if (i == 8) {
                    TextView textAmount = findViewById(R.id.textAmount8);
                    TextView textCreatedDate = findViewById(R.id.textCreatedDate8);
                    textAmount.setText(payments.get(i).getAmount() + "");
                    textCreatedDate.setText(payments.get(i).getCreated_date());
                } else if (i == 9) {
                    TextView textAmount = findViewById(R.id.textAmount9);
                    TextView textCreatedDate = findViewById(R.id.textCreatedDate9);
                    textAmount.setText(payments.get(i).getAmount() + "");
                    textCreatedDate.setText(payments.get(i).getCreated_date());
                }
            }
        }else{
            buttonPayment.setVisibility(View.GONE);
        }
        Button fab = findViewById(R.id.editSave);
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

        ImageButton home = findViewById(R.id.buttonHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(view.getContext(),DashboardActivity.class));
            }
        });
    }
    private void callLoginDialog(final int value)
    {
        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.activity_payment);
        myDialog.setCancelable(true);
        final EditText editPayment =  myDialog.findViewById(R.id.editPay);
        Button buttonPayment =  myDialog.findViewById(R.id.buttonPay);
        myDialog.show();
        buttonPayment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("PAYMENT" + value, editPayment.getText().toString());
                PaymentModel paymentModel = new PaymentModel();
                if (!TextUtils.isEmpty(editPayment.getText().toString().trim())){
                    paymentModel.setTx_id(value);
                    paymentModel.setAmount(Integer.parseInt(editPayment.getText().toString()));
                    paymentModel.setCreated_date(getDateTime());
                    dbHelper.insertPayment(paymentModel);
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
                myDialog.cancel();
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

//    @Override
//    protected void onResume() {
//
//        super.onResume();
//        this.onCreate(null);
//    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        calculateTotal();
        return super.dispatchKeyEvent(event);
    }
}
