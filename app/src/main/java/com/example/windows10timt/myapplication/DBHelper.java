package com.example.windows10timt.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context) {
        super(context, "BookDatabase.sqlite", null, 1);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Authors(id_author integer primary key,name text,address text,email text)");

        db.execSQL("create table Books("+
                "id_Book integer primary key,"+
                "title text, "+" id_author integer "+
                "constraint id_author References Authors(id_author) "+
                "On Delete Cascade ON UPDATE CASCADE)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Book");
        onCreate(db);
    }
    public boolean insertAuthor(Author author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_author",author.getId_author());
        contentValues.put("name",author.getName());
        contentValues.put("address",author.getAddress());
        contentValues.put("email",author.getEmail());
        long rows = db.insert("Author",null,contentValues);
        db.close();
        if(rows > 0)
        {
            return true;
        }
        return false;
    }

    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors", null);
        if(cursor !=null)
            cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            list.add(new Author(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return  list;
    }


    public boolean insertBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",book.getId());
        contentValues.put("title",book.getTitle());
        contentValues.put("author",book.getAuthor());
        db.insert("Book",null,contentValues);
        return true;
    }


    public ArrayList<Book> getAllBook(){
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Book", null);
        if(cursor !=null)
            cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            list.add(new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Book> getBook(int id){
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Book where id ="+id,null);
        if (cursor!= null)
            cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            list.add(new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }



}
