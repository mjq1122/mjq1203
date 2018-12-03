package com.bwei.mjqweek01test;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2018/12/3.
 */

public class MyView extends FrameLayout {
    private List<String> list=new ArrayList<>();
    private int index;

    public MyView(@NonNull Context context) {
        super(context);
    }

    public MyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //获取父控件的宽度
        int width = getWidth();
        //记录行数
        int row=1;
        //获取子控件信息
        int disWidth=10;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            final String  tag = (String) view.getTag();
            list.add(tag);
            index = i;
            //子控件的宽度
            int viewWidth = view.getWidth();
            //子控件的高度
            int viewHeight = view.getHeight();
            //判断行数信息
            if(disWidth+viewWidth>width){
                row++;
                disWidth=10;
            }
            view.layout(disWidth,viewHeight*row,disWidth+viewWidth,viewHeight*(row+1));
            disWidth+=viewWidth;
        }
        /*view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),list.get(index).toString(),Toast.LENGTH_SHORT).show();
            }
        });*/

    }

}
