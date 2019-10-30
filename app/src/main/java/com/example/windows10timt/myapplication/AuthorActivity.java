package com.example.windows10timt.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity {
    EditText et_id,et_name,et_address,et_email;
    Button bt_luu,bt_select,bt_exit,bt_update;
    GridView gr_display;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Anhxa();

        bt_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Author author = new Author();
                author.setId_author(Integer.parseInt(et_id.getText().toString()));
                author.setName(et_name.getText().toString());
                author.setAddress(et_address.getText().toString());
                author.setEmail(et_email.getText().toString() );
                if (dbHelper.insertAuthor(author))
                    Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Khong thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Author> list = new ArrayList<>();
                ArrayList<String> bookArrayList = new ArrayList<>();

                list=dbHelper.getAllAuthor();
                for (Author author:list){
                    bookArrayList.add(author.getId_author() +"");
                    bookArrayList.add(author.getName());
                    bookArrayList.add(author.getAddress());
                    bookArrayList.add(author.getEmail());
                }
                ArrayAdapter adapter = new ArrayAdapter(AuthorActivity.this,android.R.layout.simple_list_item_1,list);
                gr_display.setAdapter(adapter);
            }
        });
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Anhxa() {
        et_id= findViewById(R.id.edtMs);
        et_name= findViewById(R.id.edtHoTen);
        et_address=findViewById(R.id.edtTd);
        et_email=findViewById(R.id.edtTg);
        gr_display =findViewById(R.id.grid);
        dbHelper = new DBHelper(this);
        bt_luu = findViewById(R.id.btnSae);
        bt_select=findViewById(R.id.btnSelect);
        bt_exit=findViewById(R.id.btnexit);
        bt_update= findViewById(R.id.btnUp);

    }

}
