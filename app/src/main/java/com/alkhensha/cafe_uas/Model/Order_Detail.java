package com.alkhensha.cafe_uas.Model;

/**
 * Created by khenshaa on 2/4/18.
 */

public class Order_Detail {
    public static final String TABLE = "OrderDetail";

    public static final String KEY_IDdetail ="iddetail";
    public static final String KEY_IDorder = "idorder";
    public static final String KEY_IDmenu = "idmenu";
    public static final String KEY_Qty = "qty";
    public static final String KEY_Totalharga = "total";

    public int orderdetail_ID;
    public int idorder;
    public int idmenu;
    public int qty;
    public float total;


}
