package com.alkhensha.cafe_uas.Repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alkhensha.cafe_uas.DBHelper;
import com.alkhensha.cafe_uas.Model.Menu;
import com.alkhensha.cafe_uas.Model.Order2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by khenshaa on 1/29/18.
 */

public class OrderRepo {

    private DBHelper dbHelper;

    public OrderRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Order2 order2) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Order2.KEY_NoTable, order2.notable);
        values.put(Order2.KEY_TotalHarga, order2.totalharga);

        // Inserting Row
        long order_ID = db.insert(order2.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) order_ID;
    }

    public void delete(int order_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Order2.TABLE, Order2.KEY_IDOrder + "= ?", new String[] { String.valueOf(order_Id) });
        db.close(); // Closing database connection
    }

    public void update(Order2 order2) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Order2.KEY_NoTable, order2.notable);
        values.put(Order2.KEY_TotalHarga, order2.totalharga);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Order2.TABLE, values, Order2.KEY_IDOrder + "= ?", new String[] { String.valueOf(order2.order_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getOrderList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT * FROM " + Order2.TABLE;

        ArrayList<HashMap<String, String>> orderList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> order = new HashMap<String, String>();
                order.put("idorder", cursor.getString(cursor.getColumnIndex(Order2.KEY_IDOrder)));
                order.put("notable", cursor.getString(cursor.getColumnIndex(Order2.KEY_NoTable)));
                order.put("totalharga", cursor.getString(cursor.getColumnIndex(Order2.KEY_TotalHarga)));
                orderList.add(order);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return orderList;

    }


    public Order2 getOrderById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT * FROM " + Order2.TABLE
                + " WHERE " + Order2.KEY_IDOrder + "=?";

        int iCount =0;
        Order2 order2 = new Order2();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                order2.order_ID = cursor.getInt(cursor.getColumnIndex(Order2.KEY_IDOrder));
                order2.notable = cursor.getInt(cursor.getColumnIndex(Order2.KEY_NoTable));
                order2.totalharga = cursor.getFloat(cursor.getColumnIndex(Order2.KEY_TotalHarga));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return order2;
    }

    public List<Order2> getOrderTable(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Order2.KEY_IDOrder + "," +
                Order2.KEY_NoTable + "," +
                Order2.KEY_TotalHarga +
                " FROM " + Order2.TABLE;

        List<Order2> order2List = new ArrayList<Order2>();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Order2 order2 = new Order2();
                order2.setOrder_ID(cursor.getInt(cursor.getColumnIndex(Order2.KEY_IDOrder)));
                order2.setNotable(cursor.getInt(cursor.getColumnIndex(Order2.KEY_NoTable)));
                order2.setTotalharga(cursor.getFloat(cursor.getColumnIndex(Order2.KEY_TotalHarga)));
                order2List.add(order2);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return order2List;
    }

}
