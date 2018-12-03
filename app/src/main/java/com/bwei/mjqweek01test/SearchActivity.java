package com.bwei.mjqweek01test;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText edit_text;
    private Button btn_add;
    private MyView main_myView;
    private Button btn_clear;
    private List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //初始化控件
        initViews();
        //数据库
        final DataBase dataBase = new DataBase(SearchActivity.this);
        final SQLiteDatabase database = dataBase.getWritableDatabase();
        List<String> list1 = dataBase.queryView();
        for (int i = 0; i <list1.size() ; i++) {
            dataBase.addView(list1.get(i));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            SearchActivity.this.list.add(list1.get(i));
            TextView textView = new TextView(SearchActivity.this);
            main_myView.addView(textView,params);
        }
        //点击按钮进行监听
        //搜索进行添加历史记录
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                String text = edit_text.getText().toString();
                SearchActivity.this.list.add(text);
                TextView textView = new TextView(SearchActivity.this);
                textView.setTextSize(20);
                textView.setText(SearchActivity.this.list.get(SearchActivity.this.list.size()-1));
                textView.setPadding(10,10,10,10);
                main_myView.addView(textView,params);
                dataBase.addView(text);
            }
        });
        //点击进行清空记录
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_myView.removeAllViews();
                dataBase.delView();
            }
        });

    }

    private void initViews() {
        edit_text = findViewById(R.id.edit_text);
        btn_add = findViewById(R.id.btn_add);
        main_myView = findViewById(R.id.main_myView);
        btn_clear = findViewById(R.id.btn_clear);
    }
}
