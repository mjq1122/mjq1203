package com.bwei.mjqweek01test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2018/12/3.
 */

public class DataBase extends SQLiteOpenHelper {

    private final SQLiteDatabase database;

    public DataBase(Context context) {
        super(context, "flow.db", null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table search(id integer primary key autoincrement,keys text)");
    }
    public void addView(String keys){
        ContentValues values=new ContentValues();
        values.put("keys",keys);
        database.insert("search",null,values);
    }
    public List<String> queryView(){
        List<String> list=new ArrayList<>();
        Cursor query = database.query("search", null, null, null, null, null, null);
        while (query.moveToNext()){
            String keys = query.getString(query.getColumnIndex("keys"));
            list.add(keys);
        }
        return list;
    }
    public void delView(){
        database.delete("search",null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
