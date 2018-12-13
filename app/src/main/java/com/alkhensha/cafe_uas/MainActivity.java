package com.alkhensha.cafe_uas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.alkhensha.cafe_uas.MenuActivity.LookMenu;
import com.alkhensha.cafe_uas.OrderActivity.LookOrder;

public class MainActivity extends AppCompatActivity{

    public ImageButton TakeOrder, seeMenu;


    public void init(){
        seeMenu = (ImageButton) findViewById(R.id.seeMenu);
        seeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kemenu = new Intent(MainActivity.this, LookMenu.class);

                startActivity(kemenu);
            }
        });
    }

    public void init2(){
        TakeOrder = (ImageButton) findViewById(R.id.TakeOrder);
        TakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keorder = new Intent(MainActivity.this, PilihActivityOrder.class);

                startActivity(keorder);
            }
        });
    }




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        init2();
    }

}