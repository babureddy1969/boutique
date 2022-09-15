package com.example.boutique.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.example.boutique.database.DatabaseHelper;
import com.example.boutique.model.PaymentModel;
import com.example.boutique.model.TransactionModel;
import com.example.boutique.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                findViewById(R.id.editSave).setVisibility(View.GONE);
                buttonPayment.setVisibility(View.GONE);
            }
            txtEmpty = findViewById(R.id.txtEmpty);
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }else {
            buttonPayment.setVisibility(View.GONE);
            if (!checkContactPermission()) {
                requestContactPermission();
            }
            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(contactPickerIntent, 1);
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

//        ImageView home = findViewById(R.id.buttonHome);
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    startActivity(new Intent(view.getContext(),DashboardActivity.class));
//            }
//        });
    }
    private boolean checkContactPermission(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }
    private void requestContactPermission () {
        String[] permission = {Manifest.permission.READ_CONTACTS};
        ActivityCompat.requestPermissions(this,permission,1);
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

        if (!TextUtils.isEmpty(editId.getText().toString())) {
            t.setId(Integer.parseInt(editId.getText().toString()));
        }
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
        t.setUpdatedDate(getDateTime());
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
        editId.setText("");
        editName.setText("");
        editPhone.setText("");
        editTotal.setText("");
        editSaree.setText("");
        editFall.setText("");
        editKutchu.setText("");
        editBlouse.setText("");
        editOther.setText("");
        editAdvance.setText("");
        editPaid.setText("");
        editBalance.setText("");
        editDiscount.setText("");

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);


        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case 1:
                    Cursor cursor1 = null;
                    try {
                        String phoneNo = null ;
                        String name = null;
                        Uri uri = data.getData();
                        cursor1 = getContentResolver().query(uri, null, null, null, null);
                        if (cursor1.moveToFirst()) {
                            phoneNo = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            name = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                            ((EditText)findViewById(R.id.editPhone)).setText(phoneNo);
                            ((EditText)findViewById(R.id.editName)).setText(name);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (!cursor1.isClosed()) cursor1.close();
                }
                    break;
            }
        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }
}
