package com.alkhensha.cafe_uas.Model;

/**
 * Created by khenshaa on 1/28/18.
 */

public class Menu {

// Labels table name
    public static final String TABLE = "Menu";

    // Labels Table Columns names
    public static final String KEY_IDMenu = "id";
    public static final String KEY_NamaMenu = "name";
    public static final String KEY_Harga = "harga";
    public static final String KEY_Jenis = "jenis";

    // property help us to keep data
    public int menu_ID;
    public String name;
    public float harga;
    public String jenis;

    public int getMenu_ID(){
        return this.menu_ID;
    }
    public String getName(){
        return this.name;
    }
    public float getHarga(){
        return this.harga;
    }
    public String getJenis(){
        return this.jenis;
    }

    public void setMenu_ID(int menu_ID){
        this.menu_ID =menu_ID;
    }
    public void setName( String name){
        this.name = name;
    }
    public void setHarga(float harga){
        this.harga = harga;
    }
    public void setJenis(String jenis){
        this.jenis = jenis;
    }


}
