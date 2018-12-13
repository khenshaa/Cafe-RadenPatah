package com.alkhensha.cafe_uas.OrderActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alkhensha.cafe_uas.R;
import com.alkhensha.cafe_uas.Repo.OrderRepo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by khenshaa on 2/3/18.
 */

public class LookOrder extends ListActivity implements android.view.View.OnClickListener {

    Button btnAddOrder, btnGetAllOrder;
    TextView order_Id;

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnAddOrder)) {

            Intent intent = new Intent(this, OrderDetail.class);
            intent.putExtra("order_Id", 0);
            startActivity(intent);
        } else {

            OrderRepo repo = new OrderRepo(this);

            ArrayList<HashMap<String, String>> orderList = repo.getOrderList();
            if (orderList.size() != 0) {
                ListView lvi = getListView();
                lvi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        order_Id = (TextView) view.findViewById(R.id.order_Id);
                        String orderId = order_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), OrderDetail.class);
                        objIndent.putExtra("order_Id", Integer.parseInt(orderId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(LookOrder.this, orderList, R.layout.view_order_entry, new String[]{"idorder", "notable", "totalharga"},
                        new int[]{R.id.order_Id, R.id.notable, R.id.totalharga});
                setListAdapter(adapter);
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilihan_order);

        btnAddOrder = (Button) findViewById(R.id.btnAddOrder);
        btnAddOrder.setOnClickListener(this);

        btnGetAllOrder = (Button) findViewById(R.id.btnGetAllOrder);
        btnGetAllOrder.setOnClickListener(this);
    }
}
