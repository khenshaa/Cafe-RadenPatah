package com.alkhensha.cafe_uas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alkhensha.cafe_uas.Model.Menu;
import com.alkhensha.cafe_uas.Model.Order2;
import com.alkhensha.cafe_uas.Model.Order_Detail;

/**
 * Created by khenshaa on 1/28/18.
 */
public class DBHelper  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "cafe3000.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Menu.TABLE  + "("
                + Menu.KEY_IDMenu  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Menu.KEY_NamaMenu + " TEXT, "
                + Menu.KEY_Harga + " FLOAT, "
                + Menu.KEY_Jenis + " TEXT )";

        String CREATE_TABLE_ORDER = "CREATE TABLE " + Order2.TABLE  + "("
                + Order2.KEY_IDOrder + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Order2.KEY_NoTable + " INTEGER, "
                + Order2.KEY_TotalHarga + " FLOAT)";



        String CREATE_TABLE_ORDERDETAIL = "CREATE TABLE " + Order_Detail.TABLE + "("
                + Order_Detail.KEY_IDdetail + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Order_Detail.KEY_IDorder + " INTEGER,"
                + Order_Detail.KEY_IDmenu + " INTEGER,"
                + Order_Detail.KEY_Qty + " INTEGER,"
                + Order_Detail.KEY_Totalharga + " FLOAT)";

        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_ORDER);
        db.execSQL(CREATE_TABLE_ORDERDETAIL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Menu.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Order2.TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + Order_Detail.TABLE);

        // Create tables again
        onCreate(db);

    }

}