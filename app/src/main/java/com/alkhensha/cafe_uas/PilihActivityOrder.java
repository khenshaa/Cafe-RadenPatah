package com.alkhensha.cafe_uas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.alkhensha.cafe_uas.MenuActivity.LookMenu;
import com.alkhensha.cafe_uas.OrderActivity.LookOrder;
import com.alkhensha.cafe_uas.OrderDetailActivity.LookOrderDetail;

/**
 * Created by khenshaa on 2/3/18.
 */

public class PilihActivityOrder extends AppCompatActivity{



    Button btnkeorder, btnkedetail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takeorder);

        init();
        init2();
    }

    public void init(){
        btnkeorder = (Button) findViewById(R.id.btnkeorder);
        btnkeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keorder = new Intent(PilihActivityOrder.this, LookOrder.class);

                startActivity(keorder);
            }
        });
    }

    public void init2(){
        btnkedetail = (Button) findViewById(R.id.btnkedetail);
        btnkedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keorder = new Intent(PilihActivityOrder.this, LookOrderDetail.class);

                startActivity(keorder);
            }
        });
    }



}


