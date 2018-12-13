package com.alkhensha.cafe_uas.OrderDetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alkhensha.cafe_uas.MenuSpinnerAdapter;
import com.alkhensha.cafe_uas.Model.Order2;
import com.alkhensha.cafe_uas.Model.Order_Detail;
import com.alkhensha.cafe_uas.OrderActivity.OrderDetail;
import com.alkhensha.cafe_uas.OrderSpinnerAdapter;
import com.alkhensha.cafe_uas.R;
import com.alkhensha.cafe_uas.Repo.MenuRepo;
import com.alkhensha.cafe_uas.Repo.OrderRepo;
import com.alkhensha.cafe_uas.Repo.Order_DetailRepo;

import java.util.List;


/**
 * Created by khenshaa on 2/4/18.
 */

public class OrderDetailDetail extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnSave1, btnDelete1;
    Button btnClose1;
    EditText editIDOrder, editTot;
    TextView quantityText, mTvidmenu, mTvhargamenu;

    //jika gagal didelete
    Spinner mSpinMenu, mSpinrOrder;

    int quantity;
    private int _Orderdetail_Id = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailorder_detail);

        btnSave1 = (Button) findViewById(R.id.btnSave1);
        btnDelete1 = (Button) findViewById(R.id.btnDelete1);
        btnClose1 = (Button) findViewById(R.id.btnClose1);

        editIDOrder = (EditText) findViewById(R.id.editIDorder);

        editTot = (EditText) findViewById(R.id.editTot);
        quantityText = (TextView) findViewById(R.id.quantity_number);

        //jika gagal di delete
        mSpinMenu = (Spinner) findViewById(R.id.spinMenu);
        mSpinMenu.setOnItemSelectedListener(this);
        mTvidmenu = (TextView) findViewById(R.id.tvidmenu);
        mTvhargamenu = (TextView) findViewById(R.id.tvhargamenu);
        loadMenu();

        mSpinrOrder= (Spinner)findViewById(R.id.spinOrder);
        mSpinrOrder.setOnItemSelectedListener(this);
        loadOrder();


        btnSave1.setOnClickListener(this);
        btnDelete1.setOnClickListener(this);
        btnClose1.setOnClickListener(this);

        _Orderdetail_Id = 0;
        Intent intent = getIntent();
        _Orderdetail_Id = intent.getIntExtra("orderdetail_Id", 0);
        Order_DetailRepo repo = new Order_DetailRepo(this);
        Order_Detail order_detail = new Order_Detail();
        order_detail = repo.getOrderDetailById(_Orderdetail_Id);

        editIDOrder.setText(String.valueOf(order_detail.idorder));
        editTot.setText(String.valueOf(order_detail.total));



    }

    public void increment (View view){
        quantity = quantity + 1;
        display(quantity);
    }
    public void decrement(View view){
        quantity = quantity - 1;
        display(quantity);
    }
    private void display(int number){
        TextView quantityText = (TextView) findViewById(R.id.quantity_number);
        quantityText.setText("" + number);
    }



    @Override
    public void onClick(View v) {

        if (v == findViewById(R.id.btnSave1)) {
            Order_DetailRepo repo = new Order_DetailRepo(this);
            Order_Detail order_detail = new Order_Detail();


            order_detail.idorder = Integer.parseInt(editIDOrder.getText().toString());
            order_detail.idmenu = Integer.parseInt(mTvidmenu.getText().toString());
            order_detail.qty = Integer.parseInt(quantityText.getText().toString());
            float totalharga = Float.parseFloat(mTvhargamenu.getText().toString());
            totalharga = (order_detail.qty * totalharga );
            order_detail.total = totalharga;
            order_detail.orderdetail_ID = _Orderdetail_Id;

            if (_Orderdetail_Id == 0) {
                _Orderdetail_Id = repo.insert(order_detail);
                Toast.makeText(this, "New Data Insert", Toast.LENGTH_SHORT).show();

            } else {
                repo.update(order_detail);
                Toast.makeText(this, "Data Record Updated", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v == findViewById(R.id.btnDelete1)) {
            Order_DetailRepo repo  = new Order_DetailRepo(this);
            repo.delete(_Orderdetail_Id);
            Toast.makeText(this, "Data record Deleted", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if(v == findViewById(R.id.btnClose1)){
            finish();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        if (parentView == findViewById(R.id.spinMenu)){
            com.alkhensha.cafe_uas.Model.Menu selected = (com.alkhensha.cafe_uas.Model.Menu) parentView.getItemAtPosition(position);

            mTvidmenu.setText("" + selected.getMenu_ID());
            mTvhargamenu.setText("" + selected.getHarga());
        }
        if (parentView == findViewById(R.id.spinOrder)){
            Order2 selected2 = (Order2) parentView.getItemAtPosition(position);
            editIDOrder.setText("" + selected2.getOrder_ID());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {



    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void loadMenu(){
        MenuSpinnerAdapter menuAdapter;
        MenuRepo db = new MenuRepo(getApplicationContext());
        List<com.alkhensha.cafe_uas.Model.Menu> menus = db.getNamaMenu();
        menuAdapter = new MenuSpinnerAdapter(OrderDetailDetail.this, android.R.layout.simple_spinner_item, menus);
        mSpinMenu.setAdapter(menuAdapter);
        menuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void loadOrder(){
        OrderSpinnerAdapter orderAdapter;
        OrderRepo db = new OrderRepo(getApplicationContext());
        List<Order2> order2s = db.getOrderTable();
        orderAdapter = new OrderSpinnerAdapter(OrderDetailDetail.this, android.R.layout.simple_spinner_item, order2s);
        mSpinrOrder.setAdapter(orderAdapter);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    public boolean onOptionItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

