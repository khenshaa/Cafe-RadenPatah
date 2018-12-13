package com.alkhensha.cafe_uas.OrderDetailActivity;

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
import com.alkhensha.cafe_uas.Repo.Order_DetailRepo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by khenshaa on 2/4/18.
 */

public class LookOrderDetail extends ListActivity implements android.view.View.OnClickListener{

    Button btnAddDetail, btnGetAllDetail;
    TextView orderdetail_Id;

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnAddDetail)) {

            Intent intent = new Intent(this, OrderDetailDetail.class);
            intent.putExtra("orderdetail_Id", 0);
            startActivity(intent);

        }
        else {

            Order_DetailRepo repo = new Order_DetailRepo(this);

            ArrayList<HashMap<String, String>> orderdetailList = repo.getOrderDetailList();
            if (orderdetailList.size() != 0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        orderdetail_Id = (TextView) view.findViewById(R.id.orderdetail_Id);
                        String orderdetailId = orderdetail_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), OrderDetailDetail.class);
                        objIndent.putExtra("orderdetail_Id", Integer.parseInt(orderdetailId));
                        startActivity(objIndent);
                    }
                });
              ListAdapter adapter = new SimpleAdapter(LookOrderDetail.this, orderdetailList, R.layout.view_detail_order_entry, new String[]{"iddetail", "idorder", "idmenu", "qty", "total"},
                       new int[]{R.id.orderdetail_Id, R.id.id_order, R.id.id_menu, R.id.qty, R.id.total});
               setListAdapter(adapter);
            } else {
                Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilihan_detailorder);

        btnAddDetail = (Button) findViewById(R.id.btnAddDetail);
        btnAddDetail.setOnClickListener(this);

        btnGetAllDetail = (Button) findViewById(R.id.btnGetAllDetail);
        btnGetAllDetail.setOnClickListener(this);

    }
}
