package com.alkhensha.cafe_uas.MenuActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alkhensha.cafe_uas.Model.Menu;
import com.alkhensha.cafe_uas.R;
import com.alkhensha.cafe_uas.Repo.MenuRepo;

/**
 * Created by khenshaa on 1/29/18.
 */
public class MenuDetail extends ActionBarActivity implements android.view.View.OnClickListener, AdapterView.OnItemClickListener {

    Button btnSave, btnDelete;
    Button btnClose;
    EditText editName, editHarga, editJenis;

    private int _Menu_Id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editName = (EditText) findViewById(R.id.editName);
        editHarga = (EditText) findViewById(R.id.editHarga);
        editJenis = (EditText) findViewById(R.id.editJenis);


        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        _Menu_Id = 0;
        Intent intent = getIntent();
        _Menu_Id = intent.getIntExtra("menu_Id", 0);
        MenuRepo repo = new MenuRepo(this);
        Menu menu = new Menu();
        menu = repo.getMenutById(_Menu_Id);

        editName.setText(menu.name);
        editHarga.setText(String.valueOf(menu.harga));
        editJenis.setText(menu.jenis);





    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.btnSave)) {
            MenuRepo repo = new MenuRepo(this);
            Menu menu = new Menu();
            menu.name = editName.getText().toString();
            //menu.harga = Integer.parseInt(editHarga.getText().toString());
            menu.setHarga(Float.parseFloat(editHarga.getText().toString()));
            menu.jenis = editJenis.getText().toString();
            menu.menu_ID = _Menu_Id;

            if (_Menu_Id == 0) {
                _Menu_Id = repo.insert(menu);
                Toast.makeText(this, "New Menu Insert", Toast.LENGTH_SHORT).show();

            } else {
                repo.update(menu);
                Toast.makeText(this, "Menu Record Updated", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v == findViewById(R.id.btnDelete)) {
                MenuRepo repo  = new MenuRepo(this);
                repo.delete(_Menu_Id);
                Toast.makeText(this, "Menu record Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
            else if(v == findViewById(R.id.btnClose)){
                finish();
            }
        }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

