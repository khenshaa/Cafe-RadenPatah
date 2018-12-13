package com.alkhensha.cafe_uas.OrderActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alkhensha.cafe_uas.Model.Order2;
import com.alkhensha.cafe_uas.R;
import com.alkhensha.cafe_uas.Repo.OrderRepo;

/**
 * Created by khenshaa on 2/3/18.
 */

public class OrderDetail  extends ActionBarActivity implements android.view.View.OnClickListener {


    Button btnSave, btnDelete;
    Button btnClose;
    EditText editNoTable, editTotal;
    private int _Order_Id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editNoTable = (EditText) findViewById(R.id.editNoTable);
        editTotal = (EditText) findViewById(R.id.editTotal);


        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        _Order_Id = 0;
        Intent intent = getIntent();
        _Order_Id = intent.getIntExtra("order_Id", 0);
        OrderRepo repo = new OrderRepo(this);
        Order2 order2 = new Order2();
        order2 = repo.getOrderById(_Order_Id);

        editNoTable.setText(String.valueOf(order2.notable));
        editTotal.setText(String.valueOf(order2.totalharga));

    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.btnSave)) {
            OrderRepo repo = new OrderRepo(this);
            Order2 order2 = new Order2();
            order2.notable = Integer.parseInt(editNoTable.getText().toString());
            order2.totalharga = Float.parseFloat(editTotal.getText().toString());

            order2.order_ID = _Order_Id;

            if (_Order_Id == 0) {
                _Order_Id = repo.insert(order2);
                Toast.makeText(this, "New Order Insert", Toast.LENGTH_SHORT).show();

            } else {
                repo.update(order2);
                Toast.makeText(this, "Order record Updated", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v == findViewById(R.id.btnDelete)) {
            OrderRepo repo  = new OrderRepo(this);
            repo.delete(_Order_Id);
            Toast.makeText(this, "Order record Deleted", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if(v == findViewById(R.id.btnClose)){
            finish();
        }
    }

}


