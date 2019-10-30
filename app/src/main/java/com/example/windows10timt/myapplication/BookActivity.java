package com.example.windows10timt.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    EditText et_id,et_title,et_author;
    Button bt_luu,bt_select,bt_exit,bt_update;
    GridView gr_display;
    //DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Anhxa();

//        bt_luu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Book book = new Book();
//                book.setId(Integer.parseInt(et_id.getText().toString()));
//                book.setTitle(et_title.getText().toString());
//                book.setAuthor(Integer.parseInt(et_author.getText().toString()) );
//                if (dbHelper.insertBook(book))
//                    Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(getApplicationContext(),"Khong thanh cong", Toast.LENGTH_SHORT).show();
//            }
//        });
//        bt_select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ArrayList<String> list = new ArrayList<>();
//                ArrayList<Book> bookArrayList = new ArrayList<>();
//
//                String id= et_id.getText().toString();
//                if (id.equals(null)) {
//                    bookArrayList = dbHelper.getAllBook();
//                }else {
//                    bookArrayList = dbHelper.getBook(Integer.parseInt(id));
//                }
//
//                for (Book b:bookArrayList){
//                    list.add(b.getId()+"");
//                    list.add(b.getTitle());
//                    list.add(b.getAuthor()+"");
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<>(BookActivity.this,android.R.layout.simple_list_item_1,list);
//                gr_display.setAdapter(adapter);
//            }
//        });
        bt_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                values.put("id_book",et_id.getText().toString());
                values.put("title",et_title.getText().toString());
                values.put("id_author",et_author.getText().toString());
                String uri="content://com.example.windows10timt.myapplication";
                Uri book=Uri.parse(uri);
                Uri insert_uri=getContentResolver().insert(book,values);
                Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_SHORT).show();

            }
        });
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                String uri = "content://com.example.windows10timt.myapplication";
                Uri book= Uri.parse(uri);
                Cursor cursor= getContentResolver().query(book,null,null,null, "title");

                if (cursor!=null){
                    cursor.moveToFirst();
                    do {
                        list.add(cursor.getInt(0)+"");
                        list.add(cursor.getString(1));
                        list.add(cursor.getInt(2)+"");
                    }
                    while (cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(BookActivity.this,android.R.layout.simple_list_item_1,list);
                    gr_display.setAdapter(adapter);
                }
                else
                    Toast.makeText(getApplicationContext(),"Khong co ket qua", Toast.LENGTH_SHORT).show();
            }
        });
//        bt_exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    public void Anhxa() {
        et_id= findViewById(R.id.edtMs);
        et_title= findViewById(R.id.edtTd);
        et_author=findViewById(R.id.edtTg);
        gr_display =findViewById(R.id.grid);
        //dbHelper = new DBHelper(this);
        bt_luu = findViewById(R.id.btnSae);
        bt_select=findViewById(R.id.btnSelect);
        bt_exit=findViewById(R.id.btnexit);
        bt_update= findViewById(R.id.btnUp);

    }

}
