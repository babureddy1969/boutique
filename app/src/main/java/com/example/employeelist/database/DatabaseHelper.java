package com.example.employeelist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.employeelist.model.TransactionModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "trans";

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

    // Database Information
    static final String DB_NAME = "BOUTIQUE.DB";

    // database version
    static final int DB_VERSION = 7;

    // Creating table query
    private static String CREATE_TABLE = "create table " + TABLE_NAME + " (id"
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

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("CREATING TABLE",db.toString());
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
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
        contentValues.put(AMOUNT_PAID, t.getPaid());
        contentValues.put(BALANCE, t.getBalance());
        contentValues.put(CREATED_DATE, t.getCreatedDate());
        long ins = db.insert(TABLE_NAME, null, contentValues);
//        Log.d("DB","INSERT " + ins + " " + t.toString());
//        contentValues = new ContentValues();
//        contentValues.put(CREATED_DATE, t.getCreatedDate());
//        String[] args = {ins+""};
//        db.update(TABLE_NAME,contentValues,null,null);
    }
    private SQLiteDatabase openDB() {
        return this.getWritableDatabase();
    }

    private void closeDB(SQLiteDatabase db) {
        db.close();
    }

    public List<TransactionModel> getTransactionList(String sortBy) {
        if (sortBy == null) sortBy = "id desc";
        SQLiteDatabase db=this.getReadableDatabase();
        List<TransactionModel> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, sortBy, null);
        Log.d("DB","LOOKING FOR DATA");
        if (cursor.moveToFirst()) {
            Log.d("DB","FOUND DATA");
            do {
                TransactionModel t = new TransactionModel();
                t.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                t.setCustomerName(cursor.getString(cursor.getColumnIndex(CUSTOMERNAME)));
                t.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
                t.setSaree(cursor.getInt(cursor.getColumnIndex(SAREE_PRICE)));
                t.setFall(cursor.getInt(cursor.getColumnIndex(FALL_PRICE)));
                t.setKutchu(cursor.getInt(cursor.getColumnIndex(KUTCHU_PRICE)));
                t.setBlouse(cursor.getInt(cursor.getColumnIndex(BLOUSE_PRICE)));
                t.setOther(cursor.getInt(cursor.getColumnIndex(OTHER_PRICE)));
                t.setPaid(cursor.getInt(cursor.getColumnIndex(AMOUNT_PAID)));
                t.setDiscount(cursor.getInt(cursor.getColumnIndex(DISCOUNT)));
                t.setAdvance(cursor.getInt(cursor.getColumnIndex(ADVANCE_PAID)));
                t.setBalance(cursor.getInt(cursor.getColumnIndex(BALANCE)));
                t.setTotal(cursor.getInt(cursor.getColumnIndex(TOTAL_PRICE)));
                t.setCreatedDate(cursor.getString(cursor.getColumnIndex(CREATED_DATE)));
                list.add(t);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB(db);
        return list;
    }
}