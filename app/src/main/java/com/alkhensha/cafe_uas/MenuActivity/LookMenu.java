package com.alkhensha.cafe_uas.MenuActivity;

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
import com.alkhensha.cafe_uas.Repo.MenuRepo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by khenshaa on 1/29/18.
 */

public class LookMenu extends ListActivity implements android.view.View.OnClickListener {

    Button btnAdd, btnGetAll;
    TextView menu_Id;

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnAdd)) {

            Intent intent = new Intent(this, MenuDetail.class);
            intent.putExtra("menu_Id", 0);
            startActivity(intent);

        } else {

            MenuRepo repo = new MenuRepo(this);

            ArrayList<HashMap<String, String>> menuList = repo.getMenuList();
            if (menuList.size() != 0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        menu_Id = (TextView) view.findViewById(R.id.menu_Id);
                        String menuId = menu_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), MenuDetail.class);
                        objIndent.putExtra("menu_Id", Integer.parseInt(menuId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(LookMenu.this, menuList, R.layout.view_menu_entry, new String[]{"id", "name", "harga", "jenis"},
                        new int[]{R.id.menu_Id, R.id.menu_name, R.id.menu_harga, R.id.menu_jenis});
                setListAdapter(adapter);
            } else {
                Toast.makeText(this, "No menu!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilihan_menu);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

    }
}


