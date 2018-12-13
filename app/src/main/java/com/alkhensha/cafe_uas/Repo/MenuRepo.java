package com.alkhensha.cafe_uas.Repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alkhensha.cafe_uas.DBHelper;
import com.alkhensha.cafe_uas.Model.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by khenshaa on 1/28/18.
 */

public class MenuRepo {

    private DBHelper dbHelper;

    public MenuRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Menu menu) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Menu.KEY_NamaMenu, menu.name);
        values.put(Menu.KEY_Harga,menu.getHarga());
        values.put(Menu.KEY_Jenis, menu.jenis);

        // Inserting Row
        long menu_Id = db.insert(Menu.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) menu_Id;
    }

    public void delete(int menu_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Menu.TABLE, Menu.KEY_IDMenu + "= ?", new String[] {String.valueOf(menu_Id) });
        db.close(); // Closing database connection
    }

    public void update(Menu menu) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Menu.KEY_NamaMenu, menu.name);
        values.put(Menu.KEY_Harga,menu.harga);
        values.put(Menu.KEY_Jenis, menu.jenis);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Menu.TABLE, values, Menu.KEY_IDMenu + "= ?", new String[] { String.valueOf(menu.menu_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getMenuList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  * FROM " + Menu.TABLE;

        ArrayList<HashMap<String, String>> menuList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> menu = new HashMap<String, String>();
                menu.put("id", cursor.getString(cursor.getColumnIndex(Menu.KEY_IDMenu)));
                menu.put("name", cursor.getString(cursor.getColumnIndex(Menu.KEY_NamaMenu)));
                menu.put("harga", cursor.getString(cursor.getColumnIndex(Menu.KEY_Harga)));
                menu.put("jenis",cursor.getString(cursor.getColumnIndex(Menu.KEY_Jenis)));
                menuList.add(menu);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return menuList;

    }

    public Menu getMenutById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT * FROM " + Menu.TABLE
                + " WHERE " + Menu.KEY_IDMenu + "=?";

        int iCount =0;
        Menu menu = new Menu();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                menu.menu_ID =cursor.getInt(cursor.getColumnIndex(Menu.KEY_IDMenu));
                menu.name =cursor.getString(cursor.getColumnIndex(Menu.KEY_NamaMenu));
                menu.harga  =cursor.getFloat(cursor.getColumnIndex(Menu.KEY_Harga));
                menu.jenis =cursor.getString(cursor.getColumnIndex(Menu.KEY_Jenis));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return menu;
    }

    public List<Menu> getNamaMenu(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Menu.KEY_IDMenu + "," +
                Menu.KEY_NamaMenu + "," +
                Menu.KEY_Harga +
                " FROM " + Menu.TABLE;

        List<Menu> menuList2 = new ArrayList<Menu>();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Menu menu = new Menu();
                menu.setMenu_ID(cursor.getInt(cursor.getColumnIndex(Menu.KEY_IDMenu)));
                menu.setName(cursor.getString(cursor.getColumnIndex(Menu.KEY_NamaMenu)));
                menu.setHarga(cursor.getFloat(cursor.getColumnIndex(Menu.KEY_Harga)));
                menuList2.add(menu);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return menuList2;
    }

}

