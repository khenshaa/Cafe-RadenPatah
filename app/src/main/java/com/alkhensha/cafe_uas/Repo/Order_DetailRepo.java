package com.alkhensha.cafe_uas.Repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alkhensha.cafe_uas.DBHelper;
import com.alkhensha.cafe_uas.Model.Order_Detail;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by khenshaa on 2/4/18.
 */

public class Order_DetailRepo {
    private DBHelper dbHelper;

    public Order_DetailRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Order_Detail order_detail){
        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Order_Detail.KEY_IDorder, order_detail.idorder);
        values.put(Order_Detail.KEY_IDmenu, order_detail.idmenu);
        values.put(Order_Detail.KEY_Qty, order_detail.qty);
        values.put(Order_Detail.KEY_Totalharga, order_detail.total);

        // Inserting Row
        long order_detail_Id = db.insert(Order_Detail.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) order_detail_Id;
    }

    public void delete(int order_detail_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Order_Detail.TABLE, Order_Detail.KEY_IDdetail + "= ?", new String[] {String.valueOf(order_detail_Id) });
        db.close(); // Closing database connection
    }

    public void update(Order_Detail order_detail) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Order_Detail.KEY_IDorder, order_detail.idorder);
        values.put(Order_Detail.KEY_IDmenu,order_detail.idmenu);
        values.put(Order_Detail.KEY_Qty, order_detail.qty);
        values.put(Order_Detail.KEY_Totalharga, order_detail.total);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Order_Detail.TABLE, values, Order_Detail.KEY_IDdetail + "= ?", new String[] { String.valueOf(order_detail.orderdetail_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getOrderDetailList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  * FROM " + Order_Detail.TABLE;

        ArrayList<HashMap<String, String>> orderdetailList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> orderdetail = new HashMap<String, String>();
                orderdetail.put("iddetail", cursor.getString(cursor.getColumnIndex(Order_Detail.KEY_IDdetail)));
                orderdetail.put("idorder", cursor.getString(cursor.getColumnIndex(Order_Detail.KEY_IDorder)));
                orderdetail.put("idmenu", cursor.getString(cursor.getColumnIndex(Order_Detail.KEY_IDmenu)));
                orderdetail.put("qty",cursor.getString(cursor.getColumnIndex(Order_Detail.KEY_Qty)));
                orderdetail.put("total",cursor.getString(cursor.getColumnIndex(Order_Detail.KEY_Totalharga)));
                orderdetailList.add(orderdetail);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return orderdetailList;

    }

    public Order_Detail getOrderDetailById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT * FROM " + Order_Detail.TABLE
                + " WHERE " + Order_Detail.KEY_IDdetail + "=?";

        int iCount =0;
        Order_Detail order_detail = new Order_Detail();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                order_detail.orderdetail_ID =cursor.getInt(cursor.getColumnIndex(Order_Detail.KEY_IDdetail));
                order_detail.idorder =cursor.getInt(cursor.getColumnIndex(Order_Detail.KEY_IDorder));
                order_detail.idmenu=cursor.getInt(cursor.getColumnIndex(Order_Detail.KEY_IDmenu));
                order_detail.qty =cursor.getInt(cursor.getColumnIndex(Order_Detail.KEY_Qty));
                order_detail.total  =cursor.getFloat(cursor.getColumnIndex(Order_Detail.KEY_Totalharga));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return order_detail;
    }

}

