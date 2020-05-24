package com.example.employeelist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.employeelist.model.CustomerModel;
import com.example.employeelist.model.PaymentModel;
import com.example.employeelist.model.ReportModel;
import com.example.employeelist.model.TransactionModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    private static final String TRANS_TABLE = "trans";
    private static final String PAYMENT_TABLE = "payment";

    // Table columns
    public static final String ID = "id";
    public static final String CUSTOMERNAME = "customer_name";
    public static final String PHONE = "phone";
    public static final String SAREE_PRICE = "saree_price";
    public static final String FALL_PRICE = "fall_price";
    public static final String KUTCHU_PRICE = "kutchu_price";
    public static final String BLOUSE_PRICE = "blouse_price";
    public static final String OTHER_PRICE = "other_price";
    public static final String TOTAL_PRICE = "total_price";
    public static final String ADVANCE_PAID = "advance_paid";
    public static final String DISCOUNT = "discount";
    public static final String AMOUNT_PAID = "amount_payment";
    public static final String BALANCE = "balance";
    public static final String CREATED_DATE = "created_date";
    public static final String TX_ID = "tx_id";

    // Database Information
    static final String DB_NAME = "BOUTIQUE.DB";

    // database version
    static final int DB_VERSION = 8;

    // Creating table query
    private static String CREATE_TABLE = "create table " + TRANS_TABLE + " (id"
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CUSTOMERNAME + " TEXT NOT NULL, " +
            PHONE + " TEXT NOT NULL ," +
            SAREE_PRICE + " NUMBER NOT NULL," +
            FALL_PRICE + " NUMBER NOT NULL," +
            KUTCHU_PRICE + " NUMBER NOT NULL," +
            BLOUSE_PRICE + " NUMBER NOT NULL," +
            OTHER_PRICE + " NUMBER NOT NULL," +
            TOTAL_PRICE + " NUMBER NOT NULL," +
            ADVANCE_PAID + " NUMBER NOT NULL," +
            DISCOUNT + " NUMBER NOT NULL," +
            AMOUNT_PAID + " NUMBER NOT NULL," +
            BALANCE + " NUMBER NOT NULL, " +
            CREATED_DATE + " TEXT NOT NULL" +
            ");";

    private static String CREATE_PAYMENT_TABLE = "create table " + PAYMENT_TABLE + " (id"
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TX_ID + " NUMBER NOT NULL," +
            AMOUNT_PAID + " NUMBER NOT NULL," +
            CREATED_DATE + " TEXT NOT NULL" +
            ");";
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("CREATING TABLE",db.toString());
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_PAYMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TRANS_TABLE);
        onCreate(db);
    }

    public void insertPayment(PaymentModel t) {
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TX_ID, t.getTx_id());
        contentValues.put(AMOUNT_PAID, t.getAmount());
        contentValues.put(CREATED_DATE, t.getCreatedDate());
        long ins = db.insert(PAYMENT_TABLE, null, contentValues);
    }

    public void insert(TransactionModel t) {
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(EMP_ID, employee.getId());
        contentValues.put(CUSTOMERNAME, t.getCustomerName());
        contentValues.put(PHONE, t.getPhone());
        contentValues.put(SAREE_PRICE, t.getSaree());
        contentValues.put(FALL_PRICE, t.getFall());
        contentValues.put(KUTCHU_PRICE, t.getKutchu());
        contentValues.put(BLOUSE_PRICE, t.getBlouse());
        contentValues.put(OTHER_PRICE, t.getOther());
        contentValues.put(TOTAL_PRICE, t.getBlouse());
        contentValues.put(DISCOUNT, t.getDiscount());
        contentValues.put(ADVANCE_PAID, t.getAdvance());
        contentValues.put(BALANCE, t.getBalance());
        contentValues.put(CREATED_DATE, t.getCreatedDate());
        if (t.getId()>0) {
            String[] args = new String[]{t.getId()+""};
            db.update(TRANS_TABLE, contentValues, "id=? ", args);
        }else {
            long ins = db.insert(TRANS_TABLE, null, contentValues);
        }
    }
    private SQLiteDatabase openDB() {
        return this.getWritableDatabase();
    }

    private void closeDB(SQLiteDatabase db) {
        db.close();
    }

    public List<CustomerModel> getCustomerList(String sortBy) {
        if (sortBy == null) sortBy = CUSTOMERNAME;
        SQLiteDatabase db=this.getReadableDatabase();
        List<CustomerModel> list = new ArrayList<>();
        Cursor cursor = db.query(true, TRANS_TABLE, new String[]{"trim(upper(customer_name)) customer_name,phone"}, null, null, null, null, sortBy, null);
        if (cursor.moveToFirst()) {
            do {
                CustomerModel t = new CustomerModel();
                t.setCustomerName(cursor.getString(cursor.getColumnIndex(CUSTOMERNAME)));
                t.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
                list.add(t);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB(db);
        return list;
    }
    public List<TransactionModel> getTransactionList(String sortBy) {
        if (sortBy == null) sortBy = "id desc";
        SQLiteDatabase db=this.getReadableDatabase();
        List<TransactionModel> list = new ArrayList<>();
        Cursor cursor = db.query(TRANS_TABLE, null, null, null, null, null, sortBy, null);
        if (cursor.moveToFirst()) {
            do {
                TransactionModel t = new TransactionModel();
                t.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                Cursor paymentCursor = db.query(PAYMENT_TABLE, new String[] {"SUM(amount_payment) amount_payment"}, "tx_id=?", new String[]{""+t.getId()}, null, null, null, null);
                if (paymentCursor.moveToFirst()) {
                    t.setPaid(paymentCursor.getInt(paymentCursor.getColumnIndex(AMOUNT_PAID)));
                    Log.d("TOTAL PAYMENT",""+t.getPaid());
                }
                t.setCustomerName(cursor.getString(cursor.getColumnIndex(CUSTOMERNAME)));
                t.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
                t.setSaree(cursor.getInt(cursor.getColumnIndex(SAREE_PRICE)));
                t.setFall(cursor.getInt(cursor.getColumnIndex(FALL_PRICE)));
                t.setKutchu(cursor.getInt(cursor.getColumnIndex(KUTCHU_PRICE)));
                t.setBlouse(cursor.getInt(cursor.getColumnIndex(BLOUSE_PRICE)));
                t.setOther(cursor.getInt(cursor.getColumnIndex(OTHER_PRICE)));
                t.setTotal(t.getSaree()+t.getBlouse()+t.getFall()+t.getKutchu()+t.getOther());
                t.setDiscount(cursor.getInt(cursor.getColumnIndex(DISCOUNT)));
                t.setAdvance(cursor.getInt(cursor.getColumnIndex(ADVANCE_PAID)));
                t.setBalance(t.getTotal()-t.getAdvance()-t.getDiscount()-t.getPaid());
                t.setCreatedDate(cursor.getString(cursor.getColumnIndex(CREATED_DATE)));
                list.add(t);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB(db);
        return list;
    }
    public List<TransactionModel> getTransactionListByPhone(String phone, String orderBy) {
        if (orderBy == null) orderBy = "id desc";
        SQLiteDatabase db=this.getReadableDatabase();
        List<TransactionModel> list = new ArrayList<>();
        Cursor cursor = db.query(TRANS_TABLE, null, "phone=?", new String[]{phone}, null, null, orderBy, null);
        if (cursor.moveToFirst()) {
            do {
                TransactionModel t = new TransactionModel();
                t.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                Cursor paymentCursor = db.query(PAYMENT_TABLE, new String[] {"SUM(amount_payment) amount_payment"}, "tx_id=?", new String[]{""+t.getId()}, null, null, null, null);
                if (paymentCursor.moveToFirst()) {
                    t.setPaid(paymentCursor.getInt(paymentCursor.getColumnIndex(AMOUNT_PAID)));
                    Log.d("TOTAL PAYMENT",""+t.getPaid());
                }
                t.setCustomerName(cursor.getString(cursor.getColumnIndex(CUSTOMERNAME)));
                t.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
                t.setSaree(cursor.getInt(cursor.getColumnIndex(SAREE_PRICE)));
                t.setFall(cursor.getInt(cursor.getColumnIndex(FALL_PRICE)));
                t.setKutchu(cursor.getInt(cursor.getColumnIndex(KUTCHU_PRICE)));
                t.setBlouse(cursor.getInt(cursor.getColumnIndex(BLOUSE_PRICE)));
                t.setOther(cursor.getInt(cursor.getColumnIndex(OTHER_PRICE)));
                t.setDiscount(cursor.getInt(cursor.getColumnIndex(DISCOUNT)));
                t.setAdvance(cursor.getInt(cursor.getColumnIndex(ADVANCE_PAID)));
                t.setTotal(t.getSaree()+t.getBlouse()+t.getFall()+t.getKutchu()+t.getOther());
                t.setBalance(t.getTotal()-t.getAdvance()-t.getDiscount()-t.getPaid());
                t.setCreatedDate(cursor.getString(cursor.getColumnIndex(CREATED_DATE)));
                list.add(t);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB(db);
        return list;
    }
    public TransactionModel getTransaction(int id) {
        TransactionModel t = new TransactionModel();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query(TRANS_TABLE, null, "id=?", new String[]{id+""}, null, null, null, null);
        if (cursor.moveToFirst()) {
            t.setId(id);
            Cursor paymentCursor = db.query(PAYMENT_TABLE, new String[] {"SUM(amount_payment) amount_payment"}, "tx_id=?", new String[]{""+t.getId()}, null, null, null, null);
            if (paymentCursor.moveToFirst()) {
                t.setPaid(paymentCursor.getInt(paymentCursor.getColumnIndex(AMOUNT_PAID)));
                Log.d("TOTAL PAYMENT",""+t.getPaid());
            }
            t.setCustomerName(cursor.getString(cursor.getColumnIndex(CUSTOMERNAME)));
            t.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            t.setSaree(cursor.getInt(cursor.getColumnIndex(SAREE_PRICE)));
            t.setFall(cursor.getInt(cursor.getColumnIndex(FALL_PRICE)));
            t.setKutchu(cursor.getInt(cursor.getColumnIndex(KUTCHU_PRICE)));
            t.setBlouse(cursor.getInt(cursor.getColumnIndex(BLOUSE_PRICE)));
            t.setOther(cursor.getInt(cursor.getColumnIndex(OTHER_PRICE)));
            t.setDiscount(cursor.getInt(cursor.getColumnIndex(DISCOUNT)));
            t.setAdvance(cursor.getInt(cursor.getColumnIndex(ADVANCE_PAID)));
            t.setTotal(t.getSaree()+t.getBlouse()+t.getFall()+t.getKutchu()+t.getOther());
            t.setBalance(t.getTotal()-t.getAdvance()-t.getDiscount()-t.getPaid());
            t.setCreatedDate(cursor.getString(cursor.getColumnIndex(CREATED_DATE)));
        }
        cursor.close();
        closeDB(db);
        return t;
    }
    public List<ReportModel> getReports(int month) {
        if (month == 0) month = 1;
        List<ReportModel> list = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query(TRANS_TABLE, new String[]{"sum(total_price) total","substr(created_date,0,8) month"}, null,null, "month", null, "month desc", "10");
        if (cursor.moveToFirst()) {
            ReportModel t = new ReportModel();
            t.setMonth(cursor.getString(cursor.getColumnIndex("month")));
            t.setTotal(cursor.getInt(cursor.getColumnIndex("total")));
            list.add(t);
        }
        cursor.close();
        closeDB(db);
        return list;
    }
}