package com.alkhensha.cafe_uas.Model;

/**
 * Created by khenshaa on 1/29/18.
 */

public class Order2 {

    public static final String TABLE = "Order2";

    // Labels Table Columns names
    public static final String KEY_IDOrder = "idorder";
    public static final String KEY_NoTable = "notable";
    public static final String KEY_TotalHarga = "totalharga";

    // property help us to keep data
    public int order_ID;
    public int notable;
    public float totalharga;

    public int getOrder_ID(){
        return this.order_ID;
    }
    public int getNotable(){ return  this.notable;}
    public float getTotalharga(){ return this.totalharga;}

    public void setOrder_ID(int order_ID){
        this.order_ID =order_ID;
    }
    public void setNotable(int notable){ this.notable =notable;}
    public void setTotalharga (float totalharga ){this.totalharga = totalharga;}

}
